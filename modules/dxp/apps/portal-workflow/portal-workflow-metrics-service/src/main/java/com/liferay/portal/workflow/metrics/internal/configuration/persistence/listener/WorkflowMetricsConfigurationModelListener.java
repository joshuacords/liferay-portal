/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.metrics.internal.configuration.persistence.listener;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListener;
import com.liferay.portal.configuration.persistence.listener.ConfigurationModelListenerException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.workflow.metrics.internal.configuration.WorkflowMetricsConfiguration;

import java.util.Dictionary;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.portal.workflow.metrics.internal.configuration.WorkflowMetricsConfiguration",
	service = ConfigurationModelListener.class
)
public class WorkflowMetricsConfigurationModelListener
	implements ConfigurationModelListener {

	@Override
	public void onBeforeSave(String pid, Dictionary<String, Object> properties)
		throws ConfigurationModelListenerException {

		WorkflowMetricsConfiguration ddmFormWebConfiguration =
			ConfigurableUtil.createConfigurable(
				WorkflowMetricsConfiguration.class, new HashMapDictionary<>());

		try {
			int checkSLAJobInterval = GetterUtil.getInteger(
				properties.get("checkSLAJobInterval"),
				ddmFormWebConfiguration.checkSLAJobInterval());

			_validateCheckSLAJobInterval(checkSLAJobInterval);
		}
		catch (Exception exception) {
			throw new ConfigurationModelListenerException(
				exception.getMessage(), WorkflowMetricsConfiguration.class,
				getClass(), properties);
		}
	}

	private void _validateCheckSLAJobInterval(int checkSLAJobInterval)
		throws Exception {

		if (checkSLAJobInterval <= 0) {
			ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
				"content.Language", LocaleThreadLocal.getThemeDisplayLocale(),
				getClass());

			String message = ResourceBundleUtil.getString(
				resourceBundle, "the-job-interval-must-be-greater-than-0");

			throw new Exception(message);
		}
	}

}