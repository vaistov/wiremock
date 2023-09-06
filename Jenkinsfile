pipeline {
    agent any

    tools{
        gradle "8.1"
    }

    stages {
        stage('Build') {
            steps {
                git 'https://github.com/vaistov/wiremock.git'
                withGradle {
                    sh "gradle test"
                }
            }
        }
        stage('Report') {
            steps {
                allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'build/allure-results']]
                ])
            }
        }
    }
}
