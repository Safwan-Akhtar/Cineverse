  
function check(input) {
    let valid = document.getElementsByClassName("input:valid")
    if (valid.length === 6) {

        if (input.value !== document.getElementById('password').value) {
            input.setCustomValidity('Password Must be Matching.');
            return "match";
        } else {
            // input is valid -- reset the error message
            input.setCustomValidity('');
            return "mismatch";
        }
    } 
}

const registerUser = () => {
    let forename = document.getElementById("validationServer01").value;
    let surname = document.getElementById("surname").value;
    let username = document.getElementById("username").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let passwordMatch = document.getElementById("passwordMatch").value;

    let matchCheckPass = check(input);
    if (matchCheckPass === "match") {
        console.log(matchCheckPass)
    } else {
        console.log(matchCheckPass)
    }



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



