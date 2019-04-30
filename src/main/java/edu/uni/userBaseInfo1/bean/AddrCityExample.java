package edu.uni.userBaseInfo1.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AddrCityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AddrCityExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andStateIdIsNull() {
            addCriterion("state_id is null");
            return (Criteria) this;
        }

        public Criteria andStateIdIsNotNull() {
            addCriterion("state_id is not null");
            return (Criteria) this;
        }

        public Criteria andStateIdEqualTo(Long value) {
            addCriterion("state_id =", value, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdNotEqualTo(Long value) {
            addCriterion("state_id <>", value, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdGreaterThan(Long value) {
            addCriterion("state_id >", value, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("state_id >=", value, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdLessThan(Long value) {
            addCriterion("state_id <", value, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdLessThanOrEqualTo(Long value) {
            addCriterion("state_id <=", value, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdIn(List<Long> values) {
            addCriterion("state_id in", values, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdNotIn(List<Long> values) {
            addCriterion("state_id not in", values, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdBetween(Long value1, Long value2) {
            addCriterion("state_id between", value1, value2, "stateId");
            return (Criteria) this;
        }

        public Criteria andStateIdNotBetween(Long value1, Long value2) {
            addCriterion("state_id not between", value1, value2, "stateId");
            return (Criteria) this;
        }

        public Criteria andCityZhIsNull() {
            addCriterion("city_zh is null");
            return (Criteria) this;
        }

        public Criteria andCityZhIsNotNull() {
            addCriterion("city_zh is not null");
            return (Criteria) this;
        }

        public Criteria andCityZhEqualTo(String value) {
            addCriterion("city_zh =", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhNotEqualTo(String value) {
            addCriterion("city_zh <>", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhGreaterThan(String value) {
            addCriterion("city_zh >", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhGreaterThanOrEqualTo(String value) {
            addCriterion("city_zh >=", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhLessThan(String value) {
            addCriterion("city_zh <", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhLessThanOrEqualTo(String value) {
            addCriterion("city_zh <=", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhLike(String value) {
            addCriterion("city_zh like", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhNotLike(String value) {
            addCriterion("city_zh not like", value, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhIn(List<String> values) {
            addCriterion("city_zh in", values, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhNotIn(List<String> values) {
            addCriterion("city_zh not in", values, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhBetween(String value1, String value2) {
            addCriterion("city_zh between", value1, value2, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityZhNotBetween(String value1, String value2) {
            addCriterion("city_zh not between", value1, value2, "cityZh");
            return (Criteria) this;
        }

        public Criteria andCityEnIsNull() {
            addCriterion("city_en is null");
            return (Criteria) this;
        }

        public Criteria andCityEnIsNotNull() {
            addCriterion("city_en is not null");
            return (Criteria) this;
        }

        public Criteria andCityEnEqualTo(String value) {
            addCriterion("city_en =", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnNotEqualTo(String value) {
            addCriterion("city_en <>", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnGreaterThan(String value) {
            addCriterion("city_en >", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnGreaterThanOrEqualTo(String value) {
            addCriterion("city_en >=", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnLessThan(String value) {
            addCriterion("city_en <", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnLessThanOrEqualTo(String value) {
            addCriterion("city_en <=", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnLike(String value) {
            addCriterion("city_en like", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnNotLike(String value) {
            addCriterion("city_en not like", value, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnIn(List<String> values) {
            addCriterion("city_en in", values, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnNotIn(List<String> values) {
            addCriterion("city_en not in", values, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnBetween(String value1, String value2) {
            addCriterion("city_en between", value1, value2, "cityEn");
            return (Criteria) this;
        }

        public Criteria andCityEnNotBetween(String value1, String value2) {
            addCriterion("city_en not between", value1, value2, "cityEn");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andByWhoIsNull() {
            addCriterion("by_who is null");
            return (Criteria) this;
        }

        public Criteria andByWhoIsNotNull() {
            addCriterion("by_who is not null");
            return (Criteria) this;
        }

        public Criteria andByWhoEqualTo(Long value) {
            addCriterion("by_who =", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoNotEqualTo(Long value) {
            addCriterion("by_who <>", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoGreaterThan(Long value) {
            addCriterion("by_who >", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoGreaterThanOrEqualTo(Long value) {
            addCriterion("by_who >=", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoLessThan(Long value) {
            addCriterion("by_who <", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoLessThanOrEqualTo(Long value) {
            addCriterion("by_who <=", value, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoIn(List<Long> values) {
            addCriterion("by_who in", values, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoNotIn(List<Long> values) {
            addCriterion("by_who not in", values, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoBetween(Long value1, Long value2) {
            addCriterion("by_who between", value1, value2, "byWho");
            return (Criteria) this;
        }

        public Criteria andByWhoNotBetween(Long value1, Long value2) {
            addCriterion("by_who not between", value1, value2, "byWho");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Boolean value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Boolean value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Boolean value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Boolean value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Boolean value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Boolean> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Boolean> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
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