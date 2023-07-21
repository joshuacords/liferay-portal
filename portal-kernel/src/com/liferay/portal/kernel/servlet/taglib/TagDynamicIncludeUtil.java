/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet.taglib;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.registry.Registry;
import com.liferay.registry.RegistryUtil;
import com.liferay.registry.ServiceReference;
import com.liferay.registry.collections.ServiceReferenceMapper;
import com.liferay.registry.collections.ServiceTrackerCollections;
import com.liferay.registry.collections.ServiceTrackerMap;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlos Sierra Andrés
 * @author Raymond Augé
 */
public class TagDynamicIncludeUtil {

	public static List<TagDynamicInclude> getTagDynamicIncludes(
		String tagClassName, String tagDynamicId, String tagPoint) {

		return _tagDynamicIncludeUtil._tagDynamicIncludes.getService(
			_getKey(tagClassName, tagDynamicId, tagPoint));
	}

	public static boolean hasTagDynamicInclude(
		String tagClassName, String tagDynamicId, String tagPoint) {

		List<TagDynamicInclude> tagDynamicIncludes = getTagDynamicIncludes(
			tagClassName, tagDynamicId, tagPoint);

		if (ListUtil.isEmpty(tagDynamicIncludes)) {
			return false;
		}

		return true;
	}

	public static void include(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse, String tagClassName,
		String tagDynamicId, String tagPoint, boolean ascendingPriority) {

		List<TagDynamicInclude> tagDynamicIncludes = getTagDynamicIncludes(
			tagClassName, tagDynamicId, tagPoint);

		if (ListUtil.isEmpty(tagDynamicIncludes)) {
			return;
		}

		Iterator<TagDynamicInclude> iterator = null;

		if (ascendingPriority) {
			iterator = tagDynamicIncludes.iterator();
		}
		else {
			iterator = ListUtil.reverseIterator(tagDynamicIncludes);
		}

		while (iterator.hasNext()) {
			TagDynamicInclude tagDynamicInclude = iterator.next();

			try {
				tagDynamicInclude.include(
					httpServletRequest, httpServletResponse, tagClassName,
					tagDynamicId, tagPoint);
			}
			catch (Exception exception) {
				_log.error(exception, exception);
			}
		}
	}

	private static String _getKey(
		String tagClassName, String tagDynamicId, String tagPoint) {

		StringBundler sb = new StringBundler(5);

		sb.append(tagClassName);
		sb.append(CharPool.POUND);
		sb.append(tagPoint);
		sb.append(CharPool.POUND);
		sb.append(tagDynamicId);

		return sb.toString();
	}

	private TagDynamicIncludeUtil() {
		_tagDynamicIncludes = ServiceTrackerCollections.openMultiValueMap(
			TagDynamicInclude.class, null,
			new ServiceReferenceMapper<String, TagDynamicInclude>() {

				@Override
				public void map(
					ServiceReference<TagDynamicInclude> serviceReference,
					final Emitter<String> emitter) {

					Registry registry = RegistryUtil.getRegistry();

					TagDynamicInclude tagDynamicInclude = registry.getService(
						serviceReference);

					try {
						tagDynamicInclude.register(
							new TagDynamicInclude.TagDynamicIncludeRegistry() {

								@Override
								public void register(
									String tagClassName, String tagDynamicId,
									String tagPoint) {

									String key = _getKey(
										tagClassName, tagDynamicId, tagPoint);

									emitter.emit(key);
								}

							});
					}
					finally {
						registry.ungetService(serviceReference);
					}
				}

			});
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TagDynamicIncludeUtil.class);

	private static final TagDynamicIncludeUtil _tagDynamicIncludeUtil =
		new TagDynamicIncludeUtil();

	private final ServiceTrackerMap<String, List<TagDynamicInclude>>
		_tagDynamicIncludes;

}