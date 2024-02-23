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
		     	bat "docker build -t=rnk97/selenium ."
		     }
		}
		
		stage('Push Image'){
		   steps{
		        bat "docker push rnk97/selenium"
		   }
		}
	
	}

}