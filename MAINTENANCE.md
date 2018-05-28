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

### Important Commands

1. Restart apache 2 server - sudo service apache2 reload
2. Start Tomcte server  - sudo /opt/tomcat/apache-tomcat-7.0.63/bin/startup.sh
3. Restart Jenkins - sudo /etc/init.d/jenkins restart
4. Restart Postgres - sudo /etc/init.d/postgresql restart
5. Run postgres in debug mode - sudo -u postgres -i /usr/lib/postgresql/8.4/bin/postgres -d 3 -D /var/lib/postgresql/8.4/main -c config_file=/etc/postgresql/8.4/main/postgresql.conf
6. Run tomcat in debug mode - sudo /opt/tomcat/apache-tomcat-7.0.63/bin catalina.sh run
7. List all listening ports - sudo netstat -plnt 

### System Migration / Setup
1. Configure Services

	i. Java
	sudo apt-get update
	sudo apt-get install default-jre
	sudo apt-get install default-jdk (Optional)
	sudo apt-get install oracle-java8-installer

	Set environment variable		
	JAVA_HOME="/usr/lib/jvm/java-8-oracle"
	source /etc/environment
	
	ii. Postgres

	sudo apt-get update
	sudo apt-get install postgresql postgresql-contrib phppgadmin
	
	-Create imods user
	sudo -u postgres psql --command "CREATE USER imod WITH  PASSWORD 'imod4u'"
	
	-Create imod db
	sudo -u postgres createdb --owner imod imod-test
	sudo -u postgres createdb --owner imod imod-demo
	
	iii. Grails

	-if zip and unzip not installed (sudo apt-get install zip, sudo apt-get install unzip)
	unzip grails sdk into export GRAILS_HOME=/opt/grails-2.4.3
	export GRAILS_HOME=/opt/grails-2.4.3
	export PATH=$GRAILS_HOME/bin:$PATH
	
	iv. Jenkins
	-add the repository key to the system.
	wget -q -O - https://pkg.jenkins.io/debian/jenkins-ci.org.key | sudo apt-key add -
	
	-append the Debian package repository address to the server's sources.list
	https://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list
	sudo apt-get update
	sudo apt-get install jenkins
	
	v. Node JS	
	sudo apt-get update
	sudo apt-get install nodejs
	sudo apt-get install npm
	
	vi. Apache tomcat.
	Download Apache tomcat 7.0.63 (Or preferred version) and extract in  /opt directory

2. Static data Migration.

	i. Copy all the files from /opt/tomcat/apache-tomcat-7.0.63/webapps$ of old server to same folder in new server
	ii. Copy all the files from  /var/www/html$ of old server to same folder of new server.
