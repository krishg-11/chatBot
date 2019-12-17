package traf1.ganotrakrish.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class chatrooomActivity extends AppCompatActivity {

    String userEmail;
    String displayName;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatrooom);
        recyclerView = findViewById(R.id.recyclerView);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("EMAIL");
        displayName = intent.getStringExtra("DISPLAYNAME");
        System.out.println("from chat room activity: " + userEmail);
        System.out.println("display name:" + displayName);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<List<String>> input = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            List<String> temp = new ArrayList<>();
            temp.add("Test");
            temp.add(i+"");
            input.add(temp);
        }// define an adapter
        mAdapter = new MyAdapter(input);
        recyclerView.setAdapter(mAdapter);

    }


}
