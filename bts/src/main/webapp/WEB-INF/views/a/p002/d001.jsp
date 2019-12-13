<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/admin/style.css"> 
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheetinfo.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheet.js"></script>
<script src="${contextPath}/resources/ibsheet/ibleaders.js"></script>
<script>
	var pageheightoffset = 200;
	
	$(document).ready(function(){
		LoadPage();
		$("#p_name").focus();
		
		$('#reload').on('click',function(){
			doAction('reload');
		})
		$('#insert').on('click',function(){
			doAction('insert');
		})
		$('#search').on('click',function(){
			doAction('search');
		})
		$('#save').on('click',function(){
			doAction('save');
		})
		
		$("#p_name").on("keydown",function(event){
			if(event.keyCode==13){
				doAction('search');
				$("#p_name").focus();
			}
		});
	});
	
	function LoadPage(){
		createIBSheet2(document.getElementById("ibsheet_div"), "mySheet", "100%", "400px");
		mySheet.RemoveAll();
		//아이비시트 초기화
		var initSheet = {};
		initSheet.Cfg = {
                "SearchMode": smLazyLoad,
                "AutoFitColWidth": "search|resize|init|colhidden|rowtransaction",
                "DeferredVScroll": 1
		};
		initSheet.HeaderMode = {Sort:1,ColMove:1,ColResize:1,HeaderCheck:1};
		initSheet.Cols = [
			//SaveName은 보통 VO 속성명과 일치시켜줌.
			//MinWidth를 선언하면 줄여도 그 설정한 값 밑으로 줄어들지 않음.
			//align은 데이터를 정렬하는 방법.
			//MultiLineText설정하면 shift+enter 누를 때 하나의 셀 안에 여러 값을 넣을 수 있음.
			//Wrap은 컬럼 사이즈가 정해져 있지만 데이터 길이가 더 길 때, 뒷 부분은 알아서 줄 바꿈 해줌.
			{Header:"상태",Type:"Status",SaveName:"STATUS",MinWidth:50, Align:"Center"}, //모든 그리드에 들어감
			{Header:"제재코드",Type:"Text", SaveName:"pnish_cd",Edit:0,MinWidth:80,Align:"Center"},
			{Header:"제재명",Type:"Text",SaveName:"name",MinWidth:80,KeyField:1 ,MultiLineText:1}, //필수값을 체크하고자 할 때 keyField사용			
			{Header:"제재일수",Type:"Int", Format:"#일", SaveName:"day_cnt",MinWidth:150,KeyField:1,MultiLineText:1, Wrap:1}, //KeyField는 반드시 입력하고자 하는 값을 설정하고플 때.
			{Header:"삭제",Type:"DelCheck",SaveName:"DEL_CHK",MinWidth:50}, //모든 그리드에 들어감
		];   
		IBS_InitSheet( mySheet , initSheet);

		mySheet.SetEditableColorDiff(1); // 편집불가능할 셀 표시구분
		mySheet.SetFocusAfterProcess(0);
	}
	
	function doAction(sAction) {
		switch(sAction) {
		case "search": //조회
		    var param = FormQueryStringEnc(document.frm);
			mySheet.DoSearch("${contextPath}/admin/report/pnish/search", param);
			break;
		case "reload": //초기화
			mySheet.RemoveAll();
			break;
		case "save": // 저장
			mySheet.DoSave("${contextPath}/admin/report/pnish/save");
			break;			
		case "insert": //신규행 추가
			var row = mySheet.DataInsert();
			break;	
		}
	}
	
	function mySheet_OnSaveEnd(code, msg) {
		if (msg != "") {
			alert(msg);
			doAction('search');	
		}
	}
	
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
 -->
	<div class="page_title">
		<span><a class="closeDepth" href="#">closeDepth</a></span> 
		<span class="title">불량회원관리 > <b>제재 기준 조회/변경</b></span>
	</div>
	
	<div class="main_content">
		<div class="exp_product">신고 기준 조회 및 변경이 가능합니다.</div>
		<div class="exp_product">
			<form name='frm'>
				제재명 : <input type='text' id="p_name" name="p_name" />
				<input type="text" style="display: none;" />
			</form>
		</div>
		<div class="ib_function float_right">
			<a id="reload" class="f1_btn_gray lightgray">초기화</a>
			<a id="insert" class="f1_btn_gray lightgray">추가</a>
			<a id="search" class="f1_btn_white gray">조회</a>
			<a id="save" class="f1_btn_white gray">저장</a>
		</div>

		<div class="clear hidden"></div>
		<div id="ibsheet_div">
		</div>
	</div>
</body>
</html>