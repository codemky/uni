package edu.uni.professionalcourses.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExperimentSettingContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExperimentSettingContentExample() {
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

        public Criteria andUniversityIdIsNull() {
            addCriterion("university_id is null");
            return (Criteria) this;
        }

        public Criteria andUniversityIdIsNotNull() {
            addCriterion("university_id is not null");
            return (Criteria) this;
        }

        public Criteria andUniversityIdEqualTo(Long value) {
            addCriterion("university_id =", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotEqualTo(Long value) {
            addCriterion("university_id <>", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdGreaterThan(Long value) {
            addCriterion("university_id >", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("university_id >=", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdLessThan(Long value) {
            addCriterion("university_id <", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdLessThanOrEqualTo(Long value) {
            addCriterion("university_id <=", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdIn(List<Long> values) {
            addCriterion("university_id in", values, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotIn(List<Long> values) {
            addCriterion("university_id not in", values, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdBetween(Long value1, Long value2) {
            addCriterion("university_id between", value1, value2, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotBetween(Long value1, Long value2) {
            addCriterion("university_id not between", value1, value2, "universityId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdIsNull() {
            addCriterion("course_experiment_description_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdIsNotNull() {
            addCriterion("course_experiment_description_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdEqualTo(Long value) {
            addCriterion("course_experiment_description_id =", value, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdNotEqualTo(Long value) {
            addCriterion("course_experiment_description_id <>", value, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdGreaterThan(Long value) {
            addCriterion("course_experiment_description_id >", value, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_experiment_description_id >=", value, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdLessThan(Long value) {
            addCriterion("course_experiment_description_id <", value, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdLessThanOrEqualTo(Long value) {
            addCriterion("course_experiment_description_id <=", value, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdIn(List<Long> values) {
            addCriterion("course_experiment_description_id in", values, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdNotIn(List<Long> values) {
            addCriterion("course_experiment_description_id not in", values, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdBetween(Long value1, Long value2) {
            addCriterion("course_experiment_description_id between", value1, value2, "courseExperimentDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseExperimentDescriptionIdNotBetween(Long value1, Long value2) {
            addCriterion("course_experiment_description_id not between", value1, value2, "courseExperimentDescriptionId");
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andHourIsNull() {
            addCriterion("hour is null");
            return (Criteria) this;
        }

        public Criteria andHourIsNotNull() {
            addCriterion("hour is not null");
            return (Criteria) this;
        }

        public Criteria andHourEqualTo(Integer value) {
            addCriterion("hour =", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourNotEqualTo(Integer value) {
            addCriterion("hour <>", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourGreaterThan(Integer value) {
            addCriterion("hour >", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("hour >=", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourLessThan(Integer value) {
            addCriterion("hour <", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourLessThanOrEqualTo(Integer value) {
            addCriterion("hour <=", value, "hour");
            return (Criteria) this;
        }

        public Criteria andHourIn(List<Integer> values) {
            addCriterion("hour in", values, "hour");
            return (Criteria) this;
        }

        public Criteria andHourNotIn(List<Integer> values) {
            addCriterion("hour not in", values, "hour");
            return (Criteria) this;
        }

        public Criteria andHourBetween(Integer value1, Integer value2) {
            addCriterion("hour between", value1, value2, "hour");
            return (Criteria) this;
        }

        public Criteria andHourNotBetween(Integer value1, Integer value2) {
            addCriterion("hour not between", value1, value2, "hour");
            return (Criteria) this;
        }

        public Criteria andGroupNumberIsNull() {
            addCriterion("group_number is null");
            return (Criteria) this;
        }

        public Criteria andGroupNumberIsNotNull() {
            addCriterion("group_number is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNumberEqualTo(Integer value) {
            addCriterion("group_number =", value, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberNotEqualTo(Integer value) {
            addCriterion("group_number <>", value, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberGreaterThan(Integer value) {
            addCriterion("group_number >", value, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_number >=", value, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberLessThan(Integer value) {
            addCriterion("group_number <", value, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberLessThanOrEqualTo(Integer value) {
            addCriterion("group_number <=", value, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberIn(List<Integer> values) {
            addCriterion("group_number in", values, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberNotIn(List<Integer> values) {
            addCriterion("group_number not in", values, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberBetween(Integer value1, Integer value2) {
            addCriterion("group_number between", value1, value2, "groupNumber");
            return (Criteria) this;
        }

        public Criteria andGroupNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("group_number not between", value1, value2, "groupNumber");
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

        public Criteria andExperimentCategoryIsNull() {
            addCriterion("experiment_category is null");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryIsNotNull() {
            addCriterion("experiment_category is not null");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryEqualTo(String value) {
            addCriterion("experiment_category =", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryNotEqualTo(String value) {
            addCriterion("experiment_category <>", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryGreaterThan(String value) {
            addCriterion("experiment_category >", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("experiment_category >=", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryLessThan(String value) {
            addCriterion("experiment_category <", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryLessThanOrEqualTo(String value) {
            addCriterion("experiment_category <=", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryLike(String value) {
            addCriterion("experiment_category like", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryNotLike(String value) {
            addCriterion("experiment_category not like", value, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryIn(List<String> values) {
            addCriterion("experiment_category in", values, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryNotIn(List<String> values) {
            addCriterion("experiment_category not in", values, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryBetween(String value1, String value2) {
            addCriterion("experiment_category between", value1, value2, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andExperimentCategoryNotBetween(String value1, String value2) {
            addCriterion("experiment_category not between", value1, value2, "experimentCategory");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementIsNull() {
            addCriterion("establish_requirement is null");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementIsNotNull() {
            addCriterion("establish_requirement is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementEqualTo(String value) {
            addCriterion("establish_requirement =", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementNotEqualTo(String value) {
            addCriterion("establish_requirement <>", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementGreaterThan(String value) {
            addCriterion("establish_requirement >", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementGreaterThanOrEqualTo(String value) {
            addCriterion("establish_requirement >=", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementLessThan(String value) {
            addCriterion("establish_requirement <", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementLessThanOrEqualTo(String value) {
            addCriterion("establish_requirement <=", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementLike(String value) {
            addCriterion("establish_requirement like", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementNotLike(String value) {
            addCriterion("establish_requirement not like", value, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementIn(List<String> values) {
            addCriterion("establish_requirement in", values, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementNotIn(List<String> values) {
            addCriterion("establish_requirement not in", values, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementBetween(String value1, String value2) {
            addCriterion("establish_requirement between", value1, value2, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andEstablishRequirementNotBetween(String value1, String value2) {
            addCriterion("establish_requirement not between", value1, value2, "establishRequirement");
            return (Criteria) this;
        }

        public Criteria andDatetimeIsNull() {
            addCriterion("datetime is null");
            return (Criteria) this;
        }

        public Criteria andDatetimeIsNotNull() {
            addCriterion("datetime is not null");
            return (Criteria) this;
        }

        public Criteria andDatetimeEqualTo(Date value) {
            addCriterion("datetime =", value, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeNotEqualTo(Date value) {
            addCriterion("datetime <>", value, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeGreaterThan(Date value) {
            addCriterion("datetime >", value, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("datetime >=", value, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeLessThan(Date value) {
            addCriterion("datetime <", value, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeLessThanOrEqualTo(Date value) {
            addCriterion("datetime <=", value, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeIn(List<Date> values) {
            addCriterion("datetime in", values, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeNotIn(List<Date> values) {
            addCriterion("datetime not in", values, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeBetween(Date value1, Date value2) {
            addCriterion("datetime between", value1, value2, "datetime");
            return (Criteria) this;
        }

        public Criteria andDatetimeNotBetween(Date value1, Date value2) {
            addCriterion("datetime not between", value1, value2, "datetime");
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