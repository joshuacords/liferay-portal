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
 * This class is a wrapper for {@link Team}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Team
 * @generated
 */
public class TeamWrapper
	extends BaseModelWrapper<Team> implements ModelWrapper<Team>, Team {

	public TeamWrapper(Team team) {
		super(team);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("teamId", getTeamId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("teamKey", getTeamKey());
		attributes.put("accountId", getAccountId());
		attributes.put("name", getName());
		attributes.put("system", isSystem());

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

		Long teamId = (Long)attributes.get("teamId");

		if (teamId != null) {
			setTeamId(teamId);
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

		String teamKey = (String)attributes.get("teamKey");

		if (teamKey != null) {
			setTeamKey(teamKey);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Boolean system = (Boolean)attributes.get("system");

		if (system != null) {
			setSystem(system);
		}
	}

	@Override
	public Account getAccount()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getAccount();
	}

	/**
	 * Returns the account ID of this team.
	 *
	 * @return the account ID of this team
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	@Override
	public String getAccountKey()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getAccountKey();
	}

	/**
	 * Returns the company ID of this team.
	 *
	 * @return the company ID of this team
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this team.
	 *
	 * @return the create date of this team
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks() {

		return model.getExternalLinks();
	}

	/**
	 * Returns the modified date of this team.
	 *
	 * @return the modified date of this team
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this team.
	 *
	 * @return the mvcc version of this team
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this team.
	 *
	 * @return the name of this team
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this team.
	 *
	 * @return the primary key of this team
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the system of this team.
	 *
	 * @return the system of this team
	 */
	@Override
	public boolean getSystem() {
		return model.getSystem();
	}

	/**
	 * Returns the team ID of this team.
	 *
	 * @return the team ID of this team
	 */
	@Override
	public long getTeamId() {
		return model.getTeamId();
	}

	/**
	 * Returns the team key of this team.
	 *
	 * @return the team key of this team
	 */
	@Override
	public String getTeamKey() {
		return model.getTeamKey();
	}

	/**
	 * Returns the user ID of this team.
	 *
	 * @return the user ID of this team
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this team.
	 *
	 * @return the user uuid of this team
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this team.
	 *
	 * @return the uuid of this team
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns <code>true</code> if this team is system.
	 *
	 * @return <code>true</code> if this team is system; <code>false</code> otherwise
	 */
	@Override
	public boolean isSystem() {
		return model.isSystem();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account ID of this team.
	 *
	 * @param accountId the account ID of this team
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	@Override
	public void setAccountKey(String accountKey) {
		model.setAccountKey(accountKey);
	}

	/**
	 * Sets the company ID of this team.
	 *
	 * @param companyId the company ID of this team
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this team.
	 *
	 * @param createDate the create date of this team
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this team.
	 *
	 * @param modifiedDate the modified date of this team
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this team.
	 *
	 * @param mvccVersion the mvcc version of this team
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this team.
	 *
	 * @param name the name of this team
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this team.
	 *
	 * @param primaryKey the primary key of this team
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets whether this team is system.
	 *
	 * @param system the system of this team
	 */
	@Override
	public void setSystem(boolean system) {
		model.setSystem(system);
	}

	/**
	 * Sets the team ID of this team.
	 *
	 * @param teamId the team ID of this team
	 */
	@Override
	public void setTeamId(long teamId) {
		model.setTeamId(teamId);
	}

	/**
	 * Sets the team key of this team.
	 *
	 * @param teamKey the team key of this team
	 */
	@Override
	public void setTeamKey(String teamKey) {
		model.setTeamKey(teamKey);
	}

	/**
	 * Sets the user ID of this team.
	 *
	 * @param userId the user ID of this team
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this team.
	 *
	 * @param userUuid the user uuid of this team
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this team.
	 *
	 * @param uuid the uuid of this team
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
	protected TeamWrapper wrap(Team team) {
		return new TeamWrapper(team);
	}

}