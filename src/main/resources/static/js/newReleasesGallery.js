// function name() {

// let movies = {};

// Artemis Fowl
const reqOne = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt3089630`);
        // .then((response) => {
        //     console.log(response);
        //     // movies.push(response.data);
        //     populateDiv(response.data);
        // }, (error) => {
        //     console.log(error);
        // });
// }
// let buttGetMovieOne = document.querySelector("#getOne");
// buttGetMovieOne.addEventListener("click", artemisFowl);



// Tenet
const reqTwo = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt6723592`);
        // .then((response) => {
        //     console.log(response);
        //     // movies.push(response.data);
        //     populateDiv(response.data);
        // }, (error) => {
        //     console.log(error);
        // });
// }
// let buttGetMovieTwo = document.querySelector("#getTwo");
// buttGetMovieTwo.addEventListener("click", tenet);





// Mulan
const reqThree = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt4566758`);
        // .then((response) => {
        //     console.log(response);
        //     // movies.push(response.data);
        //     populateDiv(response.data);
        // }, (error) => {
        //     console.log(error);
        // });
// }
// let buttGetMovieThree = document.querySelector("#getThree");
// buttGetMovieThree.addEventListener("click", mulan);





// The King's Man
const reqFour = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt6856242`);
        // .then((response) => {
        //     console.log(response);
        //     // movies.push(response.data);
        //     populateDiv(response.data);
        // }, (error) => {
        //     console.log(error);
        // });
// }
// let buttGetMovieFour = document.querySelector("#getFour");
// buttGetMovieFour.addEventListener("click", theKingsMan);




// Antebellum
const reqFive = axios.get(`http://www.omdbapi.com/?apikey=335035be&i=tt10065694`);
        // .then((response) => {
        //     console.log(response);
        //     // movies.push(response.data);
        //     populateDiv(response.data);
        // }, (error) => {
        //     console.log(error);
        // });
// }
// let buttGetMovieFive = document.querySelector("#getFive");
// buttGetMovieFive.addEventListener("click", antebellum);

    // populateDiv(movies);

// }
// let buttGetMovieFive = document.querySelector("#showMovie");
// buttGetMovieFive.addEventListener("click", name);

function axiosAll()
    {
        // let movies = [];
        axios.all([reqOne, reqTwo, reqThree, reqFour, reqFive]).then(axios.spread((...responses) => {
            const responseOne = responses[0].data
            // populateDiv(responseOne);
            // movies.push(responseOne)
            const responseTwo = responses[1].data
            // populateDiv(responseTwo);
            // movies.push(responseOne)
            const responseThree = responses[2].data
            // populateDiv(responseThree);
            // movies.push(responseOne)
            console.log(responseOne)
            console.log(responseTwo)
            console.log(responseThree)
            const movies = [responseOne, responseTwo, responseThree]
            // use/access the results
            populateDiv(movies);
        })).catch(errors => {
            // react on errors.
        })
    }
let buttGetMovieFive = document.querySelector("#showMovie");
buttGetMovieFive.addEventListener("click", axiosAll);









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
        // image.position = "";
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

