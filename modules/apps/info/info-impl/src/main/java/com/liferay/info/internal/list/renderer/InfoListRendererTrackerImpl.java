/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.list.renderer;

import com.liferay.info.internal.util.GenericsUtil;
import com.liferay.info.list.renderer.InfoListRenderer;
import com.liferay.info.list.renderer.InfoListRendererTracker;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Jorge Ferrer
 */
@Component(immediate = true, service = InfoListRendererTracker.class)
public class InfoListRendererTrackerImpl implements InfoListRendererTracker {

	@Override
	public InfoListRenderer getInfoListRenderer(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		return _infoListRenderers.get(key);
	}

	@Override
	public List<InfoListRenderer> getInfoListRenderers() {
		return new ArrayList<>(_infoListRenderers.values());
	}

	@Override
	public List<InfoListRenderer> getInfoListRenderers(String itemClassName) {
		List<InfoListRenderer> infoListRenderers =
			_itemClassNameInfoListRenderers.get(itemClassName);

		if (infoListRenderers != null) {
			return new ArrayList<>(infoListRenderers);
		}

		return Collections.emptyList();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setInfoListRenderer(InfoListRenderer infoListRenderer) {
		_infoListRenderers.put(infoListRenderer.getKey(), infoListRenderer);

		List<InfoListRenderer> itemClassInfoListRenderers =
			_itemClassNameInfoListRenderers.computeIfAbsent(
				GenericsUtil.getItemClassName(infoListRenderer),
				itemClass -> new ArrayList<>());

		itemClassInfoListRenderers.add(infoListRenderer);
	}

	protected void unsetInfoListRenderer(InfoListRenderer infoListRenderer) {
		_infoListRenderers.remove(infoListRenderer.getKey());

		List<InfoListRenderer> itemClassInfoListRenderers =
			_itemClassNameInfoListRenderers.get(
				GenericsUtil.getItemClassName(infoListRenderer));

		if (itemClassInfoListRenderers != null) {
			itemClassInfoListRenderers.remove(infoListRenderer);
		}
	}

	private final Map<String, InfoListRenderer> _infoListRenderers =
		new ConcurrentHashMap<>();
	private final Map<String, List<InfoListRenderer>>
		_itemClassNameInfoListRenderers = new ConcurrentHashMap<>();

}