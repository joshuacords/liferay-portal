/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.model.impl;

import com.liferay.fragment.constants.FragmentExportImportConstants;
import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.model.Repository;
import com.liferay.portal.kernel.portletfilerepository.PortletFileRepositoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.zip.ZipWriter;

import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class FragmentCollectionImpl extends FragmentCollectionBaseImpl {

	@Override
	public List<FileEntry> getResources() throws PortalException {
		return PortletFileRepositoryUtil.getPortletFileEntries(
			getGroupId(), getResourcesFolderId());
	}

	@Override
	public long getResourcesFolderId() throws PortalException {
		return getResourcesFolderId(true);
	}

	@Override
	public long getResourcesFolderId(boolean createIfAbsent)
		throws PortalException {

		if (_resourcesFolderId != 0) {
			return _resourcesFolderId;
		}

		Repository repository =
			PortletFileRepositoryUtil.fetchPortletRepository(
				getGroupId(), FragmentPortletKeys.FRAGMENT);

		if (repository == null) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			repository = PortletFileRepositoryUtil.addPortletRepository(
				getGroupId(), FragmentPortletKeys.FRAGMENT, serviceContext);
		}

		Folder folder = null;

		try {
			folder = PortletFileRepositoryUtil.getPortletFolder(
				repository.getRepositoryId(), repository.getDlFolderId(),
				String.valueOf(getFragmentCollectionId()));
		}
		catch (Exception exception) {
			if (createIfAbsent) {
				ServiceContext serviceContext = new ServiceContext();

				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(true);

				folder = PortletFileRepositoryUtil.addPortletFolder(
					getUserId(), repository.getRepositoryId(),
					repository.getDlFolderId(),
					String.valueOf(getFragmentCollectionId()), serviceContext);
			}
			else {
				return 0;
			}
		}

		_resourcesFolderId = folder.getFolderId();

		return _resourcesFolderId;
	}

	@Override
	public boolean hasResources() throws PortalException {
		int fileEntriesCount =
			PortletFileRepositoryUtil.getPortletFileEntriesCount(
				getGroupId(), getResourcesFolderId());

		if (fileEntriesCount <= 0) {
			return false;
		}

		return true;
	}

	@Override
	public void populateZipWriter(ZipWriter zipWriter) throws Exception {
		String path = StringPool.SLASH + getFragmentCollectionKey();

		JSONObject jsonObject = JSONUtil.put(
			"description", getDescription()
		).put(
			"name", getName()
		);

		zipWriter.addEntry(
			path + StringPool.SLASH +
				FragmentExportImportConstants.FILE_NAME_COLLECTION_CONFIG,
			jsonObject.toString());

		List<FragmentEntry> fragmentEntries =
			FragmentEntryLocalServiceUtil.getFragmentEntries(
				getFragmentCollectionId(), QueryUtil.ALL_POS,
				QueryUtil.ALL_POS);

		for (FragmentEntry fragmentEntry : fragmentEntries) {
			fragmentEntry.populateZipWriter(zipWriter, path + "/fragments");
		}

		if (!hasResources()) {
			return;
		}

		for (FileEntry fileEntry : getResources()) {
			StringBundler sb = new StringBundler(4);

			sb.append(path);
			sb.append(StringPool.SLASH);
			sb.append("resources/");
			sb.append(fileEntry.getFileName());

			zipWriter.addEntry(sb.toString(), fileEntry.getContentStream());
		}
	}

	private long _resourcesFolderId;

}