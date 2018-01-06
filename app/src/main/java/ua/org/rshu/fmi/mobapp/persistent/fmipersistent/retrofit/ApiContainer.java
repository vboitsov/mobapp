package ua.org.rshu.fmi.mobapp.persistent.fmipersistent.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vb on 16/11/2017.
 */

public class ApiContainer {

    private static FmiApi fmiApi = null;

    public synchronized static FmiApi getFmiServiceInstance() {
        if(fmiApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            fmiApi = retrofit.create(FmiApi.class);
        }
        return fmiApi;
    }


}
