/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.shipping.engine.fixed.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceShippingFixedOptionService}.
 *
 * @author Alessio Antonio Rendina
 * @see CommerceShippingFixedOptionService
 * @generated
 */
public class CommerceShippingFixedOptionServiceWrapper
	implements CommerceShippingFixedOptionService,
			   ServiceWrapper<CommerceShippingFixedOptionService> {

	public CommerceShippingFixedOptionServiceWrapper(
		CommerceShippingFixedOptionService commerceShippingFixedOptionService) {

		_commerceShippingFixedOptionService =
			commerceShippingFixedOptionService;
	}

	@Override
	public
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption addCommerceShippingFixedOption(
					long userId, long groupId, long commerceShippingMethodId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.math.BigDecimal amount, double priority)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionService.
			addCommerceShippingFixedOption(
				userId, groupId, commerceShippingMethodId, nameMap,
				descriptionMap, amount, priority);
	}

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	@Override
	public
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption addCommerceShippingFixedOption(
					long commerceShippingMethodId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.math.BigDecimal amount, double priority,
					com.liferay.portal.kernel.service.ServiceContext
						serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionService.
			addCommerceShippingFixedOption(
				commerceShippingMethodId, nameMap, descriptionMap, amount,
				priority, serviceContext);
	}

	@Override
	public void deleteCommerceShippingFixedOption(
			long commerceShippingFixedOptionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceShippingFixedOptionService.deleteCommerceShippingFixedOption(
			commerceShippingFixedOptionId);
	}

	@Override
	public
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption fetchCommerceShippingFixedOption(
					long commerceShippingFixedOptionId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionService.
			fetchCommerceShippingFixedOption(commerceShippingFixedOptionId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption> getCommerceShippingFixedOptions(
					long commerceShippingMethodId, int start, int end)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionService.
			getCommerceShippingFixedOptions(
				commerceShippingMethodId, start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption> getCommerceShippingFixedOptions(
					long commerceShippingMethodId, int start, int end,
					com.liferay.portal.kernel.util.OrderByComparator
						<com.liferay.commerce.shipping.engine.fixed.model.
							CommerceShippingFixedOption> orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionService.
			getCommerceShippingFixedOptions(
				commerceShippingMethodId, start, end, orderByComparator);
	}

	@Override
	public int getCommerceShippingFixedOptionsCount(
			long commerceShippingMethodId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionService.
			getCommerceShippingFixedOptionsCount(commerceShippingMethodId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceShippingFixedOptionService.getOSGiServiceIdentifier();
	}

	@Override
	public
		com.liferay.commerce.shipping.engine.fixed.model.
			CommerceShippingFixedOption updateCommerceShippingFixedOption(
					long commerceShippingFixedOptionId,
					java.util.Map<java.util.Locale, String> nameMap,
					java.util.Map<java.util.Locale, String> descriptionMap,
					java.math.BigDecimal amount, double priority)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceShippingFixedOptionService.
			updateCommerceShippingFixedOption(
				commerceShippingFixedOptionId, nameMap, descriptionMap, amount,
				priority);
	}

	@Override
	public CommerceShippingFixedOptionService getWrappedService() {
		return _commerceShippingFixedOptionService;
	}

	@Override
	public void setWrappedService(
		CommerceShippingFixedOptionService commerceShippingFixedOptionService) {

		_commerceShippingFixedOptionService =
			commerceShippingFixedOptionService;
	}

	private CommerceShippingFixedOptionService
		_commerceShippingFixedOptionService;

}