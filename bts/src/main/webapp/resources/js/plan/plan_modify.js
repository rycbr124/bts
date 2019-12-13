/**
 * 
 */
function plan_modify(planner_info,detail_info, tagList){
	var test = document.getElementById('touristDestination').click();
	var planner = planner_info.planner[0];
	var tag = tagList.tagList;
	var detail = detail_info.plan_root;
	var title = planner['TITLE'];
	var rangeDate = planner['RANGE_DATE'];
	var person_type = planner['PERSON_SE'];
	var plan_no = planner['PLAN_NO'];
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
	// 시작일과 도착일을 계산하여 준다
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
		if(j == 7){// 토요일에서 일요일로 배열을 초기화
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
//	var active_day = $('.content_container').children()[0];
//	$(active_day).prop('class','active');
//	
//	$(active_day).prop('style','background-color:rgb(160,160,160); color:#000;')
	document.getElementById('map_controller').style.width = "460px";
	document.getElementById('plan_list_container').style.display="inline-block";
	document.getElementById('tourist').style.display = "inline-block";
	document.getElementById('map_area').style.width = '1457px';
	document.getElementById('map').style.width = '1117px';
	document.getElementById('map').style.float = 'right';
	$('.save_plan').text('수정');
	$('.save_plan').attr('onclick','modify_plan('+plan_no+')');
	var date = document.getElementById('date');
	var day = document.getElementById('day');
	var date_text = $(date).text();
	var day_val = $(day).text();
		$('.date_value').text(date_text);
		$('.day_value').text(day_val);
		$('.'+date_text).css('display','block');
		//가장  처음 보여줄 플랜 마커
	$(function showing(){
		var check_class = $('.DAY1').children('li')
		var length = $('.DAY1').children('li').length;
		var result_arr = new Array();
		for(var i=0; i<length;i++){
			var content_id = $(check_class[i]).data('value');
		
		 var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
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
      				
      				
      				var mapx = resultArray.mapx;
      				var mapy = resultArray.mapy;
      				var title = resultArray.title;
      				
      				result_obj['mapx'] = mapx;
      				result_obj['mapy'] = mapy;
      				result_obj['title'] = title;
      				
      				result_arr.push(result_obj);
      				
      			},
      			error : function(data,textStatus){
      				
      			}
      	   });
		}
		var Markers = [];
		for(var i=0;i<result_arr.length;i++){
			var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png",//마커 이미지
			imageSize = new kakao.maps.Size(24, 35), // 마커이미지의 크기입니다
			imageOption = {offset: new kakao.maps.Point(11, 34)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
			
			var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
			markerPosition = new kakao.maps.LatLng(result_arr[i].mapy, result_arr[i].mapx); // 마커가 표시될 위치입니다
			
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
	});
		
	 $('div.content_container>div').on('click',function(){
		 
		   var day_text = $(this).text();
    	   $('ul').css('display','none');
    	   var childP=$(this).children('p')[0];
           var text = $(childP).text();
           var date = $(this).children('p');
           var day = $(this).children('blockquote');
           $(this).prop('class','active');
           $(this).siblings().removeClass();
           $(this).prop('class','active_day');
           $('.active_day').prop('style','background-color:rgb(160,160,160); color:#000;'); 	
           $(this).siblings().attr('style','none');
           $('.date_value').text($(date).text());
           $('.day_value').text($(day).text());
           $('.'+text).css('display','block');
          
           
           
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
        	   var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
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
           add_marker(result_arr);
	   });
	 
	 content_value(detail);
}
function content_value(detail){
	var result_arr = new Array();
	var day_obj = new Object();
	var day_arr = new Array();
	var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
	for(var key in detail){
		var detail_arr = detail[key];
		var contentid = detail_arr['CONTENT_ID'];
		var DAY = detail_arr['DAY_NO'];
		
	var reqUrl ='http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' 
	+ serviceKey + '&contentId=' 
	+ contentid + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
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
	  		
	  		var add_list = document.getElementsByClassName(DAY)[0];
	  		$(add_list).append(li);
	  		$(li).prop('class',DAY);
	  		
	  				$(li).prop('id','add_list');
		  			$(li).attr('data-value',contentid);
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
			  		$(add_image).attr('src',resultArray.firstimage);
			  		$(add_title).prop('class','add_title')
			  		$(add_title).text(resultArray.title);
			  		$(add_address).prop('class','add_address');
			  		$(add_address).text(resultArray.addr1);
	  	
		  		result_obj['map_x'] = resultArray.mapx;
		  		result_obj['map_y'] = resultArray.mapy;
		  		result_obj['title'] = resultArray.title;
		  		
		  		result_arr.push(result_obj);
	  		
		},
		error : function(data, textStatus){
			alert("잘못 된 접근입니다.")
		}
	});
	}
	var text_arr = new Array();
	var date_length = $('.content_container').children().length;
	for(var i=0; i<date_length; i++){
		var date = $('.content_container>div').children('p')[i];
		var text = $(date).text();
		text_arr[i] = text;
	}
	for(var j in text_arr){
		var check = document.getElementsByClassName(text_arr[j])[0];
		var select_list =$(check).children().length;
		var plan_arr = new Array();
		
		for(var i=0; i<select_list;i++){
			var id = $(check).children()[i];
			var contentid =  $(id).data('value');
			plan_arr[i] = contentid;
			}	
		var class_arr = new Array();
		for(var k in plan_arr){
			var add_class = $('.'+text_arr[j]+'>a')[k];
			$(add_class).attr('class','btn_del'+k);
			var className = document.getElementsByClassName('btn_del'+k);
			class_arr[k] = className;
		}
		}
		for(var j in class_arr){
		$(class_arr[j]).on('click',function(){
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
			document.getElementsByClassName('active_day').click();
			var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
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
					
				},
				error : function(data, textStatus){
					alert('잘못된 접근 입니다.')
				}
			});
		});
	}
}


// 키보드 이벤트가 엔터일시 실행돼는 함수
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

function modify_plan(plan_no){
	var data_obj = new Object();
	var result_arr = new Array();
	var title = document.getElementById("title_val").value;
	console.log(title);
	var tag = $('.tag_value').text();
	var str = tag.substring(0,tag.length-1);
	var tag_name = str.split('x');
	
	var personnel = $('.personnel').val();
	
	if(confirm("저장하시겠습니까?") === true){
		console.log(plan_no);
		var frm_plan = document.plan;
			var resultData = {};
		var ulLength = $('.plan_list_container>ul').length;
			for(var i=0; i<ulLength; i++){
				var liLength = $(".DAY"+(i+1)+'>li').length;	
				 var arr_test = new Array();
				for(var j=0; j<liLength; j++){
					 var $data =$('.DAY'+(i+1)+'>li');
					 var test = $data.eq(j).data('value');
					
					 arr_test[j] = test;
					
				}
				resultData["DAY"+(i+1)] = arr_test;
				var result = JSON.stringify(resultData);
			}
			frm_plan.action = "/bts/plan_detail/modify";
			frm_plan.method = "get";
			frm_plan.plan_no.value = plan_no;
			frm_plan.tag_value.value = tag_name;
			frm_plan.personnel.value = personnel;
			frm_plan.detail_information.value = result;
			frm_plan.submit();
			}
}