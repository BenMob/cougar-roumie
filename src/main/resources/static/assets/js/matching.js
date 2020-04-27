'use strict';
var matchArea = document.querySelector('#matchArea');
var userNameTitle = document.querySelector('#userName');
var matchUserName = null;
var stompClient = null;


function initConnect() {

    var socket = new SockJS('/match');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
}


function onConnected() {
    stompClient.subscribe('/user/queue/matchmaking', onMessageReceived);
    stompClient.send("/app/matchmaking.initialize", {}, JSON.stringify({type: 'INIT'}));
}


function onMessageReceived(payload) {
    var msg = JSON.parse(payload.body);

    if (msg.type === 'INIT') {
        alert("Initialized...");
        stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'REQUEST'}));
    } else if (msg.type === 'NOMATCH') {
        alert("No matches...")
    } else if (msg.type === 'NEWMATCH') {
        newMatch(msg.userName);
    } else if (msg.type === 'ERROR') {
        alert(msg.userName);
    }
}

function onError(error) {
    alert("Something went wrong..." + error);
}

function newMatch(userName) {
    matchUserName = userName;
    userNameTitle.innerHTML = matchUserName;
}



function submitYes() {
    stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'LIKE', userName: matchUserName}));
}

function submitNo() {
    stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'DISLIKE', userName: matchUserName}));
}


document.getElementById("Yes").addEventListener("click", submitYes);
document.getElementById("No").addEventListener("click", submitNo);



