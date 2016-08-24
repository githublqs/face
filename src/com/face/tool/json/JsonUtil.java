package com.face.tool.json;


import java.io.StringWriter;
import java.lang.reflect.Type;

import com.face.gson.NullStringToEmptyAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonWriter;
/**
 * Java对象和JSON字符串相互转化工具类
 * @author lqs
 * 
 */

//用到了
//com.springsource.org.codehaus.jackson.mapper-1.4.2.jar
public final class JsonUtil {
	private JsonUtil(){}
    /**
     * 对象转换成json字符串
     * @param obj 
     * @return 
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    /**
     * json字符串转成对象
     * @param str  
     * @param type
     * @return 
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson  = new GsonBuilder().create();
        //然后用上面一行写的gson来序列化和反序列化实体类type
         T t=gson.fromJson(str, type);
         return t;
    }

    /**
     * json字符串转成对象
     * @param str  
     * @param type 
     * @return 
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson  = new GsonBuilder().create();
      //然后用上面一行写的gson来序列化和反序列化实体类type
       T t=gson.fromJson(str, type);
        return t;
    }

}
