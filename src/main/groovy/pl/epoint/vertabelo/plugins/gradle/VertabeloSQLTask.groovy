package pl.epoint.vertabelo.plugins.gradle

import org.gradle.api.tasks.TaskAction

class VertabeloSQLTask  extends BaseVertabeloTask {

    @Override
    String getHttpAcceptHeader() {
        return "text/sql"
    }

    @TaskAction
    def void download() {
        download(project.vertabelo.apiToken,
                project.vertabelo.modelId,
                project.vertabelo.modelTag,
                project.vertabelo.destSQLFile)
    }
}
