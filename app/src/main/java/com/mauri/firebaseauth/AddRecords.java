package com.mauri.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddRecords extends AppCompatActivity {
    private static final String TAG = "!!! AddRecords";
    private FirebaseAuth mAuth;
    TextView mFirst, mLast, mBorn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.i(TAG, "onCreate: currentUser: "+currentUser.getUid());
        mFirst = findViewById(R.id.textViewFirst);
        mLast = findViewById(R.id.textViewLast);
        mBorn = findViewById(R.id.textViewBorn);


        Button btnNewRecord = (Button) findViewById(R.id.btnNewRecord);
        btnNewRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: btnLogout");
                Map<String, Object> user = new HashMap<>();
                user.put("first", mFirst.getText().toString());
                user.put("last", mLast.getText().toString());
                user.put("born", mBorn.getText().toString());
                user.put("author", currentUser.getUid().toString());

                try {
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(AddRecords.this, "User created!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                }
                            });
                }
                catch (Exception e)
                {
                    Log.e(TAG, "onClick: ", e );
                }

            }
        });
    }
}
