package cn.app.util;

import java.util.UUID;

public class Tool {
   
	public static String createUUID(){
		return UUID.randomUUID().toString().replaceAll("-","");
	}
}
