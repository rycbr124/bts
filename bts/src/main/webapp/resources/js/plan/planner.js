/**
 * 
 */
function plan_list(planList){
	var list = planList.plan_no;
	var length = planList.plan_no.length;
	for(var i=0; i<length; i++){
		var div = document.createElement('div');
		var image = document.createElement('img');
		var a = document.createElement('a');
		var tit = document.createElement('h2');
		var rangedt = document.createElement('p');
		var registerDt = document.createElement('p');
		var member = document.createElement('p');
		var personType = document.createElement('p');
		$('.my_plan').append(div);
		$(div).prop('class','plan_info'+i);
		$(div).prop('id','plan_info');
		$('.plan_info'+i).append(a);
		$(a).append(image);
		$('.plan_info'+i).append(tit);
		$('.plan_info'+i).append(rangedt);
		$(rangedt).prop('class','rangedt');
		$('.plan_info'+i).append(registerDt);
		$(registerDt).prop('class','registerDt');
		$('.plan_info'+i).append(member);
		$(member).prop('class','memberId');
		$('.plan_info'+i).append(personType);
		$(personType).prop('class','personType');
		
	}
	
		
	var plan_arr = new Array();
	var resultArray;
	for(var key in list){
		var plan = list[key];
		var title = plan['TITLE'];
		var rangeDate = plan['RANGE_DATE'];
		var registerDate = plan['REGISTER_DATE'];
		var memberId = plan['MEMBER_ID'];
		var plan_no =plan['PLAN_NO'];
		var person_se = plan['PERSON_SE'];
		plan_arr.push(plan_no);
		
		var serviceKey = '9lYTVuZFWTTyr2CZFilfzO9woq%2Bh%2B80b5xZ4myuNqQtcxMgSl2Vz1tuOjoarEHqNuXWf2WAiOTnOBzm3zJ4Rcg%3D%3D';
		var contentid = plan['CONTENT_ID'];
		var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey='
					+ serviceKey + '&contentId='+ contentid +'&defaultYN=Y&firstImageYN=Y&overviewYN=Y&mapinfoYN=Y&MobileOS=ETC&MobileApp=AppTest';
		console.log(reqUrl);
		$.ajax({
			async:false,
			url: reqUrl,
			dataType : 'json',
			success : function(data, textStatus){
				 resultArray = data.response.body.items.item;
				$('.plan_info' + key + '>a img').attr('src',resultArray.firstimage);
				$('.plan_info' + key + '>a').attr('href','/bts/plan_detail/plan_detail?plan_no='+plan_no);
				$('.plan_info' + key + '> h2').text(title);
				$('.plan_info' + key + '> p').text(rangeDate);
				$('.plan_info' + key + '> .registerDt').text(registerDate);
				$('.plan_info' + key + '> .memberId').text(memberId);
				$('.plan_info' + key + '> .personType').text(person_se);
				
			},
			error : function(data, testStatus){
				alert("잘못된 접근입니다.");
			}
		});
	var plan_size = $('.my_plan').children().size();
	var del = document.createElement('a');
	var img = document.createElement('img');
	for(var i=0; i<plan_size; i++){
		$('.plan_info'+i).mouseenter(function(){
			$(this).append(del);
			$(del).append(img);
			var index = $(this).index()
			$(del).prop('class','delete_plan');
			$(del).attr('href','/bts/planner/delete_plan?plan_no='+ plan_arr[index]);
			$(img).attr('src','/bts/resources/image/icon/remove.png');
			$(img).prop('class','delete_icon');
		})
		.mouseleave(function(){
			$(del).remove();
		});
	}
}	
}