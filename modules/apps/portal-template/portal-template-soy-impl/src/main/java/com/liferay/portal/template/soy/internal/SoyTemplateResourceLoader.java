/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResourceLoader;
import com.liferay.portal.template.BaseTemplateResourceLoader;
import com.liferay.portal.template.TemplateResourceParser;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Miroslav Ligas
 */
@Component(
	immediate = true,
	service = {SoyTemplateResourceLoader.class, TemplateResourceLoader.class}
)
public class SoyTemplateResourceLoader extends BaseTemplateResourceLoader {

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		init(
			TemplateConstants.LANG_TYPE_SOY, _templateResourceParsers,
			_soyTemplateResourceCache);
	}

	@Deactivate
	protected void deactivate() {
		destroy();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(lang.type=" + TemplateConstants.LANG_TYPE_SOY + ")"
	)
	protected void setTemplateResourceParser(
		TemplateResourceParser templateResourceParser) {

		_templateResourceParsers.add(templateResourceParser);
	}

	protected void unsetTemplateResourceParser(
		TemplateResourceParser templateResourceParser) {

		_templateResourceParsers.remove(templateResourceParser);
	}

	@Reference
	private SoyTemplateResourceCache _soyTemplateResourceCache;

	private final Set<TemplateResourceParser> _templateResourceParsers =
		Collections.newSetFromMap(new ConcurrentHashMap<>());

}