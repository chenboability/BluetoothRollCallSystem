package com.example.excel;
import com.example.lanyadianming.MainActivity;
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

public class main extends Activity 
{
private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.excelmain);
        btn1=(Button)findViewById(R.id.btn);
        btn1.setText("y e s");
        btn1.setOnClickListener(new OnClickListener()
        {
			
			@Override
			public void onClick(View arg0) 
			{
			Intent intent=new Intent(main.this,MainActivity.class);
			startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
