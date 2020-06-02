
  var inactive = document.getElementsByClassName(".cinema-seats .seat").length;
  inactive.valueOf()
  console.log(inactive)

  var active = document.getElementsByClassName(".cinema-seats .seat active").length;
  active.valueOf()
  console.log(active)


  function getSeatValue() {
    console.log("getSeatValue() triggered")
    let element = document.getElementsByClassName(".cinema-seats .seat");
    let count = 0;
    console.log(element);

    array.forEach(element => {
      if (element.className === ".cinema-seats .seat active") {
        count++;
        console.log(count);
      } else {
        //do nothing
      }
    });

  }

  let countSeats = document.querySelector('countSeats');
  console.log(countSeats);
  countSeats.addEventListener('click', getSeatValue);

