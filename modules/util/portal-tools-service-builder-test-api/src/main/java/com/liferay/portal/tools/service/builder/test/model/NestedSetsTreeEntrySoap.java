/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.tools.service.builder.test.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class NestedSetsTreeEntrySoap implements Serializable {

	public static NestedSetsTreeEntrySoap toSoapModel(
		NestedSetsTreeEntry model) {

		NestedSetsTreeEntrySoap soapModel = new NestedSetsTreeEntrySoap();

		soapModel.setNestedSetsTreeEntryId(model.getNestedSetsTreeEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setParentNestedSetsTreeEntryId(
			model.getParentNestedSetsTreeEntryId());
		soapModel.setLeftNestedSetsTreeEntryId(
			model.getLeftNestedSetsTreeEntryId());
		soapModel.setRightNestedSetsTreeEntryId(
			model.getRightNestedSetsTreeEntryId());

		return soapModel;
	}

	public static NestedSetsTreeEntrySoap[] toSoapModels(
		NestedSetsTreeEntry[] models) {

		NestedSetsTreeEntrySoap[] soapModels =
			new NestedSetsTreeEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static NestedSetsTreeEntrySoap[][] toSoapModels(
		NestedSetsTreeEntry[][] models) {

		NestedSetsTreeEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new NestedSetsTreeEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new NestedSetsTreeEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static NestedSetsTreeEntrySoap[] toSoapModels(
		List<NestedSetsTreeEntry> models) {

		List<NestedSetsTreeEntrySoap> soapModels =
			new ArrayList<NestedSetsTreeEntrySoap>(models.size());

		for (NestedSetsTreeEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new NestedSetsTreeEntrySoap[soapModels.size()]);
	}

	public NestedSetsTreeEntrySoap() {
	}

	public long getPrimaryKey() {
		return _nestedSetsTreeEntryId;
	}

	public void setPrimaryKey(long pk) {
		setNestedSetsTreeEntryId(pk);
	}

	public long getNestedSetsTreeEntryId() {
		return _nestedSetsTreeEntryId;
	}

	public void setNestedSetsTreeEntryId(long nestedSetsTreeEntryId) {
		_nestedSetsTreeEntryId = nestedSetsTreeEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getParentNestedSetsTreeEntryId() {
		return _parentNestedSetsTreeEntryId;
	}

	public void setParentNestedSetsTreeEntryId(
		long parentNestedSetsTreeEntryId) {

		_parentNestedSetsTreeEntryId = parentNestedSetsTreeEntryId;
	}

	public long getLeftNestedSetsTreeEntryId() {
		return _leftNestedSetsTreeEntryId;
	}

	public void setLeftNestedSetsTreeEntryId(long leftNestedSetsTreeEntryId) {
		_leftNestedSetsTreeEntryId = leftNestedSetsTreeEntryId;
	}

	public long getRightNestedSetsTreeEntryId() {
		return _rightNestedSetsTreeEntryId;
	}

	public void setRightNestedSetsTreeEntryId(long rightNestedSetsTreeEntryId) {
		_rightNestedSetsTreeEntryId = rightNestedSetsTreeEntryId;
	}

	private long _nestedSetsTreeEntryId;
	private long _groupId;
	private long _parentNestedSetsTreeEntryId;
	private long _leftNestedSetsTreeEntryId;
	private long _rightNestedSetsTreeEntryId;

}