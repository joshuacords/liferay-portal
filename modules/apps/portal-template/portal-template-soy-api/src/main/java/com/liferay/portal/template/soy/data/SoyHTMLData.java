/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.data;

import com.liferay.portal.template.soy.util.SoyRawData;

/**
 * Objects of this type contain sanitized HTML suitable for its use in Soy
 * templates.
 *
 * Use the {@link SoyDataFactory} OSGi service to create objects of this class.
 *
 * @author     Iván Zaera Avellón
 * @deprecated As of Mueller (7.2.x), , replaced by {@link SoyRawData}
 * @review
 */
@Deprecated
public interface SoyHTMLData extends SoyRawData {
}