package com.quanmin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserHraReportExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserHraReportExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdIsNull() {
            addCriterion("hra_user_id is null");
            return (Criteria) this;
        }

        public Criteria andHraUserIdIsNotNull() {
            addCriterion("hra_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andHraUserIdEqualTo(Integer value) {
            addCriterion("hra_user_id =", value, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdNotEqualTo(Integer value) {
            addCriterion("hra_user_id <>", value, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdGreaterThan(Integer value) {
            addCriterion("hra_user_id >", value, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hra_user_id >=", value, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdLessThan(Integer value) {
            addCriterion("hra_user_id <", value, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("hra_user_id <=", value, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdIn(List<Integer> values) {
            addCriterion("hra_user_id in", values, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdNotIn(List<Integer> values) {
            addCriterion("hra_user_id not in", values, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdBetween(Integer value1, Integer value2) {
            addCriterion("hra_user_id between", value1, value2, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hra_user_id not between", value1, value2, "hraUserId");
            return (Criteria) this;
        }

        public Criteria andHraIdIsNull() {
            addCriterion("hra_id is null");
            return (Criteria) this;
        }

        public Criteria andHraIdIsNotNull() {
            addCriterion("hra_id is not null");
            return (Criteria) this;
        }

        public Criteria andHraIdEqualTo(Integer value) {
            addCriterion("hra_id =", value, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdNotEqualTo(Integer value) {
            addCriterion("hra_id <>", value, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdGreaterThan(Integer value) {
            addCriterion("hra_id >", value, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("hra_id >=", value, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdLessThan(Integer value) {
            addCriterion("hra_id <", value, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdLessThanOrEqualTo(Integer value) {
            addCriterion("hra_id <=", value, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdIn(List<Integer> values) {
            addCriterion("hra_id in", values, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdNotIn(List<Integer> values) {
            addCriterion("hra_id not in", values, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdBetween(Integer value1, Integer value2) {
            addCriterion("hra_id between", value1, value2, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraIdNotBetween(Integer value1, Integer value2) {
            addCriterion("hra_id not between", value1, value2, "hraId");
            return (Criteria) this;
        }

        public Criteria andHraUserNameIsNull() {
            addCriterion("hra_user_name is null");
            return (Criteria) this;
        }

        public Criteria andHraUserNameIsNotNull() {
            addCriterion("hra_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andHraUserNameEqualTo(String value) {
            addCriterion("hra_user_name =", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameNotEqualTo(String value) {
            addCriterion("hra_user_name <>", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameGreaterThan(String value) {
            addCriterion("hra_user_name >", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("hra_user_name >=", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameLessThan(String value) {
            addCriterion("hra_user_name <", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameLessThanOrEqualTo(String value) {
            addCriterion("hra_user_name <=", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameLike(String value) {
            addCriterion("hra_user_name like", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameNotLike(String value) {
            addCriterion("hra_user_name not like", value, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameIn(List<String> values) {
            addCriterion("hra_user_name in", values, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameNotIn(List<String> values) {
            addCriterion("hra_user_name not in", values, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameBetween(String value1, String value2) {
            addCriterion("hra_user_name between", value1, value2, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraUserNameNotBetween(String value1, String value2) {
            addCriterion("hra_user_name not between", value1, value2, "hraUserName");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeIsNull() {
            addCriterion("hra_report_time is null");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeIsNotNull() {
            addCriterion("hra_report_time is not null");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeEqualTo(String value) {
            addCriterion("hra_report_time =", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeNotEqualTo(String value) {
            addCriterion("hra_report_time <>", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeGreaterThan(String value) {
            addCriterion("hra_report_time >", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeGreaterThanOrEqualTo(String value) {
            addCriterion("hra_report_time >=", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeLessThan(String value) {
            addCriterion("hra_report_time <", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeLessThanOrEqualTo(String value) {
            addCriterion("hra_report_time <=", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeLike(String value) {
            addCriterion("hra_report_time like", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeNotLike(String value) {
            addCriterion("hra_report_time not like", value, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeIn(List<String> values) {
            addCriterion("hra_report_time in", values, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeNotIn(List<String> values) {
            addCriterion("hra_report_time not in", values, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeBetween(String value1, String value2) {
            addCriterion("hra_report_time between", value1, value2, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportTimeNotBetween(String value1, String value2) {
            addCriterion("hra_report_time not between", value1, value2, "hraReportTime");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlIsNull() {
            addCriterion("hra_report_url is null");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlIsNotNull() {
            addCriterion("hra_report_url is not null");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlEqualTo(String value) {
            addCriterion("hra_report_url =", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlNotEqualTo(String value) {
            addCriterion("hra_report_url <>", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlGreaterThan(String value) {
            addCriterion("hra_report_url >", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlGreaterThanOrEqualTo(String value) {
            addCriterion("hra_report_url >=", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlLessThan(String value) {
            addCriterion("hra_report_url <", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlLessThanOrEqualTo(String value) {
            addCriterion("hra_report_url <=", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlLike(String value) {
            addCriterion("hra_report_url like", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlNotLike(String value) {
            addCriterion("hra_report_url not like", value, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlIn(List<String> values) {
            addCriterion("hra_report_url in", values, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlNotIn(List<String> values) {
            addCriterion("hra_report_url not in", values, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlBetween(String value1, String value2) {
            addCriterion("hra_report_url between", value1, value2, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andHraReportUrlNotBetween(String value1, String value2) {
            addCriterion("hra_report_url not between", value1, value2, "hraReportUrl");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andExt1IsNull() {
            addCriterion("ext1 is null");
            return (Criteria) this;
        }

        public Criteria andExt1IsNotNull() {
            addCriterion("ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andExt1EqualTo(String value) {
            addCriterion("ext1 =", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotEqualTo(String value) {
            addCriterion("ext1 <>", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThan(String value) {
            addCriterion("ext1 >", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("ext1 >=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThan(String value) {
            addCriterion("ext1 <", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("ext1 <=", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Like(String value) {
            addCriterion("ext1 like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotLike(String value) {
            addCriterion("ext1 not like", value, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1In(List<String> values) {
            addCriterion("ext1 in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotIn(List<String> values) {
            addCriterion("ext1 not in", values, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1Between(String value1, String value2) {
            addCriterion("ext1 between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt1NotBetween(String value1, String value2) {
            addCriterion("ext1 not between", value1, value2, "ext1");
            return (Criteria) this;
        }

        public Criteria andExt2IsNull() {
            addCriterion("ext2 is null");
            return (Criteria) this;
        }

        public Criteria andExt2IsNotNull() {
            addCriterion("ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andExt2EqualTo(String value) {
            addCriterion("ext2 =", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotEqualTo(String value) {
            addCriterion("ext2 <>", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThan(String value) {
            addCriterion("ext2 >", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("ext2 >=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThan(String value) {
            addCriterion("ext2 <", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("ext2 <=", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Like(String value) {
            addCriterion("ext2 like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotLike(String value) {
            addCriterion("ext2 not like", value, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2In(List<String> values) {
            addCriterion("ext2 in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotIn(List<String> values) {
            addCriterion("ext2 not in", values, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2Between(String value1, String value2) {
            addCriterion("ext2 between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt2NotBetween(String value1, String value2) {
            addCriterion("ext2 not between", value1, value2, "ext2");
            return (Criteria) this;
        }

        public Criteria andExt3IsNull() {
            addCriterion("ext3 is null");
            return (Criteria) this;
        }

        public Criteria andExt3IsNotNull() {
            addCriterion("ext3 is not null");
            return (Criteria) this;
        }

        public Criteria andExt3EqualTo(String value) {
            addCriterion("ext3 =", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotEqualTo(String value) {
            addCriterion("ext3 <>", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThan(String value) {
            addCriterion("ext3 >", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("ext3 >=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThan(String value) {
            addCriterion("ext3 <", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("ext3 <=", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Like(String value) {
            addCriterion("ext3 like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotLike(String value) {
            addCriterion("ext3 not like", value, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3In(List<String> values) {
            addCriterion("ext3 in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotIn(List<String> values) {
            addCriterion("ext3 not in", values, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3Between(String value1, String value2) {
            addCriterion("ext3 between", value1, value2, "ext3");
            return (Criteria) this;
        }

        public Criteria andExt3NotBetween(String value1, String value2) {
            addCriterion("ext3 not between", value1, value2, "ext3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}