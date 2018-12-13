package com.atao.caipiao.model;

import com.atao.base.model.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

/**
 * 
 *
 * @author twang
 */
public class CpRecord extends BaseEntity {

    /**
    * 
    */
    @Id
    @Column(name = "recored_id")
    private Integer recoredId;

    /**
     * 
     */
    @Column(name = "code")
    private Integer code;
    /**
     * 购买号码
     */
    @Column(name = "buy_number")
    private Integer buyNumber;
    /**
     * 
     */
    @Column(name = "buy_money")
    private Double buyMoney;
    /**
     * 
     */
    @Column(name = "buy_time")
    private Date buyTime;
    /**
     * 
     */
    @Column(name = "win_money")
    private Double winMoney;
    /**
     * 开奖结果0:未开奖;1:赢;2:输
     */
    @Column(name = "status")
    private Integer status;
    /**
     * 追号id
     */
    @Column(name = "zh_id")
    private Integer zhId;

    public Integer getRecoredId() {
        return recoredId;
    }

    public void setRecoredId(Integer recoredId) {
        this.recoredId = recoredId;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public Integer getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    public Double getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(Double buyMoney) {
        this.buyMoney = buyMoney;
    }
    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }
    public Double getWinMoney() {
        return winMoney;
    }

    public void setWinMoney(Double winMoney) {
        this.winMoney = winMoney;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getZhId() {
        return zhId;
    }

    public void setZhId(Integer zhId) {
        this.zhId = zhId;
    }

    public static class TF {

        public static String TABLE_NAME = "CP_RECORD";   // 表名

        //public static String TABLE_SCHEMA = ConfigUtils.getValue("");   // 库名

        public static String recoredId = "recored_id";  // 
        public static String code = "code";  // 
        public static String buyNumber = "buy_number";  // 购买号码
        public static String buyMoney = "buy_money";  // 
        public static String buyTime = "buy_time";  // 
        public static String winMoney = "win_money";  // 
        public static String status = "status";  // 开奖结果0:未开奖;1:赢;2:输
        public static String zhId = "zh_id";  // 追号id

    }
}
