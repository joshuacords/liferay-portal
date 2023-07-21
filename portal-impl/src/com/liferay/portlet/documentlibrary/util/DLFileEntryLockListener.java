/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.util;

import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLFileEntryServiceUtil;
import com.liferay.portal.kernel.lock.BaseLockListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author     Alexander Chow
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 *             com.liferay.document.library.internal.lock.DLFileEntryLockListener}
 */
@Deprecated
public class DLFileEntryLockListener extends BaseLockListener {

	@Override
	public String getClassName() {
		return DLFileEntryConstants.getClassName();
	}

	@Override
	public void onAfterExpire(String key) {
		long fileEntryId = GetterUtil.getLong(key);

		try {
			if (PropsValues.DL_FILE_ENTRY_LOCK_POLICY == 1) {
				DLFileEntryServiceUtil.checkInFileEntry(
					fileEntryId, DLVersionNumberIncrease.fromMajorVersion(true),
					"Automatic timeout checkin", new ServiceContext());

				if (_log.isDebugEnabled()) {
					_log.debug("Lock expired and checked in " + fileEntryId);
				}
			}
			else {
				DLFileEntryServiceUtil.cancelCheckOut(fileEntryId);

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

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileEntryLockListener.class);

}