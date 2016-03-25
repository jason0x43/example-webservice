Programming Hacks: Creating a Web Service
=========================================

The goal is to create a web service interface to a standalone tool.


Tools
-----

* [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/download.cgi)
* [google-java-format](https://github.com/google/google-java-format)


Getting started
---------------

Open a command prompt and run:

    mvn archetype:generate -DarchetypeArtifactId=jersey-quickstart-grizzly2 \
	    -DarchetypeGroupId=org.glassfish.jersey.archetypes \
		-DarchetypeVersion=2.22.2 \
		-DinteractiveMode=false \
		-DgroupId=edu.wright.cs \
		-DartifactId=formatter-service \
		-Dpackage=edu.wright.cs

This will create a 'simple-service' directory that contains a `pom.xml` file and a couple of starter source code files.

Next, make a couple of modifications:

1. Edit `pom.xml` and add this to the properties section:
    <maven.test.skip>true</maven.text.skip>
2. Rename `src/main/java/edu/wright/cs/MyResource.java` to `src/main/java/edu/wright/cs/FormatterResource.java`
3. Edit `FormatterResource.java` and:
   1. Change the class name to "FormatterResource"
   2. Change the `@Path` value to "format"
4. Edit `Main.java` and change the BASE_URI to end in "formatter/" rather than "myapp/"

At this point, you should be able to build and run the app:

    mvn clean package exec:java

Access `http://localhost:8080/formatter/format` in a browser -- you should see "Got it!".


Target application
------------------

The application we'll be adding a web interface to is [google-java-format](https://github.com/google/google-java-format), a simple command line tool that formats a Java source file. You could use any Java application, or any command-line application. The only requirement is that the web server be able to execute the application and get output from it.

Many applications and libraries (including google-java-format) are already available throuh the central Maven repository. However, if you've created some application that you want to use in a Maven project, you'll need to install it into your local system repository using maven's install plugin: 

    mvn org.apache.maven.plugins:maven-install-plugin:2.5.1:install-file \
		-Dfile=../google-java-format-0.1-alpha.jar \
		-DgroupId=com.google.google.javaformat \
		-DartifactId=google-java-format \
		-Dversion=0.1-alpha \
		-Dpackaging=jar

To install into a project-local repository, declare one in @pom.xml@ and add `-DlocalRepositoryPath=<path to repo>` to the above command.


Handling exceptions
-------------------

By default, you won't get any useful feedback if something goes wrong in a request handler. The simplest way to improve the sitiuation is to add a generic `ExceptionMapper` to the project. This is simply a class that the application container will use to process any unhandled exceptions thrown by your request handlers. It can be type specific, but it's nice to have a catch-all handler. The mapper has a function `toResponse` that accepts an Exception and returns a JAX-RS `Response` object.


More information
----------------

* [Jersey](https://jersey.java.net)
* [JAX-RS Exception mapping](https://jersey.java.net/documentation/latest/representations.html#d0e6665)
* [Maven archetypes](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)
