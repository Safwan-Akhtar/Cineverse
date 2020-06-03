function checkNumberSeats(activeArr) {
  console.log("checkNumberSeats() triggered");
  let adultSeats = document.getElementById("adult").valueAsNumber;
  let childSeats = document.getElementById("child").valueAsNumber;
  let studentSeats = document.getElementById("student").valueAsNumber;

  let totalSeats = Number(adultSeats) + Number(childSeats) + Number(studentSeats);
  console.log(totalSeats);
  if (activeArr.length === totalSeats) {
    let type = ``;
    for (let i = 0; i < adultSeats; i++){
      type += `adult,`;
      console.log(type);
    }
    for (let i = 0; i < childSeats; i++){
      type += `child,`;
      console.log(type);
    }
    for (let i = 0; i < studentSeats; i++){
      type += `student,`;
      console.log(type);
    }
    console.log(type);
  } else {
    console.log("Nah bra. Number of seats selected doesn't match!");
  }
}


  function getSeatValue() {

    console.log("getSeatValue() triggered");

  //  let allSeats = document.getElementsByClassName("seat");
    // "reg" is standard "deluxe" is deluxe
    let active = document.getElementsByClassName("seat reg active");
    let activeDeluxe = document.getElementsByClassName("seat deluxe active");

    // if screen selection is deluxe, only count del, otherwise, count reg
    checkNumberSeats(active);

    console.log(active); // HTML Collection []
    console.log(active.length); // total seats selected
    console.log(active[0]);
    console.log(active[0].id); // HTML Collection []

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
  countSeatsHash.addEventListener('click', getSeatValue);

