
    var firebaseConfig = {
        apiKey: "AIzaSyC0xu66VYhYiyYqQGx3yWXmowSGJfmvd2w",
        authDomain: "cougar-roomie-f13a3.firebaseapp.com",
        databaseURL: "https://cougar-roomie-f13a3.firebaseio.com",
        projectId: "cougar-roomie-f13a3",
        storageBucket: "cougar-roomie-f13a3.appspot.com",
        messagingSenderId: "366126957393",
        appId: "1:366126957393:web:a1c5531b66d4e1e6d38970"
    };

    // Initialize Firebase
    firebase.initializeApp(firebaseConfig);

    firebase.auth().onAuthStateChanged(function(user) { // this will be called every time the status changes logged in/logged out
        if (user) {
          if (location.pathname === "/") {setupNav(user);}
        } else {
          if (location.pathname === "/") {setupNav();}
        }
    });

    function logout() {
        firebase.auth().signOut().then(function() {
        }).catch(function(error) {
            alert("an error occurred");
        });
    }
