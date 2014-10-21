# IMODS Design Overview

* [Overview](#overview)
* [Key Concepts](#key-concepts)
	- [Objectives Based Learning](#objectives-based-learning)
	- [Learning Objectives](#learning-objectives)
	- [Content](#content)
	- [Assessments](#assessments)
	- [Pedagogy](#pedagogy)
* [MVC Model](#mvc-model)
	- [Model](#model)
	- [View](#view)
	- [Controller](#controller)

## Overview
![PC cubed model](http://imod-asu.weebly.com/uploads/2/9/6/3/29635095/1400168368.jpg "PC cubed model")

An IMODS is a course that uses Pedagogy, Content, and Assessment to generate Learning Objectives. This creates a clearly focused course.

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

## MVC model

### Model
Models in this project are used solely for persistent datastorage.

Grails uses Hibernate for its ORM wrapper and this project uses Postgres SQL as the back end database.

Documentation for Grails Hibernate can be found [here](http://grails.org/doc/latest/ref/Domain%20Classes/Usage.html).

The current models in project can be viewed [here](https://github.com/IMOD-ASU/imod/tree/master/grails-app/domain/imodv6).

### View
Grails uses Grails Server Pages (GSP) to preprocess HTML pages.

This project leverages the GSP framework whenever possible. Documentation of GSP can be found [here](http://grails.org/doc/latest/ref/Tags/actionSubmit.html)

The pages in the project generated in a hierarchy with each page extending a template page. The current Layouts can be found [here](https://github.com/IMOD-ASU/imod/tree/master/grails-app/views/layouts)

All of the views for the project can be found [here](https://github.com/IMOD-ASU/imod/tree/master/grails-app/views)

### Controller
Controllers is the logic that holds Models and View together.

Grails controllers manage state through actions, each URL is linked to an action. Documentation for Grails controllers can be found [here](http://grails.org/doc/latest/ref/Controllers/Usage.html)

This project uses the URL design where `/${controller name}/${action name}/${imod id}` will trigger an action. For example `learningObjective/performance/123` will load learning objective 123 on the learning objective page, with the performance sub page open. A full list of controllers can be found [here](https://github.com/IMOD-ASU/imod/tree/development/grails-app/controllers/imodv6).
