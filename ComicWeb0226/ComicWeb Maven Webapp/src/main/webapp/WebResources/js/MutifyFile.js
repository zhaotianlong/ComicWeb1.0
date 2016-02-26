function GetUrl(){
	alert( document.getElementById("url").getAttribute("value"));
	return document.getElementById("url").getAttribute("value");
}
function GetPartId(){
	alert( document.getElementById("comicPartId").getAttribute("value"));
	return document.getElementById("comicPartId").getAttribute("value");
}
function GetDomId(id) {
					return document.getElementById(id);
				}
				function Output(msg) {
					var m = GetDomId("messages");
					m.innerHTML = msg + m.innerHTML;
				}
				var oDragWrap =GetDomId('uploadready');
//拖进
oDragWrap.addEventListener('dragenter', function(e) {
	　e.preventDefault();
	GetDomId('dropbox').className='box-shadow';
}, false);

//拖离
oDragWrap.addEventListener('dragleave', function(e) {
	GetDomId('dropbox').className='';
}, false);

//拖来拖去 
oDragWrap.addEventListener('dragover', function(e) {
	　e.preventDefault();
}, false);

//扔下
oDragWrap.addEventListener('drop', function(e) {
	e.stopPropagation();
	e.preventDefault();
	var files = e.dataTransfer.files;
	dropHandler(files)

}, false);

var arrFiles=[];
var dropHandler = function(files) {
	GetDomId('pageContent').className='';
	GetDomId('upload_btn').style.display='block';
	for(var f=0;f<files.length;f++){

		if(files[f].type.indexOf("image")==-1){
			alert('文件' + files[f].name + '不是图片。')
		}
		else{
			arrFiles.push(files[f]);
			//console.log('files['+f+'].index'+files[f].index)
		}
	}
	GetDomId('pageContent').innerHTML='';
	for(var i=0; i<arrFiles.length;i++){
		if(arrFiles[i].index!=='no'){
			arrFiles[i].index=parseInt(Math.random() * 9000000);
		}
	}
	for(var i=0; i<arrFiles.length;i++){
		if(arrFiles[i].index!=='no'){
			viewfile(arrFiles[i])
		}
	}

  //调试代码
  for (var i=0; i<arrFiles.length;i++){
  	console.log('新数组的索引'+'arrFiles'+i+':'+arrFiles[i].index)
  }	
  GetDomId('upload_btn').addEventListener('click', function(e) {
  	　e.preventDefault();
GetDomId('upload_btn').style.display='none';//隐藏上传按钮
for(var s=0; s<arrFiles.length;s++){
	if(arrFiles[s].index!=='no'){
		ajaxUpload(arrFiles[s])  
	}
}
arrFiles=null;
arrFiles=[];	

}, false);	
}  

//放入完毕
function ajaxUpload(files){
	var xhr=new XMLHttpRequest();
	xhr.upload.addEventListener('progress',function(e){
		var pc=parseInt(e.loaded/e.total*100);
		if(GetDomId('progress'+files.index)){
			GetDomId('progress'+files.index).value=pc;}
			console.log(GetDomId('progress'+files.index))
		},false)		 
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState == 4) {
			var oldli=GetDomId('imgViewList'+files.index);	
			var imgsrc='uploads/'+files.name;
			var	uploadLi=document.createElement('li');	 
			var   html  = '';
			html += '<div  onclick="insertPic(this)" class="img_and_info">';
			html += '<img  border="0" src="'+imgsrc+'">';
			html += '</div>';
			html += ' <p class="imginfo" contenteditable="true">上传完毕</p>';
			uploadLi.innerHTML=html;
			var oldLi=GetDomId('imgViewList'+files.index);
			GetDomId('pageContent').removeChild(oldLi);
			GetDomId('uploaded').appendChild(uploadLi);   
			var bbscode='';
			bbscode+='[img]/000/uploads/'+files.name+'[/img]<br/>'
			GetDomId('uploadView').innerHTML+=bbscode;
		}
	};
	var oMyForm = new FormData();
	oMyForm.append("partId", GetPartId());
	oMyForm.append("fileImage",files);
	
	 xhr.open("POST",GetUrl()+'/author/mutifyfile/upload', true);
	 xhr.send(oMyForm); 
}

// ajaxUpload();
function viewfile(file){
	if(window.webkitURL){
		
		var	imgsrc=window.webkitURL.createObjectURL(file)
		createView(file,imgsrc)
		
	}
	else if(window.URL){
		
		var	imgsrc=window.URL.createObjectURL(file)

		createView(file,imgsrc)
	}	

	else{



		var reader = new FileReader();
		　reader.onload = function(e) {

			var	imgsrc=e.target.result;

			createView(file,imgsrc)
		　}
		reader.readAsDataURL(file);
	}


}
	//创建预览dom
	function createView(file,imgsrc){
		
		var html = "";
		html += '<li id="imgViewList'+file.index+'">';
		html += '<div class="img_and_info">';
		html += '<img  border="0" src="'+imgsrc+'">';
		html += '</div>';
		html += '<span onclick="del(\'imgViewList'+file.index+'\')" class="upload_delete" title="删除">删除</span>'
		html += '<progress value="0" max="100" id="progress'+file.index+'"></progress>'; 
		html += '</li>';
		GetDomId('pageContent').innerHTML+=html;	
		
	}
Array.prototype.del=function(n) {　//n表示第几项，从0开始算起。
　if(n<0){　//如果n<0，则不进行任何操作。
	　　return this;}
	　else{
		　　return this.slice(0,n).concat(this.slice(n+1,this.length));
	}
}

//删除预览
function del(obj){
	   var n=parseInt(obj.substring(11));//获取数组索引
	   console.log('数组长度为：'+arrFiles.length);
	   for(var s=0;s<arrFiles.length; s++){

	   	if(arrFiles[s].index==n){

	   		console.log(arrFiles[s].index='no');

	   	}
	   }
	   GetDomId(obj).parentNode.removeChild(GetDomId(obj))
	   console.log('目前数组为：'+arrFiles);

	}