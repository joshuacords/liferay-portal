/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.PortletServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PortletSoap implements Serializable {

	public static PortletSoap toSoapModel(Portlet model) {
		PortletSoap soapModel = new PortletSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setId(model.getId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setPortletId(model.getPortletId());
		soapModel.setRoles(model.getRoles());
		soapModel.setActive(model.isActive());

		return soapModel;
	}

	public static PortletSoap[] toSoapModels(Portlet[] models) {
		PortletSoap[] soapModels = new PortletSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PortletSoap[][] toSoapModels(Portlet[][] models) {
		PortletSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PortletSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PortletSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PortletSoap[] toSoapModels(List<Portlet> models) {
		List<PortletSoap> soapModels = new ArrayList<PortletSoap>(
			models.size());

		for (Portlet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PortletSoap[soapModels.size()]);
	}

	public PortletSoap() {
	}

	public long getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(long pk) {
		setId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getId() {
		return _id;
	}

	public void setId(long id) {
		_id = id;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getPortletId() {
		return _portletId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public String getRoles() {
		return _roles;
	}

	public void setRoles(String roles) {
		_roles = roles;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	private long _mvccVersion;
	private long _id;
	private long _companyId;
	private String _portletId;
	private String _roles;
	private boolean _active;

}