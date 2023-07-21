/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.template.soy.SoyTemplateResource;
import com.liferay.portal.template.soy.SoyTemplateResourceFactory;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Tina Tian
 */
@Component(immediate = true, service = SoyTemplateResourceFactory.class)
public class SoyTemplateResourceFactoryImpl
	implements SoyTemplateResourceFactory {

	@Override
	public SoyTemplateResource createSoyTemplateResource(
		List<TemplateResource> templateResources) {

		return new SoyTemplateResourceImpl(templateResources);
	}

}