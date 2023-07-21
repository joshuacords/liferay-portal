/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.subscription.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.osb.provisioning.subscription.service.http.SubscriptionEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SubscriptionEntrySoap implements Serializable {

	public static SubscriptionEntrySoap toSoapModel(SubscriptionEntry model) {
		SubscriptionEntrySoap soapModel = new SubscriptionEntrySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setSubscriptionEntryId(model.getSubscriptionEntryId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setContactUuid(model.getContactUuid());

		return soapModel;
	}

	public static SubscriptionEntrySoap[] toSoapModels(
		SubscriptionEntry[] models) {

		SubscriptionEntrySoap[] soapModels =
			new SubscriptionEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SubscriptionEntrySoap[][] toSoapModels(
		SubscriptionEntry[][] models) {

		SubscriptionEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new SubscriptionEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SubscriptionEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SubscriptionEntrySoap[] toSoapModels(
		List<SubscriptionEntry> models) {

		List<SubscriptionEntrySoap> soapModels =
			new ArrayList<SubscriptionEntrySoap>(models.size());

		for (SubscriptionEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SubscriptionEntrySoap[soapModels.size()]);
	}

	public SubscriptionEntrySoap() {
	}

	public long getPrimaryKey() {
		return _subscriptionEntryId;
	}

	public void setPrimaryKey(long pk) {
		setSubscriptionEntryId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getSubscriptionEntryId() {
		return _subscriptionEntryId;
	}

	public void setSubscriptionEntryId(long subscriptionEntryId) {
		_subscriptionEntryId = subscriptionEntryId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getContactUuid() {
		return _contactUuid;
	}

	public void setContactUuid(String contactUuid) {
		_contactUuid = contactUuid;
	}

	private long _mvccVersion;
	private long _subscriptionEntryId;
	private Date _createDate;
	private long _classNameId;
	private long _classPK;
	private String _contactUuid;

}