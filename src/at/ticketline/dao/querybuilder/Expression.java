package at.ticketline.dao.querybuilder;

import javax.persistence.Query;

public interface Expression {
	public String createExpressionString();

	public void addParameters(Query q);

}
