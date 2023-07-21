/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.PortletPreferencesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PortletPreferencesSoap implements Serializable {

	public static PortletPreferencesSoap toSoapModel(PortletPreferences model) {
		PortletPreferencesSoap soapModel = new PortletPreferencesSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setPortletPreferencesId(model.getPortletPreferencesId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setOwnerId(model.getOwnerId());
		soapModel.setOwnerType(model.getOwnerType());
		soapModel.setPlid(model.getPlid());
		soapModel.setPortletId(model.getPortletId());
		soapModel.setPreferences(model.getPreferences());

		return soapModel;
	}

	public static PortletPreferencesSoap[] toSoapModels(
		PortletPreferences[] models) {

		PortletPreferencesSoap[] soapModels =
			new PortletPreferencesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PortletPreferencesSoap[][] toSoapModels(
		PortletPreferences[][] models) {

		PortletPreferencesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new PortletPreferencesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PortletPreferencesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PortletPreferencesSoap[] toSoapModels(
		List<PortletPreferences> models) {

		List<PortletPreferencesSoap> soapModels =
			new ArrayList<PortletPreferencesSoap>(models.size());

		for (PortletPreferences model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new PortletPreferencesSoap[soapModels.size()]);
	}

	public PortletPreferencesSoap() {
	}

	public long getPrimaryKey() {
		return _portletPreferencesId;
	}

	public void setPrimaryKey(long pk) {
		setPortletPreferencesId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getPortletPreferencesId() {
		return _portletPreferencesId;
	}

	public void setPortletPreferencesId(long portletPreferencesId) {
		_portletPreferencesId = portletPreferencesId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getOwnerId() {
		return _ownerId;
	}

	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;
	}

	public int getOwnerType() {
		return _ownerType;
	}

	public void setOwnerType(int ownerType) {
		_ownerType = ownerType;
	}

	public long getPlid() {
		return _plid;
	}

	public void setPlid(long plid) {
		_plid = plid;
	}

	public String getPortletId() {
		return _portletId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public String getPreferences() {
		return _preferences;
	}

	public void setPreferences(String preferences) {
		_preferences = preferences;
	}

	private long _mvccVersion;
	private long _portletPreferencesId;
	private long _companyId;
	private long _ownerId;
	private int _ownerType;
	private long _plid;
	private String _portletId;
	private String _preferences;

}