package it.visionmobya.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.CSVWriter;
import it.visionmobya.CSVModule.TextFiles;
import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.FTPClient.FtpClientTask;
import it.visionmobya.R;
import it.visionmobya.models.Article;
import it.visionmobya.models.DocRig;
import it.visionmobya.models.customModels.ServerCredentials;
import it.visionmobya.models.customModels.ServerRequest;
import it.visionmobya.utils.CodesUtil;
import it.visionmobya.utils.MySharedPref;
import it.visionmobya.utils.Utils;

public class LoginActivity extends AppCompatActivity {

    private TextView loginBtn;
    private EditText etPass, agentCode, etUrl, etPort;
    private VisionFileManager visionFileManager;
    private MySharedPref mySharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initUI();

        DocRig docRig = new DocRig.DocRigBuilder("12", "109").withDesDocRig("descrizione").withCodiceIva("12").build();

        try {
            CSVWriter.writeRecord(this, docRig, null);
        } catch (IOException e) {
            e.printStackTrace();
        }


        mySharedPref = new MySharedPref(this);

        //ne qofte se agjenti eshte i loguar shko direkt tek main activity duke kaluar boolean value true per key CodesUtil.NO_LOGIN
        if (mySharedPref.getSavedObjectFromPreference(CodesUtil.LOGGED_IN, boolean.class) != null && mySharedPref.getSavedObjectFromPreference(CodesUtil.LOGGED_IN, boolean.class)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(CodesUtil.NO_LOGIN, true);
            startActivity(intent);
        } else {
            visionFileManager = VisionFileManager.getInstance();
            if (mySharedPref.getStringFromSharedPref(CodesUtil.USER_NAME) != null) {
                if(mySharedPref.getStringFromSharedPref(CodesUtil.USER_NAME).equals("failedToGetStringFromShared")){
                    agentCode.setText("");
                }
                else
                agentCode.setText(mySharedPref.getStringFromSharedPref(CodesUtil.USER_NAME).replace("\"", ""));
            }
        }
    }

    private void initUI() {

        loginBtn = findViewById(R.id.login);
        etUrl = findViewById(R.id.etUrl);
        etPort = findViewById(R.id.etPort);
        etPass = findViewById(R.id.et_password);
        agentCode = findViewById(R.id.et_agentCode);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.hideKeyboardFrom(LoginActivity.this, loginBtn);
                if (validate()) {
                    login(agentCode.getText().toString(), etPass.getText().toString(), etUrl.getText().toString(), Integer.valueOf(etPort.getText().toString()+""));
                }
            }
        });
    }

    private boolean validate() {
        boolean flag = true;
        if (etUrl.getText().toString().trim().isEmpty()) {
            flag = false;
            etUrl.setError("Please insert Url!");
        }if (etPort.getText().toString().trim().isEmpty()) {
            flag = false;
            etUrl.setError("Please insert Port!");
        }
        if (agentCode.getText().toString().trim().isEmpty()) {
            flag = false;
            agentCode.setError("Please insert a username!");
        }
        if (etPass.getText().toString().trim().isEmpty()) {
            flag = false;
            etPass.setError("Please insert a password!");
        }
        return flag;
    }

    private void login(String username, String password, String url, Integer port) {
        List<String> files = new ArrayList<>();
        files.add(TextFiles.MAGGRP);
        files.add(TextFiles.ANAGRAFE);
        files.add(TextFiles.ALIQUOTE);
        files.add(TextFiles.LISTINI);
        files.add(TextFiles.MAGART);
        files.add(TextFiles.PAGAMENTI);
        ServerCredentials serverCredentials = new ServerCredentials(username, password, url, port);
        String workingDirectory = Utils.getAgentWorkingDirectory(username, Utils.IMPORT);
        serverCredentials.setWorkingDirectory(workingDirectory);
        FtpClientTask ftpClientTask = new FtpClientTask(this);
        ftpClientTask.execute(new ServerRequest(this, serverCredentials, files));
    }
}
