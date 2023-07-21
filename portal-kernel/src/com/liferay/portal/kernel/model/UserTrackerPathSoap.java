/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

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
public class UserTrackerPathSoap implements Serializable {

	public static UserTrackerPathSoap toSoapModel(UserTrackerPath model) {
		UserTrackerPathSoap soapModel = new UserTrackerPathSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setUserTrackerPathId(model.getUserTrackerPathId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserTrackerId(model.getUserTrackerId());
		soapModel.setPath(model.getPath());
		soapModel.setPathDate(model.getPathDate());

		return soapModel;
	}

	public static UserTrackerPathSoap[] toSoapModels(UserTrackerPath[] models) {
		UserTrackerPathSoap[] soapModels =
			new UserTrackerPathSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserTrackerPathSoap[][] toSoapModels(
		UserTrackerPath[][] models) {

		UserTrackerPathSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new UserTrackerPathSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserTrackerPathSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserTrackerPathSoap[] toSoapModels(
		List<UserTrackerPath> models) {

		List<UserTrackerPathSoap> soapModels =
			new ArrayList<UserTrackerPathSoap>(models.size());

		for (UserTrackerPath model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserTrackerPathSoap[soapModels.size()]);
	}

	public UserTrackerPathSoap() {
	}

	public long getPrimaryKey() {
		return _userTrackerPathId;
	}

	public void setPrimaryKey(long pk) {
		setUserTrackerPathId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getUserTrackerPathId() {
		return _userTrackerPathId;
	}

	public void setUserTrackerPathId(long userTrackerPathId) {
		_userTrackerPathId = userTrackerPathId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserTrackerId() {
		return _userTrackerId;
	}

	public void setUserTrackerId(long userTrackerId) {
		_userTrackerId = userTrackerId;
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String path) {
		_path = path;
	}

	public Date getPathDate() {
		return _pathDate;
	}

	public void setPathDate(Date pathDate) {
		_pathDate = pathDate;
	}

	private long _mvccVersion;
	private long _userTrackerPathId;
	private long _companyId;
	private long _userTrackerId;
	private String _path;
	private Date _pathDate;

}