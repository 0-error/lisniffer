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
//	  //���ø�Ŀ¼
//	  private String rootPath = "/";  
//	  @Override  
//	  
//	  protected void onCreate(Bundle savedInstanceState)  {  
//	      super.onCreate(savedInstanceState); 
//	      requestWindowFeature(Window.FEATURE_NO_TITLE);
//	      setContentView(R.layout.file_list);   
//	      this.getFileDir(rootPath);  //��ʾĿ¼�µ��ļ��Լ��ļ���
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
//	              	if(readfile.IsPcap(path)){ //���ö�����ȡ�ļ���С�����ֵ
//	              		//if(file.length() > 51200)Toast.makeText(FileListActivity.this,"�ļ�̫���ˣ�", Toast.LENGTH_SHORT).show() ;
//	              		//else{   //�����Լ�������ʾ���ĸ�ʽ�������ݰ�����ʾ
//		                		List<PacketModel> packets = readfile.readFiletoArraylist(path) ;
//		        				
//		                		if(packets.isEmpty()){ //���ݰ�Ϊ�յ�ʱ��
//		        					Toast.makeText(Second_Activity.this,"��û��ץ������", Toast.LENGTH_SHORT).show() ;
//		        				}else{  //����PacketListActivity.java��ʾ������
//		        					Intent intent = new Intent();  
//		        					intent.putExtra("packetlist", (Serializable)packets);
//		        					intent.setClass(Second_Activity.this, PacketListActivity.class);  
//		        					Second_Activity.this.startActivity(intent);  
//		        				}
//		        			
//	              		//}
//	              	}else{
//	              		Toast.makeText(Second_Activity.this, "��PCAP�ļ���δץ������", Toast.LENGTH_SHORT).show() ;
//	              	}
//	              }    
//				}	
//	      	
//			});
//
//	  
//	      }   
//	  //android ��ȡ�ļ�Ŀ¼����ʾ����
//	  public void getFileDir(String filePath) {    
//	  	ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();     
//	      paths = new ArrayList<String>();    
//	      File f = new File(filePath);    
//	      File[] files = f.listFiles();   
//	      if (!filePath.equals(rootPath)){  //��ǰĿ¼���Ǹ�Ŀ¼��ʱ��
//	      	HashMap<String,String> item1 = new HashMap<String,String>();
//	          item1.put("filename","���ظ�Ŀ¼");  
//	          item1.put("filesize","") ;
//	          paths.add(rootPath);    //���ظ�Ŀ¼
//	          HashMap<String,String> item2 = new HashMap<String,String>();
//	          item2.put("filename","������һ��Ŀ¼");
//	          item2.put("filesize","") ;
//	          paths.add(f.getParent());  //���ظ�Ŀ¼
//	          list.add(item1);
//	          list.add(item2);
//	      }    
//	      if(files != null)  
//	      {    //��ʾ�ļ���С
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
//	              if(size<1024)  //�Ե�λB��ʾ
//	            	  item.put("filesize","size:"+size+"B");
//	              else {
//	            	  if(size>1024 && size<1048576){  //�Ե�λK��ʾ
//	            		  double sizek = (double)(file.length()/1024);
//	            		  item.put("filesize","size:"+sizek+"K");
//	            	  }
//	            	  else {    //�Ե�λM��ʾ
//	            		  double sizem = (double)(file.length()/1024);
//	            		  item.put("filesize","size:"+sizem+"M");
//	            	  } 		  
//	              }
//	              paths.add(file.getPath());   
//	              list.add(item);
//	          }    
//	      }    
//
//	      //��Data��Listview��������ListAdapter������listview������ʾ���ݵ��б�Data��Ҫ��ʾ������
//	      SimpleAdapter listAdapter = new SimpleAdapter(this,list,R.layout.file,
//	      		new String[]{"filename","filesize"},new int[]{R.id.file_name,R.id.file_size});   
//	      this.setListAdapter(listAdapter);    
//	     
//	  }    
//	 
//	}  
//
