package com.example.ish_taluy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements TextWatcher{
    TextView word;
    TextView GL;
    ImageView pic;
    String secret;
    String[] secrets;
    int randint;
    String GuestLs="Guessed letters: ";
    char[] pubsec;
    char[] gel=new char[26];
    int glc=0;
    int numofpic=0;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secrets=new String[]{"balloon", "anxiety", "chapter", "extreme", "factory", "licence", "program", "support", "welcome", "traffic",
                "accept", "corner", "decide", "hidden", "server", "travel", "winter", "prince", "relate", "notice","flock", "pizza", "angry", "cross", "event", "grand", "japan", "north", "radio", "smoke",
                "what", "duck", "bear", "play", "good", "ride", "cold", "four", "cool", "boom"};
        word =findViewById(R.id.word);
        GL=findViewById(R.id.Guessed);
        pic=findViewById(R.id.imageView);
        et=findViewById(R.id.et);
        et.addTextChangedListener(this);
        secret=rw();
        pubsec=macreate();
        printText(pubsec);

    }

    public String rw() {

        Random rand = new Random();
        randint = rand.nextInt((40 - 1) + 1);
        return secrets[randint].toLowerCase();
    }
    public char[] macreate(){
        char[]maa= new char[secret.length()];
        for(int i=0; i<secret.length();i++){
            if(secret.charAt(i)==' ')
                maa[i]='-';
            else maa[i]='_';
        }
        return maa;
    }

    public void ShowLet(int x){
        for(int i=0; i<secret.length();i++){
            if(i==x) pubsec[i]=secret.charAt(i);
        }
        printText(pubsec);
    }

    public void printText(char[]text)
    {
        String OnScreen="";
        for(int i=0; i< secret.length();i++)
        {
            OnScreen+=pubsec[i]+" ";
        }
        word.setText(OnScreen);
        boolean fully=true;
        for (int i=0; i< secret.length();i++){
            if(pubsec[i]=='_')fully=false;
        }
        if(fully){
            OnScreen=OnScreen.replace('-',' ');
            Intent intent=new Intent(MainActivity.this,WinActivity.class);
            intent.putExtra("word",secret);
            startActivity(intent);
            numofpic=0;
            gel=new char[26];
            GuestLs="Guessed letters: ";
            GL.setText(GuestLs);
            glc=0;
            pic.setImageResource(getResources().getIdentifier("hangman0" , "drawable", getPackageName()));
            secret=rw();
            pubsec=macreate();
            printText(pubsec);
        }
    }
    public void printPic(int index){
        if(numofpic<7){
            int imageKey = getResources().getIdentifier("hangman" + index, "drawable", getPackageName());
            pic.setImageResource(imageKey);
        }
        else
        {
            Intent intent=new Intent(MainActivity.this,LoseActivity.class);
            intent.putExtra("word",secret);
            startActivity(intent);
            numofpic=0;
            gel=new char[26];
            GuestLs="Guessed letters: ";
            glc=0;
            GL.setText(GuestLs);
            pic.setImageResource(getResources().getIdentifier("hangman0" , "drawable", getPackageName()));
            secret=rw();
            pubsec=macreate();
            printText(pubsec);
        }
    }
    public void letguess(char let)
    {
        boolean isthere=false;
        for (int i=0; i<glc;i++)
        {
            if(let==gel[i])isthere=true;
        }
        if (!isthere) {
            gel[glc]=let;
            glc++;
            GuestLs+=let+" ";
            numofpic++;
            printPic(numofpic);
        }
        GL.setText(GuestLs);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        boolean islet=false;
        boolean inText=false;
        if(et.getText().length()==1)
        {
            char let = et.getText().toString().toLowerCase().charAt(0);
            if(let>='a'&&let>='z')islet=true;
            for(int j=0; j<secret.length();j++)
            {
                if(secret.charAt(j)==let)
                {
                    ShowLet(j);
                    inText=true;
                }
            }
            if(!inText)
            {
                letguess(let);
            }

        }
        et.getText().clear();
    }

    @Override
    public void afterTextChanged(Editable editable)
    {
    }
}