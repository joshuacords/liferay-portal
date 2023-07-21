/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.model.relationship.document.library.internal;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFileEntryType;
import com.liferay.document.library.kernel.model.DLFolder;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryLocalService;
import com.liferay.document.library.kernel.service.DLFileEntryTypeLocalService;
import com.liferay.document.library.kernel.service.DLFolderLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.relationship.Relationship;
import com.liferay.portal.relationship.RelationshipResource;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Máté Thurzó
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.document.library.kernel.model.DLFileEntryType",
	service = RelationshipResource.class
)
public class DLFileEntryTypeDLRelationshipResource
	implements RelationshipResource<DLFileEntryType> {

	@Override
	public Relationship<DLFileEntryType> relationship(
		Relationship.Builder<DLFileEntryType> builder) {

		return builder.modelSupplier(
			fileEntryTypeId -> _dlFileEntryTypeLocalService.fetchFileEntryType(
				fileEntryTypeId)
		).outboundSingleRelationship(
			this::_getFileEntry
		).outboundMultiRelationship(
			this::_getFolders
		).build();
	}

	private FileEntry _getFileEntry(DLFileEntryType fileEntryType) {
		List<DLFileEntry> dlFileEntries =
			_dlFileEntryLocalService.getFileEntries(-1, -1);

		Stream<DLFileEntry> stream = dlFileEntries.parallelStream();

		return stream.filter(
			dlFileEntry ->
				dlFileEntry.getFileEntryTypeId() ==
					fileEntryType.getFileEntryTypeId()
		).findFirst(
		).map(
			dlFileEntry -> {
				try {
					return _dlAppLocalService.getFileEntry(
						dlFileEntry.getFileEntryId());
				}
				catch (PortalException portalException) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							portalException.getMessage(), portalException);
					}

					return null;
				}
			}
		).get();
	}

	private List<Folder> _getFolders(DLFileEntryType fileEntryType) {
		List<DLFolder> dlFolders =
			_dlFolderLocalService.getDLFileEntryTypeDLFolders(
				fileEntryType.getFileEntryTypeId());

		Stream<DLFolder> stream = dlFolders.stream();

		return stream.map(
			dlFolder -> {
				try {
					return _dlAppLocalService.getFolder(dlFolder.getFolderId());
				}
				catch (PortalException portalException) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							portalException.getMessage(), portalException);
					}

					return null;
				}
			}
		).filter(
			Objects::nonNull
		).collect(
			Collectors.toList()
		);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileEntryTypeDLRelationshipResource.class);

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private DLFileEntryLocalService _dlFileEntryLocalService;

	@Reference
	private DLFileEntryTypeLocalService _dlFileEntryTypeLocalService;

	@Reference
	private DLFolderLocalService _dlFolderLocalService;

}