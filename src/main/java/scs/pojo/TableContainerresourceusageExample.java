package scs.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TableContainerresourceusageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TableContainerresourceusageExample() {
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

        public Criteria andContainernameIsNull() {
            addCriterion("containerName is null");
            return (Criteria) this;
        }

        public Criteria andContainernameIsNotNull() {
            addCriterion("containerName is not null");
            return (Criteria) this;
        }

        public Criteria andContainernameEqualTo(String value) {
            addCriterion("containerName =", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameNotEqualTo(String value) {
            addCriterion("containerName <>", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameGreaterThan(String value) {
            addCriterion("containerName >", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameGreaterThanOrEqualTo(String value) {
            addCriterion("containerName >=", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameLessThan(String value) {
            addCriterion("containerName <", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameLessThanOrEqualTo(String value) {
            addCriterion("containerName <=", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameLike(String value) {
            addCriterion("containerName like", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameNotLike(String value) {
            addCriterion("containerName not like", value, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameIn(List<String> values) {
            addCriterion("containerName in", values, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameNotIn(List<String> values) {
            addCriterion("containerName not in", values, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameBetween(String value1, String value2) {
            addCriterion("containerName between", value1, value2, "containername");
            return (Criteria) this;
        }

        public Criteria andContainernameNotBetween(String value1, String value2) {
            addCriterion("containerName not between", value1, value2, "containername");
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

        public Criteria andNetinputIsNull() {
            addCriterion("netInput is null");
            return (Criteria) this;
        }

        public Criteria andNetinputIsNotNull() {
            addCriterion("netInput is not null");
            return (Criteria) this;
        }

        public Criteria andNetinputEqualTo(Float value) {
            addCriterion("netInput =", value, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputNotEqualTo(Float value) {
            addCriterion("netInput <>", value, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputGreaterThan(Float value) {
            addCriterion("netInput >", value, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputGreaterThanOrEqualTo(Float value) {
            addCriterion("netInput >=", value, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputLessThan(Float value) {
            addCriterion("netInput <", value, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputLessThanOrEqualTo(Float value) {
            addCriterion("netInput <=", value, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputIn(List<Float> values) {
            addCriterion("netInput in", values, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputNotIn(List<Float> values) {
            addCriterion("netInput not in", values, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputBetween(Float value1, Float value2) {
            addCriterion("netInput between", value1, value2, "netinput");
            return (Criteria) this;
        }

        public Criteria andNetinputNotBetween(Float value1, Float value2) {
            addCriterion("netInput not between", value1, value2, "netinput");
            return (Criteria) this;
        }

        public Criteria andIoinputIsNull() {
            addCriterion("ioInput is null");
            return (Criteria) this;
        }

        public Criteria andIoinputIsNotNull() {
            addCriterion("ioInput is not null");
            return (Criteria) this;
        }

        public Criteria andIoinputEqualTo(Float value) {
            addCriterion("ioInput =", value, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputNotEqualTo(Float value) {
            addCriterion("ioInput <>", value, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputGreaterThan(Float value) {
            addCriterion("ioInput >", value, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputGreaterThanOrEqualTo(Float value) {
            addCriterion("ioInput >=", value, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputLessThan(Float value) {
            addCriterion("ioInput <", value, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputLessThanOrEqualTo(Float value) {
            addCriterion("ioInput <=", value, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputIn(List<Float> values) {
            addCriterion("ioInput in", values, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputNotIn(List<Float> values) {
            addCriterion("ioInput not in", values, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputBetween(Float value1, Float value2) {
            addCriterion("ioInput between", value1, value2, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIoinputNotBetween(Float value1, Float value2) {
            addCriterion("ioInput not between", value1, value2, "ioinput");
            return (Criteria) this;
        }

        public Criteria andIooutputIsNull() {
            addCriterion("ioOutput is null");
            return (Criteria) this;
        }

        public Criteria andIooutputIsNotNull() {
            addCriterion("ioOutput is not null");
            return (Criteria) this;
        }

        public Criteria andIooutputEqualTo(Float value) {
            addCriterion("ioOutput =", value, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputNotEqualTo(Float value) {
            addCriterion("ioOutput <>", value, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputGreaterThan(Float value) {
            addCriterion("ioOutput >", value, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputGreaterThanOrEqualTo(Float value) {
            addCriterion("ioOutput >=", value, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputLessThan(Float value) {
            addCriterion("ioOutput <", value, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputLessThanOrEqualTo(Float value) {
            addCriterion("ioOutput <=", value, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputIn(List<Float> values) {
            addCriterion("ioOutput in", values, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputNotIn(List<Float> values) {
            addCriterion("ioOutput not in", values, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputBetween(Float value1, Float value2) {
            addCriterion("ioOutput between", value1, value2, "iooutput");
            return (Criteria) this;
        }

        public Criteria andIooutputNotBetween(Float value1, Float value2) {
            addCriterion("ioOutput not between", value1, value2, "iooutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputIsNull() {
            addCriterion("netOutput is null");
            return (Criteria) this;
        }

        public Criteria andNetoutputIsNotNull() {
            addCriterion("netOutput is not null");
            return (Criteria) this;
        }

        public Criteria andNetoutputEqualTo(Float value) {
            addCriterion("netOutput =", value, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputNotEqualTo(Float value) {
            addCriterion("netOutput <>", value, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputGreaterThan(Float value) {
            addCriterion("netOutput >", value, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputGreaterThanOrEqualTo(Float value) {
            addCriterion("netOutput >=", value, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputLessThan(Float value) {
            addCriterion("netOutput <", value, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputLessThanOrEqualTo(Float value) {
            addCriterion("netOutput <=", value, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputIn(List<Float> values) {
            addCriterion("netOutput in", values, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputNotIn(List<Float> values) {
            addCriterion("netOutput not in", values, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputBetween(Float value1, Float value2) {
            addCriterion("netOutput between", value1, value2, "netoutput");
            return (Criteria) this;
        }

        public Criteria andNetoutputNotBetween(Float value1, Float value2) {
            addCriterion("netOutput not between", value1, value2, "netoutput");
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