
// Artemis Fowl
function artemisFowl () {
    axios({
      method: "get",
      url: `http://www.omdbapi.com/?apikey=c737e3a5&i=tt3089630`,
    }).then(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
}

let buttGetMovieOne = document.querySelector("#getOne");
buttGetMovieOne.addEventListener("click", artemisFowl);
