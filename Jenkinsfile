pipeline {
    agent any
    
    stages {

        stage('Nettoyage et compilation Maven') {
            steps {
                // Cette étape va nettoyer et compiler le projet avec Maven
                sh 'mvn clean compile'
            }
        }

        // stage('SonarQube analyse') {
        //     steps {
        //         withSonarQubeEnv(installationName: 'sq1')
        //             sh 'mvn sonar:sonar'
                
        //     }
        // }

        stage('SonarQube analysis') {
      tools {
        sonarQube 'SonarQube Scanner 2.8'
      }
     steps {
                withSonarQubeEnv(installationName: 'sq1')
                    sh 'mvn sonar:sonar'
        }
      }
    }
      }
    }

