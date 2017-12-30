package linchange.com.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.File;
import java.io.InputStream;

import linchange.com.core.app.Awesome;
import linchange.com.core.net.callback.IRequest;
import linchange.com.core.net.callback.ISuccess;
import linchange.com.core.util.file.FileUtil;
import okhttp3.ResponseBody;

/**
 * Created by lkmc2 on 2017/12/30.
 * 保存文件异步任务
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST; //请求接口回调
    private final ISuccess SUCCESS; //成功接口回调

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    //后台执行
    @Override
    protected File doInBackground(Object... params) {
        String downloaderDir = (String) params[0]; //下载路径
        String extension = (String) params[1]; //文件后缀名
        final ResponseBody body = (ResponseBody) params[2]; //请求体
        final String name = (String) params[3]; //文件名
        final InputStream inputStream = body.byteStream(); //请求体字节流

        if (downloaderDir == null || "".equals(downloaderDir)) { //下载路径为空
            downloaderDir = "down_loads"; //设置默认下载路径
        }

        if (extension == null || "".equals(extension)) { //后缀名为空
            extension = ""; //设置默认后缀名
        }

        if (name == null) { //文件名为空
            //以图片格式为文件名存储
            return FileUtil.writeToDisk(inputStream, downloaderDir,
                    extension.toUpperCase(), extension);
        } else { //文件名非空
            //正常存储
            return FileUtil.writeToDisk(inputStream, downloaderDir, name);
        }
    }

    //执行完成后的处理
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (SUCCESS != null) { //请求成功接口回调非空
            SUCCESS.onSuccess(file.getPath()); //执行请求成功接口回调
        }
        if (REQUEST != null) { //请求接口回调非空
            REQUEST.onRequestEnd(); //执行请求接口回调
        }

        autoInstallApk(file); //自动安装apk文件
    }

    /**
     * 自动安装apk文件
     * @param file apk文件
     */
    private void autoInstallApk(File file) {
        if ("apk".equals(FileUtil.getExtension(file.getPath()))) { //文件后缀名为apk
            final Intent intent = new Intent(); //新建意图
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //设置标记
            intent.setAction(Intent.ACTION_VIEW); //设置动作
            //设置数据类型
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Awesome.getApplicationContext().startActivity(intent); //启动intent
        }
    }
}
