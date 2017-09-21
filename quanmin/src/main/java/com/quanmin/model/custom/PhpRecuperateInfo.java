package com.quanmin.model.custom;

import java.util.List;

/**
 * Created by llsmp on 2017/7/19.
 */
public class PhpRecuperateInfo {

    /**
     * msg : 请求成功
     * resultCode : 200
     * success : true
     * value : [{"recuperateid":"13","name":"糖尿病并发症管理","price":"150000","memberprice":"120000","explain":"PS10每周三次+百康每周2次+PMR每周三次","createtime":"2017-07-12 22:31:06","effectivedate":"2017-01-01","projectinfo":[{"pid":"13","projectid":"8","times":"1"},{"pid":"13","projectid":"9","times":"1"},{"pid":"13","projectid":"10","times":"1"},{"pid":"13","projectid":"3","times":"1"}],"info":"","coverUrl":""},{"recuperateid":"14","name":"淋巴水肿康复","price":"120000","memberprice":"96000","explain":"优曼每周五次+PS1000每周二次","createtime":"2017-07-12 22:34:54","effectivedate":"2017-01-01","projectinfo":[{"pid":"14","projectid":"12","times":"1"},{"pid":"14","projectid":"6","times":"1"},{"pid":"14","projectid":"7","times":"1"}],"info":"","coverUrl":""},{"recuperateid":"42","name":"发的","price":"21300","memberprice":"12300","explain":"水电费地方","createtime":"2017-07-17 11:07:05","effectivedate":"2017-07-25","projectinfo":[{"pid":"42","projectid":"2","times":"1"}],"info":"","coverUrl":""},{"recuperateid":"43","name":"测试方案","price":"88800","memberprice":"100000","explain":"多少分三个","createtime":"2017-07-19 09:39:43","effectivedate":"2017-07-20","projectinfo":[{"pid":"43","projectid":"8","times":"1"},{"pid":"43","projectid":"9","times":"1"},{"pid":"43","projectid":"10","times":"1"},{"pid":"43","projectid":"1","times":"1"},{"pid":"43","projectid":"2","times":"1"}],"info":"","coverUrl":""},{"recuperateid":"44","name":"测试方案","price":"100000","memberprice":"88800","explain":"地道佛道","createtime":"2017-07-19 10:04:03","effectivedate":"2017-07-20","projectinfo":[{"pid":"44","projectid":"8","times":"1"},{"pid":"44","projectid":"9","times":"1"},{"pid":"44","projectid":"10","times":"1"},{"pid":"44","projectid":"1","times":"1"},{"pid":"44","projectid":"2","times":"1"}],"info":"","coverUrl":""},{"recuperateid":"45","name":"这是梵蒂冈","price":"100000","memberprice":"88800","explain":"但是根深蒂固","createtime":"2017-07-19 10:05:00","effectivedate":"2017-07-20","projectinfo":[{"pid":"45","projectid":"9","times":"1"},{"pid":"45","projectid":"9","times":"1"},{"pid":"45","projectid":"9","times":"1"},{"pid":"45","projectid":"7","times":"1"}],"info":"","coverUrl":""},{"recuperateid":"46","name":"是容光焕发","price":"100000","memberprice":"80000","explain":"二维疯人院","createtime":"2017-07-19 10:29:48","effectivedate":"2017-07-20","projectinfo":[{"pid":"46","projectid":"8","times":"1"},{"pid":"46","projectid":"9","times":"1"},{"pid":"46","projectid":"7","times":"1"},{"pid":"46","projectid":"6","times":"1"}],"info":"","coverUrl":""}]
     */

    private String msg;
    private String resultCode;
    private String success;
    /**
     * recuperateid : 13
     * name : 糖尿病并发症管理
     * price : 150000
     * memberprice : 120000
     * explain : PS10每周三次+百康每周2次+PMR每周三次
     * createtime : 2017-07-12 22:31:06
     * effectivedate : 2017-01-01
     * projectinfo : [{"pid":"13","projectid":"8","times":"1"},{"pid":"13","projectid":"9","times":"1"},{"pid":"13","projectid":"10","times":"1"},{"pid":"13","projectid":"3","times":"1"}]
     * info :
     * coverUrl :
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
        private String recuperateid;
        private String name;
        private String price;
        private String memberprice;
        private String explain;
        private String createtime;
        private String effectivedate;
        private String info;
        private String coverUrl;
        /**
         * pid : 13
         * projectid : 8
         * times : 1
         */

        private List<ProjectinfoBean> projectinfo;

        public String getRecuperateid() {
            return recuperateid;
        }

        public void setRecuperateid(String recuperateid) {
            this.recuperateid = recuperateid;
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

        public String getCoverUrl() {
            return coverUrl;
        }

        public void setCoverUrl(String coverUrl) {
            this.coverUrl = coverUrl;
        }

        public List<ProjectinfoBean> getProjectinfo() {
            return projectinfo;
        }

        public void setProjectinfo(List<ProjectinfoBean> projectinfo) {
            this.projectinfo = projectinfo;
        }

        public static class ProjectinfoBean {
            private String pid;
            private String projectid;
            private String times;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getProjectid() {
                return projectid;
            }

            public void setProjectid(String projectid) {
                this.projectid = projectid;
            }

            public String getTimes() {
                return times;
            }

            public void setTimes(String times) {
                this.times = times;
            }
        }
    }
}
