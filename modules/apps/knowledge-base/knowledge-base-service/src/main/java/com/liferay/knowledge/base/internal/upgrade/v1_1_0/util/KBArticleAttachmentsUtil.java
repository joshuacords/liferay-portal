/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.knowledge.base.internal.upgrade.v1_1_0.util;

import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.knowledge.base.model.KBArticle;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;

/**
 * @author Peter Shin
 */
public class KBArticleAttachmentsUtil {

	public static void deleteAttachmentsDirectory(long companyId) {
		try {
			String[] fileNames = DLStoreUtil.getFileNames(
				companyId, CompanyConstants.SYSTEM, "knowledgebase/articles");

			if (fileNames.length > 0) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to delete knowledgebase/articles");
				}

				return;
			}

			DLStoreUtil.deleteDirectory(
				companyId, CompanyConstants.SYSTEM, "knowledgebase/articles");
		}
		catch (Exception exception) {
			_log.error(exception.getMessage());
		}
	}

	public static void updateAttachments(KBArticle kbArticle) {
		try {
			long folderId = kbArticle.getClassPK();

			String oldDirName = "knowledgebase/articles/" + folderId;

			String newDirName = "knowledgebase/kbarticles/" + folderId;

			DLStoreUtil.addDirectory(
				kbArticle.getCompanyId(), CompanyConstants.SYSTEM, newDirName);

			String[] fileNames = DLStoreUtil.getFileNames(
				kbArticle.getCompanyId(), CompanyConstants.SYSTEM, oldDirName);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(kbArticle.getCompanyId());
			serviceContext.setScopeGroupId(kbArticle.getGroupId());

			for (String fileName : fileNames) {
				String shortFileName = FileUtil.getShortFileName(fileName);
				byte[] bytes = DLStoreUtil.getFileAsBytes(
					kbArticle.getCompanyId(), CompanyConstants.SYSTEM,
					fileName);

				DLStoreUtil.addFile(
					kbArticle.getCompanyId(), CompanyConstants.SYSTEM,
					newDirName + StringPool.SLASH + shortFileName, bytes);
			}

			DLStoreUtil.deleteDirectory(
				kbArticle.getCompanyId(), CompanyConstants.SYSTEM, oldDirName);

			if (_log.isInfoEnabled()) {
				_log.info("Added attachments for " + folderId);
			}
		}
		catch (Exception exception) {
			_log.error(exception.getMessage());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		KBArticleAttachmentsUtil.class);

}