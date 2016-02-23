package com.example.excel;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class excel extends Activity
{
private Button enter;
private EditText ed;
private Button liulan;
@Override
protected void onCreate(Bundle savedInstanceState) 
{
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.excel_layout);
	enter=(Button)findViewById(R.id.enter);
	ed=(EditText)findViewById(R.id.ed);
	liulan=(Button)findViewById(R.id.liulan);
	 Typeface face = Typeface.createFromAsset (getAssets() , "fonts/Kerfuffle.ttf" );//…Ë÷√◊÷ÃÂ
    enter.setTypeface(face);

     enter.setOnClickListener(new OnClickListener()
     {
			
			@Override
			public void onClick(View arg0) 
			{
		// TODO Auto-generated method stub
	        String string=ed.getText().toString();
			Intent intent=new Intent();
			intent.setClass(excel.this, excel2.class);
			Bundle bundle=new Bundle();
			bundle.putString("name", string);
			intent.putExtras(bundle);
			startActivity(intent);
			}
		});
     liulan.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) 
		{
			// TODO Auto-generated method stub
    Intent intent=new Intent();
	intent.setClass(excel.this, SDFileExplorer.class);
	startActivity(intent);
		}
	});
 }
     
}
	

