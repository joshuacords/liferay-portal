/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.page.template.internal.upgrade.v3_1_2;

import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;

import java.sql.ResultSet;
import java.sql.Statement;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Balázs Sáfrány-Kovalik
 */
public class UpgradeInstanceIds extends UpgradeProcess {

	public UpgradeInstanceIds(
		FragmentEntryLinkLocalService fragmentEntryLinkLocalService,
		PortletPreferencesLocalService portletPreferencesLocalService) {

		_fragmentEntryLinkLocalService = fragmentEntryLinkLocalService;
		_portletPreferencesLocalService = portletPreferencesLocalService;
	}

	@Override
	public void doUpgrade() throws Exception {
		_upgradeInstanceIds();
	}

	private Set<Long> _getFragmentEntryLinksIds(String data)
		throws PortalException {

		Set<Long> fragmentEntryLinkIds = new HashSet<>();

		JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject(data);

		JSONArray structureArray = dataJSONObject.getJSONArray("structure");

		for (int i = 0; i < structureArray.length(); i++) {
			JSONObject structure = structureArray.getJSONObject(i);

			JSONArray columnsArray = structure.getJSONArray("columns");

			for (int j = 0; j < columnsArray.length(); j++) {
				JSONObject column = columnsArray.getJSONObject(j);

				JSONArray idsArray = column.getJSONArray(
					"fragmentEntryLinkIds");

				for (int k = 0; k < idsArray.length(); k++) {
					fragmentEntryLinkIds.add(idsArray.getLong(k));
				}
			}
		}

		return fragmentEntryLinkIds;
	}

	private List<PortletPreferences> _getPortletPreferencesByPlid(long plid) {
		if (_portletPreferencesMap.containsKey(plid)) {
			return _portletPreferencesMap.get(plid);
		}

		List<PortletPreferences> portletPreferencesList =
			_portletPreferencesLocalService.getPortletPreferencesByPlid(plid);

		Stream<PortletPreferences> stream = portletPreferencesList.stream();

		portletPreferencesList = stream.filter(
			portletPreferences -> {
				String portletId = portletPreferences.getPortletId();

				return portletId.contains(_INSTANCE_SEPARATOR) &&
					   portletId.contains(_SEGMENTS_EXPERIENCE_SEPARATOR1);
			}
		).collect(
			Collectors.toList()
		);

		_portletPreferencesMap.put(plid, portletPreferencesList);

		return _portletPreferencesMap.get(plid);
	}

	private void _updateFragmentEntryLink(
		long segmentsExperienceId, FragmentEntryLink fragmentEntryLink) {

		String editableValues = StringUtil.replace(
			fragmentEntryLink.getEditableValues(),
			new String[] {
				_SEGMENTS_EXPERIENCE_SEPARATOR1 + segmentsExperienceId
			},
			new String[] {
				_SEGMENTS_EXPERIENCE_SEPARATOR2 + segmentsExperienceId
			});

		fragmentEntryLink.setEditableValues(editableValues);

		_fragmentEntryLinkLocalService.updateFragmentEntryLink(
			fragmentEntryLink);
	}

	private void _updatePortletPreferences(
		long plid, long segmentsExperienceId) {

		List<PortletPreferences> portletPreferencesList =
			_getPortletPreferencesByPlid(plid);

		for (PortletPreferences portletPreferences : portletPreferencesList) {
			String portletId = portletPreferences.getPortletId();

			if (!portletId.contains(
					_SEGMENTS_EXPERIENCE_SEPARATOR1 + segmentsExperienceId)) {

				continue;
			}

			String newPortletId = StringUtil.replace(
				portletId,
				new String[] {
					_SEGMENTS_EXPERIENCE_SEPARATOR1 + segmentsExperienceId
				},
				new String[] {
					_SEGMENTS_EXPERIENCE_SEPARATOR2 + segmentsExperienceId
				});

			portletPreferences.setPortletId(newPortletId);

			_portletPreferencesLocalService.updatePortletPreferences(
				portletPreferences);
		}
	}

	private void _upgradeInstanceIds() throws Exception {
		try (Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery(
				"select segmentsExperienceId, data_ from " +
					"LayoutPageTemplateStructureRel where " +
						"segmentsExperienceId > 0")) {

			while (rs.next()) {
				long segmentsExperienceId = rs.getLong("segmentsExperienceId");

				String data = rs.getString("data_");

				Set<Long> fragmentEntryLinkIds = _getFragmentEntryLinksIds(
					data);

				for (long fragmentEntryLinkId : fragmentEntryLinkIds) {
					FragmentEntryLink fragmentEntryLink =
						_fragmentEntryLinkLocalService.fetchFragmentEntryLink(
							fragmentEntryLinkId);

					if (fragmentEntryLink == null) {
						continue;
					}

					_updatePortletPreferences(
						fragmentEntryLink.getClassPK(), segmentsExperienceId);

					_updateFragmentEntryLink(
						segmentsExperienceId, fragmentEntryLink);
				}
			}
		}
	}

	private static final String _INSTANCE_SEPARATOR = "_INSTANCE_";

	private static final String _SEGMENTS_EXPERIENCE_SEPARATOR1 =
		"_SEGMENTS_EXPERIENCE_";

	private static final String _SEGMENTS_EXPERIENCE_SEPARATOR2 =
		"SEGMENTSEXPERIENCE";

	private final FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;
	private final PortletPreferencesLocalService
		_portletPreferencesLocalService;
	private final Map<Long, List<PortletPreferences>> _portletPreferencesMap =
		new HashMap<>();

}