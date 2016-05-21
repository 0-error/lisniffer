package com.lisniffer.app.activity;

import java.io.Serializable;
import java.util.List;

import com.lisniffer.app.R;
import com.lisniffer.app.data.ReadFile;
import com.lisniffer.app.model.PacketModel;
import com.lisniffer.app.service.SnifferCommand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

/**
 * 主活动，显示主界面
 * @author Administrator
 *
 */
public class Main_Activity extends Activity{
//	static Process p;
	Button button1;
	Button button2;
	Button button3;
//	SnifferCommand snifferCommand=new SnifferCommand();
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_activity);
		button1 = (Button)findViewById(R.id.button_1);
		button2 = (Button)findViewById(R.id.button_2);
		button3 = (Button)findViewById(R.id.button_3);
		button1.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				
				/**
				 * 改变按钮的文字并抓包
				 */
				if(button1.getText().equals("start")){
					button1.setText("stop");
					new Thread(new Runnable() {  
						@Override  
						public void run() {  
			            final boolean retVal = SnifferCommand.startCapture(Main_Activity.this);  
					    runOnUiThread(new Runnable() {  
					        @Override  
				            public void run() {  
				               Toast.makeText(Main_Activity.this, "startCapture result = " + retVal, Toast.LENGTH_SHORT).show();  
						                        }  
					    });  
					    }  
					}).start();  

				}
				else{
					button1.setText("start");
					SnifferCommand.stopCapture(Main_Activity.this); 
				}
				
			}
		});
		/**
		 * read按钮点击进入下一活动
		 */
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				ReadFile readfile = new ReadFile();
				List<PacketModel> packets = readfile.
						readFiletoArraylist("/mnt/sdcard2/tcpdump/1.pcap"); 
				Intent intent = new Intent();  
				intent.putExtra("packetlist", (Serializable)packets);
				intent.setClass(Main_Activity.this, PacketListActivity.class); 
				startActivity(intent);
			}
		});
		
		/**
		 * option按钮点击进入下一活动
		 */
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(Main_Activity.this,Third_Activity.class);
				startActivity(intent);
			}
			
		});
		
	}
}
