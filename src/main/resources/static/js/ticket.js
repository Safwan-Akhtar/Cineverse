const postBooking = () => {
    let customername = document.getElementById("customername").value;
    let movieTitle = document.getElementById("movieTitle").value;
    let screeningDate = document.getElementById("screeningDate").value;
    let screeningTime = document.getElementById("screeningTime").value;
    let adult = document.getElementById("adult").value;
    let child = document.getElementById("child").value;
    let student = document.getElementById("student").value;
    let seatClass = document.getElementById("seatClass").value;

    console.log(customername);
    console.log(movieTitle);
    console.log(screeningDate);
    console.log(screeningTime);
    console.log(adult);
    console.log(child);
    console.log(student);
    console.log(seatClass);

}

let postButton = document.querySelector('#postButton');
postButton.addEventListener('click', postBooking);