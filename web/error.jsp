<%-- 
    Document   : error
    Created on : Oct 31, 2018, 8:53:00 AM
    Author     : LABP2KOMP24
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error Exception</h1>
        <%
            String error = (String) request.getAttribute("error");
            out.print(error);
        %>
    </body>
</html>
