/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.documentum.repository.model;

import com.documentum.fc.client.IDfSysObject;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.IDfId;
import com.documentum.fc.common.IDfTime;

import com.liferay.document.library.repository.external.ExtRepositoryModel;
import com.liferay.document.library.repository.external.ExtRepositoryObject;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Iván Zaera
 */
public abstract class DocumentumObject
	implements ExtRepositoryModel, ExtRepositoryObject {

	public DocumentumObject(IDfSysObject idfSysObject) {
		_idfSysObject = idfSysObject;
	}

	@Override
	public boolean containsPermission(
		ExtRepositoryPermission extRepositoryPermission) {

		if (_unsupportedExtRepositoryPermissions.contains(
				extRepositoryPermission)) {

			return false;
		}

		try {
			int permit = _permits.get(extRepositoryPermission);

			if (_idfSysObject.getPermit() >= permit) {
				return true;
			}

			return false;
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public Date getCreateDate() {
		try {
			IDfTime idfTime = _idfSysObject.getCreationDate();

			return idfTime.getDate();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public String getDescription() {
		return StringPool.BLANK;
	}

	@Override
	public String getExtension() {
		try {
			return FileUtil.getExtension(_idfSysObject.getObjectName());
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public String getExtRepositoryModelKey() {
		try {
			IDfId idfId = _idfSysObject.getObjectId();

			return idfId.getId();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	public IDfSysObject getIDfSysObject() {
		return _idfSysObject;
	}

	@Override
	public Date getModifiedDate() {
		try {
			IDfTime idfTime = _idfSysObject.getModifyDate();

			return idfTime.getDate();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public String getOwner() {
		try {
			return _idfSysObject.getOwnerName();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	@Override
	public long getSize() {
		try {
			return _idfSysObject.getContentSize();
		}
		catch (DfException dfException) {
			throw new SystemException(dfException);
		}
	}

	private static final Map<ExtRepositoryPermission, Integer> _permits =
		new HashMap<ExtRepositoryPermission, Integer>() {
			{
				put(ExtRepositoryPermission.ACCESS, Constants.DF_PERMIT_BROWSE);
				put(
					ExtRepositoryPermission.ADD_DOCUMENT,
					Constants.DF_PERMIT_WRITE);
				put(
					ExtRepositoryPermission.ADD_FOLDER,
					Constants.DF_PERMIT_WRITE);
				put(
					ExtRepositoryPermission.ADD_SUBFOLDER,
					Constants.DF_PERMIT_WRITE);
				put(ExtRepositoryPermission.DELETE, Constants.DF_PERMIT_DELETE);
				put(ExtRepositoryPermission.UPDATE, Constants.DF_PERMIT_WRITE);
				put(ExtRepositoryPermission.VIEW, Constants.DF_PERMIT_READ);
			}
		};
	private static final Set<ExtRepositoryPermission>
		_unsupportedExtRepositoryPermissions = EnumSet.of(
			ExtRepositoryPermission.ADD_DISCUSSION,
			ExtRepositoryPermission.ADD_SHORTCUT,
			ExtRepositoryPermission.DELETE_DISCUSSION,
			ExtRepositoryPermission.PERMISSIONS,
			ExtRepositoryPermission.UPDATE_DISCUSSION);

	private final IDfSysObject _idfSysObject;

}