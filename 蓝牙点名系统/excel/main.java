package com.example.excel;
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
private TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=(Button)findViewById(R.id.btn);
        tv1=(TextView)findViewById(R.id.tv);
        Typeface face = Typeface.createFromAsset (getAssets() , "fonts/Kerfuffle.ttf" );//…Ë÷√◊÷ÃÂ
        tv1.setTypeface (face);
        btn1.setTypeface(face);
        btn1.setText("y e s");
        tv1.setText("c o u l d  y o u t h i n k  YuGe  s o  shine?");
        btn1.setOnClickListener(new OnClickListener()
        {
			
			@Override
			public void onClick(View arg0) 
			{
				// TODO Auto-generated method stub
			Intent intent=new Intent(main.this,excel.class);
			startActivity(intent);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
