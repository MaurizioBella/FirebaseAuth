package com.mauri.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


/**
 * NAMING CONVENTION https://github.com/ribot/android-guidelines/blob/master/project_and_code_guidelines.md
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "!!! MainActivity";
    private FirebaseAuth mAuth;
    TextView mUser;
    Button mFirestone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        mUser = findViewById(R.id.textViewUser);
        if (mAuth.getCurrentUser() != null)
        {
            mUser.setText("User: "+mAuth.getCurrentUser().getEmail());
        }
        else
        {
            mUser.setText("No User Logged In!");
            mFirestone.setEnabled(false);
        }

        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));


            }
        });

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: btnLogin");
                startActivity(new Intent(MainActivity.this, Login.class));


            }
        });

        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: btnLogout");
                try {
                    mAuth.signOut();
                    mUser.setText("No User Logged In!");
                    // If sign in fails, display a message to the user.
                    Toast.makeText(MainActivity.this, "User Logged Out!",
                            Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Log.e(TAG, "onClick: ", e );
                }

            }
        });

        mFirestone = findViewById(R.id.btnFirestore);
        mFirestone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                startActivity(new Intent(MainActivity.this, Firestore.class));
            }
        });
    }
}
