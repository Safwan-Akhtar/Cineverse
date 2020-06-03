// if total number of seats inputted matches selected, returns string array of types - otherwise defaults all to adult
function getSeatTypes(activeArr) {
  console.log("checkNumberSeats() triggered");
  let adultSeats = document.getElementById("adult").valueAsNumber;
  let childSeats = document.getElementById("child").valueAsNumber;
  let studentSeats = document.getElementById("student").valueAsNumber;

  let totalSeats = Number(adultSeats) + Number(childSeats) + Number(studentSeats);
  console.log(`Total seats inputted = ${totalSeats}`);

  let type = ``;
  if (activeArr.length === totalSeats) {

    for (let i = 0; i < adultSeats; i++){
      type += `adult,`;
    }
    for (let i = 0; i < childSeats; i++){
      type += `child,`;
    }
    for (let i = 0; i < studentSeats; i++){
      type += `student,`;
    }
    console.log(type);
  } else {
    console.log("Nah bra. Number of seats selected doesn't match!");
    for (let i = 0; i < activeArr.length; i++){
      type += `adult,`;
    }
    console.log("Types defaulted to adult");
    console.log(type);
  }
}

// returns a string array of ids for selected seats
  function getSeatIds() {
    console.log("getSeatValue() triggered");

    console.log(active); // HTML Collection []
    console.log(active.length); // total seats selected

    console.log("--------------")

    let arrSimple = Array.from(active);
    console.log(arrSimple);

    let seat = ``;
    for (let i = 0; i < active.length; i++){
      seat += `${arrSimple[i].id},`;
      console.log(seat);
    }
    console.log(seat);

  }

  let countSeatsHash = document.querySelector('#countSeats');
  countSeatsHash.addEventListener('click', function () {
    //  let allSeats = document.getElementsByClassName("seat");
    // "reg" is standard "deluxe" is deluxe
    let active = document.getElementsByClassName("seat reg active");
    let activeDeluxe = document.getElementsByClassName("seat deluxe active");

    // function to check if screen selection is deluxe/reg, count only type selected
    getSeatTypes(active);
    getSeatIds();
  });

