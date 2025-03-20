package com.hms;

import org.springframework.security.crypto.bcrypt.BCrypt;
//generate password for admin
public class A {
    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("testing",
                BCrypt.gensalt(5)));
    }

}
