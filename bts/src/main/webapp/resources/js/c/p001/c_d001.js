/**
 * 
 */

$(document).ready(function(){
	$("#profImg").click(function(){
		$("#input_img").click() ;
		})			
	})

var sel_file;

$(document).ready(function() {
    $("#input_img").on("change", fileChange);
});

function fileChange(e) {
	e.preventDefault();


	var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    filesArr.forEach(function(f) {
        if(!f.type.match("image.*")) {
            alert("확장자는 이미지 확장자만 가능합니다.");
            return;
        }

        sel_file = f;

        var reader = new FileReader();
        reader.onload = function(e) {
            $("#profImg").attr("src", e.target.result);
        	$("#profImg").css("height", "100px")
        }
        reader.readAsDataURL(f);
    });

    var file = files[0]
    console.log(file)
    var formData = new FormData();

    formData.append("file", file);
		$.ajax({
    	url: '/bts/my/uploadAjax',
		  data: formData,
		  dataType:'text',
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){

			alert("프로필 이미지가 변경 되었습니다.")

		  }
		});
		

}
