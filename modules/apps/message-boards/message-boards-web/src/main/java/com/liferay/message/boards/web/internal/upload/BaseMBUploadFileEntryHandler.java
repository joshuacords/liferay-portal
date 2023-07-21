/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.upload;

import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.message.boards.constants.MBMessageConstants;
import com.liferay.message.boards.service.MBMessageService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.TempFileEntryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.upload.UploadFileEntryHandler;

import java.io.IOException;
import java.io.InputStream;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
public abstract class BaseMBUploadFileEntryHandler
	implements UploadFileEntryHandler {

	@Override
	public FileEntry upload(UploadPortletRequest uploadPortletRequest)
		throws IOException, PortalException {

		dlValidator.validateFileSize(
			uploadPortletRequest.getFileName(getParameterName()),
			uploadPortletRequest.getSize(getParameterName()));

		ThemeDisplay themeDisplay =
			(ThemeDisplay)uploadPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long categoryId = ParamUtil.getLong(uploadPortletRequest, "categoryId");

		try (InputStream inputStream = getFileAsStream(uploadPortletRequest)) {
			String tempFileName = TempFileEntryUtil.getTempFileName(
				getFileName(uploadPortletRequest));

			return mbMessageService.addTempAttachment(
				themeDisplay.getScopeGroupId(), categoryId,
				MBMessageConstants.TEMP_FOLDER_NAME, tempFileName, inputStream,
				getContentType(uploadPortletRequest));
		}
	}

	protected String getContentType(UploadPortletRequest uploadPortletRequest) {
		return uploadPortletRequest.getContentType(getParameterName());
	}

	protected InputStream getFileAsStream(
			UploadPortletRequest uploadPortletRequest)
		throws IOException {

		return uploadPortletRequest.getFileAsStream(getParameterName());
	}

	protected String getFileName(UploadPortletRequest uploadPortletRequest) {
		return uploadPortletRequest.getFileName(getParameterName());
	}

	protected abstract String getParameterName();

	@Reference
	protected DLValidator dlValidator;

	@Reference
	protected MBMessageService mbMessageService;

}