import utilities.*

def call(body)
{
  def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()
    // creating an instance of the JenkinsSharedLibrary class
    def instance = new JenkinsSharedLibrary(this)
  
podTemplate(label: 'kubernetes',
  containers: [
    containerTemplate(name: 'test-centos', image: 'gcr.io/us-sbx-deng-sandbox/centos:centos7update', ttyEnabled: true, privileged:true, command: '/usr/sbin/init')
  ]) {
  
  instance.preparation()
  
  //echo $WORKSPACE
}
}
