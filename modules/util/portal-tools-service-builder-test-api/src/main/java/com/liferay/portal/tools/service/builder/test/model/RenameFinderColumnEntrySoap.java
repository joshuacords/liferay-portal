/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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