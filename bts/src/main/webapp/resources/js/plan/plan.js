/**
 * 
 */

$(document).ready(function(){
	kakaoMap();
   var diff;
   document.getElementById('map_controller').style.width = "200px";
   document.getElementById('plan_list_container').style.display = "none";
   document.getElementById('tourist').style.display = "none";
   document.getElementById('map_area').style.width = '1717px';
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
           for(var i=0, j=nowDate.getDay(); i<diff; i++,j++){
            var div = document.createElement("div");
            if(j==7){//토요일 에서 일요일로 배열을 초기화해준다
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
           
               
               //div 생성
               /*
               var listDiv = document.createElement('ul');
               $(listDiv).addClass('plan_list_content');
               $('.plan_list_container').append(listDiv);
                * */
               var ul = document.createElement('ul');
               $(ul).addClass("DAY"+(i+1));
               $(ul).css('display','none');
               
            
               //li 붙이기
              
             
               
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
               document.getElementById('map').style.width = '1157px';
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
function kakaoMap(mapx,mapy){
	//카카오 지도 js
		console.log(mapx);
		console.log(mapy);
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
	   center: new kakao.maps.LatLng(37.566826, 126.9786567), //지도의 중심좌표.
	   level: 8//지도의 레벨(확대, 축소 정도)
	};
	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	   var markerPosition  = new kakao.maps.LatLng(mapy,mapx); 

	   // 마커를 생성합니다
	   var marker = new kakao.maps.Marker({
	       position: markerPosition
	   });

	   // 마커가 지도 위에 표시되도록 설정합니다
	   marker.setMap(map);

	   // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
	   // marker.setMap(null);  
	}
function enter_check(e){
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
//관광지 리스트 출력 함수
function searchContentType(){
$('.detail_list_container').empty();


	var sigungucode = $('.select_place option:selected').val();	
	
 
	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
  var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
        + serviceKey
        + '&contentTypeId=38&areaCode=1&sigunguCode=' + sigungucode + '&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=30&pageNo=1&_type=json';
  console.log(reqUrl);
  var mapx , mapy;

  	$.ajax({
  		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			var resultArray = data.response.body.items.item;
			
			if(resultArray instanceof Array){
				for(var i in resultArray){
					var container = document.createElement('div');
					var content_image = document.createElement('div');
					var image = document.createElement('img');
					var title = document.createElement('h2');
					var address = document.createElement('p');
					$('.detail_list_container').append(container);
					$(container).prop('class','detail_information');
					$(container).append(content_image);
					$(container).append(title);
					$(container).append(address);
					$(content_image).append(image);
					$(title).prop('class','title_infomation');
					$(address).prop('class','address_information');
					$(content_image).prop('class','first_image');
					$(image).prop('class','image_information');
					$(image).prop('src', resultArray[i].firstimage);
					mapx = resultArray[i].mapx;
					mapy = resultArray[i].mapy;
					
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
  	kakaoMap(mapx,mapy);
} 
	

