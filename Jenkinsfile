/* Requires the Docker Pipeline plugin */
pipeline {
    agent { docker { image 'gradle:8.0.2-jdk11-alpine' } }
    stages {
        stage('build') {
            steps {
                sh 'gradle build'
            }
        }
    }
}
