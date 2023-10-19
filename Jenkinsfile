pipeline {
    agent any
    stages {
        stage('Récupération du Code') {
              steps {
                 script {
                  checkout([$class: 'GitSCM', branches: [[name: 'feat/moetazhajji_SAE6_G2']], userRemoteConfigs: [[url: 'https://github.com/MoetazHajji/sae6_g2_skistation-.git']]])
                        }
                    }
         }
         stage('Compilation avec Maven') {
            steps {
                script {
                       sh 'mvn clean' 
                }
            }
        }
        stage('SonarQube analyse') {
            steps {
                withSonarQubeEnv(installationName: 'SonarQubeTests') {
                    sh './mvnw clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
                }
            }
        }
    }
        post {
        success {
            echo 'successfully.'
        }
        failure {
            echo 'Failed'
        }
    }
}
