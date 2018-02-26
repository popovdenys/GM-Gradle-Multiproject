<%--
  File : youChoice.jsp
  Description : list of choosen Galaxies

  Author : Popov Denys
  Created : 19/02/18

  Modified : { date: 24/02/18
              ,time: 07:46 PM }
  Modified by: Popov Denys

  Last modification : expedition info, plus refresh status
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Univers</title>
    <script type="application/javascript">
        function updateExpeditionStatus() {

            var request = new XMLHttpRequest();

            request.onreadystatechange = function () {

                if(this.readyState == 4) {
                    var json = JSON.parse(this.responseText);
                    document.getElementById("status").innerHTML = json.status;
                    document.getElementById("time").innerHTML = "Last updated at " + json.time;
                }

            }

            request.open("GET", "updatedExpeditionStatus?id=${id}", true);
            request.send();

        }
/*
        window.setInterval(
            function () {
                updateExpeditionStatus();
            }, 15000);
*/
    </script>
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

    <p>The current status of expedition is <b><span id="status">${status}</span></b> <input type="button" value="refresh status" onclick="updateExpeditionStatus()" /></p>
    <p id="time"></p>

    <p>Total distance is ${totalDistance} mly</p>

    <jsp:include page="footer.jsp"/> | <a href="updateExpeditionStatus.html">Set new status</a> <br/>
    <a href="jsp/expeditions.jsp">Async expeditions list</a> | <a href="expeditionsProcessing.html">Websocket expeditions list</a>

</body>
</html>
