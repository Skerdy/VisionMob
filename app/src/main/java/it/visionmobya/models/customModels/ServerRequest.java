package it.visionmobya.models.customModels;

import android.content.Context;

import java.util.List;

public class ServerRequest {

    private Context context;
    private ServerCredentials serverCredentials;
    private List<String> filenames;

    public ServerRequest(Context context, ServerCredentials serverCredentials, List<String> filename) {
        this.context = context;
        this.serverCredentials = serverCredentials;
        this.filenames = filename;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ServerCredentials getServerCredentials() {
        return serverCredentials;
    }

    public void setServerCredentials(ServerCredentials serverCredentials) {
        this.serverCredentials = serverCredentials;
    }

    public List<String> getFilename() {
        return filenames;
    }

    public void setFilename(List<String> filename) {
        this.filenames = filename;
    }
}
