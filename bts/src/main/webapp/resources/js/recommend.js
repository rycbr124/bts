/**
 * 추천(지역별) - 한국관광공사 API 
 */
$(document).ready(function (){
	
	$('#search').on('click', result_init);
});

/*
function selectChange(){
	
	
	
	var selectItem = $("#contenttypeid").val();
	console.log(selectItem);
	var changeItem;
	var changeNum;
	
	if(selectItem == "32"){
		changeItem = accom;
		changeNum = accom_num;
	} else if(selectItem == "39"){
		changeItem = food;
		changeNum = food_num;
	}
	
	console.log(changeItem);
	
	$("#cat3").empty();
	
	for(var count = 0; count < changeItem.length; count++){
		var option = $("<option>" + changeItem[count] + "</option>");
		$("#cat3").append(option);
		console.log(changeNum[count]);
		$(option).prop('value', changeNum[count]);
	}
}
 * */


	 
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
	console.log("소분류 : " + cat3);
	if(cat3 === undefined){
		cat3 = "";
	}
	console.log("소분류22: " + cat3);
	
	var sigungucode = $("#sigungucode option:selected").val();

	console.log(sigungucode);
	var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey='
			+ serviceKey
			+ '&contentTypeId=' + contentTypeId + '&areaCode=1&sigunguCode=' + sigungucode + '&cat3=' + cat3 + '&MobileOS=ETC&MobileApp=AppTest&arrange=P&numOfRows=9&pageNo=' + pageNo + '&_type=json';
	console.log(reqUrl);
	$.ajax({
		async : false,
		url : reqUrl,
		dataType : 'json',
		success : function(data, textStatus) {
			var resultArray = data.response.body.items.item;
			console.log(resultArray)
			if(resultArray === undefined){
				var img = document.createElement('img');
				
			}
			if (resultArray instanceof Array) {
				for ( var i in resultArray) {
					var col = document.createElement('div');
					var container = document.createElement('div');
					var card = document.createElement('div');
					var image = document.createElement('img');
					var body = document.createElement('div');
					var title = document.createElement('h5');
					var addr = document.createElement('p');
					var href = document.createElement('a');					
					
					var c_title = document
							.createTextNode(resultArray[i].title);
					var c_addr1 = document
							.createTextNode(resultArray[i].addr1);
					var c_button = document
					.createTextNode('상세보기');
				
					title.appendChild(c_title);
					addr.appendChild(c_addr1);
					href.appendChild(c_button);
					
					var imageArr = resultArray[i].firstimage;
					if (imageArr === undefined) {
						$(col).prop('class', 'col-lg-4 col-md-6 mb-4');
						$(container).prop('class', 'container');
						$(card).prop('class', 'card');
						$(card).prop('style', 'width:300px');
						$(image).prop('class', 'card-img-top');
						$(image).prop('src', '/pro27/image/no_img.jpg');
						$(image).prop('alt', 'Card image');
						$(image).prop('style', 'width:100%');
						$(body).prop('class', 'card-body');
						$(title).prop('class', 'card-title');
						$(addr).prop('class', 'card-text');
						$(href).prop('href', '#');
						$(href).prop('class', 'btn btn-outline-dark btn-sm');		
						//대체 이미지 경로 삽입
					} else {
						$(col).prop('class', 'col-lg-4 col-md-6 mb-4');
						$(container).prop('class', 'container');
						$(card).prop('class', 'card');
						$(card).prop('style', 'width:300px');
						$(image).prop('class', 'card-img-top');
						$(image).prop('src', resultArray[i].firstimage);
						$(image).prop('alt', 'Card image');
						$(image).prop('style', 'width:100%');
						$(body).prop('class', 'card-body');
						$(title).prop('class', 'card-title');
						$(addr).prop('class', 'card-text');
						$(href).prop('href', '#');
						$(href).prop('class', 'btn btn-outline-dark btn-sm');	
						

					}

					$('#image_grid').append(col);
					$(col).append(container);
					
					$(container).append(card);
					$(card).append(image);
					$(card).append(body);
					$(body).append(title);
					$(body).append(addr);
					$(body).append(href);
					
					
				}
			} else {
				var col = document.createElement('div');
				var container = document.createElement('div');
				var card = document.createElement('div');
				var image = document.createElement('img');
				var body = document.createElement('div');
				var title = document.createElement('h5');
				var addr = document.createElement('p');
				var href = document.createElement('a');					
				
				var c_title = document
						.createTextNode(resultArray.title);
				var c_addr1 = document
						.createTextNode(resultArray.addr1);
				var c_button = document
				.createTextNode('상세보기');
			
				title.appendChild(c_title);
				addr.appendChild(c_addr1);
				href.appendChild(c_button);
				
				var imageArr = resultArray.firstimage;
				if (imageArr === undefined) {
					$(col).prop('class', 'col-lg-4 col-md-6 mb-4');
					$(container).prop('class', 'container');
					$(card).prop('class', 'card');
					$(card).prop('style', 'width:300px');
					$(image).prop('class', 'card-img-top');
					$(image).prop('src', '/pro27/image/no_img.jpg');
					$(image).prop('alt', 'Card image');
					$(image).prop('style', 'width:100%');
					$(body).prop('class', 'card-body');
					$(title).prop('class', 'card-title');
					$(addr).prop('class', 'card-text');
					$(href).prop('href', '#');
					$(href).prop('class', 'btn btn-outline-dark btn-sm');	
					//대체 이미지 경로 삽입
				} else {
					$(col).prop('class', 'col-lg-4 col-md-6 mb-4');
					$(container).prop('class', 'container');
					$(card).prop('class', 'card');
					$(card).prop('style', 'width:300px');
					$(image).prop('class', 'card-img-top');
					$(image).prop('src', resultArray.firstimage);
					$(image).prop('alt', 'Card image');
					$(image).prop('style', 'width:100%');
					$(body).prop('class', 'card-body');
					$(title).prop('class', 'card-title');
					$(addr).prop('class', 'card-text');
					$(href).prop('href', '#');
					$(href).prop('class', 'btn btn-outline-dark btn-sm');	

				}

				$('#image_grid').append(col);
				$(col).append(container);
				$(container).append(card);
				$(card).append(image);
				$(card).append(body);
				$(body).append(title);
				$(body).append(addr);
				$(body).append(href);

			}

		},
		error : function(data, textStatus) {
			alert("잘못된 접근입니다.")
		}
	});
}