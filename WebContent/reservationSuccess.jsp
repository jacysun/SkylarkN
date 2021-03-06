<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Skylark</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/jquery-ui.min.css">
    <link href="css/style.css" rel="stylesheet" type="text/css" media="screen">
    <link href='https://fonts.googleapis.com/css?family=Oxygen:400,300,700' rel='stylesheet' type='text/css'>
    <script>alert("Your reservation has been confirmed!");</script>
  </head>

<body>
  <header>
    <nav id="header-nav" class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div class="navbar-brand">
            <a href="index.html"><h1>Skylark</h1></a>
          </div>

          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#collapsable-nav" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>

        <div id="collapsable-nav" class="collapse navbar-collapse">
          <ul id="nav-list" class="nav navbar-nav navbar-right">
            <li><a href="index.html">Home</a></li>
            <li><a href="href/about.html">About Skylark</a></li>
            <li><a href="href/policy.html">Policy</a></li>
            <li><a href="href/contact.html">Contact Us</a></li>
          </ul>
        </div>
      </div>
    </nav>
</header>

<div id="main-content" class="container">
  <div class="row">
    <div class="col-xs-12 col-sm-7">
        <img src="images/pic1.jpg" alt="Picture of airplane">
    </div>
    <div id="search" class="col-xs-12 col-sm-5">
       <p>Search Flights</p>
       <form id="form" name="search" action="FlightSearcher" method="get">
         <label><input type="radio" name="trip" value="one-way" onchange="checkReturn()">One-way</label>
         <label><input id="roundTrip" type="radio" name="trip" value="round-trip" onchange="checkReturn()">Round-trip</label><br>
         <p></p>
         <label for='from'>From</label>
         <input type='text' id='from' name='from' placeholder="e.g. BOS" pattern="[A-Z]{3}" required onchange="checkFrom()">
         <label for='to'>To</label>
         <input type='text' id='to' name='to' placeholder="e.g. LAX" pattern="[A-Z]{3}" required onchange="checkTo()"><br>
         <p></p>
         <label for='depart'>Depart</label>
         <input type='date' id='depart' name='depart' required>
         <label for='arrive'>Return</label>
         <input type='date' id='return' name='return'><br>  
         <p></p>
         <select name="seat type" id ="seat">
           <option value="Coach">coach seat</option>
           <option value="FirstClass">first class seat</option>
         </select><br>
         <p></p>
         <input type="submit" value="Search">
       </form>
    </div>
  </div> 
</div>
  
  <footer class="panel-footer">
    <div class="container">
      <div class="row">
        <section id="hours" class="col-sm-4">
          <span>Contact Hours:</span><br>
          Monday-Friday: 8am - 6pm<br>
          Saturday-Sunday: 9am - 5pm<br>
          <hr class="visible-xs">
        </section>
        <section id="address" class="col-sm-4">
          <span>Address:</span><br>
          55 North Lake Ave<br>
          Worcester, MA 01605
          <p>* </p>
          <hr class="visible-xs">
        </section>
        <section id="other" class="col-sm-4">
          <p>"xxx"</p>
          <p>""</p>
        </section>
      </div>
      <div class="text-center">&copy; 2016 All Rights Reserved. Skylark is a registered trademark of WPI, Inc.</div>
    </div>
  </footer>

  <script src="js/jquery-2.1.4.min.js"></script>
  <script src="js/jquery-ui.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/script.js"></script>
</body>
</html>