package it.visionmobya.models.customModels;

import java.io.Serializable;

public class ServerCredentials implements Serializable {

    private String username;
    private String password;
    private String serverUrl;
    private Integer port;
    private String importDirectory;
    private String exportDirectory;

    public ServerCredentials(String username, String password, String serverUrl, Integer port) {
        this.username = username;
        this.password = password;
        this.serverUrl = serverUrl;
        this.port = port;
    }

    public String getImportDirectory() {
        return importDirectory;
    }

    public void setImportDirectory(String importDirectory) {
        this.importDirectory = importDirectory;
    }

    public String getExportDirectory() {
        return exportDirectory;
    }

    public void setExportDirectory(String exportDirectory) {
        this.exportDirectory = exportDirectory;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
