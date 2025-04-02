package com.example.lab6_chat_app;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab6_chat_app.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO:
                take message from message input
                add to data base based on user
                display on screen
                 */
            }
        });

        binding.clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*TODO:
                clear all messages from screen
                 */
            }
        });

         messageList = new ArrayList<>();
         chatAdapter = new ChatAdapter(messageList);
         layoutManager = new LinearLayoutManager(this);
         binding.rView.setAdapter(chatAdapter);
         binding.rView.setLayoutManager(layoutManager);




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //@Override
    /*protected void onStart(){
        super.onStart();
        if(auth.getCurrentUser()==null){
            auth.signInAnonymously();
        }
    }

     */
}