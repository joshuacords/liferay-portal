/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AuthenticationToken}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AuthenticationToken
 * @generated
 */
public class AuthenticationTokenWrapper
	extends BaseModelWrapper<AuthenticationToken>
	implements AuthenticationToken, ModelWrapper<AuthenticationToken> {

	public AuthenticationTokenWrapper(AuthenticationToken authenticationToken) {
		super(authenticationToken);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("authenticationTokenId", getAuthenticationTokenId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("serviceProducerId", getServiceProducerId());
		attributes.put("name", getName());
		attributes.put("prefix", getPrefix());
		attributes.put("digest", getDigest());
		attributes.put("status", getStatus());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long authenticationTokenId = (Long)attributes.get(
			"authenticationTokenId");

		if (authenticationTokenId != null) {
			setAuthenticationTokenId(authenticationTokenId);
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

		Long serviceProducerId = (Long)attributes.get("serviceProducerId");

		if (serviceProducerId != null) {
			setServiceProducerId(serviceProducerId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String prefix = (String)attributes.get("prefix");

		if (prefix != null) {
			setPrefix(prefix);
		}

		String digest = (String)attributes.get("digest");

		if (digest != null) {
			setDigest(digest);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}
	}

	/**
	 * Returns the authentication token ID of this authentication token.
	 *
	 * @return the authentication token ID of this authentication token
	 */
	@Override
	public long getAuthenticationTokenId() {
		return model.getAuthenticationTokenId();
	}

	/**
	 * Returns the company ID of this authentication token.
	 *
	 * @return the company ID of this authentication token
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this authentication token.
	 *
	 * @return the create date of this authentication token
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the digest of this authentication token.
	 *
	 * @return the digest of this authentication token
	 */
	@Override
	public String getDigest() {
		return model.getDigest();
	}

	@Override
	public String getMaskedToken() {
		return model.getMaskedToken();
	}

	/**
	 * Returns the modified date of this authentication token.
	 *
	 * @return the modified date of this authentication token
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this authentication token.
	 *
	 * @return the mvcc version of this authentication token
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this authentication token.
	 *
	 * @return the name of this authentication token
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the prefix of this authentication token.
	 *
	 * @return the prefix of this authentication token
	 */
	@Override
	public String getPrefix() {
		return model.getPrefix();
	}

	/**
	 * Returns the primary key of this authentication token.
	 *
	 * @return the primary key of this authentication token
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the service producer ID of this authentication token.
	 *
	 * @return the service producer ID of this authentication token
	 */
	@Override
	public long getServiceProducerId() {
		return model.getServiceProducerId();
	}

	/**
	 * Returns the status of this authentication token.
	 *
	 * @return the status of this authentication token
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the user ID of this authentication token.
	 *
	 * @return the user ID of this authentication token
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this authentication token.
	 *
	 * @return the user uuid of this authentication token
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the authentication token ID of this authentication token.
	 *
	 * @param authenticationTokenId the authentication token ID of this authentication token
	 */
	@Override
	public void setAuthenticationTokenId(long authenticationTokenId) {
		model.setAuthenticationTokenId(authenticationTokenId);
	}

	/**
	 * Sets the company ID of this authentication token.
	 *
	 * @param companyId the company ID of this authentication token
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this authentication token.
	 *
	 * @param createDate the create date of this authentication token
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the digest of this authentication token.
	 *
	 * @param digest the digest of this authentication token
	 */
	@Override
	public void setDigest(String digest) {
		model.setDigest(digest);
	}

	/**
	 * Sets the modified date of this authentication token.
	 *
	 * @param modifiedDate the modified date of this authentication token
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this authentication token.
	 *
	 * @param mvccVersion the mvcc version of this authentication token
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this authentication token.
	 *
	 * @param name the name of this authentication token
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the prefix of this authentication token.
	 *
	 * @param prefix the prefix of this authentication token
	 */
	@Override
	public void setPrefix(String prefix) {
		model.setPrefix(prefix);
	}

	/**
	 * Sets the primary key of this authentication token.
	 *
	 * @param primaryKey the primary key of this authentication token
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the service producer ID of this authentication token.
	 *
	 * @param serviceProducerId the service producer ID of this authentication token
	 */
	@Override
	public void setServiceProducerId(long serviceProducerId) {
		model.setServiceProducerId(serviceProducerId);
	}

	/**
	 * Sets the status of this authentication token.
	 *
	 * @param status the status of this authentication token
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the user ID of this authentication token.
	 *
	 * @param userId the user ID of this authentication token
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this authentication token.
	 *
	 * @param userUuid the user uuid of this authentication token
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected AuthenticationTokenWrapper wrap(
		AuthenticationToken authenticationToken) {

		return new AuthenticationTokenWrapper(authenticationToken);
	}

}