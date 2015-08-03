Instructional Module Development System [![Build Status](https://travis-ci.org/IMOD-ASU/imod.svg?branch=master)](https://travis-ci.org/IMOD-ASU/imod)
====

a semantic web-based tool that guides STEM educators through the complex task of outcomes-based course design.

### What is an IMOD?
![PC cubed model](http://imod-asu.weebly.com/uploads/2/9/6/3/29635095/1400168368.jpg "PC cubed model")

An IMODS is a course that uses Pedagogy, Content, and Assessment to generate Learning Objectives. This creates a clearly focused course.

### How to contribute
- Read the [Contributing Guidelines](https://github.com/IMOD-ASU/imod/blob/master/CONTRIBUTING.md)

###Installation Instructions
* Install the Java Developement Kit version 8
[(windows)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[(linux)](http://openjdk.java.net/install/)
* Install Grails version 2.5
[(windows)](http://grails.org/doc/latest/guide/gettingStarted.html#requirements)
[(linux)](http://gvmtool.net/)
* Install Postgres SQL 9.4 [(windows)](http://www.postgresql.org/download/windows/)
[(linux)](https://help.ubuntu.com/community/PostgreSQL)
* Install Node JS v0.12 [(windows) (linux)](https://nodejs.org/download/)
* For develop ensure postgres user is "postres" with password "postres"
* Create a database named "sample"
* Run `npm install bower -g`

## Key Concepts

### Objectives Based Learning
This is a style of course creation focused on an instructors goal of what a students primary, secondary and terceary knowledge should be leaving the course. Then using this information to focus on what topics and content should be covered in the couse.

### Learning Objectives
Learning Objectives are a general concept in a field that an instuctor may teach.
Learning Objectives come in various levels of importance: urgent, imporant and good to know.
These levels of importance decide the topic's priority in the course.
How much material of that topic will be covered and what weight it will carry in the course.

### Content
This is the meat of the course.
The actual materials which an instructor will directly use, or use to create their own materials to cover in the course.

### Assessments
This describes how the instructor will measure a students progress and comprehension of content and topics covered through out the course.

### Pedagogy
This explains how the Content and the Assessments will work together to satisfy each Learning Objective.
