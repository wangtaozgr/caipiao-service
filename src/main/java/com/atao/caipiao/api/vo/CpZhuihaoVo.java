package com.atao.caipiao.api.vo;

import com.atao.base.vo.BaseVo;
import java.util.Date;

/**
 * 
 *
 * @author twang
 */
public class CpZhuihaoVo extends BaseVo {

    /**
    * 
    */
    private Integer zhId;

    /**
     * 期数
     */
    private Integer code;
    /**
     * 位数
     */
    private Integer w;
    /**
     * 类型
     */
    private Integer type;
    /**
     * 
     */
    private Integer buynum;
    /**
     * 0:追号中;1:追号成功;2:追号失败
     */
    private Integer status;
    /**
     * 
     */
    private Date createTime;
    /**
     * 
     */
    private String content;

    public Integer getZhId() {
        return zhId;
    }

    public void setZhId(Integer zhId) {
        this.zhId = zhId;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getBuynum() {
        return buynum;
    }

    public void setBuynum(Integer buynum) {
        this.buynum = buynum;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static class TF {
        public static String zhId = "zhId";  // 
        public static String code = "code";  // 期数
        public static String w = "w";  // 位数
        public static String type = "type";  // 类型
        public static String buynum = "buynum";  // 
        public static String status = "status";  // 0:追号中;1:追号成功;2:追号失败
        public static String createTime = "createTime";  // 
        public static String content = "content";  // 

    }

}
