/**
 * 추천(지역별) - 한국관광공사 API 
 */
$(document).ready(function (){   
	
      
});

function result_init(){
    var pageNo = 1;
    image_init(pageNo);//이미지 삽입
    //paging 뿌리는 메소드
    $('.page-item').on('click',paging_click); //뿌린 버튼에 클릭이벤트 달아주는 메소드
}
function operation(contenttypeid, idNumber){
	var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D';
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey='+ serviceKey +'&contentTypeId='+ contenttypeid +'&contentId=' + idNumber + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y';
	$.ajax({
		async : false,
	    url : reqUrl,
	    dataType : 'json',
	    success:function(data,textStatus){
	    	var	resultArray = data.response.body.items.item;
	    	var info_array = new Array();
	    	console.log(resultArray);
	    	if(resultArray['playtime'] == null || resultArray['playtime'] == ""){
	    		
	    	}else{
	    		var start = resultArray.eventstartdate;
		    	var end = resultArray.eventenddate;
		    	var startDate = (start.toString().substring(0,4)+ '.'+start.toString().substring(4,6)+'.'+start.toString().substring(6,8));
		    	var endDate = (end.toString().substring(0,4) + '.' + end.toString().substring(4,6)+'.'+end.toString().substring(6,8));
		    	var eventDate = startDate + '~' +endDate;
		    	resultArray['playtime'] = eventDate;
	    	}
	    	var detail_key = Object.keys(resultArray)
	    	var detail_val = Object.values(resultArray);
	    	var length = detail_key.length;
	    	for(var i=0; i<length; i++){
	    		if(detail_val[i] == "" || detail_val[i] == null){
	    			
	    		}else{
	    			if(detail_key[i] == 'contentid' || detail_key[i] == 'contenttypeid' ||detail_val[i] == '가능' || detail_val[i] == '불가' || detail_key[i] == 'eventstartdate' || detail_key[i] == 'eventenddate' || detail_val[i] == '없음' || detail_val[i] == '있음'){
	    				console.log('안들어가지롱')
	  
	    			}else{
	    				info_array.push(detail_val[i]);
	    				
	    			}
	    		}
	    	}
	    	
	    	console.log(resultArray);
	    	var result_length = info_array.length;
	    	var info_container = document.createElement('div');
	    	$('.col-lg-5').append(info_container);
	    	$(info_container).prop('class','info_container');
	    	for(var j=0; j<result_length; j++){
	    		var info = document.createElement('p');
	    		$(info_container).append(info);
	    		$(info).prop('class','info'+j);
	    		$(info).html(info_array[j]);
	    	}
	    },
	    error : function(data,textStatus){
	    	  
	    }
	});
}
function image_init(idNumber, command) {
   $("#image_grid").empty();
   
   var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D';
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
         
         if(resultArray.homepage != undefined){
	         var h_page = document.createElement('p');
	         $(h_page).prop('class', 'h_page');
	         $(h_page).html("홈페이지 : " + resultArray.homepage);
         }
         
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
         $('.col-lg-5').append(addr1);
         $('.col-lg-5').append(h_page);
         $('.col-lg-5').append(form);
         $(form).append(divHeart);
         $(divHeart).append(heartTxt);
         $(divHeart).append(heart);
         $(form).append(hidden);
         $(form).append(hidden2);
         $('.content').html(resultArray.overview);
         
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
            var result_length = data.response.body.items.item.length;
            console.log("길이 : " + result_length);
            
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


