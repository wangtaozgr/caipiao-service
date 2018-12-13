package com.atao.caipiao.api.vo;

import com.atao.base.vo.BaseVo;

/**
 * 
 *
 * @author twang
 */
public class CpUserVo extends BaseVo {

    /**
    * 
    */
    private String username;

    /**
     * 
     */
    private Double money;
    /**
     * 
     */
    private String pwd;
    /**
     * 
     */
    private Boolean used;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public static class TF {
        public static String username = "username";  // 
        public static String money = "money";  // 
        public static String pwd = "pwd";  // 
        public static String used = "used";  // 

    }

}
