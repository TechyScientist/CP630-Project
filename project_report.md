# PROJECT Report

Author: Johnny Console (215803250) 

Date: 

Check [readme.txt](readme.txt) for course work statement and self-evaluation.

## R1 Requirements (project)

### R1 Write a project proposal (2-3 pages).

Complete? (Yes/No): Yes, [Project Proposal](Proposal/project_proposal.html)

### R2 Design data format, collect data, create dataset for the application.
 
Complete? (Yes/No): Yes, [Dataset](Proposal/files/TemperatureData-2013-2023-Combined.arff)

1. Collect and combine collected monthly data files from 2013-2023 using Microsoft Excel
2. Prune blank data entries with Microsoft Excel
3. Use Weka's provided tool to convert the Microsoft Excel sheet into a readAble ARFF file for importing

### R3 Develop and implement data application algorithms for the proposed application problem.
 
Complete? (Yes/No) 

If Yes, briefly describe: 

1. what you have done, 
2. what are the new features. 
3. Take some screen to demonstrate the features if applicable. 

### R4 Do data computing to generate models, representing models in portable format and stored in file or database. More credit is given if distributed approach is used data mining of big dataset.
 
Complete? (Yes/No): Yes 

1. Collected data from Environment and Climate Change Canada's Historical Data Extract Tool, 2013-2023
2. Used JavaFX Client to set up database schema, create REPTree Model and insert it into database, create two user models and insert them to the database
3. When required, import the model from the database to use for prediction calculations

### R5 Create deployable service components using application models using Java based enterprise computing technologies, to create client program to do remote call of the data mining services.
 
Complete? (Yes/No): Yes 

1. Create a wrapper project, temperature-app, containing subordinate projects temperature-ear, temperature-ejb, temperature-web, temperature-ws and temperature-rs:
   * temperature-ejb: Project containing all Enterprise Java Beans, including stateless beans and stateful/entity beans
   * temperature-web: Project containing all servlet components and Java Server Pages for interacting with the temperature-ejb
   * temperature-ws:  Project containing all web service components for performing predictions
   * temperature-rs:  Project containing all RESTful API components for performing predictions
   * temperature-ear: Wrapper for ejb, web, ws and rs components

Structure: 

      temperature-app
          temperature-ear
          temperature-ejb
          temperature-web
          temperature-ws
          temperature-rs
      temperature-client

### R6 Deploy service components onto enterprise application servers.
 
Complete? (Yes/No): Yes

1. Deployed temperature-app and all of its components to locally hosted WildFly server with MySQL configured

### R7 Create web services (SOAP, RESTful) to use the data service components.

Complete? (Yes/No) 

If Yes, briefly describe: 

1. what you have done, 
2. what are the new features. 
3. Take some screen to demonstrate the features if applicable.

### R8 Create web user interface/mobile applications to use the application/web services.

Complete? (Yes/No): Yes, see demonstration video

### R9 Test your services, log your services, and document your term project.

Complete? (Yes/No) 

If Yes, briefly describe: 

1. what you have done, 
2. what are the new features. 
3. Take some screen to demonstrate the features if applicable.

### R10 Demonstrate your term project in final project presentation, slides, short video.
 
Complete? (Yes/No) 

If Yes, briefly describe: 

1. what you have done, 
2. what are the new features. 
3. Take some screen to demonstrate the features if applicable.

**References**

1. CP630OC Project
2. CP630OC Labs
3. CP630OC Assignments
4. Any and all references noted in code files
