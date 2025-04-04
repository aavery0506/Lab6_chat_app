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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    FirebaseAuth auth = FirebaseAuth.getInstance();
    DatabaseReference chatRef = database.getReference("chats");


    private RecyclerView.LayoutManager layoutManager;
    private ChatAdapter chatAdapter;
    public List<ChatMessage> messageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        messageList = new ArrayList<>();
        chatAdapter = new ChatAdapter(messageList);
        layoutManager = new LinearLayoutManager(this);
        binding.rView.setAdapter(chatAdapter);
        binding.rView.setLayoutManager(layoutManager);

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO:
                take message from message input
                add to data base based on user
                display on screen
                 */
                String messageText = binding.messageInput.getText().toString().trim();
                if(!messageText.isEmpty()){
                    ChatMessage message = new ChatMessage("User", messageText);
                    binding.messageInput.setText(""); //clear input
                }
            }
        });

        binding.clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO:
                clear all messages from screen
                 */
                messageList.clear();
            }
        });

        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for(DataSnapshot data: snapshot.getChildren()){
                    ChatMessage message = data.getValue(ChatMessage.class);
                    if(message != null){
                        messageList.add(message);
                    }
                }
                chatAdapter.notifyDataSetChanged();
                binding.rView.scrollToPosition(messageList.size()-1);
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