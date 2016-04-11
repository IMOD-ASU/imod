# Maintenance Guide

* [Sprint Management](#sprint-management)
* [Code Maintenance](#code-maintenance)
* [Library Maintenance](#library-maintenance)
* [Server Maintenance](#server-maintenance)

## Sprint Management

For Git sprint management we follow the [Git Flow model](http://nvie.com/posts/a-successful-git-branching-model/), and
use Master branch for stable, and development branch for staging. We use the sprint number as the application version.

### Start of Sprint

1. Pull the code from `master` into `development` branch
2. In `application.properties` change the version to `0.{sprint number}.0`
3. Open a pull request from `development` to `master` with title `Sprint {sprint number}`

### End of Sprint

1. Merge the pull request to master
2. Run a build on Jenkins and ensure that all the change build properly for demo
3. Start a new sprint

---

## Code Maintenance

There are five tools used to help maintain quality code.

Its good to run these tools at least once a sprint to look for code smells and potential issues.

* [CodeNarc](http://codenarc.sourceforge.net/) for Groovy and Grails static analysis
* [FindBug](http://findbugs.sourceforge.net/) for more analysis of the compiled Groovy code and templates
* [ESLint](http://eslint.org/docs/rules/) for static analysis of JavaScript
* [Remark Lint](https://github.com/wooorm/remark-lint/blob/master/doc/rules.md) for analysis of Markdown documentation
* [CSSComb](http://csscomb.com/docs) for automatic formatting of style sheets

### Installing the tools

* CodeNarc is bundled with the grails application, nothing needed here
* Findbugs can be downloaded [here](http://findbugs.sourceforge.net/downloads.html)
* One command to install the rest `npm install`

### Running CodeNarc

1. open a terminal in the imods project folder
2. run `grails codenarc`
3. open the generated `static-analysis.html` file
4. resolve as many errors as possible

### Running FindBug

1. open a terminal in the imods project folder
2. run `grails war`
3. open findbug application
4. file > load
5. open `target/imod.war`
6. wait 5 minutes for it to process
7. filter by package `imod`
8. resolve as many errors as possible

### Running ESLint and Remark Lint

1. open a terminal in the imods project folder
2. run `npm test`
3. errors will be displayed in the terminal
4. resolve as many errors as possible

### Running CSScomb

1. open a terminal in the imods project folder
2. run `npm run comb`
3. code will be auto formatted, commit the updated code

---

## Library Maintenance

There are three main package managers that are used in the project.

* [Gant/Maven](https://maven.apache.org/) Manages Groovy on Rails packages
* [Bower](http://bower.io/) Manages UI libraries
* [NPM](https://www.npmjs.com/) manages UI testing libraries

### Checking for Grails package updates

1. open a terminal in the imods project folder
2. run `grails list-plugin-updates`
3. Look for plugins updates that are not labeled `snapshot`
4. Look up any plugins that meet the above on the [grails plugin site](http://grails.org/plugins/)
5. Check for any breaking changes in the change logs
6. Update the version to the latest stable version in the `/grails-app/conf/BuildConfig.groovy` file

### Checking for Bower package updates

1. open a terminal in the imods project folder
2. run `bower list`
3. Update the version number in `bower.json`

### Checking for NPM package updates

1. open a terminal in the imods project folder
2. run `npm update --save-dev`
3. retest code with `npm test`

---

## Server Maintenance

### Update the Test/Demo Server

1. SSH into the server
2. run `sudo apt-get update`
3. run `sudo apt-get dist-upgrade`

### Recover from Test/Demo Server Crash

1. SSH into the server
2. Run `sudo reboot`
3. Wait a few minutes
4. SSH into the server again
5. Run `sudo /usr/local/apache2/bin/apachectl start`
6. Run `sudo /opt/apache-tomcat-{version}/bin/startup.sh`

### Recover from Travis CI dependency lock

Sometimes when dependencies get updated Travis CI say all builds fail.

1. Check the logs to make sure it is not a code mistake
2. Goto the [Travis CI website](https://travis-ci.org/IMOD-ASU/imod/)
3. Login with Github credentials
4. Click Settings > Caches
5. Click Delete all repository caches

Done! the next Travis CI build will be a bit slow, but will not hang on installing dependencies.

### Update Apache Tomcat on Demo

1. SSH into the server
2. Goto `/opt`
3. Run `sudo /opt/apache-tomcat-{old version}/bin/shutdown.sh`
4. Open the [Apache Tomcat site](https://tomcat.apache.org/download-70.cgi)
5. Get the latest code binary version `sudo wget http://link-from-site.tar.gz`
6. Unzip file `sudo tar -zxvf apache-tomcat-version.tar.gz`
7. Copy the Tomcat Manager configuration from the old Tomcat config file to the new
8. In `bin/catalina.sh` copy the Java version and Java Opts from the old tomcat to the new
9. Run `sudo /opt/apache-tomcat-{new version}/bin/startup.sh`

### Update Node JS on Demo

1. SSH into the server
2. Goto `/opt`
3. Open the [Node JS downloads page](https://nodejs.org/dist/latest/)
4. Get the latest 32bit binary `sudo wget {link from site}.tar.gz`
5. Unzip the file `sudo tar -zxvf {downloaded file}.tar.gz`
6. cd into the new version
7. run `sudo ./bin/npm install -g bower`
8. update the path to Node JS in each of the Jenkins project configurations


---

## AWS Maintenance 

### Public DNS: ec2-52-26-177-245.us-west-2.compute.amazonaws.com

### Update the Test/Demo Server

1. SSH into the server
2. run `sudo apt-get update`
3. run `sudo apt-get dist-upgrade`

### Recover from Test/Demo Server Crash

1. SSH into the server
2. Run `sudo reboot`
3. Wait a few minutes
4. SSH into the server again
5. Run `sudo service apache2 start` to start apache. This runs the main static site.
6. Run `sudo /opt/apache-tomcat-{version}/bin/startup.sh`
