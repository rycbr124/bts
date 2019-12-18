<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    isErrorPage="true"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<style>
* {
  margin:0px auto;
  padding: 0px;
text-align:center;
}
body {
  background-color: #D4D9ED;
}
.cont_principal {
position: absolute;  
  width: 100%;
  height: 100%;
overflow: hidden;
}
.cont_error {
position: absolute;
  width: 100%;
  height: 300px;
  top: 50%;
  margin-top:-150px;
}

.cont_error > h1  {
 font-family: 'Lato', sans-serif;  
font-weight: 400;
  font-size:70px;
color:#fff;
text-shadow: -1px 0 #a6d8ff, 0 1px #a6d8ff, 1px 0 #a6d8ff, 0 -1px #a6d8ff,4px 4px 0px #a6d8ff;
position: relative;
left:-100%;
transition: all 0.5s;
}


.cont_error > p  {
 font-family: 'Lato', sans-serif;  
font-weight: 300;
  font-size:24px;
  letter-spacing: 5px;
color:#9294AE;
position: relative;
left:100%;
transition: all 0.5s;
    transition-delay: 0.5s;
-webkit-transition: all 0.5s;
 -webkit-transition-delay: 0.5s;
}

.cont_aura_1 {
  position:absolute;
  width:300px;
  height: 120%;
top:25px;
right: -340px;
  background-color: #8A65DF;
box-shadow: 0px 0px  60px  20px  rgba(137,100,222,0.5);
-webkit-transition: all 0.5s;
  transition: all 0.5s;
}

.cont_aura_2 {
  position:absolute;
  width:100%;
  height: 300px;
right:-10%;
bottom:-301px;
 background-color: #8B65E4;
box-shadow: 0px 0px 60px 10px rgba(131, 95, 214, 0.5),0px 0px  20px  0px  rgba(0,0,0,0.1);
  z-index:5;
transition: all 0.5s;
-webkit-transition: all 0.5s;
}

.cont_error_active > .cont_error > h1 {
  left:0%;
}
.cont_error_active > .cont_error > p {
  left:0%;
}

.cont_error_active > .cont_aura_2 {
    animation-name: animation_error_2;
    animation-duration: 4s;
  animation-timing-function: linear;
    animation-iteration-count: infinite;
    animation-direction: alternate;
transform: rotate(-20deg);    
}
.cont_error_active > .cont_aura_1 {
 transform: rotate(20deg);
  right:-170px;
    animation-name: animation_error_1;
    animation-duration: 4s;
  animation-timing-function: linear;
    animation-iteration-count: infinite;
    animation-direction: alternate;
}

@-webkit-keyframes animation_error_1 {
  from {
    -webkit-transform: rotate(20deg);
  transform: rotate(20deg);
  }
  to {  -webkit-transform: rotate(25deg);
  transform: rotate(25deg);
  }
}
@-o-keyframes animation_error_1 {
  from {
    -webkit-transform: rotate(20deg);
  transform: rotate(20deg);
  }
  to {  -webkit-transform: rotate(25deg);
  transform: rotate(25deg);
  }

}
@-moz-keyframes animation_error_1 {
  from {
    -webkit-transform: rotate(20deg);
  transform: rotate(20deg);
  }
  to {  -webkit-transform: rotate(25deg);
  transform: rotate(25deg);
  }

}
@keyframes animation_error_1 {
  from {
    -webkit-transform: rotate(20deg);
  transform: rotate(20deg);
  }
  to {  -webkit-transform: rotate(25deg);
  transform: rotate(25deg);
  }
}




@-webkit-keyframes animation_error_2 {
  from { -webkit-transform: rotate(-15deg); 
  transform: rotate(-15deg);
  }
  to { -webkit-transform: rotate(-20deg);
  transform: rotate(-20deg);
  }
}
@-o-keyframes animation_error_2 {
  from { -webkit-transform: rotate(-15deg); 
  transform: rotate(-15deg);
  }
  to { -webkit-transform: rotate(-20deg);
  transform: rotate(-20deg);
  }
}
}
@-moz-keyframes animation_error_2 {
  from { -webkit-transform: rotate(-15deg); 
  transform: rotate(-15deg);
  }
  to { -webkit-transform: rotate(-20deg);
  transform: rotate(-20deg);
  }
}
@keyframes animation_error_2 {
  from { -webkit-transform: rotate(-15deg); 
  transform: rotate(-15deg);
  }
  to { -webkit-transform: rotate(-20deg);
  transform: rotate(-20deg);
  }
}

</style>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
window.onload = function(){
	document.querySelector('.cont_principal').className= "cont_principal cont_error_active";  
	  
	}
</script>
<meta charset="UTF-8">
<title>문제가 발생했습니다.</title>
</head>
<body>
<div class="cont_principal">
<div class="cont_error">
  
<h1>죄송합니다.</h1>  
  <p>사용중 문제가 발생했습니다.</p>
	<a class="btn btn-outline-primary" href="${contextPath}/main/main" role="button">메인으로 돌아가기</a>
  </div>
<div class="cont_aura_1"></div>
<div class="cont_aura_2"></div>
</div>
</body>
</html>