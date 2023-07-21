/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.RegionServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class RegionSoap implements Serializable {

	public static RegionSoap toSoapModel(Region model) {
		RegionSoap soapModel = new RegionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setRegionId(model.getRegionId());
		soapModel.setCountryId(model.getCountryId());
		soapModel.setRegionCode(model.getRegionCode());
		soapModel.setName(model.getName());
		soapModel.setActive(model.isActive());

		return soapModel;
	}

	public static RegionSoap[] toSoapModels(Region[] models) {
		RegionSoap[] soapModels = new RegionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RegionSoap[][] toSoapModels(Region[][] models) {
		RegionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new RegionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RegionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RegionSoap[] toSoapModels(List<Region> models) {
		List<RegionSoap> soapModels = new ArrayList<RegionSoap>(models.size());

		for (Region model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new RegionSoap[soapModels.size()]);
	}

	public RegionSoap() {
	}

	public long getPrimaryKey() {
		return _regionId;
	}

	public void setPrimaryKey(long pk) {
		setRegionId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getRegionId() {
		return _regionId;
	}

	public void setRegionId(long regionId) {
		_regionId = regionId;
	}

	public long getCountryId() {
		return _countryId;
	}

	public void setCountryId(long countryId) {
		_countryId = countryId;
	}

	public String getRegionCode() {
		return _regionCode;
	}

	public void setRegionCode(String regionCode) {
		_regionCode = regionCode;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
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
	private long _regionId;
	private long _countryId;
	private String _regionCode;
	private String _name;
	private boolean _active;

}