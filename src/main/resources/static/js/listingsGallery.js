const axios = require('axios');

const display = document.querySelector("#currentListingsDisplay");

axios.get('http://www.omdbapi.com/?i=tt3896198&apikey=6b2221c9').then(resp => {

    console.log(resp.data);
});