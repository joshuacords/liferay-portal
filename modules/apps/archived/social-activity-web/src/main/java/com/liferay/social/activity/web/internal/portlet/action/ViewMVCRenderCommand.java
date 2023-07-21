/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.social.activity.web.internal.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.comparator.ModelResourceComparator;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.social.activity.web.internal.constants.SocialActivityPortletKeys;
import com.liferay.social.activity.web.internal.constants.SocialActivityWebKeys;
import com.liferay.social.kernel.model.SocialActivityDefinition;
import com.liferay.social.kernel.model.SocialActivitySetting;
import com.liferay.social.kernel.service.SocialActivitySettingService;
import com.liferay.social.kernel.util.SocialConfigurationUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Roberto Díaz
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + SocialActivityPortletKeys.SOCIAL_ACTIVITY,
		"mvc.command.name=/", "mvc.command.name=/social_activity/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			renderRequest.setAttribute(
				SocialActivityWebKeys.SOCIAL_ACTIVITY_SETTINGS_MAP,
				getActivitySettingsMap(themeDisplay));
		}
		catch (Exception exception) {
			if (exception instanceof PrincipalException) {
				SessionErrors.add(renderRequest, exception.getClass());

				return "/error.jsp";
			}

			throw new PortletException(exception);
		}

		return "/view.jsp";
	}

	protected Map<String, Boolean> getActivitySettingsMap(
			ThemeDisplay themeDisplay)
		throws Exception {

		Map<String, Boolean> activitySettingsMap = new LinkedHashMap<>();

		List<SocialActivitySetting> activitySettings =
			_socialActivitySettingService.getActivitySettings(
				themeDisplay.getSiteGroupIdOrLiveGroupId());

		String[] modelNames = SocialConfigurationUtil.getActivityModelNames();

		Comparator<String> comparator = new ModelResourceComparator(
			themeDisplay.getLocale());

		Arrays.sort(modelNames, comparator);

		for (String modelName : modelNames) {
			List<SocialActivityDefinition> activityDefinitions =
				_socialActivitySettingService.getActivityDefinitions(
					themeDisplay.getScopeGroupId(), modelName);

			for (SocialActivityDefinition activityDefinition :
					activityDefinitions) {

				if (activityDefinition.isCountersEnabled()) {
					activitySettingsMap.put(modelName, false);

					break;
				}
			}
		}

		for (SocialActivitySetting activitySetting : activitySettings) {
			String name = activitySetting.getName();

			if (name.equals("enabled") &&
				activitySettingsMap.containsKey(
					activitySetting.getClassName())) {

				activitySettingsMap.put(
					activitySetting.getClassName(),
					GetterUtil.getBoolean(activitySetting.getValue()));
			}
		}

		return activitySettingsMap;
	}

	@Reference(unbind = "-")
	protected void setSocialActivitySettingService(
		SocialActivitySettingService socialActivitySettingService) {

		_socialActivitySettingService = socialActivitySettingService;
	}

	private SocialActivitySettingService _socialActivitySettingService;

}