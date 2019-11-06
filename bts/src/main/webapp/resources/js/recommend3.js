/**
 * 추천(지역별) - 한국관광공사 API 
 */
$(document).ready(function (){


//	$("#toggle-heart").change(function(){
//		wish_list();
//		console.log("111111");
//		if($("#toggle-heart").is(":checked")){
//			wish_list();
//		}else{
//			console.log("해제");
//		}
//	});
	
		
});

function result_init(){
	 var pageNo = 1;
	 image_init(pageNo);//이미지 삽입
	 //paging 뿌리는 메소드
	 $('.page-item').on('click',paging_click); //뿌린 버튼에 클릭이벤트 달아주는 메소드
}

function image_init(idNumber) {
	$("#image_grid").empty();
	
	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + idNumber + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
	
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			var resultArray = data.response.body.items.item;
			console.log(resultArray)
			console.log(resultArray.mapx);
			console.log(resultArray.mapy);
			
			var img_1 = document.createElement('img');
			$(img_1).prop('class', 'img-fluid rounded mb-4 mb-lg-0');
			$(img_1).prop('src', resultArray.firstimage);
			
			var title = document.createElement('h1');
			$(title).prop('class', 'font-weight-light');
			var title_text = document.createTextNode(resultArray.title);
			title.appendChild(title_text);
			
			
			var addr1 = document.createElement('p');
			$(addr1).prop('class', 'addr');
			var addr1_text = document.createTextNode("주소 : " + resultArray.addr1);
			addr1.appendChild(addr1_text);
						
			var h_page = document.createElement('p');
			$(h_page).prop('class', 'h_page');
			$(h_page).html("홈페이지 : " + resultArray.homepage);
			
			var form = document.createElement('form');
			$(form).prop('name', 'wish');
			$(form).prop('action', '/bts/recommend/insert_wishlist');
			$(form).prop('method', 'post');
	
						
			var input_heart = document.createElement('input');
			$(input_heart).prop('id', 'toggle-heart');
			$(input_heart).prop('type', 'checkbox');
			$(input_heart).attr('onclick','wish_list()');
			
			var label_heart = document.createElement('label');
			$(label_heart).prop('for', 'toggle-heart');
			$(label_heart).prop('id', 'heart');
			var heart = document.createTextNode('♥');
			label_heart.appendChild(heart);
			
			var button = document.createElement('button');
			$(button).prop('type', 'button');
			$(button).prop('type', 'button');
			$(button).attr('onclick','wish_list()');
			$(button).attr('id', 'wish');
			$(button).text('위시리스트');
			
			var hidden = document.createElement('input');
			$(hidden).prop('type', 'hidden');
			$(hidden).prop('name', 'contentid');
			$(hidden).prop('value', 'id');
			
			
			
					
			var overview = document.createElement('p');
			$(overview).html(resultArray.overview);
			
			$('.col-lg-7').append(img_1);

			$('.col-lg-5').append(title);
			$('.col-lg-5').append(addr1);
			$('.col-lg-5').append(h_page);
			$('.col-lg-5').append(form);
			$(form).append(input_heart);
			$(form).append(label_heart);
			$(form).append(button);
			$(form).append(hidden);
			$('.content').append(overview);
			
			map_print(resultArray.title, resultArray.mapx, resultArray.mapy);
			
		},
		error : function(data, textStatus) {
			alert("잘못된 접근입니다.")
		}
	});
	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
		var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey=' + serviceKey + '&contentId=' + idNumber + '&imageYN=Y&MobileOS=ETC&MobileApp=AppTest';
		
		$.ajax({
			async : false,
			url : reqUrl,
			dataType : 'json',
			success : function(data, textStatus) {
				var result_length = data.response.body.items.item.length
				var div = document.createElement('div');
				$('.col-lg-7').append(div);
				$(div).prop('class','detail_image_container');
				$(div).prop('id','detail_image_container');
				for(var i = 0; i < result_length; i++){
					var resultArray = data.response.body.items.item[i];
					
					var div = document.createElement('div');
					var image = document.createElement('img');
					$(image).prop('class', 'detail_image_' + i);
					$(image).prop('id', 'detail_image');
					$(image).prop('src', resultArray.originimgurl);
									
					$('.detail_image_container').append(image);
					
					$('#detail_image').on('click',function(){
						$('.img-fluid rounded mb-4 mb-lg-0').append(this);
						
					});
			
				}
				
			},
			error : function(data, textStatus) {
				alert("잘못된 접근입니다.")
			}
		});
}

//function image_list(idNumber){
//	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
//	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey=' + serviceKey + '&contentId=' + idNumber + '&imageYN=Y&MobileOS=ETC&MobileApp=AppTest';
//	
//	$.ajax({
//		async : false,
//		url : reqUrl,
//		dataType : 'json',
//		success : function(data, textStatus) {
//			var result_length = data.response.body.items.item.length
//			var div = document.createElement('div');
//			$('.col-lg-7').append(div);
//			$(div).prop('class','detail_image_container');
//			$(div).prop('id','detail_image_container');
//			for(var i = 0; i < result_length; i++){
//				var resultArray = data.response.body.items.item[i];
//				
//				var div = document.createElement('div');
//				var image = document.createElement('img');
//				$(image).prop('class', 'detail_image_' + i);
//				$(image).prop('id', 'detail_image');
//				$(image).prop('src', resultArray.originimgurl);
//								
//				$('.detail_image_container').append(image);
//				$('.detail_image_'+i).on('click', function(){
//					$('.mb-lg-0').attr('src', resultArray);
//				});
//			}
//			
//		},
//		error : function(data, textStatus) {
//			alert("잘못된 접근입니다.")
//		}
//	});
//}

function map_print(title, mapx, mapy){
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
			center: new kakao.maps.LatLng(mapy, mapx), //지도의 중심좌표. //여기에 먼저 좌표 설정
			level: 6 //지도의 레벨(확대, 축소 정도)
	};
	
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(mapy, mapx);  //여기도 위에 좌표랑 똑같이 입력
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
		position: markerPosition
	});
	
	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
	
	var iwContent = '<div style="padding:5px;">' + title + '</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(mapy, mapx); //인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker); 
}



