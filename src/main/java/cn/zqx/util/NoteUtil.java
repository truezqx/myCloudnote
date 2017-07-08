package cn.zqx.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;






public class NoteUtil {
	/*
	 * MD5加密：摘要算法
	 * 特点：任意长度的字节处理成等长的结果，结果不可逆
	 */
	public static String md5(String src){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] output = md.digest(src.getBytes());
//			return new String(output);
			String res = Base64.encodeBase64String(output);
			return res;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return src;
	}
	public static String createId(){
		String id = UUID.randomUUID().toString();
		return id;
	}
	public static void main(String []args){
		System.out.println(createId());
		System.out.println(md5("123"));
		System.out.println(md5("123456"));
		System.out.println(md5("123").length());
		System.out.println(md5("123456").length());
	}
}
