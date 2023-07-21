/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.kernel.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SocialActivityAchievementSoap implements Serializable {

	public static SocialActivityAchievementSoap toSoapModel(
		SocialActivityAchievement model) {

		SocialActivityAchievementSoap soapModel =
			new SocialActivityAchievementSoap();

		soapModel.setActivityAchievementId(model.getActivityAchievementId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setName(model.getName());
		soapModel.setFirstInGroup(model.isFirstInGroup());

		return soapModel;
	}

	public static SocialActivityAchievementSoap[] toSoapModels(
		SocialActivityAchievement[] models) {

		SocialActivityAchievementSoap[] soapModels =
			new SocialActivityAchievementSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SocialActivityAchievementSoap[][] toSoapModels(
		SocialActivityAchievement[][] models) {

		SocialActivityAchievementSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SocialActivityAchievementSoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new SocialActivityAchievementSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SocialActivityAchievementSoap[] toSoapModels(
		List<SocialActivityAchievement> models) {

		List<SocialActivityAchievementSoap> soapModels =
			new ArrayList<SocialActivityAchievementSoap>(models.size());

		for (SocialActivityAchievement model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new SocialActivityAchievementSoap[soapModels.size()]);
	}

	public SocialActivityAchievementSoap() {
	}

	public long getPrimaryKey() {
		return _activityAchievementId;
	}

	public void setPrimaryKey(long pk) {
		setActivityAchievementId(pk);
	}

	public long getActivityAchievementId() {
		return _activityAchievementId;
	}

	public void setActivityAchievementId(long activityAchievementId) {
		_activityAchievementId = activityAchievementId;
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

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public boolean getFirstInGroup() {
		return _firstInGroup;
	}

	public boolean isFirstInGroup() {
		return _firstInGroup;
	}

	public void setFirstInGroup(boolean firstInGroup) {
		_firstInGroup = firstInGroup;
	}

	private long _activityAchievementId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _createDate;
	private String _name;
	private boolean _firstInGroup;

}