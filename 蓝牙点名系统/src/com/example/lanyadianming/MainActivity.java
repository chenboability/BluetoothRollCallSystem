package com.example.lanyadianming;

import com.example.excel.Mainexcel;
import com.example.excel.SDFileExplorer;

import cn.eoe.search.bluetooth.device.Main;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends Activity {

	private Button bt_import, bt_call, bt_set;
	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bt_call = (Button) findViewById(R.id.bt_call);
		bt_import = (Button) findViewById(R.id.bt_import);
		bt_set = (Button) findViewById(R.id.bt_set);
		

		bt_import.setOnClickListener(new Button.OnClickListener() // 导入名单那个按钮
				{
					@Override
					public void onClick(View v) {
						
						Intent intent = new Intent();
						intent.setClass(MainActivity.this, SDFileExplorer.class);
						startActivity(intent);
					}
				});
		bt_call.setOnClickListener(new Button.OnClickListener() // 发起点名的按钮
		{
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, chooselist.class);
				startActivity(intent);
			}
		});
		bt_set.setOnClickListener(new Button.OnClickListener() // 设置的按钮
		{
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MainActivity.this, settings.class);
				startActivity(intent);

			}
		});

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}


}
