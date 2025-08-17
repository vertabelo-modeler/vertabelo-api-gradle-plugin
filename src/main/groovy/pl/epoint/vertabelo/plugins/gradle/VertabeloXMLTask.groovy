package pl.epoint.vertabelo.plugins.gradle


import org.gradle.api.tasks.TaskAction

class VertabeloXMLTask extends BaseVertabeloTask {

    @Override
    String getHttpAcceptHeader() {
        return "text/xml"
    }

    @TaskAction
    def void download() {
        download(project.vertabelo.apiToken,
                project.vertabelo.modelId,
                project.vertabelo.modelTag,
                project.vertabelo.destXMLFile)
    }
}
