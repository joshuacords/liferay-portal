/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EntrySoap implements Serializable {

	public static EntrySoap toSoapModel(Entry model) {
		EntrySoap soapModel = new EntrySoap();

		soapModel.setEntryId(model.getEntryId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setFromUserId(model.getFromUserId());
		soapModel.setToUserId(model.getToUserId());
		soapModel.setContent(model.getContent());
		soapModel.setFlag(model.getFlag());

		return soapModel;
	}

	public static EntrySoap[] toSoapModels(Entry[] models) {
		EntrySoap[] soapModels = new EntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static EntrySoap[][] toSoapModels(Entry[][] models) {
		EntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new EntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new EntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static EntrySoap[] toSoapModels(List<Entry> models) {
		List<EntrySoap> soapModels = new ArrayList<EntrySoap>(models.size());

		for (Entry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new EntrySoap[soapModels.size()]);
	}

	public EntrySoap() {
	}

	public long getPrimaryKey() {
		return _entryId;
	}

	public void setPrimaryKey(long pk) {
		setEntryId(pk);
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public long getFromUserId() {
		return _fromUserId;
	}

	public void setFromUserId(long fromUserId) {
		_fromUserId = fromUserId;
	}

	public long getToUserId() {
		return _toUserId;
	}

	public void setToUserId(long toUserId) {
		_toUserId = toUserId;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getFlag() {
		return _flag;
	}

	public void setFlag(int flag) {
		_flag = flag;
	}

	private long _entryId;
	private long _createDate;
	private long _fromUserId;
	private long _toUserId;
	private String _content;
	private int _flag;

}