package com.yourui.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GatewayAddressExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GatewayAddressExample() {
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

        public Criteria andInternalNetworkIpIsNull() {
            addCriterion("internal_network_ip is null");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpIsNotNull() {
            addCriterion("internal_network_ip is not null");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpEqualTo(String value) {
            addCriterion("internal_network_ip =", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpNotEqualTo(String value) {
            addCriterion("internal_network_ip <>", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpGreaterThan(String value) {
            addCriterion("internal_network_ip >", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpGreaterThanOrEqualTo(String value) {
            addCriterion("internal_network_ip >=", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpLessThan(String value) {
            addCriterion("internal_network_ip <", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpLessThanOrEqualTo(String value) {
            addCriterion("internal_network_ip <=", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpLike(String value) {
            addCriterion("internal_network_ip like", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpNotLike(String value) {
            addCriterion("internal_network_ip not like", value, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpIn(List<String> values) {
            addCriterion("internal_network_ip in", values, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpNotIn(List<String> values) {
            addCriterion("internal_network_ip not in", values, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpBetween(String value1, String value2) {
            addCriterion("internal_network_ip between", value1, value2, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andInternalNetworkIpNotBetween(String value1, String value2) {
            addCriterion("internal_network_ip not between", value1, value2, "internalNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpIsNull() {
            addCriterion("outside_network_ip is null");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpIsNotNull() {
            addCriterion("outside_network_ip is not null");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpEqualTo(String value) {
            addCriterion("outside_network_ip =", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpNotEqualTo(String value) {
            addCriterion("outside_network_ip <>", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpGreaterThan(String value) {
            addCriterion("outside_network_ip >", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpGreaterThanOrEqualTo(String value) {
            addCriterion("outside_network_ip >=", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpLessThan(String value) {
            addCriterion("outside_network_ip <", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpLessThanOrEqualTo(String value) {
            addCriterion("outside_network_ip <=", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpLike(String value) {
            addCriterion("outside_network_ip like", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpNotLike(String value) {
            addCriterion("outside_network_ip not like", value, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpIn(List<String> values) {
            addCriterion("outside_network_ip in", values, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpNotIn(List<String> values) {
            addCriterion("outside_network_ip not in", values, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpBetween(String value1, String value2) {
            addCriterion("outside_network_ip between", value1, value2, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andOutsideNetworkIpNotBetween(String value1, String value2) {
            addCriterion("outside_network_ip not between", value1, value2, "outsideNetworkIp");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameIsNull() {
            addCriterion("gateway_address_name is null");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameIsNotNull() {
            addCriterion("gateway_address_name is not null");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameEqualTo(String value) {
            addCriterion("gateway_address_name =", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameNotEqualTo(String value) {
            addCriterion("gateway_address_name <>", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameGreaterThan(String value) {
            addCriterion("gateway_address_name >", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameGreaterThanOrEqualTo(String value) {
            addCriterion("gateway_address_name >=", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameLessThan(String value) {
            addCriterion("gateway_address_name <", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameLessThanOrEqualTo(String value) {
            addCriterion("gateway_address_name <=", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameLike(String value) {
            addCriterion("gateway_address_name like", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameNotLike(String value) {
            addCriterion("gateway_address_name not like", value, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameIn(List<String> values) {
            addCriterion("gateway_address_name in", values, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameNotIn(List<String> values) {
            addCriterion("gateway_address_name not in", values, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameBetween(String value1, String value2) {
            addCriterion("gateway_address_name between", value1, value2, "gatewayAddressName");
            return (Criteria) this;
        }

        public Criteria andGatewayAddressNameNotBetween(String value1, String value2) {
            addCriterion("gateway_address_name not between", value1, value2, "gatewayAddressName");
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