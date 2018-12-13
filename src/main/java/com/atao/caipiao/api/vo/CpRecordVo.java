package com.atao.caipiao.api.vo;

import com.atao.base.vo.BaseVo;
import java.util.Date;

/**
 * 
 *
 * @author twang
 */
public class CpRecordVo extends BaseVo {

    /**
    * 
    */
    private Integer recoredId;

    /**
     * 
     */
    private Integer code;
    /**
     * 购买号码
     */
    private Integer buyNumber;
    /**
     * 
     */
    private Double buyMoney;
    /**
     * 
     */
    private Date buyTime;
    /**
     * 
     */
    private Double winMoney;
    /**
     * 开奖结果0:未开奖;1:赢;2:输
     */
    private Integer status;
    /**
     * 追号id
     */
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
        public static String recoredId = "recoredId";  // 
        public static String code = "code";  // 
        public static String buyNumber = "buyNumber";  // 购买号码
        public static String buyMoney = "buyMoney";  // 
        public static String buyTime = "buyTime";  // 
        public static String winMoney = "winMoney";  // 
        public static String status = "status";  // 开奖结果0:未开奖;1:赢;2:输
        public static String zhId = "zhId";  // 追号id

    }

}
