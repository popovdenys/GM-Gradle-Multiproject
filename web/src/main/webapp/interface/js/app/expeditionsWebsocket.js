/*
 * File : expeditionsWebsocket.js
 * Description : client Websocket
 *
 * Author : Popov Denys
 * Created : 25 Feb, 2018
 *
 * Modified : { date: 25/02/18
 *             ,time: 11:53 PM }
 * Modified by: Popov Denys
 *
 * Last modification : send/receive message to/from client
 */

var socket = new WebSocket("ws://localhost:8080/galaxy1/expeditionsProccessing");
socket.onmessage = onMessage;

function onMessage() {

    var expedition = JSON.parse(event.data);

    if (expedition.action == "add") {
        showExpedition(expedition);
    }

    if (expedition.action == "remove") {
        removeExpedition(expedition);
    }
}
function showExpedition(expedition) {
    var itinerary = document.getElementById("itinerary");
    var divDom = document.createElement("div");
    divDom.setAttribute("id", expedition.id);

    var html = "<h3>Expedition # " + expedition.id + "</h3>";
    html += "<p>Last update at " + expedition.update + "</p>";
    html += "<p>Current status : <b>" + expedition.status + "</b></p>";
    html += "<p>Itinerary : <b>" + expedition.itinerary + "</b></p>";
    html += "<p>Contractor is <i>" + expedition.contractor + "</i></p>";

    html += "<form id='form" + expedition.id + "'>";
        html += "<input type='hidden' name='id' value='" + expedition.id + "' />";
        html += "<select name='status'>";
            html += "<option value='put in project'>put in project</option>";
            html += "<option value='prepare to expedition'>prepare to expedition</option>";
            html += "<option value='verify calculation'>verify calculation</option>";
            html += "<option value='start expedition'>start expedition</option>";
            html += "<option value='tracking expedition'>tracking expedition</option>";

        html += "</select><input type='button' value='Update' onclick='sendUpdate(\"" + expedition.id + "\");' />";
    html += "</form>";

    divDom.innerHTML = html;

    itinerary.appendChild(divDom);
}

function removeExpedition(expedition) {
    var expeditionDivDom = document.getElementById(expedition.id);
    expeditionDivDom.remove();
}

function sendUpdate(id) {
    var form = document.getElementById("form" + id);
    var status = form.elements["status"].value;

    var message = {
        "id": id,
        "status": status
    };

    socket.send(JSON.stringify(message));
}