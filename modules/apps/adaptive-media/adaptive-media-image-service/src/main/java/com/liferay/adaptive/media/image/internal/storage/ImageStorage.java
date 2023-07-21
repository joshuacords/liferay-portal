/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.image.internal.storage;

import com.liferay.adaptive.media.exception.AMRuntimeException;
import com.liferay.document.library.kernel.store.DLStoreUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.repository.model.FileVersion;

import java.io.InputStream;

import org.osgi.service.component.annotations.Component;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = ImageStorage.class)
public class ImageStorage {

	public void delete(FileVersion fileVersion, String configurationUuid) {
		DLStoreUtil.deleteDirectory(
			fileVersion.getCompanyId(), CompanyConstants.SYSTEM,
			AMStoreUtil.getFileVersionPath(fileVersion, configurationUuid));
	}

	public void delete(long companyId, String configurationUuid) {
		DLStoreUtil.deleteDirectory(
			companyId, CompanyConstants.SYSTEM,
			getConfigurationEntryPath(configurationUuid));
	}

	public InputStream getContentStream(
		FileVersion fileVersion, String configurationUuid) {

		try {
			String fileVersionPath = AMStoreUtil.getFileVersionPath(
				fileVersion, configurationUuid);

			return DLStoreUtil.getFileAsStream(
				fileVersion.getCompanyId(), CompanyConstants.SYSTEM,
				fileVersionPath);
		}
		catch (PortalException portalException) {
			throw new AMRuntimeException.IOException(portalException);
		}
	}

	public void save(
		FileVersion fileVersion, String configurationUuid,
		InputStream inputStream) {

		try {
			String fileVersionPath = AMStoreUtil.getFileVersionPath(
				fileVersion, configurationUuid);

			DLStoreUtil.addFile(
				fileVersion.getCompanyId(), CompanyConstants.SYSTEM,
				fileVersionPath, false, inputStream);
		}
		catch (PortalException portalException) {
			throw new AMRuntimeException.IOException(portalException);
		}
	}

	protected String getConfigurationEntryPath(String configurationUuid) {
		return String.format("adaptive/%s", configurationUuid);
	}

}