package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

/**
 * Created by vb on 11/01/2018.
 */

public class EmailAuthResponse {

    private final boolean sent;

    public EmailAuthResponse(boolean isSent) {
        this.sent = isSent;
    }

    public boolean isSent() {
        return sent;
    }

}
