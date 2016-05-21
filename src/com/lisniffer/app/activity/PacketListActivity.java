package com.lisniffer.app.activity;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lisniffer.app.R;
import com.lisniffer.app.model.PacketModel;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;

public class PacketListActivity extends ListActivity {

	private List<PacketModel> packets = null ;
	private Dialog infodialog = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_list);
        //����List<String>�ķ���
        packets = (List<PacketModel>)getIntent().getSerializableExtra("packetlist") ;
        
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        int count = 0 ;
        //�����������ץ��ʱ�䣬����Э�飬�Լ�����С����Ϣ
        while(count < packets.size()){
        	HashMap<String,String> map = new HashMap<String,String>();
        	PacketModel packet = packets.get(count) ;
        	map.put("time", count+".  "+packet.getTimestampHigh());
            map.put("size", packet.getProtocol2()+"      size:"+packet.getCaplen());
            list.add(map);
        	count++;
        }
        //��Data��Listview��������ListAdapter������listview������ʾ���ݵ��б�Data��Ҫ��ʾ������
        SimpleAdapter listAdapter = new SimpleAdapter(this,list,R.layout.packet,
        		new String[]{"time","size"},new int[]{R.id.time,R.id.size});
        setListAdapter(listAdapter);
    }
    
    //����ShowDialog��ʾ������ϸ��Ϣ
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		int index = position ;
		ShowDialog(index);
	}
    
	//ShowDialog�ľ���ʵ�ַ���
	public void ShowDialog(int index){
		PacketModel packet = packets.get(index) ;
		//����ShowInfo()������������ϸ��������Ϣ��ʾ
		String info = packet.ShowInfo() ;
		Context context = PacketListActivity.this; 
		infodialog = new Dialog(context);
		infodialog.setContentView(R.layout.infodialog);
		TextView textview = (TextView)infodialog.findViewById(R.id.info);
		Button btn_ok = (Button)infodialog.findViewById(R.id.okdialogbutton);
		textview.setText(info);
		Button.OnClickListener ok = new Button.OnClickListener(){ 
			@Override 
			public void onClick(View v) {
				infodialog.dismiss();
			}
		};
		btn_ok.setOnClickListener(ok);
		infodialog.show() ;
		
	}
}


