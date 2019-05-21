package edu.uni.administrativestructure.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UniversityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UniversityExample() {
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

        public Criteria andUnitNumberIsNull() {
            addCriterion("unit_number is null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIsNotNull() {
            addCriterion("unit_number is not null");
            return (Criteria) this;
        }

        public Criteria andUnitNumberEqualTo(String value) {
            addCriterion("unit_number =", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotEqualTo(String value) {
            addCriterion("unit_number <>", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThan(String value) {
            addCriterion("unit_number >", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberGreaterThanOrEqualTo(String value) {
            addCriterion("unit_number >=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThan(String value) {
            addCriterion("unit_number <", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLessThanOrEqualTo(String value) {
            addCriterion("unit_number <=", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberLike(String value) {
            addCriterion("unit_number like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotLike(String value) {
            addCriterion("unit_number not like", value, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberIn(List<String> values) {
            addCriterion("unit_number in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotIn(List<String> values) {
            addCriterion("unit_number not in", values, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberBetween(String value1, String value2) {
            addCriterion("unit_number between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andUnitNumberNotBetween(String value1, String value2) {
            addCriterion("unit_number not between", value1, value2, "unitNumber");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeIsNull() {
            addCriterion("social_trust_code is null");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeIsNotNull() {
            addCriterion("social_trust_code is not null");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeEqualTo(String value) {
            addCriterion("social_trust_code =", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeNotEqualTo(String value) {
            addCriterion("social_trust_code <>", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeGreaterThan(String value) {
            addCriterion("social_trust_code >", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeGreaterThanOrEqualTo(String value) {
            addCriterion("social_trust_code >=", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeLessThan(String value) {
            addCriterion("social_trust_code <", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeLessThanOrEqualTo(String value) {
            addCriterion("social_trust_code <=", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeLike(String value) {
            addCriterion("social_trust_code like", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeNotLike(String value) {
            addCriterion("social_trust_code not like", value, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeIn(List<String> values) {
            addCriterion("social_trust_code in", values, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeNotIn(List<String> values) {
            addCriterion("social_trust_code not in", values, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeBetween(String value1, String value2) {
            addCriterion("social_trust_code between", value1, value2, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andSocialTrustCodeNotBetween(String value1, String value2) {
            addCriterion("social_trust_code not between", value1, value2, "socialTrustCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeIsNull() {
            addCriterion("certification_code is null");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeIsNotNull() {
            addCriterion("certification_code is not null");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeEqualTo(String value) {
            addCriterion("certification_code =", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeNotEqualTo(String value) {
            addCriterion("certification_code <>", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeGreaterThan(String value) {
            addCriterion("certification_code >", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("certification_code >=", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeLessThan(String value) {
            addCriterion("certification_code <", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeLessThanOrEqualTo(String value) {
            addCriterion("certification_code <=", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeLike(String value) {
            addCriterion("certification_code like", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeNotLike(String value) {
            addCriterion("certification_code not like", value, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeIn(List<String> values) {
            addCriterion("certification_code in", values, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeNotIn(List<String> values) {
            addCriterion("certification_code not in", values, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeBetween(String value1, String value2) {
            addCriterion("certification_code between", value1, value2, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andCertificationCodeNotBetween(String value1, String value2) {
            addCriterion("certification_code not between", value1, value2, "certificationCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeIsNull() {
            addCriterion("enterprise_code is null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeIsNotNull() {
            addCriterion("enterprise_code is not null");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeEqualTo(String value) {
            addCriterion("enterprise_code =", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotEqualTo(String value) {
            addCriterion("enterprise_code <>", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeGreaterThan(String value) {
            addCriterion("enterprise_code >", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("enterprise_code >=", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeLessThan(String value) {
            addCriterion("enterprise_code <", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeLessThanOrEqualTo(String value) {
            addCriterion("enterprise_code <=", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeLike(String value) {
            addCriterion("enterprise_code like", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotLike(String value) {
            addCriterion("enterprise_code not like", value, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeIn(List<String> values) {
            addCriterion("enterprise_code in", values, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotIn(List<String> values) {
            addCriterion("enterprise_code not in", values, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeBetween(String value1, String value2) {
            addCriterion("enterprise_code between", value1, value2, "enterpriseCode");
            return (Criteria) this;
        }

        public Criteria andEnterpriseCodeNotBetween(String value1, String value2) {
            addCriterion("enterprise_code not between", value1, value2, "enterpriseCode");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesIsNull() {
            addCriterion("funding_sources is null");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesIsNotNull() {
            addCriterion("funding_sources is not null");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesEqualTo(Integer value) {
            addCriterion("funding_sources =", value, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesNotEqualTo(Integer value) {
            addCriterion("funding_sources <>", value, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesGreaterThan(Integer value) {
            addCriterion("funding_sources >", value, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesGreaterThanOrEqualTo(Integer value) {
            addCriterion("funding_sources >=", value, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesLessThan(Integer value) {
            addCriterion("funding_sources <", value, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesLessThanOrEqualTo(Integer value) {
            addCriterion("funding_sources <=", value, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesIn(List<Integer> values) {
            addCriterion("funding_sources in", values, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesNotIn(List<Integer> values) {
            addCriterion("funding_sources not in", values, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesBetween(Integer value1, Integer value2) {
            addCriterion("funding_sources between", value1, value2, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andFundingSourcesNotBetween(Integer value1, Integer value2) {
            addCriterion("funding_sources not between", value1, value2, "fundingSources");
            return (Criteria) this;
        }

        public Criteria andEstablishDateIsNull() {
            addCriterion("establish_date is null");
            return (Criteria) this;
        }

        public Criteria andEstablishDateIsNotNull() {
            addCriterion("establish_date is not null");
            return (Criteria) this;
        }

        public Criteria andEstablishDateEqualTo(Date value) {
            addCriterion("establish_date =", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateNotEqualTo(Date value) {
            addCriterion("establish_date <>", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateGreaterThan(Date value) {
            addCriterion("establish_date >", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateGreaterThanOrEqualTo(Date value) {
            addCriterion("establish_date >=", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateLessThan(Date value) {
            addCriterion("establish_date <", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateLessThanOrEqualTo(Date value) {
            addCriterion("establish_date <=", value, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateIn(List<Date> values) {
            addCriterion("establish_date in", values, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateNotIn(List<Date> values) {
            addCriterion("establish_date not in", values, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateBetween(Date value1, Date value2) {
            addCriterion("establish_date between", value1, value2, "establishDate");
            return (Criteria) this;
        }

        public Criteria andEstablishDateNotBetween(Date value1, Date value2) {
            addCriterion("establish_date not between", value1, value2, "establishDate");
            return (Criteria) this;
        }

        public Criteria andHostedByIsNull() {
            addCriterion("hosted_by is null");
            return (Criteria) this;
        }

        public Criteria andHostedByIsNotNull() {
            addCriterion("hosted_by is not null");
            return (Criteria) this;
        }

        public Criteria andHostedByEqualTo(Integer value) {
            addCriterion("hosted_by =", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByNotEqualTo(Integer value) {
            addCriterion("hosted_by <>", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByGreaterThan(Integer value) {
            addCriterion("hosted_by >", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByGreaterThanOrEqualTo(Integer value) {
            addCriterion("hosted_by >=", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByLessThan(Integer value) {
            addCriterion("hosted_by <", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByLessThanOrEqualTo(Integer value) {
            addCriterion("hosted_by <=", value, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByIn(List<Integer> values) {
            addCriterion("hosted_by in", values, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByNotIn(List<Integer> values) {
            addCriterion("hosted_by not in", values, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByBetween(Integer value1, Integer value2) {
            addCriterion("hosted_by between", value1, value2, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andHostedByNotBetween(Integer value1, Integer value2) {
            addCriterion("hosted_by not between", value1, value2, "hostedBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByIsNull() {
            addCriterion("admini_by is null");
            return (Criteria) this;
        }

        public Criteria andAdminiByIsNotNull() {
            addCriterion("admini_by is not null");
            return (Criteria) this;
        }

        public Criteria andAdminiByEqualTo(Integer value) {
            addCriterion("admini_by =", value, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByNotEqualTo(Integer value) {
            addCriterion("admini_by <>", value, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByGreaterThan(Integer value) {
            addCriterion("admini_by >", value, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByGreaterThanOrEqualTo(Integer value) {
            addCriterion("admini_by >=", value, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByLessThan(Integer value) {
            addCriterion("admini_by <", value, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByLessThanOrEqualTo(Integer value) {
            addCriterion("admini_by <=", value, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByIn(List<Integer> values) {
            addCriterion("admini_by in", values, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByNotIn(List<Integer> values) {
            addCriterion("admini_by not in", values, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByBetween(Integer value1, Integer value2) {
            addCriterion("admini_by between", value1, value2, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andAdminiByNotBetween(Integer value1, Integer value2) {
            addCriterion("admini_by not between", value1, value2, "adminiBy");
            return (Criteria) this;
        }

        public Criteria andInitialFundingIsNull() {
            addCriterion("initial_funding is null");
            return (Criteria) this;
        }

        public Criteria andInitialFundingIsNotNull() {
            addCriterion("initial_funding is not null");
            return (Criteria) this;
        }

        public Criteria andInitialFundingEqualTo(Integer value) {
            addCriterion("initial_funding =", value, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingNotEqualTo(Integer value) {
            addCriterion("initial_funding <>", value, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingGreaterThan(Integer value) {
            addCriterion("initial_funding >", value, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingGreaterThanOrEqualTo(Integer value) {
            addCriterion("initial_funding >=", value, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingLessThan(Integer value) {
            addCriterion("initial_funding <", value, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingLessThanOrEqualTo(Integer value) {
            addCriterion("initial_funding <=", value, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingIn(List<Integer> values) {
            addCriterion("initial_funding in", values, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingNotIn(List<Integer> values) {
            addCriterion("initial_funding not in", values, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingBetween(Integer value1, Integer value2) {
            addCriterion("initial_funding between", value1, value2, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andInitialFundingNotBetween(Integer value1, Integer value2) {
            addCriterion("initial_funding not between", value1, value2, "initialFunding");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateIsNull() {
            addCriterion("certification_begin_date is null");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateIsNotNull() {
            addCriterion("certification_begin_date is not null");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateEqualTo(Date value) {
            addCriterion("certification_begin_date =", value, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateNotEqualTo(Date value) {
            addCriterion("certification_begin_date <>", value, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateGreaterThan(Date value) {
            addCriterion("certification_begin_date >", value, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("certification_begin_date >=", value, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateLessThan(Date value) {
            addCriterion("certification_begin_date <", value, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateLessThanOrEqualTo(Date value) {
            addCriterion("certification_begin_date <=", value, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateIn(List<Date> values) {
            addCriterion("certification_begin_date in", values, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateNotIn(List<Date> values) {
            addCriterion("certification_begin_date not in", values, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateBetween(Date value1, Date value2) {
            addCriterion("certification_begin_date between", value1, value2, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationBeginDateNotBetween(Date value1, Date value2) {
            addCriterion("certification_begin_date not between", value1, value2, "certificationBeginDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateIsNull() {
            addCriterion("certification_end_date is null");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateIsNotNull() {
            addCriterion("certification_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateEqualTo(Date value) {
            addCriterion("certification_end_date =", value, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateNotEqualTo(Date value) {
            addCriterion("certification_end_date <>", value, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateGreaterThan(Date value) {
            addCriterion("certification_end_date >", value, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("certification_end_date >=", value, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateLessThan(Date value) {
            addCriterion("certification_end_date <", value, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateLessThanOrEqualTo(Date value) {
            addCriterion("certification_end_date <=", value, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateIn(List<Date> values) {
            addCriterion("certification_end_date in", values, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateNotIn(List<Date> values) {
            addCriterion("certification_end_date not in", values, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateBetween(Date value1, Date value2) {
            addCriterion("certification_end_date between", value1, value2, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andCertificationEndDateNotBetween(Date value1, Date value2) {
            addCriterion("certification_end_date not between", value1, value2, "certificationEndDate");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNull() {
            addCriterion("telephone is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("telephone is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("telephone =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("telephone <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("telephone >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("telephone >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("telephone <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("telephone <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("telephone like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("telephone not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("telephone in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("telephone not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("telephone between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("telephone not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
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