
var globalValue;

function setInit(path,article,member,reqUrl){
   globalValue=new valueForm();
   
   function valueForm(){
      var contextPath=path;
      var articleNo=article;
      var id=member;
      var url=reqUrl;
      
      this.getContextPath=function(){
         return contextPath;
      };
      
      this.setContextPath=function(input){
         contextPath=input;
      }
      
      this.getArticleNo=function(){
         return articleNo;
      };
      
      this.setArticleNo=function(input){
         articleNo=input;
      }
      
      this.getId=function(){
         return id;
      };
      
      this.setId=function(input){
         id=input;
      }
      
      this.getUrl=function(){
         return url;
      };
      
      this.setUrl=function(input){
         url=input;
      }
      
   }
}


$(document).ready(function(){
   
   $(document).on('click','span.comment-delete',function(){
      var con_test = confirm("삭제하시겠습니까?");
      if(con_test){
         var input = $(this).parent().parent().data('no');
         $.ajax({
            type : "post", 
            async : false,
            url : globalValue.getContextPath()+globalValue.getUrl()+"/comment/delete",
             data: {answer_no:input},
             dataType:'text',
            success : function (data,textStatus){
               if(data=='true'){
                  var paging=$('li.active').text();
                  comPaging(paging);
               }else{
                  alert("잠시 후 다시 시도해주세요.");                     
               }
            },//end success
            error : function (data,textStatus){
               alert("잠시 후 다시 시도해주세요.");
            }            
         }); //end ajax   
      }
   });
   
   $(document).on('click','a.page-link',function(){
      var paging=$(this).text();
      comPaging(paging);
   });
   
});

function comPaging(paging){
   var startPage=$('li.page-item:first').next().text();
   var endPage=$('li.page-item:last').prev().text();
   var articleNO=globalValue.getArticleNo();
   
   if(paging==$('a.page-link:first').text()){
      paging=startPage-1;
   }else if(paging==$('a.page-link:last').text()){
      paging=Number(endPage)+1;
   }
   
   var searchData={
         curPage : paging,
         article_no : articleNO   
   }
   
   $.ajax({
      type : "post", 
      async : false,
      url : globalValue.getContextPath()+globalValue.getUrl()+"/comment",
      data: searchData,
      dataType:'json',
      success : function (data,textStatus){
         $('#comment-count>span').text(data.paging.totalCount);
         $('#comments').empty();
         for(var i in data.comments){
            var comment = data.comments[i];
            var profile = comment.profile_image;
            
            if(profile==null){
               profile=globalValue.getContextPath()+'/resources/image/no_img.jpg';
            }else{
               if(comment.member_type!='kakao' && comment.member_type!='naver'){
            	   profile=globalValue.getContextPath()+profile;
               }                     
            }
            
            var comDiv = makeComment(comment.answer_no,comment.answer_desc,comment.member_id,profile,comment.register_date);
            $('#comments').append(comDiv);
         }
         makePaging(data.paging.startPage,data.paging.endPage,data.paging.curPage);
      },//end success
      error : function (data,textStatus){
         alert("에러가 발생했습니다.");
      }
   }); //end ajax   
}

function makePaging(startPage,endPage,curPage){
   $('#paging-list').empty();
   
   for(var j=startPage-1;j<=endPage+1;j++){
      var li=document.createElement('li');
      var a=document.createElement('a');
      $(li).addClass('page-item');
      if(j==curPage){
         $(li).addClass('active');
      }
      $(a).addClass('page-link');
      if(j==startPage-1){
         $(a).text('Previous');               
      }else if(j==endPage+1){
         $(a).text('Next');                              
      }else{
         $(a).text(j);               
      }
      li.append(a);
      
      $('#paging-list').append(li);
   }
   
}

function makeComment(answer_no,answer_desc,member_id,profile_image,register_date){
   var container = document.createElement('div');
   var imgDiv=document.createElement('img');
   var boxDiv=document.createElement('div');
   var textDiv=document.createElement('div');
   var footerDiv=document.createElement('div');
   var authorSpan=document.createElement('span');
   var dateSpan=document.createElement('span');
   var delSpan=document.createElement('span');
   var repSpan=document.createElement('span');
   
   $(container).addClass('comment');
   $(imgDiv).addClass('comment-image');
   $(boxDiv).addClass('comment-box');
   $(textDiv).addClass('comment-text');
   $(footerDiv).addClass('comment-footer');
   $(authorSpan).addClass('comment-author');
   $(dateSpan).addClass('comment-date');
   $(repSpan).addClass('comment-report');
   $(delSpan).addClass('comment-delete');
   
   container.append(imgDiv);
   container.append(boxDiv);
   boxDiv.append(textDiv);
   boxDiv.append(footerDiv);
   footerDiv.append(authorSpan);
   footerDiv.append(dateSpan);
   footerDiv.append(repSpan);
   if(globalValue.getId()==member_id){
      footerDiv.append(delSpan);            
   }
   $(imgDiv).prop('src',profile_image);
   $(boxDiv).data('no',answer_no);
   $(textDiv).text(answer_desc);
   $(authorSpan).text(member_id);
   var   regDate = new Date(register_date)
   $(dateSpan).text(regDate.toLocaleString('ko-KR', { dateStyle:'medium', timeStyle:'medium', hour12:false }));         
   $(repSpan).text('신고');
   $(delSpan).text('삭제');
   
   return container;
}