/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.documentlibrary.messaging;

import com.liferay.document.library.kernel.util.PDFProcessorUtil;
import com.liferay.portal.kernel.repository.model.FileVersion;

/**
 * @author Alexander Chow
 */
public class PDFProcessorMessageListener extends BaseProcessorMessageListener {

	@Override
	protected void generate(
			FileVersion sourceFileVersion, FileVersion destinationFileVersion)
		throws Exception {

		PDFProcessorUtil.generateImages(
			sourceFileVersion, destinationFileVersion);
	}

}