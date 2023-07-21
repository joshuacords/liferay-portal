/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.machine.learning.internal.recommendation.data.source;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.machine.learning.recommendation.model.ProductContentCommerceMLRecommendation;
import com.liferay.commerce.machine.learning.recommendation.service.ProductContentCommerceMLRecommendationService;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.data.source.CPDataSource;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "commerce.product.data.source.name=" + ProductContentCommerceMLRecommendationCPDataSourceImpl.NAME,
	service = CPDataSource.class
)
public class ProductContentCommerceMLRecommendationCPDataSourceImpl
	extends BaseCommerceMLRecommendationCPDataSource {

	public static final String NAME =
		"productContentCommerceMLRecommendationDataSource";

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			getResourceBundle(locale), "product-content-based-recommendations");
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public CPDataSourceResult getResult(
			HttpServletRequest httpServletRequest, int start, int end)
		throws Exception {

		CommerceAccount commerceAccount =
			commerceAccountHelper.getCurrentCommerceAccount(httpServletRequest);

		if (commerceAccount == null) {
			return new CPDataSourceResult(Collections.emptyList(), 0);
		}

		CPCatalogEntry cpCatalogEntry =
			(CPCatalogEntry)httpServletRequest.getAttribute(
				CPWebKeys.CP_CATALOG_ENTRY);

		if (cpCatalogEntry == null) {
			return new CPDataSourceResult(Collections.emptyList(), 0);
		}

		long companyId = portal.getCompanyId(httpServletRequest);

		List<ProductContentCommerceMLRecommendation>
			productContentCommerceMLRecommendations =
				_productContentCommerceMLRecommendationService.
					getProductContentCommerceMLRecommendations(
						companyId, cpCatalogEntry.getCPDefinitionId());

		if (productContentCommerceMLRecommendations.isEmpty()) {
			return new CPDataSourceResult(Collections.emptyList(), 0);
		}

		long groupId = portal.getScopeGroupId(httpServletRequest);

		List<CPCatalogEntry> cpCatalogEntries = new ArrayList<>();

		List<ProductContentCommerceMLRecommendation>
			productContentCommerceMLRecommendationList = ListUtil.subList(
				productContentCommerceMLRecommendations, start, end);

		for (ProductContentCommerceMLRecommendation
				prodcutContentCommerceMLRecommendation :
					productContentCommerceMLRecommendationList) {

			long recommendedEntryClassPK =
				prodcutContentCommerceMLRecommendation.
					getRecommendedEntryClassPK();

			if (_log.isTraceEnabled()) {
				StringBuilder sb = new StringBuilder();

				sb.append("Recommended item: ");
				sb.append(recommendedEntryClassPK);
				sb.append(" rank: ");
				sb.append(prodcutContentCommerceMLRecommendation.getRank());
				sb.append(" score: ");
				sb.append(prodcutContentCommerceMLRecommendation.getScore());

				_log.trace(sb.toString());
			}

			try {
				CPCatalogEntry recommendedCPCatalogEntry =
					cpDefinitionHelper.getCPCatalogEntry(
						commerceAccount.getCommerceAccountId(), groupId,
						recommendedEntryClassPK,
						portal.getLocale(httpServletRequest));

				cpCatalogEntries.add(recommendedCPCatalogEntry);
			}
			catch (PortalException e) {
				if (_log.isDebugEnabled()) {
					_log.debug(e, e);
				}
			}
		}

		return new CPDataSourceResult(
			cpCatalogEntries, productContentCommerceMLRecommendations.size());
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductContentCommerceMLRecommendationCPDataSourceImpl.class);

	@Reference(unbind = "-")
	private ProductContentCommerceMLRecommendationService
		_productContentCommerceMLRecommendationService;

}