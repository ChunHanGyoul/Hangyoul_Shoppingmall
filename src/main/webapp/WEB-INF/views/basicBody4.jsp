<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/w3.css">
  <link rel="stylesheet" href="resources/css/basicBody1.css" type="text/css">
  <base target="_self"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>:: 마이페이지 ::</title>
</head>
<body>
<input type="hidden" value="${success}" id="success"/>
 <!--카테고리 헤더-->
    <header>
      <div id="header">
        <table  style="position:absolute; left:7.7cm; bottom:-8px; border-spacing:100px 0px;">
          <th><a href="#" class="MenuLink">TOP</a></th>
          <th><a href="#" class="MenuLink">BOTTOM</a></th>
          <th><a href="#" class="MenuLink">OUTWEAR</a></th>
          <th><a href="#" class="MenuLink">DRESS</a></th>
        </table>
        <p style="position:absolute; left:40px; top:-27px;">
          <a href="../login" class="MenuLink">
            <font style="font-family:'Nanum Pen Script', cursive; font-size:0.6cm;">패션은 누구처럼?? </font>
            <font style="font-family:'Nanum Pen Script', cursive; font-size:0.8cm;">한결처럼~</font>
          </a>
        </p>
        <p style="position:absolute; left:7.5cm; top:-24px;">ㅣㅣ</p>
        <p style="position:absolute; right:12cm; top:-24px; cursor:pointer; display:block;" onclick="document.getElementById('id01').style.display='block'" id="loginVar"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.8cm;">Login</font></p>
        <form role="form" method="post" id="loginStatus">
        <p style="position:absolute; right:12cm; top:-24px; cursor:pointer; display:none;" id="logOutVar" class="logOutVar"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.8cm;">LogOut</font></p>
        </form>
<!--         <p style="position:absolute; right:8cm; top:-24px; cursor:pointer;" onclick="openRegist()"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.8cm;">회원가입</font></p> -->
        <p style="position:absolute; right:15cm; top:-24px;">ㅣㅣ</p>
      </div>
    </header>
    <!--카테고리 헤더-->
    <!--메인 페이지 테두리-->
      <div class="topRight">
      </div>
      <div class="topLeft">
      </div>
      <div class="RightVar">
      </div>
      <div class="LeftVar">
      </div>
    <!--메인 페이지 테두리-->


 <!--메뉴버튼-->
    <div class="MenuBtnSide" id="rightMenu">
      <font style="font-family:'Nanum Pen Script', cursive; font-size:1cm; color:white; cursor:pointer;" onclick="closeRightMenu()">Menu 닫기</font></br></br>
      <a href="../memberList" class="MenuLink"><font style="font-family:'Nanum Pen Script', cursive; font-size:1cm; color:white;">회원관리</font></a></br>
      <a href="../item/itemManagement_list" class="MenuLink"><font style="font-family:'Nanum Pen Script', cursive; font-size:1cm; color:white;">상품관리</font></a></br>
      <a href="../showAllDelivery" class="MenuLink"><font style="font-family:'Nanum Pen Script', cursive; font-size:1cm; color:white;">주문관리</font></a></br>
      <a href="../BoardMain" class="MenuLink"><font style="font-family:'Nanum Pen Script', cursive; font-size:1cm; color:white;">Q&A</font></a></br>
      <a href="../NoticeMain" class="MenuLink"><font style="font-family:'Nanum Pen Script', cursive; font-size:1cm; color:white;">NOTICE</font></a></br>
    </div>
    <div class="MenuBtn">
      <img src="/resources/image/menuBtn.png" alt="메뉴버튼" onclick="openRightMenu()" style="cursor:pointer;"><font style="font-family:'Nanum Pen Script', cursive; font-size:1cm; cursor:pointer;" onclick="openRightMenu()"> Menu</font></button>
    </div>
    <!--메뉴버튼-->
    
     <!--리모컨-->
    <div class="ScrollTopBtn">
      <a href="#"><img src="/resources/image/button.jpg" alt=""></a>
    </div>
    <div class="ScrollHomeBtn">
      <img src="/resources/image/home.jpg" alt="" width="8%">
    </div>
    <div class="ScrollSerchBtn">
      <img src="/resources/image/serch.jpg" alt="" width="8%">
    </div>
    <div class="ScrollDownBtn">
      <a href="#"><img src="/resources/image/button2.jpg" alt=""></a>
    </div>
    <!--리모컨-->
  <!--로그인-->
       
      <div id="id01" class="w3-modal">
        <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="position:absolate;  top:3cm;  width:500px; height:300px;"> <!--모델 창-->
          <div class="w3-center"><br>
             <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="닫기">&times;</span> <!--오른쪽 상단 취소버튼-->

             <form class="w3-container" action="login" id="loginTry" method="post">
              <div class="w3-section">
                  <label for="mb_id"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:0.25cm;">아이디</p></font></label>
                  <input type="text" placeholder="아이디" name="mb_id" id="mb_id_login" required>

                  <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:2cm;">비밀번호</p></font></label>
                  <input type="password" placeholder="비밀번호" name="mb_pw" id="mb_pw_login" required>
              <button type="submit" id="loginbtnTest" style="position:absolute; top:5cm; width:90%; height:10%; left:0.5cm; color:white; background-color:black; border:0; outline:0; cursor:pointer;">로그인</button>
                  <input type="checkbox" checked="checked" id="mb_idSave"> <font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:1.2cm; top:5.1cm;">아이디 저장</p></font>
              </div>
             </form>

              <div class="loginModalBottom">
                <span style="position:absolute; left:9.2cm; bottom:0.25cm;"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.5cm;">아이디 / </font></span>
                <span style="position:absolute; left:10.5cm; bottom:0.25cm;"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.5cm;">비밀번호 찾기</font></span>
              </div>
          </div>
         </div>
       </div>
    <!--로그인-->
<!--      회원가입 -->
<!--       <div id="id02" class="w3-modal"> -->
<!--         <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="position:absolate;  top:0cm;  width:500px; height:98%;"> 모델 창 -->
<!--           <div class="w3-center"><br> -->
<!--              <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="닫기">&times;</span> 오른쪽 상단 취소버튼 -->


<!--              <form class="w3-container" action="insertMember" method="post"> -->
<!--               <div class="w3-section"> -->
<!--                   <label for="mb_id"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:0.8cm;">userid</p></font></label> -->
<!--                   <input type="text" placeholder="아이디" name="mb_id" id="mb_id_regist" required> -->

<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:2.3cm;">password</p></font></label> -->
<!--                   <input type="password" placeholder="비밀번호" name="mb_pw" id="mb_pw_regist" required> -->

<!-- <!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:3.8cm;">password reconfirm</p></font></label> --> -->
<!-- <!--                   <input type="password" placeholder="비밀번호 재확인" name="mb_pw_reconfirm" id="mb_pw_reconfirm" required> --> -->

<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:5.3cm;">nickname</p></font></label> -->
<!--                   <input type="text" placeholder="닉네임" name="mb_nickname" id="mb_nickname" required> -->

<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:6.8cm;">email</p></font></label> -->
<!--                   <input type="text" placeholder="이메일" name="mb_email" id="mb_email" required> -->

<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:8.3cm;">address</p></font></label> -->
<!--                   <input type="text" placeholder="우편번호" name="mb_zipcode" id="mb_zipcode" required> -->
<!--                   <input type="text" placeholder="배송지 상세주소" name="mb_address" id="mb_address" required> -->


<!--               <button type="submit" style="position:absolute; bottom:0.5cm; width:90%; height:5%; left:0.5cm; color:white; background-color:black; border:0; outline:0; cursor:pointer;">회원가입</button> -->
<!--               </div> -->
<!--               <div class="basicInfo"> -->
<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0cm; top:-0.5cm;">username</p></font></label> -->
<!--                   <input type="text" placeholder="이름" name="mb_name" id="mb_name" required> -->

<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0cm; top:1cm;">성별</p></font></label> -->
<!--                   <input type="radio" name="mb_sex" id="mb_sex_man" value="man" required><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.4cm; top:1.7cm;">남자</p></font> -->
<!--                   <input type="radio" name="mb_sex" id="mb_sex_woman" value="woman"><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:2.2cm; top:1.7cm;">여자</p></font> -->

<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0cm; top:2.5cm;">birthday</p></font></label> -->
<!--                   <input type="text" placeholder="생년월일(ex : 19940216)" name="mb_bday" id="mb_bday" required> -->

<!--                   <label><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0cm; top:4cm;">mobile</p></font></label> -->
<!--                   <input type="text" placeholder="연락처(ex : 010-5366-2824)" name="mb_mobile" id="mb_mobile" required> -->
                  
<!-- 				  <input type="hidden" name="mb_grade" id="mb_grade" value="1"> -->
<!--               </div> -->
<!--              </form> -->

<!--               <div class="registModalTop"> -->
<!--                 <font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm;"><p style="position:absolute; left:0.5cm; top:0cm;">회원정보</font> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->
<!--         </div> -->
<!--     </div> -->
<!--    <!--회원가입--> -->
   
 <!--기업정보 -->
<!--  <div class="Bottoms"> -->
<!--     <div class="BottomVar"> -->
<!--     </div> -->
<!--     <p><font style="font-family:'Nanum Pen Script', cursive; font-size:2cm; position:absolute; left:19cm; top:19cm;">Han gyoul</font></p> -->
<!--     <p><font style="font-family:'Nanum Pen Script', cursive; font-size:0.7cm; position:absolute; left:18.3cm; top:19cm;">you want passion in grade of top </font></p> -->
<!--     <div class="BottomInfo"> -->
<!--       <table style="border-spacing:4cm 5px;"> -->
<!--         <tr style="text-align:left;"> -->
<!--           <th>GUIDE</th> -->
<!--           <th>CUSTOMER SERVICE</th> -->
<!--           <th>BANK INFO</th> -->
<!--           <th>DELIVERY</th> -->
<!--         </tr> -->
<!--         <font style="color : #e4e4e4"> -->
<!--         <tr> -->
<!--           <td>회사소개</td> -->
<!--           <td>공지사항</td> -->
<!--           <td>예금주 : 한결처럼(주)</td> -->
<!--           <td>반품주소 : 인천시 산곡동 푸르지오</td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td>개인정보처리방침</td> -->
<!--           <td>이벤트</td> -->
<!--           <td>국민은행 0000-0000000-0000</td> -->
<!--           <td>반품요청 : 우체국 택배 (1588-1300)</td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td>이용약관</td> -->
<!--           <td>포토리뷰</td> -->
<!--           <td>기업은행 000-00000-000000</td> -->
<!--           <td>배송조회</td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td>인재채용</td> -->
<!--           <td>배송조회</td> -->
<!--           <td>농협 0000-0000-00-000000</td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td>도매/제휴 문의</td> -->
<!--           <td>출석체크</td> -->
<!--           <td>우리은행 0000-00000-000000</td> -->
<!--         </tr> -->
<!--         <tr> -->
<!--           <td></td> -->
<!--           <td></td> -->
<!--           <td>신한은행 0000-000-00-000000</td> -->
<!--         </tr> -->
<!--         </font> -->
<!--       </table> -->
<!--     </div> -->
<!--     <div class="Bottom1"> -->
<!--     </div> -->
<!--     <div class="BottomInfomation"> -->
<!--       <font style="color:white"><p style="position:absolute; left:7.7cm;">㉿ 이 콘텐츠는 콘텐츠 산업 진흥법에 따라 최초 제작일로부터 5년간 보호됩니다.</p></font> -->
<!--       <table class="InfoTable" style="position:absolute; left:7.7cm; top:1.3cm; border-spacing:20px 0px;"> -->
<!--           <tr> -->
<!--             <td>회사명 : 한결처럼(주)</td> -->
<!--             <td>대표이사 : 최아름</td> -->
<!--             <td>사업자등록번호 : 000-00-00000</td> -->
<!--             <td>통신판매업신고 : 0000-부평-0000</td> -->
<!--           </tr> -->
<!--           <tr> -->
<!--             <td>주소 : (우)02-511 인천시 부평구 경인교대로 000(작전동, 건물 5층)</td> -->
<!--             <td>전화 : 1588-1588</td> -->
<!--             <td>팩스 : 032-1588-1588</td> -->
<!--           </tr> -->
<!--            <tr> -->
<!--              <td>개인정보관리책임자 : 형아형님</td> -->
<!--              <td>이메일 : 1급 기밀</td> -->
<!--              <td>제휴문의 : 선미찡에게</td> -->
<!--            </tr> -->
<!--            <tr> -->
<!--              <td colspan="4">고객님은 안전한 거래를 위해 현금등으로 결제시 저희 쇼핑몰에서 가입한 LG유플러스 구매안전서비스를 이용하실 수 있습니다.</td> -->
<!--            </tr> -->
<!--            <tr> -->
<!--              <td colspan="4">COPYRIGHT (c) 2017 BY MUTNAM.COM ALL RIGHTS RESERVED. design by 석인이형</td> -->
<!--            </tr> -->
<!--       </table> -->
<!--     </div> -->
<!--     </div> -->
<!--     기업 정보 -->
</body>
<script src="resources/js/jquery-1.12.4.min.js"></script>
<script src="resources/js/bagicLoginState.js"></script>
<script type="text/javascript">  
 
</script>
</html>