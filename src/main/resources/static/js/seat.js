$('.cinema-seats .seat').on('click', function () {
    $(this).toggleClass('active');
  });



  

  var inactive = document.getElementsByClassName(".cinema-seats .seat").length;
  inactive.valueOf()
  console.log(inactive)

  var active = document.getElementsByClassName(".cinema-seats .seat active").length;
  active.valueOf()
  console.log(active)


  function getSeatValue() {
    element = document.getElementsByClassName(".cinema-seats .seat");
    
    let count = 0;

    array.forEach(element => {
        console.log(count);
        count++;
    });


  }



  let countSeats = document.querySelector('#countSeats');
  countSeats.addEventListener('click', seatScan);

