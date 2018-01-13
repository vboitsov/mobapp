package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.entity;

/**
 * Created by vb on 11/01/2018.
 */

public class MakeRequestResponse {


    private final boolean made;

    public MakeRequestResponse(boolean isMade) {
        this.made = isMade;
    }

    public boolean isMade() {
        return made;
    }
}
