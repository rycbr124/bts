/**
 * 추천(지역별) - 한국관광공사 API 
 */
$(document).ready(function (){
	
	$('#search').on('click', result_init);

	var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey=' + serviceKey + '&areaCode=1&numOfRows=25&MobileOS=ETC&MobileApp=AppTest'
		
		$.ajax({
			async : false,
			url : reqUrl,
			dataType : 'json',
			success : function(data, textStatus) {
				var resultArray = data.response.body.items.item;
				console.log("11111111 : " + resultArray);
				if (resultArray instanceof Array) {
					for ( var i in resultArray) {
						var option = document.createElement('option');
						$(option).prop('value', resultArray[i].code);
						var name = document.createTextNode(resultArray[i].name);
						option.appendChild(name);
						
						$('#sigungucode').append(option);
	
					}
				}
						
			},
			error : function(data, textStatus) {
				alert("잘못된 접근입니다.")
			}
		});
	
});

////////////////
function result_init(){
	 var pageNo = 1;
	 image_init(pageNo);//이미지 삽입
	 //paging 뿌리는 메소드
	 $('.page-item').on('click',paging_click) //뿌린 버튼에 클릭이벤트 달아주는 메소드
}

function paging_click(){//페이징 버튼 눌렀을 때
	 var pageNo = $(this).text();//자식노드중에 텍스트노드만 가져온다.
	 if(pageNo == 'Next'){
		pageNo = 6;
	 }
	 console.log(pageNo);
	 image_init(pageNo);
	 
}
/////////////
 
function image_init(pageNo) {
	$("#image_grid").empty();
	var contentTypeId = $("#contenttypeid option:selected").val();

	var cat3 = $("#cat3 option:selected").val();

	if(cat3 === undefined){
		cat3 = "";
	}

	var sigungucode = $("#sigungucode option:selected").val();

	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
			+ serviceKey
			+ '&contentTypeId=' + contentTypeId + '&areaCode=1&sigunguCode=' + sigungucode + '&cat3=' + cat3 + '&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=9&pageNo=' + pageNo + '&_type=json';
	
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			var resultArray = data.response.body.items.item;
			console.log(resultArray);
			console.log("total 값 : " + data.response.body.totalCount);
			$('.totalCount').remove();
			var total = document.createElement('input');
			$(total).prop('type', 'hidden');
			$(total).prop('name', 'totalCount');
			$(total).prop('class', 'totalCount');
			$(total).prop('value', data.response.body.totalCount);
			
			$('.box').append(total);
			
			
			if (resultArray instanceof Array) {
				for ( var i in resultArray) {
					var col = document.createElement('div');
					var card = document.createElement('div');
					var image = document.createElement('img');
					var body = document.createElement('div');
					var title = document.createElement('h5');
					var addr = document.createElement('p');
					var form = document.createElement('form');
					var hidden = document.createElement('input');
					var submit = document.createElement('input');
					var hidden2 = document.createElement('input');
				
					
					var c_title = document
							.createTextNode(resultArray[i].title);
					var c_addr1 = document
							.createTextNode(resultArray[i].addr1);

				
					title.appendChild(c_title);
					addr.appendChild(c_addr1);

					
					var imageArr = resultArray[i].firstimage;
					if (imageArr === undefined) {
						$(col).prop('class', 'col-sm-4');
						$(card).prop('class', 'card');
						$(card).prop('style', 'width:300px');
						$(card).attr('data-value',resultArray[i].contenttypeid);
						$(image).prop('class', 'card-img-top');
						$(image).prop('src', '/bts/resources/image/no_img.jpg');
						$(image).prop('alt', 'Card image');
						$(image).prop('style', 'width:100%');
						$(body).prop('class', 'card-body');
						$(title).prop('class', 'card-title');
						$(addr).prop('class', 'card-text');
						$(form).prop('action', '/bts/recommend/place_detail');
						$(form).prop('method', 'post');
						$(hidden).prop('type', 'hidden');
						$(hidden).prop('name', 'contentid');
						$(hidden).prop('value', resultArray[i].contentid);
						$(hidden2).prop('type','hidden');
						$(hidden2).prop('name','contenttypeid');
						$(hidden2).prop('value',resultArray[i].contenttypeid);
						$(submit).prop('type', 'submit');
						$(submit).prop('class', 'btn btn-info btn-sm')
						$(submit).prop('value', '상세보기');

						//대체 이미지 경로 삽입
					} else {
						$(col).prop('class', 'col-sm-4');
						$(card).prop('class', 'card');
						$(card).attr('data-value',resultArray[i].contenttypeid);
						$(card).prop('style', 'width:300px');
						$(image).prop('class', 'card-img-top');
						$(image).prop('src', resultArray[i].firstimage);
						$(image).prop('alt', 'Card image');
						$(image).prop('style', 'width:100%');
						$(body).prop('class', 'card-body');
						$(title).prop('class', 'card-title');
						$(addr).prop('class', 'card-text');
						$(form).prop('action', '/bts/recommend/place_detail');
						$(form).prop('method', 'post');
						$(hidden).prop('type', 'hidden');
						$(hidden).prop('name', 'contentid');
						$(hidden).prop('value', resultArray[i].contentid);
						$(hidden2).prop('type','hidden');
						$(hidden2).prop('name','contenttypeid');
						$(hidden2).prop('value',resultArray[i].contenttypeid);
						$(submit).prop('type', 'submit');
						$(submit).prop('class', 'btn btn-info btn-sm')
						$(submit).prop('value', '상세보기');

						

					}

					$('#image_grid').append(col);
					
					
					$(col).append(card);
					$(card).append(image);
					$(card).append(body);
					$(body).append(title);
					$(body).append(addr);
					$(body).append(form);
					$(form).append(hidden);
					$(form).append(hidden2);
					$(form).append(submit);

					
					
				}
			} else {
				var col = document.createElement('div');
				var card = document.createElement('div');
				var image = document.createElement('img');
				var body = document.createElement('div');
				var title = document.createElement('h5');
				var addr = document.createElement('p');
				var form = document.createElement('form');
				var hidden = document.createElement('input');
				var hidden2 = document.createElement('input');
				var submit = document.createElement('input');				
				
				var c_title = document
						.createTextNode(resultArray.title);
				var c_addr1 = document
						.createTextNode(resultArray.addr1);			
			
				title.appendChild(c_title);
				addr.appendChild(c_addr1);
				
				var imageArr = resultArray.firstimage;
				if (imageArr === undefined) {
					$(col).prop('class', 'col-sm-4');
					$(card).prop('class', 'card');
					$(card).prop('style', 'width:300px');
					$(card).attr('data-value',resultArray.contenttypeid);
					$(image).prop('class', 'card-img-top');
					$(image).prop('src', '/bts/resources/image/no_img.jpg');
					$(image).prop('alt', 'Card image');
					$(image).prop('style', 'width:100%');
					$(body).prop('class', 'card-body');
					$(title).prop('class', 'card-title');
					$(addr).prop('class', 'card-text');
					$(form).prop('action', '/bts/recommend/place_detail');
					$(form).prop('method', 'post');
					$(hidden).prop('type', 'hidden');
					$(hidden).prop('name', 'contentid');
					$(hidden).prop('value', resultArray.contentid);
					$(hidden2).prop('type','hidden');
					$(hidden2).prop('name','contenttypeid');
					$(hidden2).prop('value',resultArray.contenttypeid);
					$(submit).prop('type', 'submit');
					$(submit).prop('class', 'btn btn-info btn-sm')
					$(submit).prop('value', '상세보기');	
					//대체 이미지 경로 삽입
				} else {
					$(col).prop('class', 'col-sm-4');
					$(card).prop('class', 'card');
					$(card).prop('style', 'width:300px');
					$(card).attr('data-value',resultArray.contenttypeid);
					$(image).prop('class', 'card-img-top');
					$(image).prop('src', resultArray.firstimage);
					$(image).prop('alt', 'Card image');
					$(image).prop('style', 'width:100%');
					$(body).prop('class', 'card-body');
					$(title).prop('class', 'card-title');
					$(addr).prop('class', 'card-text');
					$(form).prop('action', '/bts/recommend/place_detail');
					$(form).prop('method', 'post');
					$(hidden).prop('type', 'hidden');
					$(hidden).prop('name', 'contentid');
					$(hidden).prop('value', resultArray.contentid);
					$(hidden2).prop('type','hidden');
					$(hidden2).prop('name','contenttypeid');
					$(hidden2).prop('value',resultArray.contenttypeid);
					$(submit).prop('type', 'submit');
					$(submit).prop('class', 'btn btn-info btn-sm')
					$(submit).prop('value', '상세보기');	

				}

				$('#image_grid').append(col);
				
				$(col).append(card);
				$(card).append(image);
				$(card).append(body);
				$(body).append(title);
				$(body).append(addr);
				$(body).append(form);
				$(form).append(hidden);
				$(form).append(hidden2);
				$(form).append(submit);

			}

		},
		error : function(data, textStatus) {
			alert("잘못된 접근입니다.")
		}
	});
}