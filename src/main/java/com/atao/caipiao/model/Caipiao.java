package com.atao.caipiao.model;

import com.atao.base.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 
 *
 * @author twang
 */
public class Caipiao extends BaseEntity {

    /**
    * 
    */
    @Id
    @Column(name = "code")
    private Integer code;

    /**
     * 
     */
    @Column(name = "wan")
    private Integer wan;
    /**
     * 
     */
    @Column(name = "qian")
    private Integer qian;
    /**
     * 
     */
    @Column(name = "bai")
    private Integer bai;
    /**
     * 
     */
    @Column(name = "shi")
    private Integer shi;
    /**
     * 
     */
    @Column(name = "ge")
    private Integer ge;
    /**
     * 
     */
    @Column(name = "da")
    private Integer da;
    /**
     * 
     */
    @Column(name = "xiao")
    private Integer xiao;
    /**
     * 
     */
    @Column(name = "dan")
    private Integer dan;
    /**
     * 
     */
    @Column(name = "shuang")
    private Integer shuang;
    /**
     * 
     */
    @Column(name = "qishu")
    private Integer qishu;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public Integer getWan() {
        return wan;
    }

    public void setWan(Integer wan) {
        this.wan = wan;
    }
    public Integer getQian() {
        return qian;
    }

    public void setQian(Integer qian) {
        this.qian = qian;
    }
    public Integer getBai() {
        return bai;
    }

    public void setBai(Integer bai) {
        this.bai = bai;
    }
    public Integer getShi() {
        return shi;
    }

    public void setShi(Integer shi) {
        this.shi = shi;
    }
    public Integer getGe() {
        return ge;
    }

    public void setGe(Integer ge) {
        this.ge = ge;
    }
    public Integer getDa() {
        return da;
    }

    public void setDa(Integer da) {
        this.da = da;
    }
    public Integer getXiao() {
        return xiao;
    }

    public void setXiao(Integer xiao) {
        this.xiao = xiao;
    }
    public Integer getDan() {
        return dan;
    }

    public void setDan(Integer dan) {
        this.dan = dan;
    }
    public Integer getShuang() {
        return shuang;
    }

    public void setShuang(Integer shuang) {
        this.shuang = shuang;
    }
    public Integer getQishu() {
        return qishu;
    }

    public void setQishu(Integer qishu) {
        this.qishu = qishu;
    }

    public static class TF {

        public static String TABLE_NAME = "CAIPIAO";   // 表名

        //public static String TABLE_SCHEMA = ConfigUtils.getValue("");   // 库名

        public static String code = "code";  // 
        public static String wan = "wan";  // 
        public static String qian = "qian";  // 
        public static String bai = "bai";  // 
        public static String shi = "shi";  // 
        public static String ge = "ge";  // 
        public static String da = "da";  // 
        public static String xiao = "xiao";  // 
        public static String dan = "dan";  // 
        public static String shuang = "shuang";  // 
        public static String qishu = "qishu";  // 

    }
}
