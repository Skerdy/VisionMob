package it.visionmobya.models.customModels;

public class ServerCredentials {

    private String username;
    private String password;
    private String serverUrl;
    private Integer port;
    private String workingDirectory;

    public ServerCredentials(String username, String password, String serverUrl, Integer port) {
        this.username = username;
        this.password = password;
        this.serverUrl = serverUrl;
        this.port = port;
    }

    public String getWorkingDirectory() {
        return workingDirectory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.workingDirectory = workingDirectory;
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
