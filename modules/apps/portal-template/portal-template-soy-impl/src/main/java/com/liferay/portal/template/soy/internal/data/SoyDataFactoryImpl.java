/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal.data;

import com.liferay.portal.template.soy.data.SoyDataFactory;
import com.liferay.portal.template.soy.data.SoyHTMLData;
import com.liferay.portal.template.soy.util.SoyRawData;

import org.osgi.service.component.annotations.Component;

/**
 * @author Iván Zaera Avellón
 */
@Component(service = SoyDataFactory.class)
public class SoyDataFactoryImpl implements SoyDataFactory {

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #createSoyRawData(String)}
	 */
	@Deprecated
	@Override
	public SoyHTMLData createSoyHTMLData(String html) {
		return new SoyHTMLDataImpl(html);
	}

	@Override
	public SoyRawData createSoyRawData(String html) {
		return new SoyRawDataImpl(html);
	}

}