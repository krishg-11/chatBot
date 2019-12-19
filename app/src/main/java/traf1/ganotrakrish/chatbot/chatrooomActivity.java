package traf1.ganotrakrish.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class chatrooomActivity extends AppCompatActivity {

    String userEmail;
    String displayName;
    public EditText input;
    public FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    List<List<String>> texts;
    DatabaseReference myRef;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    int count = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatrooom);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        input = findViewById(R.id.input);
        myRef = database.getReference("messages/");


        Intent intent = getIntent();
        userEmail = intent.getStringExtra("EMAIL");
        displayName = intent.getStringExtra("DISPLAYNAME");
        System.out.println("from chat room activity: " + userEmail);
        System.out.println("display name:" + displayName);

        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        texts = new ArrayList<>();

        List<String> temp = new ArrayList<>();

        mAdapter = new MyAdapter(texts);
        recyclerView.setAdapter(mAdapter);


        fab.setOnClickListener(fabListener);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                texts.clear();
                count = 1;
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    List<String> temp = new ArrayList<>();
                    temp.add(ds.child("email").getValue()+"");
                    temp.add(ds.child("message").getValue()+"");
                    texts.add(temp);
                    System.out.println("email? " + ds.child("email").getValue());
                    mAdapter = new MyAdapter(texts);
                    recyclerView.setAdapter(mAdapter);
                    count++;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // ...
            }
        });

    }
    View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(input.getText().toString().length()==0)
            myRef = database.getReference("/messages/"+count+"/email");
            myRef.setValue(userEmail);
            myRef = database.getReference("/messages/"+count+"/message");
            myRef.setValue(input.getText().toString());
            input.setText("");
        }
    };


}
