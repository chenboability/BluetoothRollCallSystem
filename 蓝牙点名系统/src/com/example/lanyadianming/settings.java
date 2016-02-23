package com.example.lanyadianming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class settings extends Activity {

	private Button settings_back;
	private CheckBox cb_sounds, cb_list;
	private SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		settings_back = (Button) findViewById(R.id.settings_back);
		cb_list = (CheckBox) findViewById(R.id.cb_list);
		cb_sounds = (CheckBox) findViewById(R.id.cb_sounds);

		pref = getSharedPreferences("com.example.lanyadianming_preferences",
				Context.MODE_WORLD_READABLE);
		if (pref.getInt("comelist", 2) == 1)
			cb_list.setChecked(true);
		else
			cb_list.setChecked(false);

		if (pref.getInt("issound", 2) == 1)
			cb_sounds.setChecked(true);
		else
			cb_sounds.setChecked(false);
		settings_back.setOnClickListener(new Button.OnClickListener() // 选择名单按钮,点击之后显示出名单内容
				{
					@SuppressWarnings("deprecation")
					@Override
					public void onClick(View v) {
						finish();
					}
				});

		// 监听checkbox
		cb_list.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {

			// 生成考勤名单那个checkbox
			@SuppressWarnings("deprecation")
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				pref = getSharedPreferences(
						"com.example.lanyadianming_preferences",
						MODE_WORLD_WRITEABLE);
				Editor editor = pref.edit();
				if (isChecked) {
					editor.putInt("comelist", 1);
					Toast.makeText(getApplicationContext(), "考勤名单生成已设置！",
							Toast.LENGTH_LONG).show();

				} else {
					editor.putInt("comelist", 0);
				}
				editor.commit();
			}
		});

		cb_sounds
				.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
					// 声音提醒那个checkbox
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						pref = getSharedPreferences(
								"com.example.lanyadianming_preferences",
								Context.MODE_WORLD_WRITEABLE);
						Editor editor = pref.edit();
						if (isChecked) {
							editor.putInt("issound", 1);
							Toast.makeText(getApplicationContext(), "声音提醒已设置！",
									Toast.LENGTH_LONG).show();
						} else {
							editor.putInt("issound", 0);
						}
						editor.commit();
					}
				});

	}

}
