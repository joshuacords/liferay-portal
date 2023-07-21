/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.product.service.impl;

import com.liferay.commerce.product.model.CommerceChannelRel;
import com.liferay.commerce.product.service.base.CommerceChannelRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceChannelRelServiceImpl
	extends CommerceChannelRelServiceBaseImpl {

	@Override
	public CommerceChannelRel addCommerceChannelRel(
			String className, long classPK, long commerceChannelId,
			ServiceContext serviceContext)
		throws PortalException {

		return commerceChannelRelLocalService.addCommerceChannelRel(
			className, classPK, commerceChannelId, serviceContext);
	}

	@Override
	public void deleteCommerceChannelRel(long commerceChannelRelId)
		throws PortalException {

		commerceChannelRelLocalService.deleteCommerceChannelRel(
			commerceChannelRelId);
	}

	@Override
	public void deleteCommerceChannelRels(String className, long classPK) {
		commerceChannelRelLocalService.deleteCommerceChannelRels(
			className, classPK);
	}

	@Override
	public CommerceChannelRel fetchCommerceChannelRel(
		String className, long classPK, long commerceChannelId) {

		return commerceChannelRelLocalService.fetchCommerceChannelRel(
			className, classPK, commerceChannelId);
	}

	@Override
	public CommerceChannelRel getCommerceChannelRel(long commerceChannelRelId)
		throws PortalException {

		return commerceChannelRelLocalService.getCommerceChannelRel(
			commerceChannelRelId);
	}

	@Override
	public List<CommerceChannelRel> getCommerceChannelRels(
		long commerceChannelId, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		return commerceChannelRelLocalService.getCommerceChannelRels(
			commerceChannelId, start, end, orderByComparator);
	}

	@Override
	public List<CommerceChannelRel> getCommerceChannelRels(
		String className, long classPK, int start, int end,
		OrderByComparator<CommerceChannelRel> orderByComparator) {

		return commerceChannelRelLocalService.getCommerceChannelRels(
			className, classPK, start, end, orderByComparator);
	}

	@Override
	public List<CommerceChannelRel> getCommerceChannelRels(
		String className, long classPK, String classPKField, String name,
		int start, int end) {

		return commerceChannelRelFinder.findByC_C(
			className, classPK, classPKField, name, start, end, true);
	}

	@Override
	public int getCommerceChannelRelsCount(long commerceChannelId) {
		return commerceChannelRelLocalService.getCommerceChannelRelsCount(
			commerceChannelId);
	}

	@Override
	public int getCommerceChannelRelsCount(String className, long classPK) {
		return commerceChannelRelLocalService.getCommerceChannelRelsCount(
			className, classPK);
	}

	@Override
	public int getCommerceChannelRelsCount(
		String className, long classPK, String classPKField, String name) {

		return commerceChannelRelFinder.countByC_C(
			className, classPK, classPKField, name, true);
	}

}