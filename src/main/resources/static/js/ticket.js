
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

        let screeningsJson = response.data;
        let screeningsCount = Object.keys(screeningsJson).length;

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
            let textNodeDate = document.createTextNode(response.data[i].movieDateTime.substring(0,10));
            nodeDate.appendChild(textNodeDate);
            document.getElementById("dateList").appendChild(nodeDate);
            //time
            let nodeTime = document.createElement("OPTION");
            nodeTime.classList.add("screenTimeListOp");
            let textNodeTime = document.createTextNode(response.data[i].movieDateTime.substring(11,16) + " --- " + response.data[i].screenType);
            nodeTime.appendChild(textNodeTime);
            document.getElementById("timeList").appendChild(nodeTime);
        }
        console.log(response);
    })    
    .catch(function (error) {
        console.log(error);
  });
};

document.getElementById("movieTitle").addEventListener('change', searchTimes);

// shows deluxe/reg seating plan depending on selection
function showSeatingPlan() {
    let screenType = document.getElementById("timeList").value;
    let standardPlan = document.getElementById("standardSeatPlan");
    let deluxePlan = document.getElementById("deluxeSeatPlan");
    let screenPlanType = document.getElementById("screenPlanType");
    if (screenType.endsWith("standard")) {
        screenPlanType.textContent = "Choose your seats...";
        standardPlan.style.display = "block";
        deluxePlan.style.display = "none";
    } else if (screenType.endsWith("deluxe")){
        screenPlanType.textContent = "Choose your seats...";
        standardPlan.style.display = "none";
        deluxePlan.style.display = "block";
    } else {
        screenPlanType.textContent = "Select a time to choose your seats...";
    }
}

document.getElementById("timeList").addEventListener('change', showSeatingPlan)

// build string separated by ,
let type;
let seat;

// if total number of seats inputted matches selected, returns string array of types - otherwise defaults all to adult
function getSeatTypes(activeArr) {
    console.log("getSeatTypes() triggered");
    let adultSeats = document.getElementById("adult").valueAsNumber;
    let childSeats = document.getElementById("child").valueAsNumber;
    let studentSeats = document.getElementById("student").valueAsNumber;

    let totalSeats = Number(adultSeats) + Number(childSeats) + Number(studentSeats);
    console.log(`Total seats inputted = ${totalSeats}`);

    //resets typeList if changing seats
    type = ``;
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
        window.alert("You haven't set the right number of seats, please try again.");
        console.log(type);
    }
}

// returns a string array of ids for selected seats
function getSeatIds(activeArr) {
    console.log("getSeatIds() triggered");
    console.log(`Total seats selected = ${activeArr.length}`);

    //resets seatIdList if changing seats
    seat = ``;

    for (let i = 0; i < activeArr.length; i++){
        seat += `${activeArr[i].id},`;
    }
    console.log(seat);

}

let countSeatsHash = document.querySelector('#countSeats');
countSeatsHash.addEventListener('click', function () {
    //  let allSeats = document.getElementsByClassName("seat");
    let activeStandard = document.getElementsByClassName("seat reg active");
    let activeDeluxe = document.getElementsByClassName("seat deluxe active");
    let active;

    let screenType = document.getElementById("timeList").value;
    // to check if screen selection is deluxe/reg, count only type selected
    if (screenType.endsWith("standard")){
        active = activeStandard;
    } else {
        active = activeDeluxe;
    }
    getSeatTypes(active);
    getSeatIds(active);
});


const postBooking = () => {
    let customername = document.getElementById("customername").value;
    let movieTitle = document.getElementById("movieTitle").value;
    // type and seat are built outside this function
    let typesArr = type.split(",");
    let seatArr = seat.split(",");
    console.log(typesArr);
    console.log(seatArr);
    console.log((typesArr.length-1));
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
            console.log(`screeningId = ${foundId}`);
            let customersJson = response.data.customers;
            let customersCount = Object.keys(customersJson).length;
            console.log(`Customers count = ${customersCount}`);
            axios.get(`http://localhost:8181/getCustomerById/${customersCount}`, configGet)
            .then(function (response) {
                console.log(`the response from get customer by customer count`);
                console.log(response.data);
                let lastCustomerId = response.data.customersId;
                console.log(`Last customerId = ${lastCustomerId}`);

                for (let i = 0; i < (typesArr.length-1); i++){
                    axios({
                        method: 'patch',
                        url: `http://localhost:8181/addTicketsToCustomer/${lastCustomerId}`,
                        // userId is not fully implemented yet, use:
                        // localStorage.getItem('user')
                        data: `{
                        "userId": "1",
                        "screenId": "${foundId}",
                        "ticketType": "${typesArr[i]}",
                        "seatNo": "${seatArr[i]}"
                }`,
                        headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' },
                        responseType: 'json'
                    })
                }
                //success!
                // alter to viewTickets.html & or basket?
                window.alert("Your tickets have been booked! You will now be directed to checkout");
                window.location.replace("../payment.html");
            })
                .catch(function (response) {
                console.log(response);
                });
        }
            )
            .catch(function (response) {
                console.log(response);
            });
        })
        .catch(function (response) {
            console.log(response);
        });
}
let postButton = document.querySelector('#postButton');
postButton.addEventListener('click', postBooking);