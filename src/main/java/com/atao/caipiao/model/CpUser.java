package com.atao.caipiao.model;

import com.atao.base.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 * 
 *
 * @author twang
 */
public class CpUser extends BaseEntity {

    /**
    * 
    */
    @Id
    @Column(name = "username")
    private String username;

    /**
     * 
     */
    @Column(name = "money")
    private Double money;
    /**
     * 
     */
    @Column(name = "pwd")
    private String pwd;
    /**
     * 
     */
    @Column(name = "used")
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

        public static String TABLE_NAME = "CP_USER";   // 表名

        //public static String TABLE_SCHEMA = ConfigUtils.getValue("");   // 库名

        public static String username = "username";  // 
        public static String money = "money";  // 
        public static String pwd = "pwd";  // 
        public static String used = "used";  // 

    }
}
