project_name = "project-jenkins/jenkins-pipeline"
repo = "https://github.com/videocursoscloud/test-jenkins-1.git"
repo_name = "repo"

folder('project-jenkins') {
    displayName('Project A')
    description('Folder for project A')
}

pipelineJob(project_name){
	definition {
		triggers{
			scm('H/1 * * * *')
		}
		cpsScm{
			scm{
				git {
					remote {
						name(repo_name)
						url(repo)
					}
				}
				scriptPath("Jenkinsfile")
			}

		}
	}
}
