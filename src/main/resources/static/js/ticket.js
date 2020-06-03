
let configGet = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:63342' },
    responseType: 'json'
  };
  


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

// currently not functional?
function showSeatingPlan() {

    console.log("method showSeatingPlan started");
    let screenType = document.getElementById("screeningTime");
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

document.getElementById("timeList").addEventListener('change', function () {
    console.log("change to screeningTime detected");
    showSeatingPlan();
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