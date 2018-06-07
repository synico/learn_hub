package com.nick.util;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.KeyGenerator;

/**
 * refer to https://stackoverflow.com/questions/35102645/getting-viewexpiredexception-in-clustered-environment-while-state-saving-method
 * 
 *    <env-entry>
        <env-entry-name>jsf/ClientSideSecretKey</env-entry-name>
        <env-entry-type>java.lang.String</env-entry-type>
        <env-entry-value>CJYiHDxSeLENJsaFcWepfO+Gt6+rK5YL83jrYdiYx2g=</env-entry-value>
      </env-entry>
 * @author 212706300
 *
 */

public class GenAESKey {

    public static void main(String[] args) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            String key = Base64.getEncoder().encodeToString(keyGen.generateKey().getEncoded());
            System.out.println(key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        

    }

}
