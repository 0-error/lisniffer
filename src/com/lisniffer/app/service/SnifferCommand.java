package com.lisniffer.app.service;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.lisniffer.app.activity.Third_Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

//public class SnifferCommand {
	

	/**
	 * 执行adbShell命令的方法
	 */
	
//	public void execCommand(String command) throws IOException {
//        Runtime runtime = Runtime.getRuntime();
//        Process proc = runtime.exec(command);
//        try {
//            if (proc.waitFor() != 0) {
//                System.err.println("exit value = " + proc.exitValue());
//            }
//            BufferedReader in = new BufferedReader(new InputStreamReader(
//                    proc.getInputStream()));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                stringBuffer.append(line+"-");
//            }
//            System.out.println(stringBuffer.toString());
// 
//        } catch (InterruptedException e) {
//            System.err.println(e);
//        }
//    }
	
//	private void execCommand(String cmd) throws IOException{
//		//String[] command=new String[]{"/bin/sh","-c",cmd};
//        String s;
//        Process p;
//        try {
//            p = Runtime.getRuntime().exec(cmd);
//            BufferedReader br = new BufferedReader(
//                new InputStreamReader(p.getInputStream()));
//            while ((s = br.readLine()) != null)
//                Log.i("SnifferCommand","line: " + s);
//            p.waitFor();
//            Log.i ("SnifferCommand","exit: " + p.exitValue());
//            p.destroy();
//        } catch (Exception e) {
//        	
//        }
//    }
	
	/**
	 * 抓包并将其存储到指定文件
	 */
	
//	public void cpturePacket(Process p){
		
		/*
		 * 抓tcp包
		 */
		
		/**
		 * 抓udp包
		 */
		
		/**
		 * 抓所有包
		 */
//		try{
//			p = Runtime.getRuntime().exec(new String[]{"sh","-c",
//					"tcpdump  -vv -l > sdcard/tcpdump/2dump.pcap"});
//			//BufferedReader br = new BufferedReader(
//         //       new InputStreamReader(p.getInputStream()));
//	           // while ((s = br.readLine()) != null)
//	                Log.i("SnifferCommand","line: " + s);
//	            try {
//					p.waitFor();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//	            Log.i ("SnifferCommand","exit: " + p.exitValue());
//	            p.destroy();
			
//		}catch(IOException e){
//			System.err.println(e);
//		}
//		
//	}
	
	/**
	 * 结束进程
	 */
//	public void stopCapture(Process p){
//		try{
//			Process p=Runtime.getRuntime().exec("kill tcpdump");
//			p.destroy();
//		}catch(IOException e){
//			System.err.println(e);
//		}
//		p.destroy();
//		
//	}
//		try {
//			Runtime.getRuntime().exec("kill tcpdump");   
//			BufferedReader in = new BufferedReader(new InputStreamReader(process2.getInputStream()));  
//			String temp= in.readLine();  
//			temp = in.readLine();  
//			temp = temp.replaceAll("^root *([0-9]*).*","$1");  
//			int pid = Integer.parseInt(temp);         
//			process2.destroy(); 
//			String killCommand = "kill "+pid;
//			Runtime.getRuntime().exec(killCommand);
//			Process process1 = Runtime.getRuntime().exec("ps tcpdump-arm");
//			//read the output of ps
//			DataInputStream in = new DataInputStream(process1.getInputStream());
//			String temp= in.readLine();
//			temp = in.readLine();
//			//We apply a regexp to the second line of the ps output to get the pid
//			temp = temp.replaceAll("^root *([0-9]*).*","$1");
//			int pid = Integer.parseInt(temp);
//			String killCommand = "kill "+pid;
//			Process process2 = Runtime.getRuntime().exec("su");
//			DataOutputStream os = new DataOutputStream(process2.getOutputStream());
//			os.writeBytes(killCommand);
//			os.flush();
//			os.writeBytes("exit\n");
//			os.flush();
//			os.close();
//			Log.i("SnifferCommand", "sucessed");
//			} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			}
//	}

	
	
//  }
/**
 * 
 * @author lihong06
 * @since 2014-3-3
 */
public class SnifferCommand {
	
	static boolean retval=true;
//    private static final String NAME = "tcpdump";
//    private static final String TAG = "CommandsHelper";
//    public static final String DEST_FILE = Environment.getExternalStorageDirectory() + "/ture.pcap";
    
    public static boolean startCapture(Context context) {
//        InputStream is = null;
//        OutputStream os = null;
//        boolean retVal = false;
//        try {
////            AssetManager am = context.getAssets();
////           is = am.open(NAME);
////            File sdcardFile = Environment.getExternalStorageDirectory();
////            File dstFile = new File(sdcardFile, NAME);
////            os = new FileOutputStream(dstFile);
////            
////            copyStream(is, os);
//            
//            String[] commands = new String[7];
//            commands[0] = "adb shell";
//            commands[1] = "su";
////            commands[2] = "cp -rf " + dstFile.toString() + " /data/local/tcpdump";
////            commands[3] = "rm -r " + dstFile.toString();
//            commands[4] = "chmod 777 /data/local/tcpdump";
//            commands[5] = "cd /data/local";
//            commands[6] = "tcpdump -p -vv -s 0 -w " + DEST_FILE;
//            
//            execCmd(commands);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.i(TAG, "    error: " + e.getMessage());
//        } finally {
//            closeSafely(is);
//            closeSafely(os);
//        }
        
//        return retVal;
  
        try{
        	String[] commands = new String[4];
        	commands[0] = "adb shell";
        	commands[1] = "su";
        	commands[2] = "chomd 777 /mnt/sdcard2/tcpdump";
        	
        	if(Third_Activity.option == 1)
        		commands[3] = "tcpdump -i any tcp -p -s 0 -w /mnt/sdcard2/tcpdump/1.pcap ";
        	else if(Third_Activity.option == 2)
        		commands[3] = "tcpdump -i any udp -p -s 0 -w /mnt/sdcard2/tcpdump/1.pcap";
        	else
        		commands[3] = "tcpdump -i any -p -s 0 -w /mnt/sdcard2/tcpdump/1.pcap";
        	execCmd(commands);
        }catch(Exception e){
        	e.printStackTrace();
        }
        return retval;
    }
    
    public static void stopCapture(Context context) {
        // 找出所有的带有tcpdump的进程
        String[] commands = new String[2];
        commands[0] = "adb shell";
        commands[1] = "ps|grep tcpdump|grep root|awk '{print $2}'";
        Process process = execCmd(commands);
        String result = parseInputStream(process.getInputStream());
        if (!TextUtils.isEmpty(result)) {
            String[] pids = result.split("\n");
            if (null != pids) {
                String[] killCmds = new String[pids.length];
                for (int i = 0; i < pids.length; ++i) {
                    killCmds[i] = "kill -9 " + pids[i];
                }
                execCmd(killCmds);
            }
        }
    }
    
    public static Process execCmd(String command) {
        return execCmd(new String[] { command }, true);
    }
    
    public static Process execCmd(String[] commands) {
        return execCmd(commands, true);
    }
    
    public static Process execCmd(String[] commands, boolean waitFor) {
        Process suProcess = null;
        try {
            suProcess = Runtime.getRuntime().exec("su");
            DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
            for (String cmd : commands) {
                if (!TextUtils.isEmpty(cmd)) {
                    os.writeBytes(cmd + "\n");
                }
            }
            os.flush();
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        if (waitFor) {
////            boolean retval = false;
//            try {
//                int suProcessRetval = suProcess.waitFor();
//                if (255 != suProcessRetval) {
//                    retval = true;
//                } else {
//                    retval = false;
//                }
//            } catch (Exception ex) {
//                Log.w("Error ejecutando el comando Root", ex);
//            }
//        }
        
        return suProcess;
    }
    
//    private static void copyStream(InputStream is, OutputStream os) {
//        final int BUFFER_SIZE = 1024;
//        try {
//            byte[] bytes = new byte[BUFFER_SIZE];
//            for (;;) {
//                int count = is.read(bytes, 0, BUFFER_SIZE);
//                if (count == -1) {
//                    break;
//                }
//
//                os.write(bytes, 0, count);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    private static void closeSafely(Closeable is) {
//        try {
//            if (null != is) {
//                is.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    private static String parseInputStream(InputStream is) {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ( (line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return sb.toString();
    }
    

}
	
		


