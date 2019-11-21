<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티 글 작성</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
/* 팝업 js */
	$(document).ready(function (){
		$('.btn-example').click(function(){
	        var $href = $(this).attr('href');
	        layer_popup($href);
	    });
	    function layer_popup(el){

	        var $el = $(el);		//레이어의 id를 $el 변수에 저장
	        var isDim = $el.prev().hasClass('dimBg');	//dimmed 레이어를 감지하기 위한 boolean 변수

	        isDim ? $('.dim-layer').fadeIn() : $el.fadeIn();

	        var $elWidth = ~~($el.outerWidth()),
	            $elHeight = ~~($el.outerHeight()),
	            docWidth = $(document).width(),
	            docHeight = $(document).height();

	        // 화면의 중앙에 레이어를 띄운다.
	        if ($elHeight < docHeight || $elWidth < docWidth) {
	            $el.css({
	                marginTop: -$elHeight /2,
	                marginLeft: -$elWidth/2
	            })
	        } else {
	            $el.css({top: 0, left: 0});
	        }

	        $el.find('a.btn-layerClose').click(function(){
	            isDim ? $('.dim-layer').fadeOut() : $el.fadeOut(); // 닫기 버튼을 클릭하면 레이어가 닫힌다.
	            return false;
	        });

	        $('.layer .dimBg').click(function(){
	            $('.dim-layer').fadeOut();
	            return false;
	        });

	    }
		
	    /* 팝업 안 내용 */
	    var arr_title = new Array();
	    var arr_no = new Array();
	    var arr_register = new Array();
	    <c:forEach var="myPlan" items="${myPlan}" varStatus="status">
			arr_title[${status.index}] = "${myPlan.title}";
			arr_no[${status.index}] = "${myPlan.plan_no}";
			arr_register[${status.index}] = "${myPlan.register_date}";
		</c:forEach>
		
		for(var i in arr_title){
			
			var tr = document.createElement('tr');
			$(tr).prop('class', 'content_' + i);
			var td_no = document.createElement('td');
			var td_title = document.createElement('td');
			var td_register = document.createElement('td');
			
			var c_no = document.createTextNode(arr_no[i]);
			var c_title = document.createTextNode(arr_title[i]);
			var c_register = document.createTextNode(arr_register[i]);
			var a = document.createElement('a');
			$(a).prop("href", "javascript:load_plan(" + arr_no[i] + ")");
			

			td_no.appendChild(c_no);
			td_title.appendChild(c_title);
			td_register.appendChild(c_register);
			$(tr).append(td_no);
			$(a).append(td_title);
			$(tr).append(a);
			$(tr).append(td_register);
			
			$('.table_content').append(tr);
			
		}
			
		var div = document.createElement('div');
		$(div).prop('class', 'btn-r');
		$('.pop-conts').append(div);
		
		var href = document.createElement('a');
		$(href).prop('href', '#');
		$(href).prop('class', 'btn-layerClose');
		var close = document.createTextNode('Close');
		href.appendChild(close);
		$('.btn-r').append(href);
	});


	$(document).on('click', '#btnSave', function(){
		var length = $('.content_div').length;
		console.log(length);
		var frmSave = document.form;
		frmSave.action="${contextPath}/community/plan_save";
		frmSave.length.value=length;		
		frmSave.submit();
	});
	
	function load_plan(plan_no){
		console.log(plan_no);
		var plan_content = new Array();
		var plan_day = new Array();
		var title = null;
		var p_no = null;
		var plan_date = null;
		$.ajax({
			type : "post",
			async : false,
			url : "${contextPath}/community/load_plan",
			data : {
				plan_no:plan_no
			},
			dataType : 'json',
			success : function(data, textStatus){
				$('.dim-layer').fadeOut();
				$('.planner_detail').empty();
				
				for(var i in data){
					plan_content[i] = data[i].content_id;
					plan_day[i] = data[i].day_no;
					title = data[i].title;
					p_no = data[i].plan_no;
					plan_date = data[i].range_date;
					
					$('#title').prop('value', title);
					$('#p_no').prop('value', p_no);
					
				}
				var date = document.createElement('h2');
				$(date).prop('class', 'date');
				var date_text = document.createTextNode(plan_date);
				date.appendChild(date_text);
				$('.planner_detail').append(date);
				
			},
			error : function(data, textStatus) {
		         alert("잘못된 접근입니다.")
		    }
			
		});

		for(var i in plan_content){
			var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
			var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + plan_content[i] + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
			
			$.ajax({
			      async : false,
			      url : reqUrl,
			      dataType : 'json',
			      success : function(data, textStatus) {
			         var resultArray = data.response.body.items.item;
			         
			         var div = document.createElement('div');
			         $(div).prop('class', 'content_div');
			         $(div).prop('id', 'content_div' + i);
			         
			         var box = document.createElement('div');
			         $(box).prop('id', 'box' + i);
			         $(box).prop('class', 'box');

			         var title = document.createElement('strong');
			         $(title).prop('class', 'font-weight-light');
			         var title_text = document.createTextNode(resultArray.title);
			         title.appendChild(title_text);
			         
			         var img= document.createElement('img');
			         $(img).prop('class', 'content_image');
			         $(img).prop('src', resultArray.firstimage);
			         
			         var text = document.createElement('textarea');
			         $(text).prop('class', 'form-control');
			         $(text).prop('rows', '10');
			         $(text).prop('name', 'content' + i);
			         $(text).prop('id', 'content' + i);
			         $(text).prop('placeholder', plan_day[i] + '의 내용을 입력해 주세요');
			         
			         var hidden = document.createElement('input');
			         $(hidden).prop('type', 'hidden');
			         $(hidden).prop('name', 'content_id' + i);
			         $(hidden).prop('value', plan_content[i]);
			         			         

			         $('.planner_detail').append(div);
			         $('#content_div' + i).append(box);
			         $('#box' + i).append(title);
			         $('#content_div' + i).append(img);
			         
			         $('#content_div' + i).append(text);
			         $('#content_div' + i).append(hidden);
			         
			      },
			      error : function(data, textStatus) {
			         alert("잘못된 접근입니다.")
			      }
			   });
		}
	};
</script>
<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}

@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}

h3{
	font-family: "NanumSquareRoundEB";
}

*{
	font-family: "NanumSquareRoundR";
}

textarea.form-control{
	width : 450px;
	float : right;
	display : inline-block;
}

img.content_image{
	width : 400px;
	height : 250px;	
}

div.content_div{
	padding-right : 70px;
	padding-left : 70px;
	padding-top : 30px;
	background-color : #F8F8FA;
}

strong{
	display : block;
}

table.table_content{
	align : center;
	width : 100%;
	margin-top : 10px;
}

img.file{
	width : 350px;
	height : 350px;
	display: block; 
	margin: 0px auto;
	
}

a.btn-example{
	display : block;
	text-align : center;
	color : black;
}

button.btn btn-sm btn-primary{
	float : right;
}

div.box{
	width : 400px;
	text-align : center;
}

/*팝업 css*/
.pop-layer .pop-container {
  padding: 20px 25px;
}

.pop-layer p.ctxt {
  color: #666;
  line-height: 25px;
}

.pop-layer .btn-r {
  width: 100%;
  margin: 10px 0 20px;
  padding-top: 10px;
  border-top: 1px solid #DDD;
  text-align: right;
}

.pop-layer {
  display: none;
  position: absolute;
  top: 50%;
  left: 50%;
  width: 600px;
  height: auto;
  background-color: #fff;
  border: 5px solid #3571B5;
  z-index: 10;
  text-align: center;
}

.dim-layer {
  display: none;
  position: fixed;
  _position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 999999;
}

.dim-layer .dimBg {
  position: relative;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #000;
  opacity: .5;
  filter: alpha(opacity=50);
}

.dim-layer .pop-layer {
  display: block;
}

a.btn-layerClose {
  display: inline-block;
  height: 25px;
  padding: 0 14px 0;
  border: 1px solid #304a8a;
  background-color: #3f5a9d;
  font-size: 13px;
  color: #fff;
  line-height: 25px;
}

a.btn-layerClose:hover {
  border: 1px solid #091940;
  background-color: #1f326a;
  color: #fff;
}

</style>

</head>
<body>
	<div class="container">
		<form name="form" id="form" method="post" action="${contextPath}/community/plan_save">
		<h1>글쓰기</h1>
			<div class="mb-3">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
			</div>
			<div class="mb-3">
				<label for="content">내용</label>
				<div class="planner_detail">
				<img class="file" src="${contextPath}/resources/image/community/file.png">
				<a href="#layer2" class="btn-example">플래너 불러오기</a>
				</div>	
			</div>
			
			<input type="hidden" name="length" value="">
			<input type="hidden" name="p_no" id="p_no" value="">
		</form>
		<p align="right">
			<button type="button" class="btn btn-default" id="btnSave">저장</button>
		</p>
	</div>
				
		<!-- 딤처리 팝업 -->
		
		
		<div class="dim-layer">
		    <div class="dimBg"></div>
		    <div id="layer2" class="pop-layer">
		        <div class="pop-container">
		            <div class="pop-conts">
		            	<strong> <내가 작성한 플래너 목록> </strong>
		            	<table class="table_content">
			            	<tr>
			            	<th>글번호</th>
			            	<th>제목</th>
			            	<th>등록일</th>
		            	</tr>
		            
		            	</table>
		                <!--content //-->

		                <!--// content-->
		            </div>
		        </div>
		    </div>
		</div>
	

</body>
</html>