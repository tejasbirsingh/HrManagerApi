pipeline {
    agent any 
    
    triggers {
        pollSCM '* * * * *'
    }
    stages {
        stage('Compile and Clean') { 
            steps {
                echo 'Started cleaning and compiling'            
                bat "mvn clean compile"
            }
        }        
        stage('Test') { 
            steps {
            	echo 'Running tests'
                bat "mvn test"
            }     
                
        }
        stage('Code Analysis'){
        	steps{
        		echo 'Analyzing the code using Sonar'
        		bat "mvn sonar:sonar"
        		}
        	}
        stage('Deploy') { 
            steps {
             	echo 'Packing the project into war file'
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
       		echo 'Deploying the project to local tomcat server'
       		deploy adapters: [tomcat9(credentialsId: '1f9a05f6-e5a4-473f-ad2c-5eb89830c861', path: '', url: 'http://localhost:9999/')], contextPath: 'api', war: '**/target/*.war'
       		}
       }
    
    }
}