/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SubscriptionEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SubscriptionEntry
 * @generated
 */
public class SubscriptionEntryWrapper
	extends BaseModelWrapper<SubscriptionEntry>
	implements ModelWrapper<SubscriptionEntry>, SubscriptionEntry {

	public SubscriptionEntryWrapper(SubscriptionEntry subscriptionEntry) {
		super(subscriptionEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("subscriptionEntryId", getSubscriptionEntryId());
		attributes.put("createDate", getCreateDate());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("contactUuid", getContactUuid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long subscriptionEntryId = (Long)attributes.get("subscriptionEntryId");

		if (subscriptionEntryId != null) {
			setSubscriptionEntryId(subscriptionEntryId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		String contactUuid = (String)attributes.get("contactUuid");

		if (contactUuid != null) {
			setContactUuid(contactUuid);
		}
	}

	/**
	 * Returns the fully qualified class name of this subscription entry.
	 *
	 * @return the fully qualified class name of this subscription entry
	 */
	@Override
	public String getClassName() {
		return model.getClassName();
	}

	/**
	 * Returns the class name ID of this subscription entry.
	 *
	 * @return the class name ID of this subscription entry
	 */
	@Override
	public long getClassNameId() {
		return model.getClassNameId();
	}

	/**
	 * Returns the class pk of this subscription entry.
	 *
	 * @return the class pk of this subscription entry
	 */
	@Override
	public long getClassPK() {
		return model.getClassPK();
	}

	/**
	 * Returns the contact uuid of this subscription entry.
	 *
	 * @return the contact uuid of this subscription entry
	 */
	@Override
	public String getContactUuid() {
		return model.getContactUuid();
	}

	/**
	 * Returns the create date of this subscription entry.
	 *
	 * @return the create date of this subscription entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the mvcc version of this subscription entry.
	 *
	 * @return the mvcc version of this subscription entry
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this subscription entry.
	 *
	 * @return the primary key of this subscription entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the subscription entry ID of this subscription entry.
	 *
	 * @return the subscription entry ID of this subscription entry
	 */
	@Override
	public long getSubscriptionEntryId() {
		return model.getSubscriptionEntryId();
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
	 * Sets the class name ID of this subscription entry.
	 *
	 * @param classNameId the class name ID of this subscription entry
	 */
	@Override
	public void setClassNameId(long classNameId) {
		model.setClassNameId(classNameId);
	}

	/**
	 * Sets the class pk of this subscription entry.
	 *
	 * @param classPK the class pk of this subscription entry
	 */
	@Override
	public void setClassPK(long classPK) {
		model.setClassPK(classPK);
	}

	/**
	 * Sets the contact uuid of this subscription entry.
	 *
	 * @param contactUuid the contact uuid of this subscription entry
	 */
	@Override
	public void setContactUuid(String contactUuid) {
		model.setContactUuid(contactUuid);
	}

	/**
	 * Sets the create date of this subscription entry.
	 *
	 * @param createDate the create date of this subscription entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the mvcc version of this subscription entry.
	 *
	 * @param mvccVersion the mvcc version of this subscription entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this subscription entry.
	 *
	 * @param primaryKey the primary key of this subscription entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the subscription entry ID of this subscription entry.
	 *
	 * @param subscriptionEntryId the subscription entry ID of this subscription entry
	 */
	@Override
	public void setSubscriptionEntryId(long subscriptionEntryId) {
		model.setSubscriptionEntryId(subscriptionEntryId);
	}

	@Override
	protected SubscriptionEntryWrapper wrap(
		SubscriptionEntry subscriptionEntry) {

		return new SubscriptionEntryWrapper(subscriptionEntry);
	}

}