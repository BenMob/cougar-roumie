'use strict';
var matchArea = document.querySelector('#matchArea');
var matchContainer = document.querySelector('#matchContainer');
var loaderContainer = document.querySelector("#loaderContainer");
var infoMsg = document.querySelector("#infoMsg");
var userNameTitle = document.querySelector('#userName');
var nameTitle = document.querySelector('#name');
var userInfo = document.querySelector('#userInfo');
var picture = document.querySelector('#matchPic');
var matchUserName = null;
var stompClient = null;

// Triggered by page load
function initConnect() {

    var socket = new SockJS('/match');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, onConnected, onError);
}

// As soon as connection is upgraded to websocket
function onConnected() {
    stompClient.subscribe('/user/queue/matchmaking', onMessageReceived);
    stompClient.send("/app/matchmaking.initialize", {}, JSON.stringify({type: 'INIT'}));
}

// Every time a message gets received
function onMessageReceived(payload) {
    var msg = JSON.parse(payload.body);

    // Controller initialized successfully
    if (msg.type === 'INIT') {
        stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'REQUEST'})); // Request first match

    } else if (msg.type === 'NEWMATCH') { // Received a new match, update UI
        newMatch(msg.userName, msg.name, msg.bio, msg.image);
        loaderContainer.classList.add("hidden");
        matchArea.classList.remove("hidden");
    } else if (msg.type === 'ERROR') {
        // hide match controls
        matchArea.classList.add("hidden");
        infoMsg.innerHTML = msg.userName;
        loaderContainer.classList.remove("hidden");
        window.setTimeout(
            function() {
                window.location.href = "http://localhost:8080/";
            }, 1500);
    }
}

function onError(error) {
    alert("-- Websocket Connection --\n" + error);
}

// Updated UI
function newMatch(userName, name, usrInfo, img) {
    matchUserName = userName;
    userNameTitle.innerHTML = matchUserName;
    nameTitle.innerHTML = name;
    userInfo.innerHTML = usrInfo;
    if (img) {
        picture.src = "data:" + img.fileType + ";base64," + img.src;
    }
}



// Triggered by yes button in html file
function submitYes() {
    matchArea.classList.add("hidden");
    loaderContainer.classList.remove("hidden");
    stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'LIKE', userName: matchUserName}));
}

// Triggered by no button in html file
function submitNo() {
    matchArea.classList.add("hidden");
    loaderContainer.classList.remove("hidden");
    stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'DISLIKE', userName: matchUserName}));
}

// Event listeners //
document.getElementById("Yes").addEventListener("click", submitYes);
document.getElementById("No").addEventListener("click", submitNo);



