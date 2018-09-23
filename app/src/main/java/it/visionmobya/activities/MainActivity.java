package it.visionmobya.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.Interface.ProgressBarMessage;
import it.visionmobya.R;
import it.visionmobya.utils.CodesUtil;
import it.visionmobya.utils.MySharedPref;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private RelativeLayout relNewDoc, relEditDoc, relRequestDoc, relReceipt, relViewArchives, relContatori, relConfigStampante, relExportArchive, relExit;
    private boolean noLogin = false;
    private MySharedPref mySharedPref;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySharedPref = new MySharedPref(this);
        initViews();
        if (getIntent().getBooleanExtra(CodesUtil.NO_LOGIN, false)) {
            new LoadFilesTask().execute();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relNewDoc:
                Intent intent1 = new Intent(MainActivity.this, DocumentTypeActivity.class);
                startActivity(intent1);
                break;
            case R.id.relEditDoc:
                Toast.makeText(this, "Edit Doc", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relRequestDoc:
                Toast.makeText(this, "relRequestDoc", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relReceipt:
                Toast.makeText(this, "relReceipt", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relViewArchives:
                Intent articlecategoryIntent = new Intent(MainActivity.this, ArticleCategoryActivity.class);
                startActivity(articlecategoryIntent);
                break;
            case R.id.relContatori:
                Toast.makeText(this, "relContatori", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relConfigStampante:
                Toast.makeText(this, "relConfigStampante", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relExportArchive:
                Toast.makeText(this, "relExportArchive", Toast.LENGTH_SHORT).show();
                break;
            case R.id.relExit:
                mySharedPref.saveObjectToSharedPreference(CodesUtil.LOGGED_IN, false);
                Intent loginIntent = new Intent(this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
                break;
        }
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Menu principale");

        relNewDoc = findViewById(R.id.relNewDoc);
        relEditDoc = findViewById(R.id.relEditDoc);
        relRequestDoc = findViewById(R.id.relRequestDoc);
        relReceipt = findViewById(R.id.relReceipt);
        relViewArchives = findViewById(R.id.relViewArchives);
        relContatori = findViewById(R.id.relContatori);
        relConfigStampante = findViewById(R.id.relConfigStampante);
        relExportArchive = findViewById(R.id.relExportArchive);
        relExit = findViewById(R.id.relExit);

        relNewDoc.setOnClickListener(this);
        relEditDoc.setOnClickListener(this);
        relRequestDoc.setOnClickListener(this);
        relReceipt.setOnClickListener(this);
        relViewArchives.setOnClickListener(this);
        relContatori.setOnClickListener(this);
        relConfigStampante.setOnClickListener(this);
        relExportArchive.setOnClickListener(this);
        relExit.setOnClickListener(this);
    }


    private class LoadFilesTask extends AsyncTask<Void, Void, Void> implements ProgressBarMessage {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading Data!");
            progressDialog.setIndeterminate(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... contexts) {
            VisionFileManager.getInstance().init(MainActivity.this, this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            progressDialog = null;
        }

        @Override
        public void onLoadFile(String progressMessage) {
            progressDialog.setMessage(progressMessage);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.AlertDialogBox);
                alertDialog.setTitle("Esci");
                alertDialog.setMessage("Sei sicuro di voler uscire?");
                alertDialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mySharedPref.saveObjectToSharedPreference(CodesUtil.LOGGED_IN, false);
                        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
