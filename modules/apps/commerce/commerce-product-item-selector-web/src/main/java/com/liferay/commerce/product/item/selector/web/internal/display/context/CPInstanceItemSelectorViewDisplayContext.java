/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.item.selector.web.internal.display.context;

import com.liferay.commerce.product.item.selector.web.internal.CPInstanceItemSelectorView;
import com.liferay.commerce.product.item.selector.web.internal.search.CPInstanceItemSelectorChecker;
import com.liferay.commerce.product.item.selector.web.internal.util.CPItemSelectorViewUtil;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceService;
import com.liferay.portal.kernel.dao.search.RowChecker;
import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.List;

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class CPInstanceItemSelectorViewDisplayContext
	extends BaseCPItemSelectorViewDisplayContext<CPInstance> {

	public CPInstanceItemSelectorViewDisplayContext(
		HttpServletRequest httpServletRequest, PortletURL portletURL,
		String itemSelectedEventName, CPInstanceService cpInstanceService) {

		super(
			httpServletRequest, portletURL, itemSelectedEventName,
			CPInstanceItemSelectorView.class.getSimpleName());

		_cpInstanceService = cpInstanceService;

		setDefaultOrderByCol("sku");
	}

	@Override
	public PortletURL getPortletURL() {
		PortletURL portletURL = super.getPortletURL();

		portletURL.setParameter(
			"checkedCPInstanceIds", String.valueOf(getCheckedCPInstanceIds()));
		portletURL.setParameter(
			"commerceCatalogGroupId", String.valueOf(getGroupId()));

		return portletURL;
	}

	@Override
	public SearchContainer<CPInstance> getSearchContainer()
		throws PortalException {

		if (searchContainer != null) {
			return searchContainer;
		}

		searchContainer = new SearchContainer<>(
			liferayPortletRequest, getPortletURL(), null, null);

		searchContainer.setEmptyResultsMessage("no-skus-were-found");

		OrderByComparator<CPInstance> orderByComparator =
			CPItemSelectorViewUtil.getCPInstanceOrderByComparator(
				getOrderByCol(), getOrderByType());

		RowChecker rowChecker = new CPInstanceItemSelectorChecker(
			cpRequestHelper.getRenderResponse(), getCheckedCPInstanceIds());

		searchContainer.setOrderByCol(getOrderByCol());
		searchContainer.setOrderByComparator(orderByComparator);
		searchContainer.setOrderByType(getOrderByType());
		searchContainer.setRowChecker(rowChecker);

		Sort sort = CPItemSelectorViewUtil.getCPInstanceSort(
			getOrderByCol(), getOrderByType());

		BaseModelSearchResult<CPInstance> cpInstanceBaseModelSearchResult;

		if (getGroupId() > 0) {
			cpInstanceBaseModelSearchResult =
				_cpInstanceService.searchCPInstances(
					cpRequestHelper.getCompanyId(), getGroupId(), getKeywords(),
					WorkflowConstants.STATUS_APPROVED,
					searchContainer.getStart(), searchContainer.getEnd(), sort);
		}
		else {
			cpInstanceBaseModelSearchResult =
				_cpInstanceService.searchCPInstances(
					cpRequestHelper.getCompanyId(), getKeywords(),
					WorkflowConstants.STATUS_APPROVED,
					searchContainer.getStart(), searchContainer.getEnd(), sort);
		}

		List<CPInstance> cpInstances =
			cpInstanceBaseModelSearchResult.getBaseModels();
		int totalCPInstances = cpInstanceBaseModelSearchResult.getLength();

		searchContainer.setResults(cpInstances);
		searchContainer.setTotal(totalCPInstances);

		return searchContainer;
	}

	protected long[] getCheckedCPInstanceIds() {
		return ParamUtil.getLongValues(
			httpServletRequest, "checkedCPInstanceIds");
	}

	protected long getGroupId() {
		return ParamUtil.getLong(httpServletRequest, "commerceCatalogGroupId");
	}

	private final CPInstanceService _cpInstanceService;

}