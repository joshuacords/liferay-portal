/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.upload.format.handlers;

import com.liferay.message.boards.web.internal.upload.format.MBMessageFormatUploadHandler;
import com.liferay.message.boards.web.internal.util.MBAttachmentFileEntryReference;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.editor.EditorConstants;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepository;
import com.liferay.portal.kernel.repository.model.FileEntry;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = "format=html", service = MBMessageFormatUploadHandler.class
)
public class MBMessageHTMLFormatUploadHandler
	implements MBMessageFormatUploadHandler {

	@Override
	public String replaceImageReferences(
		String content,
		List<MBAttachmentFileEntryReference> mbAttachmentFileEntryReferences) {

		for (MBAttachmentFileEntryReference mbAttachmentFileEntryReference :
				mbAttachmentFileEntryReferences) {

			StringBundler sb = new StringBundler(8);

			sb.append("<\\s*?img");
			sb.append(_ATTRIBUTE_LIST_REGEXP);
			sb.append(EditorConstants.ATTRIBUTE_DATA_IMAGE_ID);
			sb.append("\\s*?=\\s*?\"");
			sb.append(
				mbAttachmentFileEntryReference.
					getTempMBAttachmentFileEntryId());
			sb.append("\"");
			sb.append(_ATTRIBUTE_LIST_REGEXP);
			sb.append("/>");

			content = content.replaceAll(
				sb.toString(),
				_getMBAttachmentFileEntryHTMLImgTag(
					mbAttachmentFileEntryReference.getMBAttachmentFileEntry()));
		}

		return content;
	}

	@Reference(unbind = "-")
	protected void setPortletFileRepository(
		PortletFileRepository portletFileRepository) {

		_portletFileRepository = portletFileRepository;
	}

	private String _getMBAttachmentFileEntryHTMLImgTag(
		FileEntry mbAttachmentFileEntry) {

		String fileEntryURL = _portletFileRepository.getPortletFileEntryURL(
			null, mbAttachmentFileEntry, StringPool.BLANK);

		return "<img src=\"" + fileEntryURL + "\" />";
	}

	private static final String _ATTRIBUTE_LIST_REGEXP =
		"(\\s*?\\w+\\s*?=\\s*?\"[^\"]*\")*?\\s*?";

	private PortletFileRepository _portletFileRepository;

}