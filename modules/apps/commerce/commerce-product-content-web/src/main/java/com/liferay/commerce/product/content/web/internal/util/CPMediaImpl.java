/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.content.web.internal.util;

import com.liferay.commerce.media.CommerceMediaResolverUtil;
import com.liferay.commerce.product.catalog.CPMedia;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author Marco Leo
 */
public class CPMediaImpl implements CPMedia {

	public CPMediaImpl(FileEntry fileEntry, ThemeDisplay themeDisplay)
		throws PortalException {

		String defaultUrl = DLUtil.getDownloadURL(
			fileEntry, fileEntry.getFileVersion(), themeDisplay,
			StringPool.BLANK);

		_downloadUrl = defaultUrl;

		_id = fileEntry.getFileEntryId();
		_url = defaultUrl;
		_thumbnailUrl = defaultUrl;
		_mimeType = fileEntry.getMimeType();
		_title = fileEntry.getTitle();
	}

	public CPMediaImpl(long groupId) throws PortalException {
		String defaultUrl = CommerceMediaResolverUtil.getDefaultUrl(groupId);

		_downloadUrl = defaultUrl;

		_id = 0;
		_mimeType = null;
		_thumbnailUrl = defaultUrl;
		_title = null;
		_url = defaultUrl;
	}

	public CPMediaImpl(
			long commerceAccountId, CPAttachmentFileEntry cpAttachmentFileEntry,
			ThemeDisplay themeDisplay)
		throws PortalException {

		_downloadUrl = CommerceMediaResolverUtil.getDownloadURL(
			commerceAccountId,
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
		_id = cpAttachmentFileEntry.getCPAttachmentFileEntryId();

		FileEntry fileEntry = cpAttachmentFileEntry.getFileEntry();

		if (fileEntry == null) {
			_mimeType = StringPool.BLANK;
		}
		else {
			_mimeType = fileEntry.getMimeType();
		}

		_thumbnailUrl = CommerceMediaResolverUtil.getThumbnailURL(
			commerceAccountId,
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
		_title = cpAttachmentFileEntry.getTitle(themeDisplay.getLanguageId());
		_url = CommerceMediaResolverUtil.getURL(
			commerceAccountId,
			cpAttachmentFileEntry.getCPAttachmentFileEntryId());
	}

	@Override
	public String getDownloadUrl() {
		return _downloadUrl;
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public String getThumbnailUrl() {
		return _thumbnailUrl;
	}

	@Override
	public String getTitle() {
		return _title;
	}

	@Override
	public String getUrl() {
		return _url;
	}

	@Override
	public String mimeType() {
		return _mimeType;
	}

	private final String _downloadUrl;
	private final long _id;
	private final String _mimeType;
	private final String _thumbnailUrl;
	private final String _title;
	private final String _url;

}