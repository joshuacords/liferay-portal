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
 * This class is a wrapper for {@link ContactAccountRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactAccountRole
 * @generated
 */
public class ContactAccountRoleWrapper
	extends BaseModelWrapper<ContactAccountRole>
	implements ContactAccountRole, ModelWrapper<ContactAccountRole> {

	public ContactAccountRoleWrapper(ContactAccountRole contactAccountRole) {
		super(contactAccountRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("contactId", getContactId());
		attributes.put("accountId", getAccountId());
		attributes.put("contactRoleId", getContactRoleId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long contactId = (Long)attributes.get("contactId");

		if (contactId != null) {
			setContactId(contactId);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Long contactRoleId = (Long)attributes.get("contactRoleId");

		if (contactRoleId != null) {
			setContactRoleId(contactRoleId);
		}
	}

	@Override
	public Account getAccount()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getAccount();
	}

	/**
	 * Returns the account ID of this contact account role.
	 *
	 * @return the account ID of this contact account role
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	@Override
	public Contact getContact()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getContact();
	}

	/**
	 * Returns the contact ID of this contact account role.
	 *
	 * @return the contact ID of this contact account role
	 */
	@Override
	public long getContactId() {
		return model.getContactId();
	}

	@Override
	public ContactRole getContactRole()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getContactRole();
	}

	/**
	 * Returns the contact role ID of this contact account role.
	 *
	 * @return the contact role ID of this contact account role
	 */
	@Override
	public long getContactRoleId() {
		return model.getContactRoleId();
	}

	/**
	 * Returns the mvcc version of this contact account role.
	 *
	 * @return the mvcc version of this contact account role
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this contact account role.
	 *
	 * @return the primary key of this contact account role
	 */
	@Override
	public
		com.liferay.osb.koroneiki.taproot.service.persistence.
			ContactAccountRolePK getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account ID of this contact account role.
	 *
	 * @param accountId the account ID of this contact account role
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	/**
	 * Sets the contact ID of this contact account role.
	 *
	 * @param contactId the contact ID of this contact account role
	 */
	@Override
	public void setContactId(long contactId) {
		model.setContactId(contactId);
	}

	/**
	 * Sets the contact role ID of this contact account role.
	 *
	 * @param contactRoleId the contact role ID of this contact account role
	 */
	@Override
	public void setContactRoleId(long contactRoleId) {
		model.setContactRoleId(contactRoleId);
	}

	/**
	 * Sets the mvcc version of this contact account role.
	 *
	 * @param mvccVersion the mvcc version of this contact account role
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this contact account role.
	 *
	 * @param primaryKey the primary key of this contact account role
	 */
	@Override
	public void setPrimaryKey(
		com.liferay.osb.koroneiki.taproot.service.persistence.
			ContactAccountRolePK primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	@Override
	protected ContactAccountRoleWrapper wrap(
		ContactAccountRole contactAccountRole) {

		return new ContactAccountRoleWrapper(contactAccountRole);
	}

}