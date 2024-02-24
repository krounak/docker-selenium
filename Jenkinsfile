pipeline{
	
	agent any
	
	stages{
		
		stage('Build Jar'){
			steps{
				bat "mvn clean package -DskipTest"
			}
		}
		
		stage('Build Image'){
		     steps{
		     	bat "docker build -t=rnk97/selenium:latest ."
		     }
		}
		
		stage('Push Image'){
		   environment{
		   		DOCKER_HUB = credentials('dockerhub-cred')
		   }
		   steps{
		   		bat 'docker login -u %DOCKER_HUB_USR% -p %DOCKER_HUB_PSW%'
		        bat "docker push rnk97/selenium:latest"
		        bat "docker tag rnk97/selenium:latest rnk97/selenium:${env.BUILD_NUMBER}"
		        bat "docker push rnk97/selenium:${env.BUILD_NUMBER}"
		   }
		}
	
	}
		post{
			always{
				bat "docker logout"
			}
		}
}