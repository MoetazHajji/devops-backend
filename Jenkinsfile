pipeline {
    agent any
    
    stages {


          stage('mvn') {
              steps {
                   script {
                        sh 'mvn clean install -DskipTests'
                  }
               }
           }
         stage('Docker Compose') {
              steps {
                   script {
                        sh 'docker compose down'
                        sh 'docker compose up -d'
                  }
               }
           }

    }}