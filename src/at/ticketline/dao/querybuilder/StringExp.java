package at.ticketline.dao.querybuilder;

import javax.persistence.Query;

public class StringExp implements Expression {
	protected String value = null;

	public StringExp(String exp) {
		this.value = exp;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String createExpressionString() {
		if ((this.value == null) || (this.value.length() == 0)) {
			throw new RuntimeException("Expression may not be null or empty");
		}
		return "(" + this.value + ")";
	}

	@Override
	public void addParameters(Query q) {
		// nothing to do
	}
}
