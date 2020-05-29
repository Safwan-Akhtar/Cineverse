
let configGet = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:8181/html/character.html' },
    responseType: 'json'
  };

const postBooking = () => {
    let customername = document.getElementById("customername").value;
    let movieTitle = document.getElementById("movieTitle").value;
    let screeningDate = document.getElementById("screeningDate").value;
    let screeningTime = document.getElementById("screeningTime").value;
    let adult = document.getElementById("adult").value;
    let child = document.getElementById("child").value;
    let student = document.getElementById("student").value;

    // console.log(customername);
    // console.log(movieTitle);
    // console.log(screeningDate);
    // console.log(screeningTime);
    // console.log(adult);
    // console.log(child);
    // console.log(student);

    axios.get(`http://localhost:8181/readScreeningsByName/${movieTitle}`, configGet)
    .then(function (response) {
        let movieSelected = response.data[0].movieName;
        console.log(response);
        console.log(movieSelected);

    })    
    .catch(function (error) {
        console.log(error);
    });
};

let postButton = document.querySelector('#postButton');
postButton.addEventListener('click', postBooking);