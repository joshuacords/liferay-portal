/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.internal.processor;

import com.liferay.fragment.contributor.PortletAliasRegistration;
import com.liferay.fragment.internal.constants.PortletFragmentEntryProcessorWebKeys;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.PortletRegistry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.portlet.PortletIdCodec;
import com.liferay.portal.kernel.portlet.PortletJSONUtil;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

/**
 * @author Pavel Savinov
 */
@Component(immediate = true, service = PortletRegistry.class)
public class PortletRegistryImpl implements PortletRegistry {

	@Override
	public List<String> getFragmentEntryLinkPortletIds(
		FragmentEntryLink fragmentEntryLink) {

		List<String> portletIds = new ArrayList<>();

		Document document = Jsoup.parseBodyFragment(
			fragmentEntryLink.getHtml());

		Document.OutputSettings outputSettings = new Document.OutputSettings();

		outputSettings.prettyPrint(false);

		document.outputSettings(outputSettings);

		for (Element element : document.select("*")) {
			String tagName = element.tagName();

			if (!StringUtil.startsWith(tagName, "lfr-widget-")) {
				continue;
			}

			String alias = StringUtil.removeSubstring(tagName, "lfr-widget-");

			String portletName = getPortletName(alias);

			if (Validator.isNull(portletName)) {
				continue;
			}

			String portletId = PortletIdCodec.encode(
				PortletIdCodec.decodePortletName(portletName),
				PortletIdCodec.decodeUserId(portletName),
				fragmentEntryLink.getNamespace() + element.attr("id"));

			portletIds.add(portletId);
		}

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				fragmentEntryLink.getEditableValues());

			String portletId = jsonObject.getString("portletId");

			if (Validator.isNotNull(portletId)) {
				String instanceId = jsonObject.getString("instanceId");

				portletIds.add(PortletIdCodec.encode(portletId, instanceId));
			}
		}
		catch (PortalException portalException) {
			_log.error("Unable to get portlet IDs", portalException);
		}

		return portletIds;
	}

	@Override
	public List<String> getPortletAliases() {
		return new ArrayList<>(_portletNames.keySet());
	}

	@Override
	public String getPortletName(String alias) {
		return _portletNames.get(alias);
	}

	@Override
	public void writePortletPaths(
			FragmentEntryLink fragmentEntryLink,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws PortalException {

		List<String> fragmentEntryLinkPortletIds =
			getFragmentEntryLinkPortletIds(fragmentEntryLink);

		if (ListUtil.isEmpty(fragmentEntryLinkPortletIds)) {
			return;
		}

		List<Portlet> allPortlets = _getAllPortlets(httpServletRequest);

		Stream<String> stream = fragmentEntryLinkPortletIds.stream();

		List<Portlet> portlets = stream.map(
			fragmentEntryLinkPortletId -> _portletLocalService.getPortletById(
				fragmentEntryLinkPortletId)
		).distinct(
		).collect(
			Collectors.toList()
		);

		for (Portlet portlet : portlets) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			try {
				PortletJSONUtil.populatePortletJSONObject(
					httpServletRequest, StringPool.BLANK, portlet, allPortlets,
					jsonObject);

				PortletJSONUtil.writeHeaderPaths(
					httpServletResponse, jsonObject);

				PortletJSONUtil.writeFooterPaths(
					httpServletResponse, jsonObject);
			}
			catch (Exception exception) {
				_log.error(
					"Unable to write portlet paths " + portlet.getPortletId(),
					exception);
			}
		}
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		target = "(com.liferay.fragment.entry.processor.portlet.alias=*)"
	)
	protected void setPortlet(
		javax.portlet.Portlet jxPortlet, Map<String, Object> properties) {

		String alias = MapUtil.getString(
			properties, "com.liferay.fragment.entry.processor.portlet.alias");
		String portletName = MapUtil.getString(
			properties, "javax.portlet.name");

		_portletNames.put(alias, portletName);

		Bundle bundle = FrameworkUtil.getBundle(jxPortlet.getClass());

		BundleContext bundleContext = bundle.getBundleContext();

		Dictionary<String, Object> aliasRegistrationProperties =
			new HashMapDictionary<>();

		aliasRegistrationProperties.put(
			"com.liferay.fragment.entry.processor.portlet.alias", alias);

		bundleContext.registerService(
			PortletAliasRegistration.class,
			new PortletAliasRegistration() {
			},
			aliasRegistrationProperties);
	}

	protected void unsetPortlet(
		javax.portlet.Portlet jxPortlet, Map<String, Object> properties) {

		String alias = MapUtil.getString(
			properties, "com.liferay.fragment.entry.processor.portlet.alias");
		String portletName = MapUtil.getString(
			properties, "javax.portlet.name");

		_portletNames.remove(alias, portletName);
	}

	private List<Portlet> _getAllPortlets(
		HttpServletRequest httpServletRequest) {

		List<Portlet> allPortlets =
			(List<Portlet>)httpServletRequest.getAttribute(
				PortletFragmentEntryProcessorWebKeys.ALL_PORTLETS);

		if (ListUtil.isNotEmpty(allPortlets)) {
			return allPortlets;
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		LayoutTypePortlet layoutTypePortlet =
			themeDisplay.getLayoutTypePortlet();

		allPortlets = layoutTypePortlet.getAllPortlets();

		httpServletRequest.setAttribute(
			PortletFragmentEntryProcessorWebKeys.ALL_PORTLETS, allPortlets);

		return allPortlets;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PortletRegistryImpl.class);

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

	private final Map<String, String> _portletNames = new ConcurrentHashMap<>();

}