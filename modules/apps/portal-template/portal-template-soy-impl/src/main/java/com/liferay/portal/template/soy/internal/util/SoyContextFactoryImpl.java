/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal.util;

import com.liferay.portal.template.soy.internal.SoyContextImpl;
import com.liferay.portal.template.soy.util.SoyContext;
import com.liferay.portal.template.soy.util.SoyContextFactory;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Matthew Tambara
 */
@Component(immediate = true, service = SoyContextFactory.class)
public class SoyContextFactoryImpl implements SoyContextFactory {

	@Override
	public SoyContext createSoyContext() {
		return new SoyContextImpl();
	}

	@Override
	public SoyContext createSoyContext(Map<String, Object> context) {
		return new SoyContextImpl(context);
	}

}