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
public class RenameFinderColumnEntrySoap implements Serializable {

	public static RenameFinderColumnEntrySoap toSoapModel(
		RenameFinderColumnEntry model) {

		RenameFinderColumnEntrySoap soapModel =
			new RenameFinderColumnEntrySoap();

		soapModel.setRenameFinderColumnEntryId(
			model.getRenameFinderColumnEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setRenamedColumn(model.getRenamedColumn());

		return soapModel;
	}

	public static RenameFinderColumnEntrySoap[] toSoapModels(
		RenameFinderColumnEntry[] models) {

		RenameFinderColumnEntrySoap[] soapModels =
			new RenameFinderColumnEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RenameFinderColumnEntrySoap[][] toSoapModels(
		RenameFinderColumnEntry[][] models) {

		RenameFinderColumnEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new RenameFinderColumnEntrySoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new RenameFinderColumnEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RenameFinderColumnEntrySoap[] toSoapModels(
		List<RenameFinderColumnEntry> models) {

		List<RenameFinderColumnEntrySoap> soapModels =
			new ArrayList<RenameFinderColumnEntrySoap>(models.size());

		for (RenameFinderColumnEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new RenameFinderColumnEntrySoap[soapModels.size()]);
	}

	public RenameFinderColumnEntrySoap() {
	}

	public long getPrimaryKey() {
		return _renameFinderColumnEntryId;
	}

	public void setPrimaryKey(long pk) {
		setRenameFinderColumnEntryId(pk);
	}

	public long getRenameFinderColumnEntryId() {
		return _renameFinderColumnEntryId;
	}

	public void setRenameFinderColumnEntryId(long renameFinderColumnEntryId) {
		_renameFinderColumnEntryId = renameFinderColumnEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getRenamedColumn() {
		return _renamedColumn;
	}

	public void setRenamedColumn(String renamedColumn) {
		_renamedColumn = renamedColumn;
	}

	private long _renameFinderColumnEntryId;
	private long _groupId;
	private String _renamedColumn;

}