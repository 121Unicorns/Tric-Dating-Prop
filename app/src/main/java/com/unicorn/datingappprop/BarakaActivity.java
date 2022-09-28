package com.unicorn.datingappprop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class BarakaActivity extends AppCompatActivity {

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
    int profimage = R.drawable.match1;
    ImageButton imageButton;
    EditText etMsg;
    String chanelId = "chennel_id";
    int notificationId = 567;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baraka);

        initViews();
        buildRecyclerView();
        loadData();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String msg = etMsg.getText().toString();
                final String dateString = sdf.format(new Date());
                int notification_timeout = 3000;

                if (msg.toLowerCase().contains("hey") || msg.toLowerCase().contains("where")) {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotification(tvProfName.getText().toString(), "Hey Nats. Niko karibu. Give me 5 minutes ♡");
                        }
                    }, notification_timeout);
                } else if (msg.toLowerCase().contains("okay") || msg.toLowerCase().contains("see") || msg.toLowerCase().contains("good") || msg.toLowerCase().contains("sawa")) {
                    messageChatModelList.add(new MessageChatModel(msg, dateString, 0, R.drawable.ic_usergrey));
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showNotification(tvProfName.getText().toString(), "See you soon.");
                        }
                    }, notification_timeout);
                }
                updateView();
                etMsg.setText("");
                saveData();
            }
        });
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_msg2);
        imageButton = findViewById(R.id.ibtn_sendmsg2);
        etMsg = findViewById(R.id.et_msg2);
        civProfPic = findViewById(R.id.civ_userprof2);
        tvProfName = findViewById(R.id.tv_username2);
        ivBack = findViewById(R.id.iv_back2);

        sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        if (profname == null) {
            profname = "Baraka";
        }

        tvProfName.setText(profname);
        civProfPic.setImageResource(profimage);
    }

    private void buildRecyclerView() {
        manager = new LinearLayoutManager(BarakaActivity.this, RecyclerView.VERTICAL, false);
        adapter = new MessageChatAdapter(messageChatModelList, BarakaActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.smoothScrollToPosition(messageChatModelList.size());
        recyclerView.setAdapter(adapter);
    }

    private void populateList() {
        messageChatModelList.add(new MessageChatModel("If I had 3 wishes!", "8:53 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Well, I'm here. So what are your other 2 wishes?", "8:57 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Ay super confident, eh? I love that.", "8:58 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Only way to live!", "8:59 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("So how are you?", "9:00 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("I'm great. Where are you from?", "9:03 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("I'm a Nairobi guy through and through. How about you?", "9:05 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Same. Total Nairobi girl. What are you up to tonight?", "9:07 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("It's game night, I'm chasing that W.", "9:53 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Lol awesome, what games y'all playing?", "9:07 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Right now? Movie trivia. What about you, what are you upto?", "9:08 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Studying, I have an exam tomorrow", "9:10 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Oh cool, what do you study?", "9:18 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("International Business Administration at USIU. Wbu?", "9:20 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("BBIT at UoN.", "9:26 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("So can I take you out next week, once your exams are done? Saturday?", "9:26 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("I'd love that. What did you have in mind?", "9:29 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("Foodie activities, maybe some dancing?", "9:38 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("Ah, you speak my language! Looking forward to it.", "9:43 PM", 0, R.drawable.ic_usergrey));
        messageChatModelList.add(new MessageChatModel("1 pm?", "9:45 PM", 1, profimage));
        messageChatModelList.add(new MessageChatModel("See you then. Have a good night, " + profname, "9:48 PM", 0, R.drawable.ic_usergrey));
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

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(BarakaActivity.this);
        notificationManager.notify(notificationId, builder.build());

        System.out.println(messageChatModelList.get(messageChatModelList.size() - 1).getText());
        System.out.println(messageChatModelList.get(messageChatModelList.size() - 2).getText());
    }

    private void loadData() {
        Gson gson = new Gson();
        messageChatModelList.clear();
        String json = sharedPreferences.getString("barakamessages", null);
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
        editor.putString("barakamessages", json);
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