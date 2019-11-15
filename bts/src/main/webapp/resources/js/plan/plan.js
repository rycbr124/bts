/**
 * 
 */

$(document).ready(function(){
	$('input[type="text"]').keydown(function() {
		  if (event.keyCode === 13) {
		    event.preventDefault();
		  };
		});
   var diff;
   document.getElementById('map_controller').style.width = "200px";
   document.getElementById('plan_list_container').style.display = "none";
   document.getElementById('tourist').style.display = "none";
   document.getElementById('map_area').style.width = '1717px';
$(function() {// jquery calendar
     $('input[name="daterange"]').daterangepicker({
       opens: 'right',
       autoUpdateInput: true,
       autoApply:true,
       locale: {
           format: 'YYYY-MM-DD'
         }
     });
   
     $('input[name="daterange"]').on('apply.daterangepicker', function(ev, picker) {
        // 시작일과 마지막일의 차수를 구해 정수로 반환해주는 코드
           picker.startDate._d.setHours(0,0,0,0);
           picker.endDate._d.setHours(0,0,0,0);
           diff = picker.endDate._d - picker.startDate._d;
           diff = diff/(1000*60*60*24)+1;
           // 요일 반환 코드
           var week = ['일', '월', '화', '수', '목', '금', '토'];
           // 일자선택을 초기화 해준다
           $("#content_container").empty(); // 초기화
           var nowDate = picker.startDate._d;// 선택한 출발날자
           for(var i=0, j=nowDate.getDay(); i<diff; i++,j++){
            var div = document.createElement("div");
            if(j==7){// 토요일 에서 일요일로 배열을 초기화해준다
               j=0;
            }
            var dayOfWeek = week[j];
               document.getElementById('content_container').appendChild(div);
               $(div).prop('id','day_list');
               var p = document.createElement('p');
               $(div).append(p);
               $(p).prop('class','date');
               $(p).prop('id','date');
               $(p).text("DAY"+(i+1));
               
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
           
               
               var ul = document.createElement('ul');
               $(ul).addClass("DAY"+(i+1));
               $(ul).prop('id','dropContainer');
               $(ul).css('display','none');
               $(ul).attr('ondragover','allowDrop(event)');
               
            
               
               $('.plan_list_container').append(ul);
           }
       
           $('div.content_container>div').on('click',function(){
              $('ul').css('display','none');
              var childP=$(this).children('p')[0];
              var text = $(childP).text();
              
               document.getElementById('map_controller').style.width = "460px";
               document.getElementById('plan_list_container').style.display="inline-block";
               document.getElementById('tourist').style.display = "inline-block";
               document.getElementById('map_area').style.width = '1457px';
               document.getElementById('map').style.width = '1117px';
               document.getElementById('map').style.float = 'right';
               var date = $(this).children('p');
               var day = $(this).children('blockquote');
               $('.date_value').text($(date).text());
               $('.day_value').text($(day).text());
               $('.'+text).css('display','block');
         });  
     });
});

});
var map_produce = $(function(){
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = { 
		center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
		level: 8 // 지도의 확대 레벨
		
	};

	var map = new kakao.maps.Map(mapContainer, mapOption);

	});// 지도를 생성합니다	
function enter_check(event){
	if(window.event.keyCode == 13){
			var text= $('.tag').val();
			var p = document.createElement('p');
			var a = document.createElement('a');
			var length = $('.tag_value').children().length;
			if(length >= 5){
				alert('태그는 5개까지만 추가할수있습니다.')
				
			}else{
				$('.tag_value').append(p);
				$(p).prop('class','tag_name');
				$(p).prop('style','display:inline-block;');
				$(p).text('#'+text);
				$('.tag_name').append(a);
				$(a).prop('class','delete_tag');
				$('.delete_tag').text('x');
			}
			$('.tag').val('');
		}
	
	$('.delete_tag').on('click',function(){
		$(this).parent().remove();
	});
	
	}
// 관광지 리스트 출력 함수

function searchContentType(contentTypeId){
$('.detail_list_container').empty();// 맵을 리셋 시킵니다.
	$('.select_place').attr('onchange','searchContentType('+ contentTypeId +')');
	var serviceKey = 'lUN5B8XHOdyoYlgxfJqeeTMdZZWYbuV9qc80jLPpilJ%2BYukKsP1%2FvR6W2AJ9UxbCgbUlkVqiN5O3%2FWiHMOyvcw%3D%3D'
	var sigungucode = $('.select_place option:selected').val();
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
	        + serviceKey
	        + '&contentTypeId='+ contentTypeId +'&areaCode=1&sigunguCode='+sigungucode+'&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=100&pageNo=1&_type=json';
	console.log(reqUrl);

  var arr_result = new Array();

  var resultArray;
  var array = [];
  	$.ajax({
  		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			 resultArray = data.response.body.items.item;
			
			if(resultArray instanceof Array){
				for(var i in resultArray){
					var obj_result = new Object();
					var container = document.createElement('div');
					var content_image = document.createElement('div');
					var information_container = document.createElement('div');
					var image = document.createElement('img');
					var image_button = document.createElement('img');
					var title = document.createElement('h2');
					var address = document.createElement('p');
					var add = document.createElement('a');
					$('.detail_list_container').append(container);
					$(container).append(information_container);
					$(container).prop('class','detail_information');
					$(information_container).append(content_image);
					$(information_container).append(title);
					$(information_container).append(address);
					$(content_image).append(image);
					$(container).append(add);
					$(add).append(image_button);
					$(title).prop('class','title_infomation');
					$(address).prop('class','address_information');
					$(content_image).prop('class','first_image');
					$(image).prop('class','image_information');
					$(image).prop('src', resultArray[i].firstimage);
					$(image_button).prop('src','/bts/resources/image/icon/plus.png');
					$(add).prop('class','add_button');
					$(add).prop('style','cursor:pointer;')
					$(image_button).prop('class','button_icon');
					obj_result['title'] = resultArray[i].title;
					obj_result['map_x'] = resultArray[i].mapx;
					obj_result['map_y'] = resultArray[i].mapy;
					obj_result['first_image'] = resultArray[i].firstimage;
					obj_result['address'] = resultArray[i].addr1;
					obj_result['contentid'] = resultArray[i].contentid;
					arr_result[i] = obj_result;
					var c_title = document
						.createTextNode(resultArray[i].title);
					var c_addr1 = document
						.createTextNode(resultArray[i].addr1);
					
					title.appendChild(c_title);
					address.appendChild(c_addr1);
					
					
				}
			}
			},
			error : function(data, textStatus) {
				alert("잘못된 접근입니다.")
			}
			
  	});
  	map_marker(arr_result);
  	
  	$('.add_button').on('click',function(){
  		var arr_index = $(this).parent().index();
  		var li = document.createElement('li');
  		var image_container = document.createElement('div');
  		var add_image = document.createElement('img');
  		var add_title = document.createElement('h3');
  		var add_address = document.createElement('p');
  		var trashcan = document.createElement('a');
  		var trashcan_img = document.createElement('img');
  		var text = $('.plan_list_header>h1').text();
  		var add_list = document.getElementsByClassName(text)[0]
  		var length =$(add_list).children().length;
  		add_list.appendChild(li);
  		$(li).prop('id','add_list');
  		$(li).append(image_container);
  		$(li).append(add_title);
  		$(li).append(add_address);
  		$(li).append(trashcan);
  		$(trashcan).append(trashcan_img);
  		$(trashcan).prop('class','trashcan');
  		$(trashcan_img).prop('class','trashcan_img');
  		$(trashcan_img).attr('src','/bts/resources/image/icon/garbage.png');
  		$(image_container).append(add_image);
  		$(image_container).prop('class','image_container');
  		$(add_image).prop('class','add_image');
  		$(add_image).attr('src',arr_result[arr_index].first_image);
  		$(add_title).prop('class','add_title')
  		$(add_title).text(arr_result[arr_index].title);
  		$(add_address).prop('class','add_address');
  		$(add_address).text(arr_result[arr_index].address);
 
  	 	var mapx = arr_result[arr_index].map_x;
  		var mapy = arr_result[arr_index].map_y;
  		var contentid = arr_result[arr_index].contentid;
  		$(li).attr('data-value',contentid);
  		add_marker(mapy,mapx);
  	});
 
} 
function add_marker(mapy,mapx){
	
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
  		mapOption = { 
  		    center: new kakao.maps.LatLng(mapy,mapx), // 지도의 중심좌표
  		    level: 8 // 지도의 확대 레벨
  		};

  		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
  		
  		// 지도에 표시된 마커 객체를 가지고 있을 배열입니다
  		var markers = [];

  		// 마커 하나를 지도위에 표시합니다
  		addMarker(new kakao.maps.LatLng(mapy, mapx));

  		// 마커를 생성하고 지도위에 표시하는 함수입니다
  		function addMarker(position) {
  		    
  		    // 마커를 생성합니다
  		    var marker = new kakao.maps.Marker({
  		        position: position
  		    });

  		    // 마커가 지도 위에 표시되도록 설정합니다
  		    marker.setMap(map);
  		    
  		    // 생성된 마커를 배열에 추가합니다
  		    markers.push(marker);
  		}

  		// 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
  		function setMarkers(map) {
  		    for (var i = 0; i < markers.length; i++) {
  		        markers[i].setMap(map);
  		    }            
  		}

  	
}

function map_marker(arr_result){
	$('#map').empty();
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = { 
	    center: new kakao.maps.LatLng(37.5759947835, 126.9768292386), // 지도의
																		// 중심좌표
	    level: 8 // 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	var arr_length = arr_result.length;
	var positions = new Array();

	for(var i = 0; i < arr_length; i++){
	   positions[i] = { content: '<div>' + arr_result[i].title + '</div>', latlng: new kakao.maps.LatLng(arr_result[i].map_y, arr_result[i].map_x)}
	   
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


function save_plan(){
	
	var data_obj = new Object();
	var result_arr = new Array();
	var title = $('.title').val();
	console.log(title);
	var tag = $('.tag_value').text();
	var str = tag.substring(0,tag.length-1);
	console.log(str);
	var tag_name = str.split('x');
	console.log(tag_name);
	var personnel = $('.personnel').val();
	console.log(personnel);
	if(confirm("저장하시겠습니까?") === true){
		var frm_plan = document.plan;
	
	var resultData = {};
	var ulLength = $('.plan_list_container>ul').length;
		for(var i=0; i<ulLength; i++){
			var liLength = $('.DAY'+(i+1)).children().length;
			 var arr_test = new Array();
			for(var j=0; j<liLength; j++){
				 var $data =$('.DAY'+(i+1)+'>li');
				 var test = $data.eq(j).data('value');
				 arr_test[j] = test;
				
			}
			resultData["DAY"+(i+1)] = arr_test;
			var result = JSON.stringify(resultData);
		}
		frm_plan.action = "/bts/plan/insert_plan";
		frm_plan.method = "get";
		frm_plan.tag_value.value = tag_name;
		frm_plan.personnel.value = personnel;
		frm_plan.detail_information.value = result;
		frm_plan.submit();
}else{
	
}
}
