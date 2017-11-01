package android.trill.com.trill;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LogIn ";


    private EditText eEmail;
    private EditText ePassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eEmail = findViewById(R.id.userLoginEditText);
        ePassword = findViewById(R.id.passwordLoginEditText);


        findViewById(R.id.login).setOnClickListener(this);
        findViewById(R.id.sign_up).setOnClickListener(this);
        findViewById(R.id.forgot_password).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            startActivity(new Intent(this, MainActivity.class));

        } else {

        }
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.login) {
            if (eEmail.getText().toString().equals("") || ePassword.getText().toString().equals("")) {
                eEmail.setError("No email");
                ePassword.setError("No Password");
            } else {
                fun_Login(eEmail.getText().toString(), ePassword.getText().toString());
            }
        } else if (i == R.id.sign_up) {
            startActivity(new Intent(this, Sign_Up.class));
        } else if (i == R.id.forgot_password) {
            Toast.makeText(this, "Forgot password not work yet", Toast.LENGTH_LONG).show();
        }

    }


    private void fun_Login(String email, String password) {
        Log.d(TAG, "LogIn:" + email);


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            eEmail.setError("Input Error");
                            ePassword.setError("Input Error");


                            updateUI(null);
                        }


                    }
                });
    }

}
