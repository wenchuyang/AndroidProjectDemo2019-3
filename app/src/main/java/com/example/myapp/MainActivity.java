package com.example.myapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    Context myBase = MainActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println((long)(Double.parseDouble("19.80")*100));
        setContentView(R.layout.activity_main);
    }

    public void startCall(View view){
        showPswDialog();
    }

    private void showPswDialog() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(MainActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(MainActivity.this);
        editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        inputDialog.setTitle("Please input password, then call me").setView(editText);
        inputDialog.setPositiveButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        inputDialog.setNegativeButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String password = new SimpleDateFormat("MMddyyyy").format(new Date());
                        if(editText.getText().toString().trim().equals(password)){
                            call();
                        }else{
                            Toast.makeText(MainActivity.this,
                                    "Wrong password " + editText.getText().toString()+ " ! DENIED!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        inputDialog.show();
    }
    private void showInputDialog() {
        /*@setView 装入一个EditView
         */
        final EditText editText = new EditText(MainActivity.this);
        AlertDialog.Builder inputDialog =
                new AlertDialog.Builder(MainActivity.this);
        editText.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        inputDialog.setTitle("Please input phone number, then call").setView(editText);
        inputDialog.setPositiveButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        inputDialog.setNegativeButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        call(editText.getText().toString().trim());
//                            Toast.makeText(MainActivity.this,
//                                    "Wrong password " + editText.getText().toString()+ " ! DENIED!",
//                                    Toast.LENGTH_SHORT).show();
                    }
                });
        inputDialog.show();
    }
    public void call(String telNum) {
        fileOpUtil fileOp = new fileOpUtil();
        String tel = "tel: "+telNum;
        fileOp.writeToFile(tel, myBase);
        String realTel = fileOp.readFromFile(myBase);
        Uri number = Uri.parse(realTel);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(callIntent);
        }
    }
    public void call() {
        String tel = "tel: 15075690870";
        Uri number = Uri.parse(tel);
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        // Verify it resolves
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(callIntent, 0);
        boolean isIntentSafe = activities.size() > 0;

        // Start an activity if it's safe
        if (isIntentSafe) {
            startActivity(callIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_op, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.name:
                Toast.makeText(this, "My name is wenchuyang", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.age:
                Toast.makeText(this, "I'm 100 years old", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

