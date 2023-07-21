/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.item.renderer;

import com.liferay.info.internal.util.GenericsUtil;
import com.liferay.info.item.renderer.InfoItemRenderer;
import com.liferay.info.item.renderer.InfoItemRendererTracker;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceComparator;
import com.liferay.osgi.service.tracker.collections.map.ServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Jorge Ferrer
 */
@Component(immediate = true, service = InfoItemRendererTracker.class)
public class InfoItemRendererTrackerImpl implements InfoItemRendererTracker {

	@Override
	public InfoItemRenderer getInfoItemRenderer(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		return _infoItemRenderersServiceTrackerMap.getService(key);
	}

	@Override
	public List<InfoItemRenderer> getInfoItemRenderers() {
		return new ArrayList<>(_infoItemRenderersServiceTrackerMap.values());
	}

	@Override
	public List<InfoItemRenderer> getInfoItemRenderers(String itemClassName) {
		List<InfoItemRenderer> infoItemRenderers =
			_itemClassNameInfoItemRendererServiceTrackerMap.getService(
				itemClassName);

		if (infoItemRenderers != null) {
			return new ArrayList<>(infoItemRenderers);
		}

		return Collections.emptyList();
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_itemClassNameInfoItemRendererServiceTrackerMap =
			ServiceTrackerMapFactory.openMultiValueMap(
				bundleContext, InfoItemRenderer.class, null,
				new ServiceReferenceMapper<String, InfoItemRenderer>() {

					@Override
					public void map(
						ServiceReference<InfoItemRenderer> serviceReference,
						Emitter<String> emitter) {

						InfoItemRenderer infoItemRenderer =
							bundleContext.getService(serviceReference);

						String className = GenericsUtil.getItemClassName(
							infoItemRenderer);

						emitter.emit(className);
					}

				},
				Collections.reverseOrder(
					new PropertyServiceReferenceComparator("service.ranking")));

		_infoItemRenderersServiceTrackerMap =
			ServiceTrackerMapFactory.openSingleValueMap(
				bundleContext, InfoItemRenderer.class, null,
				(serviceReference, emitter) -> {
					InfoItemRenderer infoItemRenderer =
						bundleContext.getService(serviceReference);

					emitter.emit(infoItemRenderer.getKey());
				});
	}

	@Deactivate
	protected void deactivate() {
		_itemClassNameInfoItemRendererServiceTrackerMap.close();
	}

	private ServiceTrackerMap<String, InfoItemRenderer>
		_infoItemRenderersServiceTrackerMap;
	private ServiceTrackerMap<String, List<InfoItemRenderer>>
		_itemClassNameInfoItemRendererServiceTrackerMap;

}