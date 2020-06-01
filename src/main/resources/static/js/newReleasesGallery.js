// Artemis Fowl
const reqOne = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt3089630`);
// Tenet
const reqTwo = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt6723592`);
// Mulan
const reqThree = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt4566758`);
// The King's Man
const reqFour = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt6856242`);
// Antebellum
const reqFive = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt10065694`);

function axiosAll()
    {
        axios.all([reqOne, reqTwo, reqThree, reqFour, reqFive]).then(axios.spread((...responses) => {
            const responseOne = responses[0].data
            const responseTwo = responses[1].data
            const responseThree = responses[2].data
            const responseFour = responses[3].data
            const responseFive = responses[4].data
            const movies = [responseOne, responseTwo, responseThree, responseFour, responseFive]
            // use/access the results
            populateDiv(movies);
        })).catch(errors => {
            console.log("Oh no, something went wrong!")
            // react on errors.
        })
    }
let buttGetMovieFive = document.querySelector("#showMovie");
buttGetMovieFive.addEventListener("click", axiosAll);

// Populates divs onto html
function populateDiv(movies) {

    const div = this.document.getElementById("galleryDiv")

    for (let movie of movies) {

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

        // article.className = "";
        article.id = movie.imdbID;
        // divTwo.className = "";
        image.src = movie.Poster;
        image.position = "centre";
        // divThree.className = "";
        // divFour.className = "";
        hTwo.id = movie.imdbID;
        aTag.href = movie.Poster;
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

