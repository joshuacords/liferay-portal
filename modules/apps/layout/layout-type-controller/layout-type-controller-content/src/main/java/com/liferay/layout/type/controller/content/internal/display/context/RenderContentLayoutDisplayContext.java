/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.type.controller.content.internal.display.context;

import com.liferay.petra.io.unsync.UnsyncStringWriter;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.PortletJSONUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.taglib.servlet.PipingServletResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rubén Pulido
 */
public class RenderContentLayoutDisplayContext {

	public RenderContentLayoutDisplayContext(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		_httpServletRequest = httpServletRequest;
		_httpServletResponse = httpServletResponse;

		_themeDisplay = (ThemeDisplay)_httpServletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);
	}

	public String getPortletFooterPaths() {
		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			_httpServletResponse, unsyncStringWriter);

		for (Portlet portlet : _getPortlets()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			try {
				PortletJSONUtil.populatePortletJSONObject(
					_httpServletRequest, StringPool.BLANK, portlet, jsonObject);

				PortletJSONUtil.writeHeaderPaths(
					pipingServletResponse, jsonObject);
			}
			catch (Exception exception) {
				_log.error(
					"Unable to write portlet footer paths " +
						portlet.getPortletId(),
					exception);
			}
		}

		return unsyncStringWriter.toString();
	}

	public String getPortletHeaderPaths() {
		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		PipingServletResponse pipingServletResponse = new PipingServletResponse(
			_httpServletResponse, unsyncStringWriter);

		for (Portlet portlet : _getPortlets()) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			try {
				PortletJSONUtil.populatePortletJSONObject(
					_httpServletRequest, StringPool.BLANK, portlet, jsonObject);

				PortletJSONUtil.writeFooterPaths(
					pipingServletResponse, jsonObject);
			}
			catch (Exception exception) {
				_log.error(
					"Unable to write portlet header paths " +
						portlet.getPortletId(),
					exception);
			}
		}

		return unsyncStringWriter.toString();
	}

	private List<Portlet> _getPortlets() {
		if (_portlets != null) {
			return _portlets;
		}

		_portlets = new ArrayList<>();

		Set<String> uniquePortletNames = new HashSet<>();

		List<PortletPreferences> portletPreferencesList =
			PortletPreferencesLocalServiceUtil.getPortletPreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, _themeDisplay.getPlid());

		for (PortletPreferences portletPreferences : portletPreferencesList) {
			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				_themeDisplay.getCompanyId(),
				portletPreferences.getPortletId());

			if (portlet == null) {
				continue;
			}

			if (!portlet.isActive() || portlet.isUndeployedPortlet()) {
				continue;
			}

			if (uniquePortletNames.add(portlet.getPortletName())) {
				_portlets.add(portlet);
			}
		}

		return _portlets;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RenderContentLayoutDisplayContext.class);

	private final HttpServletRequest _httpServletRequest;
	private final HttpServletResponse _httpServletResponse;
	private List<Portlet> _portlets;
	private final ThemeDisplay _themeDisplay;

}