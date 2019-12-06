<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 위시리스트</title>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>
$(document).ready(function (){
   $(".hover").mouseleave(
           function () {
             $(this).removeClass("hover");
           }
   );
   
   var arr_content = new Array();
   
   
   <c:forEach var="wish" items="${result}" varStatus="status">
      arr_content[${status.index}] = "${wish.content_id}";
   </c:forEach>
   
   for(var i in arr_content){
      var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
      var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + arr_content[i] + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
      
      $.ajax({
            async : false,
            url : reqUrl,
            dataType : 'json',
            success : function(data, textStatus) {
               var resultArray = data.response.body.items.item;
               
               var figure = document.createElement('figure');
               $(figure).prop('class', 'snip1321');
               $(figure).prop('id', 'snip' + i);
               
               var img = document.createElement('img');
               $(img).prop('src', resultArray.firstimage);
               $(img).prop('alt', 'sq-sample26');
               $(img).prop('id', 'image' + i);
               
               var figcaption = document.createElement('figcaption');
               $(figcaption).prop('class', 'fig' + i);
               
               var addr = document.createElement('h4');
               var addr_text = document.createTextNode(resultArray.addr1);
               addr.appendChild(addr_text);
               
               var title = document.createElement('h2');
               var title_text = document.createTextNode(resultArray.title);
               title.appendChild(title_text);
               
               var href = document.createElement('a');
               $(href).prop('href', '${contextPath}/recommend/place_detail?contentid=' + resultArray.contentid + "&contenttypeid=" + resultArray.contenttypeid);
               
               $('.content').append(figure);
               $('#snip' + i).append(img);
               $('#snip' + i).append(figcaption);
               $('.fig' + i).append(addr);
               $('.fig' + i).append(title);
               $('#snip' + i).append(href);               
               
            },
            error : function(data, textStatus) {
               alert("잘못된 접근입니다.")
            }
      });
   }
      
});
</script>

<style>
@font-face {
   src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}

@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}

h1{
   font-family : "NanumSquareRoundEB";
   display : inline-block;
}

h2{
   font-family : "NanumSquareRoundEB";
}

p{
   font-family: "NanumSquareRoundR";
   font-size : 16px;
}

img.heart{
   width : 45px;
   height : 45px;
   margin-right : 15px;
}

div.outContent{
   height : auto;
   background-color : #F8F8FA;
   margin : 0px;
   text-align : center;
   padding-bottom : 100px;
}

div.content{
   margin : 0 auto;
}

img{
   display : inline-block;
}

@import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,800);
figure.snip1321 {
  font-family: 'Raleway', Arial, sans-serif;
  position: relative;
  overflow: hidden;
  height : auto;
  width: 310px;
  max-height : 210px;
  margin: 10px;
  color: #000000;
  text-align: center;
  -webkit-perspective: 50em;
  perspective: 50em;
  display : inline-block;
}
figure.snip1321 * {
  -webkit-box-sizing: padding-box;
  box-sizing: padding-box;
  -webkit-transition: all 0.2s ease-out;
  transition: all 0.2s ease-out;
}
figure.snip1321 img {
   width:100%;
   height:auto;
   max-height:210px;
  vertical-align: top;
}
figure.snip1321 figcaption {
  top: 50%;
  left: 20px;
  right: 20px;
  position: absolute;
  opacity: 0;
  z-index: 1;
}
figure.snip1321 h2,
figure.snip1321 h4 {
  margin: 0;
}
figure.snip1321 h2 {
  font-weight: 600;
}
figure.snip1321 h4 {
  font-weight: 400;
  text-transform: uppercase;
}
figure.snip1321 i {
  font-size: 32px;
}
figure.snip1321:after {
  background-color: #ffffff;
  position: absolute;
  content: "";
  display: block;
  top: 20px;
  left: 20px;
  right: 20px;
  bottom: 20px;
  -webkit-transition: all 0.4s ease-in-out;
  transition: all 0.4s ease-in-out;
  -webkit-transform: rotateX(-90deg);
  transform: rotateX(-90deg);
  -webkit-transform-origin: 50% 50%;
  -ms-transform-origin: 50% 50%;
  transform-origin: 50% 50%;
  opacity: 0;
}
figure.snip1321 a {
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  position: absolute;
  z-index: 1;
}
figure.snip1321:hover figcaption,
figure.snip1321.hover figcaption {
  -webkit-transform: translateY(-50%);
  transform: translateY(-50%);
  opacity: 1;
  -webkit-transition-delay: 0.2s;
  transition-delay: 0.2s;
}
figure.snip1321:hover:after,
figure.snip1321.hover:after {
  -webkit-transform: rotateX(0);
  transform: rotateX(0);
  opacity: 0.9;
}

div.container{
   position : relative;
   align : center;
}
</style>
</head>
<body>
<div class="container">
   <div class="title">
      <img src="${contextPath}/resources/image/mypage/heart.png" class="heart">
      <h1 style="color:#000;">나의 위시리스트</h1>
   </div>   
   <div class="outContent">
      <div class="content">
      <hr>
      <h2>Wish List</h2>
      <p>내가 선택한 나만의 명소</p>
      
      </div>
   </div>
</div>

</body>
</html>