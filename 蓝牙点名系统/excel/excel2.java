package com.example.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import android.R.string;
import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class excel2 extends Activity 
{
ArrayList<HashMap<String,String>> al;
ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.execel2_main);
		Bundle bundle=this.getIntent().getExtras();
	//String string="mnt/sdcard/"+bundle.getString("name")+".xls";
	String	string=bundle.getString("name");
		 lv=(ListView) findViewById(R.id.listView1);
	     al=new ArrayList<HashMap<String,String>>();
	       AssetManager am=this.getAssets();
	        InputStream is=null;
	        try 
	        {
	        	
				//is=am.open(string);
	        	is = new FileInputStream(string); 
				Workbook wb=Workbook.getWorkbook(is);
			 
				Sheet sheet=wb.getSheet(0);
				int row=sheet.getRows();
				HashMap<String,String> hm;
				for(int i=0;i<row;++i)
				{
					Cell cellarea=sheet.getCell(0, i);
					Cell cellschool=sheet.getCell(1, i);
					System.out.println(cellarea.getContents()+":"+cellschool.getContents());
					hm=new HashMap<String,String>();
					hm.put("AREA", cellarea.getContents());
					hm.put("SCHOOL", cellschool.getContents());
					al.add(hm);
				}
				SimpleAdapter sa=new SimpleAdapter(this,al,R.layout.lv_item,new String[]{"AREA","SCHOOL"},new int[]{R.id.tv_area,R.id.tv_school});
				lv.setAdapter(sa);
				 
				 
				 
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	       
		
		
	}

}
