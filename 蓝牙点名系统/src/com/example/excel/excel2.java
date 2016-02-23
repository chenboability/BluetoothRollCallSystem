package com.example.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.eoe.search.bluetooth.device.Listhelper;
import cn.eoe.search.bluetooth.device.myDatabaseHelper;

import com.example.lanyadianming.MainActivity;
import com.example.lanyadianming.R;
import com.example.lanyadianming.chooselist;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import android.R.string;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class excel2 extends Activity {

	public static final String MARK = "mark";
	public static final String NAME = "name";
	public static final String NUMBER = "studentNumber";
	public static final String STATE = "state";
	public static final String ISCOME = "isComeOrNot";
	public static final String ADDRESS = "address";

	Button button1;
	Button button2;
	List<String> list1;
	List<String> list2;
	Listhelper listhelper;
	public SQLiteDatabase db2;
	ArrayList<HashMap<String, String>> al;
	ListView lv;
	myDatabaseHelper mydatabaseHelper;
	public SQLiteDatabase db;
	String dbname;
	String strings;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.execel2_main);
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
		Bundle bundle = this.getIntent().getExtras();
		String string = bundle.getString("name");
		strings = bundle.getString("names");
		lv = (ListView) findViewById(R.id.listView1);
		al = new ArrayList<HashMap<String, String>>();
		AssetManager am = this.getAssets();
		InputStream is = null;

		listhelper = new Listhelper(this, "allexcel.db3", null, 1);
		db2 = listhelper.getWritableDatabase();
		try {

			is = new FileInputStream(string);
			Workbook wb = Workbook.getWorkbook(is);

			Sheet sheet = wb.getSheet(0);
			int row = sheet.getRows();
			HashMap<String, String> hm;

			list1 = new ArrayList<String>();
			list2 = new ArrayList<String>();
			for (int i = 0; i < row; ++i) {
				Cell cellarea = sheet.getCell(0, i);
				Cell cellschool = sheet.getCell(1, i);
				System.out.println(cellarea.getContents() + ":"
						+ cellschool.getContents());
				hm = new HashMap<String, String>();
				hm.put("AREA", cellarea.getContents());
				hm.put("SCHOOL", cellschool.getContents());
				al.add(hm);
				list1.add(cellarea.getContents());
				list2.add(cellschool.getContents());

			}
			SimpleAdapter sa = new SimpleAdapter(this, al, R.layout.lv_item,
					new String[] { "AREA", "SCHOOL" }, new int[] {
							R.id.tv_area, R.id.tv_school });
			lv.setAdapter(sa);

		} catch (Exception e) {
			e.printStackTrace();
		}
		button2.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				ContentValues value2 = new ContentValues();
				value2.put("dbnames", strings);
				db2.insert("allexcel", null, value2);
		         
				dbname = strings + ".db3";
				mydatabaseHelper = new myDatabaseHelper(excel2.this, dbname, strings);
				db = mydatabaseHelper.getWritableDatabase();
				for (int i = 0; i < list1.size(); i++) {
					ContentValues value = new ContentValues();

					value.put("name", list1.get(i));
					value.put("studentNumber", list2.get(i));
					value.put("state", "1");
					value.put("isComeOrNot", "0");
					value.put("address", "000");

					db.insert(strings, null, value);
				}
				
				Toast.makeText(excel2.this, "excel表格导入成功", Toast.LENGTH_LONG)
						.show();
				startActivity(new Intent(excel2.this, chooselist.class));
				excel2.this.finish();

			}

		});
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(excel2.this, MainActivity.class);
				finish();
			}

		});

	}

}
