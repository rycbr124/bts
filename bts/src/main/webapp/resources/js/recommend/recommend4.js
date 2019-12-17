/**
 * 추천(지역별) - 한국관광공사 API 
 */
function result_init(){
	 var pageNo = 1;
	 image_init(pageNo);//이미지 삽입
	 //paging 뿌리는 메소드
	 $('.page-item').on('click',paging_click); //뿌린 버튼에 클릭이벤트 달아주는 메소드
}

function image_init(idNumber, command) {
$("#image_grid").empty();
	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + idNumber + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
	var map_array = new Array();
	var map_x;
	var map_y;
	
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			var resultArray = data.response.body.items.item;
			console.log(resultArray);
			console.log(resultArray.mapx);
			console.log(resultArray.mapy);
			map_x = resultArray.mapx;
			map_y = resultArray.mapy;
			map_array[0] = map_x;
			map_array[1] = map_y;
			
			
			
			var img_1 = document.createElement('img');
			$(img_1).prop('class', 'img-fluid rounded mb-4 mb-lg-0');
			$(img_1).prop('src', resultArray.firstimage);
			
			var title = document.createElement('h1');
			$(title).prop('class', 'font-weight-light');
			var title_text = document.createTextNode(resultArray.title);
			title.appendChild(title_text);
			
			var overview = document.createElement('p');
			var overview_text = document.createTextNode(resultArray.overview);
			$(overview).html(resultArray.overview);
			
			var form = document.createElement('form');
	         $(form).prop('name', 'wish');
	         $(form).prop('action', '/bts/recommend/insert_wishlist');
	         $(form).prop('method', 'post');
	         
	         var divHeart = document.createElement('div');
	         $(divHeart).prop('class', 'divHeart');
	         
	         var heartTxt = document.createElement('p');
	         $(heartTxt).html('위시리스트 추가 : ');
	         
	         var heart = document.createElement('img');
	         $(heart).prop('class', 'heart');
	         if(command == 'not'){
	        	 $(heart).prop('src', '/bts/resources/image/recommend/like (1).png');   
	         }else{
	        	 $(heart).prop('src', '/bts/resources/image/recommend/like.png')
	         }
	         $(heart).attr('onclick', 'wish_list()');
	         
	         var hidden = document.createElement('input');
	         $(hidden).prop('type', 'hidden');
	         $(hidden).prop('name', 'contentid');
	         $(hidden).prop('value', 'id');
	         
	         var hidden2 = document.createElement('input');
	         $(hidden2).prop('type', 'hidden');
	         $(hidden2).prop('name', 'contenttypeid');
	         $(hidden2).prop('value', resultArray.contenttypeid);
			
			
			$('.col-lg-7').append(img_1);

			$('.col-lg-5').append(title);
			$('.col-lg-5').append(overview);
			$('.col-lg-5').append(form);
			$(form).append(divHeart);
			$(divHeart).append(heartTxt);
	        $(divHeart).append(heart);
	        $(form).append(hidden);
	        $(form).append(hidden2);
	        
	        console.log(resultArray.contenttypeid);
			
			
		},
		error : function(data, textStatus) {
			alert("잘못된 접근입니다.")
		}
	});
	course_detail(idNumber, map_array);
}

function course_detail(idNumber, map_array) {
	$("#image_grid").empty();

	var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailInfo?ServiceKey=' + serviceKey + '&contentTypeId=25&contentId=' + idNumber + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&listYN=Y';
	
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			var result_length = data.response.body.items.item.length
			var subArray = new Array();
			for(var i = 0; i < result_length; i++){
						
				var resultArray = data.response.body.items.item[i];
				subArray[i] = resultArray.subcontentid;
				console.log("111111111 : " + subArray[i]);
				
				
				var content_div = document.createElement('div');
				$(content_div).prop('class', 'content_' + i);
				$(content_div).prop('id','content_list');
				$('content_'+i).prop('style','width:1000px; height:600px')
				
				
				var title = document.createElement('h3');
				$(title).prop('class', 'h3_title');
				var text_title = document.createTextNode(i+1 + '. ' + resultArray.subname);
				title.appendChild(text_title);
				
				var href = document.createElement('a');
				$(href).prop('href', '/bts/recommend/place_detail?contentid=' + resultArray.subcontentid);
				href.appendChild(title);

				
				
				var ul_list = document.createElement('ul');
				$(ul_list).prop('class', 'course_list_' + i);
				$(ul_list).prop('id', 'ul_list');
				
				var li_content = document.createElement('li');
				$(li_content).prop('class', 'content_text_' + i);
				$(li_content).prop('id', 'li_text');
				
				var strong = document.createElement('strong');
				var strong_text = document.createTextNode('<코스개요>');
				strong.appendChild(strong_text);
				
				var li_img = document.createElement('li');
				$(li_img).prop('class', 'content_img_' + i);
				$(li_img).prop('id', 'li_image');

				
				
				var overview = document.createElement('p');
				$(overview).html(resultArray.subdetailoverview);

				var img = document.createElement('img');
				$(img).prop('src', resultArray.subdetailimg);
				$(img).prop('class', 'card-img');				
				
				$('.content').append(content_div);
				$('.content_' + i).append(href);
				$('.content_' + i).append(ul_list);
				$('.course_list_' + i).append(li_img);
				$('.course_list_' + i).append(li_content);
				$('.content_text_' + i).append(strong);
				$('.content_text_' + i).append(overview);
				
				
				
				$('.content_img_' + i).append(img);
				
				
			}
			course_map(subArray, map_array);
		},
		error : function(data, textStatus) {
			alert("잘못된 접근입니다.")
		}
	});
}

function course_map(subArray, map_array){
	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D';
	var array_length = subArray.length;
	var arr_result = new Array();

	for(var i = 0; i < array_length; i++){
		var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + subArray[i] + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
		var obj_result = new Object();
			$.ajax({
				async : false,
				url : reqUrl,
				dataType : 'json',
				success : function(data, textStatus) {
					var resultArray = data.response.body.items.item;
					console.log(resultArray);
					
					obj_result['title'] = resultArray.title;
					obj_result['map_x'] = resultArray.mapx;
					obj_result['map_y'] = resultArray.mapy;
					
					arr_result[i] = obj_result;
					console.log(arr_result[i]);		

					
					
				},
				error : function(data, textStatus) {
					alert("잘못된 접근입니다.")
				}
				
			});
	}
	console.log(arr_result);
	map_print(arr_result, map_array);

}

function map_print(arr_result, map_array){
	

var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
mapOption = { 
    center: new kakao.maps.LatLng(map_array[1], map_array[0]), // 지도의 중심좌표
    level: 8 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//마커를 표시할 위치와 내용을 가지고 있는 객체 배열입니다 
var arr_length = arr_result.length;
console.log(arr_length);
var positions = new Array();

for(var i = 0; i < arr_length; i++){
	console.log(arr_result[i]);
	positions[i] = { content: '<div>' + arr_result[i].title + '</div>', latlng: new kakao.maps.LatLng(arr_result[i].map_y, arr_result[i].map_x)}
	
}
console.log(positions);

for (var i = 0; i < positions.length; i ++) {
// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    map: map, // 마커를 표시할 지도
    position: positions[i].latlng // 마커의 위치
});

// 마커에 표시할 인포윈도우를 생성합니다 
var infowindow = new kakao.maps.InfoWindow({
    content: positions[i].content // 인포윈도우에 표시할 내용
});

// 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
// 이벤트 리스너로는 클로저를 만들어 등록합니다 
// for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
}

//인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
return function() {
    infowindow.open(map, marker);
};
}

//인포윈도우를 닫는 클로저를 만드는 함수입니다 
function makeOutListener(infowindow) {
return function() {
    infowindow.close();
};
}

}

