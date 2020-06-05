


function priceTotal() {
    let amount = parseInt(sessionStorage.getItem("price"));
    console.log(amount);
}

window.addEventListener('load', priceTotal());