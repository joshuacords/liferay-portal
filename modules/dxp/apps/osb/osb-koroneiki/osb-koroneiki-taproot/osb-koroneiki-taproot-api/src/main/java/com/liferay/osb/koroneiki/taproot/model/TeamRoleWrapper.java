/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TeamRole}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TeamRole
 * @generated
 */
public class TeamRoleWrapper
	extends BaseModelWrapper<TeamRole>
	implements ModelWrapper<TeamRole>, TeamRole {

	public TeamRoleWrapper(TeamRole teamRole) {
		super(teamRole);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("teamRoleId", getTeamRoleId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("teamRoleKey", getTeamRoleKey());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("type", getType());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long teamRoleId = (Long)attributes.get("teamRoleId");

		if (teamRoleId != null) {
			setTeamRoleId(teamRoleId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String teamRoleKey = (String)attributes.get("teamRoleKey");

		if (teamRoleKey != null) {
			setTeamRoleKey(teamRoleKey);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}
	}

	/**
	 * Returns the company ID of this team role.
	 *
	 * @return the company ID of this team role
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this team role.
	 *
	 * @return the create date of this team role
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the description of this team role.
	 *
	 * @return the description of this team role
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the modified date of this team role.
	 *
	 * @return the modified date of this team role
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this team role.
	 *
	 * @return the mvcc version of this team role
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this team role.
	 *
	 * @return the name of this team role
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this team role.
	 *
	 * @return the primary key of this team role
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the team role ID of this team role.
	 *
	 * @return the team role ID of this team role
	 */
	@Override
	public long getTeamRoleId() {
		return model.getTeamRoleId();
	}

	/**
	 * Returns the team role key of this team role.
	 *
	 * @return the team role key of this team role
	 */
	@Override
	public String getTeamRoleKey() {
		return model.getTeamRoleKey();
	}

	/**
	 * Returns the type of this team role.
	 *
	 * @return the type of this team role
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this team role.
	 *
	 * @return the user ID of this team role
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this team role.
	 *
	 * @return the user uuid of this team role
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this team role.
	 *
	 * @return the uuid of this team role
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this team role.
	 *
	 * @param companyId the company ID of this team role
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this team role.
	 *
	 * @param createDate the create date of this team role
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this team role.
	 *
	 * @param description the description of this team role
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the modified date of this team role.
	 *
	 * @param modifiedDate the modified date of this team role
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this team role.
	 *
	 * @param mvccVersion the mvcc version of this team role
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this team role.
	 *
	 * @param name the name of this team role
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this team role.
	 *
	 * @param primaryKey the primary key of this team role
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the team role ID of this team role.
	 *
	 * @param teamRoleId the team role ID of this team role
	 */
	@Override
	public void setTeamRoleId(long teamRoleId) {
		model.setTeamRoleId(teamRoleId);
	}

	/**
	 * Sets the team role key of this team role.
	 *
	 * @param teamRoleKey the team role key of this team role
	 */
	@Override
	public void setTeamRoleKey(String teamRoleKey) {
		model.setTeamRoleKey(teamRoleKey);
	}

	/**
	 * Sets the type of this team role.
	 *
	 * @param type the type of this team role
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this team role.
	 *
	 * @param userId the user ID of this team role
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this team role.
	 *
	 * @param userUuid the user uuid of this team role
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this team role.
	 *
	 * @param uuid the uuid of this team role
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected TeamRoleWrapper wrap(TeamRole teamRole) {
		return new TeamRoleWrapper(teamRole);
	}

}