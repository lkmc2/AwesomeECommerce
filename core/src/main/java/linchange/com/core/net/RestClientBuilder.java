package linchange.com.core.net;

import android.content.Context;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import linchange.com.core.net.callback.IError;
import linchange.com.core.net.callback.IFailure;
import linchange.com.core.net.callback.IRequest;
import linchange.com.core.net.callback.ISuccess;
import linchange.com.core.ui.loader.LoaderStyle;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by lkmc2 on 2017/12/29.
 * 网络请求客户端生成器
 */

public class RestClientBuilder {
    private String mUrl = null; //请求网址
    private static final Map<String, Object> PARAMS = RestCreator.getParams(); //请求参数列表
    private IRequest mIRequest = null; //请求接口
    private ISuccess mISuccess = null; //请求成功接口
    private IFailure mIFailure = null; //请求失败接口
    private IError mIError = null; //请求错误接口
    private RequestBody mBody = null; //请求体
    private Context mContext = null; //上下文
    private LoaderStyle mLoaderStyle = null; //进度加载器样式
    private File mFile = null; //文件

    private String mDownloadDir = null; //下载路径
    private String mExtension = null; //后缀名
    private String mName = null; //下载文件名

    //构造器
    RestClientBuilder() {
    }

    /**
     * 设置请求网址
     * @param url 请求的url
     * @return 请求客户端生成器
     */
    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    /**
     * 设置请求参数
     * @param params 请求参数
     * @return 请求客户端生成器
     */
    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    /**
     * 设置请求参数
     * @param key 参数名
     * @param value 参数值
     * @return 请求客户端生成器
     */
    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value); //将参数键值对参数参数列表
        return this;
    }

    /**
     * 设置下载存放路径
     * @param dir 下载路径名
     * @return 请求客户端生成器
     */
    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    /**
     * 设置下载后缀名
     * @param extension 文件后缀名
     * @return 请求客户端生成器
     */
    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    /**
     * 设置上传文件
     * @param file 上传的文件
     * @return 请求客户端生成器
     */
    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    /**
     * 设置上传文件（重载）
     * @param file 上传的文件路径
     * @return 请求客户端生成器
     */
    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    /**
     * 设置原生数据
     * @param raw 原生数据
     * @return 请求客户端生成器
     */
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    /**
     * 设置请求回调接口
     * @param iRequest 请求回调接口
     * @return 请求客户端生成器
     */
    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    /**
     * 设置请求成功回调接口
     * @param iSuccess 请求成功回调接口
     * @return 请求客户端生成器
     */
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    /**
     * 设置请求失败回调接口
     * @param iFailure 请求失败回调接口
     * @return 请求客户端生成器
     */
    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    /**
     * 设置请求错误回调接口
     * @param iError 请求错误回调接口
     * @return 请求客户端生成器
     */
    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    /**
     * 设置进度加载器样式
     * @param context 上下文
     * @param style 进度加载器样式
     * @return 请求客户端生成器
     */
    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    /**
     * 设置进度加载器样式（重载）
     * @param context 上下文
     * @return 请求客户端生成器
     */
    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    /**
     * 构建新的网络请求生成器
     * @return 网络请求生成器
     */
    public final RestClient build() {
        return new RestClient(mUrl, PARAMS, mDownloadDir, mExtension, mName, mIRequest,
                mISuccess, mIFailure, mIError, mBody, mLoaderStyle, mFile, mContext);
    }
}
