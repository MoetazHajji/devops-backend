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
            stage('build') {
                         steps {
                              script {
                                   sh 'docker build -t gestion-station-ski .'
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