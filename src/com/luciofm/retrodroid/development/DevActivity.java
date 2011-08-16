package com.luciofm.retrodroid.development;

import android.app.Activity;
import android.os.Bundle;

import com.luciofm.retrodroid.development.viewserver.ViewServer;

public class DevActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewServer.get(this).addWindow(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ViewServer.get(this).removeWindow(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		ViewServer.get(this).setFocusedWindow(this);
	}
}
