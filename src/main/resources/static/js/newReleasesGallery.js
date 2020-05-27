function yo () {
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

let buttGetMovieById = document.querySelector("#getMovie");
buttGetMovieById.addEventListener("click", yo);
