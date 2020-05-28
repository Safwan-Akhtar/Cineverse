// Artemis Fowl
function artemisFowl () {
    axios({
        method: 'get',
        url: `http://www.omdbapi.com/?apikey=335035be&i=tt3089630`,
    })
        .then((response) => {
            console.log(response);
            populateDiv(response.data);
        }, (error) => {
            console.log(error);
        });
}
let buttGetMovieOne = document.querySelector("#showMovie");
buttGetMovieOne.addEventListener("click", artemisFowl);




    // Populates div

function populateDiv(movie) {

    const div = this.document.getElementById("movieDiv")

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

