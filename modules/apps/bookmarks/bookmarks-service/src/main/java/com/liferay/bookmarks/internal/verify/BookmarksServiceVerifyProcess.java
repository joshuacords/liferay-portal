/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.bookmarks.internal.verify;

import com.liferay.bookmarks.model.BookmarksFolder;
import com.liferay.bookmarks.service.BookmarksFolderLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.verify.VerifyProcess;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author     Raymond Augé
 * @author     Alexander Chow
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Component(
	immediate = true,
	property = "verify.process.name=com.liferay.bookmarks.service",
	service = VerifyProcess.class
)
@Deprecated
public class BookmarksServiceVerifyProcess extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		updateFolderAssets();
		verifyTree();
	}

	protected void updateFolderAssets() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<BookmarksFolder> folders =
				_bookmarksFolderLocalService.getNoAssetFolders();

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Processing " + folders.size() + " folders with no asset");
			}

			for (BookmarksFolder folder : folders) {
				try {
					_bookmarksFolderLocalService.updateAsset(
						folder.getUserId(), folder, null, null, null, null);
				}
				catch (Exception exception) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							StringBundler.concat(
								"Unable to update asset for folder ",
								folder.getFolderId(), ": ",
								exception.getMessage()));
					}
				}
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Assets verified for folders");
			}
		}
	}

	protected void verifyTree() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			long[] companyIds =
				_portalInstancesLocalService.getCompanyIdsBySQL();

			for (long companyId : companyIds) {
				_bookmarksFolderLocalService.rebuildTree(companyId);
			}
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BookmarksServiceVerifyProcess.class);

	@Reference
	private BookmarksFolderLocalService _bookmarksFolderLocalService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

}