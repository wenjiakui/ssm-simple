package com.wjk.web.ssm.utils;


import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ResultUtil <T>{
    private int status;
    private String msg;
    private T data ;

    public static ResultUtil success(JSON jsonObject){
        ResultUtil<JSON> resultUtil = new ResultUtil<JSON>();
        resultUtil.setMsg("success");
        resultUtil.setStatus(200);
        if(jsonObject instanceof JSONArray){
            resultUtil.setData((JSONArray)jsonObject);
        }else if(jsonObject instanceof JSONObject){
            resultUtil.setData((JSONObject)jsonObject);
        }
        return resultUtil;
    }
    public static ResultUtil success(JSON jsonObject,String msg){
        ResultUtil<JSON> resultUtil = new ResultUtil<JSON>();
        resultUtil.setMsg(msg);
        resultUtil.setStatus(200);
        if(jsonObject instanceof JSONArray){
            resultUtil.setData((JSONArray)jsonObject);
        }else if(jsonObject instanceof JSONObject){
            resultUtil.setData((JSONObject)jsonObject);
        }
        return resultUtil;
    }
    public static ResultUtil failed(JSONObject object){
        ResultUtil<JSONObject> resultUtil = new ResultUtil<JSONObject>();
        resultUtil.setMsg("failed");
        resultUtil.setStatus(204);
        resultUtil.setData(object);
        return resultUtil;
    }
    public static ResultUtil failed(JSONObject object,String msg){
        ResultUtil<JSONObject> resultUtil = new ResultUtil<JSONObject>();
        resultUtil.setMsg(msg);
        resultUtil.setStatus(204);
        resultUtil.setData(object);
        return resultUtil;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
