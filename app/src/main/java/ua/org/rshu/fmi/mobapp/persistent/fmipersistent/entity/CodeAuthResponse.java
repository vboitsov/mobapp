package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

/**
 * Created by vb on 11/01/2018.
 */

public class CodeAuthResponse {

    private final String token;

    public CodeAuthResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
