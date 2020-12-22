package com.hung.library;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {
	public static String md5Encoder(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String endText;
		MessageDigest msd = MessageDigest.getInstance("MD5");
//		// chuyen text goc sang byte
//		byte[] textByte = text.getBytes("UTF-8");
//		// MessageDigest ma hoa mang byte goc sang mang byte moi
//		byte[] endTextByte = msd.digest(textByte);
		//chuyen mang byte sang chuoi he 16
		
		
		//update lai chuoi can ma hoa
		msd.update(text.getBytes());
		BigInteger bigInt=new BigInteger(1, msd.digest());//1 la dau duong ,-1 la dau am
		endText=bigInt.toString(16);
		return endText;

	}
}
