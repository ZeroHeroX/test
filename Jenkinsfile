pipeline {
    agent any
    environment {
        // 防止Jenkins杀死后台进程（Pipeline类型必须用这个变量）
        JENKINS_NODE_COOKIE = 'dontKillMe'
    }
    tools {
        maven 'maven_3.9.9'
        jdk 'jdk_17.0.14'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                     url: 'https://github.com/ZeroHeroX/test',
                     credentialsId: '5c2a6364-0c99-4f1f-9988-fc9fb9ac850b'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Stop Old Process') {
            steps {
                script {
                    // 终止旧进程（避免端口冲突）
                    sh '''
                        if [ -f app.pid ]; then
                            kill -9 $(cat app.pid) || true
                            rm -f app.pid
                        fi
                    '''
                    echo "旧进程已终止"
                }
            }
        }

        stage('Run') {
            steps {
                script {
                    // 启动新进程并记录PID
                    sh '''
                        nohup java -jar target/test-001.jar > app.log 2>&1 &
                        echo $! > app.pid
                    '''
                    echo '应用已启动，PID: $(cat app.pid)'
                }
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/*.jar, app.log', allowEmptyArchive: true
        }
        cleanup {
            // 清理残留文件（可选）
            deleteDir()
        }
    }
}