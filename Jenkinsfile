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
                withSonarQubeEnv(installationName: 'sq1')
                    sh 'mvn sonar:sonar'
                
            }
        }

    
    }
      }


