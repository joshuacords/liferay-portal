/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.internal;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.liferay.portal.template.TemplateContextHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	service = {SoyTemplateContextHelper.class, TemplateContextHelper.class}
)
public class SoyTemplateContextHelper extends TemplateContextHelper {

	public Object deserializeValue(Object value) {
		String json = JSONFactoryUtil.looseSerializeDeep(value);

		return JSONFactoryUtil.looseDeserialize(json);
	}

	@Override
	public Map<String, Object> getHelperUtilities(
		ClassLoader classLoader, boolean restricted) {

		return Collections.emptyMap();
	}

	@Override
	public Set<String> getRestrictedVariables() {
		return _restrictedVariables;
	}

	@Override
	public void prepare(
		Map<String, Object> contextObjects,
		HttpServletRequest httpServletRequest) {

		// Custom template context contributors

		for (TemplateContextContributor templateContextContributor :
				_templateContextContributors) {

			templateContextContributor.prepare(
				contextObjects, httpServletRequest);
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(&(lang.type=" + TemplateConstants.LANG_TYPE_SOY + ")(type=" + TemplateContextContributor.TYPE_GLOBAL + "))"
	)
	protected void registerTemplateContextContributor(
		TemplateContextContributor templateContextContributor) {

		_templateContextContributors.add(templateContextContributor);
	}

	protected void unregisterTemplateContextContributor(
		TemplateContextContributor templateContextContributor) {

		_templateContextContributors.remove(templateContextContributor);
	}

	private static final Set<String> _restrictedVariables =
		Collections.singleton(TemplateConstants.NAMESPACE);

	private final List<TemplateContextContributor>
		_templateContextContributors = new CopyOnWriteArrayList<>();

}