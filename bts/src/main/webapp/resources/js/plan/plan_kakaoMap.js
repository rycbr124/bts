$(document).ready(function(){
	 var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	 var options = { //지도를 생성할 때 필요한 기본 옵션
	 	center: new kakao.maps.LatLng(37.566826, 126.9786567), //지도의 중심좌표.
	 	level: 7 //지도의 레벨(확대, 축소 정도)
	 };
	 var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
	 
	 function setMapType(maptype) { 
		    var roadmapControl = document.getElementById('btnRoadmap');
		    var skyviewControl = document.getElementById('btnSkyview'); 
		    if (maptype === 'roadmap') {
		        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);    
		        roadmapControl.className = 'selected_btn';
		        skyviewControl.className = 'btn';
		    } else {
		        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);    
		        skyviewControl.className = 'selected_btn';
		        roadmapControl.className = 'btn';
		    }
		}

		// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
		function zoomIn() {
		    map.setLevel(map.getLevel() - 1);
		}

		// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
		function zoomOut() {
		    map.setLevel(map.getLevel() + 1);
		}
		var markerPosition  = new kakao.maps.LatLng(37.566826, 126.9786567); 

		// 마커를 생성합니다
		var marker = new kakao.maps.Marker({
		    position: markerPosition
		});

		// 마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);

		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
		// marker.setMap(null);    
});