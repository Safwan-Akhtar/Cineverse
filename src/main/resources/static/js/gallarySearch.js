
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
    axios.get(`http://www.omdbapi.com/?apikey=c737e3a5&t=`+searchText)
        .then((response) => {
            console.log(response);
            let movies = response.data.Search;
            let output = '';
            //underscore removes an error message
            $.each(movies = (_gallarySearch, movie) => {
                output += `
                <div classes="col-md-3">
                    <div class="well text-center">
                        <img src="${movie.Poster}">
                        <h5>${movie.Title}</h5>
                        <a onclick="movieSelected('${movie.imdbID}')" class="btn btn-primary" href="#">Movie Details</a>
                    </div>
                </div>
                `;
            });
            $('#movies').html(output);

        })
        .catch((err) => {
            console.log(err);
    });
}
