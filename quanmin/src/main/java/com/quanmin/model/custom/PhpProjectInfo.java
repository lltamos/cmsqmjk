package com.quanmin.model.custom;

import java.util.List;

/**
 * Created by llsmp on 2017/7/19.
 */
public class PhpProjectInfo {

    /**
     * msg : 请求成功
     * resultCode : 200
     * success : true
     * value : [{"projectid":"1","name":"HRAⅠ型检测","price":"80000","memberprice":"68000","explain":"Ⅰ型/次","createtime":"2017-07-12 22:20:30","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"2","name":"HRAⅡ型检测","price":"50000","memberprice":"42500","explain":"Ⅱ型/次","createtime":"2017-07-12 22:21:24","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"3","name":"PMR全身治疗","price":"20000","memberprice":"17000","explain":"全身","createtime":"2017-07-12 22:22:15","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"4","name":"阿尔茨海默筛查","price":"20000","memberprice":"17000","explain":"筛查","createtime":"2017-07-12 22:22:52","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"5","name":"阿尔茨海默治疗","price":"16000","memberprice":"13600","explain":"治疗","createtime":"2017-07-12 22:23:36","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"6","name":"瑞能PS1000检测","price":"110000","memberprice":"93500","explain":"检测","createtime":"2017-07-12 22:24:15","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"7","name":"瑞能PS1000治疗","price":"20000","memberprice":"17000","explain":"治疗","createtime":"2017-07-12 22:24:50","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"8","name":"瑞能PS10治疗","price":"20000","memberprice":"17000","explain":"治疗","createtime":"2017-07-12 22:25:14","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"9","name":"百康过敏源筛查","price":"49800","memberprice":"42330","explain":"过敏源筛查","createtime":"2017-07-12 22:26:00","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"10","name":"百康脱敏/排毒治疗","price":"15000","memberprice":"12750","explain":"脱敏/排毒治疗","createtime":"2017-07-12 22:26:25","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"11","name":"威伐光局部治疗","price":"15000","memberprice":"12750","explain":"局部单次","createtime":"2017-07-12 22:27:11","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"12","name":"优曼治疗","price":"16000","memberprice":"13600","explain":"","createtime":"2017-07-12 22:27:47","effectivedate":"2017-01-01","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""},{"projectid":"41","name":"HRAⅠ型检测","price":"80000","memberprice":"68000","explain":"Ⅰ型/次","createtime":"2017-07-16 23:29:37","effectivedate":"2017-07-17","info":"","device":"","attendingFunction":"","ban":"","coverUrl":"","sort":""}]
     */

    private String msg;
    private String resultCode;
    private String success;
    /**
     * projectid : 1
     * name : HRAⅠ型检测
     * price : 80000
     * memberprice : 68000
     * explain : Ⅰ型/次
     * createtime : 2017-07-12 22:20:30
     * effectivedate : 2017-01-01
     * info :
     * device :
     * attendingFunction :
     * ban :
     * coverUrl :
     * sort :
     */

    private List<ValueBean> value;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ValueBean> getValue() {
        return value;
    }

    public void setValue(List<ValueBean> value) {
        this.value = value;
    }

    public static class ValueBean {
        private String projectid;
        private String name;
        private String price;
        private String memberprice;
        private String explain;
        private String createtime;
        private String effectivedate;
        private String info;
        private String device;
        private String attendingFunction;
        private String ban;
        private String coverUrl;
        private String sort;

        public String getProjectid() {
            return projectid;
        }

        public void setProjectid(String projectid) {
            this.projectid = projectid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMemberprice() {
            return memberprice;
        }

        public void setMemberprice(String memberprice) {
            this.memberprice = memberprice;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getEffectivedate() {
            return effectivedate;
        }

        public void setEffectivedate(String effectivedate) {
            this.effectivedate = effectivedate;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getAttendingFunction() {
            return attendingFunction;
        }

        public void setAttendingFunction(String attendingFunction) {
            this.attendingFunction = attendingFunction;
        }

        public String getBan() {
            return ban;
        }

        public void setBan(String ban) {
            this.ban = ban;
        }

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }
    }
}
