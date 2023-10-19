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
                withSonarQubeEnv('SonarLint') {
                    sh 'mvn sonar:sonar -Dsonar.login=VOTRE_MOT_DE_PASSE_SONAR'
                }
            }
        }
      }
    }

