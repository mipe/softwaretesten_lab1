package at.ticketline.dao.querybuilder;

import javax.persistence.Query;

public class ParameterExp implements Expression {
	public static char sign = ':';

	protected String expression = null;
	protected String parameterName = null;
	protected Object value = null;

	public ParameterExp(String expression, Object value) {
		this.setExpression(expression);
		this.value = value;
	}

	public String getExpression() {
		return this.expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
		StringBuilder p = new StringBuilder();
		boolean para = false;
		char current;
		for (int i = 0; i < expression.length(); i++) {
			current = expression.charAt(i);
			if (current == ParameterExp.sign) {
				para = true;
			} else if (para) {
				if (current == ' ') {
					break;
				} else {
					p.append(current);
				}
			}
		}
		this.parameterName = p.toString();
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String createExpressionString() {
		if (this.value == null) {
			return "";
		}
		if ((this.expression == null) || (this.expression.length() == 0)) {
			throw new RuntimeException("Expression may not be null or empty");
		}
		if ((this.parameterName == null) || (this.parameterName.length() == 0)) {
			throw new RuntimeException(
					"Parameter name must be set in expression (eg :name)");
		}
		return "(" + this.expression + ")";
	}

	@Override
	public void addParameters(Query q) {
		if (this.value == null) {
			return;
		}
		q.setParameter(this.parameterName, this.value);
	}

}
