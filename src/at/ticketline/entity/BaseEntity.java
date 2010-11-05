package at.ticketline.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {

	@Version
	protected Integer version = 0;

	public Integer getVersion() {
		return this.version;
	}
}
