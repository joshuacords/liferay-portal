/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.data.integration.web.internal.servlet.taglib.ui;

import com.liferay.commerce.data.integration.constants.CommerceDataIntegrationConstants;
import com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelper;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=30",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class CommerceDataIntegrationTriggerScreenNavigationEntry
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<CommerceDataIntegrationProcess> {

	@Override
	public String getCategoryKey() {
		return CommerceDataIntegrationConstants.
			CATEGORY_KEY_COMMERCE_DATA_INTEGRATION_SCHEDULED_TASK;
	}

	@Override
	public String getEntryKey() {
		return getCategoryKey();
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, getCategoryKey());
	}

	@Override
	public String getScreenNavigationKey() {
		return CommerceDataIntegrationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_DATA_INTEGRATION_GENERAL;
	}

	@Override
	public boolean isVisible(
		User user,
		CommerceDataIntegrationProcess commerceDataIntegrationProcess) {

		if (commerceDataIntegrationProcess == null) {
			return false;
		}

		return true;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse, "/process/trigger.jsp");
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.data.integration.model.CommerceDataIntegrationProcess)"
	)
	private ModelResourcePermission<CommerceDataIntegrationProcess>
		_commerceDataIntegrationProcessModelResourcePermission;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private SchedulerEngineHelper _schedulerEngineHelper;

}