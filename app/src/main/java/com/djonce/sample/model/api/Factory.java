package com.djonce.sample.model.api;


import com.donce.common.model.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class Factory {
    public static ApiHttpService provideHttpService() {
        return provideService(ApiHttpService.class);
    }

    private static Map<Class, Object> m_service = new HashMap();

    private static <T> T provideService(Class cls) {
        Object serv = m_service.get(cls);
        if (serv == null) {
            synchronized (cls) {
                serv = m_service.get(cls);
                if (serv == null) {
                    serv = HttpClient.getIns(ApiConstant.BASE_URL).createService(cls);
                    m_service.put(cls, serv);
                }
            }
        }
        return (T) serv;
    }
}
