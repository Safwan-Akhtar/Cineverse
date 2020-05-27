// const REQ = new XMLHttpRequest();
// function getMovieById() {
//
//     // The King's Man
//
//     // REQ.open("GET", `http://www.omdbapi.com/?apikey=c819fc9&i=tt6856242`);
//     // REQ.setRequestHeader("Content-Type", "Application/json");
//     // REQ.responseType = "json";
//     // REQ.send();
//
//
//     REQ.open("GET", "https://imdb8.p.rapidapi.com/title/get-releases?tconst=tt0944947");
//     REQ.setRequestHeader("x-rapidapi-host", "imdb8.p.rapidapi.com");
//     REQ.setRequestHeader("x-rapidapi-key", "5177a72475msh5e187a1f0dc4021p1abbffjsn4e135e71f191");
//     REQ.send(data);
//
//     REQ.onload = () => {
//         if(REQ.status == 200) {
//             console.dir(REQ);
//             console.log(REQ.response);
//             console.log("here is the movie");
//         } else {
//             console.log("there was an error in retrieving the data");
//         }
//     }
// }
// let buttGetMovieById = document.querySelector("#getMovie");
// buttGetMovieById.addEventListener("click", getMovieById);

function getMovie() {

axios.get(`http://www.omdbapi.com/?apikey=335035be&i=46`)
    .then(response => response.data ))
    .catch(err => console.error(err));

}