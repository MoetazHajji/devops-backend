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
