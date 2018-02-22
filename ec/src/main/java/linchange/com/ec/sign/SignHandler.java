package linchange.com.ec.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import linchange.com.ec.database.DatabaseManager;
import linchange.com.ec.database.UserProfile;

/**
 * Created by lkmc2 on 2018/2/22.
 * 注册控制器
 */

public class SignHandler {

    /**
     * 注册，将json数据持久化到本地
     * @param response 网络返回的json数据
     */
    public static void onSignUp(String response) {
        //获取json响应中的数据
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");

        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        //生成用户信息
        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile); //将用户信息插入数据库
    }
    
}
