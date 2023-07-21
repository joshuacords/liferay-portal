/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.kernel.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class DuplicateFileException extends PortalException {

	public DuplicateFileException() {
	}

	public DuplicateFileException(
		long companyId, long repositoryId, String fileName) {

		super(
			String.format(
				"{companyId=%s, repositoryId=%s, fileName=%s}", companyId,
				repositoryId, fileName));
	}

	public DuplicateFileException(
		long companyId, long repositoryId, String fileName, String version) {

		super(
			String.format(
				"{companyId=%s, repositoryId=%s, fileName=%s, version=%s}",
				companyId, repositoryId, fileName, version));
	}

	public DuplicateFileException(
		long companyId, long repositoryId, String fileName, String version,
		Throwable cause) {

		super(
			String.format(
				"{companyId=%s, repositoryId=%s, fileName=%s, version=%s, " +
					"cause=%s}",
				companyId, repositoryId, fileName, version, cause),
			cause);
	}

	public DuplicateFileException(
		long companyId, long repositoryId, String fileName, Throwable cause) {

		super(
			String.format(
				"{companyId=%s, repositoryId=%s, fileName=%s, cause=%s}",
				companyId, repositoryId, fileName, cause),
			cause);
	}

	public DuplicateFileException(String msg) {
		super(msg);
	}

	public DuplicateFileException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DuplicateFileException(Throwable cause) {
		super(cause);
	}

}