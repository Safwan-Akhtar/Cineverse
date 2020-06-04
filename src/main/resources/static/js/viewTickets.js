let configGet = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:63342' },
    responseType: 'json'
};
let currentUser = localStorage.getItem('user');

const ticketFinder = () => {
    axios.get(`http://localhost:8181/readCustomersByName/${currentUser}`, configGet)
        .then(function (response) {
            let ticketsArr = response.data[0].tickets;
            console.log(response.data.tickets);
            console.log(response.data[0].tickets);
            let screeningId = ticketsArr[0].screenId;
            axios.get(`http://localhost:8181/getScreeningById/${screeningId}`, configGet)
                .then(function (response) {
                    let screeningData = response.data;
                    populateTicketsDiv(ticketsArr, screeningData);
                })
                .catch(function (error) {
                    console.log(error);
                })
                .catch(function (error) {
                    console.log(error);
                });
        })
    }

const container = document.createElement('div')
container.setAttribute('class', 'container')

// set Prices
let price = ``;


function populateTicketsDiv(tickets, screening) {
const div = document.querySelector("#bookingResponse");

    for (let ticket of tickets) {
        const card = document.createElement('div');
        card.setAttribute('class', 'card-ticket');

        const cardBody = document.createElement("div");
        cardBody.setAttribute('class', 'card-body-ticket');
        const cardBodyTwo = document.createElement("ul");
        cardBodyTwo.setAttribute('class', 'list-group list-group-flush');

        // ticket has ticketId, ticketType, seatNo, screenId (same as screen's screeningsId), userId
        const pTag = document.createElement("li");
        pTag.setAttribute('class', 'list-group-item');
        const pTagOne = document.createElement("li");
        pTagOne.setAttribute('class', 'list-group-item');
        const pTagTwo = document.createElement("li");
        pTagTwo.setAttribute('class', 'list-group-item');
        // userId
        const pTagThree = document.createElement("p");
        pTag.setAttribute('class', 'text-muted');

        pTag.textContent = "SeatNo: " + ticket.seatNo;
        pTagOne.textContent = "Type: " + ticket.ticketType; //adult/child/student
        pTagTwo.textContent = "Price: TBC" + price;

        // screenId links to movie Name / Date/Time, ScreenNumber & ScreenType
        let movieDateTime = screening.movieDateTime;
        let movieDate = movieDateTime.substring(0, 10);
        let movieTime = movieDateTime.substring(11, 16);

        const hTwoMovie = document.createElement("h2");
        hTwoMovie.setAttribute('class', 'card-header');
        hTwoMovie.textContent = `${screening.movieName}`;

        const hFourTime = document.createElement("h4");
        hFourTime.setAttribute('class', 'card-title');
        hFourTime.textContent = `${movieTime}`;

        const hFourScreen = document.createElement("h4");
        hFourScreen.setAttribute('class', 'card-subtitle mb-2 text-muted');
        hFourScreen.textContent = `Screen: ${screening.screenNumber} (${screening.screenType})`;

        const date = document.createElement("p");
        date.setAttribute('class', 'card-text');
        date.textContent = `${movieDate}`;

        const breaker = document.createElement("br");

        card.id = `Ticket${ticket.id}`;

        cardBodyTwo.appendChild(pTag);
        cardBodyTwo.appendChild(pTagOne);
        cardBodyTwo.appendChild(pTagTwo);
        cardBodyTwo.appendChild(pTagThree);
        cardBody.appendChild(hTwoMovie);
        cardBody.appendChild(hFourTime);
        cardBody.appendChild(hFourScreen);
        cardBody.appendChild(date);

        card.appendChild(cardBody);
        card.appendChild(cardBodyTwo);
        div.appendChild(breaker);
        div.appendChild(card);
    }
    }

    window.addEventListener('load', ticketFinder);