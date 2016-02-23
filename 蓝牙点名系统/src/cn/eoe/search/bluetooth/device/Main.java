package cn.eoe.search.bluetooth.device;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.example.lanyadianming.MainActivity;
import com.example.lanyadianming.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends Activity {
	private BluetoothAdapter mBluetoothAdapter;
	ListView lv;
	Button returnmain;
	ProgressBar bar;

	MediaPlayer mediaPlayer;
	myDatabaseHelper mydatabaseHelper;
	public static SQLiteDatabase db;
	Adapter adapter;
	String file_name;
	String dbname;

	Cursor c; // 读数据库，为了写入文件

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.main);

		mediaPlayer = MediaPlayer.create(this, R.raw.pj);
		bar = (ProgressBar)findViewById(R.id.bar);
		lv = (ListView) findViewById(R.id.lv);
		returnmain = (Button)findViewById(R.id.returnmain);

		Bundle bundle = this.getIntent().getExtras();
		file_name = bundle.getString("filename");
		dbname = file_name + ".db3";
		mydatabaseHelper = new myDatabaseHelper(this, dbname, file_name);
		System.out.println(file_name);
		db = mydatabaseHelper.getReadableDatabase();

		c = db.rawQuery("select * from " + file_name, null);

		adapter = new Adapter(this, getData());
		adapter.setfile(file_name);
		lv.setAdapter(adapter);

		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		mBluetoothAdapter.enable();
		returnmain.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Main.this.finish();
			}
		});

		IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
		this.registerReceiver(receiver, filter);

		filter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
		this.registerReceiver(receiver, filter);

	}

	public void onClick_Search(View view) {
		setProgressBarIndeterminateVisibility(true);
		mBluetoothAdapter.enable();
		mBluetoothAdapter.startDiscovery();
		mBluetoothAdapter.enable();
		bar.setVisibility(View.VISIBLE);
	}

	@SuppressWarnings("deprecation")
	public void onClick_overSearch(View view) {
		setProgressBarIndeterminateVisibility(false);
		bar.setVisibility(View.INVISIBLE);
		if (mBluetoothAdapter.isDiscovering()) {
			mBluetoothAdapter.cancelDiscovery();
			mBluetoothAdapter.disable();
		}

		SharedPreferences pref;
		pref = getSharedPreferences("com.example.lanyadianming_preferences",
				Context.MODE_WORLD_READABLE);
		int iscome = pref.getInt("comelist", 2);
		if (iscome == 1) {
			PrintWriter pw = null;
			String sdpath = Environment.getExternalStorageDirectory()
					.toString();
			String comelist = sdpath + "/" + file_name + ".txt";
			try {
				pw = new PrintWriter(new File(comelist));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String line;
			while (c.moveToNext()) {
				String isCount, isCom;
				if (c.getString(3).equals("1")) {
					isCount = "是";
				} else {
					isCount = "否";
				}
				if (c.getString(4).equals("1")) {
					isCom = "已到";
				} else {
					isCom = "未到";
				}
				line = c.getString(1) + " " + c.getString(2) + " " + isCount
						+ " " + isCom;
				pw.println(line);
				System.out.println(line);
			}

			if (pw != null) {
				pw.flush();
				pw.close();
			}
			Toast.makeText(Main.this, "点名情况已生成！", Toast.LENGTH_LONG).show();
		}
		Main.this.finish();

	}

	private final BroadcastReceiver receiver = new BroadcastReceiver() {

		@SuppressLint("WorldReadableFiles")
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if (!alreadySelect(device.getAddress(), device.getName())) {

					if (isNewGuy(device.getAddress(), device.getName())) {
						System.out.println("new");
						// new
						// 记录address
						ContentValues values = new ContentValues();
						values.put(myDatabaseHelper.ADDRESS,
								device.getAddress());
						db.update(file_name, values, myDatabaseHelper.NAME
								+ "=?", new String[] { device.getName() });

						// 打钩数据库
						ContentValues values1 = new ContentValues();
						values1.put(myDatabaseHelper.ISCOME, 1);
						db.update(file_name, values1, myDatabaseHelper.NAME
								+ "=?", new String[] { device.getName() });

					} else {
						System.out.println("old"); // old
						// 打钩数据库
						System.out.println("dagou");
						ContentValues values1 = new ContentValues();
						values1.put(myDatabaseHelper.ISCOME, 1);
						db.update(file_name, values1, myDatabaseHelper.ADDRESS
								+ "=?", new String[] { device.getAddress() });

					}
					// 播放声音

					SharedPreferences pref;
					pref = getSharedPreferences(
							"com.example.lanyadianming_preferences",
							Context.MODE_WORLD_READABLE);
					int issound = pref.getInt("issound", 2);

					if (issound == 1) { // pool.play(sourceid, 1, 1, 0, 0, 1);
						mediaPlayer.start();
					}

					notifyLv();
				}
			} else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED
					.equals(action)) {
				mBluetoothAdapter.startDiscovery();

			}

		}

	};

	private boolean isTrueGuy(String address, String name) {

		System.out.println("in");
		Cursor cursor = db.query(file_name,
				new String[] { myDatabaseHelper.NAME },
				myDatabaseHelper.ADDRESS + " = ? ", new String[] { address },
				null, null, null, null);
		if (cursor.getCount() > 0) {
			System.out.println(">0");
			// 查到
			cursor.moveToNext();
			if (cursor.getString(0).equals(name))
				return true;
		}
		return false;
	}

	protected boolean alreadySelect(String address, String name) {
		Cursor cursor = db.query(true, file_name,
				new String[] { myDatabaseHelper.ISCOME },
				myDatabaseHelper.ADDRESS + " = ?", new String[] { address },
				null, null, null, null);
		if (cursor.moveToFirst()) {
			if (cursor.getInt(0) == 1) {
				System.out.println("111");
				return true;

			} else {
				System.out.println("000");
				return false;

			}
		}
		System.out.println("000");
		return false;
	}

	private boolean isNewGuy(String address, String name) {

		int flag = 0;
		Cursor cursor = db.query(true, file_name,
				new String[] { myDatabaseHelper.ADDRESS },
				myDatabaseHelper.ADDRESS + " = ?", new String[] { address },
				null, null, null, null);
		if (cursor.getCount() == 0) {
			// 查不到address
			flag++;
		}
		System.out.println("flag1=" + flag);
		Cursor cursor1 = db
				.query(true, file_name, new String[] { myDatabaseHelper.NAME },
						myDatabaseHelper.NAME + " = ?", new String[] { name },
						null, null, null, null);
		if (cursor1.getCount() > 0) {
			// 查到name
			flag++;
		}
		System.out.println("flag2=" + flag);
		if (flag == 2)
			return true;
		return false;

	}

	private void notifyLv() {
		adapter = new Adapter(this, getData());
		lv.setAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	private List<item> getData() { // 添加数据

		List<item> lists = new ArrayList<item>();

		Cursor cursor = db.rawQuery("select * from " + file_name, null);
		lists = converCursotToList(cursor);
		return lists;

	}

	ArrayList<item> converCursotToList(Cursor cursor) {

		ArrayList<item> lists = new ArrayList<item>();
		if (cursor.getCount() != 0) {
			while (cursor.moveToNext()) {
				item i = new item();

				i.setMark(cursor.getInt(0));
				i.setName(cursor.getString(1));
				i.setNumber(cursor.getString(2));
				i.setState(cursor.getInt(3));
				i.setIsComeOrNot(cursor.getInt(4));
				i.setAddress(cursor.getString(5));

				lists.add(i);
			}

			return lists;
		}
		return null;

	}

}