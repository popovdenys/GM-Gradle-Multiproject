<%@ page import="po.galaxy.domain.Pattern" %>
<%@ page import="java.util.List" %>
<%@ page import="po.galaxy.domain.Galaxy" %><%--
  Created by IntelliJ IDEA.
  User: popov
  Date: 19/02/18
  Time: 11:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Univers</title>
</head>
<body>
    <h2>Known Galaxies</h2>
    <form action="choosenRoute.html" method="POST">
        <ul>
            <%
                List<Galaxy> choosenGalaxies = (List<Galaxy>) request.getAttribute("galaxiesOfChoice");

                //galaxies.stream().forEach(Pattern.inputGalaxyNoteJSP(Pattern.InputType.CHECKBOX.get()));

                for (Galaxy galaxy: choosenGalaxies) {
            %>
                    <li><%=galaxy.toString()%><input type='checkbox' name='galaxy-<%=galaxy.getId()%>'></li>
            <%
                }
            %>
        </ul>

        <input type="submit" value="Make my choise">
    </form>

    <jsp:include page="footer.jsp"/>
</body>
</html>
