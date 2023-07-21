/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.display.internal.contributor.field;

import com.liferay.asset.display.contributor.AssetDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.portal.kernel.util.HashMapDictionary;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true, service = AssetDisplayContributorFieldTracker.class
)
public class AssetDisplayContributorFieldTracker {

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setAssetDisplayContributorField(
		AssetDisplayContributorField assetDisplayContributorField,
		Map<String, Object> properties) {

		Bundle bundle = FrameworkUtil.getBundle(
			assetDisplayContributorField.getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		ServiceRegistration<InfoDisplayContributorField> serviceRegistration =
			bundleContext.registerService(
				InfoDisplayContributorField.class,
				new AssetInfoDisplayContributorFieldAdapter(
					assetDisplayContributorField),
				new HashMapDictionary<>(properties));

		_serviceRegistrations.add(serviceRegistration);
	}

	protected void unsetAssetDisplayContributorField(
		AssetDisplayContributorField assetDisplayContributorField) {

		_serviceRegistrations.remove(assetDisplayContributorField);
	}

	private final List<ServiceRegistration<InfoDisplayContributorField>>
		_serviceRegistrations = new CopyOnWriteArrayList<>();

}