<%--
  File : chooseExpedition.jsp
  Description : List of Galaxies to choose

  Author : Popov Denys
  Created : 19/02/18

  Modified : { date: 20/02/18
              ,time: 12:58 PM }
  Modified by: Popov Denys

  Last modification : jstl added
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Univers</title>
</head>
<body>
    <h2>Create new Expedition from</h2>
    <h2>known Galaxies</h2>

    <form action="choosenRoute.html" method="POST">

        <input type="text" name="contractor" />

        <ul>

            <c:forEach items="${galaxiesOfChoice}" var="galaxy">
                <li>${galaxy}<input type='checkbox' name='galaxy-${galaxy.id}'></li>
            </c:forEach>
        </ul>

        <input type="submit" value="Make my choise">
    </form>

    <jsp:include page="footer.jsp"/>
</body>
</html>
