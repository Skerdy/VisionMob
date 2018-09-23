package it.visionmobya.models.customModels;

public class FtpResponse {

    public static final int STATUS_OK = 200;

    public static final int STATUS_FAIL = 201;


    private int responseCode;

    public FtpResponse(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}
