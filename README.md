# Flow Network [![License](http://img.shields.io/badge/license-MIT-lightgrey.svg?style=flat)][License]  ![Build and Deploy](https://github.com/GlowstoneMC/network/workflows/Build%20and%20Deploy/badge.svg)

Network library for the Flow collection, forked by Glowstone.

## Getting Started
* [Examples and code snippets](https://github.com/flow/examples/tree/master/network)
* [Official documentation](#documentation)
* [Issues tracker](https://github.com/GlowstoneMC/network/issues)

## Source Code
The latest and greatest source can be found here on [GitHub](https://github.com/GlowstoneMC/network). If you are using Git, use this command to clone the project:

    git clone git://github.com/GlowstoneMC/network.git

## Dependencies
We love open-source libraries! This project uses are few of them to make things easier. If you aren't using Maven or Gradle, you'll need these!
* [io.netty:netty-all](https://oss.sonatype.org/#nexus-search;gav~io.netty~netty-all~~~)
* [org.slf4j:slf4j-api](https://oss.sonatype.org/#nexus-search;gav~org.slf4j~slf4j-api~~~)

## Test Dependencies
The following dependencies are only needed if you compiling the tests included with this project. Gotta test 'em all!
* [junit:junit](https://oss.sonatype.org/#nexus-search;gav~junit~junit~~~)
* [org.hamcrest:hamcrest-library](https://oss.sonatype.org/#nexus-search;gav~org.hamcrest~hamcrest-library~~~)
* [org.powermock:powermock-api-mockito](https://oss.sonatype.org/#nexus-search;gav~org.powermock~powermock-api-mockito~~~)
* [org.powermock:powermock-module-junit4](https://oss.sonatype.org/#nexus-search;gav~org.powermock~powermock-module-junit4~~~)

## Building from Source
This project can be built with the _latest_ [Java Development Kit](http://oracle.com/technetwork/java/javase/downloads) and [Maven](https://maven.apache.org/) or [Gradle](https://www.gradle.org/). Maven and Gradle are used to simplify dependency management, but using either of them is optional.

For Maven, the command `mvn clean package` will build the project and will put the compiled JAR in `target`, and `mvn clean install` will copy it to your local Maven repository.

## Contributing
Are you a talented programmer looking to contribute some code? We'd love the help!

* Open a pull request with your changes, following our [guidelines and coding standards](CONTRIBUTING.md).
* Please follow the above guidelines for your pull request(s) accepted.
* For help setting up the project, keep reading!

Don't forget to watch and star our repo to keep up-to-date with the latest Flow development!

## Usage
If you're using [Maven](https://maven.apache.org/download.html) to manage project dependencies, simply include the following in your `pom.xml` file:

    <dependency>
        <groupId>com.flowpowered</groupId>
        <artifactId>flow-network</artifactId>
        <version>1.2.5</version>
    </dependency>

You will also need to add the Glowstone Maven repository in your `pom.xml` file:

    <repository>
        <id>glowstone-repo</id>
        <url>https://repo.glowstone.net/content/repositories/releases/</url>
    </repository>

## Legal Stuff
Flow Network is licensed under the [MIT License][License]. Basically, you can do whatever you want as long as you include the original copyright. Please see the `LICENSE.txt` file for details.

## Credits
* [Spout](https://spout.org/) and contributors - *where we all began, and for much of the re-licensed code.*
* All the people behind [Java](http://www.oracle.com/technetwork/java/index.html), [Maven](https://maven.apache.org/), and [Gradle](https://www.gradle.org/).

## Contact
Feel free to contact us on [Discord](https://discord.gg/TFJqhsC)


[License]: https://tldrlegal.com/l/mit
