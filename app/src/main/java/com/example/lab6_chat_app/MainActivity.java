package com.example.lab6_chat_app;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6_chat_app.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference chatRef;
    public List<ChatMessage> messageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        database = FirebaseDatabase.getInstance();
        chatRef = database.getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String name = user.getDisplayName();
        String email = user.getEmail();



        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String messageText = binding.messageInput.getText().toString().trim();
                if(!messageText.isEmpty()){
                    ChatMessage message = new ChatMessage(name, messageText);
                    message.setSender(name);
                    message.setMessage(messageText);
                    chatRef.child("chat1").push().setValue(message);
                }
            }
        });

        binding.clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO:
                clear all messages from screen
                 */
                chatRef.child("chat1").removeValue();
                binding.editTextChat.setText("");
            }
        });

        chatRef.child("chat1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                StringBuilder chat1out = new StringBuilder();
                for(DataSnapshot data: snapshot.getChildren()){
                    ChatMessage message = data.getValue(ChatMessage.class);
                    if(message != null){
                        chat1out.append(message);
                        chat1out.append("\n");
                    }
                    binding.editTextChat.setText(chat1out.toString());
                    binding.messageInput.setText("");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }



}