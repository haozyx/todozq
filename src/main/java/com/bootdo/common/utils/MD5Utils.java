package com.bootdo.common.utils;

import java.util.Random;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
	private static final String SALT = "1qazxsw2";

	private static final String ALGORITH_NAME = "md5";

	private static final int HASH_ITERATIONS = 2;

	public static String encrypt(String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
		return newPassword;
	}

	public static String encrypt(String username, String pswd) {
		String newPassword = new SimpleHash(ALGORITH_NAME, pswd, ByteSource.Util.bytes(username + SALT),
				HASH_ITERATIONS).toHex();
		return newPassword;
	}
	public static void main(String[] args) {
		
		System.out.println(MD5Utils.encrypt("wanghby", "wanghby..."));
	}
	
    public static String genRandomNum(){  
        int  maxNum = 36;  
        int i;  
        int count = 0;  
        char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
          'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
          'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
        StringBuffer pwd = new StringBuffer("");  
        Random r = new Random();  
        while(count < 8){  
         i = Math.abs(r.nextInt(maxNum));     
         if (i >= 0 && i < str.length) {  
          pwd.append(str[i]);  
          count ++;  
         }  
        }  
        return pwd.toString();  
      }

}
