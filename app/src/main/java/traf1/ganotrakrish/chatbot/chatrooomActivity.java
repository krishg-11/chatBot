package traf1.ganotrakrish.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class chatrooomActivity extends AppCompatActivity {

    String userEmail;
    String displayName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatrooom);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("EMAIL");
        displayName = intent.getStringExtra("DISPLAYNAME");
        System.out.println("from chat room activity: " + userEmail);
        System.out.println("display name:" + displayName);
    }


}
