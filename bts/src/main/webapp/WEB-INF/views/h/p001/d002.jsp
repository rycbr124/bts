<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${contextPath }/resources/css/reserv/hotelInfo.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<script>
$(document).ready(function(){

	
	var arr_in_date = new Array();
	var arr_out_date = new Array();
	var arr_price = new Array();
	var arr_room_type = new Array();
	var arr_whlrs_no = new Array();
	var arr_total_price = new Array();
	var arr_room_no = new Array();
	
	<c:forEach var = "roomResult" items="${roomResult}" varStatus="status">
	arr_room_type[${status.index}] = "${roomResult.room_type}";
	arr_whlrs_no [${status.index}] = "${roomResult.whlrs_no}";
	arr_price[${status.index}] = "${roomResult.price}";
	arr_room_no[${status.index}] = "${roomResult.room_no}";
	</c:forEach>
	
	
	for(var i in arr_room_type){
		var div = document.createElement('div');
		$(div).prop('class','roomInfo');
		$(div).prop('id','roomInfo'+i);
		
		var in_date = document.createElement('input');
		var test = document.createTextNode('날짜 선택 : ');
		$(in_date).prop('type','date');
		$(in_date).prop('id','in_date'+i);
		$(in_date).prop('name','in_date'+i);
		$(in_date).attr('onchange','amount()');
		
		
		var room_no = document.createElement('input');
		$(room_no).prop('type','hidden');
		$(room_no).prop('id','room_no'+i);
		$(room_no).prop('name','room_no');
		$(room_no).prop('value',arr_room_no[i]);
		
		var out_date = document.createElement('input');
		var out_dateText = document.createTextNode('\t~\t');
		$(out_date).prop('type','date');
		$(out_date).prop('id','out_date'+i);
		$(out_date).prop('name','out_date'+i);
		$(out_date).attr('onchange','amount()');
	
		
		var roomH4 = document.createElement('h4');
		$(roomH4).prop('id','room_type'+i);
		var roomH4Text = document.createTextNode(arr_room_type[i]);
		roomH4.appendChild(roomH4Text);
		
		var whlrs_noH5 = document.createElement('h5');
		$(whlrs_noH5).prop('id','whlrs_no'+i);
		var whlrs_noText = document.createTextNode('입실 인원수: '+arr_whlrs_no[i]);
		whlrs_noH5.appendChild(whlrs_noText);
		
		
		var priceH5 = document.createElement('h5');
		$(priceH5).prop('id','price'+i);
		var priceH5Text = document.createTextNode('가격 :'+arr_price[i]);
		priceH5.appendChild(priceH5Text);
		
		var totalprice = document.createElement('input');
		var price = document.createTextNode('결제 금액 : ');
		$(totalprice).prop('type','text');
		$(totalprice).prop('id','totalprice'+i);
		$(totalprice).prop('name','amount'+i);
		$(totalprice).prop('disabled','true');
	
		var a = document.createElement('a');
		$(a).prop('href','#');
		$(a).prop('class','btn btn-success');
		$(a).prop('id','reservBtn'+i);
		
		var br = document.createElement('br');
		
		var aText = document.createTextNode('예약하기');
		a.appendChild(aText);
		
		$('#roomReserv').append(roomH4);
		$('#roomReserv').append(whlrs_noH5);
		$('#roomReserv').append(priceH5);
		$('#roomReserv').append(in_date);
		$('#roomReserv').append(out_date);
		$('#roomReserv').append(totalprice);
		$('#roomReserv').append(a);
		$('#roomReserv').append(room_no);
		$('#roomReserv').append(br);
		
		
		
		var in_date0 = document.getElementById("in_date0").value;
		
		
		
	
	$(in_date).before(test);
	$(out_date).before(out_dateText);
	$(totalprice).before(price);
	}
	reservA1 = document.getElementById('reservBtn0');
	reservA2 = document.getElementById('reservBtn1');
	reservA3 = document.getElementById('reservBtn2');
	
	$(reservA1).attr('onclick','requestpay()');
	$(reservA2).attr('onclick','requestpay1()');
	$(reservA3).attr('onclick','requestpay2()');
});

	function amount(){
		var in_date0 = document.getElementById("in_date0").value;
		var in_date1 = document.getElementById("in_date1").value;
		var in_date2 = document.getElementById("in_date2").value;
		console.log(in_date0);
		console.log(in_date1);
		console.log(in_date2);
		
		var out_date0 = document.getElementById("out_date0").value;
		var out_date1 = document.getElementById("out_date1").value;
		var out_date2 = document.getElementById("out_date2").value;
		
		
		var price0 = document.getElementById("price0").innerText;
		var splitprice0 = price0.split(':');
		console.log(splitprice0);
		
		var price1 = document.getElementById("price1").innerText;
		var splitprice1 = price1.split(':');
		console.log(splitprice1);
		var price2 = document.getElementById("price2").innerText;
		var splitprice2 = price2.split(':');
		console.log(splitprice2);
		
		var ar1 = in_date0.split('-');
		var ar2 = out_date0.split('-');
		var ar3 = in_date1.split('-');
		var ar4 = out_date1.split('-');
		var ar5 = in_date2.split('-');
		var ar6 = out_date2.split('-');
		
		var da1 = new Date(ar1[0],ar1[1],ar1[2]);
		var da2 = new Date(ar2[0],ar2[1],ar2[2]);
		var da3 = new Date(ar3[0],ar3[1],ar3[2]);
		var da4 = new Date(ar4[0],ar4[1],ar4[2]);
		var da5 = new Date(ar5[0],ar5[1],ar5[2]);
		var da6 = new Date(ar6[0],ar6[1],ar6[2]);
		var dif = da2 - da1;
		var dif2 = da4 - da3;
		var dif3 = da6 - da5;
		var cDay = 24* 60 * 60 * 1000;
		if(in_date0 && out_date0){
			document.getElementById('totalprice0').value = parseInt((dif/cDay)*splitprice0[1]);
		}
		if(in_date1 && out_date1){
			document.getElementById('totalprice1').value = parseInt((dif2/cDay)*splitprice1[1]);
		}
		if(in_date2 && out_date2){
			document.getElementById('totalprice2').value = parseInt((dif3/cDay)*splitprice2[1]);
		}
	}

</script>

<meta charset="UTF-8">
<title>숙박 상세 보기</title>
</head>
<body>
	<script>
IMP.init('imp17305274');
function requestpay(){
	var amountprice = document.getElementById('totalprice0').value.toString();
	var amountprice1 = document.getElementById('totalprice1').value.toString();
	var amountprice2 = document.getElementById('totalprice2').value.toString();
	var lodging_name = "${hotelResult.name}";
	console.log(amountprice);
	console.log(amountprice1);
	console.log(amountprice2);
IMP.request_pay({
	 pg : 'inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : lodging_name,
	    amount :10,
	    buyer_tel : '010-1234-5678',
	    m_redirect_url : '${contextPath}/resve/Info'
},function(rsp){
	if ( rsp.success ) {

			var out_date0 = document.getElementById('out_date0').value.toString();
			var in_date0 = document.getElementById('in_date0').value.toString();
			var room_no = document.getElementById('room_no0').value;
			console.log(out_date0);
			console.log(amountprice);
			var requrl0 = "${contextPath}/reserv/resveInsert?amount0="+amountprice+"&member_id=${sessionScope.memberInfo.member_id}&room_no="+room_no+"&lodging_id=${hotelResult.lodging_id}&in_date0="+in_date0+"&out_date0="+out_date0;
		       var msg = '결제가 완료되었습니다.';
		       msg += '고유ID : ' + rsp.imp_uid;
		       msg += '상점 거래ID : ' + rsp.merchant_uid;
		       msg += '결제 금액 : ' + rsp.paid_amount;
		       msg += '카드 승인번호 : ' + rsp.apply_num;
		       location.replace(requrl0);

		
   } else {
       var msg = '결제에 실패하였습니다.';
       msg += '에러내용 : ' + rsp.error_msg;
   }
   alert(msg);
});
}

function requestpay1(){
	var amountprice = document.getElementById('totalprice0').value.toString();
	var amountprice1 = document.getElementById('totalprice1').value.toString();
	var amountprice2 = document.getElementById('totalprice2').value.toString();
	var lodging_name = "${hotelResult.name}";
	console.log(amountprice);
	console.log(amountprice1);
	console.log(amountprice2);
IMP.request_pay({
	 pg : 'inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : lodging_name,
	    amount :10,
	    buyer_tel : '010-1234-5678',
	    m_redirect_url : '${contextPath}/resve/Info'
},function(rsp){
	if ( rsp.success ) {
			   var room_no1 = document.getElementById('room_no1').value;
			   var in_date1 = document.getElementById('in_date1').value.toString();
			   var out_date1 = document.getElementById('out_date1').value.toString();
			   var requrl1 = "${contextPath}/reserv/resveInsert1?amount1="+amountprice1+"&member_id=${sessionScope.memberInfo.member_id}&room_no="+room_no1+"&lodging_id=${hotelResult.lodging_id}&in_date1="+in_date1+"&out_date1="+out_date1;
			   var msg = '결제가 완료되었습니다.';
			   msg += '고유ID : ' + rsp.imp_uid;
		       msg += '상점 거래ID : ' + rsp.merchant_uid;
		       msg += '결제 금액 : ' + rsp.paid_amount;
		       msg += '카드 승인번호 : ' + rsp.apply_num;
		       location.replace(requrl1);				
   } else {
       var msg = '결제에 실패하였습니다.';
       msg += '에러내용 : ' + rsp.error_msg;
   }
   alert(msg);
});
}

function backspace(){
	history.go(-1);
}

function requestpay2(){
	var amountprice = document.getElementById('totalprice0').value.toString();
	var amountprice1 = document.getElementById('totalprice1').value.toString();
	var amountprice2 = document.getElementById('totalprice2').value.toString();
	var lodging_name = "${hotelResult.name}";
	console.log(amountprice);
	console.log(amountprice1);
	console.log(amountprice2);
IMP.request_pay({
	 pg : 'inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : lodging_name,
	    amount :10,
	    buyer_tel : '010-1234-5678',
	    m_redirect_url : '${contextPath}/resve/Info'
},function(rsp){
	if ( rsp.success ) {		
			   var room_no2 = document.getElementById('room_no2').value;
			   var in_date2 = document.getElementById('in_date2').value.toString();
			   var out_date2 = document.getElementById('out_date2').value.toString();
			   var requrl2 = "${contextPath}/reserv/resveInsert2?amount2="+amountprice2+"&member_id=${sessionScope.memberInfo.member_id}&room_no="+room_no2+"&lodging_id=${hotelResult.lodging_id}&in_date2="+in_date2+"&out_date2="+out_date2;
			   var msg = '결제가 완료되었습니다.';
			   msg += '고유ID : ' + rsp.imp_uid;
		       msg += '상점 거래ID : ' + rsp.merchant_uid;
		       msg += '결제 금액 : ' + rsp.paid_amount;
		       msg += '카드 승인번호 : ' + rsp.apply_num;
		       location.replace(requrl2);		
   } else {
       var msg = '결제에 실패하였습니다.';
       msg += '에러내용 : ' + rsp.error_msg;
   }
   alert(msg);
});
}
</script>
	<div class="container">
		
		<div id="contents">
			<div id="hotelResult">
				<h1 id="headsubjectText">숙박 예약하기</h1>
				<br>
				<h2>${hotelResult.name}</h2>
				<hr>
				<img id="hotelImg" src="${contextPath}${hotelResult.lodging_image }"><br>
				<p>●위치: ${hotelResult.address}</p>
				<br>
				<h4>※상세정보</h4>
				<hr>
				<c:forEach var="description" items="${descriptionList}">
				<p>${description}</p><br>
				</c:forEach>
				<hr>
				<input type="hidden" value="${hotelResult.lodging_id}"
					name="lodging_id"> <input type="hidden"
					value="${sessionScope.memberInfo.member_id}" name="member_id">

			</div>
			<br>
			<div id="roomResult">
				<h3>객실 정보</h3>
				<hr>
				<div id="roomReserv"></div>
				<hr>


				<div id="roomReserv"></div>			
				<br><br>
				<button class="btn btn-success" id="backspace" onclick="backspace()">목록으로 돌아가기</button>

			</div>
		</div>
	</div>
<br><br><br><br><br>
</body>
</html>