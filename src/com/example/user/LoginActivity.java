package com.example.user;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText et_username, et_password;
	private Button btn_regist;
	private Button btn_login;
	private SharedPreferences sp;
	private UserDao dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dao = new UserDao(this);
		sp = getSharedPreferences("data", MODE_PRIVATE);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_regist = (Button) findViewById(R.id.btn_regist);
		btn_login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String username = et_username.getText().toString().trim();
				String password = et_password.getText().toString().trim();
				if (TextUtils.isEmpty(username) ||
						TextUtils.isEmpty(password)) {
					ToastUtil.showToast(LoginActivity.this, "请完善信息");
					return;
				}
				if (password.equals(dao.login(username))) {
					ToastUtil.showToast(LoginActivity.this, username + "登录成功");
					Editor e = sp.edit();
					e.putString("login", username);
					e.commit();
					finish();
				} else {
					ToastUtil.showToast(LoginActivity.this, "用户名或密码错误");
				}
			}
		});
		btn_regist.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this, RegistActivity.class);
				startActivity(intent);
			}
		});
	}

	
	

}
