package cn.eoe.search.bluetooth.device;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class myDatabaseHelper extends SQLiteOpenHelper {

	public  String TABLE_DB = "myDB";
	public static final String MARK = "mark";
	public static final String NAME = "name";
	public static final String NUMBER = "studentNumber";
	public static final String STATE = "state";
	public static final String ISCOME = "isComeOrNot";
	public static final String ADDRESS = "address";
   
	

	public myDatabaseHelper(Context context, String name,String fliename) {
		super(context, name, null, 1);
		TABLE_DB=fliename;
	}
	public myDatabaseHelper(Context context, String name){
		super(context, name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println(TABLE_DB);
		String CREATE_TABLE = "create table if not exists " + TABLE_DB + "("
				+ MARK + " integer primary key autoincrement," + NAME
				+ " varchar(10)," + NUMBER + " varchar(20)," + STATE + " integer,"
				+ ISCOME + " integer," + ADDRESS + " varchar(30))";
		db.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
