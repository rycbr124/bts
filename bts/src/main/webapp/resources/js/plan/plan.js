/**
 * 
 */
$(document).ready(function(){
	var diff;
$(function() {//jquery calendar
	  $('input[name="daterange"]').daterangepicker({
	    opens: 'right',
	    autoUpdateInput: true,
	    autoApply:true,
	    locale: {
	        format: 'YYYY-MM-DD'
	      }
	  });
	  $('input[name="daterange"]').on('apply.daterangepicker', function(ev, picker) {
		  //시작일과 마지막일의 차수를 구해 정수로 반환해주는 코드
	        picker.startDate._d.setHours(0,0,0,0);
	        picker.endDate._d.setHours(0,0,0,0);
	        diff = picker.endDate._d - picker.startDate._d;
	        diff = diff/(1000*60*60*24)+1;
	        
	        //요일 반환 코드
	        var week = ['일', '월', '화', '수', '목', '금', '토'];
	        //일자선택을 초기화 해준다
	        $("#content_container").empty(); //초기화
	        var nowDate = picker.startDate._d;//선택한 출발날자
	        for(var i=0, j=picker.startDate._d.getDay(); i<diff; i++,j++){
	        	var div = document.createElement("div");
	        	if(j==7){//토요일 에서 일요일로 배열을 초기화해준다
	        		j=0;
	        	}
	        	var dayOfWeek = week[j];
				document.getElementById('content_container').appendChild(div);
				$(div).prop('class','day_list');
				$(div).prop('id','day_list');
				var p = document.createElement('p');
				$(div).append(p);
				$(p).prop('class','date');
				$(p).prop('id','date');
				$(p).text("DAY"+[i+1]);
				var h2 = document.createElement('h2');
				$(div).append(h2);
				$(h2).prop('class','date_dtw');
				$(h2).prop('id','date_dtw');
				$(h2).text(dayOfWeek);
				var blockquote = document.createElement('blockquote'); 
				
				var sYear = nowDate.getFullYear();
				var sMonth = nowDate.getMonth();
				var sDate = nowDate.getDate();
				var nowDate = new Date(sYear,sMonth,sDate+1);
				var resultDay = (sMonth+1)+"-"+sDate;
				
	      	 	$(div).append(blockquote);
	      	 	$(blockquote).prop('class','day');
	      	 	$(blockquote).prop('id','day');
	      	 	$(blockquote).text(resultDay);
				
				
	        }
	        	var h2 = document.createElement('h2');
	        	var p = document.createElement('p');
	        	$('.day_list').on('click',function(){
	        		$('.plan_list_header').empty();
	        		$('.plan_box').empty();
	        		$('.plan_list_header').append(h2);
		        	$('.plan_list_header').append(p);
		        	$(h2).prop('class','date_value');
		        	$(h2).prop('id','date_value');
		        	$(p).prop('class','day_value');
		        	$(p).prop('id','day_value');
		        	
	        		var date = $(this).children('p');
	        		var day = $(this).children('blockquote');
	        		$('.date_value').text($(date).text());
	        		$('.day_value').text($(day).text());
	        		
	        		
	        		for(var i=0; i<30; i++){
	        			var li = document.createElement('li');
	        			$('.plan_box').append(li);
	        		};
	        		
	        	});
        		
	  		});
		});







//카카오 지도 js
var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
var options = { //지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(37.566826, 126.9786567), //지도의 중심좌표.
	level: 7 //지도의 레벨(확대, 축소 정도)
};
var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

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