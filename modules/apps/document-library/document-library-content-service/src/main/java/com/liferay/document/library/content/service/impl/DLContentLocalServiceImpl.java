/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.content.service.impl;

import com.liferay.document.library.content.exception.NoSuchContentException;
import com.liferay.document.library.content.model.DLContent;
import com.liferay.document.library.content.service.base.DLContentLocalServiceBaseImpl;
import com.liferay.document.library.content.util.comparator.DLContentVersionComparator;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.jdbc.OutputBlob;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
@Component(
	property = "model.class.name=com.liferay.document.library.content.model.DLContent",
	service = AopService.class
)
public class DLContentLocalServiceImpl extends DLContentLocalServiceBaseImpl {

	@Override
	public DLContent addContent(
		long companyId, long repositoryId, String path, String version,
		byte[] bytes) {

		long contentId = counterLocalService.increment();

		DLContent dlContent = dlContentPersistence.create(contentId);

		dlContent.setCompanyId(companyId);
		dlContent.setRepositoryId(repositoryId);
		dlContent.setPath(path);
		dlContent.setVersion(version);

		UnsyncByteArrayInputStream unsyncByteArrayInputStream =
			new UnsyncByteArrayInputStream(bytes);

		OutputBlob dataOutputBlob = new OutputBlob(
			unsyncByteArrayInputStream, bytes.length);

		dlContent.setData(dataOutputBlob);

		dlContent.setSize(bytes.length);

		return dlContentPersistence.update(dlContent);
	}

	@Override
	public DLContent addContent(
		long companyId, long repositoryId, String path, String version,
		InputStream inputStream, long size) {

		DLContent dlContent = null;

		try (InputStream is = inputStream) {
			long contentId = counterLocalService.increment();

			dlContent = dlContentPersistence.create(contentId);

			dlContent.setCompanyId(companyId);
			dlContent.setRepositoryId(repositoryId);
			dlContent.setPath(path);
			dlContent.setVersion(version);

			OutputBlob dataOutputBlob = new OutputBlob(is, size);

			dlContent.setData(dataOutputBlob);

			dlContent.setSize(size);

			dlContent = dlContentPersistence.update(dlContent);
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(ioException, ioException);
			}
		}

		return dlContent;
	}

	@Override
	public void deleteContent(
			long companyId, long repositoryId, String path, String version)
		throws PortalException {

		dlContentPersistence.removeByC_R_P_V(
			companyId, repositoryId, path, version);
	}

	@Override
	public void deleteContents(long companyId, long repositoryId, String path) {
		dlContentPersistence.removeByC_R_P(companyId, repositoryId, path);
	}

	@Override
	public void deleteContentsByDirectory(
		long companyId, long repositoryId, String dirName) {

		if (!dirName.endsWith(StringPool.SLASH)) {
			dirName = dirName.concat(StringPool.SLASH);
		}

		dirName = dirName.concat(StringPool.PERCENT);

		dlContentPersistence.removeByC_R_LikeP(
			companyId, repositoryId, dirName);
	}

	@Override
	public DLContent getContent(long companyId, long repositoryId, String path)
		throws NoSuchContentException {

		OrderByComparator<DLContent> orderByComparator =
			new DLContentVersionComparator();

		List<DLContent> dlContents = dlContentPersistence.findByC_R_P(
			companyId, repositoryId, path, 0, 1, orderByComparator);

		if (ListUtil.isEmpty(dlContents)) {
			throw new NoSuchContentException(path);
		}

		return dlContents.get(0);
	}

	@Override
	public DLContent getContent(
			long companyId, long repositoryId, String path, String version)
		throws NoSuchContentException {

		return dlContentPersistence.findByC_R_P_V(
			companyId, repositoryId, path, version);
	}

	@Override
	public List<DLContent> getContents(long companyId, long repositoryId) {
		return dlContentPersistence.findByC_R(companyId, repositoryId);
	}

	@Override
	public List<DLContent> getContents(
		long companyId, long repositoryId, String path) {

		return dlContentPersistence.findByC_R_P(companyId, repositoryId, path);
	}

	@Override
	public List<DLContent> getContentsByDirectory(
		long companyId, long repositoryId, String dirName) {

		if (!dirName.endsWith(StringPool.SLASH)) {
			dirName = dirName.concat(StringPool.SLASH);
		}

		dirName = dirName.concat(StringPool.PERCENT);

		return dlContentPersistence.findByC_R_LikeP(
			companyId, repositoryId, dirName);
	}

	@Override
	public boolean hasContent(
		long companyId, long repositoryId, String path, String version) {

		int count = dlContentPersistence.countByC_R_P_V(
			companyId, repositoryId, path, version);

		if (count > 0) {
			return true;
		}

		return false;
	}

	@Override
	public void updateDLContent(
		long companyId, long oldRepositoryId, long newRepositoryId,
		String oldPath, String newPath) {

		List<DLContent> dlContents = dlContentPersistence.findByC_R_P(
			companyId, oldRepositoryId, oldPath);

		for (DLContent dLContent : dlContents) {
			dLContent.setRepositoryId(newRepositoryId);
			dLContent.setPath(newPath);

			dlContentPersistence.update(dLContent);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLContentLocalServiceImpl.class);

}