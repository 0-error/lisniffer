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
//	 * 加入一个下拉框，并创建相应的点击事件
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
//	        //设置默认值  
//	    spinner.setVisibility(View.VISIBLE);  
//	          
//	}  
//	      
//	
//	    class SpinnerSelectedListener implements OnItemSelectedListener{  
//	  
//	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,  
//	                long arg3) {  
//	            view.setText("协议："+m[arg2]);  
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
		 * 点击button1选择协议
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
		 * 点击button2统计流量
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
    	// titles,colors,values 设置		
    	String[] titles = new String[] { "upTraffic" ,"downTraffic"}; // 设置标题		
    	int[] colors = new int[] { Color.MAGENTA,Color.BLUE }; // 设置绘制的柱状图为洋红色
    	XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();  //构造显示用渲染图	
    	renderer.setAxisTitleTextSize(20);   //轴标题文字的大小
    	renderer.setChartTitleTextSize(30);  //图表名称文字的大小
    	renderer.setLabelsTextSize(15);      //标签文字的大小
    	renderer.setLegendTextSize(25);      //图标文字的大小
    	//renderer.setPanEnabled(false);       //图表是否可以移动 
    	int length = colors.length;
    	//以下代码设置每个序列的颜色
    	for (int i = 0; i < length; i++) {
    		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
    		r.setColor(colors[i]);   //设置颜色
    		renderer.addSeriesRenderer(r);
    	}
    	
    	renderer.setChartTitle("trafficStatistic");  //图表标题 、X轴主题、Y轴主题
    	renderer.setXTitle("ipHost");                       
    	renderer.setYTitle("trafficValue");                      
    	renderer.setXAxisMin(0.5);   //X、Y轴最大最小值的设定
    	renderer.setXAxisMax(8.5);
    	renderer.setYAxisMin(0);
    	//renderer.setYAxisMax(25000);
    	renderer.setYAxisMax(50000);
    	renderer.setAxesColor(Color.BLACK);  //坐标轴颜色
    	renderer.setLabelsColor(Color.RED);  //坐标轴文字颜色
    	renderer.getSeriesRendererAt(0).setDisplayChartValues(true);//设置柱子上是否显示数量值
    	renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
    	// renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
    	// renderer.getSeriesRendererAt(2).setDisplayChartValues(true);
    	renderer.setXLabels(6);  //设置x轴显示12个点,根据setChartSettings的最大值和最小值自动计算点的间隔  
    	renderer.setYLabels(15); 
    	renderer.setXLabelsAlign(Align.CENTER); //刻度线与刻度标注之间的相对位置关系:Align.LEFT、Align.RIGHT、Align.CENTER
    	//renderer.setYLabelsAlign(Align.LEFT);
    	renderer.setPanEnabled(false, false); //不允许左右拖动,不允许上下拖动
    	renderer.setPanEnabled(true, true);
    	renderer. setPanLimits (new double[] { 0, 10000, 0, 5000000}); //设置拖动时 X 轴 Y 轴允许的最大值最小值.
    	renderer.setApplyBackgroundColor(true);
    	renderer.setAxesColor(Color.RED);
    	//renderer.setBackgroundColor(Color.WHITE);
    	// renderer.setMarginsColor(Color.CYAN);
    	renderer.setMargins(new int[] { 0, 40, 10, 0 }); //设置4周边距
    	renderer.setBarSpacing(0.5f); //柱子间宽度
    	renderer.setZoomButtonsVisible(true); //设置可以缩放
    			
    	//设置横坐标的显示文字
    	renderer.setXLabels(0); // 设置X轴不显示数字（改用我们手动添加的文字标签）
    	renderer.setXLabelsAngle(0); // 设置X轴标签倾斜角度(clockwise degree)
    	
    	final File captureFile = new File("/mnt/sdcard2/tcpdump/1.pcap");
		if (!captureFile.exists())  
		{
			Toast.makeText(getApplicationContext(),
					"caprure.pcap文件不存在，请先开始获取数据包！",Toast.LENGTH_LONG ).show();
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
	        	ipsource[f+k]=ipdestination[k];  //将目标地址全部存入源地址，进行统一比较
	    	String[] ipsource_1=ipsource;
	    	String[] ipsource_2=new String[2*packets.size()];
	    	int n,m;
	    	int k=0;
	    	for(int g=0;g<(2*count);g++)
	    	 {
	    	    	for(n=g+1;n<(2*count);n++)
	    	    	{
	    	    		if(ipsource[g].equals(ipsource_1[n]))  n=10000;  //n=10000是一个标志，为了跳出循环
	    	    	}
	    	    	if(n!=10001) 
	    	    	{
	    	    		ipsource_2[k]=ipsource[g];
	    	    		k++;
	    	    	}
	    	                
	    	 }
	    	for(int j=0;j<k;j++)
	    	renderer.addTextLabel(j+1,ipsource_2[j]);

	    	    	 
	    	//设置柱状图的柱子的高度，即纵坐标(上传流量)
	    	String source;
	    	long liuliang=0;
	    	double[] q=new double[k];
	    	List<double[]> values = new ArrayList<double[]>(); // 存放绘制柱状图的纵坐标	
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
	    	    	liuliang=0;  //重新置0
	    	 }
	    	    	//double[] q=new double[]{ 0, 12300, 14240, 15244, 15900, 19200,
	    	 		//		22030, 21200, 19500, 15500, 12600, 14000 };
	    	 values.add(q);
	    	
	    	 //设置柱状图的柱子的高度，即纵坐标（下载流量）
	     	String destination_2;
	     	long liuliang_2=0;
	     	double[] p=new double[k];
	     //	List<double[]> values_2 = new ArrayList<double[]>(); // 存放绘制柱状图的纵坐标	
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
	     	    	liuliang_2=0;  //重新置0
	     	 }
	     	    	//double[] q=new double[]{ 0, 12300, 14240, 15244, 15900, 19200,
	     	 		//		22030, 21200, 19500, 15500, 12600, 14000 };
	     	 values.add(p);			

			
			
			/** 
			   * 构建XYMultipleSeriesDataset,适用于柱状图,声明数据设置器
			   *  
			   * @param titles 每柱子序列的图列 
			   * @param values 柱子的高度值 
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
					Type.DEFAULT); //创建View,Intent就是要启动一个GraphicalActivity来显示图表用的
		
		}
	}
}
		
		