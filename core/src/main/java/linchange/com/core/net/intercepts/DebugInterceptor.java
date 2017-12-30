package linchange.com.core.net.intercepts;

import android.support.annotation.RawRes;

import java.io.IOException;

import linchange.com.core.util.file.FileUtil;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by lkmc2 on 2017/12/30.
 * 调试拦截器
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL; //调试网址
    private final int DEBUG_RAW_ID; //调试文件的id

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    /**
     * 获取响应
     * @param chain 请求链
     * @param json json数据
     * @return 响应数据
     */
    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    /**
     * 获取调试的响应
     * @param chain 请求链
     * @param rawId 原生资源id
     * @return 响应
     */
    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId); //获取原生资源的json数据
        return getResponse(chain, json); //返回新建响应
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求链的网址
        final String url = chain.request().url().toString();

        //请求链的网址中包含调试网址
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID); //返回调试响应
        }
        return chain.proceed(chain.request()); //返回正常响应
    }

}
