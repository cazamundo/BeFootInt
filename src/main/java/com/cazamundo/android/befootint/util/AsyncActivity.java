/**
 * 
 */
package com.cazamundo.android.befootint.util;



/**
 * @author cazamundo
 *
 */
public interface AsyncActivity {
	public MainApplication getApplicationContext();
	
	public void showLoadingProgressDialog();
	
	public void showProgressDialog(CharSequence message);
		
	public void dismissProgressDialog(); 

}
