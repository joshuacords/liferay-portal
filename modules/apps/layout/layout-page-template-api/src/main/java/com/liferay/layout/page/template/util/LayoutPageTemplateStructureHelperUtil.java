/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.util;

import com.liferay.fragment.constants.FragmentConstants;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;

import java.util.List;

/**
 * @author Jürgen
 */
public class LayoutPageTemplateStructureHelperUtil {

	public static JSONObject generateContentLayoutStructure(
		List<FragmentEntryLink> fragmentEntryLinks) {

		JSONArray structureJSONArray = JSONFactoryUtil.createJSONArray();

		for (int i = 0; i < fragmentEntryLinks.size(); i++) {
			FragmentEntryLink fragmentEntryLink = fragmentEntryLinks.get(i);

			structureJSONArray.put(
				JSONUtil.put(
					"columns",
					JSONUtil.put(
						JSONUtil.put(
							"columnId", String.valueOf(i)
						).put(
							"fragmentEntryLinkIds",
							JSONUtil.put(
								String.valueOf(
									fragmentEntryLink.getFragmentEntryLinkId()))
						).put(
							"size", StringPool.BLANK
						))
				).put(
					"rowId", String.valueOf(i)
				).put(
					"type", String.valueOf(_getRowType(fragmentEntryLink))
				));
		}

		JSONObject jsonObject = JSONUtil.put(
			"config", JSONFactoryUtil.createJSONObject()
		).put(
			"nextColumnId", fragmentEntryLinks.size()
		).put(
			"nextRowId", fragmentEntryLinks.size()
		);

		if (!fragmentEntryLinks.isEmpty()) {
			jsonObject.put(
				"nextRowId", String.valueOf(fragmentEntryLinks.size() - 1));
		}

		jsonObject.put("structure", structureJSONArray);

		return jsonObject;
	}

	private static int _getRowType(FragmentEntryLink fragmentEntryLink) {
		FragmentEntry fragmentEntry =
			FragmentEntryLocalServiceUtil.fetchFragmentEntry(
				fragmentEntryLink.getFragmentEntryId());

		if ((fragmentEntry != null) &&
			(fragmentEntry.getType() == FragmentConstants.TYPE_COMPONENT)) {

			return FragmentConstants.TYPE_COMPONENT;
		}

		return FragmentConstants.TYPE_SECTION;
	}

}