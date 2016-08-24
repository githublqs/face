package com.face.facepp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.face.config.Constant;
import com.face.facepp.entities.Center;
import com.face.facepp.entities.Detectresult;
import com.face.facepp.entities.Position;
import com.face.protocol.entiy.Face_Rect;
import com.face.tool.json.JsonUtil;
import com.face.util.HttpRequest;
import com.google.gson.Gson;

public class FacePPApi {
	private FacePPApi(){
		
	}
	//JSON to javabean
	//http://api.stay4it.com/json/index.html
	/**
	 *人脸检测与分析
	 *检测给定图片(Image)中的所有人脸(Face)的位置和相应的面部属性
	 *	目前面部属性包括性别(gender), 年龄(age), 种族(race), 微笑程度(smiling), 眼镜(glass)和姿势(pose)
	 *	若结果的face_id没有被加入任何faceset/person之中，则在72小时之后过期被自动清除。
	 * @param fileName 
	 */
	public static Detectresult faceDet(HttpServletRequest request,HttpServletResponse response,String fileName){
		Detectresult result=null;
		String httpUrl="http://apicn.faceplusplus.com/v2/detection/detect";
		String url=getImageUrl(request,fileName);
		String param="api_key="+Constant.api_key_facepp+"&api_secret="+Constant.api_secret_facepp+"&url="+url+"&attribute=glass,pose,gender,age,race,smiling";
		String ret= HttpRequest.sendGet(httpUrl, param);//{    "face": [        {            "attribute": {                "age": {                    "range": 5,                     "value": 18                },                 "gender": {                    "confidence": 99.9985,                     "value": "Female"                },                 "glass": {                    "confidence": 99.9697,                     "value": "None"                },                 "pose": {                    "pitch_angle": {                        "value": 1.9e-05                    },                     "roll_angle": {                        "value": 13.1848                    },                     "yaw_angle": {                        "value": -13.26847                    }                },                 "race": {                    "confidence": 99.0657,                     "value": "Asian"                },                 "smiling": {                    "value": 97.1243                }            },             "face_id": "82578666b8c302fc50c28325bfc761d4",             "position": {                "center": {                    "x": 50.0,                     "y": 55.0                },                 "eye_left": {                    "x": 38.628583,                     "y": 42.397333                },                 "eye_right": {                    "x": 62.459833,                     "y": 47.98025                },                 "height": 45.0,                 "mouth_left": {                    "x": 37.714333,                     "y": 66.359167                },                 "mouth_right": {                    "x": 57.593667,                     "y": 69.559167                },                 "nose": {                    "x": 46.387083,                     "y": 58.12                },                 "width": 45.0            },             "tag": ""        }    ],     "img_height": 120,     "img_id": "9d6d99094eea972b7c8c65e42bfd464f",     "img_width": 120,     "session_id": "4b8161c4db794f9ebd8be7e702ff9eee",     "url": "http://dojochinaextjs.imwork.net/SSM/uploadface/f81732766ab7bee9ae8d71dd7f4bb9a3.jpg"}
		if(ret!=null&&!"".equals(ret)){
			try {
				result=JsonUtil.fromJson(ret,Detectresult.class );
			} catch (Exception e) {
				//暂不处理 statusCode 非200
				e.printStackTrace();
			}
		}
		return result;
	} 
	/**
	 * 从 uploadface文件加下通过图片名称
	 * 拼接供face++api访问的url
	 * @param uploadImg 图片名称
	 * @return
	 */
	private static String getImageUrl(HttpServletRequest request,String uploadImg){
		String path = request.getContextPath();  
		String basePath = request.getScheme()+"://"+request.getServerName()+path+"/"; //+":"+request.getServerPort()
		String url=basePath+"uploadface/"+uploadImg;
		return url;
	}
	/**
	 * //根据上面5个值计算人脸相对于图片左上角的矩形区域坐标
	 * @param img_width
	 * @param img_height
	 * @param position
	 */
	public static Face_Rect newFaceRect(int img_width, int img_height,
			Position position) {
		Center center=position.getCenter();//3 检出的人脸框的中心点坐标, x & y 坐标分别表示在图片中的宽度和高度的百分比 (0~100之间的实数)
		int face_width = position.getWidth();//4 0~100之间的实数，表示检出的脸的宽度在图片中百分比
		int face_height = position.getHeight();//50~100之间的实数，表示检出的脸的宽度在图片中百分比
		float left=center.getX()-(face_width*1.0f/2);
		float top=center.getY()-(face_height*1.0f/2);
		return new Face_Rect((int)(left*img_width/100),
				(int)(top*img_height/100), (int)(face_width*img_width*1.0f/100),
				(int)(face_height*img_height*1.0f/100));
		
	}

}
