/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.adaptive.media.image.internal.validator;

import com.liferay.adaptive.media.image.internal.configuration.AMImageConfiguration;
import com.liferay.adaptive.media.image.mime.type.AMImageMimeTypeProvider;
import com.liferay.adaptive.media.image.validator.AMImageValidator;
import com.liferay.document.library.kernel.util.DLProcessorRegistryUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Sergio González
 */
@Component(
	configurationPid = "com.liferay.adaptive.media.image.internal.configuration.AMImageConfiguration",
	service = AMImageValidator.class
)
public class AMImageValidatorImpl implements AMImageValidator {

	@Override
	public boolean isProcessingSupported(FileVersion fileVersion) {
		if (!isValid(fileVersion)) {
			return false;
		}

		if (Objects.equals(
				fileVersion.getMimeType(), ContentTypes.IMAGE_SVG_XML)) {

			return false;
		}

		return true;
	}

	@Override
	public boolean isProcessingSupported(String mimeType) {
		if (StringUtil.equalsIgnoreCase(mimeType, ContentTypes.IMAGE_SVG_XML)) {
			return false;
		}

		return true;
	}

	@Override
	public boolean isValid(FileVersion fileVersion) {
		if (!DLProcessorRegistryUtil.isPreviewableSize(fileVersion)) {
			return false;
		}

		long imageMaxSize = _amImageConfiguration.imageMaxSize();

		if ((imageMaxSize != -1) &&
			((imageMaxSize == 0) || (fileVersion.getSize() == 0) ||
			 (fileVersion.getSize() >= imageMaxSize))) {

			return false;
		}

		if (!_amImageMimeTypeProvider.isMimeTypeSupported(
				fileVersion.getMimeType())) {

			return false;
		}

		return true;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_amImageConfiguration = ConfigurableUtil.createConfigurable(
			AMImageConfiguration.class, properties);
	}

	private volatile AMImageConfiguration _amImageConfiguration;

	@Reference
	private AMImageMimeTypeProvider _amImageMimeTypeProvider;

}