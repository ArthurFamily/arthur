package com.cloud.arthur.util;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;

/**
 * 权限转换.2的权和
 * Created by chenzhen on 2017/7/25
 */
public class RightUtil {

    public static String getRights(String[] rights) {
        BigInteger right = new BigInteger("0");

        for (String r : rights) {
            right = right.setBit(Integer.parseInt(r));
        }

        return right.toString();
    }

/*    public static void main(String[] args) {
        System.out.println(getRights(new String[]{"1","2","14","16","3","4","5","6","7","8","9","10","11","12","13","15"}));
    }*/

    public static String getRights(Map<String, String> rights) {
        BigInteger right = new BigInteger("0");

        Iterator i =  rights.keySet().iterator();

        while(i.hasNext()) {
            right = right.setBit(Integer.parseInt(i.next().toString()));
        }
        return right.toString();
    }

    /**
     *
     * @param rights 权限和
     * @param checkRights 要验证的权限
     * @return
     */
    public static String checkRightsBy(String rights, String[] checkRights) {

        BigInteger right = new BigInteger(rights);
        StringBuilder yourRights = new StringBuilder();
        for (String r : checkRights) {
            if (right.testBit(Integer.parseInt(r))) {
                yourRights.append(",");
                yourRights.append(r);
            }
        }

        return yourRights.toString().replaceFirst(",", "");
    }

    /**
     *
     * @param rights 权限和
     * @param checkRights 要验证的权限
     * @return
     */
    public static String checkRightsBy(String rights, Map checkRights) {

        BigInteger right = new BigInteger(rights);
        StringBuilder yourRights = new StringBuilder();

        Iterator i = checkRights.keySet().iterator();
        while (i.hasNext()) {
            String r = i.next().toString();
            if (right.testBit(Integer.parseInt(r))) {
                yourRights.append(",");
                yourRights.append(r);
            }
        }

        return yourRights.toString().replaceFirst(",", "");
    }
}


