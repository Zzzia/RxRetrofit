package com.zia.rxretrofit.encapsulation.net;

import com.zia.rxretrofit.encapsulation.service.RedRockService;
import com.zia.rxretrofit.encapsulation.service.ZzziaService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created By zia on 2018/8/6.
 * Retrofit管理库，采用了用单例模式
 * 记得保存代理后的类节省反射开销
 */
public class RetrofitManager {

    private static final String BASE_ZZZIA = "http://zzzia.net:8080/api/";
    private static final String BASE_REDROCK = "https://wx.idsbllp.cn";

    private RetrofitManager() {

    }

    private static class RetrofitHolder {
        private final static RetrofitManager instance = new RetrofitManager();
    }

    public static RetrofitManager getInstance() {
        return RetrofitHolder.instance;
    }

    //okHttp配置
    private OkHttpClient client = new OkHttpClient().newBuilder()
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(new ResponseInterceptor())
            .build();

    //请求zzzia的retrofit
    private Retrofit retrofit_zzzia = new Retrofit.Builder()
            .baseUrl(BASE_ZZZIA)
//            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //请求redRock的retrofit
    private Retrofit retrofit_redRock = new Retrofit.Builder()
            .baseUrl(BASE_REDROCK)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    //代理后的接口，这个需要保存下来，里面有map缓存
    ZzziaService zzzia = retrofit_zzzia.create(ZzziaService.class);
    RedRockService redRock = retrofit_redRock.create(RedRockService.class);


    //okHttp的拦截器，瞎写的，别看
    private static class ResponseInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {//log requests and responses
            Response response = chain.proceed(chain.request());
            ResponseBody responseBody = response.body();
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            MediaType contentType = responseBody.contentType();
            Charset charset = Charset.forName("UTF-8");
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            Buffer buffer = source.buffer().clone();
            String content = buffer.readString(charset);
            System.out.println("request: " + chain.request().toString());
            System.out.println("response: " + response.toString());
            for (String s : content.split("\n")) {
                System.out.println("content: " + s);
            }
            return response;
        }
    }
}
