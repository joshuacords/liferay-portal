/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.store;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;

import java.io.File;
import java.io.InputStream;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 * @author Edward Han
 */
@Transactional(rollbackFor = {PortalException.class, SystemException.class})
public interface DLStore {

	public void addDirectory(long companyId, long repositoryId, String dirName)
		throws PortalException;

	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, byte[] bytes)
		throws PortalException;

	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, File file)
		throws PortalException;

	public void addFile(
			long companyId, long repositoryId, String fileName,
			boolean validateFileExtension, InputStream is)
		throws PortalException;

	public void addFile(
			long companyId, long repositoryId, String fileName, byte[] bytes)
		throws PortalException;

	public void addFile(
			long companyId, long repositoryId, String fileName, File file)
		throws PortalException;

	public void addFile(
			long companyId, long repositoryId, String fileName, InputStream is)
		throws PortalException;

	public void checkRoot(long companyId);

	public void copyFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException;

	public void deleteDirectory(
		long companyId, long repositoryId, String dirName);

	public void deleteFile(long companyId, long repositoryId, String fileName)
		throws PortalException;

	public void deleteFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException;

	public File getFile(long companyId, long repositoryId, String fileName)
		throws PortalException;

	public File getFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException;

	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName)
		throws PortalException;

	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException;

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName)
		throws PortalException;

	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException;

	public String[] getFileNames(
			long companyId, long repositoryId, String dirName)
		throws PortalException;

	public long getFileSize(long companyId, long repositoryId, String fileName)
		throws PortalException;

	public boolean hasDirectory(
			long companyId, long repositoryId, String dirName)
		throws PortalException;

	public boolean hasFile(long companyId, long repositoryId, String fileName)
		throws PortalException;

	public boolean hasFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException;

	public boolean isValidName(String name);

	public void move(String srcDir, String destDir);

	public void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws PortalException;

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String newFileName)
		throws PortalException;

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String fileExtension, boolean validateFileExtension,
			String versionLabel, String sourceFileName, File file)
		throws PortalException;

	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String fileExtension, boolean validateFileExtension,
			String versionLabel, String sourceFileName, InputStream is)
		throws PortalException;

	public void updateFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws PortalException;

	public void validate(String fileName, boolean validateFileExtension)
		throws PortalException;

	public void validate(
			String fileName, boolean validateFileExtension, byte[] bytes)
		throws PortalException;

	public void validate(
			String fileName, boolean validateFileExtension, File file)
		throws PortalException;

	public void validate(
			String fileName, boolean validateFileExtension, InputStream is)
		throws PortalException;

	public void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension)
		throws PortalException;

	public void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, File file)
		throws PortalException;

	public void validate(
			String fileName, String fileExtension, String sourceFileName,
			boolean validateFileExtension, InputStream is)
		throws PortalException;

	public void validateDirectoryName(String directoryName)
		throws PortalException;

}