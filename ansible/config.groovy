JOB_NAME = 'AnsibleSetup'
DISPLAY_NM = 'ConfigurationProject'
CJK_GIT_URL = 'https://github.com/KevinChris7/terraform-ansible-aws.git'
JOB_REPO = 'https://github.com/KevinChris7/trial-project.git'
BRANCH_NAME = 'master'
CREDENTIALS_ID = 'github'
ANSIBLE_CRED = 'ansible'

pipelineJob(JOB_NAME){
    displayName(DISPLAY_NM)
    logRotator{
        daysToKeep(30)
        numToKeep(10)
    }
    parameters{
        stringParam('CJK_GIT_URL',CJK_GIT_URL)
        stringParam('BRANCH_NAME', BRANCH_NAME)
        stringParam('CREDENTIALS_ID', CREDENTIALS_ID)
        stringParam('ANSIBLE_CRED', ANSIBLE_CRED)
        choiceParam('INVENTORY', ['static', 'dynamic'])
    }
    definition{
        cpsScm{
            scm{
                git{
                    remote{
                        credentials(CREDENTIALS_ID)
                        url(JOB_REPO)
                    }
                    branch("*/master")
                }
            scriptPath('ansible/Jenkinsfile')
            lightweight()
            }
        }
    }
    properties{
        rebuild{
            autoRebuild(false)
            rebuildDisabled(false)
        }
    }

}