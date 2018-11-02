node {
   // Mark the code checkout 'stage'.... 
   stage 'Checkout'
      // Get some code from a GitHub repository 
      git url: 'https://github.com/flezon/RedHat.git'
      // Get the maven tool. // ** NOithubTE: This 'M3' maven tool must be configured
      // ** in the global configuration. 
      def mvnHome = tool 'M3'

      env.JAVA_HOME="${tool 'jdk'}"
      env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
      bat 'java -version'      
   
   // Mark the code build 'stage'.... 
   stage 'Build' 
      // Run the maven build 
      bat "${mvnHome}/bin/mvn clean install" 
      
   stage 'Artifactory configuration'
        // Obtain an Artifactory server instance, defined in Jenkins --> Manage:
        server = Artifactory.server SERVER_ID

        rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = MAVEN_TOOL // Tool name from Jenkins configuration
        rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
        rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
        rtMaven.deployer.deployArtifacts = false // Disable artifacts deployment during Maven run

        buildInfo = Artifactory.newBuildInfo()
    
}