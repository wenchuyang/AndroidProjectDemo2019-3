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
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

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
        showInputDialog();
//        call();
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
}

