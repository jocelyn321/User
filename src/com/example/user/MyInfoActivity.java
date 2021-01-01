package com.example.user;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyInfoActivity extends Activity {
	private Button btn_logout;
	private ImageView iv_back;
	private SharedPreferences sp;
	private LinearLayout ll_nick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_info);
		sp = getSharedPreferences("data", MODE_PRIVATE);
		iv_back = (ImageView) findViewById(R.id.iv_back);
		iv_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btn_logout = (Button) findViewById(R.id.btn_logout);
		ll_nick = (LinearLayout) findViewById(R.id.ll_nick);
		btn_logout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Editor e = sp.edit();
				e.clear(); // 清空SharedPreferences存过的数据
				e.commit();
				finish();
			}
		});
		ll_nick.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(MyInfoActivity.this, UserInfoActivity.class);
				startActivity(i);
			}
		});
	}

}
