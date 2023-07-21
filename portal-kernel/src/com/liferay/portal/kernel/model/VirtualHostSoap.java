/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class VirtualHostSoap implements Serializable {

	public static VirtualHostSoap toSoapModel(VirtualHost model) {
		VirtualHostSoap soapModel = new VirtualHostSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setVirtualHostId(model.getVirtualHostId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setLayoutSetId(model.getLayoutSetId());
		soapModel.setHostname(model.getHostname());
		soapModel.setDefaultVirtualHost(model.isDefaultVirtualHost());
		soapModel.setLanguageId(model.getLanguageId());

		return soapModel;
	}

	public static VirtualHostSoap[] toSoapModels(VirtualHost[] models) {
		VirtualHostSoap[] soapModels = new VirtualHostSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static VirtualHostSoap[][] toSoapModels(VirtualHost[][] models) {
		VirtualHostSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new VirtualHostSoap[models.length][models[0].length];
		}
		else {
			soapModels = new VirtualHostSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static VirtualHostSoap[] toSoapModels(List<VirtualHost> models) {
		List<VirtualHostSoap> soapModels = new ArrayList<VirtualHostSoap>(
			models.size());

		for (VirtualHost model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new VirtualHostSoap[soapModels.size()]);
	}

	public VirtualHostSoap() {
	}

	public long getPrimaryKey() {
		return _virtualHostId;
	}

	public void setPrimaryKey(long pk) {
		setVirtualHostId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getVirtualHostId() {
		return _virtualHostId;
	}

	public void setVirtualHostId(long virtualHostId) {
		_virtualHostId = virtualHostId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getLayoutSetId() {
		return _layoutSetId;
	}

	public void setLayoutSetId(long layoutSetId) {
		_layoutSetId = layoutSetId;
	}

	public String getHostname() {
		return _hostname;
	}

	public void setHostname(String hostname) {
		_hostname = hostname;
	}

	public boolean getDefaultVirtualHost() {
		return _defaultVirtualHost;
	}

	public boolean isDefaultVirtualHost() {
		return _defaultVirtualHost;
	}

	public void setDefaultVirtualHost(boolean defaultVirtualHost) {
		_defaultVirtualHost = defaultVirtualHost;
	}

	public String getLanguageId() {
		return _languageId;
	}

	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	private long _mvccVersion;
	private long _virtualHostId;
	private long _companyId;
	private long _layoutSetId;
	private String _hostname;
	private boolean _defaultVirtualHost;
	private String _languageId;

}