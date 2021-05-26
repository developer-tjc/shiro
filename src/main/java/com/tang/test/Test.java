package com.tang.test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class Test {
    public static void main(String[] args) {

        String password = "123";
//md5加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println(md5Hash.toHex());
//带盐的MD5加密。盐就是在原有字符串后面拼接盐形成新的字符串，然后加密。
        Md5Hash md5Hash2 = new Md5Hash(password, "2");
        System.out.println(md5Hash2);
//无论是否加盐都可以很容易的被破解，可以多次迭代加密保证数据安全性。
//第三个参数表示迭代加密次数
        Md5Hash md5Hash3 = new Md5Hash(password, 2);
        System.out.println(md5Hash3);

//使用Md5的父类也也实现
        SimpleHash simpleHash = new SimpleHash("md5",password,2);
        System.out.println(simpleHash);
    }
}
