package com.yourui.web;

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
            if (splitStr.indexOf("withdrawMoney")!=-1){
                list.add(splitStr);
            }
        }

        System.out.println(list);
    }

    @Test
    public void test2(){
//        String encrypt = "99feb2d23d0e32dbdd521934e7847d762274c184f3338b0658bdad8b55c9caf92d82081d71c990ecfeeb7e90447c0a512f00153e5cf22edf9761e85eb17a6b571b3898ec9d289d618fa838eed57a5e7fa226d1687a062125119790be4b93285ab21e6b77c3eaaab5cd25ddec835d7c0bf7193594b47a1ad7c333243d0c531e25c1668fc38054a968e46499a6ba975f0af78e5ef16042bbd4020a82e83e13dd27e01be7f1281b238ea1f07118127858db4031a3d89fdc41bef79cf49411f4d34ae01decef3f6136804cea4702b7d9df357693e51d671966f5ce4c556a3497d39c";
        String encrypt = "99feb2d23d0e32dbdd521934e7847d762274c184f3338b0658bdad8b55c9caf92d82081d71c990ecfeeb7e90447c0a51aca76d3cbfe8b7fd3500d0ac0fc7b7ea39754818b021d011c0a6a15b92a8b890a0053397867ea23321e766b642fb993de01be7f1281b238ea1f07118127858db84dde905816f78ed8ced9da26f254b9e97f2f2206be0ae0247cf67d18d41ac56898a9aa276351cae331b660ba0a6cbfe6adeef451e36d041dccd568d68efc916b7be2affb72d5c26294f4b1acfa289fb481c969872a6f6fb156b095297715b30b3425168c15b5fcfe1f5b5c64ce58703d6f71a8af3e836b9705dc33b0dcfee807477075c79da2bdbbda5642ca2fb3d937c0011d04f8bbc673377e99220996758c369e1ab5918244285d02ad1da5671a753abac8c39b807b4a9d5287268672c2e2e85d786e9a43a0e80cc8fdd557f039f87b6225037be4981351f9a97630c99a61194310763e69b1ea3221254fbd09e17a7f6c4ce03a208931de1583f238780852e85d786e9a43a0e80cc8fdd557f039f9ce8e3dfea5e86f66f802a65771fb4b0f231c05f706840bbf80ae1767108c778";

        String key = "id6plnnt2d9a9kzh";
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes());

        String decrypt = aes.decryptStr(encrypt);
        System.out.println(decrypt);
    }
}
