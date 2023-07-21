/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.opener.onedrive.web.internal.lock;

import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.document.library.opener.onedrive.web.internal.DLOpenerOneDriveManager;
import com.liferay.portal.kernel.lock.BaseLockListener;
import com.liferay.portal.kernel.lock.LockListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PropsValues;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(
	property = "service.ranking:Integer=100", service = LockListener.class
)
public class DLFileEntryLockListener extends BaseLockListener {

	@Override
	public String getClassName() {
		return DLFileEntryConstants.getClassName();
	}

	@Override
	public void onAfterExpire(String key) {
		long fileEntryId = GetterUtil.getLong(key);

		try {
			FileEntry fileEntry = _dlAppService.getFileEntry(fileEntryId);

			if (!_dlOpenerOneDriveManager.isConfigured(
					fileEntry.getCompanyId()) ||
				!_dlOpenerOneDriveManager.isOneDriveFile(fileEntry)) {

				_lockListener.onAfterExpire(key);

				return;
			}

			if (PropsValues.DL_FILE_ENTRY_LOCK_POLICY == 1) {
				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setUserId(fileEntry.getUserId());

				_dlAppService.checkInFileEntry(
					fileEntryId, DLVersionNumberIncrease.fromMajorVersion(true),
					"Automatic timeout checkin", serviceContext);

				if (_log.isDebugEnabled()) {
					_log.debug("Lock expired and checked in " + fileEntryId);
				}
			}
			else {
				_dlAppService.cancelCheckOut(fileEntryId);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Lock expired and canceled check out of " +
							fileEntryId);
				}
			}
		}
		catch (Exception exception) {
			_log.error("Unable to execute onAfterExpire for " + key, exception);
		}
	}

	@Override
	public void onAfterRefresh(String key) {
		_lockListener.onAfterRefresh(key);
	}

	@Override
	public void onBeforeExpire(String key) {
		_lockListener.onBeforeExpire(key);
	}

	@Override
	public void onBeforeRefresh(String key) {
		_lockListener.onBeforeRefresh(key);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileEntryLockListener.class);

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private DLOpenerOneDriveManager _dlOpenerOneDriveManager;

	@Reference(
		target = "(component.name=com.liferay.document.library.internal.lock.DLFileEntryLockListener)"
	)
	private LockListener _lockListener;

}