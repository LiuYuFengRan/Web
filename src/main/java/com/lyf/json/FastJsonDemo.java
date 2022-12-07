package com.lyf.json;

import com.alibaba.fastjson.JSON;
import com.lyf.pojo.User;

public class FastJsonDemo {
    public static void main(String[] args) {
        //1.将java对象转为json字符串
        User user=new User();
        user.setId(1);
        user.setUsername("wbb");
        user.setPassword("123");

        String jsonString=JSON.toJSONString(user);
        System.out.println(jsonString);

        //2.将JSON字符串转化为Java对象
        User u=JSON.parseObject("{\"id\":1,\"password\":\"123\",\"username\":\"wbb\"}",User.class);
        System.out.println(u);
    }
}
