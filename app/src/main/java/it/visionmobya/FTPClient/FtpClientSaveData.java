package it.visionmobya.FTPClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import it.visionmobya.CSVModule.TextFiles;
import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.R;
import it.visionmobya.activities.LoginActivity;
import it.visionmobya.activities.MainActivity;
import it.visionmobya.activities.OrdineClienteActivity;
import it.visionmobya.models.customModels.FtpResponse;
import it.visionmobya.models.customModels.ServerCredentials;
import it.visionmobya.models.customModels.ServerRequest;
import it.visionmobya.utils.Utils;

public class FtpClientSaveData extends AsyncTask<ServerCredentials,String, FtpResponse> {


    private ServerCredentials serverCredential;
    private FTPClient ftpClient;
    private Context context;
    private List<String>  filenames ;
    private ProgressDialog progressDialog;

    public FtpClientSaveData(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Saving data in server!");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        this.filenames = new ArrayList<>();
        filenames.add(TextFiles.DOCTES);
        filenames.add(TextFiles.DOCRIG);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.show();
    }

    @Override
    protected FtpResponse doInBackground(ServerCredentials... serverCredentials) {
        this.serverCredential = serverCredentials[0];
        String SERVER_URL = serverCredential.getServerUrl();
        Integer SERVER_PORT = serverCredential.getPort();
        String USERNAME = serverCredential.getUsername();
        String PASSWORD = serverCredential.getPassword();
        boolean status = false;
        ftpClient = new FTPClient();

        try {
            ftpClient.connect(SERVER_URL, SERVER_PORT);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                status = ftpClient.login(USERNAME, PASSWORD);
                if (status){
                    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                    ftpClient.enterLocalPassiveMode();
                    ftpClient.setAutodetectUTF8(true);
                    String exportDirectory = Utils.getAgentWorkingDirectory(USERNAME, Utils.EXPORT);
                    ftpClient.changeWorkingDirectory(exportDirectory);
                    for(String filename : filenames) {
                        Log.d("SaveServer", " Po behet save i : " + filename);
                        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + Utils.EXPORT + "/" + filename);
                        InputStream fileStream = new FileInputStream(file);
                        boolean uploaded = ftpClient.storeFile(filename, fileStream );
                        Log.d("SaveServer", " Store success " + filename + " " + uploaded);
                        fileStream.close();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(status) {
            return new FtpResponse(FtpResponse.STATUS_OK);
        }
        else {
            return new FtpResponse(FtpResponse.STATUS_FAIL);
        }
    }

    @Override
    protected void onPostExecute(FtpResponse ftpResponse) {
        super.onPostExecute(ftpResponse);
        progressDialog.dismiss();

        if(ftpResponse.getResponseCode()==FtpResponse.STATUS_OK){
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            ((OrdineClienteActivity)context).finish();
        }
        else {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context, R.style.AlertDialogBox);
            alertDialog.setTitle("Failure!");
            alertDialog.setMessage("Connecting to the server failed");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alertDialog.show();
        }
    }
}
