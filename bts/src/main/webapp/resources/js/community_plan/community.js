/**
 * 
 */

function log_check(member,ev){
	if(member == ""){
		alert('로그인 후 이용가능합니다.');
		var modal = document.getElementById('myModal');
		$(document).off('.modal');
		
	}
	
}
function go_write(){
	var plan_no = $("#myPlanList option:selected").val();
	console.log("121212 : " + plan_no);
	location.href="/bts/community/plan_write?plan_no=" + plan_no;
}

function searchPlan(){
	var select = $('#selectBox option:selected').val();
	console.log("1111111111 : " + select);
	
	var frmSearch = document.form;
	frmSearch.action = "/bts/community/plan_list";
	frmSearch.category.value = select;
	//frmSearch.submit();
	
}