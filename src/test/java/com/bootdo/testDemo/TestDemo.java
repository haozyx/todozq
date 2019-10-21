package com.bootdo.testDemo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import com.bootdo.common.utils.MD5Utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;

/*@RestController()
@RunWith(SpringRunner.class)
@SpringBootTest*/
public class TestDemo {
   /* @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void test() {
        redisTemplate.opsForValue().set("a", "b");
        System.out.println(redisTemplate.opsForValue().get("a"));
    }*/

    @Test 
    public void md5() {
    	/*String md5 = SecureUtil.md5("123456");
    	String md52 = SecureUtil.md5("123456");
    	System.out.println(md5);
    	System.out.println(md52);
    	String username ="abc";*/
        String password = MD5Utils.encrypt("appadmin", "admin123");
        System.out.println(password);
    	
    }
    @Test
    public void genMima() {
    	System.out.println((int)((Math.random()*9+1)*100000));
    }
    
    @Test
    public void date() {
    	Date date = DateUtil.parse("2019-10-19 18:28:29");
    	long ms = DateUtil.betweenMs(new Date(),date);
    	  System.out.println(ms);
    }
  
}
