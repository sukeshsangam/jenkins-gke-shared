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
        
       // git 'https://github.com/sukeshsangam/jenkins-gke-shared.git';
        
       steps.sh "echo hello"
        //junit '**/target/surefire-reports/TEST-*.xml'
        //archive 'target/*.jar'
      }
    }
  }     
}
