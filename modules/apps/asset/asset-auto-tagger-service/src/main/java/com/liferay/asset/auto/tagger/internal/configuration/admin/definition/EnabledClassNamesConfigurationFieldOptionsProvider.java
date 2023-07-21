/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.auto.tagger.internal.configuration.admin.definition;

import com.liferay.asset.auto.tagger.text.extractor.TextExtractor;
import com.liferay.asset.auto.tagger.text.extractor.TextExtractorTracker;
import com.liferay.asset.kernel.AssetRendererFactoryRegistryUtil;
import com.liferay.asset.kernel.model.AssetRendererFactory;
import com.liferay.configuration.admin.definition.ConfigurationFieldOptionsProvider;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(
	property = {
		"configuration.field.name=enabledClassNames",
		"configuration.pid=com.liferay.asset.auto.tagger.google.cloud.natural.language.internal.configuration.GCloudNaturalLanguageAssetAutoTaggerCompanyConfiguration",
		"configuration.pid=com.liferay.asset.auto.tagger.opennlp.internal.configuration.OpenNLPDocumentAssetAutoTaggerCompanyConfiguration"
	},
	service = ConfigurationFieldOptionsProvider.class
)
public class EnabledClassNamesConfigurationFieldOptionsProvider
	implements ConfigurationFieldOptionsProvider {

	public List<Option> getOptions() {
		List<AssetRendererFactory<?>> assetRendererFactories =
			AssetRendererFactoryRegistryUtil.getAssetRendererFactories(
				CompanyThreadLocal.getCompanyId());

		Stream<AssetRendererFactory<?>> stream =
			assetRendererFactories.stream();

		return stream.filter(
			assetRendererFactory -> {
				TextExtractor textExtractor =
					_textExtractorTracker.getTextExtractor(
						assetRendererFactory.getClassName());

				return textExtractor != null;
			}
		).map(
			assetRendererFactory -> new Option() {

				@Override
				public String getLabel(Locale locale) {
					return assetRendererFactory.getTypeName(locale);
				}

				@Override
				public String getValue() {
					return assetRendererFactory.getClassName();
				}

			}
		).collect(
			Collectors.toList()
		);
	}

	@Reference
	private TextExtractorTracker _textExtractorTracker;

}