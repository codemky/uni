package edu.uni.professionalcourses.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseTeachingScheduleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseTeachingScheduleExample() {
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

        public Criteria andWeekIsNull() {
            addCriterion("week is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(String value) {
            addCriterion("week =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(String value) {
            addCriterion("week <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(String value) {
            addCriterion("week >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(String value) {
            addCriterion("week >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(String value) {
            addCriterion("week <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(String value) {
            addCriterion("week <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLike(String value) {
            addCriterion("week like", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotLike(String value) {
            addCriterion("week not like", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<String> values) {
            addCriterion("week in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<String> values) {
            addCriterion("week not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(String value1, String value2) {
            addCriterion("week between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(String value1, String value2) {
            addCriterion("week not between", value1, value2, "week");
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

        public Criteria andTeachHourIsNull() {
            addCriterion("teach_hour is null");
            return (Criteria) this;
        }

        public Criteria andTeachHourIsNotNull() {
            addCriterion("teach_hour is not null");
            return (Criteria) this;
        }

        public Criteria andTeachHourEqualTo(Integer value) {
            addCriterion("teach_hour =", value, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourNotEqualTo(Integer value) {
            addCriterion("teach_hour <>", value, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourGreaterThan(Integer value) {
            addCriterion("teach_hour >", value, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourGreaterThanOrEqualTo(Integer value) {
            addCriterion("teach_hour >=", value, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourLessThan(Integer value) {
            addCriterion("teach_hour <", value, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourLessThanOrEqualTo(Integer value) {
            addCriterion("teach_hour <=", value, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourIn(List<Integer> values) {
            addCriterion("teach_hour in", values, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourNotIn(List<Integer> values) {
            addCriterion("teach_hour not in", values, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourBetween(Integer value1, Integer value2) {
            addCriterion("teach_hour between", value1, value2, "teachHour");
            return (Criteria) this;
        }

        public Criteria andTeachHourNotBetween(Integer value1, Integer value2) {
            addCriterion("teach_hour not between", value1, value2, "teachHour");
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

        public Criteria andHomeworkIsNull() {
            addCriterion("homework is null");
            return (Criteria) this;
        }

        public Criteria andHomeworkIsNotNull() {
            addCriterion("homework is not null");
            return (Criteria) this;
        }

        public Criteria andHomeworkEqualTo(String value) {
            addCriterion("homework =", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkNotEqualTo(String value) {
            addCriterion("homework <>", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkGreaterThan(String value) {
            addCriterion("homework >", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkGreaterThanOrEqualTo(String value) {
            addCriterion("homework >=", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkLessThan(String value) {
            addCriterion("homework <", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkLessThanOrEqualTo(String value) {
            addCriterion("homework <=", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkLike(String value) {
            addCriterion("homework like", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkNotLike(String value) {
            addCriterion("homework not like", value, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkIn(List<String> values) {
            addCriterion("homework in", values, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkNotIn(List<String> values) {
            addCriterion("homework not in", values, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkBetween(String value1, String value2) {
            addCriterion("homework between", value1, value2, "homework");
            return (Criteria) this;
        }

        public Criteria andHomeworkNotBetween(String value1, String value2) {
            addCriterion("homework not between", value1, value2, "homework");
            return (Criteria) this;
        }

        public Criteria andActualProgressIsNull() {
            addCriterion("actual_progress is null");
            return (Criteria) this;
        }

        public Criteria andActualProgressIsNotNull() {
            addCriterion("actual_progress is not null");
            return (Criteria) this;
        }

        public Criteria andActualProgressEqualTo(String value) {
            addCriterion("actual_progress =", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressNotEqualTo(String value) {
            addCriterion("actual_progress <>", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressGreaterThan(String value) {
            addCriterion("actual_progress >", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressGreaterThanOrEqualTo(String value) {
            addCriterion("actual_progress >=", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressLessThan(String value) {
            addCriterion("actual_progress <", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressLessThanOrEqualTo(String value) {
            addCriterion("actual_progress <=", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressLike(String value) {
            addCriterion("actual_progress like", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressNotLike(String value) {
            addCriterion("actual_progress not like", value, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressIn(List<String> values) {
            addCriterion("actual_progress in", values, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressNotIn(List<String> values) {
            addCriterion("actual_progress not in", values, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressBetween(String value1, String value2) {
            addCriterion("actual_progress between", value1, value2, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andActualProgressNotBetween(String value1, String value2) {
            addCriterion("actual_progress not between", value1, value2, "actualProgress");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonIsNull() {
            addCriterion("out_of_plan_reason is null");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonIsNotNull() {
            addCriterion("out_of_plan_reason is not null");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonEqualTo(String value) {
            addCriterion("out_of_plan_reason =", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonNotEqualTo(String value) {
            addCriterion("out_of_plan_reason <>", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonGreaterThan(String value) {
            addCriterion("out_of_plan_reason >", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonGreaterThanOrEqualTo(String value) {
            addCriterion("out_of_plan_reason >=", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonLessThan(String value) {
            addCriterion("out_of_plan_reason <", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonLessThanOrEqualTo(String value) {
            addCriterion("out_of_plan_reason <=", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonLike(String value) {
            addCriterion("out_of_plan_reason like", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonNotLike(String value) {
            addCriterion("out_of_plan_reason not like", value, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonIn(List<String> values) {
            addCriterion("out_of_plan_reason in", values, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonNotIn(List<String> values) {
            addCriterion("out_of_plan_reason not in", values, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonBetween(String value1, String value2) {
            addCriterion("out_of_plan_reason between", value1, value2, "outOfPlanReason");
            return (Criteria) this;
        }

        public Criteria andOutOfPlanReasonNotBetween(String value1, String value2) {
            addCriterion("out_of_plan_reason not between", value1, value2, "outOfPlanReason");
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