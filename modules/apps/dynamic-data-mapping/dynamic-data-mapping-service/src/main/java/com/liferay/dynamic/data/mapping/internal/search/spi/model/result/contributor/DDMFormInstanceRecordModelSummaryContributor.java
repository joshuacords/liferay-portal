/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.internal.search.spi.model.result.contributor;

import com.liferay.dynamic.data.mapping.model.DDMFormInstance;
import com.liferay.dynamic.data.mapping.service.DDMFormInstanceLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ResourceBundleLoader;
import com.liferay.portal.kernel.util.ResourceBundleLoaderUtil;
import com.liferay.portal.search.spi.model.result.contributor.ModelSummaryContributor;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rafael Praxedes
 */
@Component(
	immediate = true,
	property = "indexer.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstanceRecord",
	service = ModelSummaryContributor.class
)
public class DDMFormInstanceRecordModelSummaryContributor
	implements ModelSummaryContributor {

	@Override
	public Summary getSummary(
		Document document, Locale locale, String snippet) {

		long ddmFormInstanceId = GetterUtil.getLong(
			document.get("formInstanceId"));

		String prefix = Field.SNIPPET + StringPool.UNDERLINE;

		String description = document.get(
			locale, prefix + Field.DESCRIPTION, Field.DESCRIPTION);

		Summary summary = new Summary(
			getTitle(ddmFormInstanceId, locale), description);

		summary.setMaxContentLength(200);

		return summary;
	}

	protected ResourceBundle getResourceBundle(Locale defaultLocale) {
		ResourceBundleLoader portalResourceBundleLoader =
			ResourceBundleLoaderUtil.getPortalResourceBundleLoader();

		return portalResourceBundleLoader.loadResourceBundle(defaultLocale);
	}

	protected String getTitle(long ddmFormInstanceId, Locale locale) {
		try {
			DDMFormInstance ddmFormInstance =
				ddmFormInstanceLocalService.getFormInstance(ddmFormInstanceId);

			String ddmFormInstanceName = ddmFormInstance.getName(locale);

			return LanguageUtil.format(
				getResourceBundle(locale), "form-record-for-form-x",
				ddmFormInstanceName, false);
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return StringPool.BLANK;
	}

	@Reference
	protected DDMFormInstanceLocalService ddmFormInstanceLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormInstanceRecordModelSummaryContributor.class);

}