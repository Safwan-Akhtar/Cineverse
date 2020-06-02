  
const registerUser = () => {
    let forename = document.getElementById("forename").value;
    let surname = document.getElementById("surname").value;
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let passwordMatch = document.getElementById("passwordMatch").value;

    axios({
        method: 'post',
        url: 'http://localhost:8181/createUser',
        data: `{
            "username" : "${username}",
            "password" : "${password}",
            "matchingPassword" : "${passwordMatch}",
            "roles" : "ROLE_USER",
            "active" : "true",
            "email" : "${email}",
            "forename" : "${forename}",
            "surname" : "${surname}"
        }`,
        headers: {'Content-Type': 'application/json' }
        })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (response) {
            console.log(response);
        }); 
}

let registerButton = document.querySelector('#registerButton');
registerButton.addEventListener('click', registerUser);