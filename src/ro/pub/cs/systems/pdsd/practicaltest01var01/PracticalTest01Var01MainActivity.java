package ro.pub.cs.systems.pdsd.practicaltest01var01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class PracticalTest01Var01MainActivity extends Activity {
	protected CheckboxListener checkboxListener = new CheckboxListener();
	protected ButtonClickListener buttonClickListener = new ButtonClickListener();
	protected CompoundButton phoneCheck, emailCheck, messageCheck;
	Button navigateToActivity;
	
	private class CheckboxListener implements CompoundButton.OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			EditText numberOfCheckedText = (EditText)findViewById(R.id.number_checked);
			int numberOfChecked = Integer.valueOf(numberOfCheckedText.getText().toString());
			
			if (isChecked) {
				numberOfChecked++;
				numberOfCheckedText.setText(String.valueOf(numberOfChecked));
			} else {
				numberOfChecked--;
				numberOfCheckedText.setText(String.valueOf(numberOfChecked));
			}
			
			
		}
		
	}
	
	private class ButtonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent("ro.pub.cs.systems.pdsd.intent.action.PracticalTest01Var01SecondaryActivity");
			if (intent != null) {
				String methods = findViewById(R.id.check_phone).getContentDescription().toString() +
								findViewById(R.id.check_email).getContentDescription().toString() +
								findViewById(R.id.check_imsg).getContentDescription().toString();
				intent.putExtra("listOfMethods", methods);
				startActivityForResult(intent, 1);
			}
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var01_main);
		
		phoneCheck = (CompoundButton)findViewById(R.id.check_phone);
		emailCheck = (CompoundButton)findViewById(R.id.check_email);
		messageCheck = (CompoundButton)findViewById(R.id.check_imsg);
		navigateToActivity = (Button)findViewById(R.id.navigate_activity);
		
		phoneCheck.setOnCheckedChangeListener(checkboxListener);
		emailCheck.setOnCheckedChangeListener(checkboxListener);
		messageCheck.setOnCheckedChangeListener(checkboxListener);
		navigateToActivity.setOnClickListener(buttonClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var01_main, menu);
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
		super.onSaveInstanceState(savedInstanceState);
		
		savedInstanceState.putBoolean("phoneCheck",phoneCheck.isChecked());
		savedInstanceState.putBoolean("emailCheck",emailCheck.isChecked());
		savedInstanceState.putBoolean("messageCheck",messageCheck.isChecked());
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		
		phoneCheck.setChecked(savedInstanceState.getBoolean("phoneCheck"));
		emailCheck.setChecked(savedInstanceState.getBoolean("emailCheck"));
		messageCheck.setChecked(savedInstanceState.getBoolean("messageCheck"));
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Toast.makeText(this, "The activity returned with result: " + resultCode, Toast.LENGTH_LONG).show();
	}
}
