/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.image;

import com.liferay.document.library.kernel.exception.NoSuchFileException;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.document.library.kernel.util.DLValidatorUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.NoSuchImageException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Image;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jorge Ferrer
 */
public class DLHook extends BaseHook {

	@Override
	public void deleteImage(Image image) throws PortalException {
		String fileName = getFileName(image.getImageId(), image.getType());

		try {
			DLStoreUtil.deleteFile(_COMPANY_ID, _REPOSITORY_ID, fileName);
		}
		catch (NoSuchFileException noSuchFileException) {
			throw new NoSuchImageException(noSuchFileException);
		}
	}

	@Override
	public byte[] getImageAsBytes(Image image) throws PortalException {
		InputStream is = DLStoreUtil.getFileAsStream(
			_COMPANY_ID, _REPOSITORY_ID,
			getFileName(image.getImageId(), image.getType()));

		byte[] bytes = null;

		try {
			bytes = FileUtil.getBytes(is);
		}
		catch (IOException ioException) {
			throw new SystemException(ioException);
		}

		return bytes;
	}

	@Override
	public InputStream getImageAsStream(Image image) throws PortalException {
		return DLStoreUtil.getFileAsStream(
			_COMPANY_ID, _REPOSITORY_ID,
			getFileName(image.getImageId(), image.getType()));
	}

	@Override
	public void updateImage(Image image, String type, byte[] bytes)
		throws PortalException {

		String fileName = getFileName(image.getImageId(), image.getType());

		DLValidatorUtil.validateFileSize(fileName, bytes);

		if (DLStoreUtil.hasFile(_COMPANY_ID, _REPOSITORY_ID, fileName)) {
			DLStoreUtil.deleteFile(_COMPANY_ID, _REPOSITORY_ID, fileName);
		}

		DLStoreUtil.addFile(_COMPANY_ID, _REPOSITORY_ID, fileName, true, bytes);
	}

	protected String getFileName(long imageId, String type) {
		return imageId + StringPool.PERIOD + type;
	}

	private static final long _COMPANY_ID = 0;

	private static final long _REPOSITORY_ID = 0;

}