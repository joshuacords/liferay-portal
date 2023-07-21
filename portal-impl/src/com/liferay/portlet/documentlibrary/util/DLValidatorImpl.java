/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.util;

import com.liferay.document.library.kernel.exception.FileExtensionException;
import com.liferay.document.library.kernel.exception.FileNameException;
import com.liferay.document.library.kernel.exception.FileSizeException;
import com.liferay.document.library.kernel.exception.FolderNameException;
import com.liferay.document.library.kernel.exception.InvalidFileVersionException;
import com.liferay.document.library.kernel.exception.SourceFileNameException;
import com.liferay.document.library.kernel.util.DLValidator;
import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeFormatter;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.InputStream;

/**
 * @author     Adolfo Pérez
 * @deprecated As of Judson (7.1.x), replaced by {@link
 *             com.liferay.document.library.internal.util.DLValidatorImpl}
 */
@Deprecated
public final class DLValidatorImpl implements DLValidator {

	@Override
	public String fixName(String name) {
		return DLValidatorUtil.fixName(name);
	}

	@Override
	public long getMaxAllowableSize() {
		return DLValidatorUtil.getMaxAllowableSize();
	}

	@Override
	public boolean isValidName(String name) {
		return DLValidatorUtil.isValidName(name);
	}

	@Override
	public void validateDirectoryName(String directoryName)
		throws FolderNameException {

		DLValidatorUtil.validateDirectoryName(directoryName);
	}

	@Override
	public void validateFileExtension(String fileName)
		throws FileExtensionException {

		DLValidatorUtil.validateFileExtension(fileName);
	}

	@Override
	public void validateFileName(String fileName) throws FileNameException {
		DLValidatorUtil.validateFileName(fileName);
	}

	@Override
	public void validateFileSize(String fileName, byte[] bytes)
		throws FileSizeException {

		DLValidatorUtil.validateFileSize(fileName, bytes);
	}

	@Override
	public void validateFileSize(String fileName, File file)
		throws FileSizeException {

		DLValidatorUtil.validateFileSize(fileName, file);
	}

	@Override
	public void validateFileSize(String fileName, InputStream is)
		throws FileSizeException {

		DLValidatorUtil.validateFileSize(fileName, is);
	}

	@Override
	public void validateFileSize(String fileName, long size)
		throws FileSizeException {

		DLValidatorUtil.validateFileSize(fileName, size);
	}

	@Override
	public void validateSourceFileExtension(
			String fileExtension, String sourceFileName)
		throws SourceFileNameException {

		DLValidatorUtil.validateSourceFileExtension(
			fileExtension, sourceFileName);
	}

	@Override
	public void validateVersionLabel(String versionLabel)
		throws InvalidFileVersionException {

		DLValidatorUtil.validateVersionLabel(versionLabel);
	}

	protected String replaceDLCharLastBlacklist(String title) {
		String previousTitle = null;

		while (!title.equals(previousTitle)) {
			previousTitle = title;

			for (String blacklistLastChar :
					PropsValues.DL_CHAR_LAST_BLACKLIST) {

				if (blacklistLastChar.startsWith(
						UnicodeFormatter.UNICODE_PREFIX)) {

					blacklistLastChar = UnicodeFormatter.parseString(
						blacklistLastChar);
				}

				if (title.endsWith(blacklistLastChar)) {
					title = StringUtil.replaceLast(
						title, blacklistLastChar, StringPool.BLANK);
				}
			}
		}

		return title;
	}

	protected String replaceDLNameBlacklist(String title) {
		String extension = FileUtil.getExtension(title);
		String nameWithoutExtension = FileUtil.stripExtension(title);

		for (String blacklistName : PropsValues.DL_NAME_BLACKLIST) {
			if (StringUtil.equalsIgnoreCase(
					nameWithoutExtension, blacklistName)) {

				if (Validator.isNull(extension)) {
					return nameWithoutExtension + StringPool.UNDERLINE;
				}

				return StringBundler.concat(
					nameWithoutExtension, StringPool.UNDERLINE,
					StringPool.PERIOD, extension);
			}
		}

		return title;
	}

}