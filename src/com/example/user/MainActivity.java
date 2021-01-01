package com.example.user;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private LinearLayout ll_myinfo;
	private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("data", MODE_PRIVATE);
        ll_myinfo = (LinearLayout) findViewById(R.id.ll_myinfo);
        ll_myinfo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String login = sp.getString("login", null);
				Intent intent = null;
				if (login == null) {
					intent = new Intent(MainActivity.this, LoginActivity.class);
				} else {
					intent = new Intent(MainActivity.this, MyInfoActivity.class);
				}
				startActivity(intent);
			}
		});
    }


}
