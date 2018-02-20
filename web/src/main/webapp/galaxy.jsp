<%--
  File : galaxy.jsp
  Description : welcome list of all galaxies

  Author : Popov Denys
  Created : 19/02/18

  Modified : { date: 20/02/18
              ,time: 12:59 PM }
  Modified by: Popov Denys

  Last modification : jstl added
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Univers</title>
    <link rel="stylesheet" type="text/css" href="interface/css/styles.css">
</head>
<body>
    <h2>Known Galaxies</h2>
    <ul>

        <c:forEach items="${galaxies}" var="galaxy">
            <li>${galaxy}</li>
        </c:forEach>

    </ul>

    <jsp:include page="footer.jsp"/>

</body>
</html>
