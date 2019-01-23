<%-- 
    Document   : newjsp
    Created on : Nov 5, 2018, 10:38:19 PM
    Author     : lyx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="includes/default.css" rel="stylesheet" type="text/css" media="screen" />
        <jsp:useBean id="ub" class="yil210.myBean" scope="session" />
    </head>
    <body>
        <h2><jsp:getProperty name="ub" property="status" /></h2>
        <h2>Login</h2>
        <form method = "post" action="Servlet1" id="loginfrm">
            <input type="hidden" name="log" />
            <table border="1">
                <tbody>
                    <tr>
                        <td>Username</td>
                        <td><input type="text" name="name" id="username" /></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" id="password" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" name="Login" value="Login" id="submit" /></td>
                    </tr>
                </tbody>
            </table>
        </form>

    </body>
</html>
