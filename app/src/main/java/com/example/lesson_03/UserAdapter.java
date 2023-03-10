package com.example.lesson_03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private int resource;
    private List<User> users;
    private LayoutInflater inflater;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> users) {
        super(context, resource, users);
        this.resource = resource;
        this.users = users;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View item = inflater.inflate(resource, parent, false);
        ImageView ivLogo = item.findViewById(R.id.ivLogo);
        TextView tvFirstName = item.findViewById(R.id.tvFirstName);
        TextView tvLastName = item.findViewById(R.id.tvLastName);
        User user = users.get(position);
        ivLogo.setImageResource(user.getAvatar());
        tvFirstName.setText(user.getFirstName());
        tvLastName.setText(user.getLastName());

        return item;

    }
}
