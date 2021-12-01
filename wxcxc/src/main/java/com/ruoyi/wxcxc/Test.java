package com.ruoyi.wxcxc;

import com.ruoyi.wxcxc.util.CRCUtils;
import com.ruoyi.wxcxc.util.HexUtil;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Test {


    public static void main(String[] args) throws IOException {
        // 随机数列表
        Random random = new Random();

        int min = 150 / 12;
        int max = 430 / 12;

        double cj = 0.0;


        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 12; i++) {
                cj += ((random.nextInt(max) % (max - min + 1) + min) / 1000.0);
                DecimalFormat df = new DecimalFormat("#0.00");
                System.out.println(df.format(cj));
//            System.out.println(random.nextInt() * (max - min + 1) + min);
            }
        }

//        splitRedPacket(40000, 12, 300, 1500);


//        Float f = Float.intBitsToFloat(Integer.parseInt("BF2FB6B5", 16));
//        System.out.println(f);
//
//        byte[] bytes1 = hexToByte("030300000018");
//        System.out.println(CRCUtils.getCRC(bytes1));
//
//        byte[] b = HexUtil.hexToByte("0303000000184422");
//        byte[] b1 = "0303000000184422".getBytes();
//
//        System.out.println(HexUtil.byteToHex(b1));
//        System.out.println(HexUtil.byteToHex(b));
//        System.out.println(HexUtil.byteToHex(b));
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

    /**
     * @param total      总金额
     * @param splitCount 个数
     * @param min        最小金额
     * @param max        最大金额
     */
    public static void splitRedPacket(int total, int splitCount, int min, int max) {
        System.out.println("总金额：" + total);
        System.out.println("个数：" + splitCount);
        System.out.println("最小金额：" + min);
        System.out.println("最大金额：" + max);

        ArrayList<Integer> al = new ArrayList<Integer>();
        Random random = new Random();

        if ((splitCount & 1) == 1) {// 奇数个红包，需要单独将其中一个红包先生成，以保证后续算法拆分份数为偶数。
            System.out.println("红包个数" + splitCount + "是奇数，单独生成一个红包");
            int num = 0;
            do {
                num = random.nextInt(max);
// num = (total - num) % (splitCount / 2) + num; //
// 将后面算法拆分时的余数加入到这个随机值中。
                System.out.println("单个的随机红包为：" + num);
            } while (num >= max || num <= min);

            total = 40000 - num;
            al.add(num);
        }
        int couples = splitCount >> 1;
        int perCoupleSum = total / couples;

        if ((splitCount & 1) == 1) {
            System.out.println("处理后剩余的金额为：" + total);
        }
        System.out.println("将" + total + "元拆分为" + couples + "对金额，每对总额：" + perCoupleSum);

        for (int i = 0; i < couples; i++) {
            Boolean finish = true;
            int num1 = 0;
            int num2 = 0;
            do {
                num1 = random.nextInt(max);
                num2 = perCoupleSum - num1;
                if (!al.contains(num1) && !al.contains(num2)) {
                    if (i == 0) {
                        num1 = (total - couples * perCoupleSum) + num1;
                    }
                }
            } while (num1 < min || num1 > max || num2 < min || num2 > max);
            al.add(num1);
            al.add(num2);
        }

        int check_num = 0;
        Integer.compare(1, 2);
        al.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        System.out.println(Arrays.toString(al.toArray()));

        for (int x : al) {
            check_num = check_num + x;
        }
        System.out.println("验证总和：" + check_num);
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
