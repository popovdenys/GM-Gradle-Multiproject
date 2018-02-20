<%--
  File : youChoice.jsp
  Description : list of choosen Galaxies

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

    <h2>Choosen Galaxies</h2>
    <ul>
        <%
            // int[] choosenCount = {0};
            // choosenGalaxies.stream().peek(g->++choosenCount[0]).forEach(Pattern.infoListNote(out));
        %>

        <c:forEach items="${choosenGalaxies}" var="galaxy">
            <li>${galaxy}</li>
        </c:forEach>

    </ul>

    <p><span>${choosenGalaxies.size()} Galaxies in total</span></p>

    <jsp:include page="footer.jsp"/>

</body>
</html>
