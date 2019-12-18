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
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/boardHeader.css" />
<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}

@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}

table{
	text-align : center;
	width:100%;
}

th{
	font-family: "NanumSquareRoundEB";
}

td{
	font-family: "NanumSquareRoundR";	
}

h2{
	font-family: "NanumSquareRoundEB";
}
p {
	font-family: "NanumSquareRoundR";	
}

#main-image{
	width : 1110px;
	height : 400px;
	margin-bottom : 50px;
}

#review-list img{
	width:100%;
	height:100%;
	object-fit : cover;
}

#review-list>.row{
	margin-top : 20px;
}



.hovereffect {
  width: 100%;
  height: 200px;
  float: left;
  overflow: hidden;
  position: relative;
  text-align: center;
  cursor: pointer;
}

.hovereffect .overlay {
  width: 100%;
  height: 100%;
  position: absolute;
  overflow: hidden;
  top: 0;
  left: 0;
  background-color: rgba(75,75,75,0.5);
  -webkit-transition: all 0.4s  cubic-bezier(0.88,-0.99, 0, 1.81);
  transition: all 0.4s  cubic-bezier(0.88,-0.99, 0, 1.81);
}

.hovereffect:hover .overlay {
  background-color: rgba(48, 152, 157, 0.4);
}

.hovereffect img {
  display: block;
  position: relative;
}

.hovereffect h2 {
  color: #fff;
  text-align: center;
  position: relative;
  font-size: 17px;
  padding: 10px;
  background: rgba(0, 0, 0, 0.6);
  -webkit-transform: translateY(45px);
  -ms-transform: translateY(45px);
  transform: translateY(45px);
  -webkit-transition: all 0.4s  cubic-bezier(0.88,-0.99, 0, 1.81);
  transition: all 0.4s  cubic-bezier(0.88,-0.99, 0, 1.81);
  
  overflow:hidden;
  white-space:nowrap;
  text-overflow:ellipsis;
}



.hovereffect:hover h2 {
  -webkit-transform: translateY(5px);
  -ms-transform: translateY(5px);
  transform: translateY(5px);
}

.hovereffect a.info {
  display: inline-block;
  text-decoration: none;
  padding: 7px 14px;
  text-transform: uppercase;
  color: #fff;
  border: 1px solid #fff;
  background-color: transparent;
  opacity: 0;
  filter: alpha(opacity=0);
  -webkit-transform: scale(0);
  -ms-transform: scale(0);
  transform: scale(0);
  -webkit-transition: all 0.4s  cubic-bezier(0.88,-0.99, 0, 1.81);
  transition: all 0.4s  cubic-bezier(0.88,-0.99, 0, 1.81);
  font-weight: normal;
  /*
  padding: 62px 100px;
  margin: -52px 0 0 0;
  */
}

.hovereffect:hover a.info {
  opacity: 1;
  filter: alpha(opacity=100);
  -webkit-transform: scale(1);
  -ms-transform: scale(1);
  transform: scale(1);
}

.hovereffect a.info:hover {
  box-shadow: 0 0 5px #fff;
}

#review-list .pagination{
	margin-top :20px;
}

#search-message{
	background-color:#eeeff0;
}

.main-board{
	background:url('${contextPath}/resources/image/community/community_main2.jpg');
	background-size : 100% 100%;
}

.main-info h1{
	font-size: 2.2em;
}
</style>
<script>
	$(document).ready(function(){
		init();
		
		$('#review-list .hovereffect').on('click',function(){
			var articleNo = this.dataset.article;
			//window.location.href="${contextPath}/community/review/contents?article="+articleNo;
			document.frmContents.article.value=articleNo;
			document.frmContents.action="${contextPath}/community/review/contents";
			document.frmContents.method="post";
			document.frmContents.submit();
		})
		
		$('#search').click(function(){
			var p_info = $('#p_info').val();
			var p_search = $('#p_search').val();
			if(p_search.trim()==''){
				alert('검색어를 입력해주세요');
				return false;
			}
			if(document.frmContents.search_name==null && document.frmContents.search_value==null){			
				var search_name=document.createElement('input');
				var search_value=document.createElement('input');
				$(search_name).attr('type','hidden');
				$(search_value).attr('type','hidden');			
				$(search_name).attr('name','search_name');
				$(search_value).attr('name','search_value');
				document.frmContents.append(search_name);
				document.frmContents.append(search_value);			
			}
			document.frmContents.search_name.value=p_info;
			document.frmContents.search_value.value=p_search;
			document.frmContents.action="${contextPath}/community/review/list";
			document.frmContents.method="post";
			document.frmContents.submit();			
		});	
		
		$('a.page-link').on('click',function(){
			var nowPage=${paging.curPage};
			var startPage=${paging.startPage};
			var endPage=${paging.endPage};
			var rangePage=${paging.rangePage};
			
			var paging=$(this).text();
			if(paging==$('a.page-link:first').text()){
				paging=startPage-rangePage;
			}else if(paging==$('a.page-link:last').text()){
				paging=endPage+1;				
			}
			document.frmContents.curPage.value=paging;
			document.frmContents.action="${contextPath}/community/review/list";
			document.frmContents.method="post";
			document.frmContents.submit();
		});
		
		$('#write_review').on('click',function(){
			window.location.href="${contextPath}/community/review/write";
		});
		
		function init(){
			if(document.frmContents.search_name!=null && document.frmContents.search_value!=null){
				$('#p_info').val(document.frmContents.search_name.value);
				$('#p_search').val(document.frmContents.search_value.value);
			}
		}
	});
</script>
</head>
<body>

<div class="container">
	<div class="main-board">
		<div class='main-info'>
			<div class='main-back mx-auto'>
				<h1>커뮤니티</h1>
				<span class="main-detail">BTS와 함께 나만의 여행후기를 공유하세요!</span>
			</div>
		</div>
	</div>
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <a class="nav-link" href="${contextPath}/community/plan_list">계획</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="${contextPath}/community/review/list">후기</a>
    </li>
  </ul>
  <div id="review-list">
		<c:if test="${search_name!=null && search_value!=null}">
	  		<div class="row justify-content-center">
	  			<div id="search-message" class="col-md-12 p-3 mb-2 text-dark rounded-lg text-center">${search_value}의 검색결과입니다.</div>
	  		</div>
		</c:if>
  		<div class="row">
			<c:forEach var="article" items="${articleList}" varStatus="status">
				<c:if test="${status.count==4}">
		</div>
		<div class="row">
				</c:if>	
				<div class="col-md-4">
		    		<div class="hovereffect" data-article="${article.article_no}">
		    			<c:choose>
		    				<c:when test="${article.thumbnail_img==null}">
		         				<img src="${contextPath}/resources/image/no_img.jpg">
		    				</c:when>
		    				<c:otherwise>
				        		<img src="${contextPath}${article.thumbnail_img}" alt="">		    						    				
		    				</c:otherwise>
		    			</c:choose>
				        <div class="overlay">
				           <h2>${article.title}</h2>
				           <a class="info">
								<c:forEach var="tag_name" items="${article.tag_list}" varStatus="status">#${tag_name}</c:forEach>				          
				           </a>
			   		    </div>
				    </div>
				</div>
			</c:forEach>
		</div>
		
		<ul class="pagination justify-content-center">
		    <li class="page-item"><a class="page-link">Previous</a></li>
		    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" varStatus="status">
				<c:choose>
					<c:when test="${(paging.startPage+status.count-1)==paging.curPage}">
					    <li class="page-item active"><a class="page-link">${paging.startPage+status.count-1}</a></li>		    
					</c:when>
					<c:otherwise>
					    <li class="page-item"><a class="page-link">${paging.startPage+status.count-1}</a></li>		    
					</c:otherwise>
				</c:choose>			    
		    </c:forEach>
		    <li class="page-item"><a class="page-link">Next</a></li>
		</ul>
		
		<div class="form-row justify-content-center">
			<div class="col-md-2">
				<select id="p_info" name="p_info" class="form-control">
					<option value="title">제목</option>
					<option value="member_id">작성자</option>
					<option value="tag_name">태그</option>
				</select> 
			</div>
			<div class="col-md-3">
				<input type="text" class="form-control" name="p_search" id="p_search" placeholder="search">
			</div>
			<input type="button" id="search" class="btn btn-sm btn-outline-success" value="검색">
		</div>
		
		<c:if test="${sessionScope.isLogOn==true}">
			<div class="row justify-content-md-end">
				<input type="button" id="write_review" class="btn btn-outline-secondary" value="글쓰기">
			</div>
		</c:if>
  </div>
	<form name="frmContents">
		<input type="hidden" name="curPage">
		<input type="hidden" name="article">
		<c:if test="${search_name!=null && search_value!=null}">
			<input type="hidden" name="search_name" value="${search_name}">
			<input type="hidden" name="search_value" value="${search_value}">			
		</c:if>
	</form>
</div>
</body>
</html>