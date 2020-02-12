


//login
const registerForm = document.querySelector('#login-form');
registerForm.addEventListener('submit', (e) => {
    e.preventDefault();

    //get user information
    const email = registerForm['email'].value;
    const pass = registerForm['password'].value;

    firebase.auth().signInWithEmailAndPassword(email, pass).catch(function(error) {
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