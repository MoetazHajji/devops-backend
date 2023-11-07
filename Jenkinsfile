pipeline {
    agent any

        environment {
            NEXUS_VERSION = "nexus3"
            NEXUS_PROTOCOL = "http"
            NEXUS_URL = "192.168.33.10:8081"
            NEXUS_REPOSITORY = "maven-releases"
            NEXUS_CREDENTIAL_ID = "nexus-credentials"
            }
    
    stages {


          stage('mvn') {
              steps {
                   script {
                        sh 'mvn clean install -DskipTests'
                  }
               }
           }
          /*  stage('build') {
                         steps {
                              script {
                                   sh 'docker build -t gestion-station-ski .'
                             }
                          }
                      }*/
                      stage("publish to nexus") {
                                steps {
                                    script {
                                        artifactPath = "target/gestion-station-ski-1.0.jar";



                                        nexusArtifactUploader(
                                                nexusVersion: NEXUS_VERSION,
                                                protocol: NEXUS_PROTOCOL,
                                                nexusUrl: NEXUS_URL,
                                                groupId: 'tn.esprit',
                                                version: '1.0',
                                                repository: NEXUS_REPOSITORY,
                                                credentialsId: NEXUS_CREDENTIAL_ID,
                                                artifacts: [
                                                        // Artifact generated such as .jar, .ear and .war files.
                                                        [artifactId: 'gestion-station-ski',
                                                         classifier: '',
                                                         file      : artifactPath,
                                                         type      : 'jar']
                                                ]
                                        );

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