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
public class RecentLayoutRevisionSoap implements Serializable {

	public static RecentLayoutRevisionSoap toSoapModel(
		RecentLayoutRevision model) {

		RecentLayoutRevisionSoap soapModel = new RecentLayoutRevisionSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setRecentLayoutRevisionId(model.getRecentLayoutRevisionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setLayoutRevisionId(model.getLayoutRevisionId());
		soapModel.setLayoutSetBranchId(model.getLayoutSetBranchId());
		soapModel.setPlid(model.getPlid());

		return soapModel;
	}

	public static RecentLayoutRevisionSoap[] toSoapModels(
		RecentLayoutRevision[] models) {

		RecentLayoutRevisionSoap[] soapModels =
			new RecentLayoutRevisionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RecentLayoutRevisionSoap[][] toSoapModels(
		RecentLayoutRevision[][] models) {

		RecentLayoutRevisionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new RecentLayoutRevisionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new RecentLayoutRevisionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RecentLayoutRevisionSoap[] toSoapModels(
		List<RecentLayoutRevision> models) {

		List<RecentLayoutRevisionSoap> soapModels =
			new ArrayList<RecentLayoutRevisionSoap>(models.size());

		for (RecentLayoutRevision model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new RecentLayoutRevisionSoap[soapModels.size()]);
	}

	public RecentLayoutRevisionSoap() {
	}

	public long getPrimaryKey() {
		return _recentLayoutRevisionId;
	}

	public void setPrimaryKey(long pk) {
		setRecentLayoutRevisionId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getRecentLayoutRevisionId() {
		return _recentLayoutRevisionId;
	}

	public void setRecentLayoutRevisionId(long recentLayoutRevisionId) {
		_recentLayoutRevisionId = recentLayoutRevisionId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public long getLayoutRevisionId() {
		return _layoutRevisionId;
	}

	public void setLayoutRevisionId(long layoutRevisionId) {
		_layoutRevisionId = layoutRevisionId;
	}

	public long getLayoutSetBranchId() {
		return _layoutSetBranchId;
	}

	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_layoutSetBranchId = layoutSetBranchId;
	}

	public long getPlid() {
		return _plid;
	}

	public void setPlid(long plid) {
		_plid = plid;
	}

	private long _mvccVersion;
	private long _recentLayoutRevisionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _layoutRevisionId;
	private long _layoutSetBranchId;
	private long _plid;

}