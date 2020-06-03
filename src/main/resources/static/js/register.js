  
const registerUser = () => {
    let forename = document.getElementById("validationServer01").value;
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

function check(input) {
    if (input.value != document.getElementById('password').value) {
        input.setCustomValidity('Password Must be Matching.');
    } else {
        // input is valid -- reset the error message
        input.setCustomValidity('');
        let registerButton = document.querySelector('#registerButton');
        registerButton.addEventListener('click', registerUser);
    }
}





