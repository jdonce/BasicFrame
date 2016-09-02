package com.donce.common.model;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.donce.common.BaseApplication;
import com.donce.common.BuildConfig;
import com.donce.common.R;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.assit.SQLiteHelper;

/**
 * 数据库管理类
 * Created by Administrator on 2016/9/1 0001.
 */
public class OrmManager implements SQLiteHelper.OnUpdateListener {
    private static OrmManager mInstance;
    private LiteOrm liteOrm;
    //数据库名，表名是自动被创建的
    public static final String DB_NAME = "sample.db";

    public OrmManager() {
        Context context = BaseApplication.getInstance().getApplicationContext();
        DataBaseConfig config = new DataBaseConfig(context, DB_NAME);
        config.debugged = context.getResources().getBoolean(R.bool.debug); // open the log
        config.dbVersion = context.getResources().getInteger(R.integer.db_version); // set database version
        config.onUpdateListener = this; // set database update listener
        liteOrm = LiteOrm.newCascadeInstance(config);
    }

    public static OrmManager getInstance() {
        if (mInstance == null) {
            mInstance = new OrmManager();
        }
        return mInstance;
    }

    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    @Override
    public void onUpdate(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.d("onUpdate", "update from version" + oldVersion + "to" + newVersion + "---------------");
        //android-lite-orm 智能列探测：App升级或者Model改变，新加了属性字段，该字段将被探测到并加入数据库中，因此无需担心新字段不被存储

    }
}
