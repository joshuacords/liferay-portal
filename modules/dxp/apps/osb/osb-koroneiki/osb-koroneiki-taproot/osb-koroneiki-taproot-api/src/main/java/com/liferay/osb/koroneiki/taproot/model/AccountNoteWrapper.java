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
 * This class is a wrapper for {@link AccountNote}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountNote
 * @generated
 */
public class AccountNoteWrapper
	extends BaseModelWrapper<AccountNote>
	implements AccountNote, ModelWrapper<AccountNote> {

	public AccountNoteWrapper(AccountNote accountNote) {
		super(accountNote);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("accountNoteId", getAccountNoteId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("creatorUID", getCreatorUID());
		attributes.put("creatorName", getCreatorName());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("modifierUID", getModifierUID());
		attributes.put("modifierName", getModifierName());
		attributes.put("accountNoteKey", getAccountNoteKey());
		attributes.put("accountId", getAccountId());
		attributes.put("type", getType());
		attributes.put("priority", getPriority());
		attributes.put("content", getContent());
		attributes.put("format", getFormat());
		attributes.put("status", getStatus());

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

		Long accountNoteId = (Long)attributes.get("accountNoteId");

		if (accountNoteId != null) {
			setAccountNoteId(accountNoteId);
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

		String creatorUID = (String)attributes.get("creatorUID");

		if (creatorUID != null) {
			setCreatorUID(creatorUID);
		}

		String creatorName = (String)attributes.get("creatorName");

		if (creatorName != null) {
			setCreatorName(creatorName);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String modifierUID = (String)attributes.get("modifierUID");

		if (modifierUID != null) {
			setModifierUID(modifierUID);
		}

		String modifierName = (String)attributes.get("modifierName");

		if (modifierName != null) {
			setModifierName(modifierName);
		}

		String accountNoteKey = (String)attributes.get("accountNoteKey");

		if (accountNoteKey != null) {
			setAccountNoteKey(accountNoteKey);
		}

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer priority = (Integer)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		String format = (String)attributes.get("format");

		if (format != null) {
			setFormat(format);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	 * Returns the account ID of this account note.
	 *
	 * @return the account ID of this account note
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	/**
	 * Returns the account note ID of this account note.
	 *
	 * @return the account note ID of this account note
	 */
	@Override
	public long getAccountNoteId() {
		return model.getAccountNoteId();
	}

	/**
	 * Returns the account note key of this account note.
	 *
	 * @return the account note key of this account note
	 */
	@Override
	public String getAccountNoteKey() {
		return model.getAccountNoteKey();
	}

	/**
	 * Returns the company ID of this account note.
	 *
	 * @return the company ID of this account note
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the content of this account note.
	 *
	 * @return the content of this account note
	 */
	@Override
	public String getContent() {
		return model.getContent();
	}

	/**
	 * Returns the create date of this account note.
	 *
	 * @return the create date of this account note
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the creator name of this account note.
	 *
	 * @return the creator name of this account note
	 */
	@Override
	public String getCreatorName() {
		return model.getCreatorName();
	}

	/**
	 * Returns the creator uid of this account note.
	 *
	 * @return the creator uid of this account note
	 */
	@Override
	public String getCreatorUID() {
		return model.getCreatorUID();
	}

	/**
	 * Returns the format of this account note.
	 *
	 * @return the format of this account note
	 */
	@Override
	public String getFormat() {
		return model.getFormat();
	}

	/**
	 * Returns the modified date of this account note.
	 *
	 * @return the modified date of this account note
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the modifier name of this account note.
	 *
	 * @return the modifier name of this account note
	 */
	@Override
	public String getModifierName() {
		return model.getModifierName();
	}

	/**
	 * Returns the modifier uid of this account note.
	 *
	 * @return the modifier uid of this account note
	 */
	@Override
	public String getModifierUID() {
		return model.getModifierUID();
	}

	/**
	 * Returns the mvcc version of this account note.
	 *
	 * @return the mvcc version of this account note
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this account note.
	 *
	 * @return the primary key of this account note
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the priority of this account note.
	 *
	 * @return the priority of this account note
	 */
	@Override
	public int getPriority() {
		return model.getPriority();
	}

	/**
	 * Returns the status of this account note.
	 *
	 * @return the status of this account note
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the type of this account note.
	 *
	 * @return the type of this account note
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this account note.
	 *
	 * @return the user ID of this account note
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this account note.
	 *
	 * @return the user uuid of this account note
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this account note.
	 *
	 * @return the uuid of this account note
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
	 * Sets the account ID of this account note.
	 *
	 * @param accountId the account ID of this account note
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	/**
	 * Sets the account note ID of this account note.
	 *
	 * @param accountNoteId the account note ID of this account note
	 */
	@Override
	public void setAccountNoteId(long accountNoteId) {
		model.setAccountNoteId(accountNoteId);
	}

	/**
	 * Sets the account note key of this account note.
	 *
	 * @param accountNoteKey the account note key of this account note
	 */
	@Override
	public void setAccountNoteKey(String accountNoteKey) {
		model.setAccountNoteKey(accountNoteKey);
	}

	/**
	 * Sets the company ID of this account note.
	 *
	 * @param companyId the company ID of this account note
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the content of this account note.
	 *
	 * @param content the content of this account note
	 */
	@Override
	public void setContent(String content) {
		model.setContent(content);
	}

	/**
	 * Sets the create date of this account note.
	 *
	 * @param createDate the create date of this account note
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the creator name of this account note.
	 *
	 * @param creatorName the creator name of this account note
	 */
	@Override
	public void setCreatorName(String creatorName) {
		model.setCreatorName(creatorName);
	}

	/**
	 * Sets the creator uid of this account note.
	 *
	 * @param creatorUID the creator uid of this account note
	 */
	@Override
	public void setCreatorUID(String creatorUID) {
		model.setCreatorUID(creatorUID);
	}

	/**
	 * Sets the format of this account note.
	 *
	 * @param format the format of this account note
	 */
	@Override
	public void setFormat(String format) {
		model.setFormat(format);
	}

	/**
	 * Sets the modified date of this account note.
	 *
	 * @param modifiedDate the modified date of this account note
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the modifier name of this account note.
	 *
	 * @param modifierName the modifier name of this account note
	 */
	@Override
	public void setModifierName(String modifierName) {
		model.setModifierName(modifierName);
	}

	/**
	 * Sets the modifier uid of this account note.
	 *
	 * @param modifierUID the modifier uid of this account note
	 */
	@Override
	public void setModifierUID(String modifierUID) {
		model.setModifierUID(modifierUID);
	}

	/**
	 * Sets the mvcc version of this account note.
	 *
	 * @param mvccVersion the mvcc version of this account note
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this account note.
	 *
	 * @param primaryKey the primary key of this account note
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the priority of this account note.
	 *
	 * @param priority the priority of this account note
	 */
	@Override
	public void setPriority(int priority) {
		model.setPriority(priority);
	}

	/**
	 * Sets the status of this account note.
	 *
	 * @param status the status of this account note
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the type of this account note.
	 *
	 * @param type the type of this account note
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this account note.
	 *
	 * @param userId the user ID of this account note
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this account note.
	 *
	 * @param userUuid the user uuid of this account note
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this account note.
	 *
	 * @param uuid the uuid of this account note
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
	protected AccountNoteWrapper wrap(AccountNote accountNote) {
		return new AccountNoteWrapper(accountNote);
	}

}