package traf1.ganotrakrish.chatbot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signupActivity extends AppCompatActivity {

    Button buttonSignup;
    EditText emailSignup;
    EditText passwordSignup;
    private FirebaseAuth mAuth;
    public FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        buttonSignup = findViewById(R.id.buttonSignup);
        emailSignup = findViewById(R.id.emailSignup);
        passwordSignup = findViewById(R.id.passwordSignup);


        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(emailSignup.getText().toString(), passwordSignup.getText().toString())
                        .addOnCompleteListener(signupActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    System.out.println("createUserWithEmail:success");
                                    user = mAuth.getCurrentUser();

                                    //set display name!
                                    updateUI(user);
                                } else {
                                    // If sign up fails, display a message to the user.
                                    System.out.println("createUserWithEmail:failure"+ task.getException());
                                    Toast.makeText(signupActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void updateUI(FirebaseUser user){
        Intent intent = new Intent(signupActivity.this, chatrooomActivity.class);
        intent.putExtra("EMAIL", user.getEmail());
        intent.putExtra("DISPLAYNAME", user.getDisplayName());
        intent.putExtra("PHOTOURL", user.getPhotoUrl());
        startActivity(intent);
    }
}
