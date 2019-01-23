<%-- 
    Document   : welcome
    Created on : Nov 5, 2018, 10:33:48 PM
    Author     : lyx
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h2>Welcome
        <jsp:getProperty name="ub" property="name" />
        </h2>
        <p>Your login status is
        <jsp:getProperty name="ub" property="status" />
        </p>
    </body>
</html>
