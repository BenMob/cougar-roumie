

//login
const loginForm = document.querySelector('#login-form');
loginForm.addEventListener('submit', (e) => {
    e.preventDefault();

    if (firebase.auth().currentUser) { //if user is already logged in, log them out
        logout();
    }

    //get user information
    const email = loginForm['email'].value;
    const pass = loginForm['password'].value;

    firebase.auth().signInWithEmailAndPassword(email, pass)
        .then(function (result) {
            alert('sign in success .... redirecting');
            location.pathname = "/";
        })
        .catch(function(error) { // sign in user
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        if (errorCode === 'auth/wrong-password') {
            alert('Wrong password.');
        } else {
            alert(errorMessage);

        }
    });
});