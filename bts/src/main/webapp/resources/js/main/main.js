/**
 * 
 */
$(document).ready(function(){
	var serviceKey = '9lYTVuZFWTTyr2CZFilfzO9woq%2Bh%2B80b5xZ4myuNqQtcxMgSl2Vz1tuOjoarEHqNuXWf2WAiOTnOBzm3zJ4Rcg%3D%3D';
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
        + serviceKey
        + '&contentTypeId=32&areaCode=1&sigunguCode=&cat1=B02&cat2=B0201&cat3=B02010100&listYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=5&pageNo=1&_type=json';
		console.log(reqUrl);
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : "json",
		success : function(data, textStatus){
			resultArray = data.response.body.items.item;
			for(var i in resultArray){
				console.log(resultArray[0]);
				//recommend 숙소 top3
				var card = document.createElement('div');
				var card_img_top = document.createElement('img');
				var card_body = document.createElement('div');
				var card_title = document.createElement('h5');
				var card_text = document.createElement('p');
				$('.best_recommend').append(card);
				$(card).prop('class','card');
				$(card).prop('id',resultArray[i].contentid);
				$(card).prop('style','width:330px; display:inline-block; margin-right:20px; background-color:#FFFAE8; cursor:pointer;');
				
				
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
			var card_prev = $(card).prev();
			$(card_prev).prop('class','card');
			$(card_prev).prop('style','width:513px;height:262px; margin-top:10px; background-color:#FFFAE8; cursor:pointer;');
			$(card).prop('style','position:absolute; margin-top:10px; width:513px; height:auto; bottom:45px; cursor:pointer; background-color:#FFFAE8; left:615px;');
			$('.card').on('click',function(){
				var contentid = $(this).attr('id');
				location.href = "/bts/recommend/place_detail?contentid="+contentid;
			});
		},
		error : function(data, textStatus){
			alert('잘못 된 접근 입니다.');
		}
	});
	$.ajax({
		
	});
});
