//package com.lisniffer.app.activity;
//
//import java.io.File;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import com.lisniffer.app.R;
//import com.lisniffer.app.data.ReadFile;
//import com.lisniffer.app.model.AnalyseFile;
//import com.lisniffer.app.model.PacketModel;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.ListActivity;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//import android.widget.SimpleAdapter;
//import android.widget.Toast;
//
//public class Second_Activity extends ListActivity {  
//	  private List<String> paths = null;
//	  //设置根目录
//	  private String rootPath = "/";  
//	  @Override  
//	  
//	  protected void onCreate(Bundle savedInstanceState)  {  
//	      super.onCreate(savedInstanceState); 
//	      requestWindowFeature(Window.FEATURE_NO_TITLE);
//	      setContentView(R.layout.file_list);   
//	      this.getFileDir(rootPath);  //显示目录下的文件以及文件夹
//	   
//	      getListView().setOnItemClickListener(new OnItemClickListener() {			
//	      	@Override
//				public void onItemClick(AdapterView<?> arg0, View v, int position,long id) {  
//	              String path = paths.get(position);    
//	              File file = new File(path);    
//	              
//	              if(file.isDirectory()){    
//	              	Second_Activity.this.getFileDir(path);    
//	              }else{  
//	              	ReadFile readfile = new ReadFile();
//	              	if(readfile.IsPcap(path)){ //设置读可以取文件大小的最大值
//	              		//if(file.length() > 51200)Toast.makeText(FileListActivity.this,"文件太大了！", Toast.LENGTH_SHORT).show() ;
//	              		//else{   //按照自己设置显示包的格式进行数据包的显示
//		                		List<PacketModel> packets = readfile.readFiletoArraylist(path) ;
//		        				
//		                		if(packets.isEmpty()){ //数据包为空的时候
//		        					Toast.makeText(Second_Activity.this,"还没有抓到包呢", Toast.LENGTH_SHORT).show() ;
//		        				}else{  //调用PacketListActivity.java显示包内容
//		        					Intent intent = new Intent();  
//		        					intent.putExtra("packetlist", (Serializable)packets);
//		        					intent.setClass(Second_Activity.this, PacketListActivity.class);  
//		        					Second_Activity.this.startActivity(intent);  
//		        				}
//		        			
//	              		//}
//	              	}else{
//	              		Toast.makeText(Second_Activity.this, "非PCAP文件或未抓到包！", Toast.LENGTH_SHORT).show() ;
//	              	}
//	              }    
//				}	
//	      	
//			});
//
//	  
//	      }   
//	  //android 获取文件目录及显示调用
//	  public void getFileDir(String filePath) {    
//	  	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();     
//	      paths = new ArrayList<String>();    
//	      File f = new File(filePath);    
//	      File[] files = f.listFiles();   
//	      if (!filePath.equals(rootPath)){  //当前目录不是根目录的时候
//	      	HashMap<String,String> item1 = new HashMap<String,String>();
//	          item1.put("filename","返回根目录");  
//	          item1.put("filesize","") ;
//	          paths.add(rootPath);    //返回根目录
//	          HashMap<String,String> item2 = new HashMap<String,String>();
//	          item2.put("filename","返回上一层目录");
//	          item2.put("filesize","") ;
//	          paths.add(f.getParent());  //返回父目录
//	          list.add(item1);
//	          list.add(item2);
//	      }    
//	      if(files != null)  
//	      {    //显示文件大小
//	          int count = files.length;
//	          for (int i = 0; i < count; i++) {    
//	          	File file = files[i];    
//	              HashMap<String,String> item = new HashMap<String,String>();    
//	              item.put("filename",file.getName()) ;
//	              //double size = (double)(file.length()/1024);
//	              //item.put("filesize","size:"+size+"K");
//	              //double size = (double)file.length();
//	              //item.put("filesize","size:"+size+"B");
//	              double size = (double)file.length();
//	              if(size<1024)  //以单位B显示
//	            	  item.put("filesize","size:"+size+"B");
//	              else {
//	            	  if(size>1024 && size<1048576){  //以单位K显示
//	            		  double sizek = (double)(file.length()/1024);
//	            		  item.put("filesize","size:"+sizek+"K");
//	            	  }
//	            	  else {    //以单位M显示
//	            		  double sizem = (double)(file.length()/1024);
//	            		  item.put("filesize","size:"+sizem+"M");
//	            	  } 		  
//	              }
//	              paths.add(file.getPath());   
//	              list.add(item);
//	          }    
//	      }    
//
//	      //绑定Data和Listview的适配器ListAdapter。其中listview用来显示数据的列表，Data需要显示的数据
//	      SimpleAdapter listAdapter = new SimpleAdapter(this,list,R.layout.file,
//	      		new String[]{"filename","filesize"},new int[]{R.id.file_name,R.id.file_size});   
//	      this.setListAdapter(listAdapter);    
//	     
//	  }    
//	 
//	}  
//
