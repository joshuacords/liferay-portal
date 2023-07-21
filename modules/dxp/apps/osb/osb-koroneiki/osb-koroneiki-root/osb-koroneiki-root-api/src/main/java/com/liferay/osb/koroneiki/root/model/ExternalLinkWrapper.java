/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.root.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ExternalLink}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalLink
 * @generated
 */
public class ExternalLinkWrapper
	extends BaseModelWrapper<ExternalLink>
	implements ExternalLink, ModelWrapper<ExternalLink> {

	public ExternalLinkWrapper(ExternalLink externalLink) {
		super(externalLink);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("externalLinkId", getExternalLinkId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("externalLinkKey", getExternalLinkKey());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("domain", getDomain());
		attributes.put("entityName", getEntityName());
		attributes.put("entityId", getEntityId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long externalLinkId = (Long)attributes.get("externalLinkId");

		if (externalLinkId != null) {
			setExternalLinkId(externalLinkId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String externalLinkKey = (String)attributes.get("externalLinkKey");

		if (externalLinkKey != null) {
			setExternalLinkKey(externalLinkKey);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String domain = (String)attributes.get("domain");

		if (domain != null) {
			setDomain(domain);
		}

		String entityName = (String)attributes.get("entityName");

		if (entityName != null) {
			setEntityName(entityName);
		}

		String entityId = (String)attributes.get("entityId");

		if (entityId != null) {
			setEntityId(entityId);
		}
	}

	/**
	 * Returns the fully qualified class name of this external link.
	 *
	 * @return the fully qualified class name of this external link
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this external link.
	 *
	 * @return the class name ID of this external link
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class pk of this external link.
	 *
	 * @return the class pk of this external link
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the company ID of this external link.
	 *
	 * @return the company ID of this external link
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this external link.
	 *
	 * @return the create date of this external link
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the domain of this external link.
	 *
	 * @return the domain of this external link
	 */
	@Override
	public String getDomain() {
		return model.getDomain();
	}

	/**
	 * Returns the entity ID of this external link.
	 *
	 * @return the entity ID of this external link
	 */
	@Override
	public String getEntityId() {
		return model.getEntityId();
	}

	/**
	 * Returns the entity name of this external link.
	 *
	 * @return the entity name of this external link
	 */
	@Override
	public String getEntityName() {
		return model.getEntityName();
	}

	/**
	 * Returns the external link ID of this external link.
	 *
	 * @return the external link ID of this external link
	 */
	@Override
	public long getExternalLinkId() {
		return model.getExternalLinkId();
	}

	/**
	 * Returns the external link key of this external link.
	 *
	 * @return the external link key of this external link
	 */
	@Override
	public String getExternalLinkKey() {
		return model.getExternalLinkKey();
	}

	/**
	 * Returns the modified date of this external link.
	 *
	 * @return the modified date of this external link
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this external link.
	 *
	 * @return the mvcc version of this external link
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this external link.
	 *
	 * @return the primary key of this external link
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	@Override
	public String getUrl() {
		return model.getUrl();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void setClassName(String className) {
		model.setClassName(className);
	}

	/**
	 * Sets the class name ID of this external link.
	 *
	 * @param classNameId the class name ID of this external link
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this external link.
	 *
	 * @param classPK the class pk of this external link
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the company ID of this external link.
	 *
	 * @param companyId the company ID of this external link
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this external link.
	 *
	 * @param createDate the create date of this external link
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the domain of this external link.
	 *
	 * @param domain the domain of this external link
	 */
	@Override
	public void setDomain(String domain) {
		model.setDomain(domain);
	}

	/**
	 * Sets the entity ID of this external link.
	 *
	 * @param entityId the entity ID of this external link
	 */
	@Override
	public void setEntityId(String entityId) {
		model.setEntityId(entityId);
	}

	/**
	 * Sets the entity name of this external link.
	 *
	 * @param entityName the entity name of this external link
	 */
	@Override
	public void setEntityName(String entityName) {
		model.setEntityName(entityName);
	}

	/**
	 * Sets the external link ID of this external link.
	 *
	 * @param externalLinkId the external link ID of this external link
	 */
	@Override
	public void setExternalLinkId(long externalLinkId) {
		model.setExternalLinkId(externalLinkId);
	}

	/**
	 * Sets the external link key of this external link.
	 *
	 * @param externalLinkKey the external link key of this external link
	 */
	@Override
	public void setExternalLinkKey(String externalLinkKey) {
		model.setExternalLinkKey(externalLinkKey);
	}

	/**
	 * Sets the modified date of this external link.
	 *
	 * @param modifiedDate the modified date of this external link
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this external link.
	 *
	 * @param mvccVersion the mvcc version of this external link
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this external link.
	 *
	 * @param primaryKey the primary key of this external link
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ExternalLinkWrapper wrap(ExternalLink externalLink) {
		return new ExternalLinkWrapper(externalLink);
	}

}