/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.image.editor.integration.document.library.internal.portlet.action;

import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.document.library.constants.DLPortletKeys;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLVersionNumberIncrease;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLAppService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.upload.LiferayFileItemException;
import com.liferay.portal.kernel.upload.UploadException;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.upload.UploadRequestSizeException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.InputStream;

import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Ambrín Chaudhary
 */
@Component(
	property = {
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY,
		"javax.portlet.name=" + DLPortletKeys.DOCUMENT_LIBRARY_ADMIN,
		"javax.portlet.name=" + DLPortletKeys.MEDIA_GALLERY_DISPLAY,
		"mvc.command.name=/document_library/edit_file_entry_with_image_editor"
	},
	service = MVCActionCommand.class
)
public class EditFileEntryImageEditorMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		try {
			UploadException uploadException =
				(UploadException)actionRequest.getAttribute(
					WebKeys.UPLOAD_EXCEPTION);

			if (uploadException != null) {
				Throwable cause = uploadException.getCause();

				if (uploadException.isExceededFileSizeLimit()) {
					throw new FileSizeException(cause);
				}

				if (uploadException.isExceededLiferayFileItemSizeLimit()) {
					throw new LiferayFileItemException(cause);
				}

				if (uploadException.isExceededUploadRequestSizeLimit()) {
					throw new UploadRequestSizeException(cause);
				}

				throw new PortalException(cause);
			}

			updateFileEntry(actionRequest, actionResponse);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
		catch (PortalException portalException) {

			// LPS-52675

			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}

			handleUploadException(actionRequest, actionResponse);
		}
	}

	protected void handleUploadException(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortalException {

		JSONObject jsonObject = JSONUtil.put("success", Boolean.FALSE);

		try {
			JSONPortletResponseUtil.writeJSON(
				portletRequest, portletResponse, jsonObject);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	protected FileEntry updateFileEntry(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		UploadPortletRequest uploadPortletRequest =
			_portal.getUploadPortletRequest(actionRequest);

		long fileEntryId = ParamUtil.getLong(
			uploadPortletRequest, "fileEntryId");

		String sourceFileName = uploadPortletRequest.getFileName(
			"imageEditorFileName");

		FileEntry fileEntry = _dlAppLocalService.getFileEntry(fileEntryId);

		InputStream inputStream = uploadPortletRequest.getFileAsStream(
			"imageEditorFileName");
		String contentType = uploadPortletRequest.getContentType(
			"imageEditorFileName");
		long size = uploadPortletRequest.getSize("imageEditorFileName");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			DLFileEntry.class.getName(), uploadPortletRequest);

		long[] assetCategoryIds = _assetCategoryLocalService.getCategoryIds(
			DLFileEntry.class.getName(), fileEntry.getFileEntryId());

		serviceContext.setAssetCategoryIds(assetCategoryIds);

		fileEntry = _dlAppService.updateFileEntry(
			fileEntryId, sourceFileName, contentType, fileEntry.getTitle(),
			fileEntry.getDescription(), StringPool.BLANK,
			DLVersionNumberIncrease.MINOR, inputStream, size, serviceContext);

		JSONObject jsonObject = JSONUtil.put("success", Boolean.TRUE);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			themeDisplay.getLocale(),
			EditFileEntryImageEditorMVCActionCommand.class);

		SessionMessages.add(
			actionRequest, "requestProcessed",
			LanguageUtil.get(
				resourceBundle, "the-image-was-edited-successfully"));

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);

		return fileEntry;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		EditFileEntryImageEditorMVCActionCommand.class);

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLAppService _dlAppService;

	@Reference
	private Portal _portal;

}