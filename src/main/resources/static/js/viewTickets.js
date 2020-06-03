let configGet = {
    headers: { 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': 'http://localhost:63342' },
    responseType: 'json'
};

let id = document.getElementById("customerName").value;
axios.get(`http://localhost:8181/getCustomerById/${id}`, configGet)
    .then(function (response) {
        let ticketsArr = response.data.tickets;
        console.log(response.data.tickets);

        populateTicketsDiv(ticketsArr);
    })
    .catch(function (error) {
        console.log(error);
    });

const container = document.createElement('div')
container.setAttribute('class', 'container')

function populateTicketsDiv(tickets) {
    const div = document.querySelector("#bookingResponse");
    for (let ticket of tickets) {

        const card = document.createElement('div');
        card.setAttribute('class', 'card');

        // ticket has ticketId, ticketType, seatNo, screenId, userId
        // screenId links to movie Name / Date/Time & ScreenType

        //   class="card-body" for div containing text
        //   class="card-title" for movie title
        //   class="card-text" for actors / directors
        // or "list-group-item" if you change to li

        const article = document.createElement("article");
        const divTwo = document.createElement("div");
        const image = document.createElement("img");
        const divThree = document.createElement("div");
        const divFour = document.createElement("div");
        const header = document.createElement("header");
        const hTwo = document.createElement("h2");
        const aTag = document.createElement("a");
        const pTag = document.createElement("p");
        const pTagOne = document.createElement("p");
        const pTagTwo = document.createElement("p");
        const pTagThree = document.createElement("p");


        article.id = movie.imdbID;

        image.src = movie.Poster;
        image.position = "centre";
        image.alt = `${movie.Title} poster`;


        // Link to details will be in title (h2) tag - can move to a button
        hTwo.id = movie.imdbID;
        aTag.href = "#";
        //aTag.onclick = `movieListingSelected('${movie.imdbID}')`;
        aTag.setAttribute('onclick', `movieListingSelected('${movie.imdbID}')`);
        aTag.textContent = movie.Title;


        pTag.textContent = "Release Date: " + movie.Released;
        pTagOne.textContent = "Cast: " + movie.Actors;
        pTagTwo.textContent = "Director: " + movie.Director;
        pTagThree.textContent = "Age Rating: " + movie.Rated;

        hTwo.appendChild(aTag);
        header.appendChild(hTwo);
        header.appendChild(pTag);
        header.appendChild(pTagOne)
        header.appendChild(pTagTwo);
        header.appendChild(pTagThree);
        divFour.appendChild(header);
        divThree.appendChild(divFour);
        divTwo.appendChild(image);
        article.appendChild(divTwo);
        article.appendChild(divThree);
        div.appendChild(article);
    }
}