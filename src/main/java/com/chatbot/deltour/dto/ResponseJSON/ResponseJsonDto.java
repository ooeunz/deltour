package com.chatbot.deltour.dto.ResponseJSON;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseJsonDto {

    private int code;
    private JsonDto json = new JsonDto();

    public void successCode(String message, Map<String, Object> data) {

        this.code = 200;

        this.json.setStatus(true);
        this.json.setMessage(message);
        this.json.setData(new HashMap<String, Object>());
    }

    public void failure(int code, String message) {

        this.code = code;

        this.json.setStatus(false);
        this.json.setMessage(message);
        this.json.setData(null);
    }

    public Map<String, Object> convertObjectToMap(Object obj){

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (int i=0; i < fields.length; i++){
            fields[i].setAccessible(true);
            try{
                map.put(fields[i].getName(), fields[i].get(obj));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return map;
    }
}
