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
public class PortalPreferencesSoap implements Serializable {

	public static PortalPreferencesSoap toSoapModel(PortalPreferences model) {
		PortalPreferencesSoap soapModel = new PortalPreferencesSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setPortalPreferencesId(model.getPortalPreferencesId());
		soapModel.setOwnerId(model.getOwnerId());
		soapModel.setOwnerType(model.getOwnerType());
		soapModel.setPreferences(model.getPreferences());

		return soapModel;
	}

	public static PortalPreferencesSoap[] toSoapModels(
		PortalPreferences[] models) {

		PortalPreferencesSoap[] soapModels =
			new PortalPreferencesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PortalPreferencesSoap[][] toSoapModels(
		PortalPreferences[][] models) {

		PortalPreferencesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new PortalPreferencesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PortalPreferencesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PortalPreferencesSoap[] toSoapModels(
		List<PortalPreferences> models) {

		List<PortalPreferencesSoap> soapModels =
			new ArrayList<PortalPreferencesSoap>(models.size());

		for (PortalPreferences model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PortalPreferencesSoap[soapModels.size()]);
	}

	public PortalPreferencesSoap() {
	}

	public long getPrimaryKey() {
		return _portalPreferencesId;
	}

	public void setPrimaryKey(long pk) {
		setPortalPreferencesId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getPortalPreferencesId() {
		return _portalPreferencesId;
	}

	public void setPortalPreferencesId(long portalPreferencesId) {
		_portalPreferencesId = portalPreferencesId;
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

	public String getPreferences() {
		return _preferences;
	}

	public void setPreferences(String preferences) {
		_preferences = preferences;
	}

	private long _mvccVersion;
	private long _portalPreferencesId;
	private long _ownerId;
	private int _ownerType;
	private String _preferences;

}