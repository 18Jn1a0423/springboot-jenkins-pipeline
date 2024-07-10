pipeline {
    agent any
    tools {
        maven 'Maven 3.9.2'
    }
    stages {
         stage('Git Clone') {
             steps {
		     checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/18Jn1a0423/springboot-jenkins-pipeline.git']]])

             } 
         }
         stage('Build Maven') {
             steps {
                 bat 'mvn clean install'
             }
         }   
         stage('Image Build') {
            steps {
                script {
                    def imageExists = bat(script: 'docker images -q springboot-jenkins', returnStdout: true).trim()
                    if (imageExists == '') {
                        bat 'docker stop springboot-jenkins'
                        bat 'docker rm -f springboot-jenkins'
                        bat 'docker rmi -f springboot-jenkins'
                    }
                    bat 'docker build -t springboot-jenkins .'
                }
            }
        }
        stage('Run the container') {
            steps {
                script {
                    def containerExists = bat(script: 'docker ps -a -q -f name=springboot-jenkins', returnStatus: true)
                    if (containerExists == 0) {
                        // bat 'docker stop springboot-jenkins'
                        bat 'docker rm -f springboot-jenkins'
                    }
                    bat 'docker run -d -p 8585:8080 --name springboot-jenkins springboot-jenkins'
                }
            }
        }

	   
}
       
 }
    







