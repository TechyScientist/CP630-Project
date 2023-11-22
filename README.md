# CP630 Project Proposal: Temperature Prediction using a REPTree Model

Author: Johnny Console (215803250)

Original Publish Date: 24 October 2023

Last Modification Published: 22 November 2023

### Introduction

Temperatures vary year-to-year, month-to-month, and even day-by-day, but
it is a generally good idea to predict what the temperature of a given
day will be by using machine learning based off of the temperature data
between January 2013 and October 2023.There exists a resource, The
Farmers' Almanac, that is meant to provide such predictions, generally
speaking, across the United States and Canada, among many other things.
This project aims to add specificity to the Almanac's predictions by
providing the average temperature for a given day, given historical
temperature data.

The main goal of the project is to allow users to predict the
temperature of a given day in the next year based on historical data.

Ideally, the project would also include the ability to predict daily
temperatures for any city across Canada, but the dataset for these would
be unmanageable to download for the scope of this project, so the city
of Ottawa was chosen.

### Problem-Solving and Algorithms

#### Dataset

The datasets used for the project come from Environment and Climate
Change Canada\'s (ECCC) Historical Data Extract tool, found
[here](https://climate.weather.gc.ca/historical_data/search_historic_data_e.html).
This tool allows for downloading daily or hourly weather information gor
a given year and month or day (for hourly data only) for any give
weather station in Canada. The chosen station is **OTTAWA INTL A**
(Ottawa International Airport \[IATA: YOW, ICAO: CYOW\]).

Because the source gives the data in separate CSV files, the data files
from 2013-2023 were downloaded and combined into the included
[TemperatureData-2013-2023-Combined.csv](Proposal/files/TemperatureData-2013-2023-Combined.csv)
file, which was converted to ARFF format using Weka\'s GUI Interface.
The converted ARFF file can be found
[here](Proposal/files/TemperatureData-2013-2023-Combined.arff). Individual
historical data files can be accessed through the list in the References
section. Extra data (Latitude, Longitude, and extra climate
information), as well as rows with missing data, are removed from the
combined dataset using Microsoft Excel prior to converting to ARFF
format.

#### Algorithm

Originally, this project was going to be completed using Weka's Linear Regression algorithm, but after further testing based on the Weka recommended models for the dataset, the model was switched to the Wek REPTree Model, which provided a much more accurate prediction.

### System Design

The system will have the following components covered in this course:

- Login page with two levels of user access:
    -   **Access Level 1: Administrators** have access to the same
        features regular users do, but can also add or remove users
        (other than themselves) to the system.
    -   **Access Level 2: Regular Users** have access to a screen to
        allow the user to select a day in the next year to predict the
        average temperature of based on the REPTree.
- User passwords will be hashed using the BCrypt hashing algorithm
  when stored in the database and will be verified using the same
  algorithm when needed to increase security. The implementation of
  BCrypt found [here](https://github.com/patrickfav/bcrypt) will be
  used, as it can easily be imported into the project as a Maven
  dependency.
- SOAP and RESTful APIs to predict the temperature of a given day

The following concepts and tools used in this course will be included in
the project implementation:

- IntelliJ IDEA
- Java EE
- WildFly
- JavaFX Client (for setup and creation/insertion of database and
  initial models, prediction and administration)
- Web Clients (HTTP (for all functions), SOAP and RESTful (for prediction only))
- MySQL (for entity persistence)
- Stateless Session Beans
- Stateful/Entity Beans (for the user and the model)
- RMI lookups for session beans for client use 
- Weka (for REPTree model)

### Plan and Schedule

  | Task Item                                               | Status      | Due/Completion Date            |
  |---------------------------------------------------------|-------------|--------------------------------|
  | Compile Datasets from ECCC                              | Complete    | 25 October 2023                |
  | Convert Compiled Dataset to Required Format             | Complete    | 26 October 2023                |  
  | Complete and Submit Project Proposal Document           | Complete    | 26 October 2023                |  
  | Program EAR, EJB and JavaFX Client components           | In Progress | 12 December 2023               |
  | Program Web Client, SOAP API and RESTful API Components | In Progress | 12 December 2023               |
  | Test Components                                         | In Progress | Ongoing, Due: 12 December 2023 |
  | Documentation of Project                                | In Progress | Ongoing, Due: 12 December 2023 |
  | Create Project Demonstration Video                      | To-Do       | 12 December 2023               |
  | Submit Project Code and Demonstration                   | To-Do       | 19 December 2023               |

### References

1.  [Wikipedia/Farmer's
    Almanac](https://en.wikipedia.org/wiki/Farmers%27_Almanac)
2.  [GitHub/patrickfav/bcrypt](https://github.com/patrickfav/bcrypt)
3.  [Environment and Climate Change Canada (ECCC) Historical Climate
    Data Extract
    Tool](https://climate.weather.gc.ca/historical_data/search_historic_data_e.html)
4.  Data Files:
    -   [TemperatureData-2013-2023-Combined.arff](Proposal/files/TemperatureData-2013-2023-Combined.arff)
    -   [TemperatureData-2013-2023-Combined.csv](Proposal/files/TemperatureData-2013-2023-Combined.csv)
    -   [TemperatureData-2013.csv](Proposal/files/TemperatureData-2013.csv)
    -   [TemperatureData-2014.csv](Proposal/files/TemperatureData-2014.csv)
    -   [TemperatureData-2015.csv](Proposal/files/TemperatureData-2015.csv)
    -   [TemperatureData-2016.csv](Proposal/files/TemperatureData-2016.csv)
    -   [TemperatureData-2017.csv](Proposal/files/TemperatureData-2017.csv)
    -   [TemperatureData-2018.csv](Proposal/files/TemperatureData-2018.csv)
    -   [TemperatureData-2019.csv](Proposal/files/TemperatureData-2019.csv)
    -   [TemperatureData-2020.csv](Proposal/files/TemperatureData-2020.csv)
    -   [TemperatureData-2021.csv](Proposal/files/TemperatureData-2021.csv)
    -   [TemperatureData-2022.csv](Proposal/files/TemperatureData-2022.csv)
    -   [TemperatureData-2023.csv](Proposal/files/TemperatureData-2023.csv)
	
