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
	private EditText et; //�ı��༭��
	private ListView listView;
	private String listname=null;    //������ȡ������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.import_layout);
		
		bt_back=(Button) findViewById(R.id.bt_back);
		bt_choose=(Button) findViewById(R.id.bt_choose);
		et=(EditText) findViewById(R.id.et_listname);
		listView=(ListView) findViewById(R.id.import_listview);
		
		
		bt_back.setOnClickListener(new Button.OnClickListener()  //���ذ�ť
		{
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(importlist.this,MainActivity.class);
				startActivity(intent);
				finish();
				
			}
		}
		);
		bt_choose.setOnClickListener(new Button.OnClickListener()  //ѡ��������ť,���֮����ʾ����������
		{
			@Override
			public void onClick(View v) {
				
				listname=et.getText().toString(); // ������
				
			}
		}
		);
		
		
	}
	

}
