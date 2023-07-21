/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.scion.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.koroneiki.scion.service.http.ServiceProducerServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ServiceProducerSoap implements Serializable {

	public static ServiceProducerSoap toSoapModel(ServiceProducer model) {
		ServiceProducerSoap soapModel = new ServiceProducerSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUuid(model.getUuid());
		soapModel.setServiceProducerId(model.getServiceProducerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAuthorizationUserId(model.getAuthorizationUserId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static ServiceProducerSoap[] toSoapModels(ServiceProducer[] models) {
		ServiceProducerSoap[] soapModels =
			new ServiceProducerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceProducerSoap[][] toSoapModels(
		ServiceProducer[][] models) {

		ServiceProducerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new ServiceProducerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceProducerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceProducerSoap[] toSoapModels(
		List<ServiceProducer> models) {

		List<ServiceProducerSoap> soapModels =
			new ArrayList<ServiceProducerSoap>(models.size());

		for (ServiceProducer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceProducerSoap[soapModels.size()]);
	}

	public ServiceProducerSoap() {
	}

	public long getPrimaryKey() {
		return _serviceProducerId;
	}

	public void setPrimaryKey(long pk) {
		setServiceProducerId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getServiceProducerId() {
		return _serviceProducerId;
	}

	public void setServiceProducerId(long serviceProducerId) {
		_serviceProducerId = serviceProducerId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public long getAuthorizationUserId() {
		return _authorizationUserId;
	}

	public void setAuthorizationUserId(long authorizationUserId) {
		_authorizationUserId = authorizationUserId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _mvccVersion;
	private String _uuid;
	private long _serviceProducerId;
	private long _companyId;
	private long _userId;
	private long _authorizationUserId;
	private String _name;
	private String _description;

}