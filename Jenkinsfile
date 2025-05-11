pipeline {
    agent any  // 在任何可用节点执行
    tools {
        maven 'maven_3.9.9'  // 需在 Jenkins 全局工具中预配置 Maven
        jdk 'jdk_17.0.14'         // 需配置 JDK 名称
    }
    stages {
        // 阶段 1: 拉取代码
        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/ZeroHeroX/test',
                credentialsId: '5c2a6364-0c99-4f1f-9988-fc9fb9ac850b'  // Jenkins 中配置的 Git 凭据 ID
            }
        }

        // 阶段 2: 编译与打包
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'  // 跳过测试以加速构建
            }
        }

        // 阶段 3: 运行应用
        stage('Run') {
            steps {
                script {
                    // 后台运行并记录 PID
                    sh 'nohup java -jar target/test-001.jar > /dev/null 2>&1 & echo $! > app.pid'
                    echo "应用已启动，PID 存储在 app.pid 文件中"
                }
            }
        }
    }
    post {
        // 构建后清理
        always {
            archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: true  // 保存构建产物
//             junit 'target/surefire-reports/**/*.xml'  // 收集测试报告（即使跳过测试）
        }
    }
}