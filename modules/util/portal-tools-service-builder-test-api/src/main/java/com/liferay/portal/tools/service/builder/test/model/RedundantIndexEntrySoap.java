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
public class RedundantIndexEntrySoap implements Serializable {

	public static RedundantIndexEntrySoap toSoapModel(
		RedundantIndexEntry model) {

		RedundantIndexEntrySoap soapModel = new RedundantIndexEntrySoap();

		soapModel.setRedundantIndexEntryId(model.getRedundantIndexEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static RedundantIndexEntrySoap[] toSoapModels(
		RedundantIndexEntry[] models) {

		RedundantIndexEntrySoap[] soapModels =
			new RedundantIndexEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static RedundantIndexEntrySoap[][] toSoapModels(
		RedundantIndexEntry[][] models) {

		RedundantIndexEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new RedundantIndexEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new RedundantIndexEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static RedundantIndexEntrySoap[] toSoapModels(
		List<RedundantIndexEntry> models) {

		List<RedundantIndexEntrySoap> soapModels =
			new ArrayList<RedundantIndexEntrySoap>(models.size());

		for (RedundantIndexEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new RedundantIndexEntrySoap[soapModels.size()]);
	}

	public RedundantIndexEntrySoap() {
	}

	public long getPrimaryKey() {
		return _redundantIndexEntryId;
	}

	public void setPrimaryKey(long pk) {
		setRedundantIndexEntryId(pk);
	}

	public long getRedundantIndexEntryId() {
		return _redundantIndexEntryId;
	}

	public void setRedundantIndexEntryId(long redundantIndexEntryId) {
		_redundantIndexEntryId = redundantIndexEntryId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _redundantIndexEntryId;
	private long _companyId;
	private String _name;

}