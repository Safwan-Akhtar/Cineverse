


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
        let searchText = ($("#searchText").val());
        getMovies(searchText);
        e.preventDefault();
    });
});

function getMovies(searchText) {
    axios.get("http://www.omdbapi.com?s="movieId + "&apikey=thewdb")
    .then((response) => {
        console.log(response);
    })
    .catch((err) => {
        console.log(err);
    });
}
