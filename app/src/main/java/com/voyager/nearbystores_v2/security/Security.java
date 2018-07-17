package com.voyager.nearbystores_v2.security;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.voyager.nearbystores_v2.AppController;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.appconfig.AppContext;
import com.voyager.nearbystores_v2.utils.SecurityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Amine on 10/9/2016.
 */

public class Security {

    public static void init(Context context){
        CRYPRO_KEY = context.getResources().getString(R.string.crypt_key);
    }

    public static String KeyIV = "";
    public static String SKey = "";
    public static String CRYPRO_KEY;

    public static String getDecryptedResponse(String response){

        Security.KeyIV  = SecurityUtils.md5(CRYPRO_KEY+"1@"+ AppController.getTokens().get("token-1"))
                .toLowerCase().substring(0,16);
        Security.SKey   = SecurityUtils.md5(CRYPRO_KEY+"2@"+ AppController.getTokens().get("token-0"))
                .toLowerCase().substring(0,16);
        String responseDecrypted = Security.newInstance().decrypt(response);

        return responseDecrypted;
    }

    public static Map<String ,String> cryptedData(Map<String ,String > params){

        Security.KeyIV  = SecurityUtils.md5(CRYPRO_KEY+"1@"+ AppController.getTokens().get("token-1"))
                .toLowerCase().substring(0,16);
        Security.SKey   = SecurityUtils.md5(CRYPRO_KEY+"2@"+ AppController.getTokens().get("token-0"))
                .toLowerCase().substring(0,16);

        String hashed = Security.cryptHashMap(params);


        params = new HashMap<>();
        params.put(SecurityUtils.Tags.BODY,hashed);

        return params;
    }

    public static String cryptSimple(String map,String skey,String vi){
        Security s = new Security();
        s.setIv(vi);
        s.setSecretKey(skey);
        return s.encrypt(map);
    }

    public static String cryptHashMap(Map<String,String > map){

        String hashedStr="";
        JSONObject json = new JSONObject();
        try {

                if(map.size()>0){
                    for(String key: map.keySet()){
                        if(map.containsKey(key) && map.get(key)!=null){
                            json.put(key, URLEncoder.encode(map.get(key),"UTF-8") );
                        }else {
                            if(AppContext.DEBUG)
                                Log.e("hashKeyNull:"+key,key);
                        }

                    }
                }

            String cryptedData = newInstance().encrypt(json.toString());

            if(AppContext.DEBUG){
                Log.e("NoCryptedString - ",map.toString());
                Log.e("cryptedString - ",cryptedData);

            }
            return cryptedData;

        } catch (JSONException e) {
            if(AppContext.DEBUG)
                e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return json.toString();
    }





    private static Security mInstance;
    public static Security newInstance(){
        return new Security();
    }

    private String iv = KeyIV;//Dummy iv (CHANGE IT!)
    private IvParameterSpec ivspec;
    private SecretKeySpec keyspec;
    private Cipher cipher;

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }

    private String SecretKey = SKey;//Dummy secretKey (CHANGE IT!)

    public Security()
    {
        try {
            ivspec = new IvParameterSpec(iv.getBytes());
            keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");
        }catch (Exception e){}

        try {
            cipher = Cipher.getInstance("AES/CBC/NoPadding");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    public String encrypt(String text){
        try {

            if(!AppConfig.SAFE_MODE){
                byte[] data = text.getBytes("UTF-8");
                String base64 = Base64.encodeToString(data, Base64.DEFAULT);
                return base64;
            }else
                return bytesToHex(encryptToBytes(text));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }

    public String decrypt(String text){
        try {
            if(!AppConfig.SAFE_MODE){
                byte[] data = Base64.decode(text, Base64.DEFAULT);
                return new String(data, "UTF-8");
            }else
                return bytesToHex(decryptToBytes(text));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return text;
    }

    public byte[] encryptToBytes(String text) throws Exception
    {
        if(text == null || text.length() == 0)
            throw new Exception("Empty string");

        byte[] encrypted = null;

        try {

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] bs = text.getBytes("UTF-8");
            encrypted = cipher.doFinal(padBytes(bs));

        } catch (Exception e)
        {
            throw new Exception("[encrypt] " + e.getMessage());
        }

        return encrypted;
    }

    public byte[] decryptToBytes(String code) throws Exception
    {
        if(code == null || code.length() == 0)
            throw new Exception("Empty string");

        byte[] decrypted = null;

        try {
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            decrypted = cipher.doFinal(hexToBytes(code));

            if( decrypted.length > 0)
            {
                int trim = 0;
                for( int i = decrypted.length - 1; i >= 0; i-- ) if( decrypted[i] == 0 ) trim++;

                if( trim > 0 )
                {
                    byte[] newArray = new byte[decrypted.length - trim];
                    System.arraycopy(decrypted, 0, newArray, 0, decrypted.length - trim);
                    decrypted = newArray;
                }
            }
        } catch (Exception e)
        {
            throw new Exception("[decrypt] " + e.getMessage());
        }
        return decrypted;
    }



    public static String bytesToHex(byte[] data)
    {
        if (data==null)
        {
            return null;
        }

        int len = data.length;
        String str = "";
        for (int i=0; i<len; i++) {
            if ((data[i]&0xFF)<16)
                str = str + "0" + java.lang.Integer.toHexString(data[i]&0xFF);
            else
                str = str + java.lang.Integer.toHexString(data[i]&0xFF);
        }
        return str;
    }


    public static byte[] hexToBytes(String str) {
        if (str==null) {
            return null;
        } else if (str.length() < 2) {
            return null;
        } else {
            int len = str.length() / 2;
            byte[] buffer = new byte[len];
            for (int i=0; i<len; i++) {
                buffer[i] = (byte) Integer.parseInt(str.substring(i*2,i*2+2),16);
            }
            return buffer;
        }
    }



    private static byte[] padBytes(byte[] source){
        char paddingChar = ' ';
        int size = 16;
        int x = source.length % size;
        int padLength = size - x;
        int bufferLength = source.length + padLength;
        byte[] ret = new byte[bufferLength];
        int i = 0;
        for ( ; i < source.length; i++){
            ret[i] = source[i];
        }
        for ( ; i < bufferLength; i++){
            ret[i] = (byte)paddingChar;
        }

        return ret;
    }

}
