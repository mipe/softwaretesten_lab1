package at.ticketline.dao.querybuilder;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import at.ticketline.dao.querybuilder.ComplexExp.ComplexType;

public class QueryBuilder {
	public enum QueryType {
		SELECT, UPDATE, DELETE
	};

	private QueryType type = QueryType.SELECT;
	private String select;
	private String from;
	private String order;
	private String group;
	private String having;
	private String setValues;
	private Expression whereExpression = null;
	private Map<String, Object> parameters = null;

	protected QueryBuilder(QueryType type) {
		this.type = type;
	}

	public static QueryBuilder build() {
		return new QueryBuilder(QueryType.SELECT);
	}

	public static QueryBuilder build(QueryType type) {
		return new QueryBuilder(type);
	}

	public String getSelect() {
		return this.select;
	}

	public QueryBuilder select(String select) {
		this.select = select;
		return this;
	}

	public String getFrom() {
		return this.from;
	}

	public QueryBuilder from(String from) {
		this.from = from;
		return this;
	}

	public QueryBuilder deleteFrom(String from) {
		this.from = from;
		return this;
	}

	public QueryBuilder update(String update) {
		this.from = update;
		return this;
	}

	public String getOrder() {
		return this.order;
	}

	public QueryBuilder orderBy(String order) {
		this.order = order;
		return this;
	}

	public String group() {
		return this.group;
	}

	public String getHaving() {
		return this.having;
	}

	public QueryBuilder groupBy(String group) {
		this.group = group;
		return this;
	}

	public QueryBuilder groupBy(String group, String having) {
		this.group = group;
		this.having = having;
		return this;
	}

	public String getSet() {
		return this.setValues;
	}

	public QueryBuilder set(String setValues, Map<String, Object> parameters) {
		this.setValues = setValues;
		this.parameters = parameters;
		return this;
	}

	public QueryBuilder where(String exp) {
		this.whereExpression = new StringExp(exp);
		return this;
	}

	public QueryBuilder where(String exp, Object parameter) {
		this.whereExpression = new ParameterExp(exp, parameter);
		return this;
	}

	public QueryBuilder where(String exp, Map<String, Object> parameters) {
		this.whereExpression = new MultiParameterExp(exp, parameters);
		return this;
	}

	public QueryBuilder where(Expression e) {
		this.whereExpression = e;
		return this;
	}

	public QueryBuilder whereAnd(Expression... e) {
		this.whereExpression = new ComplexExp(ComplexType.AND, e);
		return this;
	}

	public QueryBuilder whereOr(Expression... e) {
		this.whereExpression = new ComplexExp(ComplexType.OR, e);
		return this;
	}

	public String createQueryString() {
		switch (this.type) {
		case SELECT:
			return this.createSelectQueryString();
		case UPDATE:
			return this.createUpdateQueryString();
		case DELETE:
			return this.createDeleteQueryString();
		}
		return "";
	}

	public Query createQuery(EntityManager em) {
		Query q = em.createQuery(this.createQueryString());
		if (this.whereExpression != null) {
			this.whereExpression.addParameters(q);
		}
		if ((this.type == QueryType.UPDATE) && (this.parameters != null)) {
			for (Map.Entry<String, Object> p : this.parameters.entrySet()) {
				q.setParameter(p.getKey(), p.getValue());
			}
		}
		return q;
	}

	protected String createSelectQueryString() {
		StringBuilder sb = new StringBuilder();
		if ((this.select == null) || (this.select.length() == 0)) {
			throw new RuntimeException("SELECT-Clause may not be null");
		}
		if ((this.from == null) || (this.from.length() == 0)) {
			throw new RuntimeException("FROM-Clause may not be null");
		}

		sb.append("SELECT ").append(this.select);
		sb.append(" FROM ").append(this.from);
		this.appendWhereExpression(sb);
		if ((this.group != null) && (this.group.length() > 0)) {
			sb.append(" GROUP BY ").append(this.group);
		}
		if ((this.having != null) && (this.having.length() > 0)) {
			sb.append(" HAVING ").append(this.having);
		}
		if ((this.order != null) && (this.order.length() > 0)) {
			sb.append(" ORDER BY ").append(this.order);
		}
		return sb.toString();
	}

	protected String createUpdateQueryString() {
		StringBuilder sb = new StringBuilder();
		if ((this.from == null) || (this.from.length() == 0)) {
			throw new RuntimeException("UPDATE-Clause may not be null");
		}
		if ((this.setValues == null) || (this.setValues.length() == 0)) {
			throw new RuntimeException("SET-Clause may not be null");
		}
		sb.append("UPDATE ").append(this.from);
		sb.append(" SET ").append(this.setValues);
		this.appendWhereExpression(sb);
		return sb.toString();
	}

	protected String createDeleteQueryString() {
		StringBuilder sb = new StringBuilder();
		if ((this.from == null) || (this.from.length() == 0)) {
			throw new RuntimeException("DELETE FROM-Clause may not be null");
		}
		sb.append("DELETE FROM ").append(this.from);
		this.appendWhereExpression(sb);
		return sb.toString();
	}

	protected void appendWhereExpression(StringBuilder sb) {
		if (this.whereExpression != null) {
			String exp = this.whereExpression.createExpressionString();
			if ((exp != null) && (exp.length() > 0)) {
				sb.append(" WHERE ").append(exp);
			}
		}
	}
}
