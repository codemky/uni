package edu.uni.professionalcourses.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SpecialtyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpecialtyExample() {
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

        public Criteria andDepartmentIdIsNull() {
            addCriterion("department_id is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIsNotNull() {
            addCriterion("department_id is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdEqualTo(Long value) {
            addCriterion("department_id =", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotEqualTo(Long value) {
            addCriterion("department_id <>", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThan(Long value) {
            addCriterion("department_id >", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("department_id >=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThan(Long value) {
            addCriterion("department_id <", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdLessThanOrEqualTo(Long value) {
            addCriterion("department_id <=", value, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdIn(List<Long> values) {
            addCriterion("department_id in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotIn(List<Long> values) {
            addCriterion("department_id not in", values, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdBetween(Long value1, Long value2) {
            addCriterion("department_id between", value1, value2, "departmentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentIdNotBetween(Long value1, Long value2) {
            addCriterion("department_id not between", value1, value2, "departmentId");
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

        public Criteria andDisciplineCategoryIdIsNull() {
            addCriterion("discipline_category_id is null");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdIsNotNull() {
            addCriterion("discipline_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdEqualTo(Long value) {
            addCriterion("discipline_category_id =", value, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdNotEqualTo(Long value) {
            addCriterion("discipline_category_id <>", value, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdGreaterThan(Long value) {
            addCriterion("discipline_category_id >", value, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("discipline_category_id >=", value, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdLessThan(Long value) {
            addCriterion("discipline_category_id <", value, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("discipline_category_id <=", value, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdIn(List<Long> values) {
            addCriterion("discipline_category_id in", values, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdNotIn(List<Long> values) {
            addCriterion("discipline_category_id not in", values, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdBetween(Long value1, Long value2) {
            addCriterion("discipline_category_id between", value1, value2, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("discipline_category_id not between", value1, value2, "disciplineCategoryId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdIsNull() {
            addCriterion("first_level_discipline_id is null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdIsNotNull() {
            addCriterion("first_level_discipline_id is not null");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdEqualTo(Long value) {
            addCriterion("first_level_discipline_id =", value, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdNotEqualTo(Long value) {
            addCriterion("first_level_discipline_id <>", value, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdGreaterThan(Long value) {
            addCriterion("first_level_discipline_id >", value, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdGreaterThanOrEqualTo(Long value) {
            addCriterion("first_level_discipline_id >=", value, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdLessThan(Long value) {
            addCriterion("first_level_discipline_id <", value, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdLessThanOrEqualTo(Long value) {
            addCriterion("first_level_discipline_id <=", value, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdIn(List<Long> values) {
            addCriterion("first_level_discipline_id in", values, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdNotIn(List<Long> values) {
            addCriterion("first_level_discipline_id not in", values, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdBetween(Long value1, Long value2) {
            addCriterion("first_level_discipline_id between", value1, value2, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andFirstLevelDisciplineIdNotBetween(Long value1, Long value2) {
            addCriterion("first_level_discipline_id not between", value1, value2, "firstLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdIsNull() {
            addCriterion("second_level_discipline_id is null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdIsNotNull() {
            addCriterion("second_level_discipline_id is not null");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdEqualTo(Long value) {
            addCriterion("second_level_discipline_id =", value, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdNotEqualTo(Long value) {
            addCriterion("second_level_discipline_id <>", value, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdGreaterThan(Long value) {
            addCriterion("second_level_discipline_id >", value, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdGreaterThanOrEqualTo(Long value) {
            addCriterion("second_level_discipline_id >=", value, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdLessThan(Long value) {
            addCriterion("second_level_discipline_id <", value, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdLessThanOrEqualTo(Long value) {
            addCriterion("second_level_discipline_id <=", value, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdIn(List<Long> values) {
            addCriterion("second_level_discipline_id in", values, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdNotIn(List<Long> values) {
            addCriterion("second_level_discipline_id not in", values, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdBetween(Long value1, Long value2) {
            addCriterion("second_level_discipline_id between", value1, value2, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andSecondLevelDisciplineIdNotBetween(Long value1, Long value2) {
            addCriterion("second_level_discipline_id not between", value1, value2, "secondLevelDisciplineId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdIsNull() {
            addCriterion("academic_id is null");
            return (Criteria) this;
        }

        public Criteria andAcademicIdIsNotNull() {
            addCriterion("academic_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcademicIdEqualTo(Long value) {
            addCriterion("academic_id =", value, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdNotEqualTo(Long value) {
            addCriterion("academic_id <>", value, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdGreaterThan(Long value) {
            addCriterion("academic_id >", value, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdGreaterThanOrEqualTo(Long value) {
            addCriterion("academic_id >=", value, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdLessThan(Long value) {
            addCriterion("academic_id <", value, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdLessThanOrEqualTo(Long value) {
            addCriterion("academic_id <=", value, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdIn(List<Long> values) {
            addCriterion("academic_id in", values, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdNotIn(List<Long> values) {
            addCriterion("academic_id not in", values, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdBetween(Long value1, Long value2) {
            addCriterion("academic_id between", value1, value2, "academicId");
            return (Criteria) this;
        }

        public Criteria andAcademicIdNotBetween(Long value1, Long value2) {
            addCriterion("academic_id not between", value1, value2, "academicId");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNull() {
            addCriterion("level_name is null");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNotNull() {
            addCriterion("level_name is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNameEqualTo(String value) {
            addCriterion("level_name =", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotEqualTo(String value) {
            addCriterion("level_name <>", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThan(String value) {
            addCriterion("level_name >", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("level_name >=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThan(String value) {
            addCriterion("level_name <", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThanOrEqualTo(String value) {
            addCriterion("level_name <=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLike(String value) {
            addCriterion("level_name like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotLike(String value) {
            addCriterion("level_name not like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameIn(List<String> values) {
            addCriterion("level_name in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotIn(List<String> values) {
            addCriterion("level_name not in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameBetween(String value1, String value2) {
            addCriterion("level_name between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotBetween(String value1, String value2) {
            addCriterion("level_name not between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthIsNull() {
            addCriterion("schooling_length is null");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthIsNotNull() {
            addCriterion("schooling_length is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthEqualTo(Integer value) {
            addCriterion("schooling_length =", value, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthNotEqualTo(Integer value) {
            addCriterion("schooling_length <>", value, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthGreaterThan(Integer value) {
            addCriterion("schooling_length >", value, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("schooling_length >=", value, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthLessThan(Integer value) {
            addCriterion("schooling_length <", value, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthLessThanOrEqualTo(Integer value) {
            addCriterion("schooling_length <=", value, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthIn(List<Integer> values) {
            addCriterion("schooling_length in", values, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthNotIn(List<Integer> values) {
            addCriterion("schooling_length not in", values, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthBetween(Integer value1, Integer value2) {
            addCriterion("schooling_length between", value1, value2, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andSchoolingLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("schooling_length not between", value1, value2, "schoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthIsNull() {
            addCriterion("min_schooling_length is null");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthIsNotNull() {
            addCriterion("min_schooling_length is not null");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthEqualTo(Integer value) {
            addCriterion("min_schooling_length =", value, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthNotEqualTo(Integer value) {
            addCriterion("min_schooling_length <>", value, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthGreaterThan(Integer value) {
            addCriterion("min_schooling_length >", value, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_schooling_length >=", value, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthLessThan(Integer value) {
            addCriterion("min_schooling_length <", value, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthLessThanOrEqualTo(Integer value) {
            addCriterion("min_schooling_length <=", value, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthIn(List<Integer> values) {
            addCriterion("min_schooling_length in", values, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthNotIn(List<Integer> values) {
            addCriterion("min_schooling_length not in", values, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthBetween(Integer value1, Integer value2) {
            addCriterion("min_schooling_length between", value1, value2, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMinSchoolingLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("min_schooling_length not between", value1, value2, "minSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthIsNull() {
            addCriterion("max_schooling_length is null");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthIsNotNull() {
            addCriterion("max_schooling_length is not null");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthEqualTo(Integer value) {
            addCriterion("max_schooling_length =", value, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthNotEqualTo(Integer value) {
            addCriterion("max_schooling_length <>", value, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthGreaterThan(Integer value) {
            addCriterion("max_schooling_length >", value, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_schooling_length >=", value, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthLessThan(Integer value) {
            addCriterion("max_schooling_length <", value, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthLessThanOrEqualTo(Integer value) {
            addCriterion("max_schooling_length <=", value, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthIn(List<Integer> values) {
            addCriterion("max_schooling_length in", values, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthNotIn(List<Integer> values) {
            addCriterion("max_schooling_length not in", values, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthBetween(Integer value1, Integer value2) {
            addCriterion("max_schooling_length between", value1, value2, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andMaxSchoolingLengthNotBetween(Integer value1, Integer value2) {
            addCriterion("max_schooling_length not between", value1, value2, "maxSchoolingLength");
            return (Criteria) this;
        }

        public Criteria andTotalCreditIsNull() {
            addCriterion("total_credit is null");
            return (Criteria) this;
        }

        public Criteria andTotalCreditIsNotNull() {
            addCriterion("total_credit is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCreditEqualTo(Integer value) {
            addCriterion("total_credit =", value, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditNotEqualTo(Integer value) {
            addCriterion("total_credit <>", value, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditGreaterThan(Integer value) {
            addCriterion("total_credit >", value, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_credit >=", value, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditLessThan(Integer value) {
            addCriterion("total_credit <", value, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditLessThanOrEqualTo(Integer value) {
            addCriterion("total_credit <=", value, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditIn(List<Integer> values) {
            addCriterion("total_credit in", values, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditNotIn(List<Integer> values) {
            addCriterion("total_credit not in", values, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditBetween(Integer value1, Integer value2) {
            addCriterion("total_credit between", value1, value2, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andTotalCreditNotBetween(Integer value1, Integer value2) {
            addCriterion("total_credit not between", value1, value2, "totalCredit");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
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