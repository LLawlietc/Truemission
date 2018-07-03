package com.users.usersactivity.Json;

/**
 * Created by cheng on 2018/4/12.
 */

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Json结果解析类
 */
public class JsonParser {

    public static String parseIatResult(String json) {
        StringBuffer ret = new StringBuffer() ;
        //String类是字符串常量，不可更改，StringBuffer是字符串变量，可修改扩充
        try {
            //读取json中的字符
            JSONTokener tokener = new JSONTokener(json) ;
            //无序的键/值对集合
            JSONObject joResult = new JSONObject(tokener) ;
            JSONArray words = joResult.getJSONArray("ws" );
            for (int i = 0; i < words.length(); i++) {
                // 转写结果词，默认使用第一个结果
                JSONArray items = words.getJSONObject(i).getJSONArray("cw" );
                JSONObject obj = items.getJSONObject(0 );
                ret.append(obj.getString("w" ));
                //StringBuffer append向字符串缓冲区追加元素
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret.toString();
    }

//    public static String parseGrammarResult(String json) {
//        StringBuffer ret = new StringBuffer() ;
//        try {
//            JSONTokener tokener = new JSONTokener(json) ;
//            JSONObject joResult = new JSONObject(tokener) ;
//
//            JSONArray words = joResult.getJSONArray("ws" );
//            for (int i = 0; i < words.length(); i++) {
//                JSONArray items = words.getJSONObject(i).getJSONArray("cw" );
//                for (int j = 0; j < items.length() ; j++)
//                {
//                    JSONObject obj = items.getJSONObject(j);
//                    if (obj.getString("w").contains( "nomatch"))
//                    {
//                        ret.append( "没有匹配结果.") ;
//                        return ret.toString();
//                    }
//                    ret.append( "【结果】" + obj.getString("w" ));
//                    ret.append("【置信度】 " + obj.getInt("sc" ));
//                    ret.append("\n ");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            ret.append(" 没有匹配结果 .");
//        }
//        return ret.toString();
//    }
//
//    public static String parseLocalGrammarResult(String json) {
//        StringBuffer ret = new StringBuffer() ;
//        try {
//            JSONTokener tokener = new JSONTokener(json) ;
//            JSONObject joResult = new JSONObject(tokener) ;
//
//            JSONArray words = joResult.getJSONArray("ws" );
//            for (int i = 0; i < words.length(); i++) {
//                JSONArray items = words.getJSONObject(i).getJSONArray("cw" );
//                for (int j = 0; j < items.length() ; j++)
//                {
//                    JSONObject obj = items.getJSONObject(j);
//                    if (obj.getString("w").contains( "nomatch"))
//                    {
//                        ret.append( "没有匹配结果.") ;
//                        return ret.toString();
//                    }
//                    ret.append( "【结果】" + obj.getString("w" ));
//                    ret.append("\n ");
//                }
//            }
//            ret.append("【置信度】 " + joResult.optInt("sc" ));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            ret.append(" 没有匹配结果 .");
//        }
//        return ret.toString();
//    }
}
