package com.lisniffer.app.model;

import java.io.FileInputStream;
import java.io.IOException;

import com.lisniffer.app.data.DataUtils;
import com.lisniffer.app.data.IPHeader;
import com.lisniffer.app.data.PcapDataHeader;
import com.lisniffer.app.data.ProtocolType;
import com.lisniffer.app.data.ReadMethod;
import com.lisniffer.app.data.TCPHeader;
import com.lisniffer.app.data.UDPHeader;

public class AnalyseFile {

	private String[] srcIP = new String[1000];
	private String[] dstIP = new String[1000];
	private String[] srcPort = new String[1000];
	private String[] desPort = new String[1000];
	private String[] protocolType = new String[1000];
	
	public AnalyseFile(){
		ReadMethod method=new ReadMethod();
		byte[] file_header = new byte[24];
		byte[] data_header = new byte[16];
		FileInputStream fis = null;
		int i=0;
	    try {
	        fis = new FileInputStream("/mnt/sdcard2/tcpdump/1.pcap");
	        fis.read(file_header);
	        while(fis.read()!=-1){
	        	fis.read(data_header);
	        	PcapDataHeader dataHeader = method.parseDataHeader(data_header);
	        	byte[] content = new byte[dataHeader.getCaplen()];
	        	IPHeader ipHeader = method.readIPHeader(content);
	        	srcIP[i]=DataUtils.intToHexString(ipHeader.getSrcIP());
	        	dstIP[i]=DataUtils.intToHexString(ipHeader.getDstIP());
	        	if(ipHeader.getProtocol()==6){
	        		protocolType[i] = "TCP";
	        		TCPHeader tcpHeader = method.readTCPHeader(content,34);
	        		srcPort[i]=DataUtils.shortToHexString(tcpHeader.getSrcPort());
	        		desPort[i]=DataUtils.shortToHexString(tcpHeader.getDstPort());
	        	}
	        	else if (ipHeader.getProtocol()==17){
	        		protocolType[i] = "UDP";
	        		UDPHeader udpHeader = method.readUDPHeader(content,34);
	        		srcPort[i]=DataUtils.shortToHexString(udpHeader.getSrcPort());
	        		desPort[i]=DataUtils.shortToHexString(udpHeader.getDstPort());
	        	}
	        	
	        	fis.read(content);
	        	i++;
	        }
	        } catch(IOException e){
	    }
	}
	public String getSrcIP(int i) {
		return srcIP[i];
	}
	public void setSrcIP(String[] srcIP) {
		this.srcIP = srcIP;
	}
	public String getDesIP(int i) {
		return dstIP[i];
	}
	public void setDesIP(String[] desIP) {
		this.dstIP = desIP;
	}
	public String getSrcPort(int i) {
		return srcPort[i];
	}
	public void setSrcPort(String[] srcPort) {
		this.srcPort = srcPort;
	}
	public String getDesPort(int i) {
		return desPort[i];
	}
	public void setDesPort(String[] desPort) {
		this.desPort = desPort;
	}
	public String getProtocolType(int i) {
		return protocolType[i];
	}
	public void setProtocolType(String[] protocolType) {
		this.protocolType = protocolType;
	}
}
