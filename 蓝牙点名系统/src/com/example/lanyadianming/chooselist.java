package com.example.lanyadianming;

import java.util.ArrayList;
import java.util.List;

import cn.eoe.search.bluetooth.device.Listhelper;
import cn.eoe.search.bluetooth.device.Main;
import cn.eoe.search.bluetooth.device.myDatabaseHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class chooselist extends Activity {
	String[] excellist = new String[30];
	private Button chooselist_back;
	private ListView chooseList_listView;
	myDatabaseHelper dphelper;
	Listhelper listhelper;
	SQLiteDatabase db;
	SQLiteDatabase db2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chooselist);

		chooselist_back = (Button) findViewById(R.id.chooselist_back);
		chooseList_listView = (ListView) findViewById(R.id.chooselist_listview);

		listhelper = new Listhelper(this, "allexcel.db3", null, 1);
		db2 = listhelper.getReadableDatabase();
		Cursor c = db2.rawQuery("select dbnames from allexcel", null);

		List<String> list = new ArrayList<String>();
		int i = 0;
		while (c.moveToNext()) {
			String dbname = c.getString(0) + ".db3";
			dphelper = new myDatabaseHelper(this, dbname);
			db = dphelper.getReadableDatabase();
			Cursor cursor = db
					.rawQuery(
							"select name from sqlite_master where type='table' order by name",
							null);
			while (cursor.moveToNext()) {
				// 遍历出表名
				String name = cursor.getString(0);
				if (name.equals("android_metadata")
						|| name.equals("sqlite_metadata")
						|| name.equals("sqlite_sequence")
						|| name.equals("myDB"))
					continue;
				list.add(name);
				excellist[i++] = new String(name);
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.documetname, R.id.name, list);
		chooseList_listView.setAdapter(adapter);
		chooseList_listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// 点击后在标题上显示点击了第几行 setTitle("你点击了第"+arg2+"行");
				System.out.println(excellist[arg2]);
				clear1(excellist[arg2]);
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("filename", excellist[arg2]);
				intent.setClass(chooselist.this, Main.class);
				intent.putExtras(bundle);
				startActivity(intent);
				chooselist.this.finish();
			}

			

		});
		
		chooselist_back.setOnClickListener(new Button.OnClickListener() // 返回按钮
				{
					@Override
					public void onClick(View v) {
						finish();
					}
				});

	}
	private void clear1(String string) {
		ContentValues values1 = new ContentValues();
		values1.put(myDatabaseHelper.ISCOME, 0);
		db.update(string, values1, null, null);
	}

}
