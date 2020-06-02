
// we want to know number of seats selected
// we want the id's of the seats selected
  function getSeatValue() {
    console.log("getSeatValue() triggered")

    let allSeats = document.getElementsByClassName("seat");
    let active = document.getElementsByClassName("seat active");

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

