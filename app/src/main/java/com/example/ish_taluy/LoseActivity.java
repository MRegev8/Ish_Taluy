package com.example.ish_taluy;

import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;

public class LoseActivity extends AppCompatActivity {
    TextView word;
    Button rest;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);
        Intent intent=getIntent();
        String secret=intent.getExtras().getString("word");
        word=findViewById(R.id.word);
        rest=findViewById(R.id.but);
        word.setText("the word was: "+secret);
        rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(LoseActivity.this);
                alert.setTitle("EXIT");
                alert.setMessage("ARE YOU SURE YOU WANT TO RESTART?");
                alert.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                        finish();
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(LoseActivity.this,"I want to stay", Toast.LENGTH_LONG).show();
                    }
                });
                alert.create().show();
            }
        });
    }
}

