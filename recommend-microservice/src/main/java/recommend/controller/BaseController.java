package recommend.controller;

import java.util.*;

public class BaseController {
    public Map<String, Object> handleResponseData(Integer code, Object data, String msg){
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("code", code);
        map.put("data", data);
        map.put("message", msg);
        return map;
    }

    public Map<String, Object> handleResponseData(Integer code, Object data){
        Map<String , Object> map = new HashMap<String , Object>();
        map.put("code", code);
        map.put("data", data);
        map.put("message", "ok");
        return map;
    }
}
