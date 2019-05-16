package edu.uni.userBaseInfo1.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentExample() {
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

        public Criteria andStuNoIsNull() {
            addCriterion("stu_no is null");
            return (Criteria) this;
        }

        public Criteria andStuNoIsNotNull() {
            addCriterion("stu_no is not null");
            return (Criteria) this;
        }

        public Criteria andStuNoEqualTo(String value) {
            addCriterion("stu_no =", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotEqualTo(String value) {
            addCriterion("stu_no <>", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThan(String value) {
            addCriterion("stu_no >", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoGreaterThanOrEqualTo(String value) {
            addCriterion("stu_no >=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThan(String value) {
            addCriterion("stu_no <", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLessThanOrEqualTo(String value) {
            addCriterion("stu_no <=", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoLike(String value) {
            addCriterion("stu_no like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotLike(String value) {
            addCriterion("stu_no not like", value, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoIn(List<String> values) {
            addCriterion("stu_no in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotIn(List<String> values) {
            addCriterion("stu_no not in", values, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoBetween(String value1, String value2) {
            addCriterion("stu_no between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andStuNoNotBetween(String value1, String value2) {
            addCriterion("stu_no not between", value1, value2, "stuNo");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateIsNull() {
            addCriterion("begin_learn_date is null");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateIsNotNull() {
            addCriterion("begin_learn_date is not null");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateEqualTo(Date value) {
            addCriterion("begin_learn_date =", value, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateNotEqualTo(Date value) {
            addCriterion("begin_learn_date <>", value, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateGreaterThan(Date value) {
            addCriterion("begin_learn_date >", value, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_learn_date >=", value, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateLessThan(Date value) {
            addCriterion("begin_learn_date <", value, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateLessThanOrEqualTo(Date value) {
            addCriterion("begin_learn_date <=", value, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateIn(List<Date> values) {
            addCriterion("begin_learn_date in", values, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateNotIn(List<Date> values) {
            addCriterion("begin_learn_date not in", values, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateBetween(Date value1, Date value2) {
            addCriterion("begin_learn_date between", value1, value2, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andBeginLearnDateNotBetween(Date value1, Date value2) {
            addCriterion("begin_learn_date not between", value1, value2, "beginLearnDate");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(String value) {
            addCriterion("grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(String value) {
            addCriterion("grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(String value) {
            addCriterion("grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(String value) {
            addCriterion("grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(String value) {
            addCriterion("grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(String value) {
            addCriterion("grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLike(String value) {
            addCriterion("grade like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotLike(String value) {
            addCriterion("grade not like", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<String> values) {
            addCriterion("grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<String> values) {
            addCriterion("grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(String value1, String value2) {
            addCriterion("grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(String value1, String value2) {
            addCriterion("grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdIsNull() {
            addCriterion("specialty_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdIsNotNull() {
            addCriterion("specialty_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdEqualTo(Long value) {
            addCriterion("specialty_id =", value, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdNotEqualTo(Long value) {
            addCriterion("specialty_id <>", value, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdGreaterThan(Long value) {
            addCriterion("specialty_id >", value, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("specialty_id >=", value, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdLessThan(Long value) {
            addCriterion("specialty_id <", value, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdLessThanOrEqualTo(Long value) {
            addCriterion("specialty_id <=", value, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdIn(List<Long> values) {
            addCriterion("specialty_id in", values, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdNotIn(List<Long> values) {
            addCriterion("specialty_id not in", values, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdBetween(Long value1, Long value2) {
            addCriterion("specialty_id between", value1, value2, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIdNotBetween(Long value1, Long value2) {
            addCriterion("specialty_id not between", value1, value2, "specialtyId");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(Long value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(Long value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(Long value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(Long value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(Long value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(Long value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<Long> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<Long> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(Long value1, Long value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(Long value1, Long value2) {
            addCriterion("class_id not between", value1, value2, "classId");
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

        public Criteria andLiveRoomIsNull() {
            addCriterion("live_room is null");
            return (Criteria) this;
        }

        public Criteria andLiveRoomIsNotNull() {
            addCriterion("live_room is not null");
            return (Criteria) this;
        }

        public Criteria andLiveRoomEqualTo(Long value) {
            addCriterion("live_room =", value, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomNotEqualTo(Long value) {
            addCriterion("live_room <>", value, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomGreaterThan(Long value) {
            addCriterion("live_room >", value, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomGreaterThanOrEqualTo(Long value) {
            addCriterion("live_room >=", value, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomLessThan(Long value) {
            addCriterion("live_room <", value, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomLessThanOrEqualTo(Long value) {
            addCriterion("live_room <=", value, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomIn(List<Long> values) {
            addCriterion("live_room in", values, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomNotIn(List<Long> values) {
            addCriterion("live_room not in", values, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomBetween(Long value1, Long value2) {
            addCriterion("live_room between", value1, value2, "liveRoom");
            return (Criteria) this;
        }

        public Criteria andLiveRoomNotBetween(Long value1, Long value2) {
            addCriterion("live_room not between", value1, value2, "liveRoom");
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

        public Criteria andPhoneEcommIdIsNull() {
            addCriterion("phone_ecomm_id is null");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdIsNotNull() {
            addCriterion("phone_ecomm_id is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdEqualTo(Long value) {
            addCriterion("phone_ecomm_id =", value, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdNotEqualTo(Long value) {
            addCriterion("phone_ecomm_id <>", value, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdGreaterThan(Long value) {
            addCriterion("phone_ecomm_id >", value, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdGreaterThanOrEqualTo(Long value) {
            addCriterion("phone_ecomm_id >=", value, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdLessThan(Long value) {
            addCriterion("phone_ecomm_id <", value, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdLessThanOrEqualTo(Long value) {
            addCriterion("phone_ecomm_id <=", value, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdIn(List<Long> values) {
            addCriterion("phone_ecomm_id in", values, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdNotIn(List<Long> values) {
            addCriterion("phone_ecomm_id not in", values, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdBetween(Long value1, Long value2) {
            addCriterion("phone_ecomm_id between", value1, value2, "phoneEcommId");
            return (Criteria) this;
        }

        public Criteria andPhoneEcommIdNotBetween(Long value1, Long value2) {
            addCriterion("phone_ecomm_id not between", value1, value2, "phoneEcommId");
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