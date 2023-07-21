/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLFileVersionPreviewSoap implements Serializable {

	public static DLFileVersionPreviewSoap toSoapModel(
		DLFileVersionPreview model) {

		DLFileVersionPreviewSoap soapModel = new DLFileVersionPreviewSoap();

		soapModel.setDlFileVersionPreviewId(model.getDlFileVersionPreviewId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setFileVersionId(model.getFileVersionId());
		soapModel.setPreviewStatus(model.getPreviewStatus());

		return soapModel;
	}

	public static DLFileVersionPreviewSoap[] toSoapModels(
		DLFileVersionPreview[] models) {

		DLFileVersionPreviewSoap[] soapModels =
			new DLFileVersionPreviewSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DLFileVersionPreviewSoap[][] toSoapModels(
		DLFileVersionPreview[][] models) {

		DLFileVersionPreviewSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new DLFileVersionPreviewSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DLFileVersionPreviewSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DLFileVersionPreviewSoap[] toSoapModels(
		List<DLFileVersionPreview> models) {

		List<DLFileVersionPreviewSoap> soapModels =
			new ArrayList<DLFileVersionPreviewSoap>(models.size());

		for (DLFileVersionPreview model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new DLFileVersionPreviewSoap[soapModels.size()]);
	}

	public DLFileVersionPreviewSoap() {
	}

	public long getPrimaryKey() {
		return _dlFileVersionPreviewId;
	}

	public void setPrimaryKey(long pk) {
		setDlFileVersionPreviewId(pk);
	}

	public long getDlFileVersionPreviewId() {
		return _dlFileVersionPreviewId;
	}

	public void setDlFileVersionPreviewId(long dlFileVersionPreviewId) {
		_dlFileVersionPreviewId = dlFileVersionPreviewId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public long getFileVersionId() {
		return _fileVersionId;
	}

	public void setFileVersionId(long fileVersionId) {
		_fileVersionId = fileVersionId;
	}

	public int getPreviewStatus() {
		return _previewStatus;
	}

	public void setPreviewStatus(int previewStatus) {
		_previewStatus = previewStatus;
	}

	private long _dlFileVersionPreviewId;
	private long _groupId;
	private long _fileEntryId;
	private long _fileVersionId;
	private int _previewStatus;

}