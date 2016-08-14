<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>人脸识别注册</title>
	<meta charset="utf-8" />
    <script src="js/jquery-1.11.0.min.js"></script>
    <script>
         $(function () { 
         //base64转换为原始二进制数据保存在一个字符串
            function dataURItoBlob(dataURI) {
                // base64转换为原始二进制数据保存在一个字符串
                var byteString
                    , mimestring;

                if (dataURI.split(',')[0].indexOf('base64') !== -1) {
                    byteString = atob(dataURI.split(',')[1]);
                } else {
                    byteString = decodeURI(dataURI.split(',')[1]);
                }

                mimestring = dataURI.split(',')[0].split(':')[1].split(';')[0];

                var content = new Array();
                for (var i = 0; i < byteString.length; i++) {
                    content[i] = byteString.charCodeAt(i);
                }

                return new Blob([new Uint8Array(content)], { type: mimestring });
            }
              var video = document.getElementById('webcam');
             var context = document.getElementById("canvas").getContext("2d");
            context.drawImage(video, 0, 0);//取得此刻所拍的照片
            var image = context.canvas.toDataURL("image/jpeg");//取得图片
         	var fdata = new FormData();
             fdata.append("img", dataURItoBlob(image));
         		alert();
                    $.ajax({
                        url: "urlImg",//请求路径
                        type: "post",//请求方法
                        async: false,//设置同步请求
                        dataType: "json",
                        data: fdata,
                        processData: false, //processData设置为false。因为data值是FormData对象，不需要对数据做处理。
                        contentType: false,//	contentType设置为false。因为是由<form>表单构造的FormData对象，且已经声明了
                        
                        success: function (result) {
                        },
                        error: function () {
                        }
                    });
             });
    </script>
</head>
<body>
<video id="webcam"></video>
    <div><canvas id="canvas" width="640" height="480"></canvas></div>
</body>
</html>
