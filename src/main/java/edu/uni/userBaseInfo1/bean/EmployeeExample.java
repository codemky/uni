package edu.uni.userBaseInfo1.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EmployeeExample() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andEmpNoIsNull() {
            addCriterion("emp_no is null");
            return (Criteria) this;
        }

        public Criteria andEmpNoIsNotNull() {
            addCriterion("emp_no is not null");
            return (Criteria) this;
        }

        public Criteria andEmpNoEqualTo(String value) {
            addCriterion("emp_no =", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotEqualTo(String value) {
            addCriterion("emp_no <>", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoGreaterThan(String value) {
            addCriterion("emp_no >", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoGreaterThanOrEqualTo(String value) {
            addCriterion("emp_no >=", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoLessThan(String value) {
            addCriterion("emp_no <", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoLessThanOrEqualTo(String value) {
            addCriterion("emp_no <=", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoLike(String value) {
            addCriterion("emp_no like", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotLike(String value) {
            addCriterion("emp_no not like", value, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoIn(List<String> values) {
            addCriterion("emp_no in", values, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotIn(List<String> values) {
            addCriterion("emp_no not in", values, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoBetween(String value1, String value2) {
            addCriterion("emp_no between", value1, value2, "empNo");
            return (Criteria) this;
        }

        public Criteria andEmpNoNotBetween(String value1, String value2) {
            addCriterion("emp_no not between", value1, value2, "empNo");
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

        public Criteria andSubdepartmentIdIsNull() {
            addCriterion("subdepartment_id is null");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdIsNotNull() {
            addCriterion("subdepartment_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdEqualTo(Long value) {
            addCriterion("subdepartment_id =", value, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdNotEqualTo(Long value) {
            addCriterion("subdepartment_id <>", value, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdGreaterThan(Long value) {
            addCriterion("subdepartment_id >", value, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("subdepartment_id >=", value, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdLessThan(Long value) {
            addCriterion("subdepartment_id <", value, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdLessThanOrEqualTo(Long value) {
            addCriterion("subdepartment_id <=", value, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdIn(List<Long> values) {
            addCriterion("subdepartment_id in", values, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdNotIn(List<Long> values) {
            addCriterion("subdepartment_id not in", values, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdBetween(Long value1, Long value2) {
            addCriterion("subdepartment_id between", value1, value2, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andSubdepartmentIdNotBetween(Long value1, Long value2) {
            addCriterion("subdepartment_id not between", value1, value2, "subdepartmentId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdIsNull() {
            addCriterion("employ_history_id is null");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdIsNotNull() {
            addCriterion("employ_history_id is not null");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdEqualTo(Long value) {
            addCriterion("employ_history_id =", value, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdNotEqualTo(Long value) {
            addCriterion("employ_history_id <>", value, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdGreaterThan(Long value) {
            addCriterion("employ_history_id >", value, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("employ_history_id >=", value, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdLessThan(Long value) {
            addCriterion("employ_history_id <", value, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdLessThanOrEqualTo(Long value) {
            addCriterion("employ_history_id <=", value, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdIn(List<Long> values) {
            addCriterion("employ_history_id in", values, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdNotIn(List<Long> values) {
            addCriterion("employ_history_id not in", values, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdBetween(Long value1, Long value2) {
            addCriterion("employ_history_id between", value1, value2, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andEmployHistoryIdNotBetween(Long value1, Long value2) {
            addCriterion("employ_history_id not between", value1, value2, "employHistoryId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdIsNull() {
            addCriterion("discipline_id is null");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdIsNotNull() {
            addCriterion("discipline_id is not null");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdEqualTo(Long value) {
            addCriterion("discipline_id =", value, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdNotEqualTo(Long value) {
            addCriterion("discipline_id <>", value, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdGreaterThan(Long value) {
            addCriterion("discipline_id >", value, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdGreaterThanOrEqualTo(Long value) {
            addCriterion("discipline_id >=", value, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdLessThan(Long value) {
            addCriterion("discipline_id <", value, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdLessThanOrEqualTo(Long value) {
            addCriterion("discipline_id <=", value, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdIn(List<Long> values) {
            addCriterion("discipline_id in", values, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdNotIn(List<Long> values) {
            addCriterion("discipline_id not in", values, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdBetween(Long value1, Long value2) {
            addCriterion("discipline_id between", value1, value2, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andDisciplineIdNotBetween(Long value1, Long value2) {
            addCriterion("discipline_id not between", value1, value2, "disciplineId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdIsNull() {
            addCriterion("political_id is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdIsNotNull() {
            addCriterion("political_id is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdEqualTo(Long value) {
            addCriterion("political_id =", value, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdNotEqualTo(Long value) {
            addCriterion("political_id <>", value, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdGreaterThan(Long value) {
            addCriterion("political_id >", value, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdGreaterThanOrEqualTo(Long value) {
            addCriterion("political_id >=", value, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdLessThan(Long value) {
            addCriterion("political_id <", value, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdLessThanOrEqualTo(Long value) {
            addCriterion("political_id <=", value, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdIn(List<Long> values) {
            addCriterion("political_id in", values, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdNotIn(List<Long> values) {
            addCriterion("political_id not in", values, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdBetween(Long value1, Long value2) {
            addCriterion("political_id between", value1, value2, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPoliticalIdNotBetween(Long value1, Long value2) {
            addCriterion("political_id not between", value1, value2, "politicalId");
            return (Criteria) this;
        }

        public Criteria andPositionIdIsNull() {
            addCriterion("position_id is null");
            return (Criteria) this;
        }

        public Criteria andPositionIdIsNotNull() {
            addCriterion("position_id is not null");
            return (Criteria) this;
        }

        public Criteria andPositionIdEqualTo(Long value) {
            addCriterion("position_id =", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotEqualTo(Long value) {
            addCriterion("position_id <>", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThan(Long value) {
            addCriterion("position_id >", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("position_id >=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThan(Long value) {
            addCriterion("position_id <", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdLessThanOrEqualTo(Long value) {
            addCriterion("position_id <=", value, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdIn(List<Long> values) {
            addCriterion("position_id in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotIn(List<Long> values) {
            addCriterion("position_id not in", values, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdBetween(Long value1, Long value2) {
            addCriterion("position_id between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andPositionIdNotBetween(Long value1, Long value2) {
            addCriterion("position_id not between", value1, value2, "positionId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdIsNull() {
            addCriterion("home_address_id is null");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdIsNotNull() {
            addCriterion("home_address_id is not null");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdEqualTo(Long value) {
            addCriterion("home_address_id =", value, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdNotEqualTo(Long value) {
            addCriterion("home_address_id <>", value, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdGreaterThan(Long value) {
            addCriterion("home_address_id >", value, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("home_address_id >=", value, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdLessThan(Long value) {
            addCriterion("home_address_id <", value, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdLessThanOrEqualTo(Long value) {
            addCriterion("home_address_id <=", value, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdIn(List<Long> values) {
            addCriterion("home_address_id in", values, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdNotIn(List<Long> values) {
            addCriterion("home_address_id not in", values, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdBetween(Long value1, Long value2) {
            addCriterion("home_address_id between", value1, value2, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andHomeAddressIdNotBetween(Long value1, Long value2) {
            addCriterion("home_address_id not between", value1, value2, "homeAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdIsNull() {
            addCriterion("mail_address_id is null");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdIsNotNull() {
            addCriterion("mail_address_id is not null");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdEqualTo(Long value) {
            addCriterion("mail_address_id =", value, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdNotEqualTo(Long value) {
            addCriterion("mail_address_id <>", value, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdGreaterThan(Long value) {
            addCriterion("mail_address_id >", value, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdGreaterThanOrEqualTo(Long value) {
            addCriterion("mail_address_id >=", value, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdLessThan(Long value) {
            addCriterion("mail_address_id <", value, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdLessThanOrEqualTo(Long value) {
            addCriterion("mail_address_id <=", value, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdIn(List<Long> values) {
            addCriterion("mail_address_id in", values, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdNotIn(List<Long> values) {
            addCriterion("mail_address_id not in", values, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdBetween(Long value1, Long value2) {
            addCriterion("mail_address_id between", value1, value2, "mailAddressId");
            return (Criteria) this;
        }

        public Criteria andMailAddressIdNotBetween(Long value1, Long value2) {
            addCriterion("mail_address_id not between", value1, value2, "mailAddressId");
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