/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.item.selector.web.internal.criterion;

import com.liferay.document.library.item.selector.criterion.DLItemSelectorCriterionCreationMenuRestriction;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.osgi.service.tracker.collections.map.PropertyServiceReferenceMapper;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true, service = {})
public class DLItemSelectorCriterionCreationMenuRestrictionUtil {

	public static Set<String> getAllowedCreationMenuUIItemKeys(
		ItemSelectorCriterion itemSelectorCriterion) {

		Class<? extends ItemSelectorCriterion> clazz =
			itemSelectorCriterion.getClass();

		List<DLItemSelectorCriterionCreationMenuRestriction>
			dlItemSelectorCriterionCreationMenuRestrictions =
				_serviceTrackerMap.getService(clazz.getName());

		if (dlItemSelectorCriterionCreationMenuRestrictions == null) {
			return Collections.emptySet();
		}

		Stream<DLItemSelectorCriterionCreationMenuRestriction> stream =
			dlItemSelectorCriterionCreationMenuRestrictions.stream();

		return stream.map(
			DLItemSelectorCriterionCreationMenuRestriction::
				getAllowedCreationMenuUIItemKeys
		).collect(
			HashSet::new, Set::addAll, Set::addAll
		);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, DLItemSelectorCriterionCreationMenuRestriction.class,
			null,
			(serviceReference, emitter) -> {
				Object modelClassName = serviceReference.getProperty(
					"model.class.name");

				if (modelClassName != null) {
					_propertyServiceReferenceMapper.map(
						serviceReference, emitter);

					return;
				}

				try {
					emitter.emit(
						_getGenericClassName(
							bundleContext.getService(serviceReference)));
				}
				finally {
					bundleContext.ungetService(serviceReference);
				}
			});
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private static Class<?> _getGenericClass(Class<?> clazz) {
		Type[] genericInterfaceTypes = clazz.getGenericInterfaces();

		for (Type genericInterfaceType : genericInterfaceTypes) {
			if (genericInterfaceType instanceof ParameterizedType) {
				ParameterizedType parameterizedType =
					(ParameterizedType)genericInterfaceType;

				return (Class<?>)parameterizedType.getActualTypeArguments()[0];
			}
		}

		Class<?> superClass = clazz.getSuperclass();

		if (superClass != null) {
			return _getGenericClass(superClass);
		}

		return Object.class;
	}

	private static Class<?> _getGenericClass(Object object) {
		Class<?> clazz = object.getClass();

		return _getGenericClass(clazz);
	}

	private static String _getGenericClassName(Object object) {
		Class<?> clazz = _getGenericClass(object);

		return clazz.getName();
	}

	private static ServiceTrackerMap
		<String, List<DLItemSelectorCriterionCreationMenuRestriction>>
			_serviceTrackerMap;

	private final PropertyServiceReferenceMapper
		<String, DLItemSelectorCriterionCreationMenuRestriction>
			_propertyServiceReferenceMapper =
				new PropertyServiceReferenceMapper<>("model.class.name");

}