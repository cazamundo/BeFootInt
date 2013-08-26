/**
 * 
 */
package com.cazamundo.android.befootint.util;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;

/**
 * @author cazamundo
 * 
 */
public abstract class AbstractAsyncActivity extends FragmentActivity implements
		AsyncActivity {

	protected static final String TAG = AbstractAsyncActivity.class
			.getSimpleName();

	private ProgressDialog progressDialog;

	private boolean destroyed = false;

	// ***************************************
	// Activity methods
	// ***************************************
	@Override
	public MainApplication getApplicationContext() {
		return (MainApplication) super.getApplicationContext();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.destroyed = true;
	}

	// ***************************************
	// Public methods
	// ***************************************
	@Override
	public void showLoadingProgressDialog() {
		this.showProgressDialog("Even geduld..");
	}

	@Override
	public void showProgressDialog(CharSequence message) {
		if (this.progressDialog == null) {
			this.progressDialog = new ProgressDialog(this);
			this.progressDialog.setIndeterminate(true);
		}
		this.progressDialog.setMessage(message);
		this.progressDialog.show();
	}

	@Override
	public void dismissProgressDialog() {
		if (this.progressDialog != null && !this.destroyed) {
			this.progressDialog.dismiss();
		}
	}

}
