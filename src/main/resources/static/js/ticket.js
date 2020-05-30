
let configGet = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:8181/html/character.html' },
    responseType: 'json'
  };
  


  const searchTimes = () => {
    let movieTitle = document.getElementById("movieTitle").value;
    axios.get(`http://localhost:8181/readScreeningsByName/${movieTitle}`, configGet)
    .then(function (response) {
        let movieSelected = response.data[0].movieName;
        let movieDateTime = response.data[0].movieDateTime;
        let movieDate = movieDateTime.substring(0,10)
        let movieTime = movieDateTime.substring(11,16)

        screeningsJson = response.data
        screeningsCount = Object.keys(screeningsJson).length;

        for (let i = 0; i < screeningsCount; i++) {
            //date
            let nodeDate = document.createElement("OPTION");
            nodeDate.classList.add("screenDateList");
            let textnodeDate = document.createTextNode(response.data[i].movieDateTime.substring(0,10));
            nodeDate.appendChild(textnodeDate);
            document.getElementById("dateList").appendChild(nodeDate);
            //time
            let nodeTime = document.createElement("OPTION");
            nodeTime.classList.add("screenTimeList");
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
            headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:8181/html/character.html' },
            responseType: 'json'
        })
        .then(function (response) {
            console.log(response);
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