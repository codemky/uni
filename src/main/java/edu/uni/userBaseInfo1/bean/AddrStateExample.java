package edu.uni.userBaseInfo1.bean;

import java.util.ArrayList;
import java.util.List;

public class AddrStateExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AddrStateExample() {
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

        public Criteria andCountryCodeIsNull() {
            addCriterion("country_code is null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNotNull() {
            addCriterion("country_code is not null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeEqualTo(Long value) {
            addCriterion("country_code =", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotEqualTo(Long value) {
            addCriterion("country_code <>", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThan(Long value) {
            addCriterion("country_code >", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("country_code >=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThan(Long value) {
            addCriterion("country_code <", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThanOrEqualTo(Long value) {
            addCriterion("country_code <=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIn(List<Long> values) {
            addCriterion("country_code in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotIn(List<Long> values) {
            addCriterion("country_code not in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeBetween(Long value1, Long value2) {
            addCriterion("country_code between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotBetween(Long value1, Long value2) {
            addCriterion("country_code not between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andStateZhIsNull() {
            addCriterion("state_zh is null");
            return (Criteria) this;
        }

        public Criteria andStateZhIsNotNull() {
            addCriterion("state_zh is not null");
            return (Criteria) this;
        }

        public Criteria andStateZhEqualTo(String value) {
            addCriterion("state_zh =", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhNotEqualTo(String value) {
            addCriterion("state_zh <>", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhGreaterThan(String value) {
            addCriterion("state_zh >", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhGreaterThanOrEqualTo(String value) {
            addCriterion("state_zh >=", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhLessThan(String value) {
            addCriterion("state_zh <", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhLessThanOrEqualTo(String value) {
            addCriterion("state_zh <=", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhLike(String value) {
            addCriterion("state_zh like", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhNotLike(String value) {
            addCriterion("state_zh not like", value, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhIn(List<String> values) {
            addCriterion("state_zh in", values, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhNotIn(List<String> values) {
            addCriterion("state_zh not in", values, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhBetween(String value1, String value2) {
            addCriterion("state_zh between", value1, value2, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateZhNotBetween(String value1, String value2) {
            addCriterion("state_zh not between", value1, value2, "stateZh");
            return (Criteria) this;
        }

        public Criteria andStateEnIsNull() {
            addCriterion("state_en is null");
            return (Criteria) this;
        }

        public Criteria andStateEnIsNotNull() {
            addCriterion("state_en is not null");
            return (Criteria) this;
        }

        public Criteria andStateEnEqualTo(String value) {
            addCriterion("state_en =", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnNotEqualTo(String value) {
            addCriterion("state_en <>", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnGreaterThan(String value) {
            addCriterion("state_en >", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnGreaterThanOrEqualTo(String value) {
            addCriterion("state_en >=", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnLessThan(String value) {
            addCriterion("state_en <", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnLessThanOrEqualTo(String value) {
            addCriterion("state_en <=", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnLike(String value) {
            addCriterion("state_en like", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnNotLike(String value) {
            addCriterion("state_en not like", value, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnIn(List<String> values) {
            addCriterion("state_en in", values, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnNotIn(List<String> values) {
            addCriterion("state_en not in", values, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnBetween(String value1, String value2) {
            addCriterion("state_en between", value1, value2, "stateEn");
            return (Criteria) this;
        }

        public Criteria andStateEnNotBetween(String value1, String value2) {
            addCriterion("state_en not between", value1, value2, "stateEn");
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