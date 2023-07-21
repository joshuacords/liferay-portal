/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.diff;

import java.util.Date;

/**
 * @author Eudaldo Alonso
 */
public class DiffVersion {

	public DiffVersion(long userId, double version, Date modifiedDate) {
		_userId = userId;
		_version = version;
		_modifiedDate = modifiedDate;
	}

	public DiffVersion(
		long userId, double version, Date modifiedDate, String summary,
		String extraInfo) {

		_userId = userId;
		_version = version;
		_modifiedDate = modifiedDate;
		_summary = summary;
		_extraInfo = extraInfo;
	}

	public String getExtraInfo() {
		return _extraInfo;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public String getSummary() {
		return _summary;
	}

	public long getUserId() {
		return _userId;
	}

	public double getVersion() {
		return _version;
	}

	public void setExtraInfo(String extraInfo) {
		_extraInfo = extraInfo;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setVersion(double version) {
		_version = version;
	}

	private String _extraInfo;
	private Date _modifiedDate;
	private String _summary;
	private long _userId;
	private double _version;

}