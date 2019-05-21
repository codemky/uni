package edu.uni.professionalcourses.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
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

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(String value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(String value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(String value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(String value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(String value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(String value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLike(String value) {
            addCriterion("number like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotLike(String value) {
            addCriterion("number not like", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<String> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<String> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(String value1, String value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(String value1, String value2) {
            addCriterion("number not between", value1, value2, "number");
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

        public Criteria andEnameIsNull() {
            addCriterion("ename is null");
            return (Criteria) this;
        }

        public Criteria andEnameIsNotNull() {
            addCriterion("ename is not null");
            return (Criteria) this;
        }

        public Criteria andEnameEqualTo(String value) {
            addCriterion("ename =", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotEqualTo(String value) {
            addCriterion("ename <>", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThan(String value) {
            addCriterion("ename >", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameGreaterThanOrEqualTo(String value) {
            addCriterion("ename >=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThan(String value) {
            addCriterion("ename <", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLessThanOrEqualTo(String value) {
            addCriterion("ename <=", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameLike(String value) {
            addCriterion("ename like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotLike(String value) {
            addCriterion("ename not like", value, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameIn(List<String> values) {
            addCriterion("ename in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotIn(List<String> values) {
            addCriterion("ename not in", values, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameBetween(String value1, String value2) {
            addCriterion("ename between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andEnameNotBetween(String value1, String value2) {
            addCriterion("ename not between", value1, value2, "ename");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdIsNull() {
            addCriterion("species_id is null");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdIsNotNull() {
            addCriterion("species_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdEqualTo(Long value) {
            addCriterion("species_id =", value, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdNotEqualTo(Long value) {
            addCriterion("species_id <>", value, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdGreaterThan(Long value) {
            addCriterion("species_id >", value, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdGreaterThanOrEqualTo(Long value) {
            addCriterion("species_id >=", value, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdLessThan(Long value) {
            addCriterion("species_id <", value, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdLessThanOrEqualTo(Long value) {
            addCriterion("species_id <=", value, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdIn(List<Long> values) {
            addCriterion("species_id in", values, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdNotIn(List<Long> values) {
            addCriterion("species_id not in", values, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdBetween(Long value1, Long value2) {
            addCriterion("species_id between", value1, value2, "speciesId");
            return (Criteria) this;
        }

        public Criteria andSpeciesIdNotBetween(Long value1, Long value2) {
            addCriterion("species_id not between", value1, value2, "speciesId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdIsNull() {
            addCriterion("classification_id is null");
            return (Criteria) this;
        }

        public Criteria andClassificationIdIsNotNull() {
            addCriterion("classification_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassificationIdEqualTo(Long value) {
            addCriterion("classification_id =", value, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdNotEqualTo(Long value) {
            addCriterion("classification_id <>", value, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdGreaterThan(Long value) {
            addCriterion("classification_id >", value, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdGreaterThanOrEqualTo(Long value) {
            addCriterion("classification_id >=", value, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdLessThan(Long value) {
            addCriterion("classification_id <", value, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdLessThanOrEqualTo(Long value) {
            addCriterion("classification_id <=", value, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdIn(List<Long> values) {
            addCriterion("classification_id in", values, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdNotIn(List<Long> values) {
            addCriterion("classification_id not in", values, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdBetween(Long value1, Long value2) {
            addCriterion("classification_id between", value1, value2, "classificationId");
            return (Criteria) this;
        }

        public Criteria andClassificationIdNotBetween(Long value1, Long value2) {
            addCriterion("classification_id not between", value1, value2, "classificationId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdIsNull() {
            addCriterion("exam_type_id is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdIsNotNull() {
            addCriterion("exam_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdEqualTo(Long value) {
            addCriterion("exam_type_id =", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdNotEqualTo(Long value) {
            addCriterion("exam_type_id <>", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdGreaterThan(Long value) {
            addCriterion("exam_type_id >", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exam_type_id >=", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdLessThan(Long value) {
            addCriterion("exam_type_id <", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdLessThanOrEqualTo(Long value) {
            addCriterion("exam_type_id <=", value, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdIn(List<Long> values) {
            addCriterion("exam_type_id in", values, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdNotIn(List<Long> values) {
            addCriterion("exam_type_id not in", values, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdBetween(Long value1, Long value2) {
            addCriterion("exam_type_id between", value1, value2, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIdNotBetween(Long value1, Long value2) {
            addCriterion("exam_type_id not between", value1, value2, "examTypeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdIsNull() {
            addCriterion("exam_mode_id is null");
            return (Criteria) this;
        }

        public Criteria andExamModeIdIsNotNull() {
            addCriterion("exam_mode_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamModeIdEqualTo(Long value) {
            addCriterion("exam_mode_id =", value, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdNotEqualTo(Long value) {
            addCriterion("exam_mode_id <>", value, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdGreaterThan(Long value) {
            addCriterion("exam_mode_id >", value, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdGreaterThanOrEqualTo(Long value) {
            addCriterion("exam_mode_id >=", value, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdLessThan(Long value) {
            addCriterion("exam_mode_id <", value, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdLessThanOrEqualTo(Long value) {
            addCriterion("exam_mode_id <=", value, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdIn(List<Long> values) {
            addCriterion("exam_mode_id in", values, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdNotIn(List<Long> values) {
            addCriterion("exam_mode_id not in", values, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdBetween(Long value1, Long value2) {
            addCriterion("exam_mode_id between", value1, value2, "examModeId");
            return (Criteria) this;
        }

        public Criteria andExamModeIdNotBetween(Long value1, Long value2) {
            addCriterion("exam_mode_id not between", value1, value2, "examModeId");
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

        public Criteria andCreditIsNull() {
            addCriterion("credit is null");
            return (Criteria) this;
        }

        public Criteria andCreditIsNotNull() {
            addCriterion("credit is not null");
            return (Criteria) this;
        }

        public Criteria andCreditEqualTo(Float value) {
            addCriterion("credit =", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotEqualTo(Float value) {
            addCriterion("credit <>", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThan(Float value) {
            addCriterion("credit >", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditGreaterThanOrEqualTo(Float value) {
            addCriterion("credit >=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThan(Float value) {
            addCriterion("credit <", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditLessThanOrEqualTo(Float value) {
            addCriterion("credit <=", value, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditIn(List<Float> values) {
            addCriterion("credit in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotIn(List<Float> values) {
            addCriterion("credit not in", values, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditBetween(Float value1, Float value2) {
            addCriterion("credit between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andCreditNotBetween(Float value1, Float value2) {
            addCriterion("credit not between", value1, value2, "credit");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourIsNull() {
            addCriterion("syllabus_hour is null");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourIsNotNull() {
            addCriterion("syllabus_hour is not null");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourEqualTo(Integer value) {
            addCriterion("syllabus_hour =", value, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourNotEqualTo(Integer value) {
            addCriterion("syllabus_hour <>", value, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourGreaterThan(Integer value) {
            addCriterion("syllabus_hour >", value, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("syllabus_hour >=", value, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourLessThan(Integer value) {
            addCriterion("syllabus_hour <", value, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourLessThanOrEqualTo(Integer value) {
            addCriterion("syllabus_hour <=", value, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourIn(List<Integer> values) {
            addCriterion("syllabus_hour in", values, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourNotIn(List<Integer> values) {
            addCriterion("syllabus_hour not in", values, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourBetween(Integer value1, Integer value2) {
            addCriterion("syllabus_hour between", value1, value2, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andSyllabusHourNotBetween(Integer value1, Integer value2) {
            addCriterion("syllabus_hour not between", value1, value2, "syllabusHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourIsNull() {
            addCriterion("experiment_hour is null");
            return (Criteria) this;
        }

        public Criteria andExperimentHourIsNotNull() {
            addCriterion("experiment_hour is not null");
            return (Criteria) this;
        }

        public Criteria andExperimentHourEqualTo(Integer value) {
            addCriterion("experiment_hour =", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourNotEqualTo(Integer value) {
            addCriterion("experiment_hour <>", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourGreaterThan(Integer value) {
            addCriterion("experiment_hour >", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("experiment_hour >=", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourLessThan(Integer value) {
            addCriterion("experiment_hour <", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourLessThanOrEqualTo(Integer value) {
            addCriterion("experiment_hour <=", value, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourIn(List<Integer> values) {
            addCriterion("experiment_hour in", values, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourNotIn(List<Integer> values) {
            addCriterion("experiment_hour not in", values, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourBetween(Integer value1, Integer value2) {
            addCriterion("experiment_hour between", value1, value2, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andExperimentHourNotBetween(Integer value1, Integer value2) {
            addCriterion("experiment_hour not between", value1, value2, "experimentHour");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNull() {
            addCriterion("introduction is null");
            return (Criteria) this;
        }

        public Criteria andIntroductionIsNotNull() {
            addCriterion("introduction is not null");
            return (Criteria) this;
        }

        public Criteria andIntroductionEqualTo(String value) {
            addCriterion("introduction =", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotEqualTo(String value) {
            addCriterion("introduction <>", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThan(String value) {
            addCriterion("introduction >", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionGreaterThanOrEqualTo(String value) {
            addCriterion("introduction >=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThan(String value) {
            addCriterion("introduction <", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLessThanOrEqualTo(String value) {
            addCriterion("introduction <=", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionLike(String value) {
            addCriterion("introduction like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotLike(String value) {
            addCriterion("introduction not like", value, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionIn(List<String> values) {
            addCriterion("introduction in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotIn(List<String> values) {
            addCriterion("introduction not in", values, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionBetween(String value1, String value2) {
            addCriterion("introduction between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andIntroductionNotBetween(String value1, String value2) {
            addCriterion("introduction not between", value1, value2, "introduction");
            return (Criteria) this;
        }

        public Criteria andTeachGoalIsNull() {
            addCriterion("teach_goal is null");
            return (Criteria) this;
        }

        public Criteria andTeachGoalIsNotNull() {
            addCriterion("teach_goal is not null");
            return (Criteria) this;
        }

        public Criteria andTeachGoalEqualTo(String value) {
            addCriterion("teach_goal =", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalNotEqualTo(String value) {
            addCriterion("teach_goal <>", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalGreaterThan(String value) {
            addCriterion("teach_goal >", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalGreaterThanOrEqualTo(String value) {
            addCriterion("teach_goal >=", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalLessThan(String value) {
            addCriterion("teach_goal <", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalLessThanOrEqualTo(String value) {
            addCriterion("teach_goal <=", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalLike(String value) {
            addCriterion("teach_goal like", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalNotLike(String value) {
            addCriterion("teach_goal not like", value, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalIn(List<String> values) {
            addCriterion("teach_goal in", values, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalNotIn(List<String> values) {
            addCriterion("teach_goal not in", values, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalBetween(String value1, String value2) {
            addCriterion("teach_goal between", value1, value2, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachGoalNotBetween(String value1, String value2) {
            addCriterion("teach_goal not between", value1, value2, "teachGoal");
            return (Criteria) this;
        }

        public Criteria andTeachRequireIsNull() {
            addCriterion("teach_require is null");
            return (Criteria) this;
        }

        public Criteria andTeachRequireIsNotNull() {
            addCriterion("teach_require is not null");
            return (Criteria) this;
        }

        public Criteria andTeachRequireEqualTo(String value) {
            addCriterion("teach_require =", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireNotEqualTo(String value) {
            addCriterion("teach_require <>", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireGreaterThan(String value) {
            addCriterion("teach_require >", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireGreaterThanOrEqualTo(String value) {
            addCriterion("teach_require >=", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireLessThan(String value) {
            addCriterion("teach_require <", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireLessThanOrEqualTo(String value) {
            addCriterion("teach_require <=", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireLike(String value) {
            addCriterion("teach_require like", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireNotLike(String value) {
            addCriterion("teach_require not like", value, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireIn(List<String> values) {
            addCriterion("teach_require in", values, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireNotIn(List<String> values) {
            addCriterion("teach_require not in", values, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireBetween(String value1, String value2) {
            addCriterion("teach_require between", value1, value2, "teachRequire");
            return (Criteria) this;
        }

        public Criteria andTeachRequireNotBetween(String value1, String value2) {
            addCriterion("teach_require not between", value1, value2, "teachRequire");
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