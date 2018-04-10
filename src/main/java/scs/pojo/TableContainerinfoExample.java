package scs.pojo;

import java.util.ArrayList;
import java.util.List;

public class TableContainerinfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TableContainerinfoExample() {
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

        public Criteria andContaineridIsNull() {
            addCriterion("containerId is null");
            return (Criteria) this;
        }

        public Criteria andContaineridIsNotNull() {
            addCriterion("containerId is not null");
            return (Criteria) this;
        }

        public Criteria andContaineridEqualTo(String value) {
            addCriterion("containerId =", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridNotEqualTo(String value) {
            addCriterion("containerId <>", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridGreaterThan(String value) {
            addCriterion("containerId >", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridGreaterThanOrEqualTo(String value) {
            addCriterion("containerId >=", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridLessThan(String value) {
            addCriterion("containerId <", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridLessThanOrEqualTo(String value) {
            addCriterion("containerId <=", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridLike(String value) {
            addCriterion("containerId like", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridNotLike(String value) {
            addCriterion("containerId not like", value, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridIn(List<String> values) {
            addCriterion("containerId in", values, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridNotIn(List<String> values) {
            addCriterion("containerId not in", values, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridBetween(String value1, String value2) {
            addCriterion("containerId between", value1, value2, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineridNotBetween(String value1, String value2) {
            addCriterion("containerId not between", value1, value2, "containerid");
            return (Criteria) this;
        }

        public Criteria andContaineripIsNull() {
            addCriterion("containerIp is null");
            return (Criteria) this;
        }

        public Criteria andContaineripIsNotNull() {
            addCriterion("containerIp is not null");
            return (Criteria) this;
        }

        public Criteria andContaineripEqualTo(String value) {
            addCriterion("containerIp =", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripNotEqualTo(String value) {
            addCriterion("containerIp <>", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripGreaterThan(String value) {
            addCriterion("containerIp >", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripGreaterThanOrEqualTo(String value) {
            addCriterion("containerIp >=", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripLessThan(String value) {
            addCriterion("containerIp <", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripLessThanOrEqualTo(String value) {
            addCriterion("containerIp <=", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripLike(String value) {
            addCriterion("containerIp like", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripNotLike(String value) {
            addCriterion("containerIp not like", value, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripIn(List<String> values) {
            addCriterion("containerIp in", values, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripNotIn(List<String> values) {
            addCriterion("containerIp not in", values, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripBetween(String value1, String value2) {
            addCriterion("containerIp between", value1, value2, "containerip");
            return (Criteria) this;
        }

        public Criteria andContaineripNotBetween(String value1, String value2) {
            addCriterion("containerIp not between", value1, value2, "containerip");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameIsNull() {
            addCriterion("containerHostName is null");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameIsNotNull() {
            addCriterion("containerHostName is not null");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameEqualTo(String value) {
            addCriterion("containerHostName =", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameNotEqualTo(String value) {
            addCriterion("containerHostName <>", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameGreaterThan(String value) {
            addCriterion("containerHostName >", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameGreaterThanOrEqualTo(String value) {
            addCriterion("containerHostName >=", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameLessThan(String value) {
            addCriterion("containerHostName <", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameLessThanOrEqualTo(String value) {
            addCriterion("containerHostName <=", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameLike(String value) {
            addCriterion("containerHostName like", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameNotLike(String value) {
            addCriterion("containerHostName not like", value, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameIn(List<String> values) {
            addCriterion("containerHostName in", values, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameNotIn(List<String> values) {
            addCriterion("containerHostName not in", values, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameBetween(String value1, String value2) {
            addCriterion("containerHostName between", value1, value2, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainerhostnameNotBetween(String value1, String value2) {
            addCriterion("containerHostName not between", value1, value2, "containerhostname");
            return (Criteria) this;
        }

        public Criteria andContainercommandIsNull() {
            addCriterion("containerCommand is null");
            return (Criteria) this;
        }

        public Criteria andContainercommandIsNotNull() {
            addCriterion("containerCommand is not null");
            return (Criteria) this;
        }

        public Criteria andContainercommandEqualTo(String value) {
            addCriterion("containerCommand =", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandNotEqualTo(String value) {
            addCriterion("containerCommand <>", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandGreaterThan(String value) {
            addCriterion("containerCommand >", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandGreaterThanOrEqualTo(String value) {
            addCriterion("containerCommand >=", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandLessThan(String value) {
            addCriterion("containerCommand <", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandLessThanOrEqualTo(String value) {
            addCriterion("containerCommand <=", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandLike(String value) {
            addCriterion("containerCommand like", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandNotLike(String value) {
            addCriterion("containerCommand not like", value, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandIn(List<String> values) {
            addCriterion("containerCommand in", values, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandNotIn(List<String> values) {
            addCriterion("containerCommand not in", values, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandBetween(String value1, String value2) {
            addCriterion("containerCommand between", value1, value2, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainercommandNotBetween(String value1, String value2) {
            addCriterion("containerCommand not between", value1, value2, "containercommand");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameIsNull() {
            addCriterion("containerImageName is null");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameIsNotNull() {
            addCriterion("containerImageName is not null");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameEqualTo(String value) {
            addCriterion("containerImageName =", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameNotEqualTo(String value) {
            addCriterion("containerImageName <>", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameGreaterThan(String value) {
            addCriterion("containerImageName >", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameGreaterThanOrEqualTo(String value) {
            addCriterion("containerImageName >=", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameLessThan(String value) {
            addCriterion("containerImageName <", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameLessThanOrEqualTo(String value) {
            addCriterion("containerImageName <=", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameLike(String value) {
            addCriterion("containerImageName like", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameNotLike(String value) {
            addCriterion("containerImageName not like", value, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameIn(List<String> values) {
            addCriterion("containerImageName in", values, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameNotIn(List<String> values) {
            addCriterion("containerImageName not in", values, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameBetween(String value1, String value2) {
            addCriterion("containerImageName between", value1, value2, "containerimagename");
            return (Criteria) this;
        }

        public Criteria andContainerimagenameNotBetween(String value1, String value2) {
            addCriterion("containerImageName not between", value1, value2, "containerimagename");
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