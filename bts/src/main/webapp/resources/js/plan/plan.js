/**
 * 
 */
$(document).ready(function(){
	var diff;
$(function() {
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
	        $("#content_container").empty();
	        var nowDate = picker.startDate._d;
	        for(var i=0, j=picker.startDate._d.getDay(); i<diff; i++,j++){
	        	var div = document.createElement("div");
	        	if(j==7){
	        		j=0;
	        	}
	        	var dayOfWeek = week[j];
				document.getElementById('content_container').appendChild(div);
				$(div).prop('class','day_list');
				$(div).prop('id','day_list');
				var p = document.createElement('p');
				$(div).append(p);
				$(p).prop('class','date');
				$(p).prop('id','date');
				$(p).text("DAY"+[i+1]);
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
				
				
	        }
	        	var h2 = document.createElement('h2');
	        	var p = document.createElement('p');
	        
	        	$('.day_list').on('click',function(){
	        		$('.plan_list_header').empty();
	        		$('.plan_box').empty();
	        		$('.plan_list_header').append(h2);
		        	$('.plan_list_header').append(p);
		        	$(h2).prop('class','date_value');
		        	$(h2).prop('id','date_value');
		        	$(p).prop('class','day_value');
		        	$(p).prop('id','day_value');
	        		var date = $(this).children('p');
	        		var day = $(this).children('blockquote');
	        		$('.date_value').text($(date).text());
	        		$('.day_value').text($(day).text());
	        		for(var i=0; i<30; i++){
	        			var li = document.createElement('li');
	        			$('.plan_box').append(li);
	        		};
	        	});
        		
	  		});
		});
});