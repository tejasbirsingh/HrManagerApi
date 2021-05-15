pipeline {
    agent any 
    
    triggers {
        pollSCM '* * * * *'
    }
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
        stage('Code Analysis'){
        	steps{
        		bat "mvn sonar:sonar"
        		}
        	}
        stage('Deploy') { 
            steps {
                bat "mvn package"
            }
        }      
        stage('Archiving') { 
            steps {
                 archiveArtifacts '**/target/*.war'
                 
            }
        }
       stage('Deploy on Tomcat'){
       		steps{
       		deploy adapters: [tomcat9(credentialsId: '1f9a05f6-e5a4-473f-ad2c-5eb89830c861', path: '', url: 'http://localhost:9999/')], contextPath: 'api', war: '**/target/*.war'
       		}
       }
    
    }
}