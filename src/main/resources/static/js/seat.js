
  let inactive = document.getElementsByClassName(".cinema-seats .seat").length;
  inactive.valueOf();
  console.log(inactive); //0
  console.log(inactive.valueOf()); //0

  let active = document.getElementsByClassName(".cinema-seats .seat active").length;
  active.valueOf()
  console.log(active) //0
  console.log(active.valueOf()); //0


  function getSeatValue() {
    console.log("getSeatValue() triggered")
    let element = document.getElementsByClassName(".cinema-seats .seat");
    let count = 0;
    console.log(element);

    // we want to know number of seats selected
    // we want the id's of the seats selected


    array.forEach(element => {
      if (element.className === ".cinema-seats .seat active") {
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
  console.log(countSeatsHash);
  countSeatsHash.addEventListener('click', getSeatValue);

