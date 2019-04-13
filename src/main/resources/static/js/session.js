  var lastActiveTime = new Date().getTime();
    $(document).ready(function() {
        $('body').bind('click mousemove keypress scroll resize', function() {
           lastActiveTime = new Date().getTime();
           });
           setInterval(checkIdleTime, 1000); // 1 sec
    });

     function checkIdleTime(){
     var diff = new Date().getTime() - lastActiveTime;
           if( diff > 60000){//10 sec of inactivity
            window.location.href ="/"
           }
//           else{
//               $.ajax({url: '/refreshSession', error: function(data, status, xhr){
//                    alert("cannot refresh session on server: "+xhr);
//                    window.location.reload();}
//                  });
//           }
    }