#!groovy
pipeline {
    agent any
    tools {
        maven 'Maven 3.3'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                // git url: 'https://github.com/rgielen/spring-boot-thymeleaf.git'
            }
        }

        stage('Build') {
            steps {
                // Run the maven build
                sh 'mvn clean compile'
            }
        }

        stage('Acceptance Test') {
            agent {
                docker { image 'cloudbees/java-build-tools' }
            }
            steps {
                sh 'env'
                sh '''  
                        unset M2_HOME
                        mvn verify
                   '''
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }
        }
    }

}
