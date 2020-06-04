// How to Train Your Dragon: Hidden World
const reqOne = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt2386490`);

// Frozen 2
const reqTwo = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt4520988`);

// Spiderman into the Spiderverse
const reqThree = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt4633694`);

// Mulan (2020)
const reqFour = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt4566758`);

// Avengers: Infinity War
const reqFive = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt4154756`);

// Avengers: Endgame
const reqSix = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt4154796`);

function axiosAll() {
    axios.all([reqOne, reqTwo, reqThree, reqFour, reqFive, reqSix]).then(axios.spread((...responses) => {
        const responseOne = responses[0].data;
        const responseTwo = responses[1].data;
        const responseThree = responses[2].data;
        const responseFour = responses[3].data;
        const responseFive = responses[4].data;
        const responseSix = responses[5].data;
        const movies = [responseOne, responseTwo, responseThree, responseFour, responseFive, responseSix]
        // for displaying on the page
        populateDiv(movies);
    })).catch(errors => {
        console.log("Oh dear, something went wrong...");
        console.log(errors);
        // react on errors.
    })
}

window.addEventListener("load", axiosAll);

// Populates divs
function populateDiv(movies) {

    const div = document.querySelector("#currentListingsDisplay");

    const container = document.createElement('div')
    container.setAttribute('class', 'container')

    for (let movie of movies) {
        // card should be wrapped...

        const card = document.createElement('div');
        card.setAttribute('class', 'card movie-card');
        card.setAttribute('style', 'width: 25vw;');

        //   class="card-text" for actors / directors
        // or "list-group-item" if you change to li

        const image = document.createElement("img");
        image.setAttribute('class', 'card-img-top');

        const divTwo = document.createElement("div");
        divTwo.setAttribute('class', 'card-body');

        const hTwo = document.createElement("h2");
        hTwo.setAttribute('class', 'card-title');
        const aTag = document.createElement("a");

        const pTag = document.createElement("p");
        pTag.setAttribute('class', 'card-text');
        const pTagOne = document.createElement("p");
        pTagOne.setAttribute('class', 'card-text');
        const pTagTwo = document.createElement("p");
        pTagTwo.setAttribute('class', 'card-text');
        const pTagThree = document.createElement("p");
        pTagThree.setAttribute('class', 'card-text');

        image.src = movie.Poster;
        image.position = "centre";
        image.alt = `${movie.Title} poster`;
        image.size

        // Link to details on title - can move to a button
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
        divTwo.appendChild(hTwo);
        divTwo.appendChild(pTag);
        divTwo.appendChild(pTagOne);
        divTwo.appendChild(pTagTwo);
        divTwo.appendChild(pTagThree);
        card.appendChild(image);
        card.appendChild(divTwo);
        container.appendChild(card);
    }
    div.appendChild(container);
}


// This function can probably be merged with the gallerySearch.js & gallerySearchInfo.html page
function movieListingSelected(id) {
    sessionStorage.setItem("movieId", id);
    window.location = "moviePageInfo.html";
    return false;
}
