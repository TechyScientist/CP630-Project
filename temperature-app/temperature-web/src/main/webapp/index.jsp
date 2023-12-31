<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Temperature Web App</title>

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

        p#error {
            background-color: darkred;
            color: white;
            text-align: center;
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
        }

        p#success {
            background-color: darkgreen;
            color: white;
            text-align: center;
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>
<div id="header">
    <h1>Temperature Web App</h1>
    <h5>CP630 Final Project</h5>
    <h5>Author: Johnny Console (215803250)</h5>
</div>
<div id="body">
    <% if(request.getParameter("error") != null && !request.getParameter("error").isEmpty()) {
        if(request.getParameter("error").equals("login")) {%>
            <p id="error">Invalid credentials: Please try again.</p>
        <% } else if(request.getParameter("error").equals("notloggedin")) { %>
            <p id="error">You must be logged in to access this page. Please log in below to continue.</p>
        <% }
    } else if(request.getParameter("action") != null && !request.getParameter("action").isEmpty()) {
            if(request.getParameter("action").equals("logout")) { %>
                <p id="success">You have been logged out.</p>
        <% }
        }%>
    <h3>Log In</h3>
    <form action="/temperature-web/LoginServlet" method="post">
        <label for="username">Enter Username:</label>
        <input type="text" name="username" id="username" required /><br/><br/>
        <label for="password">Enter Password:</label>
        <input type="password" name="password" id="password" required/><br/><br/>
        <input type="submit" value="Log In"/>
    </form>
</div>

<hr/>

</body>
</html>