pipeline {
    agent any 
    tools {
        jdk 'openjdk-1.8'
        maven 'Maven_3_6_3'
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
    }
}