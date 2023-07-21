/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.image;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.model.Image;

import java.io.InputStream;

/**
 * @author Jorge Ferrer
 */
public class DatabaseHook extends BaseHook {

	@Override
	public void deleteImage(Image image) {
	}

	@Override
	public byte[] getImageAsBytes(Image image) {
		return image.getTextObj();
	}

	@Override
	public InputStream getImageAsStream(Image image) {
		return new UnsyncByteArrayInputStream(getImageAsBytes(image));
	}

	@Override
	public void updateImage(Image image, String type, byte[] bytes) {
		image.setTextObj(bytes);
	}

}