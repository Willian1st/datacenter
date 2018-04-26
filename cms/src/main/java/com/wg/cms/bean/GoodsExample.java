package com.wg.cms.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.wg.cms.util.PaginationJs;

public class GoodsExample extends PaginationJs{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GoodsExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceIsNull() {
            addCriterion("originalPrice is null");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceIsNotNull() {
            addCriterion("originalPrice is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceEqualTo(BigDecimal value) {
            addCriterion("originalPrice =", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceNotEqualTo(BigDecimal value) {
            addCriterion("originalPrice <>", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceGreaterThan(BigDecimal value) {
            addCriterion("originalPrice >", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("originalPrice >=", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceLessThan(BigDecimal value) {
            addCriterion("originalPrice <", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("originalPrice <=", value, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceIn(List<BigDecimal> values) {
            addCriterion("originalPrice in", values, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceNotIn(List<BigDecimal> values) {
            addCriterion("originalPrice not in", values, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("originalPrice between", value1, value2, "originalprice");
            return (Criteria) this;
        }

        public Criteria andOriginalpriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("originalPrice not between", value1, value2, "originalprice");
            return (Criteria) this;
        }

        public Criteria andCouponIsNull() {
            addCriterion("coupon is null");
            return (Criteria) this;
        }

        public Criteria andCouponIsNotNull() {
            addCriterion("coupon is not null");
            return (Criteria) this;
        }

        public Criteria andCouponEqualTo(BigDecimal value) {
            addCriterion("coupon =", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponNotEqualTo(BigDecimal value) {
            addCriterion("coupon <>", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponGreaterThan(BigDecimal value) {
            addCriterion("coupon >", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon >=", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponLessThan(BigDecimal value) {
            addCriterion("coupon <", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon <=", value, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponIn(List<BigDecimal> values) {
            addCriterion("coupon in", values, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponNotIn(List<BigDecimal> values) {
            addCriterion("coupon not in", values, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon between", value1, value2, "coupon");
            return (Criteria) this;
        }

        public Criteria andCouponNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon not between", value1, value2, "coupon");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeIsNull() {
            addCriterion("discountStartTime is null");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeIsNotNull() {
            addCriterion("discountStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeEqualTo(String value) {
            addCriterion("discountStartTime =", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeNotEqualTo(String value) {
            addCriterion("discountStartTime <>", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeGreaterThan(String value) {
            addCriterion("discountStartTime >", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeGreaterThanOrEqualTo(String value) {
            addCriterion("discountStartTime >=", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeLessThan(String value) {
            addCriterion("discountStartTime <", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeLessThanOrEqualTo(String value) {
            addCriterion("discountStartTime <=", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeLike(String value) {
            addCriterion("discountStartTime like", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeNotLike(String value) {
            addCriterion("discountStartTime not like", value, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeIn(List<String> values) {
            addCriterion("discountStartTime in", values, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeNotIn(List<String> values) {
            addCriterion("discountStartTime not in", values, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeBetween(String value1, String value2) {
            addCriterion("discountStartTime between", value1, value2, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountstarttimeNotBetween(String value1, String value2) {
            addCriterion("discountStartTime not between", value1, value2, "discountstarttime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeIsNull() {
            addCriterion("discountEndTime is null");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeIsNotNull() {
            addCriterion("discountEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeEqualTo(String value) {
            addCriterion("discountEndTime =", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeNotEqualTo(String value) {
            addCriterion("discountEndTime <>", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeGreaterThan(String value) {
            addCriterion("discountEndTime >", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeGreaterThanOrEqualTo(String value) {
            addCriterion("discountEndTime >=", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeLessThan(String value) {
            addCriterion("discountEndTime <", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeLessThanOrEqualTo(String value) {
            addCriterion("discountEndTime <=", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeLike(String value) {
            addCriterion("discountEndTime like", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeNotLike(String value) {
            addCriterion("discountEndTime not like", value, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeIn(List<String> values) {
            addCriterion("discountEndTime in", values, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeNotIn(List<String> values) {
            addCriterion("discountEndTime not in", values, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeBetween(String value1, String value2) {
            addCriterion("discountEndTime between", value1, value2, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andDiscountendtimeNotBetween(String value1, String value2) {
            addCriterion("discountEndTime not between", value1, value2, "discountendtime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSalenumIsNull() {
            addCriterion("saleNum is null");
            return (Criteria) this;
        }

        public Criteria andSalenumIsNotNull() {
            addCriterion("saleNum is not null");
            return (Criteria) this;
        }

        public Criteria andSalenumEqualTo(BigDecimal value) {
            addCriterion("saleNum =", value, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumNotEqualTo(BigDecimal value) {
            addCriterion("saleNum <>", value, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumGreaterThan(BigDecimal value) {
            addCriterion("saleNum >", value, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("saleNum >=", value, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumLessThan(BigDecimal value) {
            addCriterion("saleNum <", value, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("saleNum <=", value, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumIn(List<BigDecimal> values) {
            addCriterion("saleNum in", values, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumNotIn(List<BigDecimal> values) {
            addCriterion("saleNum not in", values, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("saleNum between", value1, value2, "salenum");
            return (Criteria) this;
        }

        public Criteria andSalenumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("saleNum not between", value1, value2, "salenum");
            return (Criteria) this;
        }

        public Criteria andGetnumIsNull() {
            addCriterion("getNum is null");
            return (Criteria) this;
        }

        public Criteria andGetnumIsNotNull() {
            addCriterion("getNum is not null");
            return (Criteria) this;
        }

        public Criteria andGetnumEqualTo(BigDecimal value) {
            addCriterion("getNum =", value, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumNotEqualTo(BigDecimal value) {
            addCriterion("getNum <>", value, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumGreaterThan(BigDecimal value) {
            addCriterion("getNum >", value, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("getNum >=", value, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumLessThan(BigDecimal value) {
            addCriterion("getNum <", value, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("getNum <=", value, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumIn(List<BigDecimal> values) {
            addCriterion("getNum in", values, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumNotIn(List<BigDecimal> values) {
            addCriterion("getNum not in", values, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("getNum between", value1, value2, "getnum");
            return (Criteria) this;
        }

        public Criteria andGetnumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("getNum not between", value1, value2, "getnum");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("flag like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("flag not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andSjgxsjIsNull() {
            addCriterion("sjgxsj is null");
            return (Criteria) this;
        }

        public Criteria andSjgxsjIsNotNull() {
            addCriterion("sjgxsj is not null");
            return (Criteria) this;
        }

        public Criteria andSjgxsjEqualTo(String value) {
            addCriterion("sjgxsj =", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjNotEqualTo(String value) {
            addCriterion("sjgxsj <>", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjGreaterThan(String value) {
            addCriterion("sjgxsj >", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjGreaterThanOrEqualTo(String value) {
            addCriterion("sjgxsj >=", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjLessThan(String value) {
            addCriterion("sjgxsj <", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjLessThanOrEqualTo(String value) {
            addCriterion("sjgxsj <=", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjLike(String value) {
            addCriterion("sjgxsj like", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjNotLike(String value) {
            addCriterion("sjgxsj not like", value, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjIn(List<String> values) {
            addCriterion("sjgxsj in", values, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjNotIn(List<String> values) {
            addCriterion("sjgxsj not in", values, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjBetween(String value1, String value2) {
            addCriterion("sjgxsj between", value1, value2, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxsjNotBetween(String value1, String value2) {
            addCriterion("sjgxsj not between", value1, value2, "sjgxsj");
            return (Criteria) this;
        }

        public Criteria andSjgxrIsNull() {
            addCriterion("sjgxr is null");
            return (Criteria) this;
        }

        public Criteria andSjgxrIsNotNull() {
            addCriterion("sjgxr is not null");
            return (Criteria) this;
        }

        public Criteria andSjgxrEqualTo(Integer value) {
            addCriterion("sjgxr =", value, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrNotEqualTo(Integer value) {
            addCriterion("sjgxr <>", value, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrGreaterThan(Integer value) {
            addCriterion("sjgxr >", value, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrGreaterThanOrEqualTo(Integer value) {
            addCriterion("sjgxr >=", value, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrLessThan(Integer value) {
            addCriterion("sjgxr <", value, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrLessThanOrEqualTo(Integer value) {
            addCriterion("sjgxr <=", value, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrIn(List<Integer> values) {
            addCriterion("sjgxr in", values, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrNotIn(List<Integer> values) {
            addCriterion("sjgxr not in", values, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrBetween(Integer value1, Integer value2) {
            addCriterion("sjgxr between", value1, value2, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjgxrNotBetween(Integer value1, Integer value2) {
            addCriterion("sjgxr not between", value1, value2, "sjgxr");
            return (Criteria) this;
        }

        public Criteria andSjcjsjIsNull() {
            addCriterion("sjcjsj is null");
            return (Criteria) this;
        }

        public Criteria andSjcjsjIsNotNull() {
            addCriterion("sjcjsj is not null");
            return (Criteria) this;
        }

        public Criteria andSjcjsjEqualTo(String value) {
            addCriterion("sjcjsj =", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjNotEqualTo(String value) {
            addCriterion("sjcjsj <>", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjGreaterThan(String value) {
            addCriterion("sjcjsj >", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjGreaterThanOrEqualTo(String value) {
            addCriterion("sjcjsj >=", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjLessThan(String value) {
            addCriterion("sjcjsj <", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjLessThanOrEqualTo(String value) {
            addCriterion("sjcjsj <=", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjLike(String value) {
            addCriterion("sjcjsj like", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjNotLike(String value) {
            addCriterion("sjcjsj not like", value, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjIn(List<String> values) {
            addCriterion("sjcjsj in", values, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjNotIn(List<String> values) {
            addCriterion("sjcjsj not in", values, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjBetween(String value1, String value2) {
            addCriterion("sjcjsj between", value1, value2, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjsjNotBetween(String value1, String value2) {
            addCriterion("sjcjsj not between", value1, value2, "sjcjsj");
            return (Criteria) this;
        }

        public Criteria andSjcjrIsNull() {
            addCriterion("sjcjr is null");
            return (Criteria) this;
        }

        public Criteria andSjcjrIsNotNull() {
            addCriterion("sjcjr is not null");
            return (Criteria) this;
        }

        public Criteria andSjcjrEqualTo(Integer value) {
            addCriterion("sjcjr =", value, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrNotEqualTo(Integer value) {
            addCriterion("sjcjr <>", value, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrGreaterThan(Integer value) {
            addCriterion("sjcjr >", value, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrGreaterThanOrEqualTo(Integer value) {
            addCriterion("sjcjr >=", value, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrLessThan(Integer value) {
            addCriterion("sjcjr <", value, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrLessThanOrEqualTo(Integer value) {
            addCriterion("sjcjr <=", value, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrIn(List<Integer> values) {
            addCriterion("sjcjr in", values, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrNotIn(List<Integer> values) {
            addCriterion("sjcjr not in", values, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrBetween(Integer value1, Integer value2) {
            addCriterion("sjcjr between", value1, value2, "sjcjr");
            return (Criteria) this;
        }

        public Criteria andSjcjrNotBetween(Integer value1, Integer value2) {
            addCriterion("sjcjr not between", value1, value2, "sjcjr");
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