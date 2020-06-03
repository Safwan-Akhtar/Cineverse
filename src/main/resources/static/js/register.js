
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

function checkForm(form)
{
    if(form.username.value == "") {
        alert("Error: Username cannot be blank!");
        form.username.focus();
        return false;
    }
    re = /^\w+$/;
    if(!re.test(form.username.value)) {
        alert("Error: Username must contain only letters, numbers and underscores!");
        form.username.focus();
        return false;
    }

    if(form.password.value != "" && form.password.value == form.passwordMatch.value) {
        if(form.password.value.length < 6) {
            alert("Error: Password must contain at least six characters!");
            form.password.focus();
            return false;
        }
        if(form.password.value == form.username.value) {
            alert("Error: Password must be different from Username!");
            form.password.focus();
            return false;
        }
        re = /[0-9]/;
        if(!re.test(form.password.value)) {
            alert("Error: password must contain at least one number (0-9)!");
            form.password.focus();
            return false;
        }
        re = /[a-z]/;
        if(!re.test(form.password.value)) {
            alert("Error: password must contain at least one lowercase letter (a-z)!");
            form.password.focus();
            return false;
        }
        re = /[A-Z]/;
        if(!re.test(form.password.value)) {
            alert("Error: password must contain at least one uppercase letter (A-Z)!");
            form.password.focus();
            return false;
        }
    } else {
        alert("Error: Please check that you've entered and confirmed your password!");
        form.password.focus();
        return false;
    }

    alert("You entered a valid password: " + form.password.value);
    return true;
}




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
let registerButton = document.querySelector('#registerButton');
registerButton.addEventListener('click', registerUser);




