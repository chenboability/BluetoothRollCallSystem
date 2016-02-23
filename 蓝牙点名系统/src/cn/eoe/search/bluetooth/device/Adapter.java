package cn.eoe.search.bluetooth.device;

import java.util.List;


import com.example.lanyadianming.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

	private Context context;
	private List<item> lists;
	private LayoutInflater inflater;

	String file_name;
	public Adapter(Context context, List<item> lists) {
		super();
		this.context = context;
		this.lists = lists;
	}

	void setfile(String name){
		file_name=name;
	}
	@Override
	public int getCount() {

		return lists.size();
	}

	@Override
	public Object getItem(int position) {
		return lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			inflater = LayoutInflater.from(parent.getContext());
			convertView = inflater.inflate(R.layout.listview_item, null);
			viewHolder = new ViewHolder();

			viewHolder.name = (TextView) convertView.findViewById(R.id.name);
			viewHolder.number = (TextView) convertView
					.findViewById(R.id.number);
			viewHolder.state = (MyCheckBox) convertView.findViewById(R.id.state);
			viewHolder.isCome = (MyCheckBox) convertView
					.findViewById(R.id.iscome);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// 单击状态按钮，改变数据库
		item i = (item) getItem(position);
		viewHolder.state.setIndex(i.getMark());
		System.out.println(i.getMark());
		viewHolder.isCome.setIndex(i.getMark());
		
		MyListener.getInstance().set(file_name);
		viewHolder.state.setOnClickListener(MyListener.getInstance());
		viewHolder.isCome.setOnClickListener(MyListener.getInstance());

		String nameStr = lists.get(position).getName();
		String numberStr = lists.get(position).getNumber();
		int stateStr = lists.get(position).getState();
		int isComeStr = lists.get(position).getIsComeOrNot();

		if (stateStr == 1)
			viewHolder.state.setChecked(true);
		else
			viewHolder.state.setChecked(false);

		if (isComeStr == 1)
			viewHolder.isCome.setChecked(true);
		else
			viewHolder.isCome.setChecked(false);

		viewHolder.name.setText(nameStr);
		viewHolder.number.setText(numberStr);

		return convertView;
	}

	static class ViewHolder {

		public TextView name;
		public TextView number;
		public MyCheckBox state;
		public MyCheckBox isCome;

	}
}