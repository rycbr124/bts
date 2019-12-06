<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${contextPath}/resources/css/admin/style.css"> 
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheetinfo.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheet.js"></script>
<script src="${contextPath}/resources/ibsheet/ibleaders.js"></script>
<script src='https://www.ibsheet.com/demo/js/lib/sheet/ibsheet-global.js'></script>
<script language="javascript">
$(document).ready(function (){
var ib = {
	    initialize: function() {
	        var initData = {
	            "Cfg": {
	                "SearchMode": smLazyLoad,
	                "MergeSheet": msHeaderOnly,
	                "ChildPage": 5,
	                "AutoFitColWidth": "search|resize|init|colhidden|rowtransaction",
	                "DeferredVScroll": 1
	            },
	            "HeaderMode": {},
	            "Cols": [{
	                "Header": "성향분류",
	                "Type": "Text",
	                "Width": 300,
	                "SaveName": "group_desc",
	                "TreeCol": 1,
	                "LevelSaveName": "TREELEVEL",
	            }, {
	                "Header": "성향코드",
	                "Type": "Text",
	                "Width": 129,
	                "SaveName": "incln_cd",
	                "Align": "Center"
	            }, {
	                "Header": "성향이름",
	                "Type": "Text",
	                "Width": 400,
	                "SaveName": "name",
	                "Align": "Center"
	            }, {
	                "Header": "성향그룹",
	                "Type": "Text",
	                "Width": 130,
	                "SaveName": "group_name",
	                "Align": "Center"
	            }, ]
	        };

	        var container = $("#ib-container")[0];
	        createIBSheet2(container, "mySheet", "100%", "284px");

	        if (this.setIBEvents) {
	            this.setIBEvents();
	        }

	        IBS_InitSheet(mySheet, initData);

	        this.doAction();
	    },
	    setIBEvents: function() {
	        window["mySheet_OnTreeChild"] = function(row) {
	            var val = mySheet.GetCellValue(row, "group_desc"),
	                data = {};

	            switch (val) {
	                case "경비":
	                    data = gallery["treeChild"].data_list1;
	                    break;

	                case "계획":
	                    data = gallery["treeChild"].data_list2;
	                    break;

	                case "음식":
	                    data = gallery["treeChild"].data_list3;
	                    break;

	                case "사람":
	                    data = gallery["treeChild"].data_list4;
	                    break;

	                case "안전":
	                    data = gallery["treeChild"].data_list5;
	                    break;
	               
	                case "풍경":
	                    data = gallery["treeChild"].data_list6;
	                    break;
	                    
	                case "시간":
	                    data = gallery["treeChild"].data_list7;
	                    break;
	                    
	                case "스타일":
	                    data = gallery["treeChild"].data_list8;
	                    break;
	            }

	            mySheet.LoadSearchChildData(row, data, {
	                Sync: 1
	            });
	        };
	    },
	    data: {
	        "data": [{
	            "group_desc": "성향",
	            "group_name": "incln",
	            "Level": 0
	        }, {
	            "group_desc": "경비",
	            "group_name": "money",
	            "Level": 1,
	            
	        }, {
	            "group_desc": "계획",
	            "group_name": "plan",
	            "Level": 1,
	            
	        }, {
	            "group_desc": "음식",
	            "group_name": "food",
	            "Level": 1,
	            
	        }, {
	            "group_desc": "사람",
	            "group_name": "friend",
	            "Level": 1,
	            
	        }, {
	            "group_desc": "안전",
	            "group_name": "safety",
	            "Level": 1,
	            
	        }, {
	            "group_desc": "풍경",
	            "group_name": "scenery",
	            "Level": 1,
	            
	        }, {
	            "group_desc": "시간",
	            "group_name": "time",
	            "Level": 1,
	            
	        }, {
	            "group_desc": "스타일",
	            "group_name": "theme",
	            "Level": 1,
	            
	        }
	        
	        ]
	    },
	    doAction: function() {
	    	//var param = FormQueryStringEnc(document.frm);
			//mySheet.DoSearch("${contextPath}/admin/searchIncln", param);

	        mySheet.LoadSearchData(this.data, {
	            Sync: 1
	        });
	    }
	    
	   
	};


	ib.initialize();

	// jsfiddle에서 ib 접근을 위해 window에 등록
	window.ib = ib;
});
</script>
</head>
<body>
	<div class="page_title">
	    <span><a class="closeDepth" href="#">closeDepth</a></span> 
	    <span class="title">성향관리 > <b>성향 조회</b></span>
	</div>
	<div class="main_content">
		<div class="exp_product">각 행의 데이터를 수정하거나 입력,삭제시 상태컬럼의 변화를
				확인하고,저장 버튼 클릭시 서버로 전송되는 데이터를 확인한다.</div>
	    <div class="exp_product">
			<form name='frm'>
				<div style='padding-bottom:10px;'></div>
				<div id='ib-container'></div>
			</form>
		</div>
	</div>
</body>
</html>