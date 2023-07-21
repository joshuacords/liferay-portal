/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipping.engine.fixed.service.impl;

import com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption;
import com.liferay.commerce.shipping.engine.fixed.service.base.CommerceShippingFixedOptionLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.math.BigDecimal;

import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alessio Antonio Rendina
 */
public class CommerceShippingFixedOptionLocalServiceImpl
	extends CommerceShippingFixedOptionLocalServiceBaseImpl {

	@Override
	public CommerceShippingFixedOption addCommerceShippingFixedOption(
			long userId, long groupId, long commerceShippingMethodId,
			Map<Locale, String> nameMap, Map<Locale, String> descriptionMap,
			BigDecimal amount, double priority)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		long commerceShippingFixedOptionId = counterLocalService.increment();

		CommerceShippingFixedOption commerceShippingFixedOption =
			commerceShippingFixedOptionPersistence.create(
				commerceShippingFixedOptionId);

		commerceShippingFixedOption.setGroupId(groupId);
		commerceShippingFixedOption.setCompanyId(user.getCompanyId());
		commerceShippingFixedOption.setUserId(user.getUserId());
		commerceShippingFixedOption.setUserName(user.getFullName());
		commerceShippingFixedOption.setCommerceShippingMethodId(
			commerceShippingMethodId);
		commerceShippingFixedOption.setNameMap(nameMap);
		commerceShippingFixedOption.setDescriptionMap(descriptionMap);
		commerceShippingFixedOption.setAmount(amount);
		commerceShippingFixedOption.setPriority(priority);

		return commerceShippingFixedOptionPersistence.update(
			commerceShippingFixedOption);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	@Override
	public CommerceShippingFixedOption addCommerceShippingFixedOption(
			long commerceShippingMethodId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, BigDecimal amount,
			double priority, ServiceContext serviceContext)
		throws PortalException {

		return commerceShippingFixedOptionLocalService.
			addCommerceShippingFixedOption(
				serviceContext.getUserId(), serviceContext.getScopeGroupId(),
				commerceShippingMethodId, nameMap, descriptionMap, amount,
				priority);
	}

	@Override
	public CommerceShippingFixedOption deleteCommerceShippingFixedOption(
		CommerceShippingFixedOption commerceShippingFixedOption) {

		// Commerce shipping fixed option

		commerceShippingFixedOptionPersistence.remove(
			commerceShippingFixedOption);

		// Commerce shipping fixed option rels

		commerceShippingFixedOptionRelLocalService.
			deleteCommerceShippingFixedOptionRels(
				commerceShippingFixedOption.getCommerceShippingFixedOptionId());

		return commerceShippingFixedOption;
	}

	@Override
	public void deleteCommerceShippingFixedOptions(
		long commerceShippingMethodId) {

		commerceShippingFixedOptionPersistence.removeByCommerceShippingMethodId(
			commerceShippingMethodId);
	}

	@Override
	public List<CommerceShippingFixedOption> getCommerceShippingFixedOptions(
		long commerceShippingMethodId, int start, int end) {

		return commerceShippingFixedOptionPersistence.
			findByCommerceShippingMethodId(
				commerceShippingMethodId, start, end);
	}

	@Override
	public List<CommerceShippingFixedOption> getCommerceShippingFixedOptions(
		long commerceShippingMethodId, int start, int end,
		OrderByComparator<CommerceShippingFixedOption> orderByComparator) {

		return commerceShippingFixedOptionPersistence.
			findByCommerceShippingMethodId(
				commerceShippingMethodId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceShippingFixedOptionsCount(
		long commerceShippingMethodId) {

		return commerceShippingFixedOptionPersistence.
			countByCommerceShippingMethodId(commerceShippingMethodId);
	}

	@Override
	public CommerceShippingFixedOption updateCommerceShippingFixedOption(
			long commerceShippingFixedOptionId, Map<Locale, String> nameMap,
			Map<Locale, String> descriptionMap, BigDecimal amount,
			double priority)
		throws PortalException {

		CommerceShippingFixedOption commerceShippingFixedOption =
			commerceShippingFixedOptionPersistence.findByPrimaryKey(
				commerceShippingFixedOptionId);

		commerceShippingFixedOption.setNameMap(nameMap);
		commerceShippingFixedOption.setDescriptionMap(descriptionMap);
		commerceShippingFixedOption.setAmount(amount);
		commerceShippingFixedOption.setPriority(priority);

		return commerceShippingFixedOptionPersistence.update(
			commerceShippingFixedOption);
	}

}