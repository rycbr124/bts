$(window).on("scroll", function(){
	if($(window).scrollTop() > 100){
		$("#header").addClass("bg");
	}else{
		$("#header").removeClass("bg");
	}
});

