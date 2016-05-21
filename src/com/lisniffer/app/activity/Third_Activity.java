package com.lisniffer.app.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;


import com.lisniffer.app.R;
import com.lisniffer.app.data.ReadFile;
import com.lisniffer.app.model.PacketModel;
import com.lisniffer.app.service.SnifferCommand;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Third_Activity extends Activity{

	public static int option;
//	/**
//	 * ����һ�������򣬲�������Ӧ�ĵ���¼�
//	 */
//	private static final String[] m={"IP","TCP","UDP"};
//	private TextView view;
//	private Spinner spinner;
//	private ArrayAdapter<String> adapter;
//	@Override
//	protected void onCreate(Bundle savedInstanceState){
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.third_activity);
//		view = (TextView)findViewById(R.id.spinnerText);
//		spinner = (Spinner)findViewById(R.id.Spinner01);
//		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,m);
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		spinner.setAdapter(adapter);
//		spinner.setOnItemSelectedListener(new SpinnerSelectedListener());  
//         
//	        //����Ĭ��ֵ  
//	    spinner.setVisibility(View.VISIBLE);  
//	          
//	}  
//	      
//	
//	    class SpinnerSelectedListener implements OnItemSelectedListener{  
//	  
//	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
//	                long arg3) {  
//	            view.setText("Э�飺"+m[arg2]);  
//	        }  
//	  
//	        public void onNothingSelected(AdapterView<?> arg0) {  
//	        }  
//	    }  

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.third_activity);
		Button button1 = (Button)findViewById(R.id.protocol);
		Button button2 = (Button)findViewById(R.id.statistics);
		/**
		 * ���button1ѡ��Э��
		 */
		button1.setOnClickListener(new OnClickListener(){
			String[] type = {"ALL","TCP","UDP"};
			public void onClick(View v){
				
				new AlertDialog.Builder(Third_Activity.this).setTitle("protocolType").setSingleChoiceItems(
		    		     type, -1, new DialogInterface.OnClickListener() {
		    		    	@Override
		    		    	public void onClick(DialogInterface dialog, int which) {
		    		
		    		    		option=which;
		    		    		Toast.makeText(getApplicationContext(),
		    		    				"you click"+type[which],
		    		    				Toast.LENGTH_LONG).show();
		    		    		dialog.dismiss() ;
		    		    		 AlertDialog.Builder build = new AlertDialog.Builder(getBaseContext());

		    		    	}
		    		   	}) .setNegativeButton("cancel", null).show();
			}
		});
		
		/**
		 * ���button2ͳ������
		 */
		button2.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v){
				Intent intent = new Intent();
				intent = getBarIntent(Third_Activity.this);
				startActivity(intent);
			}
		});
	}
	public Intent getBarIntent(Context context) {
    	// titles,colors,values ����		
    	String[] titles = new String[] { "upTraffic" ,"downTraffic"}; // ���ñ���		
    	int[] colors = new int[] { Color.MAGENTA,Color.BLUE }; // ���û��Ƶ���״ͼΪ���ɫ
    	XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();  //������ʾ����Ⱦͼ	
    	renderer.setAxisTitleTextSize(20);   //��������ֵĴ�С
    	renderer.setChartTitleTextSize(30);  //ͼ���������ֵĴ�С
    	renderer.setLabelsTextSize(15);      //��ǩ���ֵĴ�С
    	renderer.setLegendTextSize(25);      //ͼ�����ֵĴ�С
    	//renderer.setPanEnabled(false);       //ͼ���Ƿ�����ƶ� 
    	int length = colors.length;
    	//���´�������ÿ�����е���ɫ
    	for (int i = 0; i < length; i++) {
    		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
    		r.setColor(colors[i]);   //������ɫ
    		renderer.addSeriesRenderer(r);
    	}
    	
    	renderer.setChartTitle("trafficStatistic");  //ͼ����� ��X�����⡢Y������
    	renderer.setXTitle("ipHost");                       
    	renderer.setYTitle("trafficValue");                      
    	renderer.setXAxisMin(0.5);   //X��Y�������Сֵ���趨
    	renderer.setXAxisMax(8.5);
    	renderer.setYAxisMin(0);
    	//renderer.setYAxisMax(25000);
    	renderer.setYAxisMax(50000);
    	renderer.setAxesColor(Color.BLACK);  //��������ɫ
    	renderer.setLabelsColor(Color.RED);  //������������ɫ
    	renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//�����������Ƿ���ʾ����ֵ
    	renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
    	// renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
    	// renderer.getSeriesRendererAt(2).setDisplayChartValues(true);
    	renderer.setXLabels(6);  //����x����ʾ12����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��  
    	renderer.setYLabels(15); 
    	renderer.setXLabelsAlign(Align.CENTER); //�̶�����̶ȱ�ע֮������λ�ù�ϵ:Align.LEFT��Align.RIGHT��Align.CENTER
    	//renderer.setYLabelsAlign(Align.LEFT);
    	renderer.setPanEnabled(false, false); //�����������϶�,�����������϶�
    	renderer.setPanEnabled(true, true);
    	renderer. setPanLimits (new double[] { 0, 10000, 0, 5000000}); //�����϶�ʱ X �� Y ����������ֵ��Сֵ.
    	renderer.setApplyBackgroundColor(true);
    	renderer.setAxesColor(Color.RED);
    	//renderer.setBackgroundColor(Color.WHITE);
    	// renderer.setMarginsColor(Color.CYAN);
    	renderer.setMargins(new int[] { 0, 40, 10, 0 }); //����4�ܱ߾�
    	renderer.setBarSpacing(0.5f); //���Ӽ���
    	renderer.setZoomButtonsVisible(true); //���ÿ�������
    			
    	//���ú��������ʾ����
    	renderer.setXLabels(0); // ����X�᲻��ʾ���֣����������ֶ���ӵ����ֱ�ǩ��
    	renderer.setXLabelsAngle(0); // ����X���ǩ��б�Ƕ�(clockwise degree)
    	
    	final File captureFile = new File("/mnt/sdcard2/tcpdump/1.pcap");
		if (!captureFile.exists())  
		{
			Toast.makeText(getApplicationContext(),
					"caprure.pcap�ļ������ڣ����ȿ�ʼ��ȡ���ݰ���",Toast.LENGTH_LONG ).show();
			return null;
		}
		else{
			
			ReadFile readfile = new ReadFile();
	    	String path="/mnt/sdcard2/tcpdump/1.pcap";
	    	List<PacketModel> packets = readfile.readFiletoArraylist(path) ;
	    	String[] ipsource=new String[2*packets.size()];
	    	String[] ipdestination=new String[packets.size()];
	    	int count=0;
	    	int count_pa=0;
	        while(count_pa < packets.size()){
	    	       PacketModel packet = packets.get(count_pa);
	    	       if(packet.getIp_source()!=null)
	    	       {
	    	    	   ipsource[count]=packet.getIp_source();
	    	    	   ipdestination[count]=packet.getIp_destination();
	    	    	   //renderer.addTextLabel(count+1,ipsource[count]);
	    	    	   count++;
	    	       }
	    	       count_pa++;
	    	       
	        }
	        int f=count;
	        for(int k=0;k<count;k++) 
	        	ipsource[f+k]=ipdestination[k];  //��Ŀ���ַȫ������Դ��ַ������ͳһ�Ƚ�
	    	String[] ipsource_1=ipsource;
	    	String[] ipsource_2=new String[2*packets.size()];
	    	int n,m;
	    	int k=0;
	    	for(int g=0;g<(2*count);g++)
	    	 {
	    	    	for(n=g+1;n<(2*count);n++)
	    	    	{
	    	    		if(ipsource[g].equals(ipsource_1[n]))  n=10000;  //n=10000��һ����־��Ϊ������ѭ��
	    	    	}
	    	    	if(n!=10001) 
	    	    	{
	    	    		ipsource_2[k]=ipsource[g];
	    	    		k++;
	    	    	}
	    	                
	    	 }
	    	for(int j=0;j<k;j++)
	    	renderer.addTextLabel(j+1,ipsource_2[j]);

	    	    	 
	    	//������״ͼ�����ӵĸ߶ȣ���������(�ϴ�����)
	    	String source;
	    	long liuliang=0;
	    	double[] q=new double[k];
	    	List<double[]> values = new ArrayList<double[]>(); // ��Ż�����״ͼ��������	
	    	for(int a=0;a<k;a++)
	    	{
	    	    	int count2=0;
	    	    	while(count2 < packets.size()){
	    	             PacketModel packet = packets.get(count2);
	    	             if(packet.getIp_source()!=null)
	    	             {
	    	            	 source=packet.getIp_source();
	    	            	 if(source.equals(ipsource_2[a])) 
	    	            		 liuliang=liuliang+packet.getCaplen();
	    	             }
	    	             
	    	             count2++;
	    	    	}
	    	    	q[a]=liuliang;
	    	    	liuliang=0;  //������0
	    	 }
	    	    	//double[] q=new double[]{ 0, 12300, 14240, 15244, 15900, 19200,
	    	 		//		22030, 21200, 19500, 15500, 12600, 14000 };
	    	 values.add(q);
	    	
	    	 //������״ͼ�����ӵĸ߶ȣ��������꣨����������
	     	String destination_2;
	     	long liuliang_2=0;
	     	double[] p=new double[k];
	     //	List<double[]> values_2 = new ArrayList<double[]>(); // ��Ż�����״ͼ��������	
	     	for(int a=0;a<k;a++)
	     	{
	     	    	int count2=0;
	     	    	while(count2 < packets.size()){
	     	             PacketModel packet = packets.get(count2);
	     	             if(packet.getIp_destination()!=null)
	     	             {
	     	            	 destination_2=packet.getIp_destination();
	     	            	 if(destination_2.equals(ipsource_2[a])) 
	     	            		 liuliang_2=liuliang_2+packet.getCaplen();
	     	             }
	     	            
	     	             count2++;
	     	    	}
	     	    	p[a]=liuliang_2;
	     	    	liuliang_2=0;  //������0
	     	 }
	     	    	//double[] q=new double[]{ 0, 12300, 14240, 15244, 15900, 19200,
	     	 		//		22030, 21200, 19500, 15500, 12600, 14000 };
	     	 values.add(p);			

			
			
			/** 
			   * ����XYMultipleSeriesDataset,��������״ͼ,��������������
			   *  
			   * @param titles ÿ�������е�ͼ�� 
			   * @param values ���ӵĸ߶�ֵ 
			   * @return XYMultipleSeriesDataset 
			   */
			XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
			int length2 = titles.length;
			for (int i = 0; i < length2; i++) {
				CategorySeries series = new CategorySeries(titles[i]);
				double[] v = values.get(i);
				int seriesLength = v.length;
				for (int z = 0; z < seriesLength; z++) {
					series.add(v[z]);
				}
				dataset.addSeries(series.toXYSeries());
			}
			return ChartFactory.getBarChartIntent(context, dataset, renderer,
					Type.DEFAULT); //����View,Intent����Ҫ����һ��GraphicalActivity����ʾͼ���õ�
		
		}
	}
}
		
		