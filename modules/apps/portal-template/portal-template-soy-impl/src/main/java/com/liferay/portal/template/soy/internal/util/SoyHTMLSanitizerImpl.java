/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal.util;

import com.google.template.soy.data.SanitizedContent;
import com.google.template.soy.data.UnsafeSanitizedContentOrdainer;

import com.liferay.portal.template.soy.util.SoyHTMLSanitizer;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = SoyHTMLSanitizer.class)
public class SoyHTMLSanitizerImpl implements SoyHTMLSanitizer {

	@Override
	public Object sanitize(String value) {
		return UnsafeSanitizedContentOrdainer.ordainAsSafe(
			value, SanitizedContent.ContentKind.HTML);
	}

}