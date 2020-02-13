

//login
const registerForm = document.querySelector('#login-form');
registerForm.addEventListener('submit', (e) => {
    e.preventDefault();

    if (firebase.auth().currentUser) { //if user is already logged in, log them out
        logout();
    }

    //get user information
    const email = registerForm['email'].value;
    const pass = registerForm['password'].value;

    firebase.auth().signInWithEmailAndPassword(email, pass).catch(function(error) { // sign in user
        // Handle Errors here.
        var errorCode = error.code;
        var errorMessage = error.message;
        if (errorCode === 'auth/wrong-password') {
            alert('Wrong password.');
        } else {
            alert(errorMessage);
        }
    });

})