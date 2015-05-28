package me.arkits.panchayat;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class singleitem extends ActionBarActivity {

    String title;
    String message;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singleitem);

        Intent i = getIntent();
       title = i.getStringExtra("title");
    //   message = i.getStringExtra("message");
    //  username = i.getStringExtra("username");
      //title = "title here";
       message = "message here";
       username = "arkits";

        TextView txttitle = (TextView) findViewById(R.id.title);
        TextView txtmessage = (TextView) findViewById(R.id.message);
        TextView txtusername = (TextView) findViewById(R.id.username);

        txttitle.setText(title);
        txtmessage.setText(message);
        txtusername.setText(username);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_singleitem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
