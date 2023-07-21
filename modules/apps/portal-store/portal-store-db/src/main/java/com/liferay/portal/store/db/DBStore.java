/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.store.db;

import com.liferay.document.library.content.exception.NoSuchContentException;
import com.liferay.document.library.content.model.DLContent;
import com.liferay.document.library.content.model.DLContentDataBlobModel;
import com.liferay.document.library.content.service.DLContentLocalService;
import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.store.BaseStore;
import com.liferay.document.library.kernel.store.Store;
import com.liferay.petra.io.AutoDeleteFileInputStream;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.db.DBType;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.nio.channels.FileChannel;

import java.sql.Blob;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Shuyang Zhou
 * @author Tina Tian
 */
@Component(
	immediate = true,
	property = "store.type=com.liferay.portal.store.db.DBStore",
	service = Store.class
)
public class DBStore extends BaseStore {

	@Override
	public void addDirectory(
		long companyId, long repositoryId, String dirName) {
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, byte[] bytes)
		throws PortalException {

		updateFile(
			companyId, repositoryId, fileName, Store.VERSION_DEFAULT, bytes);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName, File file)
		throws PortalException {

		updateFile(
			companyId, repositoryId, fileName, Store.VERSION_DEFAULT, file);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName,
			InputStream inputStream)
		throws PortalException {

		updateFile(
			companyId, repositoryId, fileName, Store.VERSION_DEFAULT,
			inputStream);
	}

	@Override
	public void addFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, InputStream inputStream)
		throws PortalException {

		updateFile(
			companyId, repositoryId, fileName, versionLabel, inputStream);
	}

	@Override
	public void checkRoot(long companyId) {
	}

	@Override
	public void deleteDirectory(
		long companyId, long repositoryId, String dirName) {

		_dlContentLocalService.deleteContentsByDirectory(
			companyId, repositoryId, dirName);
	}

	@Override
	public void deleteFile(long companyId, long repositoryId, String fileName) {
		_dlContentLocalService.deleteContents(
			companyId, repositoryId, fileName);
	}

	@Override
	public void deleteFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		try {
			_dlContentLocalService.deleteContent(
				companyId, repositoryId, fileName, versionLabel);
		}
		catch (PortalException portalException) {
			logFailedDeletion(
				companyId, repositoryId, fileName, versionLabel,
				portalException);
		}
	}

	@Override
	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName)
		throws NoSuchFileException {

		try {
			DLContent dlContent = _dlContentLocalService.getContent(
				companyId, repositoryId, fileName);

			return TransactionInvokerUtil.invoke(
				_transactionConfig,
				new GetBlobDataCallable(dlContent.getContentId()));
		}
		catch (Throwable t) {
			throw new NoSuchFileException(companyId, repositoryId, fileName, t);
		}
	}

	@Override
	public InputStream getFileAsStream(
			long companyId, long repositoryId, String fileName,
			String versionLabel)
		throws NoSuchFileException {

		try {
			DLContent dlContent = _dlContentLocalService.getContent(
				companyId, repositoryId, fileName, versionLabel);

			return TransactionInvokerUtil.invoke(
				_transactionConfig,
				new GetBlobDataCallable(dlContent.getContentId()));
		}
		catch (Throwable t) {
			throw new NoSuchFileException(
				companyId, repositoryId, fileName, versionLabel, t);
		}
	}

	@Override
	public String[] getFileNames(long companyId, long repositoryId) {
		List<DLContent> dlContents = _dlContentLocalService.getContents(
			companyId, repositoryId);

		String[] fileNames = new String[dlContents.size()];

		for (int i = 0; i < dlContents.size(); i++) {
			DLContent dlContent = dlContents.get(i);

			fileNames[i] = dlContent.getPath();
		}

		return fileNames;
	}

	@Override
	public String[] getFileNames(
		long companyId, long repositoryId, String dirName) {

		List<DLContent> dlContents =
			_dlContentLocalService.getContentsByDirectory(
				companyId, repositoryId, dirName);

		String[] fileNames = new String[dlContents.size()];

		for (int i = 0; i < dlContents.size(); i++) {
			DLContent dlContent = dlContents.get(i);

			fileNames[i] = dlContent.getPath();
		}

		return fileNames;
	}

	@Override
	public long getFileSize(long companyId, long repositoryId, String fileName)
		throws NoSuchFileException {

		DLContent dlContent = null;

		try {
			dlContent = _dlContentLocalService.getContent(
				companyId, repositoryId, fileName);
		}
		catch (NoSuchContentException noSuchContentException) {
			throw new NoSuchFileException(
				companyId, repositoryId, fileName, noSuchContentException);
		}

		return dlContent.getSize();
	}

	@Override
	public String[] getFileVersions(
			long companyId, long repositoryId, String fileName)
		throws PortalException {

		List<DLContent> dlContents = _dlContentLocalService.getContents(
			companyId, repositoryId, fileName);

		if (dlContents.isEmpty()) {
			return StringPool.EMPTY_ARRAY;
		}

		String[] versions = new String[dlContents.size()];

		for (int i = 0; i < dlContents.size(); i++) {
			DLContent dlContent = dlContents.get(i);

			versions[i] = dlContent.getVersion();
		}

		Arrays.sort(versions);

		return versions;
	}

	@Override
	public boolean hasDirectory(
		long companyId, long repositoryId, String dirName) {

		return true;
	}

	@Override
	public boolean hasFile(
		long companyId, long repositoryId, String fileName,
		String versionLabel) {

		return _dlContentLocalService.hasContent(
			companyId, repositoryId, fileName, versionLabel);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, long newRepositoryId,
			String fileName)
		throws NoSuchFileException {

		if (repositoryId == newRepositoryId) {
			_dlContentLocalService.deleteContents(
				companyId, newRepositoryId, fileName);
		}

		if (!hasFile(companyId, repositoryId, fileName)) {
			throw new NoSuchFileException(companyId, repositoryId, fileName);
		}

		if (hasFile(companyId, newRepositoryId, fileName)) {
			_dlContentLocalService.deleteContents(
				companyId, newRepositoryId, fileName);
		}

		_dlContentLocalService.updateDLContent(
			companyId, repositoryId, newRepositoryId, fileName, fileName);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String newFileName)
		throws NoSuchFileException {

		if (fileName.equals(newFileName)) {
			_dlContentLocalService.deleteContents(
				companyId, repositoryId, newFileName);
		}

		if (!hasFile(companyId, repositoryId, fileName)) {
			throw new NoSuchFileException(companyId, repositoryId, fileName);
		}

		if (hasFile(companyId, repositoryId, newFileName)) {
			_dlContentLocalService.deleteContents(
				companyId, repositoryId, newFileName);
		}

		_dlContentLocalService.updateDLContent(
			companyId, repositoryId, repositoryId, fileName, newFileName);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, byte[] bytes)
		throws PortalException {

		if (hasFile(companyId, repositoryId, fileName, versionLabel)) {
			_dlContentLocalService.deleteContent(
				companyId, repositoryId, fileName, versionLabel);
		}

		_dlContentLocalService.addContent(
			companyId, repositoryId, fileName, versionLabel, bytes);
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, File file)
		throws PortalException {

		if (hasFile(companyId, repositoryId, fileName, versionLabel)) {
			_dlContentLocalService.deleteContent(
				companyId, repositoryId, fileName, versionLabel);
		}

		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);
		}
		catch (FileNotFoundException fileNotFoundException) {
			throw new SystemException(fileNotFoundException);
		}

		_dlContentLocalService.addContent(
			companyId, repositoryId, fileName, versionLabel, inputStream,
			file.length());
	}

	@Override
	public void updateFile(
			long companyId, long repositoryId, String fileName,
			String versionLabel, InputStream inputStream)
		throws PortalException {

		if (_dlContentLocalService.hasContent(
				companyId, repositoryId, fileName, versionLabel)) {

			_dlContentLocalService.deleteContent(
				companyId, repositoryId, fileName, versionLabel);
		}

		long length = -1;

		if (inputStream instanceof ByteArrayInputStream) {
			ByteArrayInputStream byteArrayInputStream =
				(ByteArrayInputStream)inputStream;

			length = byteArrayInputStream.available();
		}
		else if (inputStream instanceof FileInputStream) {
			FileInputStream fileInputStream = (FileInputStream)inputStream;

			FileChannel fileChannel = fileInputStream.getChannel();

			try {
				length = fileChannel.size();
			}
			catch (IOException ioException) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to detect file size from file channel",
						ioException);
				}
			}
		}
		else if (inputStream instanceof UnsyncByteArrayInputStream) {
			UnsyncByteArrayInputStream unsyncByteArrayInputStream =
				(UnsyncByteArrayInputStream)inputStream;

			length = unsyncByteArrayInputStream.available();
		}

		if (length >= 0) {
			_dlContentLocalService.addContent(
				companyId, repositoryId, fileName, versionLabel, inputStream,
				length);
		}
		else {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to detect length from input stream. Reading " +
						"entire input stream into memory as a last resort.");
			}

			byte[] bytes = null;

			try {
				bytes = FileUtil.getBytes(inputStream);
			}
			catch (IOException ioException) {
				throw new SystemException(ioException);
			}

			_dlContentLocalService.addContent(
				companyId, repositoryId, fileName, versionLabel, bytes);
		}
	}

	@Activate
	protected void activate() {
		DB db = DBManagerUtil.getDB();

		if ((db.getDBType() == DBType.DB2) ||
			(db.getDBType() == DBType.MYSQL) ||
			(db.getDBType() == DBType.MARIADB) ||
			(db.getDBType() == DBType.SYBASE)) {

			_transactionConfig = TransactionConfig.Factory.create(
				Propagation.SUPPORTS,
				new Class<?>[] {PortalException.class, SystemException.class});
		}
		else {
			_transactionConfig = TransactionConfig.Factory.create(
				Propagation.REQUIRED,
				new Class<?>[] {PortalException.class, SystemException.class});
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(DBStore.class);

	@Reference
	private DLContentLocalService _dlContentLocalService;

	private TransactionConfig _transactionConfig;

	private class GetBlobDataCallable implements Callable<InputStream> {

		@Override
		public InputStream call() throws Exception {
			DLContentDataBlobModel dlContentDataBlobModel =
				_dlContentLocalService.getDataBlobModel(_contentId);

			Blob blob = dlContentDataBlobModel.getDataBlob();

			InputStream inputStream = blob.getBinaryStream();

			if (_transactionConfig.getPropagation() == Propagation.REQUIRED) {
				inputStream = new AutoDeleteFileInputStream(
					FileUtil.createTempFile(inputStream));
			}

			return inputStream;
		}

		private GetBlobDataCallable(long contentId) {
			_contentId = contentId;
		}

		private final long _contentId;

	}

}