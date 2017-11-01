package android.trill.com.trill;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_Up extends AppCompatActivity {


    int imgcount = 0;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();


    }

    public void nextpage(View view) {
        if (imgcount != 4) {
            ViewAnimator viewAnimator;
            Animation slide_in_left, slide_out_right;
            ImageView img1, img2, img3, img4, img5;

            img1 = findViewById(R.id.imga1);
            img2 = findViewById(R.id.imga2);
            img3 = findViewById(R.id.imga3);
            img4 = findViewById(R.id.imga4);
            img5 = findViewById(R.id.imga5);
            viewAnimator = (ViewAnimator) findViewById(R.id.viewanimator);

            slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
            slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
            viewAnimator.setInAnimation(slide_in_left);
            viewAnimator.setOutAnimation(slide_out_right);

            switch (imgcount) {
                case 0:
                    img2.setImageResource(R.drawable.fill_layout_colorblue);
                    imgcount++;
                    viewAnimator.showNext();
                    break;
                case 1:
                    img3.setImageResource(R.drawable.fill_layout_colorblue);
                    imgcount++;
                    viewAnimator.showNext();
                    break;
                case 2:
                    EditText username, password;
                    username = (EditText) findViewById(R.id.useremailsign);
                    password = (EditText) findViewById(R.id.passwordsignEditText);
                    createuser(username.getText().toString(), password.getText().toString());
                    //  img4.setImageResource(R.drawable.fill_layout_colorblue);
                    //imgcount++;
                    break;
                case 3:
                    //   img5.setImageResource(R.drawable.fill_layout_colorblue);
                    // imgcount++;
                    break;
            }

        }
    }

    public void changelayout(View view) {
        int i = view.getId();
        LinearLayout emaillayout = (LinearLayout) findViewById(R.id.emaillayout);
        LinearLayout phonelayout = (LinearLayout) findViewById(R.id.phonelayout);
        CardView emaileditor = (CardView) findViewById(R.id.emaileditor);
        CardView phoneeditor = (CardView) findViewById(R.id.phoneeditor);
        TextView emaileditortext = (TextView) findViewById(R.id.emaileditortext);
        TextView phoneeditortext = (TextView) findViewById(R.id.phoneeditortext);
        if (i == R.id.emaileditor) {
            emaillayout.setVisibility(View.VISIBLE);
            phonelayout.setVisibility(View.GONE);
            emaileditor.setCardBackgroundColor(getResources().getColor(R.color.colorblue));
            phoneeditor.setCardBackgroundColor(getResources().getColor(R.color.colorwhite));
            emaileditortext.setTextColor(getResources().getColor(R.color.colorwhite));
            phoneeditortext.setTextColor(getResources().getColor(R.color.colorblack));
            emaileditor.setCardElevation(10);
            phoneeditor.setCardElevation(9);
        } else if (i == R.id.phoneeditor) {
            emaillayout.setVisibility(View.GONE);
            phonelayout.setVisibility(View.VISIBLE);
            emaileditor.setCardBackgroundColor(getResources().getColor(R.color.colorwhite));
            phoneeditor.setCardBackgroundColor(getResources().getColor(R.color.colorblue));
            emaileditortext.setTextColor(getResources().getColor(R.color.colorblack));
            phoneeditortext.setTextColor(getResources().getColor(R.color.colorwhite));
            emaileditor.setCardElevation(9);
            phoneeditor.setCardElevation(10);

        }
    }

    public void ImageClick(View view) {
        Toast.makeText(this, "The Image Not Working yet", Toast.LENGTH_LONG).show();
    }

    public void Birhdayinput(View view) {
        Toast.makeText(this, "The birthday Not Working yet", Toast.LENGTH_LONG).show();

    }

    public void createuser(String username, String Password) {


        mAuth.createUserWithEmailAndPassword(username, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("Sign Up", "createUserWithEmail:success");
                            Toast.makeText(Sign_Up.this, "User creat for know", Toast.LENGTH_LONG).show();


                        } else {

                            Log.w("Sign Up", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Sign_Up.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}
