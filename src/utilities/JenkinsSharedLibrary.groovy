package utilities

/*
Jenkins shared library class which consists of the different stages as class functions

*/
class JenkinsSharedLibrary implements Serializable
{
      /*
      Declaring steps variable which is used to assign steps instance inside the class constructor
      */
      def steps

      /*

      JenkinsSharedLibrary class Constructor which initial class variable steps

      */

      JenkinsSharedLibrary(steps)
      {
          this.steps = steps
      }

      /*
      Here the checkout function handles the checkout stage which takes git_url and node_label as the function arguments.

      git_url = Is the github link of the Job

      node_label = Is an array of node labels where the repository needs to be checkout

      */
      
      def preparation(){
            steps.stage('Preparation') {
    steps.node("kubernetes") {
      steps.container("test-centos") {
           
       steps.git 'https://github.com/sukeshsangam/jenkins-gke-shared.git';
       // steps.cat > test.txt
       steps.echo "hello"
         //   steps.sh 'cd /'
        //junit '**/target/surefire-reports/TEST-*.xml'
     // steps.sh 'pwd'
       // steps.archiveArtifacts ('**')
   
      
         //  steps.sh 'cd /'
        //junit '**/target/surefire-reports/TEST-*.xml'
    //steps.sh 'pwd'
  //steps.archiveArtifacts ('**')
        //  steps.sh "docker"
        //  steps.sh "sudo yum install -y yum-utils"
         // steps.sh "sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo"
         // steps.sh "yum-config-manager --enable docker-ce-nightly"
         // steps.sh "sudo yum install docker-ce docker-ce-cli containerd.io"
         // steps.sh "sudo systemctl start docker"
          
         //  def customImage = steps.docker.build("my-image:78")
          
          //steps.sh "yum update && yum -y install sudo --stdin root"
          //steps.sh "sudo yum check-update"
         // steps.sh "curl -fsSL https://get.docker.com/ | sh"
         steps.sh "sudo rm -rf  /var/lib/docker"
         steps.sh "sudo systemctl enable docker"
          steps.sh "sudo systemctl start docker"
          steps.sh "sudo systemctl status docker"
          def customImage = steps.docker.build("my-image:78")
          customImage.push()
      }
    }
  }     
}
}
