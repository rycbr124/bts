/**
 * 
 */
$(document).ready(function(){
	var serviceKey = '9lYTVuZFWTTyr2CZFilfzO9woq%2Bh%2B80b5xZ4myuNqQtcxMgSl2Vz1tuOjoarEHqNuXWf2WAiOTnOBzm3zJ4Rcg%3D%3D';
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
        + serviceKey
        + '&contentTypeId=32&areaCode=1&sigunguCode=&cat1=B02&cat2=B0201&cat3=B02010100&listYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=6&pageNo=1&_type=json';
	var resultArray;
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : "json",
		success : function(data, textStatus){
			resultArray = data.response.body.items.item;
			for(var i in resultArray){
				//recommend 숙소 top3
				var card = document.createElement('div');
				var card_img_top = document.createElement('img');
				var card_body = document.createElement('div');
				var card_title = document.createElement('h5');
				var card_text = document.createElement('p');
				$('.best_recommend').append(card);
				$(card).prop('class','card');
				$(card).prop('id',resultArray[i].contentid);
				$(card).prop('style','width:400px;height:250px; display:inline-block; margin:20px; background-color:#FFFAE8; cursor:pointer;');
				
				
				$(card).append(card_img_top);
				$(card_img_top).prop('class','card-img-top');
				$(card_img_top).prop('src', resultArray[i].firstimage);
				$(card_img_top).prop('style', 'width:100%;height:auto; max-height:200px;')
				
				$(card).append(card_body);
				$(card_body).prop('class','card-body');

				
				$(card_body).append(card_title);
				$(card_title).prop('class','card-title');
				$(card_title).prop('style','font-weight:bold;');
				$(card_title).text(resultArray[i].title);
				
				$(card_body).append(card_text);
				$(card_text).prop('class','card-text')
				$(card_text).prop('style','font-size:12px;');
				$(card_text).text(resultArray[i].addr1);
			
			}
			$('.card').on('click',function(){
				var contentid = $(this).attr('id');
				location.href = "/bts/recommend/place_detail?contentid="+contentid;
			});
		},
		error : function(data, textStatus){
			alert('잘못 된 접근 입니다.');
		}
	});
	
	var reqUrl2 = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
        + serviceKey
        + '&contentTypeId=15&areaCode=1&sigunguCode=&cat1=A02&cat2=A0207&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=Q&numOfRows=5&pageNo=1&_type=json';
	$.ajax({
		async : false,
		url : reqUrl2,
		dataType : "json",
		success : function(data , textStatus){
			resultArray2 = data.response.body.items.item;
			
			for(var j in resultArray2){
				var festival_container = document.createElement('div');
				var festival_img = document.createElement('img');
				var festival_body = document.createElement('div');
				var festival_title = document.createElement('h6');
				var festival_add = document.createElement('p');
				var festival_date = document.createElement('p');
				$('.best_festival').append(festival_container);
				$(festival_container).prop('class','fs-container');
				$(festival_container).prop('id',resultArray2[j].contentid);
				
				$(festival_container).append(festival_img);
				$(festival_img).prop('class','fs-img');
				$(festival_img).prop('src',resultArray2[j].firstimage);
				
				$(festival_container).append(festival_body);
				$(festival_body).prop('class','fs-body');
				
				$(festival_body).append(festival_title);
				$(festival_title).prop('class','fs-title');
				$(festival_title).text(resultArray2[j].title);
				
				$(festival_body).append(festival_add);
				$(festival_add).text(resultArray2[j].addr1);
				
				$(festival_body).append(festival_date);
				$(festival_date).prop('class','fs-date');
				
			}
			$('.fs-container').on('mouseenter',function(){
				$(this).children('.fs-body').fadeIn();
				var content_id  = $(this).attr('id');
				var serviceKey = '9lYTVuZFWTTyr2CZFilfzO9woq%2Bh%2B80b5xZ4myuNqQtcxMgSl2Vz1tuOjoarEHqNuXWf2WAiOTnOBzm3zJ4Rcg%3D%3D';
				var reqUrlDetail ='http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey='+serviceKey+'&contentTypeId=15&contentId='+content_id+'&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&introYN=Y';
				$.ajax({
					async : false,
					url : reqUrlDetail,
					dataType : "json",
					success : function(data, textStatus){
						resultDetail = data.response.body.items.item;
						var startDate = resultDetail.eventstartdate;
						var endDate = resultDetail.eventenddate;
						
						var stYear = startDate.toString().substring(0,4);
						var stMonth = startDate.toString().substring(4,6);
						var stDay = startDate.toString().substring(6,8);
						var start = (stYear + '.' + stMonth + '.' + stDay);
						
						var endYear = endDate.toString().substring(0,4);
						var endMonth = endDate.toString().substring(4,6);
						var endDay = endDate.toString().substring(6,8);
						var end = (endYear + '.' + endMonth + '.' + endDay);
						
						$('.fs-date').text(start + '~' + end);
					},
					error : function(data, textStatus){
						
					}
				});
			});
			$('.fs-container').on('mouseleave',function(){	
				$(this).children('.fs-body').fadeOut();
			});
			$('.fs-container').on('click',function(){
				var id = $(this).attr('id');
				location.href = "/bts/recommend/place_detail?contentid="+id;
			});
		},
		error : function(data, textStatus){
			alert('잘못된 접근입니다.');
		}
	});
	$('.more').on('mouseover',function(){
		$('.more p').fadeIn();
	});
	$('.more').on('mouseleave',function(){
		$('.more p').fadeOut();
	});
});
