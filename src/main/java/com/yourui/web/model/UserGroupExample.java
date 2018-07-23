package com.yourui.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGroupExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UserGroupExample() {
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

        public Criteria andUserGroupNameIsNull() {
            addCriterion("user_group_name is null");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameIsNotNull() {
            addCriterion("user_group_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameEqualTo(String value) {
            addCriterion("user_group_name =", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameNotEqualTo(String value) {
            addCriterion("user_group_name <>", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameGreaterThan(String value) {
            addCriterion("user_group_name >", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_group_name >=", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameLessThan(String value) {
            addCriterion("user_group_name <", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameLessThanOrEqualTo(String value) {
            addCriterion("user_group_name <=", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameLike(String value) {
            addCriterion("user_group_name like", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameNotLike(String value) {
            addCriterion("user_group_name not like", value, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameIn(List<String> values) {
            addCriterion("user_group_name in", values, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameNotIn(List<String> values) {
            addCriterion("user_group_name not in", values, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameBetween(String value1, String value2) {
            addCriterion("user_group_name between", value1, value2, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andUserGroupNameNotBetween(String value1, String value2) {
            addCriterion("user_group_name not between", value1, value2, "userGroupName");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsIsNull() {
            addCriterion("rule_group_ids is null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsIsNotNull() {
            addCriterion("rule_group_ids is not null");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsEqualTo(String value) {
            addCriterion("rule_group_ids =", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsNotEqualTo(String value) {
            addCriterion("rule_group_ids <>", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsGreaterThan(String value) {
            addCriterion("rule_group_ids >", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsGreaterThanOrEqualTo(String value) {
            addCriterion("rule_group_ids >=", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsLessThan(String value) {
            addCriterion("rule_group_ids <", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsLessThanOrEqualTo(String value) {
            addCriterion("rule_group_ids <=", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsLike(String value) {
            addCriterion("rule_group_ids like", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsNotLike(String value) {
            addCriterion("rule_group_ids not like", value, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsIn(List<String> values) {
            addCriterion("rule_group_ids in", values, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsNotIn(List<String> values) {
            addCriterion("rule_group_ids not in", values, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsBetween(String value1, String value2) {
            addCriterion("rule_group_ids between", value1, value2, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andRuleGroupIdsNotBetween(String value1, String value2) {
            addCriterion("rule_group_ids not between", value1, value2, "ruleGroupIds");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNull() {
            addCriterion("game_id is null");
            return (Criteria) this;
        }

        public Criteria andGameIdIsNotNull() {
            addCriterion("game_id is not null");
            return (Criteria) this;
        }

        public Criteria andGameIdEqualTo(Long value) {
            addCriterion("game_id =", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotEqualTo(Long value) {
            addCriterion("game_id <>", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThan(Long value) {
            addCriterion("game_id >", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdGreaterThanOrEqualTo(Long value) {
            addCriterion("game_id >=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThan(Long value) {
            addCriterion("game_id <", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdLessThanOrEqualTo(Long value) {
            addCriterion("game_id <=", value, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdIn(List<Long> values) {
            addCriterion("game_id in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotIn(List<Long> values) {
            addCriterion("game_id not in", values, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdBetween(Long value1, Long value2) {
            addCriterion("game_id between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andGameIdNotBetween(Long value1, Long value2) {
            addCriterion("game_id not between", value1, value2, "gameId");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyIsNull() {
            addCriterion("encrypt_key is null");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyIsNotNull() {
            addCriterion("encrypt_key is not null");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyEqualTo(String value) {
            addCriterion("encrypt_key =", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotEqualTo(String value) {
            addCriterion("encrypt_key <>", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyGreaterThan(String value) {
            addCriterion("encrypt_key >", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyGreaterThanOrEqualTo(String value) {
            addCriterion("encrypt_key >=", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyLessThan(String value) {
            addCriterion("encrypt_key <", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyLessThanOrEqualTo(String value) {
            addCriterion("encrypt_key <=", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyLike(String value) {
            addCriterion("encrypt_key like", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotLike(String value) {
            addCriterion("encrypt_key not like", value, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyIn(List<String> values) {
            addCriterion("encrypt_key in", values, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotIn(List<String> values) {
            addCriterion("encrypt_key not in", values, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyBetween(String value1, String value2) {
            addCriterion("encrypt_key between", value1, value2, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andEncryptKeyNotBetween(String value1, String value2) {
            addCriterion("encrypt_key not between", value1, value2, "encryptKey");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsIsNull() {
            addCriterion("gateway_address_ids is null");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsIsNotNull() {
            addCriterion("gateway_address_ids is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsEqualTo(String value) {
            addCriterion("gateway_address_ids =", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsNotEqualTo(String value) {
            addCriterion("gateway_address_ids <>", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsGreaterThan(String value) {
            addCriterion("gateway_address_ids >", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_address_ids >=", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsLessThan(String value) {
            addCriterion("gateway_address_ids <", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsLessThanOrEqualTo(String value) {
            addCriterion("gateway_address_ids <=", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsLike(String value) {
            addCriterion("gateway_address_ids like", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsNotLike(String value) {
            addCriterion("gateway_address_ids not like", value, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsIn(List<String> values) {
            addCriterion("gateway_address_ids in", values, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsNotIn(List<String> values) {
            addCriterion("gateway_address_ids not in", values, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsBetween(String value1, String value2) {
            addCriterion("gateway_address_ids between", value1, value2, "gatewayAddressIds");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressIdsNotBetween(String value1, String value2) {
            addCriterion("gateway_address_ids not between", value1, value2, "gatewayAddressIds");
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