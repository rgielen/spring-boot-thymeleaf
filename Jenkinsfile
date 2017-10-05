#!groovy
node {

    stage('Checkout') {
        checkout scm
        // git url: 'https://github.com/rgielen/spring-boot-thymeleaf.git'
    }

    stage('Build') {
        withMaven(maven: 'Maven 3.3') {

            // Run the maven build
            sh "mvn clean compile"
        }
    }

    stage('Acceptance Test') {
        agent {
            docker { image 'markhobson/maven-chrome' }
        }
        steps {
            sh 'mvn verify"'
        }
        post {
            success {
                junit 'target/surefire-reports/**/*.xml'
            }
        }
    }

}
