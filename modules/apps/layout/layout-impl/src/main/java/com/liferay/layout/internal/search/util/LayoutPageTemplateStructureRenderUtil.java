/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.internal.search.util;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.renderer.DefaultFragmentRendererContext;
import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.fragment.service.FragmentEntryLinkLocalServiceUtil;
import com.liferay.layout.page.template.model.LayoutPageTemplateStructure;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Eudaldo Alonso
 */
public class LayoutPageTemplateStructureRenderUtil {

	public static String renderLayoutContent(
			FragmentRendererController fragmentRendererController,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			LayoutPageTemplateStructure layoutPageTemplateStructure,
			String mode, Map<String, Object> parameterMap, Locale locale,
			long[] segmentsExperienceIds)
		throws PortalException {

		if (fragmentRendererController == null) {
			return StringPool.BLANK;
		}

		String data = layoutPageTemplateStructure.getData(
			segmentsExperienceIds);

		if (Validator.isNull(data)) {
			return StringPool.BLANK;
		}

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject(data);

		JSONArray structureJSONArray = dataJSONObject.getJSONArray("structure");

		if (structureJSONArray == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler();

		JSONArray nonIndexableFragmentEntryLinkIdsJSONArray =
			dataJSONObject.getJSONArray("nonIndexableFragmentEntryLinkIds");

		List<String> nonIndexableFragmentEntryLinkIds = JSONUtil.toStringList(
			nonIndexableFragmentEntryLinkIdsJSONArray);

		for (int i = 0; i < structureJSONArray.length(); i++) {
			JSONObject rowJSONObject = structureJSONArray.getJSONObject(i);

			JSONObject rowConfigJSONObject = rowJSONObject.getJSONObject(
				"config");

			if (rowConfigJSONObject != null) {
				boolean nonIndexable = rowConfigJSONObject.getBoolean(
					"nonIndexable", false);

				if (nonIndexable &&
					Objects.equals(mode, FragmentEntryLinkConstants.SEARCH)) {

					continue;
				}
			}

			JSONArray columnsJSONArray = rowJSONObject.getJSONArray("columns");

			for (int j = 0; j < columnsJSONArray.length(); j++) {
				JSONObject columnJSONObject = columnsJSONArray.getJSONObject(j);

				JSONArray fragmentEntryLinkIdsJSONArray =
					columnJSONObject.getJSONArray("fragmentEntryLinkIds");

				for (int k = 0; k < fragmentEntryLinkIdsJSONArray.length();
					 k++) {

					long fragmentEntryLinkId =
						fragmentEntryLinkIdsJSONArray.getLong(k);

					if (fragmentEntryLinkId <= 0) {
						continue;
					}

					FragmentEntryLink fragmentEntryLink =
						FragmentEntryLinkLocalServiceUtil.
							fetchFragmentEntryLink(fragmentEntryLinkId);

					if (fragmentEntryLink == null) {
						continue;
					}

					if (Objects.equals(
							mode, FragmentEntryLinkConstants.SEARCH) &&
						nonIndexableFragmentEntryLinkIds.contains(
							String.valueOf(fragmentEntryLinkId))) {

						continue;
					}

					DefaultFragmentRendererContext fragmentRendererContext =
						new DefaultFragmentRendererContext(fragmentEntryLink);

					fragmentRendererContext.setFieldValues(parameterMap);
					fragmentRendererContext.setLocale(locale);
					fragmentRendererContext.setMode(mode);
					fragmentRendererContext.setSegmentsExperienceIds(
						segmentsExperienceIds);

					sb.append(
						fragmentRendererController.render(
							fragmentRendererContext, httpServletRequest,
							httpServletResponse));
				}
			}
		}

		return sb.toString();
	}

}