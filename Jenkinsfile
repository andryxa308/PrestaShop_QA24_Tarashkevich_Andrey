pipeline {
    agent any

    tools {

        maven "M3"
        jdk 'JDK19'
    }

    triggers {
        parameterizedCron('''
        0 21 * * 0-6 %SUITE=smokeTest.xml;BROWSER=Chrome;HEADLESS=true;

        ''')
    }

    parameters {
        gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
        choice(name: 'SUITE', choices: ['suites/smokeTest.xml', 'suites/regressionTest.xml'], description: 'Choose suite to run')

    }

    stages {
        stage('Run test') {
            steps {
                git branch: "${params.BRANCH}", url: 'https://github.com/andryxa308/PrestaShop_QA24_Tarashkevich_Andrey'

                sh "mvn -Dmaven.test.failure.ignore=true -Dsuite=${params.SUITE} -Dbrowser=${params.BROWSER} -Dheadless=${params.HEADLESS} clean test"

            }

            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'

                }
            }
        }

        stage('Generate Allure report') {
    steps {
         script {
            allure([
             includeProperties: false,
             jdk: '',
             properties: [],
             reportBuildPolicy: 'ALWAYS',
             results: [[path: 'target/allure-results']]
             ])
        }
       }
}
}
}