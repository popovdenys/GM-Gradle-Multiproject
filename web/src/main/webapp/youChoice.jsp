<%@ page import="po.galaxy.domain.Galaxy" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: popov
  Date: 19/02/18
  Time: 11:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Univers</title>
</head>
<body>

    <h2>Choosen Galaxies</h2>
    <ul>
        <%
            List<Galaxy> choosenGalaxies = (List<Galaxy>) session.getAttribute("choosenGalaxies");

            // int[] choosenCount = {0};
            // choosenGalaxies.stream().peek(g->++choosenCount[0]).forEach(Pattern.infoListNote(out));

            for (Galaxy galaxy: choosenGalaxies) {
        %>
            <li><%=galaxy.toString()%></li>
        <%
            }
        %>

    </ul>

    <p><span><%=choosenGalaxies.size()%> Galaxies in total</span></p>

    <jsp:include page="footer.jsp"/>

</body>
</html>
