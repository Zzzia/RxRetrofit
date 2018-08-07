package com.zia.rxretrofit.encapsulation.demo.retrofit.custom;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created By zia on 2018/8/4.
 * 工厂模式，这里解析String和Gson对象，单例
 * 瞎写的，别参考
 */
public class MyConverterFactory extends Converter.Factory {

    private static final MyConverterFactory instance = new MyConverterFactory();
    private Gson gson = new Gson();

    private MyConverterFactory() {
    }

    public static MyConverterFactory create() {
        return instance;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
//        Type ttt = ((ParameterizedType)type).getActualTypeArguments()[0];
//        System.out.println(ttt.getTypeName());
        if (type == String.class) {//如果是String
            return StringConverter.instance;
        } else {//如果是String之外的（这里直接不管这么多了，假设是需要gson序列化的对象。。）（可能会有Void等等）
            try {
                type = ((ParameterizedType) type).getActualTypeArguments()[0];
            } catch (ClassCastException e) { }//这个tryCatch是不是很精髓 =_=
            return new GsonConverter(type);
        }
    }

    public enum StringConverter implements Converter<ResponseBody, String> {

        instance;

        @Override
        public String convert(ResponseBody responseBody) throws IOException {
            return responseBody.string();
        }
    }

    public class GsonConverter implements Converter<ResponseBody, Object> {

        private Type type;

        GsonConverter(Type type) {
            this.type = type;
        }

        @Override
        public Object convert(ResponseBody responseBody) throws IOException {
            return gson.fromJson(responseBody.string(), type);
        }
    }
}