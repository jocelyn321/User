package com.example.user;

import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistActivity extends Activity {
	private EditText et_username, et_password, et_again;
	private Button btn_regist;
	private UserDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		dao = new UserDao(this);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		et_again = (EditText) findViewById(R.id.et_again);
		btn_regist = (Button) findViewById(R.id.btn_regist);
		btn_regist.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String username = et_username.getText().toString().trim();
				String password = et_password.getText().toString().trim();
				String again = et_again.getText().toString().trim();
				if (TextUtils.isEmpty(username) ||
						TextUtils.isEmpty(password) ||
						TextUtils.isEmpty(again)) {
					ToastUtil.showToast(RegistActivity.this, "请完善信息");
					return;
				}
				if (password.equals(again)) {
					// 把username、password注册到
					addUser(username, password);
					finish();
				} else {
					ToastUtil.showToast(RegistActivity.this, "两次密码输入不一样，请重新输入");
				}
				
			}
		});
	}
	
	public void addUser(String username, String password) {
		String pwd = dao.login(username);
		if (pwd == null) {
			dao.regist(username, password);
		} else {
			ToastUtil.showToast(RegistActivity.this, "该用户名已经被注册过了");
		}
	}
	



}
