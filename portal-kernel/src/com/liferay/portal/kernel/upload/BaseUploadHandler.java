/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.upload;

import com.liferay.document.library.kernel.antivirus.AntivirusScannerException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.editor.EditorConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.io.InputStream;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * @author Sergio González
 * @author Adolfo Pérez
 * @author Roberto Díaz
 */
public abstract class BaseUploadHandler implements UploadHandler {

	@Override
	public void upload(
			PortletRequest portletRequest, PortletResponse portletResponse)
		throws PortalException {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		checkPermission(
			themeDisplay.getScopeGroupId(), getFolderId(uploadPortletRequest),
			themeDisplay.getPermissionChecker());

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			UploadException uploadException =
				(UploadException)portletRequest.getAttribute(
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

			JSONObject imageJSONObject = getImageJSONObject(portletRequest);

			String randomId = ParamUtil.getString(
				uploadPortletRequest, "randomId");

			imageJSONObject.put("randomId", randomId);

			jsonObject.put(
				"file", imageJSONObject
			).put(
				"success", Boolean.TRUE
			);

			JSONPortletResponseUtil.writeJSON(
				portletRequest, portletResponse, jsonObject);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
		catch (PortalException portalException) {
			handleUploadException(
				portletRequest, portletResponse, portalException, jsonObject);
		}
	}

	protected abstract FileEntry addFileEntry(
			long userId, long groupId, long folderId, String fileName,
			String contentType, InputStream inputStream, long size,
			ServiceContext serviceContext)
		throws PortalException;

	protected abstract void checkPermission(
			long groupId, long folderId, PermissionChecker permissionChecker)
		throws PortalException;

	protected void doHandleUploadException(
			PortletRequest portletRequest, PortletResponse portletResponse,
			PortalException portalException, JSONObject jsonObject)
		throws PortalException {

		throw portalException;
	}

	protected abstract FileEntry fetchFileEntry(
			long userId, long groupId, long folderId, String fileName)
		throws PortalException;

	protected long getFolderId(UploadPortletRequest uploadPortletRequest) {
		return 0;
	}

	protected JSONObject getImageJSONObject(PortletRequest portletRequest)
		throws PortalException {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(portletRequest);

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		JSONObject imageJSONObject = JSONFactoryUtil.createJSONObject();

		try {
			imageJSONObject.put(
				"attributeDataImageId",
				EditorConstants.ATTRIBUTE_DATA_IMAGE_ID);

			String parameterName = getParameterName();

			String fileName = uploadPortletRequest.getFileName(parameterName);
			String contentType = uploadPortletRequest.getContentType(
				parameterName);
			long size = uploadPortletRequest.getSize(parameterName);

			validateFile(fileName, contentType, size);

			long folderId = getFolderId(uploadPortletRequest);

			String uniqueFileName = getUniqueFileName(
				themeDisplay, fileName, folderId);

			try (InputStream inputStream = uploadPortletRequest.getFileAsStream(
					parameterName)) {

				FileEntry fileEntry = addFileEntry(
					themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
					folderId, uniqueFileName, contentType, inputStream, size,
					getServiceContext(uploadPortletRequest));

				imageJSONObject.put(
					"fileEntryId", fileEntry.getFileEntryId()
				).put(
					"groupId", fileEntry.getGroupId()
				).put(
					"title", fileEntry.getTitle()
				).put(
					"type", "document"
				).put(
					"url", getURL(fileEntry, themeDisplay)
				).put(
					"uuid", fileEntry.getUuid()
				);

				return imageJSONObject;
			}
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	protected abstract String getParameterName();

	/**
	 * @throws PortalException
	 */
	protected ServiceContext getServiceContext(
			UploadPortletRequest uploadPortletRequest)
		throws PortalException {

		return null;
	}

	protected String getUniqueFileName(
			ThemeDisplay themeDisplay, String fileName, long folderId)
		throws PortalException {

		FileEntry fileEntry = fetchFileEntry(
			themeDisplay.getUserId(), themeDisplay.getScopeGroupId(), folderId,
			fileName);

		if (fileEntry == null) {
			return fileName;
		}

		int suffix = 1;

		for (int i = 0;
			 i < UploadServletRequestConfigurationHelperUtil.getMaxTries();
			 i++) {

			String curFileName = FileUtil.appendParentheticalSuffix(
				fileName, String.valueOf(suffix));

			fileEntry = fetchFileEntry(
				themeDisplay.getUserId(), themeDisplay.getScopeGroupId(),
				folderId, curFileName);

			if (fileEntry == null) {
				return curFileName;
			}

			suffix++;
		}

		throw new PortalException(
			"Unable to get a unique file name for " + fileName);
	}

	protected String getURL(FileEntry fileEntry, ThemeDisplay themeDisplay) {
		return PortletFileRepositoryUtil.getPortletFileEntryURL(
			themeDisplay, fileEntry, StringPool.BLANK);
	}

	protected void handleUploadException(
			PortletRequest portletRequest, PortletResponse portletResponse,
			PortalException portalException, JSONObject jsonObject)
		throws PortalException {

		jsonObject.put("success", Boolean.FALSE);

		if (portalException instanceof AntivirusScannerException ||
			portalException instanceof FileNameException ||
			portalException instanceof FileSizeException ||
			portalException instanceof UploadRequestSizeException) {

			String errorMessage = StringPool.BLANK;
			int errorType = 0;

			if (portalException instanceof AntivirusScannerException) {
				errorType =
					ServletResponseConstants.SC_FILE_ANTIVIRUS_EXCEPTION;
				AntivirusScannerException antivirusScannerException =
					(AntivirusScannerException)portalException;

				ThemeDisplay themeDisplay =
					(ThemeDisplay)portletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				errorMessage = themeDisplay.translate(
					antivirusScannerException.getMessageKey());
			}
			else if (portalException instanceof FileNameException) {
				errorType = ServletResponseConstants.SC_FILE_NAME_EXCEPTION;
			}
			else if (portalException instanceof FileSizeException) {
				errorType = ServletResponseConstants.SC_FILE_SIZE_EXCEPTION;
			}
			else if (portalException instanceof UploadRequestSizeException) {
				errorType =
					ServletResponseConstants.SC_UPLOAD_REQUEST_SIZE_EXCEPTION;
			}

			jsonObject.put(
				"error",
				JSONUtil.put(
					"errorType", errorType
				).put(
					"message", errorMessage
				));
		}
		else {
			doHandleUploadException(
				portletRequest, portletResponse, portalException, jsonObject);
		}

		try {
			JSONPortletResponseUtil.writeJSON(
				portletRequest, portletResponse, jsonObject);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	protected abstract void validateFile(
			String fileName, String contentType, long size)
		throws PortalException;

	protected static final String TEMP_FOLDER_NAME =
		BaseUploadHandler.class.getName();

}