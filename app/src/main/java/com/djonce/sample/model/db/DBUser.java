package com.djonce.sample.model.db;

import com.djonce.sample.model.bean.User;
import com.donce.common.model.OrmManager;
import com.litesuits.orm.LiteOrm;

import java.util.List;

/**
 * Created by Administrator on 2016/9/1 0001.
 */
public class DBUser {
    private LiteOrm liteOrm;

    public DBUser() {
        this.liteOrm = OrmManager.getInstance().getLiteOrm();
    }

    public List<User> queryUsers() {
        return liteOrm.query(User.class);
    }

    public void saveUser(User user) {
        liteOrm.save(user);
    }
}
