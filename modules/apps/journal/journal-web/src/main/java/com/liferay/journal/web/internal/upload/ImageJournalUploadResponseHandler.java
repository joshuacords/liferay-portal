/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.web.internal.upload;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.ImageTypeException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseConstants;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.upload.UploadResponseHandler;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eduardo García
 * @author Alejandro Tardín
 */
@Component(service = ImageJournalUploadResponseHandler.class)
public class ImageJournalUploadResponseHandler
	implements UploadResponseHandler {

	@Override
	public JSONObject onFailure(
			PortletRequest portletRequest, PortalException portalException)
		throws PortalException {

		JSONObject jsonObject = _defaultUploadResponseHandler.onFailure(
			portletRequest, portalException);

		if (portalException instanceof ImageTypeException) {
			jsonObject.put(
				"error",
				JSONUtil.put(
					"errorType",
					ServletResponseConstants.SC_FILE_EXTENSION_EXCEPTION
				).put(
					"message", StringPool.BLANK
				));
		}

		return jsonObject;
	}

	@Override
	public JSONObject onSuccess(
			UploadPortletRequest uploadPortletRequest, FileEntry fileEntry)
		throws PortalException {

		JSONObject jsonObject = _defaultUploadResponseHandler.onSuccess(
			uploadPortletRequest, fileEntry);

		JSONObject fileJSONObject = jsonObject.getJSONObject("file");

		fileJSONObject.put("type", "journal");

		return jsonObject;
	}

	@Reference(target = "(upload.response.handler.system.default=true)")
	private UploadResponseHandler _defaultUploadResponseHandler;

}