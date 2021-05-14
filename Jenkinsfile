pipeline {
    agent any 
  
    stages {
        stage('Compile and Clean') { 
            steps {
                bat "mvn clean compile"
            }
        }        
        stage('Test') { 
            steps {
                bat "mvn test"
            }     
                
        }
        stage('Deploy') { 
            steps {
                bat "mvn package"
            }
        }      
        stage('Archving') { 
            steps {
                 archiveArtifacts '**/target/*.jar'
            }
        }
        stage('Deploying'){
        	steps{
        	  bat 'copy **/target/*.jar "C:\\Program Files\\Apache Software Foundation\\apache-tomcat-9.0.45\\webapps\\"'
        	}
        }
    }
}