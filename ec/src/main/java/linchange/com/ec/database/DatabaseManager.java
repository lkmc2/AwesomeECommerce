package linchange.com.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by lkmc2 on 2018/2/22.
 * 数据库管理器
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null; //dao会话
    private UserProfileDao mDao = null; //用户信息dao

    private DatabaseManager() {
    }

    /**
     * 初始化信息
     * @param context 上下文
     * @return 本对象
     */
    public DatabaseManager init(Context context) {
        initDao(context); //初始化dao数据
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCE = new DatabaseManager(); //单例对象
    }

    public static DatabaseManager getInstance() { //获取实例对象
        return Holder.INSTANCE;
    }

    /**
     * 初始化dao数据
     * @param context 上下文
     */
    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb(); //获取可写数据库
        mDaoSession = new DaoMaster(db).newSession(); //获取新的会话
        mDao = mDaoSession.getUserProfileDao(); //获取用户信息访问对象
    }

    /**
     * 获取Dao对象
     * @return Dao对象
     */
    public final UserProfileDao getDao() {
        return mDao;
    }
}
