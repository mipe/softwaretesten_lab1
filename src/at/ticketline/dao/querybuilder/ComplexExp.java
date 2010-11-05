package at.ticketline.dao.querybuilder;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

public class ComplexExp extends AbstractList<Expression> implements Expression {
	public enum ComplexType {
		AND, OR
	};

	protected ComplexType type = null;
	protected List<Expression> expressions = null;

	public ComplexExp(ComplexType type, Expression... e) {
		this.type = type;
		this.expressions = Arrays.asList(e);
	}

	public String createExpressionString() {
		StringBuilder sb = new StringBuilder();
		if (this.expressions.size() == 0) {
			return "";
		}
		sb.append(this.expressions.get(0).createExpressionString());
		if (this.expressions.size() == 1) {
			return sb.toString();
		}
		String sep = "";
		switch (this.type) {
		case AND:
			sep = " AND ";
			break;
		case OR:
			sep = " OR ";
			break;
		}
		for (int i = 1; i < this.expressions.size(); i++) {
			String exp = this.expressions.get(i).createExpressionString();
			if ((exp != null) && (exp.length() > 0)) {
				if (sb.length() > 0) {
					sb.append(sep);
				}
				sb.append(exp);
			}
		}
		if (sb.length() == 0) {
			return "";
		} else {
			return "(" + sb.append(")").toString();
		}
	}

	@Override
	public void addParameters(Query q) {
		for (Expression e : this.expressions) {
			e.addParameters(q);
		}

	}

	@Override
	public Expression get(int pos) {
		return this.expressions.get(pos);
	}

	@Override
	public int size() {
		return this.expressions.size();
	}

	@Override
	public void add(int index, Expression element) {
		this.expressions.add(index, element);
	}

	@Override
	public Expression remove(int index) {
		return this.expressions.remove(index);
	}
}
