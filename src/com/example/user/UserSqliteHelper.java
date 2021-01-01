package com.example.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSqliteHelper extends SQLiteOpenHelper {

	// 创建数据库
	public UserSqliteHelper(Context context) {
		super(context, "helper.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	// 创建数据表
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table if not exists tbl_user(" +
				"id integer primary key autoincrement," +
				"username varchar(20), " +
				"password varchar(20), " +
				"nick varchar(20), " +
				"sign varchar(40), " +
				"sex varchar(2)"
				+ ")");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists tbl_user");
		onCreate(db);
	}

	

}
