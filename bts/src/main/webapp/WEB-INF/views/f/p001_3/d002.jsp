<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/comment/comment.css"> <!-- 커스텀 css -->
<script src="${contextPath}/resources/js/comment/comment.js"></script> <!-- 커스텀 js -->
<script src="${contextPath}/resources/js/report/report.js"></script> <!-- 커스텀 js -->
<style>
	@font-face{
		src:url("/bts/resources/fonts/Nanum/NANUMBARUNGOTHICBOLD.TTF");
		font-family:"nanumB";	
	}

	@font-face{
		src:url("/bts/resources/fonts/Binggrae-Bold.ttf");
		font-family:"nanum";	
	}
	
	@font-face{
		src:url("/bts/resources/fonts/BMJUA_ttf.ttf");
		font-family:"bmjua";	
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

#review-modify,#review-delete{
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

#refer{
	padding-bottom:10px;
	margin-bottom:5px;
}

#refer:after{
	margin-top:5px;
	content:"";
	display:block;
	width:45px;
	border-bottom:3px solid #5c5c5c;
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

</style>
<script>
	$(document).ready(function(){
		init();
		
		$('#review-modify').on('click',function(){
			var form = document.createElement('form');
			var hidden = document.createElement('input');
			$(hidden).attr('type','hidden');
			$(hidden).attr('name','article_no');			
			form.append(hidden);
			
			form.article_no.value=${result.article_no}
			form.action="${contextPath}/community/review/write/mod";
			form.method="post";
			$('body').append(form);
			form.submit();
		});

		$('#review-delete').on('click',function(){
		    var del_test = confirm("게시글을 삭제하시겠습니까?");
		    if(del_test){
				var form = document.createElement('form');
				var hidden = document.createElement('input');
				$(hidden).attr('type','hidden');
				$(hidden).attr('name','article_no');			
				form.append(hidden);
				
				form.article_no.value=${result.article_no}
				form.action="${contextPath}/community/review/contents/delete";
				form.method="post";
				$('body').append(form);
				form.submit();		    	
		    }
		});		
		
		$(document).on('click','span.comment-report',function(){
			var reqUrl="${contextPath}/report/comment"
			var contents_cd=$(this).parent().parent().data('no');
			var target_id=$(this).parent().find('.comment-author').text();
			var popup = openReport(reqUrl,contents_cd,target_id);			
		});
		
		$('#contents-report').on('click',function(){
			var reqUrl="${contextPath}/report/article/review"
			var contents_cd=${result.article_no};
			var target_id="${result.member_id}";
			var popup = openReport(reqUrl,contents_cd,target_id);
		});
		
		function init(){
			var date = '${result.register_date}';
			date = date.substr(0,date.lastIndexOf('.'));
			$('#register-date').text(date);
			
			var context="${contextPath}";
			var no=${result.article_no};
			var id="${sessionScope.memberInfo.member_id}";
			var url="${reqUrl}";
			setInit(context,no,id,url);
			var paging = ${initTotal};
			comPaging(paging);
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
				<c:choose>
					<c:when test="${result.profile_image==null}">
						<img id="profile-image" src="${contextPath}/resources/image/no_img.jpg">
					</c:when>
					<c:otherwise>
						<c:if test="${result.member_type=='naver' || result.member_type=='kakao'}">
							<img id="profile-image" src="${result.profile_image}">
						</c:if>
						<c:if test="${result.member_type!='naver' && result.member_type!='kakao'}">
							<img id="profile-image" src="${contextPath}${result.profile_image}">
						</c:if>
					</c:otherwise>
				</c:choose>						
				<h3 id="member-id">${result.member_id}</h3>
				<p id="register-date"></p>
				<c:if test="${sessionScope.memberInfo.member_id==result.member_id}">
					<input type="button" id="review-modify" class="btn btn-outline-light" value="수정">
					<input type="button" id="review-delete" class="btn btn-outline-danger" value="삭제">
				</c:if>
			</div>
		</div>
		<div id="review-detail">
			<c:if test="${result.refer_link!=null}">
				<div id="refer"><span>이 게시글은 </span><a href="${contextPath}${result.refer_link}" target="_blank">${result.refer_title}</a><span>에 대한 후기입니다.</span></div>
			</c:if>
			<div id="contents">${result.contents}</div>
			<div id="tags">
				<c:forEach var="tag_name" items="${result.tag_list}">
					<span class='tag'>${tag_name}</span>
				</c:forEach>
			</div>
			<div id='contents-info'>
				<span id="comment-count">
					<i class="far fa-comment-dots fa-2x"></i>
					<span></span>
				</span>
				<span id="view-count"></span>
				<span id="contents-report">게시글 신고</span>
			</div>
			
			<div id="comment-form" class="mx-auto">
				<form name="frmCom" class="row justify-content-md-end"  action="${contextPath}/community/review/comment/write" method="post">
					<textarea name="input-comment"></textarea>
					<input type="submit" class="btn btn-outline-secondary btn-sm" value="작성하기">
					<input type="hidden" name="article_no" value="${result.article_no}">
				</form>
			</div>
			<div id="comments">
			</div>
			<div id="comment-paging">
				<ul id="paging-list" class="pagination justify-content-center pagination-sm">
				</ul>
			</div>
		</div>
	</div>
</body>
</html>