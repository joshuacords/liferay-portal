/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.checkout.web.internal.util;

import com.liferay.commerce.checkout.web.internal.util.comparator.CommerceCheckoutStepServiceWrapperOrderComparator;
import com.liferay.commerce.util.CommerceCheckoutStep;
import com.liferay.commerce.util.CommerceCheckoutStepServicesTracker;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory.ServiceWrapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Marco Leo
 */
@Component(
	immediate = true, service = CommerceCheckoutStepServicesTracker.class
)
public class CommerceCheckoutStepServicesTrackerImpl
	implements CommerceCheckoutStepServicesTracker {

	@Override
	public CommerceCheckoutStep getCommerceCheckoutStep(
		String commerceCheckoutStepName) {

		if (Validator.isNull(commerceCheckoutStepName)) {
			return null;
		}

		ServiceWrapper<CommerceCheckoutStep>
			commerceCheckoutStepServiceWrapper =
				_commerceCheckoutStepServiceTrackerMap.getService(
					commerceCheckoutStepName);

		if (commerceCheckoutStepServiceWrapper == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(
					"No commerce checkout step registered with name " +
						commerceCheckoutStepName);
			}

			return null;
		}

		return commerceCheckoutStepServiceWrapper.getService();
	}

	@Override
	public List<CommerceCheckoutStep> getCommerceCheckoutSteps(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		List<CommerceCheckoutStep> commerceCheckoutSteps = new ArrayList<>();

		List<ServiceWrapper<CommerceCheckoutStep>>
			commerceCheckoutStepServiceWrappers = ListUtil.fromCollection(
				_commerceCheckoutStepServiceTrackerMap.values());

		Collections.sort(
			commerceCheckoutStepServiceWrappers,
			_commerceCheckoutStepServiceWrapperDisplayOrderComparator);

		for (ServiceWrapper<CommerceCheckoutStep>
				commerceCheckoutStepServiceWrapper :
					commerceCheckoutStepServiceWrappers) {

			CommerceCheckoutStep commerceCheckoutStep =
				commerceCheckoutStepServiceWrapper.getService();

			if (commerceCheckoutStep.isActive(
					httpServletRequest, httpServletResponse)) {

				commerceCheckoutSteps.add(commerceCheckoutStep);
			}
		}

		return Collections.unmodifiableList(commerceCheckoutSteps);
	}

	@Override
	public CommerceCheckoutStep getNextCommerceCheckoutStep(
			String commerceCheckoutStepName,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		if (Validator.isNull(commerceCheckoutStepName)) {
			return null;
		}

		List<CommerceCheckoutStep> commerceCheckoutSteps =
			getCommerceCheckoutSteps(httpServletRequest, httpServletResponse);

		int commerceCheckoutStepIndex = commerceCheckoutSteps.indexOf(
			getCommerceCheckoutStep(commerceCheckoutStepName));

		if ((commerceCheckoutStepIndex >= 0) &&
			(commerceCheckoutStepIndex < (commerceCheckoutSteps.size() - 1))) {

			return commerceCheckoutSteps.get(commerceCheckoutStepIndex + 1);
		}

		return null;
	}

	@Override
	public CommerceCheckoutStep getPreviousCommerceCheckoutStep(
			String commerceCheckoutStepName,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws Exception {

		if (Validator.isNull(commerceCheckoutStepName)) {
			return null;
		}

		List<CommerceCheckoutStep> commerceCheckoutSteps =
			getCommerceCheckoutSteps(httpServletRequest, httpServletResponse);

		int commerceCheckoutStepIndex = commerceCheckoutSteps.indexOf(
			getCommerceCheckoutStep(commerceCheckoutStepName));

		if (commerceCheckoutStepIndex > 0) {
			return commerceCheckoutSteps.get(commerceCheckoutStepIndex - 1);
		}

		return null;
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_commerceCheckoutStepServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, CommerceCheckoutStep.class,
				"commerce.checkout.step.name",
				ServiceTrackerCustomizerFactory.
					<CommerceCheckoutStep>serviceWrapper(bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_commerceCheckoutStepServiceTrackerMap.close();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceCheckoutStepServicesTrackerImpl.class);

	private ServiceTrackerMap<String, ServiceWrapper<CommerceCheckoutStep>>
		_commerceCheckoutStepServiceTrackerMap;
	private final Comparator<ServiceWrapper<CommerceCheckoutStep>>
		_commerceCheckoutStepServiceWrapperDisplayOrderComparator =
			new CommerceCheckoutStepServiceWrapperOrderComparator();

}