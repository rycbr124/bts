/**
 * 
 */
function plan_modify(planner_info,detail_info, tagList){
	var planner = planner_info.planner[0];
	var tag = tagList.tagList;
	var detail = detail_info.plan_root;
	var title = planner['TITLE'];
	var rangeDate = planner['RANGE_DATE'];
	var person_type = planner['PERSON_SE'];
	$('.title').attr('value',title);
	$('.daterange').attr('value',rangeDate);
	$('.personnel').val(person_type).attr('selected','selected');

	var contentid_arr = new Array();
	for(var key in detail){
		var detail_arr = detail[key];
		var contentid = detail_arr['CONTENT_ID'];
		contentid_arr.push(contentid);
	}
	var delete_tag = document.createElement('a');
	for(var key in tag){
		var tag_name = document.createElement('p');
		var tag_arr = tag[key];
		var tag_value = tag_arr['TAG_NAME'];
		$('.tag_value').append(tag_name);
		$(tag_name).prop('class','tag_name');
		$(tag_name).prop('style','display:inline-block;');
		$(tag_name).text(tag_value);
		$(delete_tag).prop('class','delete_tag');
	}
	$('.tag_name').append(delete_tag);
	$('.delete_tag').text('x');
	$('.delete_tag').on('click',function(){
		$(this).parent().remove();
	});
	//시작일과 도착일을 계산하여 준다
	var start = rangeDate.substring(0,10);
	var startDt_arr = start.split('-');
	var end = rangeDate.substring(12,23);
	var endDt_arr = end.split('-');	

	var startDate = new Date(startDt_arr[0],Number(startDt_arr[1])-1, startDt_arr[2]);
	var endDate = new Date(endDt_arr[0],Number(endDt_arr[1])-1, endDt_arr[2]);
	
	var day_num = (endDate.getTime() - startDate.getTime())/1000/60/60/24;
	var diff = day_num +1;
	
	var week = ['일', '월', '화', '수', '목', '금', '토'];
	var nowDate = startDate
	for(var i=0, j=nowDate.getDay(); i<diff; i++,j++){
		var travel_day = document.createElement('div');
		if(j == 7){//토요일에서 일요일로 배열을 초기화
			j =0;
		}
		var dayOfWeek = week[j];
		document.getElementById('content_container').appendChild(travel_day);
		$(travel_day).prop('id','day_list');
		var dayNum = document.createElement('p');
		$(travel_day).append(dayNum);
		$(dayNum).prop('class','date');
		$(dayNum).prop('id','date');
		$(dayNum).text('DAY'+(i+1));
		
		var dtw = document.createElement('h2');
		$(travel_day).append(dtw);
		$(dtw).prop('class','date_dtw');
		$(dtw).prop('id','date_dtw');
		$(dtw).text(dayOfWeek);
		var blockquote = document.createElement('blockquote'); 
        var sYear = nowDate.getFullYear();
        var sMonth = nowDate.getMonth();
        var sDate = nowDate.getDate();
        var nowDate = new Date(sYear,sMonth,sDate+1);
        var resultDay = (sMonth+1)+"-"+sDate;
        
        $(travel_day).append(blockquote);
        $(blockquote).prop('class','day');
        $(blockquote).prop('id','day');
        $(blockquote).text(resultDay);
        
        var ul = document.createElement('ul');
        $(ul).addClass("DAY"+(i+1));
        $(ul).prop('id','dropContainer');
        $(ul).css('display','none');
        
        $('.plan_list_container').append(ul);
	}
	   $('div.content_container>div').on('click',function(){
		   searchContentType(12);
		   
		   document.getElementById('map_controller').style.width = "460px";
		   document.getElementById('plan_list_container').style.display="inline-block";
		   document.getElementById('tourist').style.display = "inline-block";
		   document.getElementById('map_area').style.width = '1457px';
		   document.getElementById('map').style.width = '1117px';
		   document.getElementById('map').style.float = 'right';
		   $('.save_plan').text('수정');
		   $('.save_plan').attr('onclick','modify_plan()');
		   $('.plan_list_container > ul').empty();
		   var day_text = $(this).text();
		   var compare_day = day_text.substring(0,4);
    	  
    	   $('ul').css('display','none');
    	   var childP=$(this).children('p')[0];
           var text = $(childP).text();
    	   
    	   
    	   
           
           var date = $(this).children('p');
           var day = $(this).children('blockquote');
           $('.date_value').text($(date).text());
           $('.day_value').text($(day).text());
           $('.'+text).css('display','block');
           
           content_value(detail,compare_day);
	   });
}
function content_value(detail,compare_day){
	
	var result_arr = new Array();
	var serviceKey = 'cYcvlZ9yaPE20UToWcxr8bpZJbItY6rEa3kIxGzSd3N4e3R1kmaERDblD3vpL6zg3bM76YmVcfip6YU83Nc4CA%3D%3D'
	for(var key in detail){
		var detail_arr = detail[key];
		var contentid = detail_arr['CONTENT_ID'];
		var DAY = detail_arr['DAY_NO'];
		
	var reqUrl ='http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' 
	+ serviceKey + '&contentId=' 
	+ contentid + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
	console.log(reqUrl);
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			resultArray = data.response.body.items.item;
			var result_obj = new Object();
			var li = document.createElement('li');
	  		var image_container = document.createElement('div');
	  		var add_image = document.createElement('img');
	  		var add_title = document.createElement('h3');
	  		var add_address = document.createElement('p');
	  		var trashcan = document.createElement('a');
	  		var trashcan_img = document.createElement('img');
	  		var text = $('.plan_list_header>h1').text();
	  		var add_list = document.getElementsByClassName(text)[0]
	  		add_list.appendChild(li);
	  		$(li).prop('class',DAY);
	  		var className2 = $(li).attr('class');
	  		if(compare_day === className2){
	  			$(li).prop('id','add_list');
	  			$(li).attr('data-value',contentid);
	  			$(li).append(image_container);
		  		$(li).append(add_title);
		  		$(li).append(add_address);
		  		$(li).append(trashcan);
		  		$(trashcan).append(trashcan_img);
		  		$(trashcan).prop('class','trashcan');
		  		$(trashcan).prop('href','#');
		  		$(trashcan_img).prop('class','trashcan_img');
		  		$(trashcan_img).attr('src','/bts/resources/image/icon/garbage.png');
		  		$(image_container).append(add_image);
		  		$(image_container).prop('class','image_container');
		  		$(add_image).prop('class','add_image');
		  		$(add_image).attr('src',resultArray.firstimage);
		  		$(add_title).prop('class','add_title')
		  		$(add_title).text(resultArray.title);
		  		$(add_address).prop('class','add_address');
		  		$(add_address).text(resultArray.addr1);
		  		result_obj['map_x'] = resultArray.mapx;
		  		result_obj['map_y'] = resultArray.mapy;
		  		result_obj['title'] = resultArray.title;
		  		
		  		result_arr.push(result_obj);
		  	 	
	  		}else{
	  			$(li).remove();
	  		}
	  		
		},
		error : function(data, textStatus){
			alert("잘못 된 접근입니다.")
		}
	});
	}
	//장소 삭제시 실행
	$('.trashcan').on('click',function(){
			$(this).parent().remove();
			
	});
	
	$('#map').empty();
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div
		mapOption = { 
		    center: new kakao.maps.LatLng(37.5759947835, 126.9768292386), // 지도의
																			// 중심좌표
		    level: 8 // 지도의 확대 레벨
		};
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		
		var arr_length = result_arr.length;
		var positions = new Array();

		for(var i = 0; i < arr_length; i++){
		   positions[i] = { content: '<div>' + result_arr[i].title + '</div>', latlng: new kakao.maps.LatLng(result_arr[i].map_y, result_arr[i].map_x)}
		   
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

//키보드 이벤트가 엔터일시 실행돼는 함수
function add_tag(){
	var text= $('.tag').val();
	var p = document.createElement('p');
	var a = document.createElement('a');
	var length = $('.tag_value').children().length;
	if(length >= 5){
		alert('태그는 5개까지만 추가할수있습니다.')
		
	}else{
		$('.tag_value').append(p);
		$(p).prop('class','tag_name');
		$('.tag_name').prop('style','display:inline-block;');
		$(p).text('#'+text);
		$('.tag_name').append(a);
		$(a).prop('class','delete_tag');
		$('.delete_tag').text('x');
	}
	$('.tag').val('');
	$('.delete_tag').on('click',function(){
		$(this).parent().remove();
	});
	}

function modify_plan(){
	var data_obj = new Object();
	var result_arr = new Array();
	var title = document.getElementById("title_val").value;

	var tag = $('.tag_value').text();
	var str = tag.substring(0,tag.length-1);
	var tag_name = str.split('x');
	
	var personnel = $('.personnel').val();
	
			if(confirm("저장하시겠습니까?") === true){
		var frm_plan = document.plan;
			var resultData = {};
		var ulLength = $('.plan_list_container>ul').length;
			for(var i=0; i<ulLength; i++){
				var liLength = $(".DAY"+(i+1)+'>li').length;	
				console.log(liLength);		
				 var arr_test = new Array();
				for(var j=0; j<liLength; j++){
					 var $data =$('.DAY'+(i+1)+'>li');
					 var test = $data.eq(j).data('value');
					
					 arr_test[j] = test;
					
				}
				resultData["DAY"+(i+1)] = arr_test;
				var result = JSON.stringify(resultData);
				
			}
// 			frm_plan.action = "/bts/plan/update_plan";
// 			frm_plan.method = "get";
// 			frm_plan.tag_value.value = tag_name;
// 			frm_plan.personnel.value = personnel;
// 			frm_plan.detail_information.value = result;
// 			frm_plan.submit();
			}
}