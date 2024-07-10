pipeline {
    agent any
    tools {
        maven 'Maven 3.9.2'
    }
    stages {
         stage('Git Clone') {
             steps {
                 // git branch: 'main', url: 'https:github.com/AjayPulapa/springboot-jenkins-automation.git'
		     checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/AjayPulapa/springboot-jenkins-automation.git']]])

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
        stage("Email Notification") {
            steps {
                script {
                    mail bcc: '',
                            body: '''Hi Welcome to Jenkins Email Alerts
                            Pipeline Executed Successfully
                            Thank You 
                            ''',
                            cc: 'ajayajaypulapa143@gmail.com',
                            from: '',
                            replyTo: '',
                            subject: 'Sample Jenkins',
                            to: 'ajaypulapa7864@gmail.com'
                }
            }
        }
	 // stage("Push to Docker Hub")
  //        {
  //           steps
  //            {
  //               withCredentials([string(credentialsId: 'dockerhubpasswd', variable: 'dockerhubpasswd')]) {
  //                bat "docker login -u ajaypulapa1 -p ${dockerhubpasswd}"
        
  //             }
  //            bat "docker tag devops ajaypulapa1/springboot-jenkins"
  //            bat "docker push ajaypulapa1/springboot-jenkins"
  //             }
  //         }

	    stage("Push to Docker Hub") {
    steps {
        withCredentials([string(credentialsId: 'dockerhubpasswd', variable: 'dockerhubpasswd')]) {
            bat "docker login -u ajaypulapa1 -p ${dockerhubpasswd}"
        }

        script {
             // Print available Docker images for debugging
            bat "docker images"

             // Tag the 'devops' image with the repository name
            def sourceImage = "springboot-jenkins:latest"
            def targetImage = "ajaypulapa1/springboot-jenkins:latest"
            
            bat "docker tag $sourceImage $targetImage"
            
             // Print available Docker images again for debugging
            bat "docker images"
            
             // Push the tagged image to Docker Hub
            bat "docker push $targetImage"
        }
    }
}

       
         }
    }







