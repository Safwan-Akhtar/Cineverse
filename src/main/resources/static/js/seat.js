
// let navTest = document.getElementsByClassName("nav-item active");
// console.log(navTest);
//
// let navTest2 = document.getElementsByClassName("nav-item");
// console.log(navTest2);

let inactive = document.getElementsByClassName("seat");
  inactive.valueOf();
  console.log(inactive);
  console.log(inactive.length); // 0
  console.log(inactive.valueOf()); //  HTML Collection []

  let active = document.getElementsByClassName("seat active");
  active.valueOf();
  console.log(active); // HTML Collection []
  console.log(active.length); // 0
  console.log(active.valueOf()); // HTML Collection []


  function getSeatValue() {
    console.log("getSeatValue() triggered")

    let inactive = document.getElementsByClassName("seat");
    inactive.valueOf();

    console.log(inactive); // HTML Collection []
    console.log(inactive.length); // 0
    console.log(inactive.valueOf()); //  HTML Collection []

    let active = document.getElementsByClassName("seat active");
    active.valueOf();
    console.log(active); // HTML Collection []
    console.log(active.length); // 0
    console.log(active.valueOf()); // HTML Collection []

    console.log("--------------")

    let element = document.getElementsByClassName("seat");
    let count = 0;
    console.log(element);

    // we want to know number of seats selected
    // we want the id's of the seats selected

    array.forEach(element => {
      if (element.className === "cinema-seats seat active") {
        count++;
        console.log(count);
        let seatNo = element.id;
        console.log(seatNo);
      } else {
        //do nothing
      }
    });

  }

  let countSeatsHash = document.querySelector('#countSeats');
  countSeatsHash.addEventListener('click', getSeatValue);

