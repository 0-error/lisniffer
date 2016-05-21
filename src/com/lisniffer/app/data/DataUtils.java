package com.lisniffer.app.data;

public class DataUtils {

	public static String intToHexString(int n){
		
		return Integer.toHexString(n);
	}
	
	public static String shortToHexString(short n){
		
		return Integer.toHexString(n & 0xffff);
	} 
	
	public static String byteToHexString(byte n){
		
			StringBuffer buf = new StringBuffer();
			
				int halfbyte = (n >>> 4) & 0x0F;
				int two_halfs = 0;
				do {
					if ((0 <= halfbyte) && (halfbyte <= 9))
						buf.append((char) ('0' + halfbyte));
					else
						buf.append((char) ('A' + (halfbyte - 10)));
					halfbyte = n & 0x0F;
				} while (two_halfs++ < 1);
			
			return buf.toString();
	}
 
	
	public static short byteArrayToShort(byte[] b){
		short s = 0;  
        short s0 = (short) (b[0] & 0xff);// 最低位  
        short s1 = (short) (b[1] & 0xff);  
        s1 <<= 8;  
        s = (short) (s0 | s1);  
        return s; 
	} 
	
	public static int byteArrayToInt(byte[] b){
		
		byte[] a = new byte[4];  
	    int i = a.length - 1,j = b.length - 1;  
	    for (; i >= 0 ; i--,j--) {//从b的尾部(即int值的低位)开始copy数据  
	        if(j >= 0)  
	            a[i] = b[j];  
	        else  
	            a[i] = 0;//如果b.length不足4,则将高位补0  
	  }  
	    int v0 = (a[0] & 0xff) << 24;//&0xff将byte值无差异转成int,避免Java自动类型提升后,会保留高位的符号位  
	    int v1 = (a[1] & 0xff) << 16;  
	    int v2 = (a[2] & 0xff) << 8;  
	    int v3 = (a[3] & 0xff) ;  
	    return v0 + v1 + v2 + v3;  
	} 
	
	public static byte[] reverseByteArray(byte[] b){
	
		for(int i=0;i<b.length/2;i++){
              byte rb = b[i];
              b[i] = b[b.length-1-i];
              b[b.length-1-i] = rb;
      }
		return b;
	} 
}
