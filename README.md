Vertabelo API Gradle plugin
========================================================================

A Gradle plugin to download XML and SQL from Vertabelo.

How to
------------------------------------------------------------------------

Add it to your buildscript dependencies:

```groovy
plugins {
  id "com.vertabelo.gradle.vertabelo" version "1.1.0"
}

vertabelo {
    destSQLFile = 'destination/model.sql'
    destXMLFile = 'destination/model.xml'
    apiToken = System.getenv('VERTABELO_API_TOKEN')
    modelId = 'model gid'
    modalTag = 'model tag (optional)'
}
```

Parameters description:

1. `destSQLFILE` - destination where the SQL file will be written
2. `destXMLFile` - destination where the XML file will be written
3. `apiToken` - API token generated in Vertabelo, more here: http://www.vertabelo.com/blog/documentation/vertabelo-api
4. `modelId` - model's gid, more here: http://www.vertabelo.com/blog/documentation/vertabelo-api
5. `modelTag` - model's tag, more here: http://www.vertabelo.com/blog/documentation/vertabelo-api

and apply it (after "plugins" section):

```groovy
plugins {
...
}
apply plugin: 'vertabelo'
```

Tasks
------------------------------------------------------------------------

* Download the SQL file:
    ```
    gradle vertabeloSQL
    ```
* Download the XML file:
    ```
    gradle vertabeloXML
    ```
  
To download both create following section in your `build.gradle` file:
```groovy
task updateVertabelo << {}

updateVertabelo.dependsOn vertabeloSQL
updateVertabelo.dependsOn vertabeloXML
```
