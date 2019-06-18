package com.kzw.sq.sqcommunity.provider;

import com.alibaba.fastjson.JSON;
import com.kzw.sq.sqcommunity.dto.AccessTokenDto;
import com.kzw.sq.sqcommunity.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String s =response.body().string();
                String[] split = s.split("&");
                String s1 = split[0];
                String[] split1 = s1.split("=");
                String s2 = split1[1];
//                System.out.println(s1);
//                System.out.println("------------");
//                System.out.println(s2);
//                System.out.println("------------");
//                System.out.println(s);
                return s2;
            } catch (IOException e) {
                    e.printStackTrace();
                }
            return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();
        try {
            Response response = client.newCall(request).execute();
            String s =response.body().string();
            GithubUser githubUser =JSON.parseObject(s,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
