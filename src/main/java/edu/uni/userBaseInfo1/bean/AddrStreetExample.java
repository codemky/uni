package edu.uni.userBaseInfo1.bean;

import java.util.ArrayList;
import java.util.List;

public class AddrStreetExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AddrStreetExample() {
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

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNull() {
            addCriterion("area_code is null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIsNotNull() {
            addCriterion("area_code is not null");
            return (Criteria) this;
        }

        public Criteria andAreaCodeEqualTo(Long value) {
            addCriterion("area_code =", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotEqualTo(Long value) {
            addCriterion("area_code <>", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThan(Long value) {
            addCriterion("area_code >", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("area_code >=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThan(Long value) {
            addCriterion("area_code <", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeLessThanOrEqualTo(Long value) {
            addCriterion("area_code <=", value, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeIn(List<Long> values) {
            addCriterion("area_code in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotIn(List<Long> values) {
            addCriterion("area_code not in", values, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeBetween(Long value1, Long value2) {
            addCriterion("area_code between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andAreaCodeNotBetween(Long value1, Long value2) {
            addCriterion("area_code not between", value1, value2, "areaCode");
            return (Criteria) this;
        }

        public Criteria andStreetZhIsNull() {
            addCriterion("street_zh is null");
            return (Criteria) this;
        }

        public Criteria andStreetZhIsNotNull() {
            addCriterion("street_zh is not null");
            return (Criteria) this;
        }

        public Criteria andStreetZhEqualTo(String value) {
            addCriterion("street_zh =", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhNotEqualTo(String value) {
            addCriterion("street_zh <>", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhGreaterThan(String value) {
            addCriterion("street_zh >", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhGreaterThanOrEqualTo(String value) {
            addCriterion("street_zh >=", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhLessThan(String value) {
            addCriterion("street_zh <", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhLessThanOrEqualTo(String value) {
            addCriterion("street_zh <=", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhLike(String value) {
            addCriterion("street_zh like", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhNotLike(String value) {
            addCriterion("street_zh not like", value, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhIn(List<String> values) {
            addCriterion("street_zh in", values, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhNotIn(List<String> values) {
            addCriterion("street_zh not in", values, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhBetween(String value1, String value2) {
            addCriterion("street_zh between", value1, value2, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetZhNotBetween(String value1, String value2) {
            addCriterion("street_zh not between", value1, value2, "streetZh");
            return (Criteria) this;
        }

        public Criteria andStreetEnIsNull() {
            addCriterion("street_en is null");
            return (Criteria) this;
        }

        public Criteria andStreetEnIsNotNull() {
            addCriterion("street_en is not null");
            return (Criteria) this;
        }

        public Criteria andStreetEnEqualTo(String value) {
            addCriterion("street_en =", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnNotEqualTo(String value) {
            addCriterion("street_en <>", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnGreaterThan(String value) {
            addCriterion("street_en >", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnGreaterThanOrEqualTo(String value) {
            addCriterion("street_en >=", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnLessThan(String value) {
            addCriterion("street_en <", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnLessThanOrEqualTo(String value) {
            addCriterion("street_en <=", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnLike(String value) {
            addCriterion("street_en like", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnNotLike(String value) {
            addCriterion("street_en not like", value, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnIn(List<String> values) {
            addCriterion("street_en in", values, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnNotIn(List<String> values) {
            addCriterion("street_en not in", values, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnBetween(String value1, String value2) {
            addCriterion("street_en between", value1, value2, "streetEn");
            return (Criteria) this;
        }

        public Criteria andStreetEnNotBetween(String value1, String value2) {
            addCriterion("street_en not between", value1, value2, "streetEn");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(Long value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Long value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Long value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Long value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Long value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Long> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Long> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Long value1, Long value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Long value1, Long value2) {
            addCriterion("code not between", value1, value2, "code");
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