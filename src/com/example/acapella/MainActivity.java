package com.example.acapella;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private ButtonClickListener buttonListener = new ButtonClickListener();
	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        EditText leftEditText = (EditText)findViewById(R.id.editText1);
        EditText rightEditText = (EditText)findViewById(R.id.editText2);
        
        Button leftButton = (Button)findViewById(R.id.button1);
        leftButton.setOnClickListener(this.buttonListener);
        Button rightButton = (Button)findViewById(R.id.button2);
        rightButton.setOnClickListener(this.buttonListener);
        
        Button navigateToSecondaryActivityButton = (Button)findViewById(R.id.button3);
        navigateToSecondaryActivityButton.setOnClickListener(buttonListener);  
        
        if (savedInstanceState != null) {
        	String leftCount = savedInstanceState.getString("leftCount");
            if (leftCount != null) {
              leftEditText.setText(leftCount);
            } else {
              leftEditText.setText(String.valueOf(0));
            }
            String rightCount = savedInstanceState.getString("rightCount");
            if (rightCount != null) {
              rightEditText.setText(rightCount);
            } else {
              rightEditText.setText(String.valueOf(0));
            }
        }
        else {
            leftEditText.setText(String.valueOf(0));
            rightEditText.setText(String.valueOf(0));
          }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
      Toast.makeText(this, "The activity returned with result "+resultCode, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        EditText leftEditText = (EditText)findViewById(R.id.editText1);
        EditText rightEditText = (EditText)findViewById(R.id.editText2);
        savedInstanceState.putString("leftCount", leftEditText.getText().toString());
        savedInstanceState.putString("rightCount", rightEditText.getText().toString());
    }
    
    public class ButtonClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			EditText leftText = (EditText)MainActivity.this.findViewById(R.id.editText1);
			EditText rightText = (EditText)MainActivity.this.findViewById(R.id.editText2);
			
			int leftNumber = Integer.parseInt(leftText.getText().toString());
			int rightNumber = Integer.parseInt(rightText.getText().toString());
			
			switch (v.getId()) {
			case R.id.button1:
				leftNumber++;
				leftText.setText(String.valueOf(leftNumber));
				break;
			case R.id.button2:
				rightNumber++;
				rightText.setText(String.valueOf(rightNumber));
				break;
			case R.id.button3:
		          Intent intent = new Intent("ro.pub.cs.systems.pdsd.intent.action.MyIntent");
		          intent.putExtra("number_of_clicks",
		            String.valueOf(
		              Integer.parseInt(leftText.getText().toString())
		              + Integer.parseInt(rightText.getText().toString())
		            ));
		          startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
		          break;

			default:
				break;
			}
			
		}
    	
    }
}
