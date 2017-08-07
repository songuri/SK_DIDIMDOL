<script   src="http://code.jquery.com/jquery-3.1.1.min.js"></script>

37.557774, 127.001846

lat <input type=text id=lat value=37.557774> <br> lon <input type=text id=lon value=127.001846> <br>
<button id=btn>weather</button>

<div id=msg></div>

<script>

   $("#btn").click(function() {
	   

	   var key = "342bd9672f19bbc63b63ec3b629cb610";
	   var lat = $("#lat").val();
	   var lon = $("#lon").val();

	   var q = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&APPID=" + key;
	   console.log(q);
	   $.ajax({
           url:q,
           dataType : 'json',
           success:function(result){
        	  
        	   /*
        	   echo  $msg['main']['temp'] ."<br>";
        	   echo  $msg['main']['humidity'] ."<br>";
        	   echo  $msg['weather'][0]['main'] ."<br>";
        	   */

        	   
        	  var msg = result.weather[0].description + "<br>";
        	  msg += " <img src=http://openweathermap.org/img/w/" +result.weather[0].icon + ".png>" ;        	  
   		   	  $('#msg').html(msg);
   		   	
   		 
   		 
          },
          error:function(request,status,error){
              alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
           }
     	});
	   
	   
	   
   });


</script>