package edu.uni.professionalcourses.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SyllabusTeachingContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SyllabusTeachingContentExample() {
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

        public Criteria andCourseSyllabusDescriptionIdIsNull() {
            addCriterion("course_syllabus_description_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdIsNotNull() {
            addCriterion("course_syllabus_description_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdEqualTo(Long value) {
            addCriterion("course_syllabus_description_id =", value, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdNotEqualTo(Long value) {
            addCriterion("course_syllabus_description_id <>", value, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdGreaterThan(Long value) {
            addCriterion("course_syllabus_description_id >", value, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_syllabus_description_id >=", value, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdLessThan(Long value) {
            addCriterion("course_syllabus_description_id <", value, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdLessThanOrEqualTo(Long value) {
            addCriterion("course_syllabus_description_id <=", value, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdIn(List<Long> values) {
            addCriterion("course_syllabus_description_id in", values, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdNotIn(List<Long> values) {
            addCriterion("course_syllabus_description_id not in", values, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdBetween(Long value1, Long value2) {
            addCriterion("course_syllabus_description_id between", value1, value2, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andCourseSyllabusDescriptionIdNotBetween(Long value1, Long value2) {
            addCriterion("course_syllabus_description_id not between", value1, value2, "courseSyllabusDescriptionId");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireIsNull() {
            addCriterion("teach_goal_require is null");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireIsNotNull() {
            addCriterion("teach_goal_require is not null");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireEqualTo(String value) {
            addCriterion("teach_goal_require =", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireNotEqualTo(String value) {
            addCriterion("teach_goal_require <>", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireGreaterThan(String value) {
            addCriterion("teach_goal_require >", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireGreaterThanOrEqualTo(String value) {
            addCriterion("teach_goal_require >=", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireLessThan(String value) {
            addCriterion("teach_goal_require <", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireLessThanOrEqualTo(String value) {
            addCriterion("teach_goal_require <=", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireLike(String value) {
            addCriterion("teach_goal_require like", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireNotLike(String value) {
            addCriterion("teach_goal_require not like", value, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireIn(List<String> values) {
            addCriterion("teach_goal_require in", values, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireNotIn(List<String> values) {
            addCriterion("teach_goal_require not in", values, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireBetween(String value1, String value2) {
            addCriterion("teach_goal_require between", value1, value2, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachGoalRequireNotBetween(String value1, String value2) {
            addCriterion("teach_goal_require not between", value1, value2, "teachGoalRequire");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointIsNull() {
            addCriterion("teach_important_point is null");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointIsNotNull() {
            addCriterion("teach_important_point is not null");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointEqualTo(String value) {
            addCriterion("teach_important_point =", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointNotEqualTo(String value) {
            addCriterion("teach_important_point <>", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointGreaterThan(String value) {
            addCriterion("teach_important_point >", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointGreaterThanOrEqualTo(String value) {
            addCriterion("teach_important_point >=", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointLessThan(String value) {
            addCriterion("teach_important_point <", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointLessThanOrEqualTo(String value) {
            addCriterion("teach_important_point <=", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointLike(String value) {
            addCriterion("teach_important_point like", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointNotLike(String value) {
            addCriterion("teach_important_point not like", value, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointIn(List<String> values) {
            addCriterion("teach_important_point in", values, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointNotIn(List<String> values) {
            addCriterion("teach_important_point not in", values, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointBetween(String value1, String value2) {
            addCriterion("teach_important_point between", value1, value2, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachImportantPointNotBetween(String value1, String value2) {
            addCriterion("teach_important_point not between", value1, value2, "teachImportantPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointIsNull() {
            addCriterion("teach_difficult_point is null");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointIsNotNull() {
            addCriterion("teach_difficult_point is not null");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointEqualTo(String value) {
            addCriterion("teach_difficult_point =", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointNotEqualTo(String value) {
            addCriterion("teach_difficult_point <>", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointGreaterThan(String value) {
            addCriterion("teach_difficult_point >", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointGreaterThanOrEqualTo(String value) {
            addCriterion("teach_difficult_point >=", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointLessThan(String value) {
            addCriterion("teach_difficult_point <", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointLessThanOrEqualTo(String value) {
            addCriterion("teach_difficult_point <=", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointLike(String value) {
            addCriterion("teach_difficult_point like", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointNotLike(String value) {
            addCriterion("teach_difficult_point not like", value, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointIn(List<String> values) {
            addCriterion("teach_difficult_point in", values, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointNotIn(List<String> values) {
            addCriterion("teach_difficult_point not in", values, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointBetween(String value1, String value2) {
            addCriterion("teach_difficult_point between", value1, value2, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachDifficultPointNotBetween(String value1, String value2) {
            addCriterion("teach_difficult_point not between", value1, value2, "teachDifficultPoint");
            return (Criteria) this;
        }

        public Criteria andTeachMethodIsNull() {
            addCriterion("teach_method is null");
            return (Criteria) this;
        }

        public Criteria andTeachMethodIsNotNull() {
            addCriterion("teach_method is not null");
            return (Criteria) this;
        }

        public Criteria andTeachMethodEqualTo(String value) {
            addCriterion("teach_method =", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodNotEqualTo(String value) {
            addCriterion("teach_method <>", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodGreaterThan(String value) {
            addCriterion("teach_method >", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodGreaterThanOrEqualTo(String value) {
            addCriterion("teach_method >=", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodLessThan(String value) {
            addCriterion("teach_method <", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodLessThanOrEqualTo(String value) {
            addCriterion("teach_method <=", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodLike(String value) {
            addCriterion("teach_method like", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodNotLike(String value) {
            addCriterion("teach_method not like", value, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodIn(List<String> values) {
            addCriterion("teach_method in", values, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodNotIn(List<String> values) {
            addCriterion("teach_method not in", values, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodBetween(String value1, String value2) {
            addCriterion("teach_method between", value1, value2, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andTeachMethodNotBetween(String value1, String value2) {
            addCriterion("teach_method not between", value1, value2, "teachMethod");
            return (Criteria) this;
        }

        public Criteria andChapterContentIsNull() {
            addCriterion("chapter_content is null");
            return (Criteria) this;
        }

        public Criteria andChapterContentIsNotNull() {
            addCriterion("chapter_content is not null");
            return (Criteria) this;
        }

        public Criteria andChapterContentEqualTo(String value) {
            addCriterion("chapter_content =", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentNotEqualTo(String value) {
            addCriterion("chapter_content <>", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentGreaterThan(String value) {
            addCriterion("chapter_content >", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentGreaterThanOrEqualTo(String value) {
            addCriterion("chapter_content >=", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentLessThan(String value) {
            addCriterion("chapter_content <", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentLessThanOrEqualTo(String value) {
            addCriterion("chapter_content <=", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentLike(String value) {
            addCriterion("chapter_content like", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentNotLike(String value) {
            addCriterion("chapter_content not like", value, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentIn(List<String> values) {
            addCriterion("chapter_content in", values, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentNotIn(List<String> values) {
            addCriterion("chapter_content not in", values, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentBetween(String value1, String value2) {
            addCriterion("chapter_content between", value1, value2, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andChapterContentNotBetween(String value1, String value2) {
            addCriterion("chapter_content not between", value1, value2, "chapterContent");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionIsNull() {
            addCriterion("reflection_question is null");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionIsNotNull() {
            addCriterion("reflection_question is not null");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionEqualTo(String value) {
            addCriterion("reflection_question =", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionNotEqualTo(String value) {
            addCriterion("reflection_question <>", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionGreaterThan(String value) {
            addCriterion("reflection_question >", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionGreaterThanOrEqualTo(String value) {
            addCriterion("reflection_question >=", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionLessThan(String value) {
            addCriterion("reflection_question <", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionLessThanOrEqualTo(String value) {
            addCriterion("reflection_question <=", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionLike(String value) {
            addCriterion("reflection_question like", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionNotLike(String value) {
            addCriterion("reflection_question not like", value, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionIn(List<String> values) {
            addCriterion("reflection_question in", values, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionNotIn(List<String> values) {
            addCriterion("reflection_question not in", values, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionBetween(String value1, String value2) {
            addCriterion("reflection_question between", value1, value2, "reflectionQuestion");
            return (Criteria) this;
        }

        public Criteria andReflectionQuestionNotBetween(String value1, String value2) {
            addCriterion("reflection_question not between", value1, value2, "reflectionQuestion");
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