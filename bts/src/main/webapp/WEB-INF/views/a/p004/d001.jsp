<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/admin/style.css"> 
<link rel="stylesheet" href="${contextPath}/resources/css/admin/admin_question.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheetinfo.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheet.js"></script>
<script src="${contextPath}/resources/ibsheet/ibleaders.js"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script> 
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script language="javascript">
	//시트 높이 계산용
	var pageheightoffset = 200;
	
	/*Sheet 기본 설정 */
	function LoadPage() {
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
			{Header:"상태",Type:"Status",SaveName:"STATUS",MinWidth:50, Align:"Center"},//모든 그리드에 들어감
			{Header:"contact_no",Type:"Html",SaveName:"contact_no",MinWidth:30, KeyField:1,Hidden:1},
			{Header:"제목",Type:"Text",SaveName:"title",MinWidth:300,Align:"Center",Edit:0},
			{Header:"카테고리",Type:"Html",SaveName:"contact_type",MinWidth:150,KeyField:1 ,MultiLineText:1,Align:"Center",Edit:0}, //필수값을 체크하고자 할 때 keyField사용			
			{Header:"ID",Type:"Html",SaveName:"member_id",MinWidth:100, KeyField:1,Edit:0,Align:"Center"},
			{Header:"문의날짜",Type:"Html",SaveName:"contact_date",MinWidth:100, KeyField:1,Edit:0,Align:"Center"},
			{Header:"처리상태",Type:"Html",SaveName:"answer_at",MinWidth:100,Edit:0,Align:"Center"},
		];   
		IBS_InitSheet( mySheet , initSheet);
		mySheet.SetEditableColorDiff(1); // 편집불가능할 셀 표시구분
		mySheet.SetFocusAfterProcess(0);
        //mySheet.ShowSubSum([{StdCol:"Release",SumCols:"price",Sort:"asc"}]);
		doAction('search');
		
	}
	/*Sheet 각종 처리*/
	function doAction(sAction) {
		switch(sAction) {
			case "search": //조회
			    var param = FormQueryStringEnc(document.frm);
				mySheet.DoSearch("${contextPath}/admin/searchContact", param);
				//mySheet.DoSearch("transaction_data2.json");
				console.log(param);
				break;
			case "reload": //초기화
				mySheet.RemoveAll();
				break;
			case "save": // 저장
				//var tempStr = mySheet.GetSaveString();
				//alert("서버로 전달되는 문자열 확인 :"+tempStr);
				mySheet.DoSave("${contextPath}/admin/saveContact");
				break;			
			case "insert": //신규행 추가
				var row = mySheet.DataInsert();
				break;
		}
	}
	//모달 
	function mySheet_OnClick(row,col){
		var OrgValue = mySheet.CellSearchValue(row, 'answer_at');
		if(mySheet.ColSaveName(col) == "title" && row >= 1){
			var contact_no = mySheet.GetCellValue(row,'contact_no');
			$.ajax({
				async: false,
				url : "/bts/admin/question_answer",
				type : 'get',
				data : {"contact_no":contact_no},
				dataType : 'json',
				success : function(data){
					var info = data.questionInfo;
					for(var i in info){
					 var data_obj = info[i];
					 var title = data_obj['title'];
					 var answer_at =data_obj['answer_at'];
					 var member_id = data_obj['member_id'];
					 var contact_date = data_obj['contact_date'];
					 var contents = data_obj['contents'];
					}
					//문의 제목
					var title_container = document.createElement('div');
					var question_title = document.createElement('div');
					//문의 회원
					var member_date_container = document.createElement('div');
					var member_container = document.createElement('div');
					//문의 날짜
					var questionDt_container = document.createElement('div');
					var question_member = document.createElement('div');
					var question_date = document.createElement('div');
					
					var content_container = document.createElement('div');
					var content_title = document.createElement('div');
					var question_content = document.createElement("div");
					
					var answer_container = document.createElement('div');
					var answer_title = document.createElement('div');
					var answerArea = document.createElement('textarea');
					
					var form = document.createElement('form');
					var hidden = document.createElement('input');
					$('.modal-body').empty();
					//타이틀 부분 tag
					$('.modal-body').append(title_container)
					$(title_container).prop('class','titleArea');
					$(title_container).text('제 목');
					$(title_container).append(question_title);
					$(question_title).prop('class','title');
					$(question_title).text(title);
					//작성자/작성일자 container
					$('.modal-body').append(member_date_container);
					$(member_date_container).prop('class','member_date_container')
					//작성자 tag
					$(member_date_container).append(member_container);
					$(member_container).prop('class','memberArea');
					$(member_container).text('작성자');
					$(member_container).append(question_member);
					$(question_member).prop('class','member');
					$(question_member).text(member_id);
					//작성일자 tag
					$(member_date_container).append(questionDt_container);
					$(questionDt_container).prop('class','questionDt');
					$(questionDt_container).text('작성일자');
					$(questionDt_container).append(question_date);
					$(question_date).prop('class','date');
					$(question_date).text(contact_date);
					//내용 tag
					$('.modal-body').append(content_container);
					$(content_container).prop('class','contentArea');
					$(content_container).append(content_title);
					$(content_title).prop('class','content_title');
					$(content_title).text('문의 내용');
					$(content_title).append(question_content);
					$(question_content).prop('class','content');
					$(question_content).text(contents);
					//문의 답변
					$('.modal-body').append(form);
					$(form).prop('name','answer');
					$(form).append(answer_container);
					$(answer_container).prop('class','answer_container');
					$(answer_container).append(answer_title);
					$(answer_title).prop('class','answer_title');
					$(answer_title).text('답변 내용');
					$(answer_container).append(answerArea);
					$(answerArea).prop('class','answerArea');
					$(answerArea).prop('name','answer_content');
					$(answerArea).prop('rows','10');
					$(answerArea).prop('style','resize:none');
					$(form).append(hidden);
					$(hidden).prop('type','hidden');
					$(hidden).prop('name','contact_no');
					$(hidden).prop('value',contact_no);
					$(hidden).prop('class','contact_no');
					//처리완료인 문의클릭시 실행
					if(OrgValue == '처리완료'){
						var answer_info = data.answerInfo;
						for(var j in answer_info){
							var answer_obj = answer_info[j];
							var answer_contents = answer_obj['contents'];
							$(answerArea).prop('disabled','true');
							$(answerArea).prop('style','resize:none');
							$(answerArea).val(answer_contents);
							$('.btn-primary').remove();
						}
					}
				},
				error : function(data, textStatus){
					alert("잘못된 접근입니다.");
				}
			});
			$('#myModal').modal('show');
		}
	}
	// 저장완료 후 처리할 작업
	// code: 0(저장성공), -1(저장실패)
	function mySheet_OnSaveEnd(code,msg){
		if(msg != ""){
			alert(msg);	
			//번호 다시 매기기
            //mySheet.ReNumberSeq();
		}	
	}	
	function answer_submit(){
		var frm_answer = document.answer;
		var contact_no = $('.contact_no').val();
		var answer_val = $('.answerArea').val();
		frm_answer.action = "/bts/admin/addAnswer";
		frm_answer.method = 'get';
		frm_answer.contact_no.value = contact_no;
		frm_answer.answer_content.value = answer_val;
		frm_answer.submit();
		
	}
</script>
</head>
<body onload="LoadPage()">
  <div class="page_title">
    <span><a class="closeDepth" href="#">closeDepth</a></span> 
    <span class="title">문의관리 > <b>문의 목록 조회</b></span>
  </div>
  <div class="main_content">
    <div class="exp_product">각 행의 데이터를 수정하거나 입력,삭제시 상태컬럼의 변화를
			확인하고,저장 버튼 클릭시 서버로 전송되는 데이터를 확인한다.</div>
    <div class="exp_product">
      <form name='frm'>
        ID: <input type='text' id="p_id" name="p_id" /> 
      </form>
    </div>
    <div class="ib_function float_right">
	  <a href="javascript:doAction('reload')" class="f1_btn_gray lightgray">초기화</a>
	  <a href="javascript:doAction('search')" class="f1_btn_white gray">조회</a>
	</div>

	<div class="clear hidden"></div>
	<div>
	<script>createIBSheet("mySheet", "100%", "400px");</script>
	</div>
  </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">문의 답변</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body">
				</div>
				<div class="modal-footer">
					<button type="button" onclick="answer_submit()" class="btn btn-primary">저장</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>