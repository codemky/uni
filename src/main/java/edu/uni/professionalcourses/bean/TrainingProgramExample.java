package edu.uni.professionalcourses.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingProgramExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TrainingProgramExample() {
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

        public Criteria andTrainingTargetIsNull() {
            addCriterion("training_target is null");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetIsNotNull() {
            addCriterion("training_target is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetEqualTo(String value) {
            addCriterion("training_target =", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetNotEqualTo(String value) {
            addCriterion("training_target <>", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetGreaterThan(String value) {
            addCriterion("training_target >", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetGreaterThanOrEqualTo(String value) {
            addCriterion("training_target >=", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetLessThan(String value) {
            addCriterion("training_target <", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetLessThanOrEqualTo(String value) {
            addCriterion("training_target <=", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetLike(String value) {
            addCriterion("training_target like", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetNotLike(String value) {
            addCriterion("training_target not like", value, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetIn(List<String> values) {
            addCriterion("training_target in", values, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetNotIn(List<String> values) {
            addCriterion("training_target not in", values, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetBetween(String value1, String value2) {
            addCriterion("training_target between", value1, value2, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingTargetNotBetween(String value1, String value2) {
            addCriterion("training_target not between", value1, value2, "trainingTarget");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsIsNull() {
            addCriterion("training_specifications is null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsIsNotNull() {
            addCriterion("training_specifications is not null");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsEqualTo(String value) {
            addCriterion("training_specifications =", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsNotEqualTo(String value) {
            addCriterion("training_specifications <>", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsGreaterThan(String value) {
            addCriterion("training_specifications >", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("training_specifications >=", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsLessThan(String value) {
            addCriterion("training_specifications <", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("training_specifications <=", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsLike(String value) {
            addCriterion("training_specifications like", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsNotLike(String value) {
            addCriterion("training_specifications not like", value, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsIn(List<String> values) {
            addCriterion("training_specifications in", values, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsNotIn(List<String> values) {
            addCriterion("training_specifications not in", values, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsBetween(String value1, String value2) {
            addCriterion("training_specifications between", value1, value2, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTrainingSpecificationsNotBetween(String value1, String value2) {
            addCriterion("training_specifications not between", value1, value2, "trainingSpecifications");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsIsNull() {
            addCriterion("total_credits is null");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsIsNotNull() {
            addCriterion("total_credits is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsEqualTo(Integer value) {
            addCriterion("total_credits =", value, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsNotEqualTo(Integer value) {
            addCriterion("total_credits <>", value, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsGreaterThan(Integer value) {
            addCriterion("total_credits >", value, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_credits >=", value, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsLessThan(Integer value) {
            addCriterion("total_credits <", value, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsLessThanOrEqualTo(Integer value) {
            addCriterion("total_credits <=", value, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsIn(List<Integer> values) {
            addCriterion("total_credits in", values, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsNotIn(List<Integer> values) {
            addCriterion("total_credits not in", values, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsBetween(Integer value1, Integer value2) {
            addCriterion("total_credits between", value1, value2, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andTotalCreditsNotBetween(Integer value1, Integer value2) {
            addCriterion("total_credits not between", value1, value2, "totalCredits");
            return (Criteria) this;
        }

        public Criteria andGpaIsNull() {
            addCriterion("gpa is null");
            return (Criteria) this;
        }

        public Criteria andGpaIsNotNull() {
            addCriterion("gpa is not null");
            return (Criteria) this;
        }

        public Criteria andGpaEqualTo(Double value) {
            addCriterion("gpa =", value, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaNotEqualTo(Double value) {
            addCriterion("gpa <>", value, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaGreaterThan(Double value) {
            addCriterion("gpa >", value, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaGreaterThanOrEqualTo(Double value) {
            addCriterion("gpa >=", value, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaLessThan(Double value) {
            addCriterion("gpa <", value, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaLessThanOrEqualTo(Double value) {
            addCriterion("gpa <=", value, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaIn(List<Double> values) {
            addCriterion("gpa in", values, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaNotIn(List<Double> values) {
            addCriterion("gpa not in", values, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaBetween(Double value1, Double value2) {
            addCriterion("gpa between", value1, value2, "gpa");
            return (Criteria) this;
        }

        public Criteria andGpaNotBetween(Double value1, Double value2) {
            addCriterion("gpa not between", value1, value2, "gpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsIsNull() {
            addCriterion("compilsory_credits is null");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsIsNotNull() {
            addCriterion("compilsory_credits is not null");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsEqualTo(Integer value) {
            addCriterion("compilsory_credits =", value, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsNotEqualTo(Integer value) {
            addCriterion("compilsory_credits <>", value, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsGreaterThan(Integer value) {
            addCriterion("compilsory_credits >", value, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsGreaterThanOrEqualTo(Integer value) {
            addCriterion("compilsory_credits >=", value, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsLessThan(Integer value) {
            addCriterion("compilsory_credits <", value, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsLessThanOrEqualTo(Integer value) {
            addCriterion("compilsory_credits <=", value, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsIn(List<Integer> values) {
            addCriterion("compilsory_credits in", values, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsNotIn(List<Integer> values) {
            addCriterion("compilsory_credits not in", values, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsBetween(Integer value1, Integer value2) {
            addCriterion("compilsory_credits between", value1, value2, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryCreditsNotBetween(Integer value1, Integer value2) {
            addCriterion("compilsory_credits not between", value1, value2, "compilsoryCredits");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaIsNull() {
            addCriterion("compilsory_gpa is null");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaIsNotNull() {
            addCriterion("compilsory_gpa is not null");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaEqualTo(Double value) {
            addCriterion("compilsory_gpa =", value, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaNotEqualTo(Double value) {
            addCriterion("compilsory_gpa <>", value, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaGreaterThan(Double value) {
            addCriterion("compilsory_gpa >", value, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaGreaterThanOrEqualTo(Double value) {
            addCriterion("compilsory_gpa >=", value, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaLessThan(Double value) {
            addCriterion("compilsory_gpa <", value, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaLessThanOrEqualTo(Double value) {
            addCriterion("compilsory_gpa <=", value, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaIn(List<Double> values) {
            addCriterion("compilsory_gpa in", values, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaNotIn(List<Double> values) {
            addCriterion("compilsory_gpa not in", values, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaBetween(Double value1, Double value2) {
            addCriterion("compilsory_gpa between", value1, value2, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andCompilsoryGpaNotBetween(Double value1, Double value2) {
            addCriterion("compilsory_gpa not between", value1, value2, "compilsoryGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsIsNull() {
            addCriterion("elective_credits is null");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsIsNotNull() {
            addCriterion("elective_credits is not null");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsEqualTo(Integer value) {
            addCriterion("elective_credits =", value, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsNotEqualTo(Integer value) {
            addCriterion("elective_credits <>", value, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsGreaterThan(Integer value) {
            addCriterion("elective_credits >", value, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsGreaterThanOrEqualTo(Integer value) {
            addCriterion("elective_credits >=", value, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsLessThan(Integer value) {
            addCriterion("elective_credits <", value, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsLessThanOrEqualTo(Integer value) {
            addCriterion("elective_credits <=", value, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsIn(List<Integer> values) {
            addCriterion("elective_credits in", values, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsNotIn(List<Integer> values) {
            addCriterion("elective_credits not in", values, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsBetween(Integer value1, Integer value2) {
            addCriterion("elective_credits between", value1, value2, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveCreditsNotBetween(Integer value1, Integer value2) {
            addCriterion("elective_credits not between", value1, value2, "electiveCredits");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaIsNull() {
            addCriterion("elective_gpa is null");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaIsNotNull() {
            addCriterion("elective_gpa is not null");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaEqualTo(Double value) {
            addCriterion("elective_gpa =", value, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaNotEqualTo(Double value) {
            addCriterion("elective_gpa <>", value, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaGreaterThan(Double value) {
            addCriterion("elective_gpa >", value, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaGreaterThanOrEqualTo(Double value) {
            addCriterion("elective_gpa >=", value, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaLessThan(Double value) {
            addCriterion("elective_gpa <", value, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaLessThanOrEqualTo(Double value) {
            addCriterion("elective_gpa <=", value, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaIn(List<Double> values) {
            addCriterion("elective_gpa in", values, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaNotIn(List<Double> values) {
            addCriterion("elective_gpa not in", values, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaBetween(Double value1, Double value2) {
            addCriterion("elective_gpa between", value1, value2, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andElectiveGpaNotBetween(Double value1, Double value2) {
            addCriterion("elective_gpa not between", value1, value2, "electiveGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsIsNull() {
            addCriterion("practice_credits is null");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsIsNotNull() {
            addCriterion("practice_credits is not null");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsEqualTo(Integer value) {
            addCriterion("practice_credits =", value, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsNotEqualTo(Integer value) {
            addCriterion("practice_credits <>", value, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsGreaterThan(Integer value) {
            addCriterion("practice_credits >", value, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsGreaterThanOrEqualTo(Integer value) {
            addCriterion("practice_credits >=", value, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsLessThan(Integer value) {
            addCriterion("practice_credits <", value, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsLessThanOrEqualTo(Integer value) {
            addCriterion("practice_credits <=", value, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsIn(List<Integer> values) {
            addCriterion("practice_credits in", values, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsNotIn(List<Integer> values) {
            addCriterion("practice_credits not in", values, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsBetween(Integer value1, Integer value2) {
            addCriterion("practice_credits between", value1, value2, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeCreditsNotBetween(Integer value1, Integer value2) {
            addCriterion("practice_credits not between", value1, value2, "practiceCredits");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaIsNull() {
            addCriterion("practice_gpa is null");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaIsNotNull() {
            addCriterion("practice_gpa is not null");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaEqualTo(Double value) {
            addCriterion("practice_gpa =", value, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaNotEqualTo(Double value) {
            addCriterion("practice_gpa <>", value, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaGreaterThan(Double value) {
            addCriterion("practice_gpa >", value, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaGreaterThanOrEqualTo(Double value) {
            addCriterion("practice_gpa >=", value, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaLessThan(Double value) {
            addCriterion("practice_gpa <", value, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaLessThanOrEqualTo(Double value) {
            addCriterion("practice_gpa <=", value, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaIn(List<Double> values) {
            addCriterion("practice_gpa in", values, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaNotIn(List<Double> values) {
            addCriterion("practice_gpa not in", values, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaBetween(Double value1, Double value2) {
            addCriterion("practice_gpa between", value1, value2, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andPracticeGpaNotBetween(Double value1, Double value2) {
            addCriterion("practice_gpa not between", value1, value2, "practiceGpa");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsIsNull() {
            addCriterion("educate_credits is null");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsIsNotNull() {
            addCriterion("educate_credits is not null");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsEqualTo(Integer value) {
            addCriterion("educate_credits =", value, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsNotEqualTo(Integer value) {
            addCriterion("educate_credits <>", value, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsGreaterThan(Integer value) {
            addCriterion("educate_credits >", value, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsGreaterThanOrEqualTo(Integer value) {
            addCriterion("educate_credits >=", value, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsLessThan(Integer value) {
            addCriterion("educate_credits <", value, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsLessThanOrEqualTo(Integer value) {
            addCriterion("educate_credits <=", value, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsIn(List<Integer> values) {
            addCriterion("educate_credits in", values, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsNotIn(List<Integer> values) {
            addCriterion("educate_credits not in", values, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsBetween(Integer value1, Integer value2) {
            addCriterion("educate_credits between", value1, value2, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateCreditsNotBetween(Integer value1, Integer value2) {
            addCriterion("educate_credits not between", value1, value2, "educateCredits");
            return (Criteria) this;
        }

        public Criteria andEducateGpaIsNull() {
            addCriterion("educate_gpa is null");
            return (Criteria) this;
        }

        public Criteria andEducateGpaIsNotNull() {
            addCriterion("educate_gpa is not null");
            return (Criteria) this;
        }

        public Criteria andEducateGpaEqualTo(Double value) {
            addCriterion("educate_gpa =", value, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaNotEqualTo(Double value) {
            addCriterion("educate_gpa <>", value, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaGreaterThan(Double value) {
            addCriterion("educate_gpa >", value, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaGreaterThanOrEqualTo(Double value) {
            addCriterion("educate_gpa >=", value, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaLessThan(Double value) {
            addCriterion("educate_gpa <", value, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaLessThanOrEqualTo(Double value) {
            addCriterion("educate_gpa <=", value, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaIn(List<Double> values) {
            addCriterion("educate_gpa in", values, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaNotIn(List<Double> values) {
            addCriterion("educate_gpa not in", values, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaBetween(Double value1, Double value2) {
            addCriterion("educate_gpa between", value1, value2, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andEducateGpaNotBetween(Double value1, Double value2) {
            addCriterion("educate_gpa not between", value1, value2, "educateGpa");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("version like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("version not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("version not between", value1, value2, "version");
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