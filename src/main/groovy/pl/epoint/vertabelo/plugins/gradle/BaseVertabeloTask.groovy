package pl.epoint.vertabelo.plugins.gradle

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Internal

/**
 * Downloads model data from Vertabelo API based on following parameters:
 * - apiToken : API token to Vertabelo user account
 * - modelId : Vertabelo model id
 * - modelTag : Vertabelo model version tag (optional)
 * - fileName : file where model data will be saved to
 */
abstract class BaseVertabeloTask extends DefaultTask {

    @Internal
    def GET_MODEL_ADDR = "https://my.vertabelo.com/model"

    @Internal
    abstract String getHttpAcceptHeader()

    def void download(String apiToken, String modelId, String tag, String fileName) {

        if (apiToken == null || apiToken.trim().length() == 0) {
            throw new IllegalArgumentException("Vertabelo api token not set")
        }
        if (modelId == null || modelId.trim().length() == 0) {
            throw new IllegalArgumentException("Vertabelo model id not set")
        }

        def requestUrl;
        if (tag != null) {
            requestUrl = GET_MODEL_ADDR + "/" + modelId + "/" + tag
        } else {
            requestUrl = GET_MODEL_ADDR + "/" + modelId
        }

        new File(fileName.toString()).parentFile.mkdirs()

        def connection = new URL(requestUrl).openConnection()
        def auth = "${apiToken}:".bytes.encodeBase64()
        connection.setRequestProperty("Authorization", "Basic ${auth}")
        connection.setRequestProperty("Accept", getHttpAcceptHeader())
        logger.info "Status code: ${connection.getResponseCode()}" // HTTP request done on first read
        new File(fileName.toString()).write(connection.getInputStream().getText())
    }
}
