package com.yourui.web;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StrTest {

    @Test
    public void test1(){
        String str = "{\"withdrawMoney_1532523008172\":{\"realName\":\"312312\",\"amount\":10.8,\"alipayCardNo\":\"123\",\"idCard\":\"441821199409230055\",\"transferMode\":\"ALIPAY\",\"accountNo\":\"150136_zn\",\"accessorTransferNo\":\"46_150136_zn2018-07-25-08-50-07\"},\"withdrawMoney_1532538796399\":{\"realName\":\"44\",\"amount\":10.0,\"alipayCardNo\":\"44445454545\",\"idCard\":\"441821199409230055\",\"transferMode\":\"ALIPAY\",\"accountNo\":\"150136_zn\",\"accessorTransferNo\":\"47_150136_zn2018-07-26-01-13-16\"},\"withdrawMoney_1532533796754\":{\"realName\":\"da\",\"amount\":11.0,\"alipayCardNo\":\"78979\",\"idCard\":\"441821199409230055\",\"transferMode\":\"ALIPAY\",\"accountNo\":\"150136_zn\",\"accessorTransferNo\":\"91_150136_zn2018-07-25-11-49-41\"},\"nickName\":\"984185\",\"withdrawMoney_1532535575597\":{\"realName\":\"213\",\"amount\":11.0,\"alipayCardNo\":\"24\",\"idCard\":\"441821199409230055\",\"transferMode\":\"ALIPAY\",\"accountNo\":\"150136_zn\",\"accessorTransferNo\":\"5_150136_zn2018-07-26-12-19-35\"}}";

        String[] split = str.split("\":*");
        List<String> list = new ArrayList<>();
        for (String splitStr:split) {
            if (splitStr.indexOf("withdrawMoney") != -1){
                list.add(splitStr);
            }
        }

        System.out.println(list);
    }

    @Test
    public void test2(){
//        String encrypt = "99feb2d23d0e32dbdd521934e7847d762274c184f3338b0658bdad8b55c9caf92d82081d71c990ecfeeb7e90447c0a512f00153e5cf22edf9761e85eb17a6b571b3898ec9d289d618fa838eed57a5e7fa226d1687a062125119790be4b93285ab21e6b77c3eaaab5cd25ddec835d7c0bf7193594b47a1ad7c333243d0c531e25c1668fc38054a968e46499a6ba975f0af78e5ef16042bbd4020a82e83e13dd27e01be7f1281b238ea1f07118127858db4031a3d89fdc41bef79cf49411f4d34ae01decef3f6136804cea4702b7d9df357693e51d671966f5ce4c556a3497d39c";
        String encrypt = "99feb2d23d0e32dbdd521934e7847d762274c184f3338b0658bdad8b55c9caf9df8426e6affe4d3ed31e39e9d348dc86209cd6be9430e2806e8c14cc156846412a75f9764c0fe90b9908b1bd033b0335aa5327f5d824efd243ed4b1c110022b5a591ca1bf18956849301286e5e263b534ad11936baa637b4e6c4cd32493e3c778e522a1e327d035225398b693a2ef03a3c701d68b4a2c23e23a99ddf9120190e6a194150832594c05ad7ac0df9ba38da72e428e063bcbab1fe26329417ea2a5bef94a6d37c6eea6130567a70d70970e2f99f50f5bf44eb685b5bf5c7d2b3fc662ca2f71ddd0b8b94a5365dfec65babb4015b1583c3da935d2cb8925ceb4aae63c03af8a9103f91bfd99f69b33fe212fe0e92a4535cf152a67ac25977be2b6957c2bace86c674c7b4684c4f709e01b916f9759dacdc5d7709f35625f508b1118d1baedd8fedaba87b7c9621139c3b164eacef92d82773d26db8109b8871dfdc19994bd408ac298c14aceb731eda01004e04f7e796dc04fa397b8dbea0b4a83e5158eea8f52467fd92600ba366fa099289";

        String key = "id6plnnt2d9a9kzh";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());

        String decrypt = aes.decryptStr(encrypt);
        System.out.println(decrypt);
    }

    @Test
    public void test3(){
        for (int i = 0; i < 30; i++) {
            String s = RandomUtil.randomString(16);
            System.out.println(s);
        }
    }
}
