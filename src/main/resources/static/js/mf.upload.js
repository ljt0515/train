/**
 * @fileOverview 文件上传
 * @update 2014-6-26
 * @author yaorui 
 * @revision 1.0
 * @param id(input框 id) showId previewId(预览id)
 */

if (typeof window.mf == "undefined") {
	window.mf = {};
}
if (typeof window.mf.upload == "undefined") {
	window.mf.upload = {};
}

(function(package){
	package.imageUp = function(id,callback){
		var inputId;
		if(typeof id =="string"){
			inputId = jQuery("#"+id);
		}else if(typeof id == "object"){
			inputId = id;
		}else{
			return false;	
		}
		var func = {};
		if(typeof callback == "function"){
			func = callback;
			
		}
		var url = path+'/img/uploadImg';
		if(typeof filePath != "undefined"){
			url += '?ut='+filePath+'&r'+Math.random();
		}else{
			url += '?r'+Math.random();
		}
		
		if(inputId.val()==''){
			alert("请先选择需要上传的图片");
		}else if(_isImage(id)){
			jQuery.ajaxFileUpload({
			     url:url, 
			     secureuri:false,
			     fileElementId:id,
			     dataType: 'json',
			     success: function (data, status) {
			    	 var state = data.state;
			    	 var src = data.url;
			    	 if(state=='SUCCESS') {
			    		 ic = new ImgCropper("bgDiv", "dragDiv", src, 500, 400, {
			 				dragTop: 50,
			 				dragLeft: 50,
			 				Right: "rRight",
			 				Left: "rLeft",
			 				Up: "rUp",
			 				Down: "rDown",
			 				View: "viewDiv",
			 				viewSize: 50,
			 				MaxView: "maxView",
			 				maxViewSize: 100
			 			})
			    	 } else {
			    		 alert(state);
			    	 }
			     },
			     error: function (data, status, e) {
			   		alert(e);
			     }
		  	});
		}else{
			alert("请选择正确的图片文件，后缀只能是BMP,JPG,JPEG,PNG,GIF");
		}
	};
	var _isImage = function(imgId){	
		var imgName = $("#"+imgId).val().toUpperCase();
		if(imgName.indexOf(".BMP")!=-1 || imgName.indexOf(".JPG")!=-1
			|| imgName.indexOf(".JPEG")!=-1 || imgName.indexOf(".PNG")!=-1
			|| imgName.indexOf(".GIF")!=-1 || imgName.indexOf(".PDF")!=-1
			|| imgName.indexOf(".DOCX")!=-1 || imgName.indexOf(".DOC")!=-1
			|| imgName.indexOf(".WPS")!=-1  || imgName.indexOf(".XLS")!=-1
			|| imgName.indexOf(".XLSX")!=-1) {
		return true;
	}
	return false;
	};	
})(mf.upload);