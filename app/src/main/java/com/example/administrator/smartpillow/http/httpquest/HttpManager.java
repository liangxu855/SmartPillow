package com.example.administrator.smartpillow.http.httpquest;

import android.util.SparseArray;

import com.example.administrator.smartpillow.code.MyApplication;
import com.example.administrator.smartpillow.http.GsonHelper;
import com.example.administrator.smartpillow.http.mode.BaseServiceApi;
import com.example.administrator.smartpillow.http.mode.ServiceApi;
import com.example.administrator.smartpillow.model.httpresponse.HeadType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.administrator.smartpillow.model.UserInfo.userData;


/**
 * Created by liukun on
 * 管理Retrofit 和 ServiceApi
 */
public class HttpManager {
    //请求地址
    public static final String BASE_HOST = ApiConstants.BASE_HOST;
    //路径
    public static final String BASE_API = "";
    //默认的超时时间
    private static final int DEFAULT_TIMEOUT = 30;
    //加长的超时间
    private static final int LONG_TIMEOUT = 200;

    public Retrofit retrofit;
    //可以在下一步请求的时候,获得对象
    public ServiceApi serviceApi;
    public BaseServiceApi baseServiceApi;

    private static SparseArray<HttpManager> mHttpManagers = new SparseArray<>();

    /**
     * 构建头部类型
     */
    private static HashMap<String, Integer> hashMap = new HashMap();

    //构造方法私有
    private HttpManager() {
        retrofit = getRetrofit();
        //3:创建请求
        serviceApi = retrofit.create(ServiceApi.class);
        //一步一步将对象传递上来,然后在BaseHttpRequest调用
        baseServiceApi = retrofit.create(BaseServiceApi.class);
        //4订阅回调数据
        //5处理返回值
    }

    private Retrofit getRetrofit() {
        //2：生成Retrofit实例
        return getRetrofitBuilderBase()
                .build();
    }

    /**
     * 2：生成Retrofit实例
     */
    private Retrofit.Builder getRetrofitBuilderBase() {

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ApiConstants.getHost())
                .client(getOkHttpClientType());
    }

    /**
     * 注释说明: 请求网络时增加头部
     * <p>
     * NULL_HEAD            没有头部（一些共用接口，不需要头部）
     * LOGIN_HEAD           两个头部(登录时要添加的头部)
     * UNREGISTERED_HEAD    三个头部（未登录要添加的头部）
     * 阳：2017/3/4-15:02
     */
    private OkHttpClient getOkHttpClientType() {

        final OkHttpClient builder = getRetrofitBuilder().addInterceptor(new Interceptor() {
            //intercept拦截
            @Override
            public Response intercept(Chain chain) throws IOException {
                //获取请求
                Request oldRequest = chain.request();
                String str = oldRequest.url().encodedPath();
                Request request;
                int type = 0;
                if (hashMap.get(str) != null) {
                    type = hashMap.get(str).intValue();
                }
                if (type == HeadType.LOGIN_HEAD.getKey()) {
                    request = chain.request().newBuilder()
                            .addHeader("code", String.valueOf(userData.getCode()))
                            .addHeader("token", userData.getToken())
                            .build();
                } else if (type == HeadType.UNREGISTERED_HEAD.getKey()) {
                    request = chain.request().newBuilder()
                            .addHeader("mac", userData.getMac())
                            .addHeader("ticket", userData.getTicket())
                            .addHeader("secret", userData.getSecret())
                            .build();
                } else {
                    request = chain.request().newBuilder()
                            .build();
                }
                return chain.proceed(request);
            }
        }).build();
        return builder;
    }


    public OkHttpClient.Builder getRetrofitBuilder() {
        //1：手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);//链接失败重新链接
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//链接超时
        builder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//读取超时
        builder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);//写入超时
        //设置缓存目录
        File cacheDirectory = new File(MyApplication.appSDCachePath, "HttpCache");
        final Cache cache = new Cache(cacheDirectory, 50 * 1024 * 1024);//
        builder.cache(cache);//设置缓存
        return builder;
    }

    //获取单例，不带头部类型
    public static HttpManager getInstance() {
        return getInstance(HostType.TYPE_DEFAULT, -1, null);
    }

    //获取单例，默认头部类型
    public static HttpManager getInstance(int headType, String path) {
        return getInstance(HostType.TYPE_DEFAULT, headType, path);
    }


    /**
     * 获取HttpManager
     *
     * @param hostType 请求主机地址
     * @param headType 头部类型
     * @param path     请求的路径
     * @return HttpManager
     */
    public static HttpManager getInstance(@HostType.HostTypeChecker int hostType, int headType, String path) {
        HttpManager retrofitManager = mHttpManagers.get(hostType);
        if (headType != -1) {
            //已存入的url作为key值,来作为标识.
            hashMap.put(path, headType);
        }
        if (retrofitManager == null) {
            retrofitManager = new HttpManager();
            mHttpManagers.put(hostType, retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }


    public OkHttpClient getOkHttpClient() {
        return (OkHttpClient) getRetrofit().callFactory();
    }


    /**
     * 单独的一个Retrofit  用于获取上传的进度
     * <p>
     * 当参数为空  就用单列的serviceApi
     *
     * @param progressCallBack
     * @return
     */
    public ServiceApi getServiceApi(final ProgressCallBack progressCallBack) {
        if (progressCallBack == null)
            return serviceApi;
        else {
            OkHttpClient.Builder builder = getRetrofitBuilder();
            builder.writeTimeout(LONG_TIMEOUT, TimeUnit.SECONDS);//写入超时

            builder.addNetworkInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    //增加请求的精度回调
                    Request newRequest = chain.request().body() != null ? chain.request().newBuilder().post(new ProgressRequestBody(chain.request().body(), progressCallBack)).
                            build() : chain.request();

                    return chain.proceed(newRequest);

                }
            });
            return getRetrofit().create(ServiceApi.class);
        }
    }

    /**
     * 单独的一个Retrofit  用于传大文件
     * <p>
     * 当参数为空  就用单列的serviceApi
     *
     * @return
     */
    public BaseServiceApi getLongServiceApi() {
        OkHttpClient.Builder builder = getRetrofitBuilder();
        builder.writeTimeout(LONG_TIMEOUT, TimeUnit.SECONDS);//写入超时
        return getRetrofit().create(BaseServiceApi.class);
    }

    /**
     * 设置接口类型
     *
     * @param headType
     * @param path
     */
    public synchronized void setHeadType(int headType, String path) {
        hashMap.put(path, headType);
    }
}
