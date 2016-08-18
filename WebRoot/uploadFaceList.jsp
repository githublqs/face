<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'uploadFaceList.jsp' starting page</title>
	
    <link rel="stylesheet" href="css/bootstrap-responsive.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/qunit-1.11.0.css">
    <script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>


  </head>
  
  <body>
  <script src="js/bootstrap-paginator.js"></script>
<script src="js/qunit-1.11.0.js"></script>
  
  <script>
  $(function () {
    $.ajax({
      url: "faceRec/uploadFaceList",
      datatype: 'json',
      type: "post",
       contentType: "application/json; charset=utf-8",
       data:JSON.stringify({                  
				    "pageNo":1,"pageSize":20
				}),
      success: function (data) {
        if (data != null) {
         $("#list").empty();  
         $("#list").append('<table id="data_table" class="table table-striped">');
          $("#list").append('<thead>');
          $("#list").append('<tr>');
          $("#list").append('<th>Id</th>');
          $("#list").append('<th>缩略图</th>');
          $("#list").append('<th>日期</th>');
          $("#list").append('</tr>');
          $("#list").append('</thead>');
          $("#list").append('<tbody>');
          $.each(eval("(" + data + ")").list, function (index, item) { //遍历返回的json
            $("#list").append('<tr>');
            $("#list").append('<td>' + item.id + '</td>');
           /*  $("#list").append('<td>' + item.thumbnailImgFileName + '</td>'); */
           $("#list").append("<td><img width=100 height=100 src=\"uploadface/thumb/"+item.thumbnailImgFileName+"\"  alt=\""+item.thumbnailImgFileName+"\" /></td>");
           $("#list").append('<td>'+item.date+'</td>');
            $("#list").append('</tr>');
          });
           $("#list").append('</tbody>');
           $("#list").append('</table>');
          var pageCount = eval("(" + data + ")").pageCount; //取到pageCount的值(把返回数据转成object类型)
          var currentPage = eval("(" + data + ")").CurrentPage; //得到urrentPage
          var options = {
            bootstrapMajorVersion: 2, //版本
            currentPage: currentPage, //当前页数
            totalPages: pageCount, //总页数
            itemTexts: function (type, page, current) {
              switch (type) {
                case "first":
                  return "首页";
                case "prev":
                  return "上一页";
                case "next":
                  return "下一页";
                case "last":
                  return "末页";
                case "page":
                  return page;
              }
            },//点击事件，用于通过Ajax来刷新整个list列表
            onPageClicked: function (event, originalEvent, type, page) {
              $.ajax({
                //url: "/OA/Setting/GetDate?id=" + page,
                url: "faceRec/uploadFaceList",
                type: "post",
                datatype: 'json',
                contentType: "application/json; charset=utf-8",
                data:JSON.stringify({                  
				    "pageNo":page,"pageSize":20
				}),
                success: function (data1) {
                  if (data1 != null) {
                  $("#list").empty();  
                  $("#list").append('<table id="data_table" class="table table-striped">');
		          $("#list").append('<thead>');
		          $("#list").append('<tr>');
		          $("#list").append('<th>Id</th>');
		          $("#list").append('<th>缩略图</th>');
		          $("#list").append('<th>日期</th>');
		          $("#list").append('</tr>');
		          $("#list").append('</thead>');
		          $("#list").append('<tbody>');
                    $.each(eval("(" + data1 + ")").list, function (index, item) { //遍历返回的json
                       $("#list").append('<tr>');
			            $("#list").append('<td>' + item.id + '</td>');
			            $("#list").append("<td><img width=100 height=100 src=\"uploadface/thumb/"+item.thumbnailImgFileName+"\"  alt=\""+item.thumbnailImgFileName+"\" /></td>");
			            $("#list").append('<td>'+item.date+'</td>');
			            $("#list").append('</tr>');
                    });
                    $("#list").append('</tbody>');
                    $("#list").append('</table>');
                  }
                }
              });
            }
          };
          $('#example').bootstrapPaginator(options);
        }
      }
    });
    	var videoPrepared=false;
       //初始化变量
            var context = document.getElementById("canvas").getContext("2d");
            navigator.getUserMedia = navigator.getUserMedia ||
                         navigator.webkitGetUserMedia ||
                         navigator.mozGetUserMedia;
            if (navigator.getUserMedia) {
             navigator.getUserMedia({ audio: true, video:true },
			      function(stream) {
			     
			         var video = document.querySelector('video');
			         video.src = window.URL.createObjectURL(stream);
			         video.onloadedmetadata = function(e) {
			         	
			         	video.play();
			         	videoPrepared=true;
			         	
			         	
			         	$("#faceDet").click(function(){
			         	
						   context.drawImage(video, 0, 0);//取得此刻所拍的照片
		                    var image = context.canvas.toDataURL("image/jpeg");//取得图片
		                    $.ajax({
		                        url: "faceRec/faceDetBase64",//请求路径
		                        type: "post",//请求方法
		                         dataType: "json",
		                        contentType: "application/json; charset=utf-8",
		                        data:JSON.stringify({                  
								     "id": "12345678",
									  "serviceType": 1,
									  "img": "",
									  "img_type": "jpeg"
								}),
		                        success: function (result) {
		                           if(result){  
			                          var dataObj= result;
			                           if(dataObj){
			                           			if(dataObj.errorcode==0){
			                           				if(dataObj.face_num>0){
			                           				
			                           					for (var i=0;i<dataObj.face_rect.length;i++)
														{
															var face_rect_obj= dataObj.face_rect[i];
															context.beginPath();
															context.lineWidth=2;
															context.strokeStyle='#00ff00';
															context.strokeRect(face_rect_obj.left,face_rect_obj.top,face_rect_obj.width,face_rect_obj.height);
															context.stroke();
														}
			                           				}
			                           			}
			                           		
			                           } 
		                           }
		                        },
		                        error: function () {
		                            alert("很抱歉，传递数据到服务器出错，这是服务器的原因，所以您可以等待，过段时间，获联系负责人");
		                        }
		                    });
						});
			         };
			      },
			      function(err) {
			         console.log("The following error occurred: " + err.name);
			      }
			  );
            }
            var img0dataurl;
            document.getElementById('file').addEventListener( 'change' ,
				ProcessFile , false );
				function ProcessFile( e ) { 
			var file = document.getElementById('file').files[0];
			if (file) {
					var reader = new FileReader();
						reader.onload = function ( event ) { 
								var txt = event.target.result;//dataurl
								img0dataurl=txt;
					document.getElementById("result").src = txt;
					};
				}
				reader.readAsDataURL(file);
			}//base64转换为原始二进制数据保存在一个字符串
            function dataURItoBlob(dataURI) {
                // base64转换为原始二进制数据保存在一个字符串
                var byteString
                    , mimestring

                if (dataURI.split(',')[0].indexOf('base64') !== -1) {
                    byteString = atob(dataURI.split(',')[1])
                } else {
                    byteString = decodeURI(dataURI.split(',')[1])
                }

                mimestring = dataURI.split(',')[0].split(':')[1].split(';')[0]

                var content = new Array();
                for (var i = 0; i < byteString.length; i++) {
                    content[i] = byteString.charCodeAt(i)
                }

                return new Blob([new Uint8Array(content)], { type: mimestring });
            }
            $("#computeSim").click(function(){
	            if(videoPrepared==true&&!($.isEmptyObject(img0dataurl))){
		            //使用ajax发送
					var fdata = new FormData();
					fdata.append("img0", dataURItoBlob(img0dataurl));
				   	context.drawImage(video, 0, 0);//取得此刻所拍的照片
                    var image = context.canvas.toDataURL("image/jpeg");//取得图片
					fdata.append("img", dataURItoBlob(image));
					 $.ajax({
                        url: "urlImg",//请求路径
                        type: "post",//请求方法
                        dataType: "json",
                        processData: false, //processData设置为false。因为data值是FormData对象，不需要对数据做处理。
                        contentType: false,//	contentType设置为false。因为是由<form>表单构造的FormData对象，且已经声明了
                        data: fdata,
                        success: function (result) {
                            if (result.matching == "yes") {
                                alert("相似度够高的");
                            }
                            else {
                            alert("相似度不够高");
                            }
                        },
                        error: function () {
                            alert("很抱歉，传递数据到服务器出错，这是服务器的原因，所以您可以等待，过段时间，获联系负责人");
                        }
                    });
	            
            	}
            });
			
  });
</script>
    <div>
        <label>上传照片列表</label>
       
        <div id="list"></div>

        <div id="example"></div>
       
         <label>人脸检测</label>
         
          <table class="table">
          	<tr>
          		<td><video id="video"></video></td>
          		<td><canvas id="canvas" width="640" height="480"></canvas></td>
          	</tr>
          </table>
          
          <button id="faceDet" type="button" class="btn btn-default">人脸检测</button>
           <div>相似度</div>
           	请选取一个图像文件: <input type = "file" id = "file" name = "file" />
   			<img id = "result"> 
			<button id="computeSim" type="button" class="btn btn-default">计算相似度</button>

           
    </div>
  </body>
</html>
