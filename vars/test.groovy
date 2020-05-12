import utilities.*

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    // creating an instance of the JenkinsSharedLibrary class
    def instance = new JenkinsSharedLibrary(this)
    // assigning node_labels at which nodes it needs to be checkout
    String[] node_labels = ["kubernetes"]
    // stage_status tells at which stage the execution as failed, if it success, the job ran successfully
    String stage_status = "checkout"
    // try and finally block is written so that, the finally blocks will executes at any case, even it's a failure or a success
    try
    {
        // calling each and every function which executes respective stage as per the requirements
        instance.checkout(config.git_url,node_labels)
        stage_status = "Success"
    }
    catch(Exception e)
    {
        echo "$e"
        currentBuild.result = 'FAILURE'
    }

    echo "${stage_status}"
}
