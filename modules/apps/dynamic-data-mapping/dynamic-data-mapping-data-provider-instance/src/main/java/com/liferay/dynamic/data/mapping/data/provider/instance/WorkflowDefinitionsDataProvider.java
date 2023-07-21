/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.data.provider.instance;

import com.liferay.dynamic.data.mapping.data.provider.DDMDataProvider;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderException;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest;
import com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Marcellus Tavares
 */
@Component(
	immediate = true,
	property = "ddm.data.provider.instance.id=workflow-definitions",
	service = DDMDataProvider.class
)
public class WorkflowDefinitionsDataProvider implements DDMDataProvider {

	@Override
	public DDMDataProviderResponse getData(
			DDMDataProviderRequest ddmDataProviderRequest)
		throws DDMDataProviderException {

		List<KeyValuePair> keyValuePairs = new ArrayList<>();

		Locale locale = ddmDataProviderRequest.getLocale();

		keyValuePairs.add(
			new KeyValuePair(
				"no-workflow", LanguageUtil.get(locale, "no-workflow")));

		DDMDataProviderResponse.Builder builder =
			DDMDataProviderResponse.Builder.newBuilder();

		if (workflowDefinitionManager == null) {
			builder = builder.withOutput("Default-Output", keyValuePairs);

			return builder.build();
		}

		try {
			List<WorkflowDefinition> workflowDefinitions =
				workflowDefinitionManager.getActiveWorkflowDefinitions(
					ddmDataProviderRequest.getCompanyId(), QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			String languageId = LocaleUtil.toLanguageId(locale);

			for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
				String value = workflowDefinition.getName();

				keyValuePairs.add(
					new KeyValuePair(
						value, workflowDefinition.getTitle(languageId)));
			}

			builder = builder.withOutput("Default-Output", keyValuePairs);
		}
		catch (WorkflowException workflowException) {
			throw new DDMDataProviderException(workflowException);
		}

		return builder.build();
	}

	@Override
	public Class<?> getSettings() {
		throw new UnsupportedOperationException();
	}

	@Reference(
		cardinality = ReferenceCardinality.OPTIONAL,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		target = "(proxy.bean=false)"
	)
	protected volatile WorkflowDefinitionManager workflowDefinitionManager;

}