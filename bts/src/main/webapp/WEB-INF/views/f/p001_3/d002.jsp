<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<style>
	@font-face{
		src:url("/bts/resources/fonts/Nanum/NANUMBARUNGOTHICBOLD.TTF");
		font-family:"nanumB";	
	}

	@font-face{
		src:url("/bts/resources/fonts/Binggrae-Bold.ttf");
		font-family:"nanum";	
	}

#title *{
	text-align:center;
}

#title>div{
	border-bottom:2px solid #9c9c9c;
}

#review-header{
	width: 100%;
    height: 400px;
}

#thumbnail-image{
	position:relative;
	display:inline-block;
    width: 100%;
    height: 400px;
    left: 50%;
    transform: translate(-50%,0);
    object-fit : cover;
}

#header-info{
	position: relative;
    width: 100%;
    height: 200px;
    background-color: rgba(130,130,130,0);
    background-image: linear-gradient(rgba(100%,100%,100%,0), rgba(100,100,100));
    color: #fff;
    bottom: 200px;
    padding: 20px;
}

#header-info *{

}

#title{
    width: auto;
    margin: 0;
    font-family:nanumB;
    font-size:3em
}

#profile-image{
    display: inline-block;
	width:32px;
	height:32px;
	border-radius:50%;
	object-fit : cover;
	margin-right:10px;
}

#member-id{
	display: inline-block;
    font-size: 20px;
    margin: auto 0;
}

#register-date{
	margin:0;
	width: 200px;
}

#review-modify{
    display: block;
    width: 100px;
    height: 40px;
    font-size: 15px;
    margin-top:10px;
    cursor: pointer;
}

#review-detail{
	background-color:#F8F8FA;
	padding : 50px;
	/*
	padding:50px 50px 10px 50px;
	*/
}

#contents img{
	max-width:80%;
	max-height : 500px;
}

#contents{
	overflow:hidden;
	height:auto;
}

#tags{
	margin : 10px 0;
	padding : 15px 10px;
	border-top:1px solid #cfcfcf;
}

/*tag*/
.tag {
  background: #e3e3e3;
  border-radius: 3px 0 0 3px;
  color: #999;
  display: inline-block;
  height: 26px;
  line-height: 26px;
  padding: 0 20px 0 23px;
  position: relative;
  margin: 0;
  margin-right: 5px;
  text-decoration: none;
  -webkit-transition: color 0.2s;
}

.tag::before {
  background: #fff;
  border-radius: 10px;
  box-shadow: inset 0 1px rgba(0, 0, 0, 0.25);
  content: '';
  height: 6px;
  left: 10px;
  position: absolute;
  width: 6px;
  top: 10px;
}

.tag::after {
  background: #F8F8FA;
  border-bottom: 13px solid transparent;
  border-left: 10px solid #e3e3e3;
  border-top: 13px solid transparent;
  content: '';
  position: absolute;
  right: 0;
  top: 0;
}

.tag:hover {
  background-color: #50bcdf;
  color: white;
}

.tag:hover::after {
   border-left-color: #50bcdf; 
}

#contents-info{
	display:flex;
	justify-content : space-between;
	margin-bottom : 10px;
}

.fa-comment-dots{
	color:rgba(170,170,170);
	margin-right:5px;
}

#comments{
	margin-top:10px;
	background-color:#f7f7f7;	
}

#comment-form{
	width:50%;
	margin: auto 0;
}

#comment-form textarea{
 	background-color: #fcfcfc;
    border: none;
    border-radius: 4px;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .30);
    color: #555f77;
    font-size: 14px;
    padding: 5px 10px;
    outline: none;
    width: 100%;
    resize:none;
    margin-bottom:10px;
}

.comment{
	margin-bottom: 20px;
	position: relative;
	z-index: 0;
}

img.comment-image{
	border: 3px solid #fff;
    border-radius: 50%;
    box-shadow: 0 1px 2px rgba(0, 0, 0, .5);
    height: 80px;
    left: 0;
    overflow: hidden;
	position: absolute;
	top: 0;
	width: 80px;
	background-color:grey;
}

.comment-box{
	background-color: #fcfcfc;
	border-radius: 4px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .15);
    margin-left: 100px;
    min-height: 60px;
    position: relative;
    padding: 15px;
}

.comment .comment-box::before{
	border-color: transparent rgba(0, 0, 0, .1);
	top: 22px;
}

.comment-box::before,.comment-box::after{
    border-width: 10px 10px 10px 0;
    border-style: solid;
    border-color: transparent #FCFCFC;
    content: "";
    left: -10px;
    position: absolute;
    top: 20px;
}

.comment-text{
	color:#555f77;
	font-size: 15px;
	margin-bottom: 25px;
}

.comment-footer{
	color:#acb4c2;
	font-size: 13px;
}

.comment-date{
	margin-left:10px;
}

.comment-delete{
	border-right:1px dotted #acb4c2;
}

.comment-delete, .comment-report{
	padding:0 5px;
	float:right;
	color:#555f77;
}

.comment-delete:hover, .comment-report:hover{
	text-decoration:underline;
	cursor: pointer;
}

</style>
<script>
	$(document).ready(function(){
		init();
		
		$(document).on('click','span.comment-report',function(){
		});
		
		$(document).on('click','span.comment-delete',function(){
			var con_test = confirm("삭제하시겠습니까?");
			if(con_test){
			   var input = $(this).parent().parent().data('no');
				$.ajax({
					type : "post", 
					async : false,
					url : "${contextPath}/community/review/comment/delete",
				    data: {answer_no:input},
				    dataType:'text',
					success : function (data,textStatus){
						if(data=='true'){
							var paging=$('li.active').text();
							comPaging(paging);
						}else{
							alert("잠시 후 다시 시도해주세요.");							
						}
					},//end success
					error : function (data,textStatus){
						alert("잠시 후 다시 시도해주세요.");
					}				
				}); //end ajax	
			}
		});
		
		$(document).on('click','a.page-link',function(){
			var paging=$(this).text();
			comPaging(paging);
		});
		
		function init(){
			var date = '${result.register_date}';
			date = date.substr(0,date.lastIndexOf('.'));
			$('#register-date').text(date);
		}
		
		function comPaging(paging){
			var startPage=$('li.page-item:first').next().text();
			var endPage=$('li.page-item:last').prev().text();
			var articleNO=${result.article_no};
			
			if(paging==$('a.page-link:first').text()){
				paging=startPage-1;
			}else if(paging==$('a.page-link:last').text()){
				paging=endPage+1;
			}
			
			var searchData={
				curPage : paging,
				article_no : articleNO	
			}
			
			$.ajax({
				type : "post", 
				async : false,
				url : "${contextPath}/community/review/comment",
			    data: searchData,
			    dataType:'json',
				success : function (data,textStatus){
					$('#comments').empty();
					for(var i in data.comments){
						var comment = data.comments[i];
						var profile = comment.profile_image;
						
						if(profile==null){
							profile='${contextPath}/resources/image/no_img.jpg';
						}else{
							if(comment.member_type!='kakao' && comment.member_type!='naver'){
								imgSrc='${contextPath}/'+imgSrc;
							}							
						}
						
						var comDiv = makeComment(comment.answer_no,comment.answer_desc,comment.member_id,profile,comment.register_date);
						$('#comments').append(comDiv);
					}
					makePaging(data.paging.startPage,data.paging.endPage,data.paging.curPage);
				},//end success
				error : function (data,textStatus){
					alert("에러가 발생했습니다.");
				}
			}); //end ajax	
		}
		
		function makePaging(startPage,endPage,curPage){
			$('#paging-list').empty();
			
			for(var j=startPage-1;j<=endPage+1;j++){
				var li=document.createElement('li');
				var a=document.createElement('a');
				$(li).addClass('page-item');
				if(j==curPage){
					$(li).addClass('active');
				}
				$(a).addClass('page-link');
				if(j==startPage-1){
					$(a).text('Previous');					
				}else if(j==endPage+1){
					$(a).text('Next');										
				}else{
					$(a).text(j);					
				}
				li.append(a);
				
				$('#paging-list').append(li);
			}
			
		}
		
		function makeComment(answer_no,answer_desc,member_id,profile_image,register_date){
			var container = document.createElement('div');
			var imgDiv=document.createElement('img');
			var boxDiv=document.createElement('div');
			var textDiv=document.createElement('div');
			var footerDiv=document.createElement('div');
			var authorSpan=document.createElement('span');
			var dateSpan=document.createElement('span');
			var delSpan=document.createElement('span');
			var repSpan=document.createElement('span');
			
			$(container).addClass('comment');
			$(imgDiv).addClass('comment-image');
			$(boxDiv).addClass('comment-box');
			$(textDiv).addClass('comment-text');
			$(footerDiv).addClass('comment-footer');
			$(authorSpan).addClass('comment-author');
			$(dateSpan).addClass('comment-date');
			$(repSpan).addClass('comment-report');
			$(delSpan).addClass('comment-delete');
			
			container.append(imgDiv);
			container.append(boxDiv);
			boxDiv.append(textDiv);
			boxDiv.append(footerDiv);
			footerDiv.append(authorSpan);
			footerDiv.append(dateSpan);
			footerDiv.append(repSpan);
			if('${sessionScope.memberInfo.member_id}'==member_id){
				footerDiv.append(delSpan);				
			}
			$(imgDiv).prop('src',profile_image);
			$(boxDiv).data('no',answer_no);
			$(textDiv).text(answer_desc);
			$(authorSpan).text(member_id);
			var	regDate = new Date(register_date)
			$(dateSpan).text(regDate.toLocaleString('ko-KR', { dateStyle:'medium', timeStyle:'medium', hour12:false }));			
			$(repSpan).text('신고');
			$(delSpan).text('삭제');
			
			return container;
		}
	})
	
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div id="review-header">
			<c:choose>
				<c:when test="${result.thumbnail_img==null}">
					<img id="thumbnail-image" src="${contextPath}/resources/image/no_img.jpg">
				</c:when>
				<c:otherwise>
					<img id="thumbnail-image" src="${contextPath}${result.thumbnail_img}" alt="">
				</c:otherwise>
			</c:choose>
			<div id="header-info">
				<h1 id="title">${result.title}</h1>
				<img id="profile-image" src="${contextPath}/resources/image/no_img.jpg">
				<h3 id="member-id">${result.member_id}</h3>
				<p id="register-date"></p>
				<input type="button" id="review-modify" class="btn btn-outline-light" value="수정">
			</div>
		</div>

		<div id="review-detail">
			<div id="contents">${result.contents}</div>
			<div id="tags">
				<c:forEach var="tag_name" items="${result.tag_list}">
					<span class='tag'>${tag_name}</span>
				</c:forEach>
			</div>
			<div id='contents-info'>
				<span id="comment-count"><i class="far fa-comment-dots fa-2x"></i>${paging.totalCount}</span><span id="view-count"></span>
			</div>
			
			<div id="comment-form" class="mx-auto">
				<form name="frmCom" class="row justify-content-md-end"  action="${contextPath}/community/review/comment/write" method="post">
					<textarea name="input-comment"></textarea>
					<input type="submit" class="btn btn-outline-secondary btn-sm" value="작성하기">
					<input type="hidden" name="article_no" value="${result.article_no}">
				</form>
			</div>
			<div id="comments">
				<c:forEach var="com" items="${comments}" varStatus="status">
					<div class="comment">
						<c:choose>
							<c:when test="${com.profile_image==null}">
								<img class="comment-image" src="${contextPath}/resources/image/no_img.jpg">
							</c:when>
							<c:otherwise>
								<c:if test="${com.member_type=='naver' || com.member_type=='kakao'}">
									<img class="comment-image" src="${com.profile_image}">
								</c:if>
								<c:if test="${com.member_type!='naver' && com.member_type!='kakao'}">
									<img class="comment-image" src="${contextPath}${com.profile_image}">
								</c:if>
							</c:otherwise>
						</c:choose>					
						<div class="comment-box" data-no="${com.answer_no}">
							<div class="comment-text">${com.answer_desc}</div>
							<div class="comment-footer">
								<span class="comment-author">${com.member_id}</span>
								<span class="comment-date"><fmt:formatDate value="${com.register_date}" pattern="yyyy. MM. dd. HH:mm:ss"/></span>
								<span class="comment-report">신고</span>
								<c:if test="${sessionScope.memberInfo.member_id==com.member_id}">
									<span class="comment-delete">삭제</span>
								</c:if>
							</div>						
						</div>		
					</div>
				</c:forEach>
			</div>
			<div id="comment-paging">
				<ul id="paging-list" class="pagination justify-content-center pagination-sm">
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
			</div>
		</div>
	</div>
</body>
</html>