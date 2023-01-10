<div align="center">
   <img src="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/160/microsoft/74/package_1f4e6.png" />
   <h1>box</h1>
   <a href="https://github.com/ilotterytea/box/actions/workflows/release.yml"><img alt="Build status" title="Build status" src="https://github.com/ilotterytea/box/actions/workflows/release.yml/badge.svg" /></a>
   <hr>
</div>

A simple, easy-to-use box for your files. Built with Java, Gradle and Spring.io web framework.
## Features
+ Upload your files to the server and it will serve them. Easily access them from the link you get in the request.
+ Keep optimal data storage with Expired File Collector! It collects expired files (by last modified time and maximum age) and deletes them.
+ Link the uploaded files to the same file by their identical MD5 hash sum. New uploaded files with the same hash will update the expiration timer.
## Installation guide
### Install a ready-made file.
Install the box from GitHub releases with the default configuration. If you want to change the configuration, go to ["Custom"](#custom).
### Custom
1. Download or fork [the source code of box](https://github.com/ilotterytea/box): 
```shell
git clone https://github.com/ilotterytea/box.git
cd box
```
2. The configuration file is located at [**src/main/resources/application.properties**]().
   Edit it with your favorite text editor. [You can view the available configuration options here!](https://github.com/ilotterytea/box/wiki/Configuration)
3. Start the application. The application runs on port 8080.
```shell
./gradlew bootRun
```
4. Build the application. The built file is located at **build/libs**.
```shell
./gradlew bootJar
```
