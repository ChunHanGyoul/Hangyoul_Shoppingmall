<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="han.gyoul.shop.category.dto.CategoryDTO"%>
<%
   List<CategoryDTO> ctlist = (List) request.getAttribute("list");
%>
<jsp:include page="../basicBody4.jsp" flush="true"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>분류관리</title>
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script> -->
<link rel="stylesheet" href="../resources/css/w3.css">
<link rel="stylesheet" href="../resources/css/basicBody1.css" type="text/css">
<script src="../resources/js/jquery-1.12.4.min.js"></script>
<script src="../resources/js/bagicLoginState.js"></script>
<script type="text/javascript" src="../resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">

var origin_category_no;
var origin_subclass_no;
var origin_category_name;

// 위에서 중복확인을 했겠지만 대분류 등록시 대분류코드에 숫자 이외에 다른 값이 가지 못하도록 하는 코드
// getElementByID 가 작동이 안되어서 여기도 임의의 클래스 적용
function check_Field(){
	
	var numbersOnly = $("#category_no").val();
	if(isNaN(numbersOnly)||numbersOnly<1){
		alert('숫자가 아닙니다!!');
		$("#category_no").focus();
		return false;
		}
	else{
		$("#registerForm").attr("action","./categoryInsert").submit();
		alert('등록되었습니다!')
			
	}
	
}
	


// 소분류 등록시에 소분류에 숫자 이외에 다른 값이 가지 못하도록 하는 코드
function checkfield(){
	var numberOnly = $("#subclass_no_in").val();
	if(isNaN(numberOnly)||numberOnly<1){
		alert('숫자만 입력가능합니다. 다시 작성해주세요!!');
		$("#subclass_no_in").focus();
		return false;
		}
	else{
		$("#registerForm_s").attr("action","./categoryInsert").submit();
		alert('등록되었습니다!')
			
	}
	
}

$(function(){
   $("#category, #subclass, .edit_delete").hide();//처음에 목록을 눌렀을 때 대분류,소분류 등록 및 수정/삭제 버튼을 숨김
   $("#btnCategory").on("click",function(){//대분류 등록화면을 띄움
      $("#category").show();
      $("#subclass").hide();
      $(".hide_register").hide();//등록할 때 수정키를 숨김
   });
   $("#btnSubclass").on("click",function(){//소분류 등록화면을 띄움
      $("#subclass").show();
      $("#category").hide();
      $(".hide_register").hide();//등록할 때 수정키를 숨김
   });
   $("#btnEdit_delete").on("click",function(){//수정/삭제 메뉴를 토글로 열었다 닫았다 해줌
      $(".edit_delete").toggle();
   });
   
});

//소분류수정
// function modify_subclass_onclick(){

// 	var category_no = document.getElementsByClassName("category_no")[0].value;
// 	var subclass_no = document.getElementsByClassName("subclass_no")[0].value;
// 	var category_name = document.getElementsByClassName("category_name")[0].value;
// //form
// var form = document.createElement("form");
// form.setAttribute("method","post");
// form.setAttribute("action","/shop/category/subclassModify")//보내줄 주소

// var hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "category_no");
// hiddenField.setAttribute("value", category_no);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "subclass_no");
// hiddenField.setAttribute("value", subclass_no);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "category_name");
// hiddenField.setAttribute("value", category_name);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "origin_category_no");
// hiddenField.setAttribute("value", origin_category_no);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "origin_subclass_no");
// hiddenField.setAttribute("value", origin_subclass_no);
// form.appendChild(hiddenField);

// document.body.appendChild(form);

// var numberOnly = subclass_no;
// if(isNaN(numberOnly)||numberOnly<1){
// 	alert('숫자만 입력가능합니다. 다시 작성해주세요!!');
// 	$("#subclass_no_in").focus();
// 	return false;
// 	}
// else{
	
// 	alert('등록되었습니다!')
// 	form.submit();		
// }

// // 폼 전송


// }
//javascript => jquery 소분류 수정
$(function(){
	$("#modify_subclass_onclick").on("click",function(e){
		var category_no = $("#category_no_in").val();
		var subclass_no = $("#subclass_no_in").val();
		var category_name = $("#category_name_in").val();
	
		  
		
	    var form = $("<form></form>");
	    form.attr("method","post").attr("action","./subclassModify");
	    var c_no = $("<input />").attr("type", "hidden").attr("name", "category_no").attr("value", category_no).appendTo(form);
	    var s_no = $("<input />").attr("type", "hidden").attr("name", "subclass_no").attr("value", subclass_no).appendTo(form);
	    var c_name = $("<input />").attr("type", "hidden").attr("name", "category_name").attr("value", category_name).appendTo(form);
	    var o_c_no = $("<input />").attr("type", "hidden").attr("name", "origin_category_no").attr("value", origin_category_no).appendTo(form);
	    var o_s_no = $("<input />").attr("type", "hidden").attr("name", "origin_subclass_no").attr("value", origin_subclass_no).appendTo(form);
// 	    var o_c_name = $("<input />").attr("type", "hidden").attr("name", "origin_category_name").attr("value", origin_category_name).appendTo(form);
	    
	    

	    

	    var numberOnly = subclass_no;
	    if(isNaN(numberOnly)||numberOnly<1){
	    	alert('숫자가 아닙니다!!');
	    	$("#subclass_no_in").focus();
	    	return false;
	    	}
	    else{
	    	
	    // 폼 전송
	   	
	    	$(document.body).append(form);
	    	e.preventDefault();
	    	form.submit();
	    	alert('등록되었습니다!');
	    	
	    }
	
	});
})
	


// 1차 소스
// function modify_subclass_onclick() {
//    var category_no = document.getElementById("category_no").value;
//    var subclass_no = document.getElementById("subclass_no").value;
//    var category_name = document.getElementById("category_name").value;
//    location.href="./subclassModify?category_no="+category_no + "&subclass_no="+subclass_no+"&category_name="+category_name + "&origin_category_no="+origin_category_no+"&origin_subclass_no="+origin_subclass_no+"&origin_category_name="+origin_category_name;
   
// };

//대분류수정
//자바스크립트로 form 을 만들어서 POST방식으로 넘긴다.
// function modify_onclick(){


//  	var category_no = $("#category_no").val();
// 	var subclass_no = $("#subclass_no").val();
// 	var category_name = $("#category_name").val();
// //form
// var form = document.createElement("form");
// form.setAttribute("method","post");
// form.setAttribute("action","/shop/category/categoryModify")//보내줄 주소

// var hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "category_no");
// hiddenField.setAttribute("value", category_no);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "subclass_no");
// hiddenField.setAttribute("value", subclass_no);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "category_name");
// hiddenField.setAttribute("value", category_name);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "origin_category_no");
// hiddenField.setAttribute("value", origin_category_no);
// form.appendChild(hiddenField);

// hiddenField = document.createElement("input");
// hiddenField.setAttribute("type", "hidden");
// hiddenField.setAttribute("name", "origin_category_name");
// hiddenField.setAttribute("value", origin_category_name);
// form.appendChild(hiddenField);

// document.body.appendChild(form);

// var numbersOnly = category_no;
// if(isNaN(numbersOnly)||numbersOnly<1){
// 	alert('숫자가 아닙니다!!');
// 	$("#category_no").focus();
// 	return false;
// 	}
// else{
// 	alert('등록되었습니다!')
// // 폼 전송
// 	form.submit();		
// }




// }

// javascript => jquery 대분류 수정
$(function(){
	$("#modify_onclick").on("click",function(e){
		var category_no = $("#category_no").val();
		var subclass_no = $("#subclass_no").val();
		var category_name = $("#category_name").val();
	
		  
		
	    var form = $("<form></form>");
	    form.attr("method","post").attr("action","./categoryModify");
	    var c_no = $("<input />").attr("type", "hidden").attr("name", "category_no").attr("value", category_no).appendTo(form);
	    var s_no = $("<input />").attr("type", "hidden").attr("name", "subclass_no").attr("value", subclass_no).appendTo(form);
	    var c_name = $("<input />").attr("type", "hidden").attr("name", "category_name").attr("value", category_name).appendTo(form);
	    var o_c_no = $("<input />").attr("type", "hidden").attr("name", "origin_category_no").attr("value", origin_category_no).appendTo(form);
	    var o_c_name = $("<input />").attr("type", "hidden").attr("name", "origin_category_name").attr("value", origin_category_name).appendTo(form);
	    
	    

	    

	    var numbersOnly = category_no;
	    if(isNaN(numbersOnly)||numbersOnly<1){
	    	alert('숫자가 아닙니다!!');
	    	$("#category_no").focus();
	    	return false;
	    	}
	    else{
	    	
	    // 폼 전송
	   	
	    	$(document.body).append(form);
	    	e.preventDefault();
	    	form.submit();
	    	alert('등록되었습니다!');
	    	
	    }
	
	});
})
	
	



// 1차에서 GET방식으로 값을 넘겨주던 방식
// function modify_onclick() {
//    var category_no = document.getElementById("category_no").value;
//    var subclass_no = document.getElementById("subclass_no").value;
//    var category_name = document.getElementById("category_name").value;
//    var ahref = "./categoryModify?category_no="+category_no + "&subclass_no="+subclass_no+"&category_name="+category_name +"&origin_category_no="+origin_category_no+"&origin_subclass_no="+origin_subclass_no+"&origin_category_name="+origin_category_name;
//    location.href="./categoryModify?category_no="+category_no + "&subclass_no="+subclass_no+"&category_name="+category_name +"&origin_category_no="+origin_category_no+"&origin_subclass_no="+origin_subclass_no+"&origin_category_name="+origin_category_name;
// };

// 수정버튼을 눌러서 소분류값이 10번이면 대분류를 그렇지 않으면 소분류수정을 띄워준다.


function btnModifyForm() {
   var no = event.target.id;
   var arr = event.target.value.split(",");//받아온 값을 나눠준다
   console.log(arr[0]);//category_no
   console.log(arr[1]);//subclass_no
   console.log(arr[2]);//category_name
   
   if(arr[1]=="10") {//대분류수정(소분류번호에 10번이 들어가게 되면 대분류로 인식)
      $("#category").show();
      document.getElementById("category_no").value = arr[0];
      document.getElementById("subclass_no").value = arr[1];
      document.getElementById("category_name").value = arr[2];
      origin_category_no = arr[0];
      origin_subclass_no = arr[1];
      origin_category_name = arr[2];
      $("#subclass").hide();
      $(".hide_edit").hide();
      $("#hide_editC").hide();
      $(".check").hide();
   } else {
	   //소분류수정
      $("#subclass").show();
      
		document.getElementById("category_no_in").value = arr[0];
		document.getElementById("subclass_no_in").value = arr[1];
		document.getElementById("category_name_in").value = arr[2];
      origin_category_no = arr[0];
      origin_subclass_no = arr[1];
      origin_category_name = arr[2];
      
      $("#category").hide();
      $(".hide_edit").hide();
      $("#hide_editC").hide();
      $(".check").hide();
   }
};

// 분류삭제
function btnDelete(){
	var no = event.target.id;
	var arr = event.target.value.split(",");//받아온 값을 나눠준다
	console.log(arr[0]);//category_no
	console.log(arr[1]);//subclass_no
	console.log(arr[2]);//category_name
	var category_no = arr[0];
	var subclass_no = arr[1];
	if(arr[1]=="10"){
		$.ajax({
			type : 'POST',
			url : './categoryDelete',
			data : "category_no="+category_no,
			success: function(response){
				alert("대분류 삭제되었습니다.");
				location='./list';
			}
		});
	}else{
		$.ajax({
			type : 'POST',
			url : './subclassDelete',
			data :  "category_no="+category_no+"&subclass_no="+subclass_no,
			success : function(response){
				alert("소분류 삭제되었습니다.");
				location='./list';
			}
		});
	}
}

//대분류중복확인
function category_noCheck(){
   var category_no = $('#category_no').val();
   $.ajax({
      type : 'POST',
      url : './category_noCheck',
      data : "category_no="+category_no,
      success: function(response){
//          alert(result); 0이 나오면 중복된 코드, 1이 나오면 사용가능
		console.log(response);
         if(response == "1"){
            alert("사용 가능합니다.");
         }else{
            alert("중복된 코드입니다.");
            $('#category_no').focus();
         }
      }
   });   
}

//소분류중복확인
function subclass_noCheck(){
   var subclass_no = $('.subclass_no').val();
   var category_no = $('.category_no').val();
   
//     alert(subclass_no);
//     alert(category_no);
   $.ajax({
      type : 'POST',
      url : './subclass_noCheck',
      data : "category_no="+category_no+"&subclass_no="+subclass_no,
      success: function(response){
//           alert(response); //0이 나오면 중복된 코드, 1이 나오면 사용가능 
         console.log(response);
         if(response == "1"){
            alert("사용 가능합니다.");
         }else{
            alert("중복된 코드입니다.");
            $('#subclass_no_in').focus();
         }
      }
   });   
}



</script>


</head>
<body>
	<div id="contentsArea" style = "margin : auto; margin-top : 100px; width : 60%;">
      <table border="1">
         <caption>분류관리</caption>

         <thead>
            <tr>
               <th>대분류번호</th>
               <th>소분류번호</th>
               <th>분류이름</th>
               <th class="edit_delete">수정/삭제</th>
            </tr>
         </thead>
         <!-- c:foreach 태그로 var 속성의 속성값인 category 변수로 설정하고 items 속성에서 El인 ${list} 로 호출 -->
         <!-- varStatus 속성의 속성값인 status 변수로 반복상태를 갖게 한다. -->
         <%
            int count = 0;
         %>
         <c:forEach var="category" items="${list}" varStatus="status">

            <tbody>
               <tr>
                  <td><c:out value="${category.category_no}" /></td>
                  <td><c:out value="${category.subclass_no}" /></td>
                  <td><c:out value="${category.category_name}" /></td>
                  <td class="edit_delete">
                     <button
                        id="edit<%=ctlist.get(count).getCategory_no() %><%=ctlist.get(count).getSubclass_no() %>"
                        type="button"
                        value="${category.category_no},${category.subclass_no},${category.category_name}"
                        onclick="btnModifyForm()">수정</button>
                     <button 
                     	id="delete<%=ctlist.get(count).getCategory_no() %><%=ctlist.get(count).getSubclass_no() %>"
                        type="button"
                        value="${category.category_no},${category.subclass_no},${category.category_name}"
                        onclick="btnDelete()">삭제</button>
                  	
                  </td>

               </tr>
            </tbody>
            <%
               count++;
            %>
         </c:forEach>

      </table>

      <div>
         <button type="button" id="btnCategory">대분류등록</button>
         <button type="button" id="btnSubclass">소분류등록</button>
         <button type="button" value="button"
            onclick="location.href='./list'">분류목록</button>
         <button type="button" id="btnEdit_delete">수정/삭제</button>
         <button type="button" onclick="location.href='../item/itemManagement_list'">상품목록</button>
      </div>
   </div>


   <div id="category">
      <section>
         <h1>대분류관리</h1>

         <form action="./categoryInsert" method="post" id="registerForm"
            name="registerform">
            <!-- 글을 등록 처리하는 categoryInsert 서비스로 넘긴다.  -->
            <fieldset>
               <legend>대분류 등록관리</legend>
               <p>
                  <label for="category_no">대분류코드 <strong class="require">필수</strong></label>
                  <input type="text" id="category_no" name="category_no" class="category_no_in"
                     required placeholder="대분류코드"> <input type="button"
                     value="분류코드중복확인" onclick="category_noCheck()" />
               </p>
               <p>
                  <label for="subclass_no">소분류코드 <strong class="require">필수</strong></label>
                  <input type="text" id="subclass_no" name="subclass_no"
                     required placeholder="소분류코드" value="10" readonly /><br /> <span
                     class="hide_edit">대분류코드는 자동으로 소분류코드 10이 입력됩니다.</span>
               </p>
               <p>
                  <label for="category_name">대분류이름 <strong class="require">필수</strong></label>
                  <input type="text" id="category_name" name="category_name"
                     required placeholder="대분류명" />
               </p>


               <div>
                  <button type="button" id="hide_editC" onclick="check_Field()">등록</button>
                  <button class="hide_register" type="button"
                     id="modify_onclick">수정</button>
                  <button type="reset">취소</button>
                  <button type="button" value="button"
                     onclick="location.href='./list'">목록</button>
               </div>
            </fieldset>
         </form>
      </section>
   </div>
   <div id="subclass">
      <section>
         <h1>소분류관리</h1>
         <!--          소분류 등록 서블렛으로 전송 -->
         <form action="./categoryInsert" method="post" id="registerForm_s" name="registerform_s">
            <!-- 글을 등록 처리하는 categoryInsert 로 값을 보내준다.  -->
            <!-- 소분류 등록할 때는 대분류인 (subclass_no 가 10인 분류) 분류명만 출력해야 하기 때문에 c:if태그를 사용하여 조건을 준다. -->
            <fieldset>
               <legend>소분류 등록관리</legend>
               <p></p>
               <p>
                  <label for="category_no">대분류 분류명 <strong class="require">필수</strong></label>
                  <select class="category_no" name="category_no" id="category_no_in">
                     <c:forEach var="category" items="${list}" varStatus="status">
                        <c:if test="${category.subclass_no eq '10'}">
                           <option value="${category.category_no}"><c:out
                                 value="${category.category_name}" /></option>
                        </c:if>
                     </c:forEach>
                  </select>
               </p>
               <p>
                  <label for="subclass_no">소분류코드 <strong class="require">필수</strong></label>
                  <input type="text" class="subclass_no" name="subclass_no" required id="subclass_no_in"
                     placeholder="소분류코드" />
                  <input type="button" value="분류코드중복확인" onclick="subclass_noCheck()">   
               </p>
               <p>
                  <label for="category_name">소분류이름 <strong class="require">필수</strong></label>
                  <input type="text" class="category_name" name="category_name" id="category_name_in"
                     required placeholder="소분류명" />
               </p>


               <div>
                  <button type="button" class="hide_edit" onclick="checkfield()">등록</button>
                  <button class="hide_register" type="button"
                     id="modify_subclass_onclick">소분류수정</button>
                  <button type="reset">취소</button>
                  <button type="button" value="button"
                     onclick="location.href='./list'">목록</button>
               </div>
            </fieldset>
         </form>
      </section>
   </div>


</body>
</html>