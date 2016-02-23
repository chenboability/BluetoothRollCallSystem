package cn.eoe.search.bluetooth.device;

import com.example.lanyadianming.R;

import android.content.ContentValues;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class MyListener implements OnClickListener {

	String file_name;
	
	private static MyListener instance = null;

	

	public static MyListener getInstance() {
		if (instance == null)
			instance = new MyListener();
		return instance;
	}
    
	public MyListener(){
		
	}
	void set(String name){
		file_name=name;
	}
	@Override
	public void onClick(View v) {
		int mark = ((MyCheckBox) v).getIndex();
		int id_Box = ((MyCheckBox) v).getId();
		boolean isCheck;
		 if (((MyCheckBox) v).isChecked()) {
			 isCheck = true;
	        } else {
	        	isCheck = false;
	        }

		if (id_Box == R.id.state){
			updateItemOpen(mark, isCheck, true);
		}
		else if (id_Box == R.id.iscome){
			updateItemOpen(mark, isCheck, false);
		}
	}
	
	public void updateItemOpen(int mark, boolean isChecked, boolean isState) {
		ContentValues values = new ContentValues();
		System.out.println(mark + "µã»÷" + isChecked + "d " + isState);

		if (isState) {// state
			if (isChecked) {
				values.put(myDatabaseHelper.STATE, 1);
				Main.db.update(file_name, values,
						myDatabaseHelper.MARK + "=?",
						new String[] { mark + "" });

			} else {
				values.put(myDatabaseHelper.STATE, 0);
				Main.db.update(file_name, values,
						myDatabaseHelper.MARK + "=?",
						new String[] { mark + "" });
			}
		} else {// iscome
			if (isChecked) {
				values.put(myDatabaseHelper.ISCOME, 1);
				Main.db.update(file_name, values,
						myDatabaseHelper.MARK + "=?",
						new String[] { mark + "" });

			} else {
				values.put(myDatabaseHelper.ISCOME, 0);
				Main.db.update(file_name, values,
						myDatabaseHelper.MARK + "=?",
						new String[] { mark + "" });
			}
		}

	}

}
