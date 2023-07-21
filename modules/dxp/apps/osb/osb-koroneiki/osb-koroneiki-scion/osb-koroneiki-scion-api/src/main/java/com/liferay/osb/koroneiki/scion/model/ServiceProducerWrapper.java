/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ServiceProducer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ServiceProducer
 * @generated
 */
public class ServiceProducerWrapper
	extends BaseModelWrapper<ServiceProducer>
	implements ModelWrapper<ServiceProducer>, ServiceProducer {

	public ServiceProducerWrapper(ServiceProducer serviceProducer) {
		super(serviceProducer);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("serviceProducerId", getServiceProducerId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("authorizationUserId", getAuthorizationUserId());
		attributes.put("name", getName());
		attributes.put("description", getDescription());

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

		Long serviceProducerId = (Long)attributes.get("serviceProducerId");

		if (serviceProducerId != null) {
			setServiceProducerId(serviceProducerId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long authorizationUserId = (Long)attributes.get("authorizationUserId");

		if (authorizationUserId != null) {
			setAuthorizationUserId(authorizationUserId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	 * Returns the authorization user ID of this service producer.
	 *
	 * @return the authorization user ID of this service producer
	 */
	@Override
	public long getAuthorizationUserId() {
		return model.getAuthorizationUserId();
	}

	/**
	 * Returns the authorization user uuid of this service producer.
	 *
	 * @return the authorization user uuid of this service producer
	 */
	@Override
	public String getAuthorizationUserUuid() {
		return model.getAuthorizationUserUuid();
	}

	/**
	 * Returns the company ID of this service producer.
	 *
	 * @return the company ID of this service producer
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the description of this service producer.
	 *
	 * @return the description of this service producer
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the mvcc version of this service producer.
	 *
	 * @return the mvcc version of this service producer
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this service producer.
	 *
	 * @return the name of this service producer
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this service producer.
	 *
	 * @return the primary key of this service producer
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the service producer ID of this service producer.
	 *
	 * @return the service producer ID of this service producer
	 */
	@Override
	public long getServiceProducerId() {
		return model.getServiceProducerId();
	}

	/**
	 * Returns the user ID of this service producer.
	 *
	 * @return the user ID of this service producer
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user uuid of this service producer.
	 *
	 * @return the user uuid of this service producer
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the uuid of this service producer.
	 *
	 * @return the uuid of this service producer
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
	 * Sets the authorization user ID of this service producer.
	 *
	 * @param authorizationUserId the authorization user ID of this service producer
	 */
	@Override
	public void setAuthorizationUserId(long authorizationUserId) {
		model.setAuthorizationUserId(authorizationUserId);
	}

	/**
	 * Sets the authorization user uuid of this service producer.
	 *
	 * @param authorizationUserUuid the authorization user uuid of this service producer
	 */
	@Override
	public void setAuthorizationUserUuid(String authorizationUserUuid) {
		model.setAuthorizationUserUuid(authorizationUserUuid);
	}

	/**
	 * Sets the company ID of this service producer.
	 *
	 * @param companyId the company ID of this service producer
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the description of this service producer.
	 *
	 * @param description the description of this service producer
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the mvcc version of this service producer.
	 *
	 * @param mvccVersion the mvcc version of this service producer
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this service producer.
	 *
	 * @param name the name of this service producer
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this service producer.
	 *
	 * @param primaryKey the primary key of this service producer
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the service producer ID of this service producer.
	 *
	 * @param serviceProducerId the service producer ID of this service producer
	 */
	@Override
	public void setServiceProducerId(long serviceProducerId) {
		model.setServiceProducerId(serviceProducerId);
	}

	/**
	 * Sets the user ID of this service producer.
	 *
	 * @param userId the user ID of this service producer
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user uuid of this service producer.
	 *
	 * @param userUuid the user uuid of this service producer
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the uuid of this service producer.
	 *
	 * @param uuid the uuid of this service producer
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	protected ServiceProducerWrapper wrap(ServiceProducer serviceProducer) {
		return new ServiceProducerWrapper(serviceProducer);
	}

}