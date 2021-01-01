package com.example.user;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

// Activity:爷爷，BaseActivity:爸爸，ChangeUserInfoActivity:儿子
public class ChangeUserInfoActivity extends BaseActivity {
	private TextView tv_title;
	private EditText et_input;
	private TextView tv_save;
	private SharedPreferences sp;
	private UserDao dao;
	private int type = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_user_info);
		sp = getSharedPreferences("data", MODE_PRIVATE);
		dao = new UserDao(this);
		tv_title = (TextView) findViewById(R.id.tv_title);
		et_input = (EditText) findViewById(R.id.et_input);
		tv_save = (TextView) findViewById(R.id.tv_save);
		tv_save.setVisibility(View.VISIBLE); // tv_save显示
		Intent intent = getIntent();
		final String title = intent.getStringExtra("title");
		String hint = intent.getStringExtra("hint");
		type = intent.getIntExtra("type", 0);
		tv_title.setText(title);
		et_input.setHint(hint);
		tv_save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String input = et_input.getText().toString();
				if (TextUtils.isEmpty(input)) {
					ToastUtil.showToast(ChangeUserInfoActivity.this, "请输入" + title);
					return;
				}
				String username = sp.getString("login", null);
				if (type == 0) {
					dao.update(username, input, null);
				} else {
					dao.update(username, null, input);
				}
				ToastUtil.showToast(ChangeUserInfoActivity.this, "修改" + title + "成功");
				finish();
			}
		});
	}


}
