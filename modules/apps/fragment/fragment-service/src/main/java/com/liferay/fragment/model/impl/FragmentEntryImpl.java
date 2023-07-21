/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model.impl;

import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.constants.FragmentExportImportConstants;
import com.liferay.fragment.service.FragmentEntryLinkLocalServiceUtil;
import com.liferay.fragment.util.FragmentEntryRenderUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.zip.ZipWriter;

/**
 * @author Eudaldo Alonso
 */
public class FragmentEntryImpl extends FragmentEntryBaseImpl {

	@Override
	public String getContent() {
		return FragmentEntryRenderUtil.renderFragmentEntry(this);
	}

	@Override
	public int getGlobalUsageCount() {
		return FragmentEntryLinkLocalServiceUtil.
			getFragmentEntryLinksCountByFragmentEntryId(getFragmentEntryId());
	}

	@Override
	public String getImagePreviewURL(ThemeDisplay themeDisplay) {
		if (Validator.isNotNull(_imagePreviewURL)) {
			return _imagePreviewURL;
		}

		try {
			FileEntry fileEntry = _getPreviewFileEntry();

			if (fileEntry == null) {
				return StringPool.BLANK;
			}

			return DLUtil.getImagePreviewURL(fileEntry, themeDisplay);
		}
		catch (Exception exception) {
			_log.error("Unable to get preview entry image URL", exception);
		}

		return StringPool.BLANK;
	}

	@Override
	public String getTypeLabel() {
		return FragmentConstants.getTypeLabel(getType());
	}

	@Override
	public int getUsageCount() {
		return FragmentEntryLinkLocalServiceUtil.getFragmentEntryLinksCount(
			getGroupId(), getFragmentEntryId());
	}

	@Override
	public boolean isCacheable() {
		return _cacheable;
	}

	@Override
	public void populateZipWriter(ZipWriter zipWriter, String path)
		throws Exception {

		path = path + StringPool.SLASH + getFragmentEntryKey();

		JSONObject jsonObject = JSONUtil.put(
			"configurationPath", "index.json"
		).put(
			"cssPath", "index.css"
		).put(
			"htmlPath", "index.html"
		).put(
			"jsPath", "index.js"
		).put(
			"name", getName()
		);

		FileEntry previewFileEntry = _getPreviewFileEntry();

		if (previewFileEntry != null) {
			jsonObject.put(
				"thumbnailPath",
				"thumbnail." + previewFileEntry.getExtension());
		}

		String typeLabel = getTypeLabel();

		if (Validator.isNotNull(typeLabel)) {
			jsonObject.put("type", typeLabel);
		}

		zipWriter.addEntry(
			path + StringPool.SLASH +
				FragmentExportImportConstants.FILE_NAME_FRAGMENT_CONFIG,
			jsonObject.toString());

		zipWriter.addEntry(path + "/index.css", getCss());
		zipWriter.addEntry(path + "/index.js", getJs());
		zipWriter.addEntry(path + "/index.json", getConfiguration());
		zipWriter.addEntry(path + "/index.html", getHtml());

		if (previewFileEntry != null) {
			zipWriter.addEntry(
				path + "/thumbnail." + previewFileEntry.getExtension(),
				previewFileEntry.getContentStream());
		}
	}

	@Override
	public void setCacheable(boolean cacheable) {
		_cacheable = cacheable;
	}

	@Override
	public void setImagePreviewURL(String imagePreviewURL) {
		_imagePreviewURL = imagePreviewURL;
	}

	private FileEntry _getPreviewFileEntry() {
		if (getPreviewFileEntryId() <= 0) {
			return null;
		}

		try {
			return PortletFileRepositoryUtil.getPortletFileEntry(
				getPreviewFileEntryId());
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"Unable to get file entry preview ", portalException);
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FragmentEntryImpl.class);

	private boolean _cacheable;
	private String _imagePreviewURL;

}