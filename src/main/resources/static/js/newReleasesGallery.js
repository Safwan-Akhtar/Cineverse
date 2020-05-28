// Artemis Fowl
function artemisFowl () {
    axios({
        method: 'get',
        url: `http://www.omdbapi.com/?apikey=335035be&i=tt3089630`,
    })
        .then((response) => {
            console.log(response);
        }, (error) => {
            console.log(error);
        });
}

let buttGetMovieOne = document.querySelector("#getOne");
buttGetMovieOne.addEventListener("click", artemisFowl);



// Tenet
function tenet () {
    axios({
        method: 'get',
        url: `http://www.omdbapi.com/?apikey=335035be&i=tt6723592`,
    })
        .then((response) => {
            console.log(response);
        }, (error) => {
            console.log(error);
        });
}

let buttGetMovieTwo = document.querySelector("#getTwo");
buttGetMovieTwo.addEventListener("click", tenet);



// Mulan
function mulan () {
    axios({
        method: 'get',
        url: `http://www.omdbapi.com/?apikey=335035be&i=tt6723592`,
    })
        .then((response) => {
            console.log(response);
        }, (error) => {
            console.log(error);
        });
}

let buttGetMovieThree = document.querySelector("#getThree");
buttGetMovieThree.addEventListener("click", mulan);





// The King's Man
function theKingsMan () {
    axios({
        method: 'get',
        url: `http://www.omdbapi.com/?apikey=335035be&i=tt6856242`,
    })
        .then((response) => {
            console.log(response);
        }, (error) => {
            console.log(error);
        });
}

let buttGetMovieFour = document.querySelector("#getFour");
buttGetMovieFour.addEventListener("click", theKingsMan);




// Antebellum
function antebellum () {
    axios({
        method: 'get',
        url: `http://www.omdbapi.com/?apikey=335035be&i=tt10065694`,
    })
        .then((response) => {
            console.log(response);
        }, (error) => {
            console.log(error);
        });
}

let buttGetMovieFive = document.querySelector("#getFive");
buttGetMovieFive.addEventListener("click", antebellum);


