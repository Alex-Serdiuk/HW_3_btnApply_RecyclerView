package com.example.lesson_03;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {
    public static final String KEY_USER = "user";
    public static final String KEY_USER_ID = "id";
    public static final int REQUEST_CODE = 1;
    public static int USER_CODE;
    private ListView lvContacts;

    private List<User> users;
    User selectedUser;
    int idUser;
    private UserAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_adapter);
        lvContacts = findViewById(R.id.lvContacts);
        users = UserDB.getAll();
        adapter = new UserAdapter(this, R.layout.item_contact, users);
        lvContacts.setAdapter(adapter);
        lvContacts.setOnItemClickListener((parent, view, position, id) -> {
            selectedUser = users.get(position);
            Intent intent = new Intent(this, FullInfoActivity.class);
            intent.putExtra(KEY_USER, selectedUser);
            intent.putExtra(KEY_USER_ID, users.indexOf(selectedUser));
            activityResultLauncher.launch(intent);
        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
//            users.clear();
//            //users =(List<User>) data.getSerializableExtra("users");
//            users = UserDB.getAll();
//            User user = (User) data.getSerializableExtra("user");
//            users.set(USER_CODE,user);
//            adapter.notifyDataSetChanged();
//            //users=(UserDB) getIntent().getSerializableExtra("users");
//        }
//    }


//    @Override
//    protected void onStart() {
//        super.onStart();
//        initListView();
//    }
//
//    private void initListView() {
//        Intent intent = getIntent();
//        users.clear();
//        users = UserDB.getAll();
//        selectedUser= (User) intent.getSerializableExtra("user");
//        users.set(USER_CODE,selectedUser);
//        adapter.notifyDataSetChanged();
//        lvContacts.setAdapter(adapter);
//    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == REQUEST_CODE){
                        Intent intent = result.getData();
                        if(intent!=null){
                            users.clear();
                            selectedUser = (User) intent.getSerializableExtra(KEY_USER);
                            idUser = (Integer) intent.getSerializableExtra(KEY_USER_ID);
                            users = UserDB.getAll();
                            users.set(idUser,selectedUser);
                            adapter.notifyDataSetChanged();
                            lvContacts.setAdapter(adapter);
                        }
                    }
                }
            }
    );
}