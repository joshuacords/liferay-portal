/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.seo.internal;

import com.liferay.layout.seo.internal.configuration.LayoutSEOCompanyConfiguration;
import com.liferay.layout.seo.kernel.LayoutSEOLink;
import com.liferay.layout.seo.kernel.LayoutSEOLinkManager;
import com.liferay.layout.seo.model.LayoutSEOEntry;
import com.liferay.layout.seo.service.LayoutSEOEntryLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Cristina González
 */
@Component(service = LayoutSEOLinkManager.class)
public class LayoutSEOLinkManagerImpl implements LayoutSEOLinkManager {

	@Override
	public List<LayoutSEOLink> getLocalizedLayoutSEOLinks(
			Layout layout, Locale locale, String canonicalURL,
			Map<Locale, String> alternateURLs)
		throws PortalException {

		List<LayoutSEOLink> layoutSEOLinks = new ArrayList<>(
			alternateURLs.size() + 2);

		layoutSEOLinks.add(
			new LayoutSEOLinkImpl(
				_html.escapeAttribute(
					_getCanonicalURL(
						layout, locale, canonicalURL, alternateURLs)),
				null, LayoutSEOLink.Relationship.CANONICAL));

		alternateURLs.forEach(
			(urlLocale, url) -> layoutSEOLinks.add(
				new LayoutSEOLinkImpl(
					_html.escapeAttribute(url),
					LocaleUtil.toW3cLanguageId(urlLocale),
					LayoutSEOLink.Relationship.ALTERNATE)));

		String defaultLocaleURL = alternateURLs.get(
			_portal.getSiteDefaultLocale(layout.getGroupId()));

		if (defaultLocaleURL == null) {
			return layoutSEOLinks;
		}

		layoutSEOLinks.add(
			new LayoutSEOLinkImpl(
				_html.escapeAttribute(defaultLocaleURL), "x-default",
				LayoutSEOLink.Relationship.ALTERNATE));

		return layoutSEOLinks;
	}

	private String _getCanonicalURL(
			Layout layout, Locale locale, String canonicalURL,
			Map<Locale, String> alternateURLs)
		throws ConfigurationException {

		String layoutCanonicalURL = _getLayoutCanonicalURL(locale, layout);

		if (Validator.isNotNull(layoutCanonicalURL)) {
			return layoutCanonicalURL;
		}

		LayoutSEOCompanyConfiguration layoutSEOCompanyConfiguration =
			_configurationProvider.getCompanyConfiguration(
				LayoutSEOCompanyConfiguration.class, layout.getCompanyId());

		if (Objects.equals(
				layoutSEOCompanyConfiguration.canonicalURL(),
				"default-language-url")) {

			return canonicalURL;
		}

		return alternateURLs.getOrDefault(locale, canonicalURL);
	}

	private String _getLayoutCanonicalURL(Locale locale, Layout layout) {
		LayoutSEOEntry layoutSEOEntry =
			_layoutSEOEntryLocalService.fetchLayoutSEOEntry(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId());

		if ((layoutSEOEntry == null) || !layoutSEOEntry.isEnabled()) {
			return StringPool.BLANK;
		}

		return layoutSEOEntry.getCanonicalURL(locale);
	}

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private Html _html;

	@Reference
	private LayoutSEOEntryLocalService _layoutSEOEntryLocalService;

	@Reference
	private Portal _portal;

}