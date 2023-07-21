/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.store;

import com.liferay.document.library.kernel.store.Store;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 */
public class StoreProxyBean extends BaseProxyBean implements Store {

	@Override
	public void addDirectory(
		long companyId, long repositoryId, String dirName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addFile(
		long companyId, long repositoryId, String fileName, byte[] bytes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addFile(
		long companyId, long repositoryId, String fileName, File file) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addFile(
		long companyId, long repositoryId, String fileName, InputStream is) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void addFile(
		long companyId, long repositoryId, String fileName, String versionLabel,
		InputStream is) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void checkRoot(long companyId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void copyFileToStore(
		long companyId, long repositoryId, String fileName, String versionLabel,
		Store targetStore) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void copyFileVersion(
		long companyId, long repositoryId, String fileName,
		String fromVersionLabel, String toVersionLabel) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteDirectory(
		long companyId, long repositoryId, String dirName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		throw new UnsupportedOperationException();
	}

	@Override
	public File getFile(long companyId, long repositoryId, String fileName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public File getFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] getFileAsBytes(
		long companyId, long repositoryId, String fileName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] getFileAsBytes(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		throw new UnsupportedOperationException();
	}

	@Override
	public InputStream getFileAsStream(
		long companyId, long repositoryId, String fileName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public InputStream getFileAsStream(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		throw new UnsupportedOperationException();
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String[] getFileNames(
		long companyId, long repositoryId, String dirName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public long getFileSize(
		long companyId, long repositoryId, String fileName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public String[] getFileVersions(
			long companyId, long repositoryId, String fileName)
		throws PortalException {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasDirectory(
		long companyId, long repositoryId, String dirName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasFile(long companyId, long repositoryId, String fileName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean hasFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void move(String srcDir, String destDir) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void moveFileToStore(
		long companyId, long repositoryId, String fileName, String versionLabel,
		Store targetStore) {

		throw new UnsupportedOperationException();
	}

	public void reindex(String[] ids) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, long newRepositoryId,
		String fileName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName,
		String newFileName) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName, String versionLabel,
		byte[] bytes) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName, String versionLabel,
		File file) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName, String versionLabel,
		InputStream is) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void updateFileVersion(
		long companyId, long repositoryId, String fileName,
		String fromVersionLabel, String toVersionLabel) {

		throw new UnsupportedOperationException();
	}

}