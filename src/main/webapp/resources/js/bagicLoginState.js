var myIndex = 0;
  function carousel() {
      var i;
      var x = document.getElementsByClassName("mySlides");
      for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";
      };
      myIndex++;
      if (myIndex > x.length) {myIndex = 1}
      x[myIndex-1].style.display = "block";
      setTimeout(carousel, 2500);
  };

  function openRightMenu() {
      document.getElementById("rightMenu").style.display = "block";
  };
  function closeRightMenu() {
      document.getElementById("rightMenu").style.display = "none";
  };
  

  $(function(){
    var lastScrollTop = 0, delta = 15;
    $(window).scroll(function(event){
       var st = $(this).scrollTop();

       if(Math.abs(lastScrollTop - st) <= delta)
          return; // 스크롤값을 받아서 리턴한다.
if ((st > lastScrollTop) && (lastScrollTop>0)) {
       // downscroll code
      $("#header").css("top","8px"); // 스크롤을 내렸을때 #header의 CSS 속성중 top 값을 -50px로 변경한다.

   } else {
      // upscroll code
      $("#header").css("top","8px"); // 스크롤을 올렸을때 #header의 CSS 속성중 top 값을 0px로 변경한다.
   }
       lastScrollTop = st;
    });
});
  
  
function openRegist(){
  document.getElementById('id02').style.display='block'
};


$(document).ready(function(){
	
	var loginResult = document.getElementById('success').value;
	
	if(loginResult==0){
		
	}else{
		document.getElementById('loginVar').style.display='none';
		document.getElementById('logOutVar').style.display='block';
	};
});


$(document).ready(function(){
	
	var logOutVar = document.getElementById('logOutVar');
	var formObj = $("form[id='loginStatus']");
	
	logOutVar.onclick = function(){
		formObj.attr("action", "logOut");
		formObj.submit();
		
	};
	
});


//$(document).ready(function(){
//	
//	var formObject = $("form[id='loginTry']");
//	var loginbut = document.getElementById('loginbtnTest');
//	
//	loginbut.onclick = function(){
//		formObject.attr("method","post");
//		formObject.submit();
//	};
//	
//});
