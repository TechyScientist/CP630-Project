<!DOCTYPE HTML>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Temperature RESTful Test</title>

        <style>
            * {
                margin: 0;
                padding: 0;
                font-family: "Calibri Light", sans-serif;
            }

            div#body {
                margin: 30px;
            }

            h3 {
                font-size: 20px;
                margin-bottom: 10px;
            }

            form {
                margin-left: 30px;
                margin-bottom: 25px;
            }

            label {
                margin-right: 10px;
            }

            input, select {
                padding: 5px
            }

            input[type="text"], select {
                margin-right: 10px;
            }

            div#header {
                background:purple;
                padding: 20px;
                color: white;
                border: 2px solid gold;
            }

            hr {
                height: 20px;
                background-color: purple;
            }

            p.error {
                background-color: darkred;
                color: white;
                text-align: center;
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
            }

            p.success {
                background-color: darkgreen;
                color: white;
                text-align: center;
                width: 100%;
                padding: 10px;
                margin-bottom: 10px;
            }

        </style>
        <script>
            let xhttp;

            function init() {
                xhttp = new XMLHttpRequest();
            }
            function callREST() {
                const model = document.getElementById("model");
                const year = document.getElementById("year");
                const month = document.getElementById("month");
                const day = document.getElementById("day");
                const response = document.getElementById("response");
                if(model.value === "") {
                    response.className = "";
                    response.innerHTML = "";
                    return;
                }
                const url = "http://localhost:8080/temperature-rs/rest/predict?model=" + model.value + "&year=" + year.value + "&month=" + month.value + "&day=" + day.value;
                xhttp.open('GET', url, true);
                xhttp.send();
                xhttp.onreadystatechange = function() {
                    if (this.readyState === 4) {
                        if (this.status === 200) {
                            const json = JSON.parse(this.responseText)
                            //The prediction only has a relative accuracy of +- 0.01,
                            // so if the result is > 0 and <= 0.001, then it's invalid.
                            if (json.result > 0 && json.result <= 1e-3) {
                                response.className = "error";
                                response.innerHTML = "There was an error with making the prediction. Please try again.";
                            } else {
                                response.className = "success";
                                response.innerHTML = "The predicted temperatue on " + day.value + " " + getMonthName(month.value) + " " + year.value + " is: <b>" + json.result + "</b> &deg;C";
                            }
                        }
                    }
                };

                function getMonthName(month) {
                    switch(month) {
                        case 1: return "January";
                        case 2: return "February";
                        case 3: return "March";
                        case 4: return "April";
                        case 5: return "May";
                        case 6: return "June";
                        case 7: return "July";
                        case 8: return "August";
                        case 9: return "September";
                        case 10: return "October";
                        case 11: return "November";
                        default: return "December";
                    }
                }
            }
        </script>
    </head>
    <body onload="init()">
        <div id="header">
            <h1>RESTful Test</h1>
            <h5>CP630 Final Project</h5>
            <h5>Author: Johnny Console (215803250)</h5>
        </div>
        <div id="body">
            <p id="response"></p>
            <h3>Predict Temperature</h3>
            <label for="model">Model Name:</label>
            <input type="text" name="model" id="model" /><br/><br/>
            <label for="year">Year:</label>
            <select name="year" id="year">
                <% for(int i = 2000; i <= 2030; i++) { %>
                    <option value="<%= i %>"><%= i %></option>
                <% } %>
            </select><br/><br/>
            <label for="month">Month:</label>
            <select name="month" id="month">
                <option value="1">January</option>
                <option value="2">February</option>
                <option value="3">March</option>
                <option value="4">April</option>
                <option value="5">May</option>
                <option value="6">June</option>
                <option value="7">July</option>
                <option value="8">August</option>
                <option value="9">September</option>
                <option value="10">October</option>
                <option value="11">November</option>
                <option value="12">December</option>
            </select><br/><br/>
            <label for="day">Day:</label>
            <select name="day" id="day">
                <% for(int i = 1; i <= 31; i++) {%>
                    <option value="<%= i %>"><%= i %></option>
                <% } %>
            </select><br/><br/>
            <input type="submit" value="Predict" onclick="callREST();"/>
        </div>
        <hr/>
    </body>
</html>