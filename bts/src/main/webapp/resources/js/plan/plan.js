/**
 * 
 */
var globalKey;

function setGlobal_p(key){
	console.log(key);
	globalKey = key;
}
var map_produce = function(){
	$(function produce(){
	 var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	 mapOption = { 
	 	center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
	 	level: 8 // 지도의 확대 레벨
	 	
	 };

	  map = new kakao.maps.Map(mapContainer, mapOption);// 지도를 생성합니다
	});
 }
$(document).ready(map_produce);
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
           console.log(nowDate.getDay());
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
              $(this).prop('class','active_day');
              $(this).siblings().removeClass();
              $('.active_day').prop('style','background-color:rgb(160,160,160); color:#000;'); 	
              $(this).siblings().attr('style','none');
              document.getElementById('map_controller').style.width = "460px";
              document.getElementById('plan_list_container').style.display="inline-block";
              document.getElementById('tourist').style.display = "inline-block";
              document.getElementById('map_area').style.width = '1457px';
              document.getElementById('map').style.width = '1117px';
              
               
               var date = $(this).children('p');
               var day = $(this).children('blockquote');
               $('.date_value').text($(date).text());
               $('.day_value').text($(day).text());
               $('.'+text).css('display','block');
               $('.paging').attr('style','display:block');
               var search = document.getElementsByClassName(text);
               
               var li_length = $(search).children('li').length;
               var data_arr = new Array();
               for(var i=0; i<li_length; i++){
            	   var data_container = $(search).children('li');
            	   var data_value = $(data_container[i]).data('value');
            	   data_arr[i] = data_value;
               }
               var result_arr = new Array();
               for(var j in data_arr){
            	   var content_id = data_arr[j];
            	   var serviceKey = globalKey;
            	   var reqUrl =  'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey='
     			        + serviceKey
      			        + '&contentId='+ content_id+'&areaCode=1&sigunguCode=&MobileOS=ETC&MobileApp=AppTest&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json'; 
            	   $.ajax({
            		   async : false,
            			url : reqUrl,
            			dataType : 'json',
            			success : function(data,textStatus){
            				var resultArray = data.response.body.items.item;
            				var result_obj = new Object();
            				result_obj['mapx'] = resultArray.mapx
            				result_obj['mapy'] = resultArray.mapy
            				result_obj['title'] = resultArray.title
            				
            				result_arr[j] = result_obj;
            			},
            			error : function(data, textStatus){
            				alert('잘못 된 접근입니다.')
            			}
            	   });
               }
               hideMarkers();
               searchContentType(12);
               add_marker(result_arr);
         });  
           
     });
});

});


function enter_check(event){
	if(window.event.keyCode == 13){
		add_tag();
	}		
}
// 관광지 리스트 출력 함수
function searchContentType(contentTypeId){
	
	$('.detail_list_container').empty();

	$('.select_place').attr('onchange','searchContentType('+ contentTypeId +')');
	var serviceKey = globalKey;
	var sigungucode = $('.select_place option:selected').val();
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
	        + serviceKey
	        + '&contentTypeId='+ contentTypeId +'&areaCode=1&sigunguCode='+sigungucode+'&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=100&pageNo=1&_type=json';

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
			removeMarkers();
			place_marker(arr_result);
			},
			error : function(data, textStatus) {
				alert("잘못된 접근입니다.")
			}
			
  	});
  	$('.add_button').on('click',function(){
  		
  	
  		var arr_index = $(this).parent().index();
  		var mapx = arr_result[arr_index].map_x;
  		var mapy = arr_result[arr_index].map_y;
  		var title = arr_result[arr_index].title;
  		var contentid = arr_result[arr_index].contentid;
  		var li = document.createElement('li');
  		var image_container = document.createElement('div');
  		var add_image = document.createElement('img');
  		var add_title = document.createElement('h3');
  		var add_address = document.createElement('p');
  		var trashcan = document.createElement('a');
  		var trashcan_img = document.createElement('img');
  		var text = $('.plan_list_header>h1').text();
  		var add_list = document.getElementsByClassName(text)[0]
  		var location_obj = new Object();
  		var location_arr = new Array();
  		
  		add_list.appendChild(li);
  		$(li).prop('id','add_list');
  		$(li).append(image_container);
  		$(li).append(add_title);
  		$(li).append(add_address);
  		$(li).append(trashcan);
  		$(li).attr('data-value',contentid);
  		$(image_container).append(add_image);
  		$(image_container).prop('class','image_container');
  		$(add_image).prop('class','add_image');
  		$(add_image).attr('src',arr_result[arr_index].first_image);
  		$(add_title).prop('class','add_title')
  		$(add_title).text(arr_result[arr_index].title);
  		$(add_address).prop('class','add_address');
  		$(add_address).text(arr_result[arr_index].address);
  		$(trashcan).append(trashcan_img);
  		$(trashcan).prop('id','remove_place');
  		$(trashcan).prop('href','#');
  		$(trashcan_img).prop('class','trashcan_img');
  		$(trashcan_img).attr('src','/bts/resources/image/icon/garbage.png');
  		
  		location_obj['mapx'] = mapx;
  		location_obj['mapy'] = mapy;
  		location_obj['title'] = title;
  		location_arr.push(location_obj);
  		
  		add_marker(location_arr);
  		
  		var select_list =$(add_list).children().length;
  		var plan_arr = new Array();
  		for(var i=0; i<select_list;i++){
  			var id = $(add_list).children()[i];
  			var contentid =  $(id).data('value');
  			plan_arr[i] = contentid;
  		}
  		for(var i in plan_arr){
  			$(trashcan).prop('class','btn_del'+i);
  			var className = document.getElementsByClassName('btn_del'+i);
  		}
  		$(className).on('click',function(){
  			var test = $(this).parent().nextAll();
  			
  			$(this).parent().remove();
  			for(var i=0; i<test.length;i++){
  				var change_class = $(test).children('a');
  				
  				var next_class = $(change_class[i]).attr('class');
  				var class_title = next_class.substring(0,7);
  				var number = next_class.substr(7);
  				var class_number = number-1;
  				var result = class_title.concat(class_number); 
  				$(change_class[i]).prop('class',result);
  				
  			}
  		
  			var serviceKey = globalKey;
  			var content_id = $(this).parent().data('value');
  			var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey='
  			        + serviceKey
  			        + '&contentId='+ content_id+'&areaCode=1&sigunguCode=&MobileOS=ETC&MobileApp=AppTest&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y&_type=json';
  			var result_obj = new Object();
  			var result_arr = new Array();
  			$.ajax({
  				async : false,
  				url : reqUrl,
  				dataType : 'json',
  				success : function(data,textStatus){
  					var resultArray = data.response.body.items.item;
  					var mapx = resultArray.mapx;
  					var mapy = resultArray.mapy;
  					var title = resultArray.title;
  					
  					result_obj['mapx'] = mapx;
  					result_obj['mapy'] = mapy;
  					result_obj['title'] = title;
  					
  					result_arr.push(result_obj);
  					
  					document.getElementsByClassName('active_day')[0].click();
  				},
  				error : function(data, textStatus){
  					alert('잘못된 접근 입니다.')
  				}
  			});
  		});
  		
  	});
  	
  		
}


var markers_arr = new Array();
function place_marker(arr_result){
	
	var arr_length = arr_result.length;

	var positions = new Array();
	var bounds = new kakao.maps.LatLngBounds();
	
	for(var i = 0; i < arr_length; i++){
	   positions[i] = { content: '<div>' + arr_result[i].title + '</div>', latlng: new kakao.maps.LatLng(arr_result[i].map_y, arr_result[i].map_x)}
	   bounds.extend(positions[i].latlng);
    }
	for (var i = 0; i < positions.length; i ++) {
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    map:map, // 마커를 표시할 지도
	    position: positions[i].latlng // 마커의 위치
	});
	markers_arr[i] = marker;
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
	map.setBounds(bounds);
	
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
function removeMarkers(){
	for(var i=0; i<markers_arr.length; i++){
		markers_arr[i].setMap(null);
	}
	markers_arr = [];
}

function selectWishList(){
	$('.detail_list_container').empty();
	$.ajax({
  		async : false,
		url : "/bts/plan/select_wishList",
		dataType : 'json',
		success : function(data, textStatus){
			var wish = data.wishList;
			myWishList(wish);
		},
		error : function(data, textStatus) {
			alert("잘못된 접근입니다.");
		}
	});
}
function myWishList(wish){
	var serviceKey = globalKey;
	
	var result_arr = new Array();
	for(var key in wish){
		var my_wishList = wish[key];
		var contentid = my_wishList['CONTENT_ID'];
		var reqUrl ='http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' 
					+ serviceKey + '&contentId=' + contentid + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
		
		$.ajax({
	  		async : false,
			url : reqUrl,
			dataType : 'json',
			success : function(data, textStatus) {
				var result_obj = new Object();
				 resultArray = data.response.body.items.item;
				 var container = document.createElement('div');
				 var image_container = document.createElement('div');
				 var information_container = document.createElement('div');
				 var image = document.createElement('img');
				 var add_icon = document.createElement('img');
				 var title = document.createElement('h2');
				 var address = document.createElement('p');
				 var add_plan = document.createElement('a');
				
				 
				 $('.detail_list_container').append(container);
				 $(container).append(information_container);
				 $(container).prop('class','detail_information');
				 $(information_container).append(image_container);
				 $(information_container).append(title);
				 $(image_container).append(image);
				 $(container).append(add_plan);
				 $(add_plan).append(add_icon);
				 $(title).prop('class','title_information');
				 $(image_container).prop('class','first_image');
				 $(image).prop('class','image_infoemation');
				 $(image).attr('src',resultArray.firstimage);
				 $(information_container).append(address);
				$(address).prop('class','address_information');
				 $(add_icon).attr('src','/bts/resources/image/icon/plus.png');
				 $(add_plan).prop('class','add_button');
				 $(add_plan).prop('style','cursor:pointer');
				 $(add_icon).prop('class','button_icon');
				 
				 var p_title = document.createTextNode(resultArray.title);
				 title.appendChild(p_title);
				 var p_addr = document.createTextNode(resultArray.addr1);
				 address.appendChild(p_addr);
				 
				 result_obj['title'] = resultArray.title;
				 result_obj['first_image'] = resultArray.firstimage;
				 result_obj['contentid'] = resultArray.contentid;
				 result_obj['map_x'] = resultArray.mapx;
				 result_obj['map_y'] = resultArray.mapy;
				 result_obj['address'] = resultArray.addr1;
				 
				 result_arr.push(result_obj);
				 
		},
		error : function(data, textStatus) {
			alert("잘못 된 접근입니다.");
		}
		
	});
	
	}
	removeMarkers();
	place_marker(result_arr);
	$('.add_button').on('click',function(){
		var index = $(this).parent().index();
		var result_obj = new Object();
		var result_array = new Array();
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
  		$(trashcan).prop('id','remove_place');
  		$(trashcan).prop('href','#');
  		$(trashcan_img).prop('class','trashcan_img');
  		$(trashcan_img).attr('src','/bts/resources/image/icon/garbage.png');
  		$(image_container).append(add_image);
  		$(image_container).prop('class','image_container');
  		$(add_image).prop('class','add_image');
  		$(add_image).attr('src',result_arr[index].first_image);
  		$(add_title).prop('class','add_title')
  		$(add_title).text(result_arr[index].title);
  		$(add_address).prop('class','add_address');
  		$(add_address).text(result_arr[index].address);
  		var contentid = result_arr[index].contentid;
  		$(li).attr('data-value',contentid);
  		
  		var mapx = result_arr[index].map_x;
  		var mapy = result_arr[index].map_y;
  		var title = result_arr[index].title;
  		console.log(title);
  		
  		result_obj['mapx'] = mapx;
  		result_obj['mapy'] = mapy;
  		result_obj['title'] = title;
  		
  		result_array.push(result_obj);
  		
  		add_marker(result_array);
  		
  		var select_list =$(add_list).children().length;
  		var plan_arr = new Array();
  		for(var i=0; i<select_list;i++){
  			var id = $(add_list).children()[i];
  			var contentid =  $(id).data('value');
  			plan_arr[i] = contentid;
  		}
  		for(var j in plan_arr){
  			$(trashcan).prop('class','btn_del'+j);
  			var add_class = document.getElementsByClassName('btn_del'+j);
  		}
  		$(add_class).on('click',function(){
  			var test = $(this).parent().nextAll();
  			
  			$(this).parent().remove();
  			for(var i=0; i<test.length;i++){
  				var change_class = $(test).children('a');
  				
  				var next_class = $(change_class[i]).attr('class');
  				var class_title = next_class.substring(0,7);
  				var number = next_class.substr(7);
  				var class_number = number-1;
  				var result = class_title.concat(class_number); 
  				$(change_class[i]).prop('class',result);
  				
  			}
  			document.getElementsByClassName('active_day')[0].click();
  		});

	});
}
//마커 객체를 가지고 있을 배열을 전역변수로 설정
var markers = [];
function add_marker(result){
	
	if(result == [] || result == null || result == Array(0)){
		hideMarkers();
	}else{
	for(var i=0;i<result.length;i++){
			var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",//마커 이미지
			imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
			imageOption = {offset: new kakao.maps.Point(11, 34)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
			
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
			markerPosition = new kakao.maps.LatLng(result[i].mapy, result[i].mapx); // 마커가 표시될 위치입니다
			
			// 마커를 생성합니다
			var marker = new kakao.maps.Marker({
				map: map,
				position: markerPosition,
				image: markerImage, // 마커이미지 설정
				zIndex:100
				
			});
			
			//마커의 z-index설정
			marker.setZIndex(100);
			marker.getZIndex();
			// 마커가 지도 위에 표시되도록 설정합니다
			marker.setMap(map);
			// 생성된 마커를 배열에 추가합니다
			markers.push(marker);
		}
	}
		
}
//지도에 마커를 숨기거나 보여주는 함수
function setMarkers(map){
	for(var i=0; i<markers.length;i++){
		markers[i].setMap(map);
	}
}
//지도에 마커 보여주기
function showMarkers() {
    setMarkers(map)    
}
//지도에서 마커 감추기
function hideMarkers(){
	setMarkers(null);
}

function save_plan(){
	var data_obj = new Object();
	var result_arr = new Array();
	var title = $('.title').val();
	var tag = $('.tag_value').text();
	var str = tag.substring(0,tag.length-1);
	var tag_name = str.split('x');
	var personnel = $('.personnel').val();
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
			console.log(result);
		}
		frm_plan.action = "/bts/plan/insert_plan";
		frm_plan.method = "get";
		frm_plan.tag_value.value = tag_name;
		frm_plan.personnel.value = personnel;
		frm_plan.detail_information.value = result;
		frm_plan.submit();
}

}

