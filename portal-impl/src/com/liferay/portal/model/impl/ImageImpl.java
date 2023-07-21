/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.util.PropsValues;

import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 */
public class ImageImpl extends ImageBaseImpl {

	@Override
	public byte[] getTextObj() {
		if (_textObj != null) {
			return _textObj;
		}

		long imageId = getImageId();

		try {
			DLFileEntry dlFileEntry = null;

			if (PropsValues.WEB_SERVER_SERVLET_CHECK_IMAGE_GALLERY) {
				dlFileEntry =
					DLFileEntryLocalServiceUtil.fetchFileEntryByAnyImageId(
						imageId);
			}

			InputStream is = null;

			if ((dlFileEntry != null) &&
				(dlFileEntry.getLargeImageId() == imageId)) {

				is = DLStoreUtil.getFileAsStream(
					dlFileEntry.getCompanyId(),
					dlFileEntry.getDataRepositoryId(), dlFileEntry.getName());
			}
			else {
				is = DLStoreUtil.getFileAsStream(
					_DEFAULT_COMPANY_ID, _DEFAULT_REPOSITORY_ID, getFileName());
			}

			byte[] bytes = FileUtil.getBytes(is);

			_textObj = bytes;
		}
		catch (Exception exception) {
			_log.error("Error reading image " + imageId, exception);
		}

		return _textObj;
	}

	@Override
	public void setTextObj(byte[] textObj) {
		_textObj = textObj;
	}

	protected String getFileName() {
		return getImageId() + StringPool.PERIOD + getType();
	}

	private static final long _DEFAULT_COMPANY_ID = 0;

	private static final long _DEFAULT_REPOSITORY_ID = 0;

	private static final Log _log = LogFactoryUtil.getLog(ImageImpl.class);

	private byte[] _textObj;

}