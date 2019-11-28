

function openReport(url,contents_cd,target_id){
	var title="reportForm"
	var wid=800;
	var hei=550;
	var top = (window.screen.height/2)-(hei/2);
	var left = (window.screen.width/2)-(wid/2);
	
	var pop=window.open("",title,'width='+wid+',height='+hei+',top='+top+',left='+left);
	
	var form = document.createElement('form');
	var cdInput = document.createElement('input');
	var idInput = document.createElement('input');
	$(cdInput).attr('type','hidden');
	$(cdInput).attr('name','contents_cd');
	$(idInput).attr('type','hidden');
	$(idInput).attr('name','target_id');
	form.append(cdInput);
	form.append(idInput);
	
	form.contents_cd.value=contents_cd;			
	form.target_id.value=target_id;
	form.target=title;
	form.action=url;
	form.method="post";
	$('body').append(form);
	form.submit();
	return pop;
}