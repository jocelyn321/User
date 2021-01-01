package com.example.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
	private UserSqliteHelper helper;

	// ���췽��
	public UserDao(Context context) {
		helper = new UserSqliteHelper(context);
	}
	
	// ��¼��������ѯ����
	public String login(String username) {
		SQLiteDatabase db = helper.getReadableDatabase();
		String sql = "select * from tbl_user where username=?";
		Cursor c = db.rawQuery(sql, new String[]{username});
		String password = null;
		while (c.moveToNext()) {
			password = c.getString(c.getColumnIndex("password"));
		}
		c.close();
		db.close();
		return password;
	}
	
	// ע�᷽����д�����
	public void regist(String username, String password) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("username", username);
		values.put("password", password);
		db.insert("tbl_user", null, values);
		db.close();
	}
	
	// �޸��û���Ϣ
	public void update(String username, String nick, String sign) {
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		if (nick != null) {
			values.put("nick", nick);
		}
		if (sign != null) {
			values.put("sign", sign);
		}
		db.update("tbl_user", values, "username=?", new String[]{username});
		db.close();
	}
	
	// �����û�����ȡ�û�������Ϣ
	public User getUserByUsername(String username) {
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from tbl_user where username = ?", new String[]{username});
		User user = null;
		while (c.moveToNext()) {
			user = new User();
			user.username = c.getString(c.getColumnIndex("username"));
			user.password = c.getString(c.getColumnIndex("password"));
			user.nick = c.getString(c.getColumnIndex("nick"));
			user.sign = c.getString(c.getColumnIndex("sign"));
			user.sex = c.getString(c.getColumnIndex("sex"));
		}
		return user;
	}

}
