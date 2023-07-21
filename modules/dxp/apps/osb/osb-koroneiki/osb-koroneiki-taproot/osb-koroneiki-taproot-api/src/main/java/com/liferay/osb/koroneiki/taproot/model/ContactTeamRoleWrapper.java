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
 * This class is a wrapper for {@link ContactTeamRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContactTeamRole
 * @generated
 */
public class ContactTeamRoleWrapper
	extends BaseModelWrapper<ContactTeamRole>
	implements ContactTeamRole, ModelWrapper<ContactTeamRole> {

	public ContactTeamRoleWrapper(ContactTeamRole contactTeamRole) {
		super(contactTeamRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("contactId", getContactId());
		attributes.put("teamId", getTeamId());
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

		Long teamId = (Long)attributes.get("teamId");

		if (teamId != null) {
			setTeamId(teamId);
		}

		Long contactRoleId = (Long)attributes.get("contactRoleId");

		if (contactRoleId != null) {
			setContactRoleId(contactRoleId);
		}
	}

	@Override
	public Contact getContact()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getContact();
	}

	/**
	 * Returns the contact ID of this contact team role.
	 *
	 * @return the contact ID of this contact team role
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
	 * Returns the contact role ID of this contact team role.
	 *
	 * @return the contact role ID of this contact team role
	 */
	@Override
	public long getContactRoleId() {
		return model.getContactRoleId();
	}

	/**
	 * Returns the mvcc version of this contact team role.
	 *
	 * @return the mvcc version of this contact team role
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this contact team role.
	 *
	 * @return the primary key of this contact team role
	 */
	@Override
	public
		com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK
			getPrimaryKey() {

		return model.getPrimaryKey();
	}

	@Override
	public Team getTeam()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getTeam();
	}

	/**
	 * Returns the team ID of this contact team role.
	 *
	 * @return the team ID of this contact team role
	 */
	@Override
	public long getTeamId() {
		return model.getTeamId();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the contact ID of this contact team role.
	 *
	 * @param contactId the contact ID of this contact team role
	 */
	@Override
	public void setContactId(long contactId) {
		model.setContactId(contactId);
	}

	/**
	 * Sets the contact role ID of this contact team role.
	 *
	 * @param contactRoleId the contact role ID of this contact team role
	 */
	@Override
	public void setContactRoleId(long contactRoleId) {
		model.setContactRoleId(contactRoleId);
	}

	/**
	 * Sets the mvcc version of this contact team role.
	 *
	 * @param mvccVersion the mvcc version of this contact team role
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this contact team role.
	 *
	 * @param primaryKey the primary key of this contact team role
	 */
	@Override
	public void setPrimaryKey(
		com.liferay.osb.koroneiki.taproot.service.persistence.ContactTeamRolePK
			primaryKey) {

		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the team ID of this contact team role.
	 *
	 * @param teamId the team ID of this contact team role
	 */
	@Override
	public void setTeamId(long teamId) {
		model.setTeamId(teamId);
	}

	@Override
	protected ContactTeamRoleWrapper wrap(ContactTeamRole contactTeamRole) {
		return new ContactTeamRoleWrapper(contactTeamRole);
	}

}