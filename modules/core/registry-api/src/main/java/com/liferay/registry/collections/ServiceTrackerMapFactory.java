/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.collections;

import com.liferay.registry.ServiceReference;
import com.liferay.registry.ServiceTrackerCustomizer;

import java.util.Comparator;
import java.util.List;

/**
 * @author Carlos Sierra Andrés
 */
public interface ServiceTrackerMapFactory {

	public <S> ServiceTrackerMap<String, List<S>> openMultiValueMap(
		Class<S> clazz, String propertyKey);

	public <K, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper);

	public <K, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper,
		Comparator<ServiceReference<S>> comparator);

	public <K, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper,
		ServiceTrackerMapListener<K, ? super S, List<S>>
			serviceTrackerMapListener);

	public <K, SR, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer);

	public <K, SR, S> ServiceTrackerMap<K, List<S>> openMultiValueMap(
		Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer,
		Comparator<ServiceReference<SR>> comparator);

	public <SR, S> ServiceTrackerMap<String, List<S>> openMultiValueMap(
		Class<SR> clazz, String propertyKey,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer);

	public <S> ServiceTrackerMap<String, S> openSingleValueMap(
		Class<S> clazz, String propertyKey);

	public <K, S> ServiceTrackerMap<K, S> openSingleValueMap(
		Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper);

	public <K, S> ServiceTrackerMap<K, S> openSingleValueMap(
		Class<S> clazz, String filterString,
		ServiceReferenceMapper<K, ? super S> serviceReferenceMapper,
		Comparator<ServiceReference<S>> comparator);

	public <K, SR, S> ServiceTrackerMap<K, S> openSingleValueMap(
		Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer);

	public <K, SR, S> ServiceTrackerMap<K, S> openSingleValueMap(
		Class<SR> clazz, String filterString,
		ServiceReferenceMapper<K, ? super SR> serviceReferenceMapper,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer,
		Comparator<ServiceReference<SR>> comparator);

	public <SR, S> ServiceTrackerMap<String, S> openSingleValueMap(
		Class<SR> clazz, String propertyKey,
		ServiceTrackerCustomizer<SR, S> serviceTrackerCustomizer);

}