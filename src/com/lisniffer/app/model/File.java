package com.lisniffer.app.model;

import java.io.FileInputStream;
import java.io.IOException;

import com.lisniffer.app.data.DataUtils;
import com.lisniffer.app.data.PcapDataHeader;
import com.lisniffer.app.data.PcapFileHeader;
import com.lisniffer.app.data.ReadMethod;

public class File {

	private String[] time = new String[1000];
	private String[] size = new String[1000];
	private int number;
	
	public File(){
		ReadMethod method=new ReadMethod();
		byte[] file_header = new byte[24];
		byte[] data_header = new byte[16];
		FileInputStream fis = null;
		int i=0;
	    try {
	        fis = new FileInputStream("/mnt/sdcard2/tcpdump/1.pcap");
	        fis.read(file_header);
//	        PcapFileHeader fileHeader = method.parseFileHeader(file_header);
	        //time = ""+fileHeader.getTimezone();
	        while(fis.read()!=-1){
	        	fis.read(data_header);
	        	PcapDataHeader dataHeader = method.parseDataHeader(data_header);
//	        	time[i] = DataUtils.intToHexString(fileHeader.getTimezone());
	        	byte[] content = new byte[dataHeader.getCaplen()];
	        	size[i] = ""+dataHeader.getCaplen();
	        	fis.read(content);
	        	i++;
	        }
	        number=i;
	    } catch(IOException e){
	    }
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getTime(int i) {
		return time[i];
	}
	public void setTime(String[] time) {
		this.time = time;
	}
	public String getSize(int i) {
		return size[i];
	}
	public void setSize(String[] size) {
		this.size = size;
	}
	
}
