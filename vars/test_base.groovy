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
    containerTemplate(name: 'test-centos', image: 'centos:centos7', ttyEnabled: true, command: 'cat')
  ]) {
  instance.preparation()
  kubectl config
}
}
