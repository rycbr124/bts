/**
 * 
 */
$(function() {
	$(document).on(
			"click",
			"#emailBtn",
			function() {

				$.ajax({
					beforeSend : function() {
						loadingBarStart();
					},
					type : "get",
					url : "<c:url value='/signup/email'/>",
					data : "userEamil=" + $("#email").val + "&random="
							+ $("#random").val(),
					success : function(data) {
						alert("사용가능한 이메일입니다. 인증번호를 입력해주세요.");
					},
					error : function(data) {
						alert("에러가 발생했습니다.");
						return false;
					}
				})
			})
	$(document).on(
			"click",
			"#emailAuthBtn",
			function() {
				$.ajax({
					beforeSend : function() {
						loadingBarStart();
					},
					type : "get",
					url : "<c:url value='/login/emailAuth.do'/>",
					data : "authCode=" + $('#emailAuth').val() + "&random="
							+ $("#random").val(),
					success : function(data) {
						if (data == "complete") {
							alert("인증이 완료되었습니다.");
						} else if (data == "false") {
							alert("인증번호를 잘못 입력하셨습니다.")
						}
					},
					complete : function() {
						loadingBarEnd();
					},
					error : function(data) {
						alert("에러가 발생했습니다.");
					}
				});
			});
});
