package com.example.acapella;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {

	private ButtonClickListener buttonListener = new ButtonClickListener();

	public class ButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			switch(v.getId()) {
	        	case R.id.ok_button:
	        		setResult(RESULT_OK, new Intent());
	        		finish();
	        		break;
	        	case R.id.cancel_button:
	        		setResult(RESULT_CANCELED, new Intent());
	        		finish();
	        		break;
	      }
			
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		TextView numberOfClicksTextView = (TextView)findViewById(R.id.number_of_clicks_text_view);
	    Intent intent = getIntent();
	    if (intent != null) {
	      String numberOfClicks = intent.getStringExtra(Messages.getString("SecondActivity.0")); //$NON-NLS-1$
	      if (numberOfClicks != null) {
	        numberOfClicksTextView.setText(numberOfClicks); //$NON-NLS-1$
	      }
	    }
	 
	    Button buttonOk = (Button)findViewById(R.id.ok_button);
	    buttonOk.setOnClickListener(buttonListener);
	    Button buttonCancel = (Button)findViewById(R.id.cancel_button);
	    buttonCancel.setOnClickListener(buttonListener); 
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
