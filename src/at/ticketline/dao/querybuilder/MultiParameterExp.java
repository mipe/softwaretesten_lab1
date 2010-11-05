package at.ticketline.dao.querybuilder;

import java.util.Map;

import javax.persistence.Query;

public class MultiParameterExp implements Expression {
	public static char sign = ':';

	protected String expression = null;
	protected Map<String, Object> parameters = null;

	public MultiParameterExp(String expression, Map<String, Object> parameters) {
		this.expression = expression;
		this.parameters = parameters;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Map<String, Object> getParameters() {
		return this.parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}

	public String createExpressionString() {
		if ((this.expression == null) || (this.expression.length() == 0)) {
			throw new RuntimeException("Expression may not be null or empty");
		}
		return "(" + this.expression + ")";
	}

	@Override
	public void addParameters(Query q) {
		for (Map.Entry<String, Object> e : this.parameters.entrySet()) {
			if (e.getValue() != null) {
				q.setParameter(e.getKey(), e.getValue());
			} else {
				q.setParameter(e.getKey(), "");
			}
		}
	}

}
