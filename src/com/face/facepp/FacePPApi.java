package com.face.facepp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.face.config.Constant;
import com.face.facepp.entities.Center;
import com.face.facepp.entities.Detectresult;
import com.face.facepp.entities.Position;
import com.face.facepp.entities.recognition.compare.Similarity;
import com.face.protocol.entiy.Face_Rect;
import com.face.tool.json.JsonUtil;
import com.face.util.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

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
		String ret= HttpRequest.sendGet(httpUrl, param);
		if(ret!=null&&!"".equals(ret)){
			try {
				//Detectresult 由http://api.stay4it.com/json/index.html生成 
				//其中的@JsonProperty注解字段全为null或0 需改成@SerializedName 
				result=JsonUtil.fromJson(ret,Detectresult.class );
			} catch (Exception e) {
				//暂不处理 statusCode 非200
				e.printStackTrace();
			}
		}
		//{    "face": [        {            "attribute": {                "age": {                    "range": 7,                     "value": 45                },                 "gender": {                    "confidence": 99.9995,                     "value": "Male"                },                 "glass": {                    "confidence": 99.5741,                     "value": "Normal"                },                 "pose": {                    "pitch_angle": {                        "value": 0.008547                    },                     "roll_angle": {                        "value": -2.16337                    },                     "yaw_angle": {                        "value": -10.435651                    }                },                 "race": {                    "confidence": 99.5124,                     "value": "Asian"                },                 "smiling": {                    "value": 10.6257                }            },             "face_id": "fe420e7446dfae0136e6cdff5cf9fb7b",             "position": {                "center": {                    "x": 46.774194,                     "y": 42.666667                },                 "eye_left": {                    "x": 36.364516,                     "y": 36.805                },                 "eye_right": {                    "x": 57.420046,                     "y": 36.229667                },                 "height": 29.333333,                 "mouth_left": {                    "x": 39.388479,                     "y": 53.237167                },                 "mouth_right": {                    "x": 53.908065,                     "y": 53.220167                },                 "nose": {                    "x": 44.659447,                     "y": 44.3885                },                 "width": 40.552995            },             "tag": ""        }    ],     "img_height": 1600,     "img_id": "032e5e6306986d615500cc6581f270d2",     "img_width": 1159,     "session_id": "c2f6716cbdaf43ada3112315e41f2998",     "url": "http://dojochinaextjs.imwork.net/SSM/uploadface/dc4578704a224ee9b09769cfa7383060.jpg"}
		return result;
	} 
	/**
	 * 计算两个Face的相似性以及五官相似度
	 * @param face_id1
	 * @param face_id2
	 * @param request
	 * @param response
	 * @return
	 */
	public static Similarity recognitionCompare(String face_id1,String face_id2){
		Similarity similarity=null;
		String httpUrl="https://apicn.faceplusplus.com/v2/recognition/compare";
		String param="api_secret="+Constant.api_secret_facepp+"&api_key="+Constant.api_key_facepp+
				"&face_id2="+
				face_id2
				+"&face_id1="+
				face_id1;
		
		String ret= HttpRequest.sendGet(httpUrl, param);
		if(ret!=null&&!"".equals(ret)){
			try {
				similarity=JsonUtil.fromJson(ret,Similarity.class );
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return similarity;
		
	} 
	public static String recognitionCompare2(String face_id1,String face_id2){
		String httpUrl="https://apicn.faceplusplus.com/v2/recognition/compare";
		String param="api_secret="+Constant.api_secret_facepp+"&api_key="+Constant.api_key_facepp+
				"&face_id2="+
				face_id2
				+"&face_id1="+
				face_id1;
		
		String ret= HttpRequest.sendGet(httpUrl, param);
		
		return ret;
		
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
		int face_width = (int) position.getWidth();//4 0~100之间的实数，表示检出的脸的宽度在图片中百分比
		int face_height = (int) position.getHeight();//50~100之间的实数，表示检出的脸的宽度在图片中百分比
		float left=(float) (center.getX()-(face_width*1.0f/2));
		float top=(float) (center.getY()-(face_height*1.0f/2));
		return new Face_Rect((int)(left*img_width/100),
				(int)(top*img_height/100), (int)(face_width*img_width*1.0f/100),
				(int)(face_height*img_height*1.0f/100));
		
	}
	
	

}
