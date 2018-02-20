<%--
  File : index.jsp
  Description : welcome list of all galaxies

  Author : Popov Denys
  Created : 19/02/18

  Modified : { date: 19/02/18
              ,time: 5:32 PM }
  Modified by: Popov Denys

  Last modification : list of galaxies
--%>
<%@ page import="po.galaxy.db.GalaxiesData" %>
<%@ page import="po.galaxy.domain.Galaxy" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Univers</title>
    <link rel="stylesheet" type="text/css" href="interface/css/styles.css">
</head>
<body>
    <h2>Known Galaxies</h2>
    <ul>
        <%
            List<Galaxy> galaxies = (List<Galaxy>) request.getAttribute("galaxies");

            for (Galaxy galaxy: galaxies) {
        %>
                <li><%=galaxy.toString()%></li>
        <%
            }
        %>

    </ul>

    <jsp:include page="footer.jsp"/>

</body>
</html>
