package com.quanmin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InformationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InformationExample() {
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andBodyTitleIsNull() {
            addCriterion("body_title is null");
            return (Criteria) this;
        }

        public Criteria andBodyTitleIsNotNull() {
            addCriterion("body_title is not null");
            return (Criteria) this;
        }

        public Criteria andBodyTitleEqualTo(String value) {
            addCriterion("body_title =", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleNotEqualTo(String value) {
            addCriterion("body_title <>", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleGreaterThan(String value) {
            addCriterion("body_title >", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleGreaterThanOrEqualTo(String value) {
            addCriterion("body_title >=", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleLessThan(String value) {
            addCriterion("body_title <", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleLessThanOrEqualTo(String value) {
            addCriterion("body_title <=", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleLike(String value) {
            addCriterion("body_title like", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleNotLike(String value) {
            addCriterion("body_title not like", value, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleIn(List<String> values) {
            addCriterion("body_title in", values, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleNotIn(List<String> values) {
            addCriterion("body_title not in", values, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleBetween(String value1, String value2) {
            addCriterion("body_title between", value1, value2, "bodyTitle");
            return (Criteria) this;
        }

        public Criteria andBodyTitleNotBetween(String value1, String value2) {
            addCriterion("body_title not between", value1, value2, "bodyTitle");
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

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andScannumIsNull() {
            addCriterion("scannum is null");
            return (Criteria) this;
        }

        public Criteria andScannumIsNotNull() {
            addCriterion("scannum is not null");
            return (Criteria) this;
        }

        public Criteria andScannumEqualTo(Integer value) {
            addCriterion("scannum =", value, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumNotEqualTo(Integer value) {
            addCriterion("scannum <>", value, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumGreaterThan(Integer value) {
            addCriterion("scannum >", value, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumGreaterThanOrEqualTo(Integer value) {
            addCriterion("scannum >=", value, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumLessThan(Integer value) {
            addCriterion("scannum <", value, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumLessThanOrEqualTo(Integer value) {
            addCriterion("scannum <=", value, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumIn(List<Integer> values) {
            addCriterion("scannum in", values, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumNotIn(List<Integer> values) {
            addCriterion("scannum not in", values, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumBetween(Integer value1, Integer value2) {
            addCriterion("scannum between", value1, value2, "scannum");
            return (Criteria) this;
        }

        public Criteria andScannumNotBetween(Integer value1, Integer value2) {
            addCriterion("scannum not between", value1, value2, "scannum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumIsNull() {
            addCriterion("collectionsum is null");
            return (Criteria) this;
        }

        public Criteria andCollectionsumIsNotNull() {
            addCriterion("collectionsum is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionsumEqualTo(Integer value) {
            addCriterion("collectionsum =", value, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumNotEqualTo(Integer value) {
            addCriterion("collectionsum <>", value, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumGreaterThan(Integer value) {
            addCriterion("collectionsum >", value, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collectionsum >=", value, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumLessThan(Integer value) {
            addCriterion("collectionsum <", value, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumLessThanOrEqualTo(Integer value) {
            addCriterion("collectionsum <=", value, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumIn(List<Integer> values) {
            addCriterion("collectionsum in", values, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumNotIn(List<Integer> values) {
            addCriterion("collectionsum not in", values, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumBetween(Integer value1, Integer value2) {
            addCriterion("collectionsum between", value1, value2, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCollectionsumNotBetween(Integer value1, Integer value2) {
            addCriterion("collectionsum not between", value1, value2, "collectionsum");
            return (Criteria) this;
        }

        public Criteria andCoverUrlIsNull() {
            addCriterion("cover_url is null");
            return (Criteria) this;
        }

        public Criteria andCoverUrlIsNotNull() {
            addCriterion("cover_url is not null");
            return (Criteria) this;
        }

        public Criteria andCoverUrlEqualTo(String value) {
            addCriterion("cover_url =", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlNotEqualTo(String value) {
            addCriterion("cover_url <>", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlGreaterThan(String value) {
            addCriterion("cover_url >", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlGreaterThanOrEqualTo(String value) {
            addCriterion("cover_url >=", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlLessThan(String value) {
            addCriterion("cover_url <", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlLessThanOrEqualTo(String value) {
            addCriterion("cover_url <=", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlLike(String value) {
            addCriterion("cover_url like", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlNotLike(String value) {
            addCriterion("cover_url not like", value, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlIn(List<String> values) {
            addCriterion("cover_url in", values, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlNotIn(List<String> values) {
            addCriterion("cover_url not in", values, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlBetween(String value1, String value2) {
            addCriterion("cover_url between", value1, value2, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andCoverUrlNotBetween(String value1, String value2) {
            addCriterion("cover_url not between", value1, value2, "coverUrl");
            return (Criteria) this;
        }

        public Criteria andPublishIsNull() {
            addCriterion("publish is null");
            return (Criteria) this;
        }

        public Criteria andPublishIsNotNull() {
            addCriterion("publish is not null");
            return (Criteria) this;
        }

        public Criteria andPublishEqualTo(Integer value) {
            addCriterion("publish =", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotEqualTo(Integer value) {
            addCriterion("publish <>", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishGreaterThan(Integer value) {
            addCriterion("publish >", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishGreaterThanOrEqualTo(Integer value) {
            addCriterion("publish >=", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishLessThan(Integer value) {
            addCriterion("publish <", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishLessThanOrEqualTo(Integer value) {
            addCriterion("publish <=", value, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishIn(List<Integer> values) {
            addCriterion("publish in", values, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotIn(List<Integer> values) {
            addCriterion("publish not in", values, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishBetween(Integer value1, Integer value2) {
            addCriterion("publish between", value1, value2, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishNotBetween(Integer value1, Integer value2) {
            addCriterion("publish not between", value1, value2, "publish");
            return (Criteria) this;
        }

        public Criteria andPublishUrlIsNull() {
            addCriterion("publish_url is null");
            return (Criteria) this;
        }

        public Criteria andPublishUrlIsNotNull() {
            addCriterion("publish_url is not null");
            return (Criteria) this;
        }

        public Criteria andPublishUrlEqualTo(String value) {
            addCriterion("publish_url =", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlNotEqualTo(String value) {
            addCriterion("publish_url <>", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlGreaterThan(String value) {
            addCriterion("publish_url >", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlGreaterThanOrEqualTo(String value) {
            addCriterion("publish_url >=", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlLessThan(String value) {
            addCriterion("publish_url <", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlLessThanOrEqualTo(String value) {
            addCriterion("publish_url <=", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlLike(String value) {
            addCriterion("publish_url like", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlNotLike(String value) {
            addCriterion("publish_url not like", value, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlIn(List<String> values) {
            addCriterion("publish_url in", values, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlNotIn(List<String> values) {
            addCriterion("publish_url not in", values, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlBetween(String value1, String value2) {
            addCriterion("publish_url between", value1, value2, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishUrlNotBetween(String value1, String value2) {
            addCriterion("publish_url not between", value1, value2, "publishUrl");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("publish_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(Date value) {
            addCriterion("publish_time =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(Date value) {
            addCriterion("publish_time <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(Date value) {
            addCriterion("publish_time >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("publish_time >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(Date value) {
            addCriterion("publish_time <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("publish_time <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<Date> values) {
            addCriterion("publish_time in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<Date> values) {
            addCriterion("publish_time not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(Date value1, Date value2) {
            addCriterion("publish_time between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("publish_time not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andDelStatusIsNull() {
            addCriterion("del_status is null");
            return (Criteria) this;
        }

        public Criteria andDelStatusIsNotNull() {
            addCriterion("del_status is not null");
            return (Criteria) this;
        }

        public Criteria andDelStatusEqualTo(Integer value) {
            addCriterion("del_status =", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusNotEqualTo(Integer value) {
            addCriterion("del_status <>", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusGreaterThan(Integer value) {
            addCriterion("del_status >", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("del_status >=", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusLessThan(Integer value) {
            addCriterion("del_status <", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusLessThanOrEqualTo(Integer value) {
            addCriterion("del_status <=", value, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusIn(List<Integer> values) {
            addCriterion("del_status in", values, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusNotIn(List<Integer> values) {
            addCriterion("del_status not in", values, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusBetween(Integer value1, Integer value2) {
            addCriterion("del_status between", value1, value2, "delStatus");
            return (Criteria) this;
        }

        public Criteria andDelStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("del_status not between", value1, value2, "delStatus");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeIsNull() {
            addCriterion("last_operate_time is null");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeIsNotNull() {
            addCriterion("last_operate_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeEqualTo(Date value) {
            addCriterion("last_operate_time =", value, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeNotEqualTo(Date value) {
            addCriterion("last_operate_time <>", value, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeGreaterThan(Date value) {
            addCriterion("last_operate_time >", value, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_operate_time >=", value, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeLessThan(Date value) {
            addCriterion("last_operate_time <", value, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_operate_time <=", value, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeIn(List<Date> values) {
            addCriterion("last_operate_time in", values, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeNotIn(List<Date> values) {
            addCriterion("last_operate_time not in", values, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeBetween(Date value1, Date value2) {
            addCriterion("last_operate_time between", value1, value2, "lastOperateTime");
            return (Criteria) this;
        }

        public Criteria andLastOperateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_operate_time not between", value1, value2, "lastOperateTime");
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