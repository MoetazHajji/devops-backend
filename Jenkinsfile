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
        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES' ) {
                waitForQualityGate abortPipeline: true
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
