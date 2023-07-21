/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.convert.documentlibrary;

import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.convert.BaseConvertProcess;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author Alexander Chow
 */
public class DocumentLibraryExtraSettingsConvertProcess
	extends BaseConvertProcess {

	@Override
	public String getDescription() {
		return "convert-extra-settings-from-document-library-files";
	}

	@Override
	public String getPath() {
		return "/admin_server/edit_document_library_extra_settings";
	}

	@Override
	public boolean isEnabled() {
		try {
			return DLFileEntryLocalServiceUtil.hasExtraSettings();
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return false;
		}
	}

	@Override
	protected void doConvert() throws Exception {
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DocumentLibraryExtraSettingsConvertProcess.class);

}