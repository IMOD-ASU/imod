## Contents
* [Contributing Guidelines](#contributing-guidelines)
  - [Editor Guidelines](#editor-guidelines)
  - [Git Guidelines](#git-guidelines)
  - [Code Guidlines](#code-guidelines)
* [Maintainance Guidelines](#maintainance-guidelines)
  - [Git Sprint Management](#git-sprint-management)
  - [Code Maintainance](#code-maintainance)
  - [Library Maintainance](#library-maintainance)
  - [Server Maintainance](#server-maintainance)

# Contributing Guidelines

### Editor Guidelines
Please install the [Editor Config plugin](http://editorconfig.org/#download) for your preferred editor to assist with uniform code style

<br>
### Git Guidelines
Please follow the [Git Flow branching model](http://nvie.com/posts/a-successful-git-branching-model/)

<br>
### Code Guidelines
##### HTML
HTML and GSP code should be written using [HTML5](http://www.w3schools.com/tags/default.asp) following the [Google HTML style guide](https://google-styleguide.googlecode.com/svn/trunk/htmlcssguide.xml) (using tabs instead of two spaces)

##### Javacript
Javascript follows the [Yandex code style](https://github.com/yandex/codestyle/blob/master/javascript.md).
Javascipt should pass [JShint](http://jshint.com/) static analysis and [JSCS](http://jscs.info/) style checking.

##### CSS
CSS follows the [Yandex code style](https://github.com/yandex/codestyle/blob/master/javascript.md).
CSS files should be auto formatted using [CSScomb](http://www.csscomb.com/)

<br>
<br>
<br>
<br>
# Maintainance Guidelines

### Git Sprint Management
For Git management we follow the [Git Flow model](http://nvie.com/posts/a-successful-git-branching-model/).

And use Master branch for stable, and development branch for staging.

For application versioning we follow [Semantic Versioning](http://semver.org/)

##### Start of Sprint
1. Pull the code from `master` into `development` branch
2. In `application.properties` change the version to `0.{minor}.0` with the `minor` being the sprint number
3. Open a pull request from `development` to `master` with title `Sprint {number}` with the Sprint number

##### Mid-Sprint Hot Fix
1. Merge Sprint pull request
2. Pull the code from `master` into `development` branch
3. In `application.properties` change the version to `0.{minor}.{patch}` with the minor being the sprint number, and increment the previous `patch` number.
4. Open a pull request from `development` to `master` with title `Sprint {number} Part {Increment}` with the Sprint number, and the increment from above

##### End of Sprint
1. Merge the pull request to master
2. Run a build on Jenkins and ensure that all the change build properly for demo
3. Start a new sprint

<br>
<br>
### Code Maintainance
There are six tools used to help maintain quality code.

Its good to run these tools at least once a sprint to look for code smells and potential issues.

* [CodeNarc](http://codenarc.sourceforge.net/) for Groovy and Grails static analysis
* [FindBug](http://findbugs.sourceforge.net/) for more analysis of the compiled Groovy code and templates
* [JShint](http://jshint.com/docs/) for static analysis of javascript
* [JSCS](http://jscs.info/) for style checking javascript
* [CSSComb](http://csscomb.com/docs) for automatic formatting of stylesheets
* [HTML hint](http://htmlhint.com/) for HTML validation and stylechecking

##### Installing the tools
* CodeNarc and HTMLhint are bundled with the grails application, nothing needed here
* Findbugs can be downloaded [here](http://findbugs.sourceforge.net/downloads.html)
* One command to install the rest `npm install -g jshint jscs csscomb`

##### Running CodeNarc
1. open a terminal in the imods project folder
2. run `grails codenarc`
3. open the generated `static-analysis.html` file
4. resolve as many errors as possible

##### Running FindBug
1. open a terminal in the imods project folder
2. run `grails war`
3. open findbug application
4. file > load
5. open `target/imod.war`
6. wait 5 minutes for it to process
7. filter by package `imod`
8. resolve as many errors as possible

##### Running JSHint
1. open a terminal in the imods project folder
2. run `jshint web-app/js/`
3. errors will be displayed in the terminal
4. resolve as many errors as possible

##### Running JSCS
1. open a terminal in the imods project folder
2. run `jscs web-app/js/`
3. errors will be displayed in the terminal
4. resolve as many errors as possible

##### Running CSScomb
1. open a terminal in the imods project folder
2. run `csscomb web-app/css/`
3. code will be auto formatted, commit the updated code

##### Running HTMLHint
* Start the grails application
* Open the site in a web browser
* Open developer console
* Warnings will be showing this the console logs

<br>
<br>
### Library Maintainance
There are two main package managers that are used in the project.

* [Gant/Maven](https://maven.apache.org/) Manages Groovy on Rails packages
* [Bower](http://bower.io/) Manages UI libraries

and what that is semi used
* [NPM](https://www.npmjs.com/) Mananges Node js libraries, used to keep what tools are used for code maintainance (and what version is used).

##### Checking for Grails package updates
1. open a terminal in the imods project folder
2. run `grails list-plugin-updates`
3. Look for plugins that have either
   - a newer version that is stable (not `beta`, `rc`, `snapshot`)
   - or has a `rc` or `snapshot` that is two patches or a minor ahead
4. Look up any plugins that meet the above on the [grails plugin site](http://grails.org/plugins/)
5. Check for any breaking changes in the change logs
   - see if breaking changes can be resolved
6. Update the version to the latest stable version in the `/grails-app/conf/BuildConfig.groovy` file

##### Checking for Bower package updates
1. open a terminal in the imods project folder
2. run `bower list`
3. Bower will show any stable libary updates
4. If there is a plugin that has a Major or Minor number change
   - [search for the plugin on the bower site](http://bower.io/search/)
   - Bower will link to the repo
   - Look at the release notes and see what breaking changes there are
5. Update the version number in `bower.json`
6. Resolve any breaking code

##### Checking for NPM package updates
NOTE: this is only used for reference so developers know what tools and versions are being used.

1. open a terminal in the imods project folder
2. run `npm outdated`
3. update the `package.json` file with the latest version
4. run `npm update -g {packageName}`
5. run `npm install`

<br>
<br>
### Server Maintainance

##### Update the Test/Demo Server
1. SSH into the server
2. run `sudo apt-get update`
3. run `sudo apt-get dist-upgrade`

##### Recover from Test/Demo Server Crash
1. SSH into the server
2. Run `sudo reboot`
3. Wait a few minutes
4. SSH into the server again
5. Run `sudo /opt/apache-tomcat-7.0.61/bin/startup.sh`

##### Recover from Travis CI depandancy lock
Sometimes when dependancies get updated Travis CI say all builds fail.

1. Check the logs to make sure it is not a code mistake
2. Goto the [Travis CI website](https://travis-ci.org/IMOD-ASU/imod/)
3. Login with Github credentials
4. Click Settings > Caches
5. Click Delete all repository caches

Done! the next Travis CI build will be a bit slow, but will not hang on installing dependancies.

##### Update Apache Tomcat on Demo
1. SSH into the server
2. Goto `/opt`
3. Run `sudo /opt/apache-tomcat-{new version}/bin/shutdown.sh`
4. Open the [Apache Tomcat site](https://tomcat.apache.org/download-70.cgi)
5. Get the latest code binary version `sudo wget http://whatever.tar.gz`
6. Unzip file `sudo tar -zxvf whatever.tar.gz`
7. Copy the Tomcat Manager configuration from the old Tomcat config file to the new
8. In `bin/catalina.sh` copy the Java version and Java Opts from the old tomcat to the new
9. Run `sudo /opt/apache-tomcat-{new version}/bin/startup.sh`

##### Update NodeJS on Demo
1. SSH into the server
2. Goto `/opt`
3. Open the [iojs website](https://iojs.org/en/index.html)
4. Get the latest binary `sudo wget whatever.tar.gz`
5. Unzip the file `sudo tar -zxvf whatever.tar.gz`
6. cd into the new version
7. run `bin/npm install bower`
8. update the path to iojs in each of the Jenkins project configurations
