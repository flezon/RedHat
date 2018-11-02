node {
  def branchVersion = ""

  stage ('Checkout') {
    // checkout repository
    checkout scm

    // checkout input branch 
    sh "git checkout ${caller.env.BRANCH_NAME}"
  }

  stage ('Determine Branch Version') {
    // add maven to path
    env.PATH = "${tool 'M3'}/bin:${env.PATH}"

    // determine version in pom.xml
    def pomVersion = sh(script: 'mvn -q -Dexec.executable=\'echo\' -Dexec.args=\'${project.version}\' --non-recursive exec:exec', returnStdout: true).trim()

    // compute proper branch SNAPSHOT version
    pomVersion = pomVersion.replaceAll(/-SNAPSHOT/, "") 
    branchVersion = env.BRANCH_NAME
    branchVersion = branchVersion.replaceAll(/origin\//, "") 
    branchVersion = branchVersion.replaceAll(/\W/, "-")
    branchVersion = "${pomVersion}-${branchVersion}-SNAPSHOT"

    // set branch SNAPSHOT version in pom.xml
    sh "mvn versions:set -DnewVersion=${branchVersion}"
  }

  stage ('Java Build') {
    // build .war package
    sh 'mvn clean package -U'
  }
}