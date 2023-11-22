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

        div.horiz {
            display: inline-block;
            margin-right: 50px;
        }

        div#left {
            clear:right;
        }

        div#right {
            clear: left;
            vertical-align: top;
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
    <h2>Welcome, <%= name.contains(" ") ? name.substring(0, name.indexOf(' ')) : name %>!</h2><br/>
    <form action="/temperature-web/LogOutServlet" method="post">
        <input type="submit" value="Log Out"/>
    </form>
    <h3>Predict Temperature</h3>
    <form action="" method="post">
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
    <% if(accessLevel == 1) { %>
        <div style="display: -webkit-box; -webkit-box-orient: horizontal;">
            <div class="horiz" id="left">
                <h3>Add a User</h3>
                <form action="" method="post">
                    <label for="username">Username:</label>
                    <input type="text" name="username" id="username" required/><br/><br/>
                    <label for="name">Name:</label>
                    <input type="text" name="name" id="name" required/><br/><br/>
                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" required/><br/><br/>
                    <label for="accessLevel">Name:</label>
                    <select name="accessLevel" id="accessLevel">
                        <option value="0">Standard</option>
                        <option value="1">Elevated</option>
                    </select><br/><br/>
                    <input type="submit" value="Add User"/>
                </form>
            </div>
            <div class="horiz" id="right">
                <h3>Remove User</h3>
                <form action="" method="post">
                    <label for="user">Select User:</label>
                    <input type="text" name="user" id="user" required/><br/><br/>
                    <input type="submit" value="Remove User"/>
                </form>
            </div>
        </div>
    <% }
    } else {
        response.sendRedirect("/temperature-web/?error=notloggedin");
     } %>
</div>

<hr/>

</body>
</html>