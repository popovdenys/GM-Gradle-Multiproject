<%@ page import="po.galaxy.domain.Galaxy" %>
<%@ page import="java.util.List" %><%--
  File : searchResult.jsp
  Description : search result page

  Author : Popov Denys
  Created : 19/02/18

  Modified : { date: 19/02/18
              ,time: 11:37 PM }
  Modified by: Popov Denys

  Last modification : list of found galaxies
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Univers</title>
</head>
<body>
    <h3>Found route</h3>

    <ul>
        <%
            List<Galaxy> foundGalaxies = (List<Galaxy>) request.getAttribute("foundGalaxies");

            Integer countOfGalaxies = (Integer) request.getAttribute("countOfGalaxies");
            //foundGalaxies.stream().forEach(Pattern.infoListNoteJSP());

            for (Galaxy galaxy: foundGalaxies) {
        %>
            <li><%=galaxy.toString()%></li>
        <%
            }
        %>

    </ul>


    <p><span><%=countOfGalaxies%> Galaxies in total</span></p>

    <jsp:include page="footer.jsp"/>
</body>
</html>
