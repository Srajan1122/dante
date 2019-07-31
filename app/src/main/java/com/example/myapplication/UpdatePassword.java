package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdatePassword extends AppCompatActivity {

    private Button update;
    private EditText newPaswword, newConfirmPassword;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        update = findViewById(R.id.btnUpdatePassword);
        newPaswword = findViewById(R.id.etNewPassword);
        newConfirmPassword = findViewById(R.id.etNewCondirmPassword);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userPasswordNew = newPaswword.getText().toString();
                String newConfirmPassword = newPaswword.getText().toString();

                if(newPaswword.length()<6){
                    Toast.makeText(UpdatePassword.this, "Password too short", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!newPaswword.equals(newConfirmPassword)){
                    Toast.makeText(UpdatePassword.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    firebaseUser.updatePassword(userPasswordNew).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(UpdatePassword.this, "Password Changed", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(UpdatePassword.this, "Password Update Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });


    }
}
