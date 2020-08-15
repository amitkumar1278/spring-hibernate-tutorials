package com.spring.sboot.model;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<T> {

	@CreatedBy
	protected T createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	protected Date createdDate;
	
	@LastModifiedBy
	protected T lastmodifiedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	protected Date lastmodifiedDate;

	public T getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(T createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public T getLastmodifiedBy() {
		return lastmodifiedBy;
	}

	public void setLastmodifiedBy(T lastmodifiedBy) {
		this.lastmodifiedBy = lastmodifiedBy;
	}

	public Date getLastmodifiedDate() {
		return lastmodifiedDate;
	}

	public void setLastmodifiedDate(Date lastmodifiedDate) {
		this.lastmodifiedDate = lastmodifiedDate;
	}

	@Override
	public String toString() {
		return "Auditable [createdBy=" + createdBy + ", createdDate=" + createdDate + ", lastmodifiedBy="
				+ lastmodifiedBy + ", lastmodifiedDate=" + lastmodifiedDate + "]";
	}
	
	
}
