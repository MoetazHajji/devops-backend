pipeline {
    agent any
    
    stages {

        stage('Nettoyage et compilation Maven') {
            steps {
                // Cette étape va nettoyer et compiler le projet avec Maven
                sh 'mvn clean compile'
            }
        }

        stage('SonarQube analyse') {
            steps {
               script {
            withSonarQubeEnv(installationName: 'sq1') {
                sh 'mvn sonar:sonar'
            }
        }
                
            }
        }
        stage('Quality Gate') {
            steps {
               timeout(time: 2, unit: 'MINUTES') {
                   waitForQualityGate abortPipeline: true
               }
                
            }
        }

    
    }
      }


