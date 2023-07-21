/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.info.internal.item.selector;

import com.liferay.info.internal.util.GenericsUtil;
import com.liferay.info.item.selector.InfoItemSelector;
import com.liferay.info.item.selector.InfoItemSelectorTracker;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Eudaldo Alonso
 */
@Component(immediate = true, service = InfoItemSelectorTracker.class)
public class InfoItemSelectorTrackerImpl implements InfoItemSelectorTracker {

	@Override
	public InfoItemSelector getInfoItemSelector(String key) {
		if (Validator.isNull(key)) {
			return null;
		}

		return _infoItemSelectors.get(key);
	}

	@Override
	public List<InfoItemSelector> getInfoItemSelectors() {
		return new ArrayList<>(_infoItemSelectors.values());
	}

	@Override
	public List<InfoItemSelector> getInfoItemSelectors(String itemClassName) {
		List<InfoItemSelector> infoItemSelectors =
			_itemClassNameInfoItemSelectors.get(itemClassName);

		if (infoItemSelectors != null) {
			return new ArrayList<>(infoItemSelectors);
		}

		return Collections.emptyList();
	}

	@Override
	public Set<String> getInfoItemSelectorsClassNames() {
		return _itemClassNameInfoItemSelectors.keySet();
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC
	)
	protected void setInfoItemSelector(InfoItemSelector infoItemSelector) {
		_infoItemSelectors.put(infoItemSelector.getKey(), infoItemSelector);

		List<InfoItemSelector> itemClassInfoItemSelectors =
			_itemClassNameInfoItemSelectors.computeIfAbsent(
				GenericsUtil.getItemClassName(infoItemSelector),
				itemClass -> new ArrayList<>());

		itemClassInfoItemSelectors.add(infoItemSelector);
	}

	protected void unsetInfoItemSelector(InfoItemSelector infoItemSelector) {
		_infoItemSelectors.remove(infoItemSelector.getKey());

		List<InfoItemSelector> itemClassInfoItemSelectors =
			_itemClassNameInfoItemSelectors.get(
				GenericsUtil.getItemClassName(infoItemSelector));

		if (itemClassInfoItemSelectors != null) {
			itemClassInfoItemSelectors.remove(infoItemSelector);
		}
	}

	private final Map<String, InfoItemSelector> _infoItemSelectors =
		new ConcurrentHashMap<>();
	private final Map<String, List<InfoItemSelector>>
		_itemClassNameInfoItemSelectors = new ConcurrentHashMap<>();

}