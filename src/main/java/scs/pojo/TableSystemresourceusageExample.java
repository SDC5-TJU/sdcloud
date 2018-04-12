package scs.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableSystemresourceusageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected int limit;
    
    public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public TableSystemresourceusageExample() {
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

        public Criteria andAutoidIsNull() {
            addCriterion("autoId is null");
            return (Criteria) this;
        }

        public Criteria andAutoidIsNotNull() {
            addCriterion("autoId is not null");
            return (Criteria) this;
        }

        public Criteria andAutoidEqualTo(Integer value) {
            addCriterion("autoId =", value, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidNotEqualTo(Integer value) {
            addCriterion("autoId <>", value, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidGreaterThan(Integer value) {
            addCriterion("autoId >", value, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidGreaterThanOrEqualTo(Integer value) {
            addCriterion("autoId >=", value, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidLessThan(Integer value) {
            addCriterion("autoId <", value, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidLessThanOrEqualTo(Integer value) {
            addCriterion("autoId <=", value, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidIn(List<Integer> values) {
            addCriterion("autoId in", values, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidNotIn(List<Integer> values) {
            addCriterion("autoId not in", values, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidBetween(Integer value1, Integer value2) {
            addCriterion("autoId between", value1, value2, "autoid");
            return (Criteria) this;
        }

        public Criteria andAutoidNotBetween(Integer value1, Integer value2) {
            addCriterion("autoId not between", value1, value2, "autoid");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNull() {
            addCriterion("hostName is null");
            return (Criteria) this;
        }

        public Criteria andHostnameIsNotNull() {
            addCriterion("hostName is not null");
            return (Criteria) this;
        }

        public Criteria andHostnameEqualTo(String value) {
            addCriterion("hostName =", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotEqualTo(String value) {
            addCriterion("hostName <>", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThan(String value) {
            addCriterion("hostName >", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameGreaterThanOrEqualTo(String value) {
            addCriterion("hostName >=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThan(String value) {
            addCriterion("hostName <", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLessThanOrEqualTo(String value) {
            addCriterion("hostName <=", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameLike(String value) {
            addCriterion("hostName like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotLike(String value) {
            addCriterion("hostName not like", value, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameIn(List<String> values) {
            addCriterion("hostName in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotIn(List<String> values) {
            addCriterion("hostName not in", values, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameBetween(String value1, String value2) {
            addCriterion("hostName between", value1, value2, "hostname");
            return (Criteria) this;
        }

        public Criteria andHostnameNotBetween(String value1, String value2) {
            addCriterion("hostName not between", value1, value2, "hostname");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateIsNull() {
            addCriterion("cpuUsageRate is null");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateIsNotNull() {
            addCriterion("cpuUsageRate is not null");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateEqualTo(Float value) {
            addCriterion("cpuUsageRate =", value, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateNotEqualTo(Float value) {
            addCriterion("cpuUsageRate <>", value, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateGreaterThan(Float value) {
            addCriterion("cpuUsageRate >", value, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateGreaterThanOrEqualTo(Float value) {
            addCriterion("cpuUsageRate >=", value, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateLessThan(Float value) {
            addCriterion("cpuUsageRate <", value, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateLessThanOrEqualTo(Float value) {
            addCriterion("cpuUsageRate <=", value, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateIn(List<Float> values) {
            addCriterion("cpuUsageRate in", values, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateNotIn(List<Float> values) {
            addCriterion("cpuUsageRate not in", values, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateBetween(Float value1, Float value2) {
            addCriterion("cpuUsageRate between", value1, value2, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andCpuusagerateNotBetween(Float value1, Float value2) {
            addCriterion("cpuUsageRate not between", value1, value2, "cpuusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateIsNull() {
            addCriterion("memUsageRate is null");
            return (Criteria) this;
        }

        public Criteria andMemusagerateIsNotNull() {
            addCriterion("memUsageRate is not null");
            return (Criteria) this;
        }

        public Criteria andMemusagerateEqualTo(Float value) {
            addCriterion("memUsageRate =", value, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateNotEqualTo(Float value) {
            addCriterion("memUsageRate <>", value, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateGreaterThan(Float value) {
            addCriterion("memUsageRate >", value, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateGreaterThanOrEqualTo(Float value) {
            addCriterion("memUsageRate >=", value, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateLessThan(Float value) {
            addCriterion("memUsageRate <", value, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateLessThanOrEqualTo(Float value) {
            addCriterion("memUsageRate <=", value, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateIn(List<Float> values) {
            addCriterion("memUsageRate in", values, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateNotIn(List<Float> values) {
            addCriterion("memUsageRate not in", values, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateBetween(Float value1, Float value2) {
            addCriterion("memUsageRate between", value1, value2, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusagerateNotBetween(Float value1, Float value2) {
            addCriterion("memUsageRate not between", value1, value2, "memusagerate");
            return (Criteria) this;
        }

        public Criteria andMemusageamountIsNull() {
            addCriterion("memUsageAmount is null");
            return (Criteria) this;
        }

        public Criteria andMemusageamountIsNotNull() {
            addCriterion("memUsageAmount is not null");
            return (Criteria) this;
        }

        public Criteria andMemusageamountEqualTo(Float value) {
            addCriterion("memUsageAmount =", value, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountNotEqualTo(Float value) {
            addCriterion("memUsageAmount <>", value, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountGreaterThan(Float value) {
            addCriterion("memUsageAmount >", value, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountGreaterThanOrEqualTo(Float value) {
            addCriterion("memUsageAmount >=", value, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountLessThan(Float value) {
            addCriterion("memUsageAmount <", value, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountLessThanOrEqualTo(Float value) {
            addCriterion("memUsageAmount <=", value, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountIn(List<Float> values) {
            addCriterion("memUsageAmount in", values, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountNotIn(List<Float> values) {
            addCriterion("memUsageAmount not in", values, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountBetween(Float value1, Float value2) {
            addCriterion("memUsageAmount between", value1, value2, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andMemusageamountNotBetween(Float value1, Float value2) {
            addCriterion("memUsageAmount not between", value1, value2, "memusageamount");
            return (Criteria) this;
        }

        public Criteria andNetusagerateIsNull() {
            addCriterion("netUsageRate is null");
            return (Criteria) this;
        }

        public Criteria andNetusagerateIsNotNull() {
            addCriterion("netUsageRate is not null");
            return (Criteria) this;
        }

        public Criteria andNetusagerateEqualTo(Float value) {
            addCriterion("netUsageRate =", value, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateNotEqualTo(Float value) {
            addCriterion("netUsageRate <>", value, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateGreaterThan(Float value) {
            addCriterion("netUsageRate >", value, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateGreaterThanOrEqualTo(Float value) {
            addCriterion("netUsageRate >=", value, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateLessThan(Float value) {
            addCriterion("netUsageRate <", value, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateLessThanOrEqualTo(Float value) {
            addCriterion("netUsageRate <=", value, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateIn(List<Float> values) {
            addCriterion("netUsageRate in", values, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateNotIn(List<Float> values) {
            addCriterion("netUsageRate not in", values, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateBetween(Float value1, Float value2) {
            addCriterion("netUsageRate between", value1, value2, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andNetusagerateNotBetween(Float value1, Float value2) {
            addCriterion("netUsageRate not between", value1, value2, "netusagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateIsNull() {
            addCriterion("ioUsageRate is null");
            return (Criteria) this;
        }

        public Criteria andIousagerateIsNotNull() {
            addCriterion("ioUsageRate is not null");
            return (Criteria) this;
        }

        public Criteria andIousagerateEqualTo(Float value) {
            addCriterion("ioUsageRate =", value, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateNotEqualTo(Float value) {
            addCriterion("ioUsageRate <>", value, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateGreaterThan(Float value) {
            addCriterion("ioUsageRate >", value, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateGreaterThanOrEqualTo(Float value) {
            addCriterion("ioUsageRate >=", value, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateLessThan(Float value) {
            addCriterion("ioUsageRate <", value, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateLessThanOrEqualTo(Float value) {
            addCriterion("ioUsageRate <=", value, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateIn(List<Float> values) {
            addCriterion("ioUsageRate in", values, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateNotIn(List<Float> values) {
            addCriterion("ioUsageRate not in", values, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateBetween(Float value1, Float value2) {
            addCriterion("ioUsageRate between", value1, value2, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andIousagerateNotBetween(Float value1, Float value2) {
            addCriterion("ioUsageRate not between", value1, value2, "iousagerate");
            return (Criteria) this;
        }

        public Criteria andBlockioIsNull() {
            addCriterion("blockIo is null");
            return (Criteria) this;
        }

        public Criteria andBlockioIsNotNull() {
            addCriterion("blockIo is not null");
            return (Criteria) this;
        }

        public Criteria andBlockioEqualTo(Float value) {
            addCriterion("blockIo =", value, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioNotEqualTo(Float value) {
            addCriterion("blockIo <>", value, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioGreaterThan(Float value) {
            addCriterion("blockIo >", value, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioGreaterThanOrEqualTo(Float value) {
            addCriterion("blockIo >=", value, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioLessThan(Float value) {
            addCriterion("blockIo <", value, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioLessThanOrEqualTo(Float value) {
            addCriterion("blockIo <=", value, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioIn(List<Float> values) {
            addCriterion("blockIo in", values, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioNotIn(List<Float> values) {
            addCriterion("blockIo not in", values, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioBetween(Float value1, Float value2) {
            addCriterion("blockIo between", value1, value2, "blockio");
            return (Criteria) this;
        }

        public Criteria andBlockioNotBetween(Float value1, Float value2) {
            addCriterion("blockIo not between", value1, value2, "blockio");
            return (Criteria) this;
        }

        public Criteria andCollecttimeIsNull() {
            addCriterion("collectTime is null");
            return (Criteria) this;
        }

        public Criteria andCollecttimeIsNotNull() {
            addCriterion("collectTime is not null");
            return (Criteria) this;
        }

        public Criteria andCollecttimeEqualTo(Date value) {
            addCriterion("collectTime =", value, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeNotEqualTo(Date value) {
            addCriterion("collectTime <>", value, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeGreaterThan(Date value) {
            addCriterion("collectTime >", value, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("collectTime >=", value, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeLessThan(Date value) {
            addCriterion("collectTime <", value, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeLessThanOrEqualTo(Date value) {
            addCriterion("collectTime <=", value, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeIn(List<Date> values) {
            addCriterion("collectTime in", values, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeNotIn(List<Date> values) {
            addCriterion("collectTime not in", values, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeBetween(Date value1, Date value2) {
            addCriterion("collectTime between", value1, value2, "collecttime");
            return (Criteria) this;
        }

        public Criteria andCollecttimeNotBetween(Date value1, Date value2) {
            addCriterion("collectTime not between", value1, value2, "collecttime");
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