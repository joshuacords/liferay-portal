/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.image;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Image;

import java.io.InputStream;

/**
 * @author Jorge Ferrer
 */
public interface Hook {

	public void deleteImage(Image image) throws PortalException;

	public byte[] getImageAsBytes(Image image) throws PortalException;

	public InputStream getImageAsStream(Image image) throws PortalException;

	public void updateImage(Image image, String type, byte[] bytes)
		throws PortalException;

}