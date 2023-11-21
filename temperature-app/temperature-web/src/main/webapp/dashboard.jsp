<%@ page import="static net.johnnyconsole.cp630.project.web.util.ApplicationSession.*" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Temperature Web App: Dashboard</title>

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
    </style>
</head>

<body>
<div id="header">
    <h1>Temperature Web App: Dashboard</h1>
    <h5>CP630 Final Project</h5>
    <h5>Author: Johnny Console (215803250)</h5>
</div>
<div id="body">
    <%if(username != null) {%>
    <h3>Predict Temperature</h3>
    <form action="/temperature-web/TemperaturePredictionServlet" method="post">
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
        <input type="submit" value="Predict"/>
    </form>
    <!-- <h3>Log In</h3>
     <form action="/temperature-web/LoginServlet" method="post">
         <label for="username">Enter Username:</label>
         <input type="text" name="username" id="username" required /><br/><br/>
         <label for="password">Enter Password:</label>
         <input type="password" name="password" id="password" required/><br/><br/>
         <input type="submit" value="Log In"/>
     </form> -->
    <% } else {
        response.sendRedirect("/temperature-web/?error=notloggedin");
     } %>
</div>

<hr/>

</body>
</html>