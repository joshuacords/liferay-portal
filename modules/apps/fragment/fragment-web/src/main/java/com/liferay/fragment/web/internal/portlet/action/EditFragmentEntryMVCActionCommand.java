/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.web.internal.portlet.action;

import com.liferay.fragment.constants.FragmentPortletKeys;
import com.liferay.fragment.exception.FragmentEntryConfigurationException;
import com.liferay.fragment.exception.FragmentEntryContentException;
import com.liferay.fragment.model.FragmentEntry;
import com.liferay.fragment.service.FragmentEntryService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.ResourceBundle;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + FragmentPortletKeys.FRAGMENT,
		"mvc.command.name=/fragment/edit_fragment_entry"
	},
	service = MVCActionCommand.class
)
public class EditFragmentEntryMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long fragmentEntryId = ParamUtil.getLong(
			actionRequest, "fragmentEntryId");

		String name = ParamUtil.getString(actionRequest, "name");
		String css = ParamUtil.getString(actionRequest, "cssContent");
		String html = ParamUtil.getString(actionRequest, "htmlContent");
		String js = ParamUtil.getString(actionRequest, "jsContent");
		String configuration = ParamUtil.getString(
			actionRequest, "configurationContent");
		int status = ParamUtil.getInteger(actionRequest, "status");

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		try {
			FragmentEntry fragmentEntry =
				_fragmentEntryService.updateFragmentEntry(
					fragmentEntryId, name, css, html, js, configuration,
					status);

			if (status == WorkflowConstants.ACTION_SAVE_DRAFT) {
				String redirect = _getSaveAndContinueRedirect(
					actionRequest, fragmentEntry);

				jsonObject.put("redirect", redirect);
			}
		}
		catch (FragmentEntryConfigurationException |
			   FragmentEntryContentException exception) {

			hideDefaultErrorMessage(actionRequest);

			String errorMessage = exception.getLocalizedMessage();

			if (exception instanceof FragmentEntryConfigurationException) {
				ThemeDisplay themeDisplay =
					(ThemeDisplay)actionRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

				ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
					themeDisplay.getLocale(),
					EditFragmentEntryMVCActionCommand.class);

				errorMessage = LanguageUtil.get(
					resourceBundle,
					"please-provide-a-valid-configuration-for-the-fragment");
			}

			jsonObject.put("error", errorMessage);
		}

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private String _getSaveAndContinueRedirect(
		ActionRequest actionRequest, FragmentEntry fragmentEntry) {

		PortletURL portletURL = PortletURLFactoryUtil.create(
			actionRequest, FragmentPortletKeys.FRAGMENT,
			PortletRequest.RENDER_PHASE);

		portletURL.setParameter(
			"mvcRenderCommandName", "/fragment/edit_fragment_entry");
		portletURL.setParameter(
			"fragmentCollectionId",
			String.valueOf(fragmentEntry.getFragmentCollectionId()));
		portletURL.setParameter(
			"fragmentEntryId",
			String.valueOf(fragmentEntry.getFragmentEntryId()));

		return portletURL.toString();
	}

	@Reference
	private FragmentEntryService _fragmentEntryService;

}