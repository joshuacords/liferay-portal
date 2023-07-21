/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.store.file.system;

import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.store.BaseStore;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 * @author Sten Martinez
 * @author Alexander Chow
 * @author Edward Han
 * @author Manuel de la Peña
 */
public class FileSystemStore extends BaseStore {

	public FileSystemStore(
		FileSystemStoreConfiguration fileSystemStoreConfiguration) {

		_fileSystemStoreConfiguration = fileSystemStoreConfiguration;

		initializeRootDir();

		fileSystemHelper = new FileSystemHelper(
			_fileSystemStoreConfiguration.useHardLinks(), getRootDirPath());
	}

	@Override
	public void addDirectory(
		long companyId, long repositoryId, String dirName) {

		File dirNameDir = getDirNameDir(companyId, repositoryId, dirName);

		if (dirNameDir.exists()) {
			return;
		}

		try {
			FileUtil.mkdirs(dirNameDir);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Override
	public void addFile(
		long companyId, long repositoryId, String fileName, InputStream is) {

		try {
			File fileNameVersionFile = getFileNameVersionFile(
				companyId, repositoryId, fileName, VERSION_DEFAULT);

			FileUtil.write(fileNameVersionFile, is);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Override
	public void addFile(
		long companyId, long repositoryId, String fileName, String versionLabel,
		InputStream is) {

		try {
			File fileNameVersionFile = getFileNameVersionFile(
				companyId, repositoryId, fileName, versionLabel);

			FileUtil.write(fileNameVersionFile, is);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Override
	public void checkRoot(long companyId) {
	}

	@Override
	public void copyFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws NoSuchFileException {

		File fromFileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, fromVersionLabel);

		if (!fromFileNameVersionFile.exists()) {
			throw new NoSuchFileException(
				companyId, repositoryId, fileName, fromVersionLabel);
		}

		File toFileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, toVersionLabel);

		try {
			fileSystemHelper.copy(
				fromFileNameVersionFile, toFileNameVersionFile);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Override
	public void deleteDirectory(
		long companyId, long repositoryId, String dirName) {

		File dirNameDir = getDirNameDir(companyId, repositoryId, dirName);

		if (!dirNameDir.exists()) {
			logFailedDeletion(companyId, repositoryId, dirName);

			return;
		}

		File parentFile = dirNameDir.getParentFile();

		FileUtil.deltree(dirNameDir);

		RepositoryDirKey repositoryDirKey = new RepositoryDirKey(
			companyId, repositoryId);

		_repositoryDirs.remove(repositoryDirKey);

		deleteEmptyAncestors(parentFile);
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName) {
		File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

		if (!fileNameDir.exists()) {
			logFailedDeletion(companyId, repositoryId, fileName);

			return;
		}

		File parentFile = fileNameDir.getParentFile();

		FileUtil.deltree(fileNameDir);

		deleteEmptyAncestors(companyId, repositoryId, parentFile);
	}

	@Override
	public void deleteFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		File fileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, versionLabel);

		if (!fileNameVersionFile.exists()) {
			logFailedDeletion(companyId, repositoryId, fileName, versionLabel);

			return;
		}

		File parentFile = fileNameVersionFile.getParentFile();

		fileNameVersionFile.delete();

		deleteEmptyAncestors(companyId, repositoryId, parentFile);
	}

	@Override
	public File getFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws NoSuchFileException {

		if (Validator.isNull(versionLabel)) {
			versionLabel = getHeadVersionLabel(
				companyId, repositoryId, fileName);
		}

		File fileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, versionLabel);

		if (!fileNameVersionFile.exists()) {
			throw new NoSuchFileException(
				companyId, repositoryId, fileName, versionLabel);
		}

		return fileNameVersionFile;
	}

	@Override
	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName)
		throws PortalException {

		try {
			File file = getFile(companyId, repositoryId, fileName);

			return Files.readAllBytes(file.toPath());
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Override
	public byte[] getFileAsBytes(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws PortalException {

		try {
			File file = getFile(
				companyId, repositoryId, fileName, versionLabel);

			return Files.readAllBytes(file.toPath());
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Override
	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws NoSuchFileException {

		if (Validator.isNull(versionLabel)) {
			versionLabel = getHeadVersionLabel(
				companyId, repositoryId, fileName);
		}

		File fileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, versionLabel);

		try {
			return new FileInputStream(fileNameVersionFile);
		}
		catch (FileNotFoundException fileNotFoundException) {
			throw new NoSuchFileException(
				companyId, repositoryId, fileName, fileNotFoundException);
		}
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId) {
		File repositoryDir = getRepositoryDir(companyId, repositoryId);

		List<String> fileNames = new ArrayList<>();

		String[] dirNames = FileUtil.listDirs(repositoryDir);

		for (String dirName : dirNames) {
			getFileNames(
				fileNames, dirName,
				repositoryDir.getPath() + StringPool.SLASH + dirName);
		}

		return fileNames.toArray(new String[0]);
	}

	@Override
	public String[] getFileNames(
		long companyId, long repositoryId, String dirName) {

		File dirNameDir = getDirNameDir(companyId, repositoryId, dirName);

		if (!dirNameDir.exists()) {
			return new String[0];
		}

		List<String> fileNames = new ArrayList<>();

		getFileNames(fileNames, dirName, dirNameDir.getPath());

		Collections.sort(fileNames);

		return fileNames.toArray(new String[0]);
	}

	@Override
	public long getFileSize(long companyId, long repositoryId, String fileName)
		throws NoSuchFileException {

		String versionLabel = getHeadVersionLabel(
			companyId, repositoryId, fileName);

		File fileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, versionLabel);

		if (!fileNameVersionFile.exists()) {
			throw new NoSuchFileException(companyId, repositoryId, fileName);
		}

		return fileNameVersionFile.length();
	}

	@Override
	public String[] getFileVersions(
			long companyId, long repositoryId, String fileName)
		throws PortalException {

		File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

		if (!fileNameDir.exists()) {
			return StringPool.EMPTY_ARRAY;
		}

		String[] versions = FileUtil.listFiles(fileNameDir);

		Arrays.sort(versions);

		return versions;
	}

	@Override
	public boolean hasDirectory(
		long companyId, long repositoryId, String dirName) {

		File dirNameDir = getDirNameDir(companyId, repositoryId, dirName);

		return dirNameDir.exists();
	}

	@Override
	public boolean hasFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		File fileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, versionLabel);

		return fileNameVersionFile.exists();
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws NoSuchFileException {

		File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

		if (!fileNameDir.exists()) {
			throw new NoSuchFileException(companyId, repositoryId, fileName);
		}

		File newFileNameDir = getFileNameDir(
			companyId, newRepositoryId, fileName);

		File parentFile = fileNameDir.getParentFile();

		fileSystemHelper.move(fileNameDir, newFileNameDir);

		deleteEmptyAncestors(companyId, repositoryId, parentFile);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String newFileName)
		throws NoSuchFileException {

		File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

		if (!fileNameDir.exists()) {
			throw new NoSuchFileException(companyId, repositoryId, fileName);
		}

		File newFileNameDir = getFileNameDir(
			companyId, repositoryId, newFileName);

		File parentFile = fileNameDir.getParentFile();

		fileSystemHelper.move(fileNameDir, newFileNameDir);

		deleteEmptyAncestors(companyId, repositoryId, parentFile);
	}

	@Override
	public void updateFile(
		long companyId, long repositoryId, String fileName, String versionLabel,
		InputStream is) {

		try {
			File fileNameVersionFile = getFileNameVersionFile(
				companyId, repositoryId, fileName, versionLabel);

			FileUtil.write(fileNameVersionFile, is);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	@Override
	public void updateFileVersion(
			long companyId, long repositoryId, String fileName,
			String fromVersionLabel, String toVersionLabel)
		throws NoSuchFileException {

		File fromFileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, fromVersionLabel);

		if (!fromFileNameVersionFile.exists()) {
			throw new NoSuchFileException(
				companyId, repositoryId, fileName, fromVersionLabel);
		}

		File toFileNameVersionFile = getFileNameVersionFile(
			companyId, repositoryId, fileName, toVersionLabel);

		fileSystemHelper.move(fromFileNameVersionFile, toFileNameVersionFile);
	}

	protected void deleteEmptyAncestors(File file) {
		deleteEmptyAncestors(-1, -1, file);
	}

	protected void deleteEmptyAncestors(
		long companyId, long repositoryId, File file) {

		while (file != null) {
			if (!file.delete()) {
				return;
			}

			String fileName = file.getName();

			if ((repositoryId > 0) &&
				fileName.equals(String.valueOf(repositoryId))) {

				RepositoryDirKey repositoryDirKey = new RepositoryDirKey(
					companyId, repositoryId);

				_repositoryDirs.remove(repositoryDirKey);
			}

			file = file.getParentFile();
		}
	}

	protected File getCompanyDir(long companyId) {
		File companyDir = new File(_rootDir + StringPool.SLASH + companyId);

		try {
			FileUtil.mkdirs(companyDir);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}

		return companyDir;
	}

	protected File getDirNameDir(
		long companyId, long repositoryId, String dirName) {

		return getFileNameDir(companyId, repositoryId, dirName);
	}

	protected File getFileNameDir(
		long companyId, long repositoryId, String fileName) {

		File repositoryDir = getRepositoryDir(companyId, repositoryId);

		return new File(repositoryDir + StringPool.SLASH + fileName);
	}

	protected void getFileNames(
		List<String> fileNames, String dirName, String path) {

		String[] pathDirNames = FileUtil.listDirs(path);

		if (ArrayUtil.isNotEmpty(pathDirNames)) {
			for (String pathDirName : pathDirNames) {
				String subdirName = null;

				if (Validator.isBlank(dirName)) {
					subdirName = pathDirName;
				}
				else {
					subdirName = dirName + StringPool.SLASH + pathDirName;
				}

				getFileNames(
					fileNames, subdirName,
					path + StringPool.SLASH + pathDirName);
			}
		}
		else {
			File file = new File(path);

			if (file.isDirectory()) {
				fileNames.add(dirName);
			}
		}
	}

	protected File getFileNameVersionFile(
		long companyId, long repositoryId, String fileName, String version) {

		File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

		return new File(fileNameDir + StringPool.SLASH + version);
	}

	protected String getHeadVersionLabel(
		long companyId, long repositoryId, String fileName) {

		File fileNameDir = getFileNameDir(companyId, repositoryId, fileName);

		if (!fileNameDir.exists()) {
			return VERSION_DEFAULT;
		}

		String[] versionLabels = FileUtil.listFiles(fileNameDir);

		String headVersionLabel = VERSION_DEFAULT;

		for (String versionLabel : versionLabels) {
			if (DLUtil.compareVersions(versionLabel, headVersionLabel) > 0) {
				headVersionLabel = versionLabel;
			}
		}

		return headVersionLabel;
	}

	protected File getRepositoryDir(long companyId, long repositoryId) {
		RepositoryDirKey repositoryDirKey = new RepositoryDirKey(
			companyId, repositoryId);

		File repositoryDir = _repositoryDirs.get(repositoryDirKey);

		if (repositoryDir == null) {
			File companyDir = getCompanyDir(companyId);

			repositoryDir = new File(
				companyDir + StringPool.SLASH + repositoryId);

			try {
				FileUtil.mkdirs(repositoryDir);
			}
			catch (IOException ioException) {
				throw new SystemException(ioException);
			}

			_repositoryDirs.put(repositoryDirKey, repositoryDir);
		}

		return repositoryDir;
	}

	protected String getRootDirName() {
		return _fileSystemStoreConfiguration.rootDir();
	}

	protected Path getRootDirPath() {
		return _rootDir.toPath();
	}

	protected void initializeRootDir() {
		String path = getRootDirName();

		_rootDir = new File(path);

		if (!_rootDir.isAbsolute()) {
			_rootDir = new File(PropsUtil.get(PropsKeys.LIFERAY_HOME), path);
		}

		try {
			FileUtil.mkdirs(_rootDir);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}
	}

	protected final FileSystemHelper fileSystemHelper;

	private final FileSystemStoreConfiguration _fileSystemStoreConfiguration;
	private final Map<RepositoryDirKey, File> _repositoryDirs =
		new ConcurrentHashMap<>();
	private File _rootDir;

	private static class RepositoryDirKey {

		public RepositoryDirKey(long companyId, long repositoryId) {
			_companyId = companyId;
			_repositoryId = repositoryId;
		}

		@Override
		public boolean equals(Object obj) {
			RepositoryDirKey repositoryDirKey = (RepositoryDirKey)obj;

			if ((_companyId == repositoryDirKey._companyId) &&
				(_repositoryId == repositoryDirKey._repositoryId)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return (int)((_companyId * 11) + _repositoryId);
		}

		private final long _companyId;
		private final long _repositoryId;

	}

}