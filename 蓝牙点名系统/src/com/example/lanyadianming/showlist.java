package com.example.lanyadianming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class showlist extends Activity{

	private Button showlist_bt_back,showlist_bt_start;
	private ListView showlist_listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlist);
		
		showlist_bt_back=(Button) findViewById(R.id.showlist_back);
		showlist_bt_start=(Button) findViewById(R.id.showlist_bt_start);
		showlist_listView=(ListView) findViewById(R.id.showlist_listview);// showlist这个布局下的listview控件
		
		showlist_bt_back.setOnClickListener(new Button.OnClickListener()    //返回按钮
		{
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(showlist.this,chooselist.class);
				startActivity(intent);
				finish();
			}
		}
		);
		
	}

	
	
}
