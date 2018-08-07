package com.yourui.web;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.hutool.json.JSONObject;
import com.google.common.collect.Maps;

import cn.hutool.http.HttpUtil;

public class CountDownLatchTest  implements Runnable{
    static final CountDownLatch end = new CountDownLatch(1000);
    static final CountDownLatchTest demo = new CountDownLatchTest();
    static long fuckNum = 9999L;
    @Override
    public void run() {
        try {
            // 模拟检查任务
            JSONObject jsonObject = new JSONObject();
            Map test = Maps.newHashMap();
            test.put("userToken", ++fuckNum + "");
            jsonObject.put("ACT_NICK_NAME", "大狗" + fuckNum);
            jsonObject.put("ACT_ICO", "hz32123123123123123123123123123123");
            System.out.println(HttpUtil.createPost("http://192.168.1.144:10008/user/login").addHeaders(test).body(jsonObject.toString()).execute());
            System.out.println(HttpUtil.createPost("http://192.168.1.144:10008/scoreAct/index\n").addHeaders(test).body(jsonObject.toString()).execute());
            end.countDown();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newFixedThreadPool(1000);
        for (int i = 0; i < 100; i++) {
            timePost(exec, 1000);
            Thread.sleep(1000);
        }

        System.out.println("out!!");
        exec.shutdown();
    }

    private static void timePost(ExecutorService exec, int time) throws InterruptedException {
        for(int i = 0; i < time; i++) {
            exec.submit(demo);
        }
        end.await();
    }

}
