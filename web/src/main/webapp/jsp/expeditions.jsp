<%--
  File : expeditions.jsp
  Description : list of all expeditions

  Author : Popov Denys
  Created : 25/02/18

  Modified : { date: 25/02/18
              ,time: 11:05 AM }
  Modified by: Popov Denys

  Last modification : todo - move JavaScript to separate file
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Univers</title>

    <script type="application/javascript">
        var connection = false;
        var expedition = 1;
        function getExpeditions() {
            if(!connection) {
                connection = true;
                var request = new XMLHttpRequest();
                request.onreadystatechange = function (ev) {
                    if( request.readyState == 4 && request.status == 200 ) {
                        connection = false;
                        expedition++;
                        var expeditionsDom = document.getElementById("expeditions");
                        expeditionsDom.innerHTML = request.responseText + expeditionsDom.innerHTML;
                    }
                }

                console.log("get expedition #" + expedition);

                request.open("GET", "../expeditionsServlet?size=" + expedition, true);
                request.send();
            }
        }
        setInterval(getExpeditions, 1000);
    </script>
</head>
<body>
<h2>List of expeditions</h2>

<div id="expeditions"></div>

<jsp:include page="footer.jsp"/>
</body>
</html>
