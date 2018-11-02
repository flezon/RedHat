node {
 
   // Mark the code checkout 'stage'.... 

   stage 'Checkout' 
   // Get some code from a GitHub repository 
   git url: 'https://github.com/flezon/RedHat.git'
   // Get the maven tool. // ** NOTE: This 'M3' maven tool must be configured
   // ** in the global configuration. 
   def mvnHome = tool 'M3'
   
   env.JAVA_HOME="${tool 'jdk'}"
   env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
   bat 'java -version'
   // Mark the code build 'stage'.... 

   stage 'Build' 

   // Run the maven build 

   bat "${mvnHome}/bin/mvn clean install" 

}