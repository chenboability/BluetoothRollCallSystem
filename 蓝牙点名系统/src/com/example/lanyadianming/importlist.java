package com.example.lanyadianming;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class importlist   extends Activity{

	private Button  bt_back,bt_choose;
	private EditText et; //文本编辑框
	private ListView listView;
	private String listname=null;    //用来获取名单名
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.import_layout);
		
		bt_back=(Button) findViewById(R.id.bt_back);
		bt_choose=(Button) findViewById(R.id.bt_choose);
		et=(EditText) findViewById(R.id.et_listname);
		listView=(ListView) findViewById(R.id.import_listview);
		
		
		bt_back.setOnClickListener(new Button.OnClickListener()  //返回按钮
		{
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(importlist.this,MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		}
		);
		bt_choose.setOnClickListener(new Button.OnClickListener()  //选择名单按钮,点击之后显示出名单内容
		{
			@Override
			public void onClick(View v) {
				
				listname=et.getText().toString(); // 名单名
				
			}
		}
		);
		
		
	}
	

}
