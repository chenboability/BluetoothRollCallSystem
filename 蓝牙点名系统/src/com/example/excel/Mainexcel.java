package com.example.excel;


import java.util.Timer;
import java.util.TimerTask;

import com.example.lanyadianming.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

public class Mainexcel extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main);
        Timer timer=new Timer();
        TimerTask task=new TimerTask()
        {
			
			@Override
			public void run() 
			{
				Intent intent=new Intent();
				intent.setClass(Mainexcel.this, main.class);
				startActivity(intent);
				overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
				
			}
		};
		timer.schedule(task, 2000);
		
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
