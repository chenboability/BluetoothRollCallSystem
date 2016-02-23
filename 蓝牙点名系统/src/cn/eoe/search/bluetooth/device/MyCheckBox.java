package cn.eoe.search.bluetooth.device;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Switch;

public class MyCheckBox extends CheckBox {

	private int index = -1;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MyCheckBox(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyCheckBox(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyCheckBox(Context context) {
		super(context);
	}

}
