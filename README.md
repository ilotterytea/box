<div align="center">
   <img src="https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/160/microsoft/74/package_1f4e6.png" />
   <h1>shitbox</h1>
   <!--<a href="https://github.com/ilotterytea/box/actions/workflows/release.yml"><img alt="Build status" title="Build status" src="https://github.com/ilotterytea/box/actions/workflows/release.yml/badge.svg" /></a>-->
   <hr>
</div>

A simple, easy-to-use box for your files. Built with Rust and Rocket web framework.

## Features

+ Upload your files to the server and it will serve them. Easily access them from the link you get in the request.
+ Keep optimal data storage with Expired File Collector! It collects expired files (by last modified time and maximum age) and deletes them.
+ Link the uploaded files to the same file by their identical MD5 hash sum. New uploaded files with the same hash will update the expiration timer.

## Installation guide

1. Download or fork [the source code of box](https://github.com/ilotterytea/shitbox):

```shell
git clone https://github.com/ilotterytea/shitbox.git
cd shitbox
```

2. If desired, you can create a config.toml file to change the configuration of the shitbox.
   Edit it with your favorite text editor. [You can view the available configuration options here!](https://github.com/ilotterytea/box/wiki/Configuration)
3. Start the application. The application runs on port 8000.

```shell
cargo run
```
