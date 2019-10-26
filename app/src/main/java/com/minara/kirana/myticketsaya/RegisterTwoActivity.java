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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class RegisterTwoActivity extends AppCompatActivity {

    LinearLayout llback;
    Button btnContinueRegisterTwo, btnAddPhoto;

    // TODO 12 - b
    ImageView ivPhoto;
    Uri photo_location;
    Integer photoMax = 1;
    EditText edtBio, edtNama;

    DatabaseReference reference;
    StorageReference storage;

    // ambil  data user yang sedang laogin melalui sharedprefrenece
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        getUsernameLocal();

        btnContinueRegisterTwo = findViewById(R.id.btn_registertwoact_continue);
        llback = findViewById(R.id.ll_registertwo_back);
        btnAddPhoto = findViewById(R.id.btn_registertwoact_uploadphoto);
        ivPhoto = findViewById(R.id.iv_registertwoact_photo);
        edtBio = findViewById(R.id.edt_registertwoact_bio);
        edtNama = findViewById(R.id.edt_registertwoact_nama);

        llback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoSignIn = new Intent(RegisterTwoActivity.this, RegisterOneActivity.class);
                startActivity(backtoSignIn);
            }
        });



        btnAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhoto();
            }
        });

        btnContinueRegisterTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 12 - c // ubah state menjadi loading
                btnContinueRegisterTwo.setEnabled(false);
                btnContinueRegisterTwo.setText("loading");

                //menyimpan kepada firebase
                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(username_key_new);
                // membuat dan menyimpan kepada storage di firebase
                storage = FirebaseStorage.getInstance().getReference()
                        .child("PhotoUsers").child(username_key_new);

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
                            reference.getRef().child("nama_lengkap").setValue(edtNama.getText().toString());
                            reference.getRef().child("bio").setValue(edtBio.getText().toString());

                        }
                    }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            Intent backtoSuccess = new Intent(RegisterTwoActivity.this, SuccessRegisterActivity.class);
                            startActivity(backtoSuccess);
                        }
                    });
                }
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
                    .centerCrop().fit().into(ivPhoto);
        }
    }

    // ambil data dari local sharedpreference yang sebelumnya telah di simpan dari registerone
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
