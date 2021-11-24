package com.ruoyi.wxcxc;

import com.ruoyi.wxcxc.util.CRCUtils;
import com.ruoyi.wxcxc.util.HexUtil;

import java.io.*;

public class Test {


    public static void main(String[] args) throws IOException {


        Float f = Float.intBitsToFloat(Integer.parseInt("BF2FB6B5", 16));
        System.out.println(f);

        byte[] bytes1 = hexToByte("030300000018");
        System.out.println(CRCUtils.getCRC(bytes1));

        byte[] b = HexUtil.hexToByte("0303000000184422");
        byte[] b1 = "0303000000184422".getBytes();

        System.out.println(HexUtil.byteToHex(b1));
        System.out.println(HexUtil.byteToHex(b));
        System.out.println(HexUtil.byteToHex(b));
//
////        byte b = (byte)Integer.parseInt("FF",16);
//        hexToByte("FF");
//        System.out.println(byteToHex(hexToByte("FF")));
////        System.out.println(Integer.toHexString(b & 0xFF));
//
//        File file = new File("C:\\temp\\test1.dat");
////        File file = new File("C:\\temp\\test1.dat");
//
//        FileInputStream fis = new FileInputStream(file);
//
//        byte[] b = new byte[21];
////        byte[] b = new byte[1];
//        int i = fis.read(b);
//
//        System.out.println("fe".equals(byteToHex(Arrays.copyOfRange(b, 0, 1))));
//
////        byte[] phoneByte = new byte[11];
////        System.arraycopy(b, 4, phoneByte, 0, 11);
////
////        System.out.println(byteToHex(Arrays.copyOfRange(b, 0, 1)));
////        System.out.println(new String(phoneByte, "gb2312"));
////        System.out.println(Integer.parseInt(byteToHex(Arrays.copyOfRange(b, 16, 17)),16));
////        System.out.println(Integer.parseInt(byteToHex(Arrays.copyOfRange(b, 17, 18)),16));
////        System.out.println(Integer.parseInt(byteToHex(Arrays.copyOfRange(b, 18, 19)),16));
////        System.out.println(Integer.parseInt(byteToHex(Arrays.copyOfRange(b, 19, 20)),16));
//
//        System.out.println("end");
//
////        FileOutputStream fos = new FileOutputStream(file);
////        fos.write(hexToByte("FF"));
////        fos.close();
    }

    public static String byteToHex(byte[] bytes) {
        String strHex = "";
        StringBuilder sb = new StringBuilder("");
        for (int n = 0; n < bytes.length; n++) {
            strHex = Integer.toHexString(bytes[n] & 0xFF);
            sb.append((strHex.length() == 1) ? "0" + strHex : strHex); // 每个字节由两个字符表示，位数不够，高位补0
        }
        return sb.toString().trim();
    }

    public static byte[] hexToByte(String hex) {
        int m = 0, n = 0;
        int byteLen = hex.length() / 2; // 每两个字符描述一个字节
        byte[] ret = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            int intVal = Integer.decode("0x" + hex.substring(i * 2, m) + hex.substring(m, n));
            ret[i] = Byte.valueOf((byte) intVal);
        }
        return ret;
    }
}
