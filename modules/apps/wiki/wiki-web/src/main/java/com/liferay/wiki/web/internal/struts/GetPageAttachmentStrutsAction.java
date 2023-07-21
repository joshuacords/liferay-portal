/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.web.internal.struts;

import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.flash.FlashMagicBytesUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.struts.StrutsAction;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.trash.TrashHelper;
import com.liferay.wiki.exception.NoSuchPageException;
import com.liferay.wiki.model.WikiPage;
import com.liferay.wiki.service.WikiPageService;
import com.liferay.wiki.web.internal.importer.MediaWikiImporter;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jorge Ferrer
 */
@Component(
	property = "path=/wiki/get_page_attachment", service = StrutsAction.class
)
public class GetPageAttachmentStrutsAction implements StrutsAction {

	@Override
	public String execute(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		try {
			long nodeId = ParamUtil.getLong(httpServletRequest, "nodeId");

			String title = ParamUtil.getString(httpServletRequest, "title");
			String fileName = ParamUtil.getString(
				httpServletRequest, "fileName");

			if (fileName.startsWith(
					MediaWikiImporter.SHARED_IMAGES_TITLE + StringPool.SLASH)) {

				String[] fileNameParts = fileName.split(
					MediaWikiImporter.SHARED_IMAGES_TITLE + StringPool.SLASH);

				fileName = fileNameParts[1];

				title = MediaWikiImporter.SHARED_IMAGES_TITLE;
			}

			int status = ParamUtil.getInteger(
				httpServletRequest, "status",
				WorkflowConstants.STATUS_APPROVED);

			getFile(
				nodeId, title, fileName, status, httpServletRequest,
				httpServletResponse);

			return null;
		}
		catch (Exception exception) {
			if (exception instanceof NoSuchFileException ||
				exception instanceof NoSuchPageException) {

				if (_log.isWarnEnabled()) {
					_log.warn(exception, exception);
				}
			}
			else {
				_portal.sendError(
					exception, httpServletRequest, httpServletResponse);
			}

			return null;
		}
	}

	protected void getFile(
			long nodeId, String title, String fileName, int status,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		WikiPage wikiPage = _wikiPageService.getPage(nodeId, title);

		FileEntry fileEntry = PortletFileRepositoryUtil.getPortletFileEntry(
			wikiPage.getGroupId(), wikiPage.getAttachmentsFolderId(), fileName);

		if ((status != WorkflowConstants.STATUS_IN_TRASH) &&
			fileEntry.isInTrash()) {

			return;
		}

		if (fileEntry.isInTrash()) {
			fileName = _trashHelper.getOriginalTitle(fileEntry.getTitle());
		}

		InputStream is = fileEntry.getContentStream();

		FlashMagicBytesUtil.Result flashMagicBytesUtilResult =
			FlashMagicBytesUtil.check(is);

		if (flashMagicBytesUtilResult.isFlash()) {
			fileName = FileUtil.stripExtension(fileName) + ".swf";
		}

		is = flashMagicBytesUtilResult.getInputStream();

		ServletResponseUtil.sendFile(
			httpServletRequest, httpServletResponse, fileName, is,
			fileEntry.getSize(), fileEntry.getMimeType());
	}

	@Reference(unbind = "-")
	protected void setWikiPageService(WikiPageService wikiPageService) {
		_wikiPageService = wikiPageService;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GetPageAttachmentStrutsAction.class);

	@Reference
	private Portal _portal;

	@Reference
	private TrashHelper _trashHelper;

	private WikiPageService _wikiPageService;

}