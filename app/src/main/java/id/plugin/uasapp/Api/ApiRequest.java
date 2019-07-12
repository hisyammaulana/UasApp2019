package id.plugin.uasapp.Api;

import id.plugin.uasapp.Model.ResponsModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponsModel> sendBiodata(@Field("nik") String nik,
                                   @Field("nama") String nama,
                                   @Field("kelas") String kelas,
                                   @Field("jam") String jam);

    @GET("read.php")
    Call<ResponsModel> getBiodata();

}
