package com.unicorn.datingappprop;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    List<MessageChatModel> messageChatModelList = new ArrayList<>();
    List<MessageChatModel> updatedMessageList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    LinearLayoutManager manager;
    MessageChatAdapter adapter;
    CircleImageView civProfPic;
    ImageView ivBack;
    TextView tvProfName;
    String profname;
    int profimage;
    ImageButton imageButton;
    EditText etMsg;
    String chanelId = "chennel_id";
    int notificationId = 567;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        initViews();
        buildRecyclerView();
        loadData();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = etMsg.getText().toString();
                final String dateString = sdf.format(new Date());
                int notification_timeout = 3000;

                if (msg.toLowerCase().contains("hey")) {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotification(tvProfName.getText().toString(), "Liv! I can't wait to see you tomorrow. I have a surprise for you ♡");
                        }
                    }, notification_timeout);
                } else if (msg.toLowerCase().contains("real")) {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotification(tvProfName.getText().toString(), "Absolutely!");
                        }
                    }, notification_timeout);

                } else if (msg.toLowerCase().contains("what")) {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotification(tvProfName.getText().toString(), "You'll just have to wait and see, beautiful.");
                        }
                    }, notification_timeout);
                } else if (msg.toLowerCase().contains("okay") || msg.toLowerCase().contains("see") || msg.toLowerCase().contains("good")) {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotification(tvProfName.getText().toString(), "See you soon.");
                        }
                    }, notification_timeout);
                } else if (msg.toLowerCase().contains("mystery")) {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotification(tvProfName.getText().toString(), "Aah, si you know?");
                        }
                    }, notification_timeout);
                }else {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                }

                //recyclerView.smoothScrollToPosition(messageChatModelList.size());
                //adapter.notifyItemInserted(messageChatModelList.size());
                //adapter.notifyDataSetChanged();
                updateView();
                etMsg.setText("");

                saveData();
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_msg);
        imageButton = findViewById(R.id.ibtn_sendmsg);
        etMsg = findViewById(R.id.et_msg);
        civProfPic = findViewById(R.id.civ_userprof);
        tvProfName = findViewById(R.id.tv_username);
        ivBack = findViewById(R.id.iv_back);

        profimage = getIntent().getIntExtra("avatar", R.drawable.match15);
        profname = getIntent().getStringExtra("name");

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        if (profname == null) {
            profname = "James";
        }

        tvProfName.setText(profname);
        civProfPic.setImageResource(profimage);
    }

    private void buildRecyclerView() {
        manager = new LinearLayoutManager(MessageActivity.this, RecyclerView.VERTICAL, false);
        adapter = new MessageChatAdapter(messageChatModelList, MessageActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.smoothScrollToPosition(messageChatModelList.size());
        recyclerView.setAdapter(adapter);
    }

    private void populateList() {
        messageChatModelList.add(new MessageChatModel("Wow! How are you this beautiful?", "9:53 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Hey! Thanks for the compliment. You're very handsome yourself", "9:57 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Sweet! So, what are you upto tonight?", "9:59 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Nah, I dunno. Play soccer... or learn more coding perhaps?", "10:03 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("You play soccer? That's amazing!", "10:05 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Really? Why's that?", "10:07 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Its my favourite sport. I'm a huge fan of Chelsea FC.", "10:53 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("That's so cool? Do you play?", "10:07 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Absolutely. Its the gentleman's sport you know?", "10:08 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Lol, I know. What position do you play?", "10:10 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Defender. I'm a center back. What about you?", "10:18 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("I'm a goalie. Absolutely love it.", "10:20 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("It's been great talking to you today, Liv. Have a good night.", "10:26 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Have a good night, " + profname + ".", "10:28 PM", 0, R.drawable.ic_usergrey));
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "TricNotifChannel";
            String description = "This is the TricNotifChannel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(chanelId, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String name, String message) {
        createNotificationChannel();
        String title = "Message From " + name;
        String body = message.trim();

        String dateString = sdf.format(new Date()).toUpperCase();
        messageChatModelList.add(new MessageChatModel(body, dateString, 1, profimage));
        updateView();

        Intent intent = new Intent(this, MessageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), chanelId)
                .setSmallIcon(R.drawable.logotrans)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MessageActivity.this);
        notificationManager.notify(notificationId, builder.build());

        System.out.println(messageChatModelList.get(messageChatModelList.size() - 1).getText());
        System.out.println(messageChatModelList.get(messageChatModelList.size() - 2).getText());
    }

    private void loadData() {
        Gson gson = new Gson();
        messageChatModelList.clear();
        String json = sharedPreferences.getString("messages", null);
        Type type = new TypeToken<ArrayList<MessageChatModel>>() {
        }.getType();
        if (messageChatModelList == null || json == null) {
            populateList();
        } else {
            updatedMessageList = gson.fromJson(json, type);
            for (int i = 0; i < updatedMessageList.size(); i++) {
                messageChatModelList.add(new MessageChatModel(updatedMessageList.get(i).getText(), updatedMessageList.get(i).getTime(), updatedMessageList.get(i).getViewType(), updatedMessageList.get(i).getResource()));
            }
        }
        updateView();
    }

    private void saveData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(messageChatModelList);
        editor.putString("messages", json);
        editor.apply();
        updateView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void updateView() {
        recyclerView.smoothScrollToPosition(messageChatModelList.size());
        adapter.notifyItemInserted(messageChatModelList.size());
        adapter.notifyDataSetChanged();
    }
}