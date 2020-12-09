package Person;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.photofastfinal.R;
import com.example.photofastfinal.SettingsActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Person.PersonObject;

public class PersonProfileActivity extends AppCompatActivity {

    private EditText mNameField, mPhoneField;

    private Button mBack, mConfirm;

    private ImageView mProfileImage;
    private ImageView mProfileImage2;
    private ImageView mProfileImage4;
    private ImageView mProfileImage5;
    private ImageView mProfileImage6;
    private ImageView mProfileImage7;
    private ImageView mProfileImage8;

    private FirebaseAuth mAuth;
    private DatabaseReference mUserDatabase;

    private String userID, name, phone, profileImagemUrl,profileImagemUrl2,profileImagemUrl4,profileImagemUrl5,profileImagemUrl6 ,profileImagemUrl7,profileImagemUrl8, userCat;

    private Uri resultUri,resultUri2,resultUri3,resultUri4,resultUri5,resultUri6,resultUri7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_profile);

        mNameField = (EditText) findViewById(R.id.name_another);
        mPhoneField = (EditText) findViewById(R.id.phone_another);

        mProfileImage = (ImageView) findViewById(R.id.profileImage_another);
        mProfileImage2 = (ImageView) findViewById(R.id.profileImage2_another);
        mProfileImage4 = (ImageView) findViewById(R.id.profileImage3_another);
        mProfileImage5 = (ImageView) findViewById(R.id.profileImage4_another);
        mProfileImage6 = (ImageView) findViewById(R.id.profileImage5_another);
        mProfileImage7 = (ImageView) findViewById(R.id.profileImage6_another);
        mProfileImage8 = (ImageView) findViewById(R.id.profileImage7_another);

        mBack = (Button) findViewById(R.id.back_another);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child("KYOdcLd63zavDlvfpDyay0uCsv02");

        getUserInfo();

        mProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        mProfileImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 2);
            }
        });
        mProfileImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 3);
            }
        });
        mProfileImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 4);
            }
        });
        mProfileImage6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 5);
            }
        });
        mProfileImage7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 6);
            }
        });
        mProfileImage8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 7);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                return;
            }
        });

    }

    private void getUserInfo() {
        mUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists() && dataSnapshot.getChildrenCount()>0){
                    Map<String,  Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    if(map.get("name") !=null){
                        name = map.get("name").toString();
                        mNameField.setText(name);
                    }

                    if(map.get("phone") !=null){
                        phone = map.get("phone").toString();
                        mPhoneField.setText(phone);
                    }
                    if(map.get("cat") !=null){
                        userCat = map.get("cat").toString();
                    }
                    Glide.clear(mProfileImage);
                    if(map.get("profileImagemUrl") !=null){
                        profileImagemUrl = map.get("profileImagemUrl").toString();
                        switch (profileImagemUrl){
                            case "default":
                                mProfileImage.setImageResource(R.mipmap.ic_baseline_person_24);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImagemUrl).into(mProfileImage);
                                break;
                        }
                    }
                    Glide.clear(mProfileImage2);
                    if(map.get("profileImagemUrl2") !=null){
                        profileImagemUrl2 = map.get("profileImagemUrl2").toString();
                        switch (profileImagemUrl2){
                            case "default":
                                mProfileImage2.setImageResource(R.mipmap.ic_baseline_person_24);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImagemUrl2).into(mProfileImage2);
                                break;
                        }
                    }
                    Glide.clear(mProfileImage4);
                    if(map.get("profileImagemUrl4") !=null){
                        profileImagemUrl4 = map.get("profileImagemUrl4").toString();
                        switch (profileImagemUrl4){
                            case "default":
                                mProfileImage4.setImageResource(R.mipmap.ic_baseline_person_24);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImagemUrl4).into(mProfileImage4);
                                break;
                        }
                    }
                    Glide.clear(mProfileImage5);
                    if(map.get("profileImagemUrl5") !=null){
                        profileImagemUrl5 = map.get("profileImagemUrl5").toString();
                        switch (profileImagemUrl5){
                            case "default":
                                mProfileImage5.setImageResource(R.mipmap.ic_baseline_person_24);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImagemUrl5).into(mProfileImage5);
                                break;
                        }
                    }
                    Glide.clear(mProfileImage6);
                    if(map.get("profileImagemUrl6") !=null){
                        profileImagemUrl6 = map.get("profileImagemUrl6").toString();
                        switch (profileImagemUrl6){
                            case "default":
                                mProfileImage6.setImageResource(R.mipmap.ic_baseline_person_24);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImagemUrl6).into(mProfileImage6);
                                break;
                        }
                    }
                    Glide.clear(mProfileImage7);
                    if(map.get("profileImagemUrl7") !=null){
                        profileImagemUrl7 = map.get("profileImagemUrl7").toString();
                        switch (profileImagemUrl7){
                            case "default":
                                mProfileImage7.setImageResource(R.mipmap.ic_baseline_person_24);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImagemUrl7).into(mProfileImage7);
                                break;
                        }
                    }
                    Glide.clear(mProfileImage8);
                    if(map.get("profileImagemUrl8") !=null){
                        profileImagemUrl8 = map.get("profileImagemUrl8").toString();
                        switch (profileImagemUrl8){
                            case "default":
                                mProfileImage8.setImageResource(R.mipmap.ic_baseline_person_24);
                                break;
                            default:
                                Glide.with(getApplication()).load(profileImagemUrl8).into(mProfileImage8);
                                break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}