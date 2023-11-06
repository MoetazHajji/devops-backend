pipeline {
    agent any
    
    stages {

      //  stage('Nettoyage et compilation Maven') {
        //    steps {
                // Cette étape va nettoyer et compiler le projet avec Maven
          //      sh 'mvn clean compile'
            //}
        //}
          stage('SonarQube analyse') {
            steps {
              script {
            withSonarQubeEnv(installationName: 'sq') {
                sh 'mvn sonar:sonar'
            }
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
        stage('Mockito test') {
            steps {
                script {
                    sh 'mvn test'
                }
            }


      }
         stage('Build Docker Image') {
            steps {
                echo 'Construction de l\'image Docker'
                script {
                    sh 'docker build -t siwaratiya/gestion-station-ski:latest .'
                }
            }
        }

      //  stage('Push vers DockerHub') {
     //   steps {
      //     script {
       //        sh 'docker push siwaratiya/gestion-station-ski:lates'
       //  }
   //  }
// }

    }}
