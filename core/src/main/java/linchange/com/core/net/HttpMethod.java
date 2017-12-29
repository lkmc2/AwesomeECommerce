package linchange.com.core.net;

/**
 * Created by lkmc2 on 2017/12/29.
 * 网络请求方式枚举
 */

public enum HttpMethod {
    GET, //get方式请求
    POST, //post方式请求
    POST_RAW, //post原生数据方式请求
    PUT, //put存入方式请求
    PUT_RAW, //put存入原生数据方式请求
    DELETE, //delete删除方式请求
    UPLOAD //upload上传方式请求
}
