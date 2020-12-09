package com.example.photofastfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.google.firebase.storage.StorageRegistrar;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_settings);

        mNameField = (EditText) findViewById(R.id.name);
        mPhoneField = (EditText) findViewById(R.id.phone);

        mProfileImage = (ImageView) findViewById(R.id.profileImage);
        mProfileImage2 = (ImageView) findViewById(R.id.profileImage2);
        mProfileImage4 = (ImageView) findViewById(R.id.profileImage3);
        mProfileImage5 = (ImageView) findViewById(R.id.profileImage4);
        mProfileImage6 = (ImageView) findViewById(R.id.profileImage5);
        mProfileImage7 = (ImageView) findViewById(R.id.profileImage6);
        mProfileImage8 = (ImageView) findViewById(R.id.profileImage7);

        mBack = (Button) findViewById(R.id.back);
        mConfirm = (Button) findViewById(R.id.confirm);

        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);

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
        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedUserInformation();
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

    private void savedUserInformation() {
        name = mNameField.getText().toString();
        phone = mPhoneField.getText().toString();

        Map userInfo = new HashMap();
        userInfo.put("name", name);
        userInfo.put("phone", phone);
        mUserDatabase.updateChildren(userInfo);
        if (resultUri != null) {
            StorageReference filepath = FirebaseStorage.getInstance().getReference().child("profileImages").child(userID);
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

                //Exporto as fotos por aqui!
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                byte[] data = baos.toByteArray();
                UploadTask uploadTask = filepath.putBytes(data);
                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Map newImage = new HashMap();
                                newImage.put("profileImagemUrl", uri.toString());
                                mUserDatabase.updateChildren(newImage);
                                finish();
                                return;
                            }
                        });
                    }
                });
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SettingsActivity.this, "Falha no envio", Toast.LENGTH_SHORT).show();
                    }
                });

            } else {
                finish();
            }

        if (resultUri2 != null) {
            StorageReference filepath2 = FirebaseStorage.getInstance().getReference().child("otherImages").child(userID).child("Imagem2");
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri2);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Exporto as fotos por aqui!
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath2.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Map newImage = new HashMap();
                            newImage.put("profileImagemUrl2", uri.toString());
                            mUserDatabase.updateChildren(newImage);
                            finish();
                            return;
                        }
                    });
                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SettingsActivity.this, "Falha no envio", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            finish();
        }
        if (resultUri4 != null) {
            StorageReference filepath4 = FirebaseStorage.getInstance().getReference().child("otherImages").child(userID).child("Imagem3");
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri4);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Exporto as fotos por aqui!
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath4.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath4.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Map newImage = new HashMap();
                            newImage.put("profileImagemUrl4", uri.toString());
                            mUserDatabase.updateChildren(newImage);
                            finish();
                            return;
                        }
                    });
                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SettingsActivity.this, "Falha no envio", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            finish();
        }
        if (resultUri5 != null) {
            StorageReference filepath5 = FirebaseStorage.getInstance().getReference().child("otherImages").child(userID).child("Imagem4");
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri5);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Exporto as fotos por aqui!
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath5.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath5.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Map newImage = new HashMap();
                            newImage.put("profileImagemUrl5", uri.toString());
                            mUserDatabase.updateChildren(newImage);
                            finish();
                            return;
                        }
                    });
                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SettingsActivity.this, "Falha no envio", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            finish();
        }
        if (resultUri6 != null) {
            StorageReference filepath6 = FirebaseStorage.getInstance().getReference().child("otherImages").child(userID).child("Imagem5");
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri6);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Exporto as fotos por aqui!
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath6.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath6.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Map newImage = new HashMap();
                            newImage.put("profileImagemUrl6", uri.toString());
                            mUserDatabase.updateChildren(newImage);
                            finish();
                            return;
                        }
                    });
                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SettingsActivity.this, "Falha no envio", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            finish();
        }
        if (resultUri7 != null) {
            StorageReference filepath7 = FirebaseStorage.getInstance().getReference().child("otherImages").child(userID).child("Imagem7");
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri7);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Exporto as fotos por aqui!
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath7.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath7.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Map newImage = new HashMap();
                            newImage.put("profileImagemUrl7", uri.toString());
                            mUserDatabase.updateChildren(newImage);
                            finish();
                            return;
                        }
                    });
                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SettingsActivity.this, "Falha no envio", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            finish();
        }
        if (resultUri3 != null) {
            StorageReference filepath3 = FirebaseStorage.getInstance().getReference().child("otherImages").child(userID).child("Imagem8");
            Bitmap bitmap = null;

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri3);
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Exporto as fotos por aqui!
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = filepath3.putBytes(data);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    filepath3.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Map newImage = new HashMap();
                            newImage.put("profileImagemUrl8", uri.toString());
                            mUserDatabase.updateChildren(newImage);
                            finish();
                            return;
                        }
                    });
                }
            });
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SettingsActivity.this, "Falha no envio", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            finish();
        }
        }

    @Override
    // Se eu quiser exportar mais fotos preciso seguir esse processo!
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            mProfileImage.setImageURI(resultUri);
        }
        else if(requestCode == 2 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri2 = imageUri;
            mProfileImage2.setImageURI(resultUri2);
        }
        else if(requestCode == 3 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri3 = imageUri;
            mProfileImage4.setImageURI(resultUri3);
        }
        else if(requestCode == 4 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri4 = imageUri;
            mProfileImage5.setImageURI(resultUri4);
        }
        else if(requestCode == 5 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri5 = imageUri;
            mProfileImage6.setImageURI(resultUri5);
        }
        else if(requestCode == 6 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri6 = imageUri;
            mProfileImage7.setImageURI(resultUri6);
        }
        else if(requestCode == 7 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri7 = imageUri;
            mProfileImage8.setImageURI(resultUri7);
        }
    }
}