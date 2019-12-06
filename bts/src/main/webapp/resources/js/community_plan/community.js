/**
 * 
 */

function go_write(){
	var plan_no = $("#myPlanList option:selected").val();
	console.log("121212 : " + plan_no);
	location.href="/bts/community/plan_write?plan_no=" + plan_no;
	

}