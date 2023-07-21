/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.pricing.web.internal.frontend;

import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.DefaultFilterImpl;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.service.CommercePricingClassCPDefinitionRelService;
import com.liferay.commerce.pricing.service.CommercePricingClassService;
import com.liferay.commerce.pricing.web.internal.frontend.constants.CommercePricingDataSetConstants;
import com.liferay.commerce.pricing.web.internal.model.PricingClass;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.FastDateFormatFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.text.DateFormat;
import java.text.Format;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = "commerce.data.provider.key=" + CommercePricingDataSetConstants.COMMERCE_DATA_SET_KEY_PRICING_CLASSES,
	service = CommerceDataSetDataProvider.class
)
public class CommercePricingClassDataSetDataProvider
	implements CommerceDataSetDataProvider<PricingClass> {

	@Override
	public int countItems(HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		DefaultFilterImpl defaultFilterImpl = (DefaultFilterImpl)filter;

		String keywords = defaultFilterImpl.getKeywords();

		if (Validator.isNotNull(keywords)) {
			BaseModelSearchResult<CommercePricingClass> baseModelSearchResult =
				_getBaseModelSearchResult(
					themeDisplay.getCompanyId(), keywords, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null);

			return baseModelSearchResult.getLength();
		}

		return _commercePricingClassService.getCommercePricingClassesCount(
			themeDisplay.getCompanyId());
	}

	@Override
	public List<PricingClass> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		DefaultFilterImpl defaultFilterImpl = (DefaultFilterImpl)filter;

		List<PricingClass> pricingClasses = new ArrayList<>();

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		Format dateTimeFormat = FastDateFormatFactoryUtil.getDateTime(
			DateFormat.MEDIUM, DateFormat.MEDIUM, themeDisplay.getLocale(),
			themeDisplay.getTimeZone());

		long companyId = themeDisplay.getCompanyId();

		List<CommercePricingClass> commercePricingClasses =
			_getCommercePricingClasses(
				companyId, defaultFilterImpl.getKeywords(),
				pagination.getStartPosition(), pagination.getEndPosition(),
				sort);

		for (CommercePricingClass commercePricingClass :
				commercePricingClasses) {

			pricingClasses.add(
				new PricingClass(
					commercePricingClass.getCommercePricingClassId(),
					commercePricingClass.getTitle(themeDisplay.getLocale()),
					_commercePricingClassCPDefinitionRelService.
						getCommercePricingClassCPDefinitionRelsCount(
							commercePricingClass.getCommercePricingClassId()),
					dateTimeFormat.format(
						commercePricingClass.getLastPublishDate())));
		}

		return pricingClasses;
	}

	private BaseModelSearchResult<CommercePricingClass>
			_getBaseModelSearchResult(
				long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		return _commercePricingClassService.searchCommercePricingClasses(
			companyId, keywords, start, end, sort);
	}

	private List<CommercePricingClass> _getCommercePricingClasses(
			long companyId, String keywords, int start, int end, Sort sort)
		throws PortalException {

		if (Validator.isNotNull(keywords)) {
			BaseModelSearchResult<CommercePricingClass> baseModelSearchResult =
				_getBaseModelSearchResult(
					companyId, keywords, start, end, sort);

			return baseModelSearchResult.getBaseModels();
		}

		return _commercePricingClassService.getCommercePricingClasses(
			companyId, start, end, null);
	}

	@Reference
	private CommercePricingClassCPDefinitionRelService
		_commercePricingClassCPDefinitionRelService;

	@Reference
	private CommercePricingClassService _commercePricingClassService;

}