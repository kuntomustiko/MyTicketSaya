package com.minara.kirana.myticketsaya;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class EditProfileActivity extends AppCompatActivity {

    ImageView iv_edtprofileact_profil;
    EditText edt_edtprofileact_nama, edt_edtprofileact_bio, edt_edtprofileact_username,
            edt_edtprofileact_password, edt_edtprofileact_emailaddress;
    Button btn_edtprofileact_saveprofile,btn_edtprofileact_add_new_photo;
    LinearLayout ll_edtProfileAct_back;

    DatabaseReference reference;
    StorageReference storage;
    Uri photo_location;
    Integer photoMax = 1;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    // key yang sedang login
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        iv_edtprofileact_profil = findViewById(R.id.iv_edtprofileact_profil);
        ll_edtProfileAct_back = findViewById(R.id.ll_edtProfileAct_back);
        btn_edtprofileact_saveprofile = findViewById(R.id.btn_edtprofileact_saveprofile);
        btn_edtprofileact_add_new_photo = findViewById(R.id.btn_edtprofileact_add_new_photo);

        edt_edtprofileact_nama = findViewById(R.id.edt_edtprofileact_nama);
        edt_edtprofileact_bio = findViewById(R.id.edt_edtprofileact_bio);
        edt_edtprofileact_username = findViewById(R.id.edt_edtprofileact_username);
        edt_edtprofileact_password = findViewById(R.id.edt_edtprofileact_password);
        edt_edtprofileact_emailaddress = findViewById(R.id.edt_edtprofileact_emailaddress);

        getUsernameLocal();

        reference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(username_key_new);
        storage = FirebaseStorage.getInstance().getReference()
                .child("PhotoUsers").child(username_key_new);

        // untuk mendapatkan update terbaru "addValueEventListener"
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                edt_edtprofileact_nama.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                edt_edtprofileact_bio.setText(dataSnapshot.child("bio").getValue().toString());
                edt_edtprofileact_username.setText(dataSnapshot.child("username").getValue().toString());
                edt_edtprofileact_password.setText(dataSnapshot.child("password").getValue().toString());
                edt_edtprofileact_emailaddress.setText(dataSnapshot.child("email_address").getValue().toString());

                Picasso.with(EditProfileActivity.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit().into(iv_edtprofileact_profil);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btn_edtprofileact_saveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_edtprofileact_saveprofile.setEnabled(false);
                btn_edtprofileact_saveprofile.setText("loading");
                // ini cuman sekali di update "addListenerForSingleValueEvent"
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("nama_lengkap").setValue(edt_edtprofileact_nama.getText().toString());
                        dataSnapshot.getRef().child("bio").setValue(edt_edtprofileact_bio.getText().toString());
                        dataSnapshot.getRef().child("username").setValue(edt_edtprofileact_username.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(edt_edtprofileact_password.getText().toString());
                        dataSnapshot.getRef().child("email_address").setValue(edt_edtprofileact_emailaddress.getText().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                // validasi untuk file apakah ada
                if (photo_location != null){
                    StorageReference storageReference1 = storage
                            .child(System.currentTimeMillis()+ "." + getFileExtension(photo_location));

                    // ketika berhasil di ambil photo nya
                    storageReference1.putFile(photo_location)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                            String uri_photo =taskSnapshot.getDownloadUrl().toString();
                                    String uri_photo =taskSnapshot.getUploadSessionUri().toString();
                                    reference.getRef().child("url_photo_profile").setValue(uri_photo);
                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Intent gotobackprofile = new Intent(EditProfileActivity.this, MyProfileActivity.class);
                            startActivity(gotobackprofile);
                        }
                    });
                }
            }
        });

        btn_edtprofileact_add_new_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhoto();
            }
        });
    }

    // ketika menyimpan foto di firebase
    String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }


    // cari photo dahulu
    public void findPhoto(){
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic,photoMax);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == photoMax && resultCode == RESULT_OK
                && data != null && data.getData() != null){
            photo_location = data.getData();
            Picasso.with(this).load(photo_location)
                    .centerCrop().fit().into(iv_edtprofileact_profil);
        }
    }

    // ambil data dari local sharedpreference yang sebelumnya telah di simpan dari registerone
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
