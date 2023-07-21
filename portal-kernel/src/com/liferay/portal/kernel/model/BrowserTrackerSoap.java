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
public class BrowserTrackerSoap implements Serializable {

	public static BrowserTrackerSoap toSoapModel(BrowserTracker model) {
		BrowserTrackerSoap soapModel = new BrowserTrackerSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setBrowserTrackerId(model.getBrowserTrackerId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setBrowserKey(model.getBrowserKey());

		return soapModel;
	}

	public static BrowserTrackerSoap[] toSoapModels(BrowserTracker[] models) {
		BrowserTrackerSoap[] soapModels = new BrowserTrackerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BrowserTrackerSoap[][] toSoapModels(
		BrowserTracker[][] models) {

		BrowserTrackerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new BrowserTrackerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new BrowserTrackerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BrowserTrackerSoap[] toSoapModels(
		List<BrowserTracker> models) {

		List<BrowserTrackerSoap> soapModels = new ArrayList<BrowserTrackerSoap>(
			models.size());

		for (BrowserTracker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BrowserTrackerSoap[soapModels.size()]);
	}

	public BrowserTrackerSoap() {
	}

	public long getPrimaryKey() {
		return _browserTrackerId;
	}

	public void setPrimaryKey(long pk) {
		setBrowserTrackerId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getBrowserTrackerId() {
		return _browserTrackerId;
	}

	public void setBrowserTrackerId(long browserTrackerId) {
		_browserTrackerId = browserTrackerId;
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

	public long getBrowserKey() {
		return _browserKey;
	}

	public void setBrowserKey(long browserKey) {
		_browserKey = browserKey;
	}

	private long _mvccVersion;
	private long _browserTrackerId;
	private long _companyId;
	private long _userId;
	private long _browserKey;

}