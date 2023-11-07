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


          stage('Nettoyage et compilation Maven') {
                    steps {
                         // Cette étape va nettoyer et compiler le projet avec Maven
                         sh 'mvn clean compile'
                     }
                 }

          stage('Compile with Maven') {
               steps {
                          script {
                                 sh 'mvn clean install'
                                 }
                     }


            }
            stage('Mockito test') {
                                 steps {
                                     script {
                                         sh 'mvn test'
                                     }
                                 }
                           }

           stage('SonarQube analyse') {
                                 steps {
                                   script {
                                 withSonarQubeEnv(installationName: 'sq') {
                                     sh 'mvn sonar:sonar'
                                                                           }
                                          }
                                        }
                                       }

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

                /*  stage('Build Docker Image') {
                     steps {
                         echo 'Construction de l\'image Docker'
                         script {
                             sh 'docker build -t siwaratiya/gestion-station-ski:latest .'
                         }
                     }
                 }*/

               //  stage('Push vers DockerHub') {
              //   steps {
               //     script {
                //        sh 'docker push siwaratiya/gestion-station-ski:lates'
                //  }
            //  }
         // }


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
                      }
                      */

         stage('Docker Compose') {
              steps {
                   script {
                        sh 'docker compose down'
                        sh 'docker compose up -d'
                  }
               }
           }

    }}
