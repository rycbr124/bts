/**
 * 
 */
//href = # 막기
$(document).on('click', 'a[href="#"]', function(e) {
		e.preventDefault();
	});
function planRoot(plan_root,planner,e){
	var plan_detail = plan_root.plan_root;
	var plan_info = planner.planner;
	var planNo = plan_detail[0];
	var plan_no = planNo['PLAN_NO']; 
	
	
	for(var key in plan_info){
		var plan_info = plan_info[key];
		var title = plan_info['TITLE'];
		var rangeDate = plan_info['RANGE_DATE'];
		var member_id = plan_info['MEMBER_ID'];
		var person_se = plan_info['PERSON_SE'];	
	}
	
	//day수 만큼 DIV생성
	var startDt = rangeDate.substring(0,10);
	var startDt_arr = startDt.split('-');
	var endDt = rangeDate.substring(12,23);
	var endDt_arr = endDt.split('-');
	
	var startDate = new Date(startDt_arr[0],Number(startDt_arr[1])-1, startDt_arr[2]);
	var endDate = new Date(endDt_arr[0],Number(endDt_arr[1])-1, endDt_arr[2]);
	
	var diff = ((endDate.getTime() - startDate.getTime())/1000/60/60/24)+1;
	
	var week = ['일', '월', '화', '수', '목', '금', '토'];
	var nowDate = startDate;
	for(var i=0, j=nowDate.getDay(); i<diff; i++,j++){
		
		var day = document.createElement('div');
		$('.plan_detail_info').append(day);
		$(day).prop('class','DAY'+(i+1));
		$(day).prop('id','compare_day');
		
		var day_info_box = document.createElement('div');
		$(day_info_box).prop('class','day_info_box');
		$(day).append(day_info_box);
		
		var day_text = document.createElement('div');
		$(day_info_box).append(day_text);
		$(day_text).prop('class','day_text');
		$(day_text).text('DAY'+(i+1));
		
		
		
		var by_day_info = document.createElement('div');
		$(day_info_box).append(by_day_info);
		$(by_day_info).prop('class','by_day_info');
		
		
		var date = document.createElement('div');
		var sYear = nowDate.getFullYear();
        var sMonth = nowDate.getMonth();
        var sDate = nowDate.getDate();
        var nowDate = new Date(sYear,sMonth,sDate+1);
        var resultDay = sYear+"."+(sMonth+1)+"."+sDate;
        if(j==7){
        	j = 0;
        }
        var dayOfWeek = week[j];
		$(by_day_info).append(date);
		$(date).prop('class','date_info');
		$(date).text(resultDay+'('+dayOfWeek+')');
		

		
	}
	
    //plan_detail에 들어갈 information	
	var resultArray;
	var array = new Array();
	var arr_location = new Array();
	var info = document.createElement('div');
	var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
	var day_arr = new Array();
	var day;
	var desc;
	for(var key in plan_detail){
		var detail = plan_detail[key];
		var dayNo = detail['DAY_NO'];
		var detail_no = detail['DETAIL_NO'];
		var plan_desc = detail['PLAN_DESC'];
		day_arr.push(dayNo);
		day = day_arr.reduce(function(a,b){
			if(a.indexOf(b) < 0) a.push(b);
			return a;
		},[]);
		var contentid = detail['CONTENT_ID'];
		var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey='
				   + serviceKey + '&contentId='+ contentid +'&defaultYN=Y&firstImageYN=Y&overviewYN=Y&mapinfoYN=Y&MobileOS=ETC&MobileApp=AppTest';
		$.ajax({
			async:false,
			url: reqUrl,
			dataType: 'json',
			success: function(data, textStatus){
				resultArray = data.response.body.items.item;
				array.push(resultArray.firstimage);
				$('.plan_detail_header > .thumbnail_image').attr('src', array[0]);
				$('.plan_detail_header').append(info);
				$(info).prop('class','plan_information');
				
				//compare_day에 들어갈 내용
				
				var by_day = document.createElement('div');
				var day_sch_num = document.createElement('div');
				var sch_num = document.createElement('div');
				var sch_content = document.createElement('div');
				var spot_content_box = document.createElement('div');
				var spot_name = document.createElement('div');
				var btn_memo = document.createElement('div');
				var sch_add_memo = document.createElement('div')
				var save_memo = document.createElement('a');
				var place_desc = document.createElement('input');
				var sch_memo = document.createElement('textarea');
				var image = document.createElement('img');
				var add_content = document.getElementsByClassName(dayNo)[0];
				$(add_content).append(by_day);
				$(by_day).prop('class','day_sch_box');
				$(by_day).attr('data-no',detail_no);
				$(by_day).append(day_sch_num);
				$(day_sch_num).prop('class','day_sch_num');
				$(day_sch_num).append(sch_num);
				$(sch_num).prop('class','sch_num');
				$(by_day).append(sch_content);
				$(sch_content).prop('class','sch_content');
				$(sch_content).append(image);
				$(image).prop('class','spot_img');
				$(image).attr('src',resultArray.firstimage);
				$(sch_content).append(spot_content_box);
				$(spot_content_box).prop('class','spot_content_box');
				$(spot_content_box).append(spot_name);
				$(spot_name).prop('class','spot_name');
				$(spot_name).text(resultArray.title);
				$(spot_content_box).append(btn_memo);
				$(btn_memo).prop('class','btn_memo');
				$(btn_memo).text('메모하기');
				$(spot_content_box).append(place_desc);
				$(place_desc).prop('class','place_desc');
				$(place_desc).prop('type', 'text');
				$(place_desc).attr('disabled', true);
				$(place_desc).prop('value',plan_desc);

				
				//메모를 추가할시 생성
				$(sch_content).append(sch_add_memo);
				$(sch_add_memo).prop('class','sch_add_memo');
				$(sch_add_memo).append(sch_memo);
				$(sch_memo).prop('class','sch_memo');
				$(sch_memo).prop('cols','30');
				$(sch_memo).prop('rows','10');
				$(sch_add_memo).append(save_memo);
				$(save_memo).text('저장');
				$(save_memo).prop('href','#');
				$(save_memo).prop('class','save_memo');
				
				
			},
			error : function(data, textStatus) {
				alert("잘못된 접근입니다.")
			}
			
		});
	}
	
	//hearder에 들어갈 information
	var header_title = document.createElement('h1');
	var header_rangeDt = document.createElement('p');
	var header_member = document.createElement('h3');
	var profile = document.createElement('img');
	var modified = document.createElement('button');
	var personType = document.createElement('p');
	$('.plan_information').append(header_title);
	$(header_title).prop('class', 'title');
	$(header_title).text(title);
	$('.plan_information').append(header_rangeDt);
	$(header_rangeDt).prop('class', 'rangeDate');
	$(header_rangeDt).text(rangeDate);
	$('.plan_information').append(profile);
	$(profile).prop('class','profile_image');
	$(profile).attr('src','/bts/resources/image/icon/user.png');
	$('.plan_information').append(header_member);
	$(header_member).prop('class', 'member');
	$(header_member).text(member_id);
	$('.plan_information').append(personType);
	$(personType).prop('class','person_type');
	$(personType).text('('+person_se+')');
	$('.plan_information').append(modified);
	$(modified).prop('class','modify');
	$(modified).attr('onclick','location.href="/bts/plan_detail/plan_modify?plan_no=' + plan_no + '"');
	$(modified).text('수정하기');
	
	
	$('.btn_memo').on('click',function(){
		var toggleArea = $(this).parent();
		var dropdown = toggleArea.next();
		$(dropdown).toggle();
	});
	$('.save_memo').on('click',function(e){
		var frm_desc = document.desc;
		var desc = $(this).prev().val();
		var textArea = $(this).parent().prev();
		var add = $(textArea).children().last();
		$(add).val(desc);
		console.log(desc);
		var desc_value = $(add).val();
		var data = $(this).parent().parent().parent();
		var content_no = $(data).data('no');
		$('.sch_add_memo').attr('style','diplay:none;');
		frm_desc.action = '/bts/plan_detail/update_desc';
		frm_desc.method = 'get';
		frm_desc.plan_no.value = plan_no;
		frm_desc.detail_no.value = content_no;
 		frm_desc.plan_desc.value = desc_value;
		frm_desc.submit();
	});
}
	
	

