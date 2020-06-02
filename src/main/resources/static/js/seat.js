function checkNumberSeats() {
  console.log("checkNumberSeats() triggered");
  let adultSeats = document.getElementById("adult").valueAsNumber;
  let childSeats = document.getElementById("child").valueAsNumber;
  let studentSeats = document.getElementById("student").valueAsNumber;
  let active = document.getElementsByClassName("seat active");

  let totalSeats = Number(adultSeats) + Number(childSeats) + Number(studentSeats);
  console.log(totalSeats);
  if (active.length === totalSeats) {
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
    console.log("Number of seats selected doesn't match!");
  }
}


  function getSeatValue() {

    console.log("getSeatValue() triggered");

    let allSeats = document.getElementsByClassName("seat");
    let active = document.getElementsByClassName("seat active");

    checkNumberSeats();

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

