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
public class FinderWhereClauseEntrySoap implements Serializable {

	public static FinderWhereClauseEntrySoap toSoapModel(
		FinderWhereClauseEntry model) {

		FinderWhereClauseEntrySoap soapModel = new FinderWhereClauseEntrySoap();

		soapModel.setFinderWhereClauseEntryId(
			model.getFinderWhereClauseEntryId());
		soapModel.setName(model.getName());
		soapModel.setNickname(model.getNickname());

		return soapModel;
	}

	public static FinderWhereClauseEntrySoap[] toSoapModels(
		FinderWhereClauseEntry[] models) {

		FinderWhereClauseEntrySoap[] soapModels =
			new FinderWhereClauseEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FinderWhereClauseEntrySoap[][] toSoapModels(
		FinderWhereClauseEntry[][] models) {

		FinderWhereClauseEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new FinderWhereClauseEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new FinderWhereClauseEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FinderWhereClauseEntrySoap[] toSoapModels(
		List<FinderWhereClauseEntry> models) {

		List<FinderWhereClauseEntrySoap> soapModels =
			new ArrayList<FinderWhereClauseEntrySoap>(models.size());

		for (FinderWhereClauseEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new FinderWhereClauseEntrySoap[soapModels.size()]);
	}

	public FinderWhereClauseEntrySoap() {
	}

	public long getPrimaryKey() {
		return _finderWhereClauseEntryId;
	}

	public void setPrimaryKey(long pk) {
		setFinderWhereClauseEntryId(pk);
	}

	public long getFinderWhereClauseEntryId() {
		return _finderWhereClauseEntryId;
	}

	public void setFinderWhereClauseEntryId(long finderWhereClauseEntryId) {
		_finderWhereClauseEntryId = finderWhereClauseEntryId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getNickname() {
		return _nickname;
	}

	public void setNickname(String nickname) {
		_nickname = nickname;
	}

	private long _finderWhereClauseEntryId;
	private String _name;
	private String _nickname;

}