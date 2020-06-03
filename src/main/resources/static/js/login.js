const storeUser = () => {

    let username = document.getElementById("username").value;
    localStorage.setItem('user', username);

}

let userLog = document.querySelector('#userLog');
userLog.addEventListener('click', storeUser);




