firebase.auth().onAuthStateChanged(user => {
    var user = firebase.auth().currentUser;
    var userName;

    if (user != null) {
        if (user.displayName == null) {
            userName = "Unknown";
        } else {
            userName = user.displayName;
        }
        document.getElementById("welcome").innerHTML = "Welcome, " + userName;
        document.getElementById("emailAddress").innerHTML = "Email: " + user.email;
        document.getElementById("currentName").innerHTML = "Name: " + userName;
    }


    const AccountForm = document.querySelector('#Account-form');
    AccountForm.addEventListener('submit', (e) => {
        e.preventDefault();

        //get user information
        const email = AccountForm['email'].value;
        userName = AccountForm['name'].value;

        if (email) {
            user.updateEmail(email).then(function() {
                alert("Email was successfully changed to: " + email + " please sign in with your new email");
                logout();
            }).catch(function(error) {
                alert(error);
            });
        }
        if (userName) {
            user.updateProfile({
                displayName: userName
            }).then(function() {
                location.reload();
            }).catch(function(error) {
                alert('Name Error');
            });
        }

    });
});


