<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JavaScript 图片切割效果（带拖放、缩放效果）</title>
<script>
	var path = "${request.contextPath}";
</script>
<script type="text/javascript"
	src="${request.contextPath}/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${request.contextPath}/static/js/mf.ajaxfileupload.js"></script>
<script type="text/javascript"
	src="${request.contextPath}/static/js/mf.upload.js"></script>
<style type="text/css">
#rRight, #rDown {
	position: absolute;
	background: #7B68EE;
	width: 5px;
	height: 5px;
	z-index: 500;
	font-size: 0;
}
#dragDiv {
	border: 2px solid #7B68EE;
}
.img_a {
	margin: 0px auto;
	width: 550px;
	height: 420px;
	overflow: hidden;
	_position: relative;
}

.img_a .img_b {
	margin-right: 130px;
	position: relative;
	width: 420px;
	height: 420px;
	_position: absolute;
	_top: 0px;
	_left: 0px;
}

.img_a .img_c {
	float: right;
	position: relative;
	width: 120px;
	overflow: hidden;
	height: 420px;
	_position: absolute;
	_top: 0px;
	_left: 430px;
}


.img_a .img_c_1, .img_a .img_c_2 {
	position: relative;
	background-color: #F5F5F5;
	overflow: hidden;
}

.img_a .img_c p {
	margin: 0px;
	padding: 0px;
	padding-bottom: 10px;
	line-height: 25px;
	color: #999;
	font-size: 12px;
}
.img_a .img_c_1 {
	width: 120px;
	height: 120px;
}

.img_a .img_c_2 {
	width: 50px;
	height: 50px;
}
* {
	margin: 0;
	padding: 0;
}
.box {
	position: relative;
	float: left;
}

input.uploadFile {
	position: absolute;
	right: 0px;
	top: 0px;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
	width: 276px;
	height: 36px;
	overflow: hidden;
}

.link {
	float: left;
	display: inline-block;
	padding: 4px 16px;
	color: #fff;
	font: 14px "Microsoft YaHei", Verdana, Geneva, sans-serif;
	cursor: pointer;
	background-color: #0099ff;
	line-height: 28px;
	text-decoration: none;
}
</style>
<script type="text/javascript"
	src="${request.contextPath}/static/js/img.js"></script>
<script>
	var ic = null;
	function Create() {
		if (ic == null) {
			alert("请先上传图片");
			return;
		}
		var p = ic.Url, x = ic.Drag.offsetLeft, y = ic.Drag.offsetTop,

		w = ic.Drag.offsetWidth, h = ic.Drag.offsetHeight,
		pw = ic.Width, ph = ic.Height;
		
		$.post("${request.contextPath}/img/createServlet",{p:p,x:x,y:y,w:w,h:h,pw:pw,ph:ph},function(result){
		   alert(result.message);
	  	});
		
	}
</script>
</head>
<body style="background-color: #111;">
	<div class="box">
		<a href="javascript:void(0);" class="link">上传图片</a> <input type="file"
			accept="image/*" name="file" id="file" class="uploadFile"
			name="upload" onchange="mf.upload.imageUp('file');" />
	</div>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" class="link" type="button" onclick="Create()" >确认</a>
	<div class="img_a">
		<div class="img_c">
			<div id="maxView" class="img_c_1" style="width: 120px; height: 120px; border-radius: 50%; behavior: url(border-radius.htc);">
			</div>
			<p>宽度*高度=120*120</p>
			<div class="img_c_2" id="viewDiv" style="width: 50px; height: 50px; border-radius: 50%; behavior: url(border-radius.htc);">
			</div>
			<p>宽度*高度=50*50</p>
		</div>
		<div class="img_b" id="bgDiv" >
			<div id="dragDiv" style="border-radius: 50%; display: none;">
				<span id="rRight" style="right: 0; top: 50%;"> </span> 
				<span id="rDown" style="bottom: 0; left: 50%;"> </span>
			</div>
		</div>
	</div>
</body>
</html>