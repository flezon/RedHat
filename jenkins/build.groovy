node {
 
   // Mark the code checkout 'stage'.... 

   stage ('Checkout') {
   // Get some code from a GitHub repository 
      git url: 'https://github.com/flezon/RedHat.git'
      // Get the maven tool. // ** NOTE: This 'M3' maven tool must be configured
      // ** in the global configuration. 
      def mvnHome = tool 'M3'

      env.JAVA_HOME="${tool 'jdk'}"
      env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
      bat 'java -version'
      // Mark the code build 'stage'.... 
   }
   stage ('Build') {
      // Run the maven build 
      bat "${mvnHome}/bin/mvn clean install" 

      stage ''
   }
   stage ('Analysis') {
        def mvnHome = tool 'mvn-default'
 
        sh "${mvnHome}/bin/mvn -batch-mode -V -U -e checkstyle:checkstyle pmd:pmd pmd:cpd findbugs:findbugs spotbugs:spotbugs"
 
        def checkstyle = scanForIssues tool: [$class: 'CheckStyle'], pattern: '**/target/checkstyle-result.xml'
        publishIssues issues:[checkstyle]
    
        def pmd = scanForIssues tool: [$class: 'Pmd'], pattern: '**/target/pmd.xml'
        publishIssues issues:[pmd]
         
        def cpd = scanForIssues tool: [$class: 'Cpd'], pattern: '**/target/cpd.xml'
        publishIssues issues:[cpd]
         
        def findbugs = scanForIssues tool: [$class: 'FindBugs'], pattern: '**/target/findbugsXml.xml'
        publishIssues issues:[findbugs]
 
        def spotbugs = scanForIssues tool: [$class: 'SpotBugs'], pattern: '**/target/spotbugsXml.xml'
        publishIssues issues:[spotbugs]
    }
}