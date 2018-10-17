package com.yourui.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RulesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public RulesExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andRuleGroupIdIsNull() {
            addCriterion("rule_group_id is null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdIsNotNull() {
            addCriterion("rule_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdEqualTo(Long value) {
            addCriterion("rule_group_id =", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotEqualTo(Long value) {
            addCriterion("rule_group_id <>", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdGreaterThan(Long value) {
            addCriterion("rule_group_id >", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rule_group_id >=", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLessThan(Long value) {
            addCriterion("rule_group_id <", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdLessThanOrEqualTo(Long value) {
            addCriterion("rule_group_id <=", value, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdIn(List<Long> values) {
            addCriterion("rule_group_id in", values, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotIn(List<Long> values) {
            addCriterion("rule_group_id not in", values, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdBetween(Long value1, Long value2) {
            addCriterion("rule_group_id between", value1, value2, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdNotBetween(Long value1, Long value2) {
            addCriterion("rule_group_id not between", value1, value2, "ruleGroupId");
            return (Criteria) this;
        }

        public Criteria andFromPortIsNull() {
            addCriterion("from_port is null");
            return (Criteria) this;
        }

        public Criteria andFromPortIsNotNull() {
            addCriterion("from_port is not null");
            return (Criteria) this;
        }

        public Criteria andFromPortEqualTo(Long value) {
            addCriterion("from_port =", value, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortNotEqualTo(Long value) {
            addCriterion("from_port <>", value, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortGreaterThan(Long value) {
            addCriterion("from_port >", value, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortGreaterThanOrEqualTo(Long value) {
            addCriterion("from_port >=", value, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortLessThan(Long value) {
            addCriterion("from_port <", value, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortLessThanOrEqualTo(Long value) {
            addCriterion("from_port <=", value, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortIn(List<Long> values) {
            addCriterion("from_port in", values, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortNotIn(List<Long> values) {
            addCriterion("from_port not in", values, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortBetween(Long value1, Long value2) {
            addCriterion("from_port between", value1, value2, "fromPort");
            return (Criteria) this;
        }

        public Criteria andFromPortNotBetween(Long value1, Long value2) {
            addCriterion("from_port not between", value1, value2, "fromPort");
            return (Criteria) this;
        }

        public Criteria andToPortIsNull() {
            addCriterion("to_port is null");
            return (Criteria) this;
        }

        public Criteria andToPortIsNotNull() {
            addCriterion("to_port is not null");
            return (Criteria) this;
        }

        public Criteria andToPortEqualTo(Long value) {
            addCriterion("to_port =", value, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortNotEqualTo(Long value) {
            addCriterion("to_port <>", value, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortGreaterThan(Long value) {
            addCriterion("to_port >", value, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortGreaterThanOrEqualTo(Long value) {
            addCriterion("to_port >=", value, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortLessThan(Long value) {
            addCriterion("to_port <", value, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortLessThanOrEqualTo(Long value) {
            addCriterion("to_port <=", value, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortIn(List<Long> values) {
            addCriterion("to_port in", values, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortNotIn(List<Long> values) {
            addCriterion("to_port not in", values, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortBetween(Long value1, Long value2) {
            addCriterion("to_port between", value1, value2, "toPort");
            return (Criteria) this;
        }

        public Criteria andToPortNotBetween(Long value1, Long value2) {
            addCriterion("to_port not between", value1, value2, "toPort");
            return (Criteria) this;
        }

        public Criteria andToIpIsNull() {
            addCriterion("to_ip is null");
            return (Criteria) this;
        }

        public Criteria andToIpIsNotNull() {
            addCriterion("to_ip is not null");
            return (Criteria) this;
        }

        public Criteria andToIpEqualTo(String value) {
            addCriterion("to_ip =", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpNotEqualTo(String value) {
            addCriterion("to_ip <>", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpGreaterThan(String value) {
            addCriterion("to_ip >", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpGreaterThanOrEqualTo(String value) {
            addCriterion("to_ip >=", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpLessThan(String value) {
            addCriterion("to_ip <", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpLessThanOrEqualTo(String value) {
            addCriterion("to_ip <=", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpLike(String value) {
            addCriterion("to_ip like", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpNotLike(String value) {
            addCriterion("to_ip not like", value, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpIn(List<String> values) {
            addCriterion("to_ip in", values, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpNotIn(List<String> values) {
            addCriterion("to_ip not in", values, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpBetween(String value1, String value2) {
            addCriterion("to_ip between", value1, value2, "toIp");
            return (Criteria) this;
        }

        public Criteria andToIpNotBetween(String value1, String value2) {
            addCriterion("to_ip not between", value1, value2, "toIp");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNull() {
            addCriterion("rule_name is null");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNotNull() {
            addCriterion("rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andRuleNameEqualTo(String value) {
            addCriterion("rule_name =", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotEqualTo(String value) {
            addCriterion("rule_name <>", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThan(String value) {
            addCriterion("rule_name >", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("rule_name >=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThan(String value) {
            addCriterion("rule_name <", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThanOrEqualTo(String value) {
            addCriterion("rule_name <=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLike(String value) {
            addCriterion("rule_name like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotLike(String value) {
            addCriterion("rule_name not like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameIn(List<String> values) {
            addCriterion("rule_name in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotIn(List<String> values) {
            addCriterion("rule_name not in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameBetween(String value1, String value2) {
            addCriterion("rule_name between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotBetween(String value1, String value2) {
            addCriterion("rule_name not between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andAgreementIsNull() {
            addCriterion("agreement is null");
            return (Criteria) this;
        }

        public Criteria andAgreementIsNotNull() {
            addCriterion("agreement is not null");
            return (Criteria) this;
        }

        public Criteria andAgreementEqualTo(String value) {
            addCriterion("agreement =", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementNotEqualTo(String value) {
            addCriterion("agreement <>", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementGreaterThan(String value) {
            addCriterion("agreement >", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("agreement >=", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementLessThan(String value) {
            addCriterion("agreement <", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementLessThanOrEqualTo(String value) {
            addCriterion("agreement <=", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementLike(String value) {
            addCriterion("agreement like", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementNotLike(String value) {
            addCriterion("agreement not like", value, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementIn(List<String> values) {
            addCriterion("agreement in", values, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementNotIn(List<String> values) {
            addCriterion("agreement not in", values, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementBetween(String value1, String value2) {
            addCriterion("agreement between", value1, value2, "agreement");
            return (Criteria) this;
        }

        public Criteria andAgreementNotBetween(String value1, String value2) {
            addCriterion("agreement not between", value1, value2, "agreement");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnIsNull() {
            addCriterion("max_concurrent_conn is null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnIsNotNull() {
            addCriterion("max_concurrent_conn is not null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnEqualTo(String value) {
            addCriterion("max_concurrent_conn =", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnNotEqualTo(String value) {
            addCriterion("max_concurrent_conn <>", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnGreaterThan(String value) {
            addCriterion("max_concurrent_conn >", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnGreaterThanOrEqualTo(String value) {
            addCriterion("max_concurrent_conn >=", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnLessThan(String value) {
            addCriterion("max_concurrent_conn <", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnLessThanOrEqualTo(String value) {
            addCriterion("max_concurrent_conn <=", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnLike(String value) {
            addCriterion("max_concurrent_conn like", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnNotLike(String value) {
            addCriterion("max_concurrent_conn not like", value, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnIn(List<String> values) {
            addCriterion("max_concurrent_conn in", values, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnNotIn(List<String> values) {
            addCriterion("max_concurrent_conn not in", values, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnBetween(String value1, String value2) {
            addCriterion("max_concurrent_conn between", value1, value2, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnNotBetween(String value1, String value2) {
            addCriterion("max_concurrent_conn not between", value1, value2, "maxConcurrentConn");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpIsNull() {
            addCriterion("max_concurrent_conn_per_ip is null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpIsNotNull() {
            addCriterion("max_concurrent_conn_per_ip is not null");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpEqualTo(String value) {
            addCriterion("max_concurrent_conn_per_ip =", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpNotEqualTo(String value) {
            addCriterion("max_concurrent_conn_per_ip <>", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpGreaterThan(String value) {
            addCriterion("max_concurrent_conn_per_ip >", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpGreaterThanOrEqualTo(String value) {
            addCriterion("max_concurrent_conn_per_ip >=", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpLessThan(String value) {
            addCriterion("max_concurrent_conn_per_ip <", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpLessThanOrEqualTo(String value) {
            addCriterion("max_concurrent_conn_per_ip <=", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpLike(String value) {
            addCriterion("max_concurrent_conn_per_ip like", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpNotLike(String value) {
            addCriterion("max_concurrent_conn_per_ip not like", value, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpIn(List<String> values) {
            addCriterion("max_concurrent_conn_per_ip in", values, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpNotIn(List<String> values) {
            addCriterion("max_concurrent_conn_per_ip not in", values, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpBetween(String value1, String value2) {
            addCriterion("max_concurrent_conn_per_ip between", value1, value2, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxConcurrentConnPerIpNotBetween(String value1, String value2) {
            addCriterion("max_concurrent_conn_per_ip not between", value1, value2, "maxConcurrentConnPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpIsNull() {
            addCriterion("max_new_conn_per_min_per_ip is null");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpIsNotNull() {
            addCriterion("max_new_conn_per_min_per_ip is not null");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpEqualTo(String value) {
            addCriterion("max_new_conn_per_min_per_ip =", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpNotEqualTo(String value) {
            addCriterion("max_new_conn_per_min_per_ip <>", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpGreaterThan(String value) {
            addCriterion("max_new_conn_per_min_per_ip >", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpGreaterThanOrEqualTo(String value) {
            addCriterion("max_new_conn_per_min_per_ip >=", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpLessThan(String value) {
            addCriterion("max_new_conn_per_min_per_ip <", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpLessThanOrEqualTo(String value) {
            addCriterion("max_new_conn_per_min_per_ip <=", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpLike(String value) {
            addCriterion("max_new_conn_per_min_per_ip like", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpNotLike(String value) {
            addCriterion("max_new_conn_per_min_per_ip not like", value, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpIn(List<String> values) {
            addCriterion("max_new_conn_per_min_per_ip in", values, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpNotIn(List<String> values) {
            addCriterion("max_new_conn_per_min_per_ip not in", values, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpBetween(String value1, String value2) {
            addCriterion("max_new_conn_per_min_per_ip between", value1, value2, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andMaxNewConnPerMinPerIpNotBetween(String value1, String value2) {
            addCriterion("max_new_conn_per_min_per_ip not between", value1, value2, "maxNewConnPerMinPerIp");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsIsNull() {
            addCriterion("rev_first_pkg_timeout_mills is null");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsIsNotNull() {
            addCriterion("rev_first_pkg_timeout_mills is not null");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsEqualTo(String value) {
            addCriterion("rev_first_pkg_timeout_mills =", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsNotEqualTo(String value) {
            addCriterion("rev_first_pkg_timeout_mills <>", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsGreaterThan(String value) {
            addCriterion("rev_first_pkg_timeout_mills >", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsGreaterThanOrEqualTo(String value) {
            addCriterion("rev_first_pkg_timeout_mills >=", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsLessThan(String value) {
            addCriterion("rev_first_pkg_timeout_mills <", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsLessThanOrEqualTo(String value) {
            addCriterion("rev_first_pkg_timeout_mills <=", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsLike(String value) {
            addCriterion("rev_first_pkg_timeout_mills like", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsNotLike(String value) {
            addCriterion("rev_first_pkg_timeout_mills not like", value, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsIn(List<String> values) {
            addCriterion("rev_first_pkg_timeout_mills in", values, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsNotIn(List<String> values) {
            addCriterion("rev_first_pkg_timeout_mills not in", values, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsBetween(String value1, String value2) {
            addCriterion("rev_first_pkg_timeout_mills between", value1, value2, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRevFirstPkgTimeoutMillsNotBetween(String value1, String value2) {
            addCriterion("rev_first_pkg_timeout_mills not between", value1, value2, "revFirstPkgTimeoutMills");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andDelIsNull() {
            addCriterion("del is null");
            return (Criteria) this;
        }

        public Criteria andDelIsNotNull() {
            addCriterion("del is not null");
            return (Criteria) this;
        }

        public Criteria andDelEqualTo(Byte value) {
            addCriterion("del =", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotEqualTo(Byte value) {
            addCriterion("del <>", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThan(Byte value) {
            addCriterion("del >", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelGreaterThanOrEqualTo(Byte value) {
            addCriterion("del >=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThan(Byte value) {
            addCriterion("del <", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelLessThanOrEqualTo(Byte value) {
            addCriterion("del <=", value, "del");
            return (Criteria) this;
        }

        public Criteria andDelIn(List<Byte> values) {
            addCriterion("del in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotIn(List<Byte> values) {
            addCriterion("del not in", values, "del");
            return (Criteria) this;
        }

        public Criteria andDelBetween(Byte value1, Byte value2) {
            addCriterion("del between", value1, value2, "del");
            return (Criteria) this;
        }

        public Criteria andDelNotBetween(Byte value1, Byte value2) {
            addCriterion("del not between", value1, value2, "del");
            return (Criteria) this;
        }

        public Criteria andCrttimeIsNull() {
            addCriterion("crtTime is null");
            return (Criteria) this;
        }

        public Criteria andCrttimeIsNotNull() {
            addCriterion("crtTime is not null");
            return (Criteria) this;
        }

        public Criteria andCrttimeEqualTo(Date value) {
            addCriterion("crtTime =", value, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeNotEqualTo(Date value) {
            addCriterion("crtTime <>", value, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeGreaterThan(Date value) {
            addCriterion("crtTime >", value, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("crtTime >=", value, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeLessThan(Date value) {
            addCriterion("crtTime <", value, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeLessThanOrEqualTo(Date value) {
            addCriterion("crtTime <=", value, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeIn(List<Date> values) {
            addCriterion("crtTime in", values, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeNotIn(List<Date> values) {
            addCriterion("crtTime not in", values, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeBetween(Date value1, Date value2) {
            addCriterion("crtTime between", value1, value2, "crttime");
            return (Criteria) this;
        }

        public Criteria andCrttimeNotBetween(Date value1, Date value2) {
            addCriterion("crtTime not between", value1, value2, "crttime");
            return (Criteria) this;
        }
    }

    /**
     */
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