'use strict';
var matchArea = document.querySelector('#matchArea');
var matchContainer = document.querySelector('#matchContainer');
var userNameTitle = document.querySelector('#userName');
var nameTitle = document.querySelector('#name');
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

        // Initialize message, loading circle
        var infoContainer = document.createElement("div");
        var loaderCircle = document.createElement("div");
        loaderCircle.setAttribute("id", "loadCircle");
        loaderCircle.classList.add("loader");
        infoContainer.appendChild(loaderCircle);
        var initMsg = document.createElement("h4");
        initMsg.setAttribute("id", "initialize");
        initMsg.innerHTML = "Websocket Initialized! Loading Matches Now";
        infoContainer.appendChild(initMsg);
        matchContainer.appendChild(infoContainer);

        window.setTimeout(
            function() {
                document.getElementById("initialize").remove();
                document.getElementById("loadCircle").remove();
                matchArea.classList.remove("hidden");
                stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'REQUEST'})); // Request first match
            }, 5000);



    } else if (msg.type === 'NEWMATCH') { // Received a new match, update UI
        newMatch(msg.userName, msg.name);
    } else if (msg.type === 'ERROR') {
        // hide match controls
        matchArea.classList.add("hidden");

        // Error message and loader circle that does nothing lol - Just thought it was cool...
        var errorMessage = document.createElement("h4");
        errorMessage.innerHTML = "Well that sucks, " + msg.userName + "\n\nTaking you home in 5 seconds..";
        var infoContainer = document.createElement("div");
        var loaderCircle = document.createElement("div");
        loaderCircle.classList.add("loader");
        infoContainer.appendChild(errorMessage);
        infoContainer.appendChild(loaderCircle);
        matchContainer.appendChild(infoContainer);

        matchContainer.appendChild(errorMessage);
        //window.setTimeout(
          //  function() {
           //     window.location.href = "http://localhost:8080/";
          //  }, 5000);
    }
}

function onError(error) {
    alert("-- Websocket Connection --\n" + error);
}

// Updated UI
function newMatch(userName, name) {
    matchUserName = userName;
    userNameTitle.innerHTML = matchUserName;
    nameTitle.innerHTML = name;
}


// Triggered by yes button in html file
function submitYes() {
    matchArea.classList.add("hidden");
    window.setTimeout(
        function() {
            var likeMsg = document.createElement("h4");
            likeMsg.setAttribute("id", "liked");
            likeMsg.innerHTML = "Liked: " + matchUserName;
            matchContainer.appendChild(likeMsg);
        }, 100);

    window.setTimeout(
        function() {
            document.getElementById("liked").remove();
            matchArea.classList.remove("hidden");
            stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'LIKE', userName: matchUserName}));
        }, 700);

}

// Triggered by no button in html file
function submitNo() {

    matchArea.classList.add("hidden");
    window.setTimeout(
        function() {
            var dislikeMsg = document.createElement("h4");
            dislikeMsg.setAttribute("id", "disliked");
            dislikeMsg.innerHTML = "Disliked: " + matchUserName;
            matchContainer.appendChild(dislikeMsg);
        }, 200);

    window.setTimeout(
        function() {
            document.getElementById("disliked").remove();
            matchArea.classList.remove("hidden");
            stompClient.send("/app/matchmaking.getMatch", {}, JSON.stringify({type: 'DISLIKE', userName: matchUserName}));
        }, 800);


}

// Event listeners //
document.getElementById("Yes").addEventListener("click", submitYes);
document.getElementById("No").addEventListener("click", submitNo);



