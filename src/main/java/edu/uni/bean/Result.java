package edu.uni.bean;

import com.fasterxml.jackson.core.JsonProcessingException;
import edu.uni.utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;

public class Result extends HashMap<String, Object>{


    private Result(int code, String msg) {    
        this.put("code", code);    this.put("msg", msg);   
    }

    public static Result build(ResultType resultType){
        return new Result(resultType.getCODE(), resultType.getMSG());
    }

    public static Result build(ResultType resultType, String msg){
        return new Result(resultType.getCODE(), msg);
    }


    public Result appendData(String key, Object value){
        this.put(key, value);
        return this;
    }

    public String convertIntoJSON() throws JsonProcessingException {
        return JsonUtils.obj2String(this);
    }

    public static Result TranslateJSON(String json) throws IOException {
        return JsonUtils.string2Obj(json, Result.class);
    }
}