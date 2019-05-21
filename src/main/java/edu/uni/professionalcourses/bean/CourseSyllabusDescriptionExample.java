package edu.uni.professionalcourses.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseSyllabusDescriptionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseSyllabusDescriptionExample() {
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

        public Criteria andCourseSyllabusIdIsNull() {
            addCriterion("course_syllabus_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdIsNotNull() {
            addCriterion("course_syllabus_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdEqualTo(Long value) {
            addCriterion("course_syllabus_id =", value, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdNotEqualTo(Long value) {
            addCriterion("course_syllabus_id <>", value, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdGreaterThan(Long value) {
            addCriterion("course_syllabus_id >", value, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_syllabus_id >=", value, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdLessThan(Long value) {
            addCriterion("course_syllabus_id <", value, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdLessThanOrEqualTo(Long value) {
            addCriterion("course_syllabus_id <=", value, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdIn(List<Long> values) {
            addCriterion("course_syllabus_id in", values, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdNotIn(List<Long> values) {
            addCriterion("course_syllabus_id not in", values, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdBetween(Long value1, Long value2) {
            addCriterion("course_syllabus_id between", value1, value2, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusIdNotBetween(Long value1, Long value2) {
            addCriterion("course_syllabus_id not between", value1, value2, "courseSyllabusId");
            return (Criteria) this;
        }

        public Criteria andChapterIsNull() {
            addCriterion("chapter is null");
            return (Criteria) this;
        }

        public Criteria andChapterIsNotNull() {
            addCriterion("chapter is not null");
            return (Criteria) this;
        }

        public Criteria andChapterEqualTo(String value) {
            addCriterion("chapter =", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterNotEqualTo(String value) {
            addCriterion("chapter <>", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterGreaterThan(String value) {
            addCriterion("chapter >", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterGreaterThanOrEqualTo(String value) {
            addCriterion("chapter >=", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterLessThan(String value) {
            addCriterion("chapter <", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterLessThanOrEqualTo(String value) {
            addCriterion("chapter <=", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterLike(String value) {
            addCriterion("chapter like", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterNotLike(String value) {
            addCriterion("chapter not like", value, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterIn(List<String> values) {
            addCriterion("chapter in", values, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterNotIn(List<String> values) {
            addCriterion("chapter not in", values, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterBetween(String value1, String value2) {
            addCriterion("chapter between", value1, value2, "chapter");
            return (Criteria) this;
        }

        public Criteria andChapterNotBetween(String value1, String value2) {
            addCriterion("chapter not between", value1, value2, "chapter");
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

        public Criteria andTeachingHourIsNull() {
            addCriterion("teaching_hour is null");
            return (Criteria) this;
        }

        public Criteria andTeachingHourIsNotNull() {
            addCriterion("teaching_hour is not null");
            return (Criteria) this;
        }

        public Criteria andTeachingHourEqualTo(Integer value) {
            addCriterion("teaching_hour =", value, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourNotEqualTo(Integer value) {
            addCriterion("teaching_hour <>", value, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourGreaterThan(Integer value) {
            addCriterion("teaching_hour >", value, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("teaching_hour >=", value, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourLessThan(Integer value) {
            addCriterion("teaching_hour <", value, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourLessThanOrEqualTo(Integer value) {
            addCriterion("teaching_hour <=", value, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourIn(List<Integer> values) {
            addCriterion("teaching_hour in", values, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourNotIn(List<Integer> values) {
            addCriterion("teaching_hour not in", values, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourBetween(Integer value1, Integer value2) {
            addCriterion("teaching_hour between", value1, value2, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andTeachingHourNotBetween(Integer value1, Integer value2) {
            addCriterion("teaching_hour not between", value1, value2, "teachingHour");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementIsNull() {
            addCriterion("assessment_requirement is null");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementIsNotNull() {
            addCriterion("assessment_requirement is not null");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementEqualTo(String value) {
            addCriterion("assessment_requirement =", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementNotEqualTo(String value) {
            addCriterion("assessment_requirement <>", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementGreaterThan(String value) {
            addCriterion("assessment_requirement >", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementGreaterThanOrEqualTo(String value) {
            addCriterion("assessment_requirement >=", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementLessThan(String value) {
            addCriterion("assessment_requirement <", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementLessThanOrEqualTo(String value) {
            addCriterion("assessment_requirement <=", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementLike(String value) {
            addCriterion("assessment_requirement like", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementNotLike(String value) {
            addCriterion("assessment_requirement not like", value, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementIn(List<String> values) {
            addCriterion("assessment_requirement in", values, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementNotIn(List<String> values) {
            addCriterion("assessment_requirement not in", values, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementBetween(String value1, String value2) {
            addCriterion("assessment_requirement between", value1, value2, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andAssessmentRequirementNotBetween(String value1, String value2) {
            addCriterion("assessment_requirement not between", value1, value2, "assessmentRequirement");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdIsNull() {
            addCriterion("course_book_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdIsNotNull() {
            addCriterion("course_book_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdEqualTo(Long value) {
            addCriterion("course_book_id =", value, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdNotEqualTo(Long value) {
            addCriterion("course_book_id <>", value, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdGreaterThan(Long value) {
            addCriterion("course_book_id >", value, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_book_id >=", value, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdLessThan(Long value) {
            addCriterion("course_book_id <", value, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdLessThanOrEqualTo(Long value) {
            addCriterion("course_book_id <=", value, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdIn(List<Long> values) {
            addCriterion("course_book_id in", values, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdNotIn(List<Long> values) {
            addCriterion("course_book_id not in", values, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdBetween(Long value1, Long value2) {
            addCriterion("course_book_id between", value1, value2, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andCourseBookIdNotBetween(Long value1, Long value2) {
            addCriterion("course_book_id not between", value1, value2, "courseBookId");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNull() {
            addCriterion("instruction is null");
            return (Criteria) this;
        }

        public Criteria andInstructionIsNotNull() {
            addCriterion("instruction is not null");
            return (Criteria) this;
        }

        public Criteria andInstructionEqualTo(String value) {
            addCriterion("instruction =", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotEqualTo(String value) {
            addCriterion("instruction <>", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThan(String value) {
            addCriterion("instruction >", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionGreaterThanOrEqualTo(String value) {
            addCriterion("instruction >=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThan(String value) {
            addCriterion("instruction <", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLessThanOrEqualTo(String value) {
            addCriterion("instruction <=", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionLike(String value) {
            addCriterion("instruction like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotLike(String value) {
            addCriterion("instruction not like", value, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionIn(List<String> values) {
            addCriterion("instruction in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotIn(List<String> values) {
            addCriterion("instruction not in", values, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionBetween(String value1, String value2) {
            addCriterion("instruction between", value1, value2, "instruction");
            return (Criteria) this;
        }

        public Criteria andInstructionNotBetween(String value1, String value2) {
            addCriterion("instruction not between", value1, value2, "instruction");
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