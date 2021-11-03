package com.ruoyi.wxcxc.netty;


import io.netty.buffer.ByteBuf;

import java.util.Date;
import java.util.List;

//  解析接口
public interface ParseData {
    //解析
    boolean parse(byte[] bytes);

    //解码
    boolean decoder(ByteBuf in, List<Object> list);

    //保存
    boolean save();

    //保存文本
    public boolean save2Txt();

    //设置项目信息
//    void setDrcProInfo(DrcProInfo drcProInfo);

    //获取项目信息
//    DrcProInfo getDrcProInfo();

    //是否是心跳包
    boolean isHeartPack();

    //获取最后心跳时间
    Date getLastHeartPackDate();

}
