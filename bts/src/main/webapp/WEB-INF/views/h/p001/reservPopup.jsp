<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1> 예약 </h1>
<div>
<h4></h4>
	입실날짜 :
	<input type="date" id="in_date" name="in_date" onchange="amount()">&nbsp;&nbsp;&nbsp;
	퇴실날짜:
	<input type="date" id="out_date" name="out_date" onchange="amount()">
	총 금액 :
	<input type="text" id="totalPrice" name="amount">
	<br>
	<a href="#" class="btn btn-success"
		style="background: #666666; border: 1px solid #666666;">결제하기</a>
	<input type="hidden" id="price" value="${roomResult.price }">
</div>

	<script>
		function amount() {
			var in_date = document.getElementById("in_date").value;
			console.log(in_date);
			var out_date = document.getElementById("out_date").value;
			console.log(out_date);
			var price = document.getElementById("price").value;
			console.log(price);
			var ar1 = in_date.split('-');
			var ar2 = out_date.split('-');
			var da1 = new Date(ar1[0], ar1[1], ar1[2]);
			var da2 = new Date(ar2[0], ar2[1], ar2[2]);
			var dif = da2 - da1;
			var cDay = 24 * 60 * 60 * 1000;
			console.log(dif);
			console.log(cDay);
			console.log(amount);
			if (in_date && out_date) {

				document.getElementById('totalPrice').value = parseInt((dif / cDay)
						* price);
			}
		}
	</script>

</body>
</html>