
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    String redirectURL = request.getContextPath() + "/fc?command=show-random";
    response.sendRedirect(redirectURL);
%>
</body>
</html>
