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

      def checkout(String git_url, String[] node_label)
      {
          steps.stage('checkout')
          {
              steps.echo "$node_label"
              /*
              iterating over the nodes and performing checkout of the function
              */
              for (int i = 0; i < node_label.size(); i++)
              {
                  steps.echo "####################  checkout in ${node_label[i]}  ######################"
                  steps.node((String)node_label[i])
                  {

                      /*
                      Geting report name and the branch name of the Job
                      */
                      def report = steps.sh( script: "echo ${steps.JOB_NAME} | awk -F '/' '{print \$1}'", returnStdout: true).trim()
                      def branch = steps.sh( script: "echo ${steps.JOB_NAME} | awk -F '/' '{print \$2}'", returnStdout: true).trim()

                      /*
                      The below checkout function is the part of the steps class which is provided by default
                      It does checkout by connecting to github using the provided CredentialID

                      */
                      steps.checkout([$class: 'GitSCM',
                          branches: [[name: "*/$branch"]],
                          doGenerateSubmoduleConfigurations: false,
                          gitTool: 'Default',
                          url: "$git_url"]]])

                  }

              }
          }
      }

      }
