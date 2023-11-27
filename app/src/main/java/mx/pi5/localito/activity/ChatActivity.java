package mx.pi5.localito.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import mx.pi5.localito.AuthorizedActivity;
import mx.pi5.localito.adapter.ChatAdapter;
import mx.pi5.localito.databinding.ActivityChatBinding;
import mx.pi5.localito.endpoints.Chat;
import mx.pi5.localito.endpoints.SendMessage;
import mx.pi5.localito.entity.Message;
import mx.pi5.localito.service.Client;

public class ChatActivity extends AuthorizedActivity {
    protected ActivityChatBinding binding;
    protected List<Message> messages = new ArrayList<>();
    int order, buyer;

    public ChatActivity() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        Intent i = getIntent();
        order = i.getIntExtra("orderId", 0);
        buyer = i.getIntExtra("buyerId", 0);
        setContentView(binding.getRoot());
        binding.btnEnviar.setOnClickListener(v -> {
            String body = binding.etMensaje.getText().toString();
            Client client = Client.getInstance(null);
            Request<String> request = new SendMessage(body, order, response -> {
                Message message = new Message();
                message.body = body;
                Date date = new Date();
                SimpleDateFormat simple = new SimpleDateFormat("Y-m-d h:m:s");
                message.message_timestamp = simple.format(date);
                messages.add(message);
                binding.messagesList.setAdapter(new ChatAdapter(messages, this));
                binding.etMensaje.setText("");
            }, error -> {
                Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
            });
            client.getQueue().add(request);
            client.getQueue().start();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Client client = Client.getInstance(this);

        client.getQueue().add(new Chat(order+"", response -> {
                List<Message> data = Arrays.asList(response);
                binding.messagesList.setAdapter(new ChatAdapter(data, this));
                messages.addAll(data);
            }, error -> Snackbar.make( binding.getRoot(), error.getMessage(), Snackbar.LENGTH_SHORT) .show() )
        );
        client.getQueue().start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Client.getInstance(this).getQueue().stop();
    }
}
