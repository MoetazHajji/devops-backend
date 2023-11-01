pipeline {
    agent any
    
    stages {

        // stage('Nettoyage et compilation Maven') {
        //     steps {
        //         // Cette étape va nettoyer et compiler le projet avec Maven
        //         sh 'mvn clean install'
        //     }
        // }

        // stage('SonarQube analyse') {
        //     steps {
        //        script {
        //     withSonarQubeEnv(installationName: 'sq1') {
        //         sh 'mvn sonar:sonar'
        //     }
        // }
                
        //     }
        // }

        // stage('Mockito & JUnit test') {
        //     steps {
        //        script {
        //         sh 'mvn test'
        //       }
                
        //     }
        // }

        // stage('Nexust') {
        //     steps {
        //        script {
        //         sh 'mvn deploy'
        //       }
                
        //     }
        // }

        stage('Build et Push Docker Image') {
    steps {
        script {
            // Construire l'image Docker
            sh 'docker build -t iheblafi/gestion-station-ski:latest .'
        }
    }
}
        stage('Push vers DockerHub') {
    steps {
        script {
            sh 'docker push iheblafi/gestion-station-ski:latest'
        }
    }
}


        stage('Docker-compose') {
            steps {
               script {
                sh 'docker compose up -d'
              }
                
            }
        }

        
        // stage('Quality Gate') {
        //     steps {
        //        timeout(time: 5, unit: 'MINUTES') {
        //            waitForQualityGate abortPipeline: true
        //        }
                
        //     }
        // }

    
    }
      }


