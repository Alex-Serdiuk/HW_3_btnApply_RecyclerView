package com.example.lesson_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.lesson_03.databinding.ActivityFullInfoBinding;

import java.io.Serializable;
import java.util.List;

public class FullInfoActivity extends AppCompatActivity {

    private ActivityFullInfoBinding binding;
    private List<User> users;
    private UserAdapter adapter;
    public static final int REQUEST_CODE = 1;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        users = UserDB.getAll();
        adapter = new UserAdapter(this, R.layout.item_contact, users);

        User user = (User) getIntent().getSerializableExtra(CustomAdapterActivity.KEY_USER);
        index = (Integer)getIntent().getSerializableExtra(CustomAdapterActivity.KEY_USER_ID);
        binding.ivAvatar.setImageResource(user.getAvatar());
        binding.etFirstName.setText(user.getFirstName());
        binding.etLastName.setText(user.getLastName());
        binding.etPhone.setText(user.getPhone());
        binding.etEmail.setText(user.getEmail());
        binding.btnApply.setOnClickListener(view -> {
            if (binding.etFirstName.getText().toString().trim().length() == 0 ||
                    binding.etLastName.getText().toString().trim().length() == 0 ||
                    binding.etPhone.getText().toString().trim().length() == 0 ||
                    binding.etEmail.getText().toString().trim().length() == 0) {
                String message = "Заполните все поля!";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }else {

                    user.setFirstName(binding.etFirstName.getText().toString());
                    user.setLastName(binding.etLastName.getText().toString());
                    user.setPhone(binding.etPhone.getText().toString());
                    user.setEmail(binding.etEmail.getText().toString());

                   //UserDB.setUserById(index, user);
                   Intent intent=new Intent(this,CustomAdapterActivity.class);
                           intent.putExtra("user", user);
                           intent.putExtra(CustomAdapterActivity.KEY_USER_ID, index);
                           setResult(CustomAdapterActivity.REQUEST_CODE, intent);
                           finish();
            }
        });
    }
}