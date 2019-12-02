<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
	font-family: "nanum";
}

@font-face {
	src: url("../fonts/BMJUA_ttf.ttf");
	font-family: "bm";
}

#cke_editor {
	margin: 0 auto;
}

#title {
	text-align: center;
	margin: 20px 0 10px 20px;
	font-size: 50px;
}

#title>input {
	padding: 0 10px;
	width: 80%;
	border: none;
	border-bottom: 2px solid #9c9c9c;
	font-size: 45px;
}

#title>input::placeholder, #tag-list>input[type=text]::placeholder {
	font-style: oblique;
	color: #a8a8a8;
	font-family: initial;
}

#tag-list {
	width: 80%;
	margin: 0 auto;
	margin-bottom: 10px;
}

#tag-list>input[type=text] {
	margin-left: 20px;
	padding: 0 10px;
	border: none;
	border-bottom: 1px solid #9c9c9c;
}

.tag-result {
	border: 1px solid;
	border-radius: 10px;
	padding: 5px;
	text-align: center;
	margin-left: 5px;
}

.tag-result span {
	margin-left: 0 3px;
}

#tag-list a {
	margin-left: 3px;
	color: red;
	cursor: pointer;
}

#end {
	margin-top: 30px;
	margin-right: 11%;
	width: 150px;
}
</style>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/e/p003/d001.css" />
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/library/ckeditor4/ckeditor.js"></script>
<script>
$(document).ready(function(){
	CKEDITOR.replace('editor',{
		width:'80%',
		height:500,
		filebrowserUploadUrl : "${contextPath}/accompany2/image"
	});
	
	CKEDITOR.on('dialogDefinition',function(ev){
		var dialogName=ev.data.name;
		var dialog = ev.data.definition.dialog;
		var dialogDefiniton = ev.data.definition;
		
		if(dialogName=="image"){
			dialog.on('show',function(obj){
				this.selectPage("Upload");
			});
			dialogDefiniton.removeContents('advanced');
			dialogDefiniton.removeContents('Link');
			
			var infoTab = dialogDefiniton.getContents('info');
			console.log(infoTab);
			infoTab.remove('txtAlt');
	        infoTab.remove( 'txtHSpace'); //info 탭 내에 불필요한 엘레멘트들 제거
	        infoTab.remove( 'txtVSpace');
	        infoTab.remove( 'txtBorder');

		}
	});
	
	$('#end').on('click',function(){
		var frm = submitAction();
		frm.action="${contextPath}/accompany3/accUpdate?article_no=${article_no}";
		frm.submit();
	})
	
	$('#endMod').on('click',function(){
		var frm = submitAction();
		frm.action="${contextPath}/community/review/upload/mod";
		frm.submit();
	})
	
	$('#tag-list>input[type=text]').on("keydown",function(event){
		if(event.keyCode==13){
			var inputText = $(this).val();
			if(inputText.trim()==''){
				alert('태그를 입력해주세요');
				return;
			};
			addTag(inputText);
			$(this).val('');
		}
	});

	//팝업 검색결과 클릭
	$(document).on("click",".tag-result>a",function(){
		$(this).parent().remove();
	});

	function submitAction(){
		var test = document.createElement('div');
		var contents = CKEDITOR.instances.editor.getData();
		test.innerHTML = contents;
		var img = $(test).find('img').toArray();
		var result = new Array();
		
		for(var i in img){
			result.push($(img[i]).attr('src'));
		}
		
		//tag
		var inputTag = $('.tag-input').toArray();
		var tagResult = new Array();
		for(var j in inputTag){
			tagResult.push($(inputTag[j]).text());
			console.log($(inputTag[j]).text());
		}
		console.log(tagResult);
		var frm = document.frmWrite;
		frm.imageList.value=JSON.stringify(result);
		frm.tagList.value=JSON.stringify(tagResult);
		return frm;
	}
	
	function isTagExist(inputText){
		var inputTag = $('.tag-input').toArray();
		for(var j in inputTag){
			if($(inputTag[j]).text()==inputText){
				return true;
			}
		}
		return false;
	}
	
	function addTag(/*String*/inputText){
		if(!isTagExist(inputText)){				
			var result=document.createElement('span');
			var sharp=document.createElement('span');
			var input=document.createElement('span');
			var a=document.createElement('a');
			
			result.append(sharp);
			result.append(input);
			result.append(a);
			
			$(result).addClass('tag-result');
			$(input).addClass('tag-input');
			$(sharp).text('#');
			$(input).text(inputText)
			$(a).text('x');
			
			$('#tag-list>span').append(result);
		}else{
			alert('이미 존재하는 태그입니다.');
		}
	}
})
</script>
<title>동행 게시글 수정</title>
</head>
<body>
	<div id="container">
		<h1 id="subject">동행 게시글 수정</h1>
		<form name="frmWrite" method="post">
			<div id="form-group">
				<label for="inputSubject" class="col-lg-2 control-label">제목</label>
				<div class="form-control">
					<input type="text" name="acc_title" placeholder="제목">
				</div>
			</div>
			<div id="forom-group">
			<label for="inputarticle_no" class="col-lg-2 control-label">게시글 번호</label>
			<div class="form-control">
			<input type="text" name="article_no" value="${article_no }" placeholder="${article_no}" disabled>
			</div>
			</div>
			<div id="form-group">
				<label for="inputId" class="col-lg-2 control-label">아이디</label>
				<div class="form-control">
					<input type="text" name="member_id" value="${sessionScope.memberInfo.member_id}" placeholder="${sessionScope.memberInfo.member_id}" disabled>
				</div>
			</div>
			<div id="form-group">
				<label for="inputAge" class="col-lg-2 control-label">희망나이</label>
				<div class="form-control">
					<input type="text" name="age" placeholder="희망나이">
				</div>
			</div>
			<div id="form-group">
				<label for="inputTraffic" class="col-lg-2 control-label">교통수단</label>
				<div class="form-control">
					<select name="traffic">
						<option value="버스">버스</option>
						<option value="택시">택시</option>
						<option value="자차">자차</option>
						<option value="도보">도보</option>
					</select>
				</div>
			</div>
			<div id="form-group">
				<label for="inputGender" class="col-lg-2 control-label">성별</label>
				<div class="form-control">
					<select name="gender" id="gender">
						<option value="남">남</option>
						<option value="여">여</option>
					</select>
				</div>
			</div>
			<div id="form-group">
				<label for="inputWhlrs_no" class="col-lg-2 control-label">인원수</label>
				<div class="form-control">
					<input type="text" name="whlrs_no" placeholder="인원수">
				</div>
			</div>
			<br> <br>
			<div id="form-group">
				<div class="form-control" id="tag-list">
					<input type="text" placeholder="태그"> 
					<span> 
					<c:forEach var="tags" items="${tagResult.tag_list }">
							<span class="tag-result"> 
							<span>#</span> 
							<span class="tag-input">${tags}</span> 
							<a>x</a>
							</span>
						</c:forEach>
					</span>
				</div>
			</div>
			<div id="form-group">
				<textarea id="editor" name="editor"></textarea>
				<input type="hidden" name="imageList"> 
				<input type="hidden" name="tagList">
			</div>
			<div class="col-lg-offset-2 col-lg-10">
				<input type="button" id="end" class="btn btn-outline-secondary" id="insertAccBtn" value="수정하기">
			</div>
		</form>
	</div>
</body>
</html>