/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AccountField}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountField
 * @generated
 */
public class AccountFieldWrapper
	extends BaseModelWrapper<AccountField>
	implements AccountField, ModelWrapper<AccountField> {

	public AccountFieldWrapper(AccountField accountField) {
		super(accountField);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("accountFieldId", getAccountFieldId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("accountId", getAccountId());
		attributes.put("name", getName());
		attributes.put("value", getValue());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long accountFieldId = (Long)attributes.get("accountFieldId");

		if (accountFieldId != null) {
			setAccountFieldId(accountFieldId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String value = (String)attributes.get("value");

		if (value != null) {
			setValue(value);
		}
	}

	/**
	 * Returns the account field ID of this account field.
	 *
	 * @return the account field ID of this account field
	 */
	@Override
	public long getAccountFieldId() {
		return model.getAccountFieldId();
	}

	/**
	 * Returns the account ID of this account field.
	 *
	 * @return the account ID of this account field
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	/**
	 * Returns the company ID of this account field.
	 *
	 * @return the company ID of this account field
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the mvcc version of this account field.
	 *
	 * @return the mvcc version of this account field
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this account field.
	 *
	 * @return the name of this account field
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this account field.
	 *
	 * @return the primary key of this account field
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the user ID of this account field.
	 *
	 * @return the user ID of this account field
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this account field.
	 *
	 * @return the user uuid of this account field
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the value of this account field.
	 *
	 * @return the value of this account field
	 */
	@Override
	public String getValue() {
		return model.getValue();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account field ID of this account field.
	 *
	 * @param accountFieldId the account field ID of this account field
	 */
	@Override
	public void setAccountFieldId(long accountFieldId) {
		model.setAccountFieldId(accountFieldId);
	}

	/**
	 * Sets the account ID of this account field.
	 *
	 * @param accountId the account ID of this account field
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	/**
	 * Sets the company ID of this account field.
	 *
	 * @param companyId the company ID of this account field
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the mvcc version of this account field.
	 *
	 * @param mvccVersion the mvcc version of this account field
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this account field.
	 *
	 * @param name the name of this account field
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this account field.
	 *
	 * @param primaryKey the primary key of this account field
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the user ID of this account field.
	 *
	 * @param userId the user ID of this account field
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this account field.
	 *
	 * @param userUuid the user uuid of this account field
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the value of this account field.
	 *
	 * @param value the value of this account field
	 */
	@Override
	public void setValue(String value) {
		model.setValue(value);
	}

	@Override
	protected AccountFieldWrapper wrap(AccountField accountField) {
		return new AccountFieldWrapper(accountField);
	}

}