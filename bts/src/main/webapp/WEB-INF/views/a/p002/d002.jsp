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
		$("#p_id").focus();
		
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
		$("#p_id").on("keydown",function(event){
			if(event.keyCode==13){
				doAction('search');
				$("#p_id").focus();
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
			{Header:"상태",Type:"Status",SaveName:"STATUS",MinWidth:50, Align:"Center"}, //모든 그리드에 들어감
			{Header:"대상ID",Type:"Text", SaveName:"member_id", Edit:1, KeyField:1},
			{Header:"시작일자",Type:"Date", SaveName:"begin_date", Edit:1, KeyField:1},
			{Header:"종료일자",Type:"Date", SaveName:"end_date", Edit:1, KeyField:1},			
			{Header:"신고번호",Type:"Int", SaveName:"report_no", Edit:0}, //KeyField는 반드시 입력하고자 하는 값을 설정하고플 때.
			{Header:"신고사유",Type:"ComboEdit", ComboText:"${pnish_combo}", SaveName:"pnish_desc", Edit:1},
			{Header:"신고확인",Type:"Button", Align:"Center", SaveName:"btn_report", Edit:1},	
			{Header:"삭제",Type:"DelCheck",SaveName:"DEL_CHK",MinWidth:50}, //모든 그리드에 들어감
			{Header:"최초대상ID",Type:"Text", SaveName:"first_id", Edit:1, Hidden:1},
			{Header:"최초시작일자",Type:"Date", SaveName:"first_begin", Edit:1, Hidden:1},
			{Header:"최초종료일자",Type:"Date", SaveName:"first_end", Edit:1, Hidden:1}
		];   
		IBS_InitSheet( mySheet , initSheet);

		mySheet.SetEditableColorDiff(1); // 편집불가능할 셀 표시구분
		mySheet.SetFocusAfterProcess(0);
		 
	}

	function doAction(sAction) {
		switch(sAction) {
		case "search": //조회
		    var param = FormQueryStringEnc(document.frm);
			mySheet.DoSearch("${contextPath}/admin/report/history/search", param);
			break;
		case "reload": //초기화
			mySheet.RemoveAll();
			break;
		case "save": // 저장
			mySheet.DoSave("${contextPath}/admin/report/history/save");
			break;			
		case "insert": //신규행 추가
			var row = mySheet.DataInsert();
			break;	
		}
	}

	function mySheet_OnChange(Row,Col, Value, OldValue, RaiseFlag) {
		var input = {
			first_id : mySheet.CellSearchValue(Row,'member_id'),
			first_begin : mySheet.CellSearchValue(Row,'begin_date'),
			first_end : mySheet.CellSearchValue(Row,'end_date')
		};
		mySheet.SetRowData(Row,input);
	} 
 
	function mySheet_OnButtonClick(Row, Col){
		console.log("[" + Row + "," + Col + "] 셀의 버튼 클릭");
		console.log(mySheet.GetRowData(Row));
		var url="${contextPath}/admin/report/list/contents?report_no="+mySheet.GetRowData(Row).report_no;
		window.open(url,"_blank");
	}	
	
	function mySheet_OnSaveEnd(code, msg) {
		if (msg != "") {
			alert(msg);
		}
	}
	
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page_title">
		<span><a class="closeDepth" href="#">closeDepth</a></span> 
		<span class="title">불량회원관리 > <b>제재 내역 조회/변경</b></span>
	</div>
	
	<div class="main_content">
		<div class="exp_product">제재 내역 조회 및 변경이 가능합니다.</div>
		<div class="exp_product">
			<form name='frm'>
				대상ID : <input type='text' id="p_id" name="p_id" />
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