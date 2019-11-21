/**
 * 
 */
function planRoot(plan_root,planner){
	var plan_detail = plan_root.plan_root;
	var plan_info = planner.planner;
	var planNo = plan_detail[0];
	var plan_no = planNo['PLAN_NO']; 
	var resultArray;
	var array = new Array();
	var arr_location = new Array();
	
	var info = document.createElement('div');
	var serviceKey = '8MlvFH5fs4groXQuW9uCj0jvncbl0Pk9sppAzxq0jolCi5lsMOdlpLHgX3wC0rTwyrMHAPkLBm7lmsY44FwxGg%3D%3D'
	for(var key in plan_detail){
		var day_location = new Object();
		var detail = plan_detail[key];
		var dayNo = detail['DAY_NO'];
		var location = new Object();
		
		
		var contentid = detail['CONTENT_ID'];
		var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey='
				   + serviceKey + '&contentId='+ contentid +'&defaultYN=Y&firstImageYN=Y&overviewYN=Y&mapinfoYN=Y&MobileOS=ETC&MobileApp=AppTest';
		
		$.ajax({
			async:false,
			url: reqUrl,
			dataType: 'json',
			success: function(data, textStatus){
				resultArray = data.response.body.items.item;
				array.push(resultArray.firstimage);
				$('.plan_detail_header > .thumbnail_image').attr('src', array[0]);
				$('.plan_detail_header').append(info);
				$(info).prop('class','plan_information');
				 var title = resultArray.title;
			     var mapx = resultArray.mapx;
				 var mapy = resultArray.mapy;
				arr_location.push(dayNo);
			},
			error : function(data, textStatus) {
				alert("잘못된 접근입니다.")
			}
			
		});
		map_marker(arr_location);
	}
	
	for(var key in plan_info){
		var plan_info = plan_info[key];
		var title = plan_info['TITLE'];
		var rangeDate = plan_info['RANGE_DATE'];
		var member_id = plan_info['MEMBER_ID'];
		var person_se = plan_info['PERSON_SE'];
		
		
	}
	var header_title = document.createElement('h1');
	var header_rangeDt = document.createElement('p');
	var header_member = document.createElement('h3');
	var profile = document.createElement('img');
	var modified = document.createElement('button');
	var personType = document.createElement('p');
	$('.plan_information').append(header_title);
	$(header_title).prop('class', 'title');
	$(header_title).text(title);
	$('.plan_information').append(header_rangeDt);
	$(header_rangeDt).prop('class', 'rangeDate');
	$(header_rangeDt).text(rangeDate);
	$('.plan_information').append(profile);
	$(profile).prop('class','profile_image');
	$(profile).attr('src','/bts/resources/image/icon/user.png');
	$('.plan_information').append(header_member);
	$(header_member).prop('class', 'member');
	$(header_member).text(member_id);
	$('.plan_information').append(personType);
	$(personType).prop('class','person_type');
	$(personType).text('('+person_se+')');
	$('.plan_information').append(modified);
	$(modified).prop('class','modify');
	$(modified).attr('onclick','location.href="/bts/plan_detail/plan_modify?plan_no=' + plan_no + '"');
	$(modified).text('수정하기');
	
}
function map_marker(arr_location){
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = { 
	    center: new kakao.maps.LatLng(37.5759947835, 126.9768292386), // 지도의
																		// 중심좌표
	    level: 9 // 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	var arr_length = arr_location.length;
	var positions = new Array();
	for(var i = 0; i < arr_length; i++){
	   positions[i] = { content: '<div>' + arr_location[i].title + '</div>', latlng: new kakao.maps.LatLng(arr_location[i].mapy, arr_location[i].mapx)}
	   
    }

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

	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다
	function makeOverListener(map, marker, infowindow) {
	return function() {
	    infowindow.open(map, marker);
	};
	}

	// 인포윈도우를 닫는 클로저를 만드는 함수입니다
	function makeOutListener(infowindow) {
	return function() {
	    infowindow.close();
	};
	}
}