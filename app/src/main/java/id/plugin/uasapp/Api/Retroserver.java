package id.plugin.uasapp.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retroserver {
    private  static  final String base_url = "http://192.168.1.28/apiuas/";

    private static Retrofit retrofit;


    public static Retrofit getClient()
    {
        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return  retrofit;
    }
}
