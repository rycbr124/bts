<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript"
	src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
IMP.init('imp17305274');

IMP.request_pay({
	 pg : 'inicis', // version 1.1.0부터 지원.
	    pay_method : 'card',
	    merchant_uid : 'merchant_' + new Date().getTime(),
	    name : '주문명:결제테스트',
	    amount : 300,
	    buyer_tel : '010-1234-5678',
	    m_redirect_url : '${contextPath}/resve/Info'
},function(rsp){
	if ( rsp.success ) {
       var msg = '결제가 완료되었습니다.';
       msg += '고유ID : ' + rsp.imp_uid;
       msg += '상점 거래ID : ' + rsp.merchant_uid;
       msg += '결제 금액 : ' + rsp.paid_amount;
       msg += '카드 승인번호 : ' + rsp.apply_num;
   } else {
       var msg = '결제에 실패하였습니다.';
       msg += '에러내용 : ' + rsp.error_msg;
   }
   alert(msg);
});
</script>
</body>
</html>