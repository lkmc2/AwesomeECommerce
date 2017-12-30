package linchange.com.core.net.rx;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import linchange.com.core.net.RestClient;
import linchange.com.core.net.RestCreator;
import linchange.com.core.net.callback.IError;
import linchange.com.core.net.callback.IFailure;
import linchange.com.core.net.callback.IRequest;
import linchange.com.core.net.callback.ISuccess;
import linchange.com.core.ui.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by lkmc2 on 2017/12/29.
 * RxJava响应式网络请求客户端生成器
 */

public class RxRestClientBuilder {
    private String mUrl = null; //请求网址
    private static final Map<String, Object> PARAMS = RestCreator.getParams(); //请求参数列表
    private RequestBody mBody = null; //请求体
    private Context mContext = null; //上下文
    private LoaderStyle mLoaderStyle = null; //进度加载器样式
    private File mFile = null; //文件


    //构造器
    RxRestClientBuilder() {
    }

    /**
     * 设置请求网址
     * @param url 请求的url
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 设置请求参数
     * @param params 请求参数
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    /**
     * 设置请求参数
     * @param key 参数名
     * @param value 参数值
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value); //将参数键值对参数参数列表
        return this;
    }


    /**
     * 设置上传文件
     * @param file 上传的文件
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 设置上传文件（重载）
     * @param file 上传的文件路径
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 设置原生数据
     * @param raw 原生数据
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 设置进度加载器样式
     * @param context 上下文
     * @param style 进度加载器样式
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    /**
     * 设置进度加载器样式（重载）
     * @param context 上下文
     * @return 请求客户端生成器
     */
    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    /**
     * 构建新的网络请求生成器
     * @return 网络请求生成器
     */
    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mLoaderStyle, mFile, mContext);
    }
}
