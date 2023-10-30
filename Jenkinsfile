pipeline {
    agent any
    stages {
        stage('Get source Code') {
              steps {
                 script {
                  checkout([$class: 'GitSCM', branches: [[name: 'feat/moetazhajji_SAE6_G2']], userRemoteConfigs: [[url: 'https://github.com/MoetazHajji/sae6_g2_skistation-.git']]])
                        }
                    }
         }
         stage('Compile with Maven') {
            steps {
                script {
                       sh 'mvn clean install' 
                }
            }
        }
        stage('SonarQube analyse') {
            steps {
                withSonarQubeEnv(installationName: 'SonarQubeTests') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar -Dsonar.java.binaries=target/classes'
                }
            }
        }
        stage('Deployment with maven') {
            steps {
              sh 'mvn deploy'
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
