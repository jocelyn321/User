package com.example.user;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserInfoActivity extends BaseActivity implements OnClickListener {
	private RelativeLayout rl_nick, rl_sign;
	private TextView tv_nick, tv_sign;
	private SharedPreferences sp;
	private UserDao dao;
	
	// 生命周期方法
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		sp = getSharedPreferences("data", MODE_PRIVATE);
		dao = new UserDao(this);
		rl_nick = (RelativeLayout) findViewById(R.id.rl_nick);
		rl_sign = (RelativeLayout) findViewById(R.id.rl_sign);
		tv_nick = (TextView) findViewById(R.id.tv_nick);
		tv_sign = (TextView) findViewById(R.id.tv_sign);
		rl_nick.setOnClickListener(this);
		rl_sign.setOnClickListener(this);
	}
	
	// 生命周期方法，创建页面，从不可见变为可见，onResume()都会被调用
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		String username = sp.getString("login", null);
		User user = dao.getUserByUsername(username);
		if (user != null) {
			tv_nick.setText(user.nick);
			tv_sign.setText(user.sign);
		}
	}

	@Override
	public void onClick(View v) {
		Intent i = new Intent(UserInfoActivity.this, ChangeUserInfoActivity.class);
		TextView tv1 = (TextView) ((RelativeLayout) v).getChildAt(0);
		TextView tv2 = (TextView) ((RelativeLayout) v).getChildAt(1);
		i.putExtra("title", tv1.getText().toString());
		i.putExtra("hint", tv2.getText().toString());
		switch (v.getId()) {
		case R.id.rl_nick:
			i.putExtra("type", 0);
			break;
		case R.id.rl_sign:
			i.putExtra("type", 1);
			break;
		}
		startActivity(i);
	}
	
	

}
