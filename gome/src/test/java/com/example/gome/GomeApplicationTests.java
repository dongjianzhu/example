package com.example.gome;

import cn.com.gome.cloud.openplatform.bridge.core.client.DefaultGmosClient;
import cn.com.gome.cloud.openplatform.bridge.core.client.GmosClient;
import gome.open.api.sdk.cloud.client.domain.bridge.client.request.GomeShopShopInfoGetRequest;
import gome.open.api.sdk.cloud.client.domain.bridge.client.response.GomeShopShopInfoGetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class GomeApplicationTests {

    @Test
    void contextLoads() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://oauth.gome.com.cn/token?grant_type=authorization_code&client_id=c52e799904a84598ad23b5b91f46184f&client_secret=474a73f61b74483982eea7864a24e2f4" +
                "&redirect_uri=http://127.0.0.1:8080/login/oauth2/code/gome&code=7a87fec098554bdca68e502bb8f20c80&state=OtiivJBFUNKL9R8nhxlXZ03PaE1Y52LocTbyfxHaRJM=";
        String s = restTemplate.getForObject(url, String.class);
        System.out.println(s);
    }

    String url = "http://gw.gome.com.cn/router";
    String appKey = "c52e799904a84598ad23b5b91f46184f";
    String secret = "474a73f61b74483982eea7864a24e2f4";
    String token = "1121f4fa2cfa43adbf0be127caa961e7";

    @Test
    void testShop(){
        GmosClient client = new DefaultGmosClient(url, appKey, secret, token);
        GomeShopShopInfoGetRequest request = new GomeShopShopInfoGetRequest();
        //详细参数信息请参考API详情页面。
        GomeShopShopInfoGetResponse response = client.execute(request);
        System.out.println(response.getBody());
    }
}
