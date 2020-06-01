
/// original test alert function (required scripts at top of the html)

// $(document).ready(() => {
//     alert(1);
// });


/// test alert function - (alternative syntax, for when scripts are not at the top of html)

// jQuery(function ($) { alert(1); })(jQuery);


/// user input to console - working

// $(document).ready(() => {
//     $('#searchForm').on('submit', (e) => {
//         console.log($('#searchText').val());
//         e.preventDefault();
//     });
// });

$(document).ready(() => {
    $("#searchForm").on("submit", (e) => {
        let searchText = $("#searchText").val();
        getMovies(searchText);
        e.preventDefault();
    });
});

function getMovies(searchText) {
    axios.get(`http://www.omdbapi.com/?apikey=c737e3a5&s=`+searchText)
        .then((response) => {
            console.log(response.data.Search);
            let movies = response.data.Search;
            let output = '';
            //underscore removes an error message
            $.each(movies, (_gallarySearch, movie) => {
                output += `
                <div class="col-md-3">
                    <div class="well text-center">
                        <img src="${movie.Poster}" alt="${movie.Title}'s Movie Poster">
                        <h5>${movie.Title}</h5>
                        <a onclick="movieSelected('${movie.imdbID}')" class="btn btn-primary" href="#">Movie Details</a>
                    </div>
                </div>
                `;
                //<a onclick="movieSelected('${movie.imdbID}')" class="btn btn-primary" href="#">Movie Details</a>
            });
            $('#movies').html(output);

        })
        .catch((err) => {
            console.log(err);
    });
}


function movieSelected(id) {
    sessionStorage.setItem("movieId", id);
    window.location = "gallarySearchInfo.html";
    return false;
}


//Individual movie page info

function getMovie() {
    let movieId = sessionStorage.getItem("movieId");

    axios.get(`http://www.omdbapi.com/?apikey=c737e3a5&i=` + movieId)
        .then((response) => {
            console.log(response.data);
            let movie = response.data;

            let output = `
            <div class="row">
                <div class="col-md-4">
                    <img src="${movie.Poster}" class="thumbnail" alt="${movie.Title}'s Movie Poster">
                </div>
                <div class="col-md-8">
                    <h2>${movie.Title}</h2>
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Genre:</strong> ${movie.Genre}</li>
                        <li class="list-group-item"><strong>Released:</strong> ${movie.Released}</li>
                        <li class="list-group-item"><strong>Rated:</strong> ${movie.Rated}</li>
                        <li class="list-group-item"><strong>IMDB Rating:</strong> ${movie.imdbRating}</li>
                        <li class="list-group-item"><strong>Director:</strong> ${movie.Director}</li>
                        <li class="list-group-item"><strong>Writer:</strong> ${movie.Writer}</li>
                        <li class="list-group-item"><strong>Actors:</strong> ${movie.Actors}</li>
                    </ul>
                </div>
            </div>

            <div>
                <div class="row">
                    <div class="well">
                        <h3>Plot</h3>
                        ${movie.Plot}
                        <hr>
                        <a href="http://imdb.com/title/${movie.imdbID}" target="_blank" class="btn btn-primary">View IMDB</a>
                        <a href="gallarySearch.html" class="btn btn-default">Go back to search</a>
                    </div>
            </div>
            `;

            $("#movie").html(output);
        })

        
        .catch((err) => {
            console.log(err);
        });

}
