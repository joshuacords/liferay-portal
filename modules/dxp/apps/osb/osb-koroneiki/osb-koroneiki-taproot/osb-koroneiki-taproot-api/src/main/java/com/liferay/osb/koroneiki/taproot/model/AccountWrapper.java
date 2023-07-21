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
 * This class is a wrapper for {@link Account}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Account
 * @generated
 */
public class AccountWrapper
	extends BaseModelWrapper<Account>
	implements Account, ModelWrapper<Account> {

	public AccountWrapper(Account account) {
		super(account);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("accountId", getAccountId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("accountKey", getAccountKey());
		attributes.put("parentAccountId", getParentAccountId());
		attributes.put("name", getName());
		attributes.put("code", getCode());
		attributes.put("description", getDescription());
		attributes.put("logoId", getLogoId());
		attributes.put("contactEmailAddress", getContactEmailAddress());
		attributes.put("profileEmailAddress", getProfileEmailAddress());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("faxNumber", getFaxNumber());
		attributes.put("website", getWebsite());
		attributes.put("tier", getTier());
		attributes.put("region", getRegion());
		attributes.put("dataRegion", getDataRegion());
		attributes.put("language", getLanguage());
		attributes.put("internal", isInternal());
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

		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
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

		String accountKey = (String)attributes.get("accountKey");

		if (accountKey != null) {
			setAccountKey(accountKey);
		}

		Long parentAccountId = (Long)attributes.get("parentAccountId");

		if (parentAccountId != null) {
			setParentAccountId(parentAccountId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String code = (String)attributes.get("code");

		if (code != null) {
			setCode(code);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Long logoId = (Long)attributes.get("logoId");

		if (logoId != null) {
			setLogoId(logoId);
		}

		String contactEmailAddress = (String)attributes.get(
			"contactEmailAddress");

		if (contactEmailAddress != null) {
			setContactEmailAddress(contactEmailAddress);
		}

		String profileEmailAddress = (String)attributes.get(
			"profileEmailAddress");

		if (profileEmailAddress != null) {
			setProfileEmailAddress(profileEmailAddress);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		String faxNumber = (String)attributes.get("faxNumber");

		if (faxNumber != null) {
			setFaxNumber(faxNumber);
		}

		String website = (String)attributes.get("website");

		if (website != null) {
			setWebsite(website);
		}

		String tier = (String)attributes.get("tier");

		if (tier != null) {
			setTier(tier);
		}

		String region = (String)attributes.get("region");

		if (region != null) {
			setRegion(region);
		}

		String dataRegion = (String)attributes.get("dataRegion");

		if (dataRegion != null) {
			setDataRegion(dataRegion);
		}

		String language = (String)attributes.get("language");

		if (language != null) {
			setLanguage(language);
		}

		Boolean internal = (Boolean)attributes.get("internal");

		if (internal != null) {
			setInternal(internal);
		}

		String status = (String)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	@Override
	public java.util.List<AccountField> getAccountFields() {
		return model.getAccountFields();
	}

	@Override
	public Map<String, String> getAccountFieldsMap() {
		return model.getAccountFieldsMap();
	}

	/**
	 * Returns the account ID of this account.
	 *
	 * @return the account ID of this account
	 */
	@Override
	public long getAccountId() {
		return model.getAccountId();
	}

	/**
	 * Returns the account key of this account.
	 *
	 * @return the account key of this account
	 */
	@Override
	public String getAccountKey() {
		return model.getAccountKey();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Address>
		getAddresses() {

		return model.getAddresses();
	}

	@Override
	public java.util.List<Team> getAssignedTeams() {
		return model.getAssignedTeams();
	}

	@Override
	public java.util.List<Account> getChildAccounts()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getChildAccounts();
	}

	/**
	 * Returns the code of this account.
	 *
	 * @return the code of this account
	 */
	@Override
	public String getCode() {
		return model.getCode();
	}

	/**
	 * Returns the company ID of this account.
	 *
	 * @return the company ID of this account
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the contact email address of this account.
	 *
	 * @return the contact email address of this account
	 */
	@Override
	public String getContactEmailAddress() {
		return model.getContactEmailAddress();
	}

	/**
	 * Returns the create date of this account.
	 *
	 * @return the create date of this account
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the data region of this account.
	 *
	 * @return the data region of this account
	 */
	@Override
	public String getDataRegion() {
		return model.getDataRegion();
	}

	/**
	 * Returns the description of this account.
	 *
	 * @return the description of this account
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	@Override
	public java.util.List
		<com.liferay.osb.koroneiki.phytohormone.model.Entitlement>
			getEntitlements() {

		return model.getEntitlements();
	}

	@Override
	public java.util.List<com.liferay.osb.koroneiki.root.model.ExternalLink>
		getExternalLinks() {

		return model.getExternalLinks();
	}

	/**
	 * Returns the fax number of this account.
	 *
	 * @return the fax number of this account
	 */
	@Override
	public String getFaxNumber() {
		return model.getFaxNumber();
	}

	/**
	 * Returns the internal of this account.
	 *
	 * @return the internal of this account
	 */
	@Override
	public boolean getInternal() {
		return model.getInternal();
	}

	/**
	 * Returns the language of this account.
	 *
	 * @return the language of this account
	 */
	@Override
	public String getLanguage() {
		return model.getLanguage();
	}

	/**
	 * Returns the logo ID of this account.
	 *
	 * @return the logo ID of this account
	 */
	@Override
	public long getLogoId() {
		return model.getLogoId();
	}

	/**
	 * Returns the modified date of this account.
	 *
	 * @return the modified date of this account
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this account.
	 *
	 * @return the mvcc version of this account
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this account.
	 *
	 * @return the name of this account
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	@Override
	public Account getParentAccount()
		throws com.liferay.portal.kernel.exception.PortalException {

		return model.getParentAccount();
	}

	/**
	 * Returns the parent account ID of this account.
	 *
	 * @return the parent account ID of this account
	 */
	@Override
	public long getParentAccountId() {
		return model.getParentAccountId();
	}

	/**
	 * Returns the phone number of this account.
	 *
	 * @return the phone number of this account
	 */
	@Override
	public String getPhoneNumber() {
		return model.getPhoneNumber();
	}

	/**
	 * Returns the primary key of this account.
	 *
	 * @return the primary key of this account
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the profile email address of this account.
	 *
	 * @return the profile email address of this account
	 */
	@Override
	public String getProfileEmailAddress() {
		return model.getProfileEmailAddress();
	}

	/**
	 * Returns the region of this account.
	 *
	 * @return the region of this account
	 */
	@Override
	public String getRegion() {
		return model.getRegion();
	}

	/**
	 * Returns the status of this account.
	 *
	 * @return the status of this account
	 */
	@Override
	public String getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the tier of this account.
	 *
	 * @return the tier of this account
	 */
	@Override
	public String getTier() {
		return model.getTier();
	}

	/**
	 * Returns the user ID of this account.
	 *
	 * @return the user ID of this account
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this account.
	 *
	 * @return the user uuid of this account
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this account.
	 *
	 * @return the uuid of this account
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	/**
	 * Returns the website of this account.
	 *
	 * @return the website of this account
	 */
	@Override
	public String getWebsite() {
		return model.getWebsite();
	}

	/**
	 * Returns <code>true</code> if this account is internal.
	 *
	 * @return <code>true</code> if this account is internal; <code>false</code> otherwise
	 */
	@Override
	public boolean isInternal() {
		return model.isInternal();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the account ID of this account.
	 *
	 * @param accountId the account ID of this account
	 */
	@Override
	public void setAccountId(long accountId) {
		model.setAccountId(accountId);
	}

	/**
	 * Sets the account key of this account.
	 *
	 * @param accountKey the account key of this account
	 */
	@Override
	public void setAccountKey(String accountKey) {
		model.setAccountKey(accountKey);
	}

	/**
	 * Sets the code of this account.
	 *
	 * @param code the code of this account
	 */
	@Override
	public void setCode(String code) {
		model.setCode(code);
	}

	/**
	 * Sets the company ID of this account.
	 *
	 * @param companyId the company ID of this account
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the contact email address of this account.
	 *
	 * @param contactEmailAddress the contact email address of this account
	 */
	@Override
	public void setContactEmailAddress(String contactEmailAddress) {
		model.setContactEmailAddress(contactEmailAddress);
	}

	/**
	 * Sets the create date of this account.
	 *
	 * @param createDate the create date of this account
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the data region of this account.
	 *
	 * @param dataRegion the data region of this account
	 */
	@Override
	public void setDataRegion(String dataRegion) {
		model.setDataRegion(dataRegion);
	}

	/**
	 * Sets the description of this account.
	 *
	 * @param description the description of this account
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the fax number of this account.
	 *
	 * @param faxNumber the fax number of this account
	 */
	@Override
	public void setFaxNumber(String faxNumber) {
		model.setFaxNumber(faxNumber);
	}

	/**
	 * Sets whether this account is internal.
	 *
	 * @param internal the internal of this account
	 */
	@Override
	public void setInternal(boolean internal) {
		model.setInternal(internal);
	}

	/**
	 * Sets the language of this account.
	 *
	 * @param language the language of this account
	 */
	@Override
	public void setLanguage(String language) {
		model.setLanguage(language);
	}

	/**
	 * Sets the logo ID of this account.
	 *
	 * @param logoId the logo ID of this account
	 */
	@Override
	public void setLogoId(long logoId) {
		model.setLogoId(logoId);
	}

	/**
	 * Sets the modified date of this account.
	 *
	 * @param modifiedDate the modified date of this account
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this account.
	 *
	 * @param mvccVersion the mvcc version of this account
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this account.
	 *
	 * @param name the name of this account
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the parent account ID of this account.
	 *
	 * @param parentAccountId the parent account ID of this account
	 */
	@Override
	public void setParentAccountId(long parentAccountId) {
		model.setParentAccountId(parentAccountId);
	}

	/**
	 * Sets the phone number of this account.
	 *
	 * @param phoneNumber the phone number of this account
	 */
	@Override
	public void setPhoneNumber(String phoneNumber) {
		model.setPhoneNumber(phoneNumber);
	}

	/**
	 * Sets the primary key of this account.
	 *
	 * @param primaryKey the primary key of this account
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the profile email address of this account.
	 *
	 * @param profileEmailAddress the profile email address of this account
	 */
	@Override
	public void setProfileEmailAddress(String profileEmailAddress) {
		model.setProfileEmailAddress(profileEmailAddress);
	}

	/**
	 * Sets the region of this account.
	 *
	 * @param region the region of this account
	 */
	@Override
	public void setRegion(String region) {
		model.setRegion(region);
	}

	/**
	 * Sets the status of this account.
	 *
	 * @param status the status of this account
	 */
	@Override
	public void setStatus(String status) {
		model.setStatus(status);
	}

	/**
	 * Sets the tier of this account.
	 *
	 * @param tier the tier of this account
	 */
	@Override
	public void setTier(String tier) {
		model.setTier(tier);
	}

	/**
	 * Sets the user ID of this account.
	 *
	 * @param userId the user ID of this account
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this account.
	 *
	 * @param userUuid the user uuid of this account
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this account.
	 *
	 * @param uuid the uuid of this account
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	/**
	 * Sets the website of this account.
	 *
	 * @param website the website of this account
	 */
	@Override
	public void setWebsite(String website) {
		model.setWebsite(website);
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected AccountWrapper wrap(Account account) {
		return new AccountWrapper(account);
	}

}