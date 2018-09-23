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

import java.io.IOException;
import java.io.OutputStream;

import it.visionmobya.CSVModule.VisionFileManager;
import it.visionmobya.Interface.ProgressBarMessage;
import it.visionmobya.activities.MainActivity;
import it.visionmobya.R;
import it.visionmobya.models.customModels.FtpResponse;
import it.visionmobya.models.customModels.ServerRequest;
import it.visionmobya.utils.CodesUtil;
import it.visionmobya.utils.MySharedPref;

public class FtpClientTask extends AsyncTask<ServerRequest, String, FtpResponse> implements ProgressBarMessage {

    private FTPClient ftpClient;
    private ServerRequest serverRequest;
    private ProgressDialog progressDialog;
    private Context context;
    private MySharedPref mySharedPref;


    public FtpClientTask(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        mySharedPref = new MySharedPref(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Logging in");
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected FtpResponse doInBackground(ServerRequest... serverRequests) {
       this.serverRequest = serverRequests[0];
       String SERVER_URL = serverRequest.getServerCredentials().getServerUrl();
       Integer SERVER_PORT = serverRequest.getServerCredentials().getPort();
       String USERNAME = serverRequest.getServerCredentials().getUsername();
       String PASSWORD = serverRequest.getServerCredentials().getPassword();
       boolean status = false;
       ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER_URL, SERVER_PORT);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                status = ftpClient.login(USERNAME, PASSWORD);
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                String[] filesInServer = ftpClient.listNames();

                //shfaq te gjitha filet qe ndodhen ne server
                for(int i = 0 ; i< filesInServer.length;i++){
                    Log.d("Files", "Server File " + 1 + filesInServer[i]);
                }

                Log.d("Ftp ", " Value of status : " + status);
                //logjika per marrjen e filet nga serveri lokalisht
                //iterojme per sa filename kemi futur si parameter dhe per cdo filename krijojme nje file dhe e kthejme mbrapsht tek metoda on PostExecute
                for(String filename : serverRequest.getFilename()){
                    ftpClient.changeWorkingDirectory(serverRequest.getServerCredentials().getWorkingDirectory());
                   // bufferedInputStream = new BufferedInputStream(new FileInputStream(SERVER_URL+filename));
                    try (OutputStream os = serverRequest.getContext().openFileOutput(filename,0)){
                        boolean retrieved =  ftpClient.retrieveFile(filename,os);
                        Log.d("SaveFile", "File : " + filename + " retrieved : " + retrieved);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(status) {
            VisionFileManager.getInstance().init( context, this);
            return new FtpResponse(FtpResponse.STATUS_OK);
        }
        else
         return new FtpResponse(FtpResponse.STATUS_FAIL);
    }

    @Override
    protected void onPostExecute(FtpResponse ftpResponse) {
        super.onPostExecute(ftpResponse);
        progressDialog.dismiss();
        mySharedPref.saveObjectToSharedPreference(CodesUtil.USER_NAME, serverRequest.getServerCredentials().getUsername());
        mySharedPref.saveObjectToSharedPreference(CodesUtil.LOGGED_IN, true);

        if(ftpResponse.getResponseCode()==FtpResponse.STATUS_OK){
            Intent intent = new Intent(serverRequest.getContext(), MainActivity.class);
            serverRequest.getContext().startActivity(intent);
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

    @Override
    public void onLoadFile( String progressMessage) {
        publishProgress(progressMessage);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        progressDialog.setMessage(values[0]);

    }
}
