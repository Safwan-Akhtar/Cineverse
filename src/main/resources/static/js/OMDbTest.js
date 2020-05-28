let configGet = {
    headers: { 'Content-Type': 'application/json' },
    responseType: 'json'
  };

function getMovieCurrentDB(){
    axios.get('https://api.themoviedb.org/3/movie/now_playing?api_key=70cf4dddb2d9a01d923c561a9d7797ac&language=en-US&page=1', configGet)
    .then(function (response) {
        console.log(response)
    });
}

function getMovieUpcomingDB(){
    axios.get('https://api.themoviedb.org/3/movie/upcoming?api_key=70cf4dddb2d9a01d923c561a9d7797ac&language=en-US&page=1', configGet)
    .then(function (response) {
        console.log(response)
    });
}
window.addEventListener("load", getMovieCurrentDB);
window.addEventListener("load", getMovieUpcomingDB);