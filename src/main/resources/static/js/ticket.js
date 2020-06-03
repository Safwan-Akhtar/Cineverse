
let configGet = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:63342' },
    responseType: 'json'
  };
  

// populates the Screening Time and Date based off the movie selected
  const searchTimes = () => {
    let movieTitle = document.getElementById("movieTitle").value;
    axios.get(`http://localhost:8181/readScreeningsByName/${movieTitle}`, configGet)
    .then(function (response) {
        let movieDateTime = response.data[0].movieDateTime;
        let movieDate = movieDateTime.substring(0,10)
        let movieTime = movieDateTime.substring(11,16)

        screeningsJson = response.data
        screeningsCount = Object.keys(screeningsJson).length;

        for (let i = 0; i < screeningsCount; i++) {
            // clears existing date and time

            // let existingDates = document.getElementsByClassName("screenDateListOp");
            // let existingTimes = document.getElementsByClassName("screenTimeListOp");
            // for (let i = 0; i < existingDates.length; i++){
            //     document.getElementById("dateList").removeChild();
            // }

            //date
            let nodeDate = document.createElement("OPTION");
            nodeDate.classList.add("screenDateListOp");
            let textnodeDate = document.createTextNode(response.data[i].movieDateTime.substring(0,10));
            nodeDate.appendChild(textnodeDate);
            document.getElementById("dateList").appendChild(nodeDate);
            //time
            let nodeTime = document.createElement("OPTION");
            nodeTime.classList.add("screenTimeListOp");
            let textnodeTime = document.createTextNode(response.data[i].movieDateTime.substring(11,16) + " --- " + response.data[i].screenType);
            nodeTime.appendChild(textnodeTime);
            document.getElementById("timeList").appendChild(nodeTime);
        }

        console.log(response);

        var dateControl = document.querySelector('input[type="date"]');
        dateControl.value = movieDate;

        var timeControl = document.querySelector('input[type="time"]');
        timeControl.value = movieTime;
    })    
    .catch(function (error) {
        console.log(error);
  });
};

document.getElementById("movieTitle").addEventListener('change', searchTimes);

// currently not functional? - shows deluxe/reg seating plan
function showSeatingPlan() {
    let screenType = document.getElementById("timeList").value; // this only selects the empty option
    let standardPlan = document.getElementById("standardSeatPlan");
    let deluxePlan = document.getElementById("deluxeSeatPlan");
    let screenPlanType = document.getElementById("screenPlanType");
    console.log(screenType);
    console.log(screenType.getText());
    console.log(screenType.value.getText());
    if (screenType.getText().endsWith("standard")) {
        screenPlanType.textContent = "Choose your seats...";
        standardPlan.style.display = "block";
        deluxePlan.style.display = "none";
    } else if (screenType.value.getText().endsWith("deluxe")){
        screenPlanType.textContent = "Choose your seats...";
        standardPlan.style.display = "none";
        deluxePlan.style.display = "block";
    } else {
        screenPlanType.textContent = "Select a time to choose your seats...";
    }
}

document.getElementById("timeList").addEventListener('change', showSeatingPlan)

// if total number of seats inputted matches selected, returns string array of types - otherwise defaults all to adult
function getSeatTypes(activeArr) {
    console.log("checkNumberSeats() triggered");
    let adultSeats = document.getElementById("adult").valueAsNumber;
    let childSeats = document.getElementById("child").valueAsNumber;
    let studentSeats = document.getElementById("student").valueAsNumber;

    let totalSeats = Number(adultSeats) + Number(childSeats) + Number(studentSeats);
    console.log(`Total seats inputted = ${totalSeats}`);

    let type = ``;
    if (activeArr.length === totalSeats) {

        for (let i = 0; i < adultSeats; i++){
            type += `adult,`;
        }
        for (let i = 0; i < childSeats; i++){
            type += `child,`;
        }
        for (let i = 0; i < studentSeats; i++){
            type += `student,`;
        }
        console.log(type);
    } else {
        console.log("Nah bra. Number of seats selected doesn't match!");
        for (let i = 0; i < activeArr.length; i++){
            type += `adult,`;
        }
        console.log("Types defaulted to adult");
        console.log(type);
    }
}

// returns a string array of ids for selected seats
function getSeatIds() {
    console.log("getSeatValue() triggered");

    console.log(active); // HTML Collection []
    console.log(active.length); // total seats selected

    console.log("--------------")

    let arrSimple = Array.from(active);
    console.log(arrSimple);

    let seat = ``;
    for (let i = 0; i < active.length; i++){
        seat += `${arrSimple[i].id},`;
        console.log(seat);
    }
    console.log(seat);

}

let countSeatsHash = document.querySelector('#countSeats');
countSeatsHash.addEventListener('click', function () {
    //  let allSeats = document.getElementsByClassName("seat");
    // "reg" is standard "deluxe" is deluxe
    let active = document.getElementsByClassName("seat reg active");
    let activeDeluxe = document.getElementsByClassName("seat deluxe active");

    // function to check if screen selection is deluxe/reg, count only type selected
    getSeatTypes(active);
    getSeatIds();
});


const postBooking = () => {
    let customername = document.getElementById("customername").value;
    let movieTitle = document.getElementById("movieTitle").value;
    let screeningDate = document.getElementById("screeningDate").value;
    let screeningTime = document.getElementById("screeningTime").value;
    let adult = document.getElementById("adult").value;
    let child = document.getElementById("child").value;
    let student = document.getElementById("student").value;
    axios.get(`http://localhost:8181/readScreeningsByName/${movieTitle}`, configGet)
    .then(function (response) {
        
        let foundId = response.data[0].screeningsId;

        axios({
            method: 'patch',
            url: `http://localhost:8181/addCustomerToScreening/${foundId}`,
            data: `{
                "name": "${customername}"
            }`,
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
            responseType: 'json'
        })
        .then(function (response) {
            console.log(response);
            axios({
                method: 'patch',
                url: `http://localhost:8181/addTicketsToCustomer/1`,
                data: `{
                    "ticketType": "child",
                    "seatNo": "F9"
                }`,
                headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
                responseType: 'json'
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (response) {
                console.log(response);
            });
        })
        .catch(function (response) {
            console.log(response);
        });
    })
    .catch(function (error) {
        console.log(error);
  });
}
let postButton = document.querySelector('#postButton');
postButton.addEventListener('click', postBooking);