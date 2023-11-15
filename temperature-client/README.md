# Temperature Client
### Installation Steps
1. Ensure WildFly (with standalone-full.xml configuration and test datasource), Apache and MySQL are turned on.
2. Download and unzip the full repository.
3. Deploy necessary EAR/Web Components: 

    ```cd ../temperature-app```

    ```mvn clean install wildfly:deploy```
4. Package the temperature-client app:
    
    ```mvn clean package```
5. Run the packaged JAR File:

    ```java -jar target/temperature-client.jar```

    __Note:__ You can also run the JAR file at the time of building with:

    ```mvn clean package exec:java```
6. Select the "Setup" button
7. Select the "Create Database" Button
8. Enter a name for the regression model, and press the "Generate Regression Model..." button to select the [dataset file](../Proposal/files/TemperatureData-2013-2023-Combined.arff).

__Note__: Steps 6, 7 and 8 above only need to be completed __once__. If you have already done these steps, you can proceed to step 9 below.

9. Select the "Back" button to log in. By default, there are __two__ users configured:
   * __Username__: user, __Password__: User123!, Access Level 0 (Standard)
   * __Username__: admin, __Password__: Admin123!, Access Level 1 (Administrator)
10. To predict a day's temperature, selec the "Prediction" tab and enter the year, month and day to predict, and press the "Predict" button. The result of the prediction will be shown under the button.
11. __Administrator Users Only__: To add or remove users, select the "Administration" tab and click the appropriate button, and enter the information required by the window.
12. To Sign Out, select the "Sign Out" button, or close the dashboard window.
13. To close the app, sign out, then press the "Exit" button (or close the sign in window), and select "Yes" in the confirmation box. __Note__: Any other input (i.e.,  clicking the "No" button or closing the confirmation box) will __not__ close the app, and will bring you back to the sign in screen.