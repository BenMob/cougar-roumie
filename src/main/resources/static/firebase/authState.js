
firebase.auth().onAuthStateChanged(function(user) { // this will be called every time the status changes logged in/logged out
    if (user) {
        if (location.pathname === "/") {setupNav(user);} // The page is home, so set links to logged in..
    } else {
        if (location.pathname === "/") {setupNav();} // The page is home, so set links to logged out..
    }
});
