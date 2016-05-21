package com.lisniffer.app.data;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadMethod {

	/**
	 * 读取文件到内存
	 */
	public void ReadAll()  {
		FileInputStream fis = null;
		byte[] b = null;
	    try {
	        fis = new FileInputStream("sdcard/tcpdump/mytcpdump.pcap");
	        int m = fis.read(b);
	        //....
	    } catch(IOException e){
	    	
	    }// .....
	}
	/**
	 * 读取pcap文件头
	 */
	public PcapFileHeader parseFileHeader(byte[] file_header) 
			throws IOException{
		 PcapFileHeader fileHeader = new PcapFileHeader();
	        byte[] buff_4 = new byte[4];    // 4 字节的数组
	        byte[] buff_2 = new byte[2];    // 2 字节的数组

	        int offset = 0;
	        for (int i = 0; i < 4; i ++) {
	            buff_4[i] = file_header[i + offset];
	        }
	        offset += 4;
	        int magic = DataUtils.byteArrayToInt(buff_4);
	        fileHeader.setMagic(magic);

	        for (int i = 0; i < 2; i ++) {
	            buff_2[i] = file_header[i + offset];
	        }
	        offset += 2;
	        short magorVersion = DataUtils.byteArrayToShort(buff_2);
	        fileHeader.setMagorVersion(magorVersion);

	        for (int i = 0; i < 2; i ++) {
	            buff_2[i] = file_header[i + offset];
	        }
	        offset += 2;
	        short minorVersion = DataUtils.byteArrayToShort(buff_2);
	        fileHeader.setMinorVersion(minorVersion);

	        for (int i = 0; i < 4; i ++) {
	            buff_4[i] = file_header[i + offset];
	        }
	        offset += 4;
	        int timezone = DataUtils.byteArrayToInt(buff_4);
	        fileHeader.setTimezone(timezone);

	        for (int i = 0; i < 4; i ++) {
	            buff_4[i] = file_header[i + offset];
	        }
	        offset += 4;
	        int sigflags = DataUtils.byteArrayToInt(buff_4);
	        fileHeader.setSigflags(sigflags);

	        for (int i = 0; i < 4; i ++) {
	            buff_4[i] = file_header[i + offset];
	        }
	        offset += 4;
	        int snaplen = DataUtils.byteArrayToInt(buff_4);
	        fileHeader.setSnaplen(snaplen);

	        for (int i = 0; i < 4; i ++) {
	            buff_4[i] = file_header[i + offset];
	        }
	        offset += 4;
	        int linktype = DataUtils.byteArrayToInt(buff_4);
	        fileHeader.setLinktype(linktype);

//	      LogUtils.printObjInfo(fileHeader);

	        return fileHeader;
		
	} 
	
	/**
	 * 读取数据包头
	 */
	public PcapDataHeader parseDataHeader(byte[] data_header){
		
		byte[] buff_4 = new byte[4];
        PcapDataHeader dataHeader = new PcapDataHeader();
        int offset = 0;
        for (int i = 0; i < 4; i ++) {
            buff_4[i] = data_header[i + offset];
        }
        offset += 4;
        int timeS = DataUtils.byteArrayToInt(buff_4);
        dataHeader.setTimeS(timeS);

        for (int i = 0; i < 4; i ++) {
            buff_4[i] = data_header[i + offset];
        }
        offset += 4;
        int timeMs = DataUtils.byteArrayToInt(buff_4);
        dataHeader.setTimeMs(timeMs);

        for (int i = 0; i < 4; i ++) {
            buff_4[i] = data_header[i + offset];
        }
        offset += 4;
        // 得先逆序在转为 int
//        DataUtils.reverseByteArray(buff_4);
        int caplen = DataUtils.byteArrayToInt(buff_4);
        dataHeader.setCaplen(caplen);
//      LogUtils.printObj("数据包实际长度", dataHeader.getCaplen());

        for (int i = 0; i < 4; i ++) {
            buff_4[i] = data_header[i + offset];
        }
        offset += 4;
        //      int len = DataUtils.byteArrayToInt(buff_4);
        DataUtils.reverseByteArray(buff_4);
        int len = DataUtils.byteArrayToInt(buff_4);
        dataHeader.setLen(len);

//      LogUtils.printObjInfo(dataHeader);

        return dataHeader;
	}
	
	/**
     * 读取 Pcap 数据帧
     * @param fis
     */
    public void readPcapDataFrame(byte[] content) {
    	
    	 PcapDataFrame dataFrame = new PcapDataFrame();
         int offset = 12;
         byte[] buff_2 = new byte[2];
         for (int i = 0; i < 2; i ++) {
             buff_2[i] = content[i + offset];
         }
         short frameType = DataUtils.byteArrayToShort(buff_2);
         dataFrame.setFrameType(frameType);

//       LogUtils.printObjInfo(dataFrame);
    }
    /**
     * 读取IP头
     * @param content
     * @return
     */
    public  IPHeader readIPHeader(byte[] content){
    	
    	 int offset = 14;
         IPHeader ip = new IPHeader();

         byte[] buff_2 = new byte[2];
         byte[] buff_4 = new byte[4];

         byte varHLen = content[offset ++];              // offset = 15
//       LogUtils.printByteToBinaryStr("varHLen", varHLen);
         if (varHLen == 0) {
             return null;
         }

         ip.setVarHLen(varHLen);

         byte tos = content[offset ++];                  // offset = 16
         ip.setTos(tos);

         for (int i = 0; i < 2; i ++) {      
             buff_2[i] = content[i + offset];
         }
         offset += 2;                                    // offset = 18
         short totalLen = DataUtils.byteArrayToShort(buff_2);
         ip.setTotalLen(totalLen);

         for (int i = 0; i < 2; i ++) {          
             buff_2[i] = content[i + offset];
         }
         offset += 2;                                    // offset = 20
         short id = DataUtils.byteArrayToShort(buff_2);
         ip.setId(id);

         for (int i = 0; i < 2; i ++) {                  
             buff_2[i] = content[i + offset];
         }
         offset += 2;                                    // offset = 22
         short flagSegment = DataUtils.byteArrayToShort(buff_2);
         ip.setFlagSegment(flagSegment);

         byte ttl = content[offset ++];                  // offset = 23
         ip.setTtl(ttl);

         byte protocol = content[offset ++];             // offset = 24
         ip.setProtocol(protocol);

         for (int i = 0; i < 2; i ++) {                  
             buff_2[i] = content[i + offset];
         }
         offset += 2;                                    // offset = 26
         short checkSum = DataUtils.byteArrayToShort(buff_2);
         ip.setCheckSum(checkSum);

         for (int i = 0; i < 4; i ++) {                  
             buff_4[i] = content[i + offset];
         }
         offset += 4;                                    // offset = 30
         int srcIP = DataUtils.byteArrayToInt(buff_4);
         ip.setSrcIP(srcIP);

         // 拼接出 SourceIP
         StringBuilder builder = new StringBuilder();
         for (int i = 0; i < 4; i++) {
             builder.append((int) (buff_4[i] & 0xff));
             builder.append(".");
         }
         builder.deleteCharAt(builder.length() - 1);
         String sourceIP = builder.toString();
         ProtocolData protocolData=new ProtocolData();
         protocolData.setSrcIP(sourceIP);

         for (int i = 0; i < 4; i ++) {      
             buff_4[i] = content[i + offset];
         }
         offset += 4;                                    // offset = 34
         int dstIP = DataUtils.byteArrayToInt(buff_4);
         ip.setDstIP(dstIP);

         // 拼接出 DestinationIP
         builder = new StringBuilder();
         for (int i = 0; i < 4; i++) {
             builder.append((int) (buff_4[i] & 0xff));
             builder.append(".");
         }
         builder.deleteCharAt(builder.length() - 1);
         String destinationIP = builder.toString();
         protocolData.setDesIP(destinationIP);

//       LogUtils.printObjInfo(ip);

         return ip;
    }
	/**
	 * 读取TCP头
	 */
    public TCPHeader readTCPHeader(byte[] content, int offset){
    	byte[] buff_2 = new byte[2];
        byte[] buff_4 = new byte[4];

        TCPHeader tcp = new TCPHeader();

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
//          LogUtils.printByteToBinaryStr("TCP: buff_2[" + i + "]", buff_2[i]);
        }
        offset += 2;                                    // offset = 36
        short srcPort = DataUtils.byteArrayToShort(buff_2);
        tcp.setSrcPort(srcPort);

        String sourcePort = validateData(srcPort);
        ProtocolData protocolData=new ProtocolData();
        protocolData.setSrcPort(sourcePort);

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
        }
        offset += 2;                                    // offset = 38
        short dstPort = DataUtils.byteArrayToShort(buff_2);
        tcp.setDstPort(dstPort);

        String desPort = validateData(dstPort);
        protocolData.setDesPort(desPort);

        for (int i = 0; i < 4; i ++) {
            buff_4[i] = content[i + offset];
        }
        offset += 4;                                    // offset = 42
        int seqNum = DataUtils.byteArrayToInt(buff_4);
        tcp.setSeqNum(seqNum);

        for (int i = 0; i < 4; i ++) {
            buff_4[i] = content[i + offset];
        }
        offset += 4;                                    // offset = 46
        int ackNum = DataUtils.byteArrayToInt(buff_4);
        tcp.setAckNum(ackNum);

        byte headerLen = content[offset ++];            // offset = 47
        tcp.setHeaderLen(headerLen);

        byte flags = content[offset ++];                // offset = 48
        tcp.setFlags(flags);

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
        }
        offset += 2;                                    // offset = 50
        short window = DataUtils.byteArrayToShort(buff_2);
        tcp.setWindow(window);

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
        }
        offset += 2;                                    // offset = 52
        short checkSum = DataUtils.byteArrayToShort(buff_2);
        tcp.setCheckSum(checkSum);

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
        }
        offset += 2;                                    // offset = 54
        short urgentPointer = DataUtils.byteArrayToShort(buff_2);
        tcp.setUrgentPointer(urgentPointer);

//      LogUtils.printObj("tcp.offset", offset);
//      data_offset = offset;
//      LogUtils.printObjInfo(tcp);

        return tcp;
    }
    
   /**
     * 读取UDP头
     */
    public UDPHeader readUDPHeader(byte[] content, int offset){
    	
    	byte[] buff_2 = new byte[2];

        UDPHeader udp = new UDPHeader();
        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
//          LogUtils.printByteToBinaryStr("UDP: buff_2[" + i + "]", buff_2[i]);
        }
        offset += 2;                                    // offset = 36
        short srcPort = DataUtils.byteArrayToShort(buff_2);
        udp.setSrcPort(srcPort);

        String sourcePort = validateData(srcPort);
        ProtocolData protocolData=new ProtocolData();
        protocolData.setSrcPort(sourcePort);

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
        }
        offset += 2;                                    // offset = 38
        short dstPort = DataUtils.byteArrayToShort(buff_2);
        udp.setDstPort(dstPort);

        String desPort = validateData(dstPort);
        protocolData.setDesPort(desPort);

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
        }
        offset += 2;                                    // offset = 40
        short length = DataUtils.byteArrayToShort(buff_2);
        udp.setLength(length);

        for (int i = 0; i < 2; i ++) {
            buff_2[i] = content[i + offset];
        }
        offset += 2;                                    // offset = 42
        short checkSum = DataUtils.byteArrayToShort(buff_2);
        udp.setCheckSum(checkSum);

//      LogUtils.printObj("udp.offset", offset );
//      LogUtils.printObjInfo(udp);
//        data_offset = offset;

        return udp;
    }
    
    public String validateData(short s){
    	
    	return ""+s;
    }
}
