/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.headless.delivery.internal.dto.v1_0.util;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.io.StreamUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;

import java.io.InputStream;

import java.util.Optional;

import javax.ws.rs.core.UriInfo;

/**
 * @author Javier Gamarra
 */
public class ContentValueUtil {

	public static String toContentValue(
		String field, UnsafeSupplier<InputStream, Exception> unsafeSupplier,
		Optional<UriInfo> uriInfoOptional) {

		if (uriInfoOptional.map(
				UriInfo::getQueryParameters
			).map(
				parameters -> parameters.getFirst("nestedFields")
			).map(
				fields -> fields.contains(field)
			).orElse(
				false
			)) {

			try {
				return Base64.encode(
					StreamUtil.toByteArray(unsafeSupplier.get()));
			}
			catch (Exception exception) {
				if (_log.isWarnEnabled()) {
					_log.warn(exception, exception);
				}
			}
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ContentValueUtil.class);

}