def call(body)
{
podTemplate(label: 'kubernetes',
  containers: [
    containerTemplate(name: 'test-centos', image: 'centos:centos7', ttyEnabled: true, command: 'cat')
  ]) {
  stage('Preparation') {
    node("kubernetes") {
      container("test-centos") {
        
        git 'https://github.com/sukeshsangam/jenkins-gke-shared.git';
        sh "echo hello"
        //junit '**/target/surefire-reports/TEST-*.xml'
        //archive 'target/*.jar'
      }
    }
  }
}
}
