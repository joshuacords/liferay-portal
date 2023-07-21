/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class OAuthConsumerSoap implements Serializable {

	public static OAuthConsumerSoap toSoapModel(OAuthConsumer model) {
		OAuthConsumerSoap soapModel = new OAuthConsumerSoap();

		soapModel.setOAuthConsumerId(model.getOAuthConsumerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setGadgetKey(model.getGadgetKey());
		soapModel.setServiceName(model.getServiceName());
		soapModel.setConsumerKey(model.getConsumerKey());
		soapModel.setConsumerSecret(model.getConsumerSecret());
		soapModel.setKeyType(model.getKeyType());

		return soapModel;
	}

	public static OAuthConsumerSoap[] toSoapModels(OAuthConsumer[] models) {
		OAuthConsumerSoap[] soapModels = new OAuthConsumerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OAuthConsumerSoap[][] toSoapModels(OAuthConsumer[][] models) {
		OAuthConsumerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OAuthConsumerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OAuthConsumerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OAuthConsumerSoap[] toSoapModels(List<OAuthConsumer> models) {
		List<OAuthConsumerSoap> soapModels = new ArrayList<OAuthConsumerSoap>(
			models.size());

		for (OAuthConsumer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OAuthConsumerSoap[soapModels.size()]);
	}

	public OAuthConsumerSoap() {
	}

	public long getPrimaryKey() {
		return _oAuthConsumerId;
	}

	public void setPrimaryKey(long pk) {
		setOAuthConsumerId(pk);
	}

	public long getOAuthConsumerId() {
		return _oAuthConsumerId;
	}

	public void setOAuthConsumerId(long oAuthConsumerId) {
		_oAuthConsumerId = oAuthConsumerId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getGadgetKey() {
		return _gadgetKey;
	}

	public void setGadgetKey(String gadgetKey) {
		_gadgetKey = gadgetKey;
	}

	public String getServiceName() {
		return _serviceName;
	}

	public void setServiceName(String serviceName) {
		_serviceName = serviceName;
	}

	public String getConsumerKey() {
		return _consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return _consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;
	}

	public String getKeyType() {
		return _keyType;
	}

	public void setKeyType(String keyType) {
		_keyType = keyType;
	}

	private long _oAuthConsumerId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _gadgetKey;
	private String _serviceName;
	private String _consumerKey;
	private String _consumerSecret;
	private String _keyType;

}