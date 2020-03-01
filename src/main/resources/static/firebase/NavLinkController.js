const loggedInLinks = document.querySelectorAll(".logged-in");
const loggedOutLinks = document.querySelectorAll(".logged-out");

const setupNav = (user) => {
    if (user) {
        console.log("Logged In");
        loggedInLinks.forEach(item => item.style.display = 'block');
        loggedOutLinks.forEach(item => item.style.display = 'none');
    } else {
        console.log("Logged Out");
        loggedInLinks.forEach(item => item.style.display = 'none');
        loggedOutLinks.forEach(item => item.style.display = 'block');
    }
}