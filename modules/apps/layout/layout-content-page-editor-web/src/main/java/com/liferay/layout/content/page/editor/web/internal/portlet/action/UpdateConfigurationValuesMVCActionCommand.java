/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.fragment.constants.FragmentEntryLinkConstants;
import com.liferay.fragment.model.FragmentEntryLink;
import com.liferay.fragment.processor.DefaultFragmentEntryProcessorContext;
import com.liferay.fragment.processor.FragmentEntryProcessorContext;
import com.liferay.fragment.processor.FragmentEntryProcessorRegistry;
import com.liferay.fragment.renderer.DefaultFragmentRendererContext;
import com.liferay.fragment.renderer.FragmentRendererController;
import com.liferay.fragment.service.FragmentEntryLinkService;
import com.liferay.fragment.util.FragmentEntryConfigUtil;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.constants.SegmentsExperienceConstants;

import java.util.Iterator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/content_layout/update_configuration_values"
	},
	service = MVCActionCommand.class
)
public class UpdateConfigurationValuesMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long fragmentEntryLinkId = ParamUtil.getLong(
			actionRequest, "fragmentEntryLinkId");

		String editableValues = ParamUtil.getString(
			actionRequest, "editableValues");

		FragmentEntryLink fragmentEntryLink =
			_fragmentEntryLinkService.updateFragmentEntryLink(
				fragmentEntryLinkId, editableValues, true);

		FragmentEntryProcessorContext fragmentEntryProcessorContext =
			new DefaultFragmentEntryProcessorContext(
				_portal.getHttpServletRequest(actionRequest),
				_portal.getHttpServletResponse(actionResponse),
				FragmentEntryLinkConstants.EDIT,
				LocaleUtil.getMostRelevantLocale());

		String processedHTML =
			_fragmentEntryProcessorRegistry.processFragmentEntryLinkHTML(
				fragmentEntryLink, fragmentEntryProcessorContext);

		JSONObject defaultEditableValuesJSONObject =
			_fragmentEntryProcessorRegistry.getDefaultEditableValuesJSONObject(
				processedHTML, fragmentEntryLink.getConfiguration());

		JSONObject newEditableValuesJSONObject = _mergeEditableValuesJSONObject(
			defaultEditableValuesJSONObject, editableValues);

		fragmentEntryLink = _fragmentEntryLinkService.updateFragmentEntryLink(
			fragmentEntryLinkId, newEditableValuesJSONObject.toString());

		hideDefaultSuccessMessage(actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse,
			_getFragmentEntryLinkJSONObject(
				actionRequest, actionResponse, fragmentEntryLink));
	}

	private JSONObject _getFragmentEntryLinkJSONObject(
			ActionRequest actionRequest, ActionResponse actionResponse,
			FragmentEntryLink fragmentEntryLink)
		throws JSONException {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		boolean isolated = themeDisplay.isIsolated();

		themeDisplay.setIsolated(true);

		try {
			DefaultFragmentRendererContext defaultFragmentRendererContext =
				new DefaultFragmentRendererContext(fragmentEntryLink);

			defaultFragmentRendererContext.setLocale(themeDisplay.getLocale());
			defaultFragmentRendererContext.setMode(
				FragmentEntryLinkConstants.EDIT);
			defaultFragmentRendererContext.setSegmentsExperienceIds(
				new long[] {SegmentsExperienceConstants.ID_DEFAULT});

			String configuration = _fragmentRendererController.getConfiguration(
				defaultFragmentRendererContext);

			return JSONUtil.put(
				"configuration", JSONFactoryUtil.createJSONObject(configuration)
			).put(
				"content",
				_fragmentRendererController.render(
					defaultFragmentRendererContext,
					_portal.getHttpServletRequest(actionRequest),
					_portal.getHttpServletResponse(actionResponse))
			).put(
				"defaultConfigurationValues",
				FragmentEntryConfigUtil.getConfigurationDefaultValuesJSONObject(
					configuration)
			).put(
				"editableValues",
				JSONFactoryUtil.createJSONObject(
					fragmentEntryLink.getEditableValues())
			).put(
				"fragmentEntryLinkId",
				fragmentEntryLink.getFragmentEntryLinkId()
			);
		}
		finally {
			themeDisplay.setIsolated(isolated);
		}
	}

	private JSONObject _mergeEditableValuesJSONObject(
			JSONObject defaultEditableValuesJSONObject, String editableValues)
		throws Exception {

		JSONObject editableValuesJSONObject = JSONFactoryUtil.createJSONObject(
			editableValues);

		JSONObject editableFragmentEntryProcessorJSONObject =
			editableValuesJSONObject.getJSONObject(
				_KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR);

		JSONObject mergedEditableFragmentEntryProcessorJSONObject =
			JSONFactoryUtil.createJSONObject();

		JSONObject defaultEditableFragmentEntryProcessorJSONObject =
			defaultEditableValuesJSONObject.getJSONObject(
				_KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR);

		Iterator<String> keys =
			defaultEditableFragmentEntryProcessorJSONObject.keys();

		while (keys.hasNext()) {
			String key = keys.next();

			if (editableFragmentEntryProcessorJSONObject.has(key)) {
				mergedEditableFragmentEntryProcessorJSONObject.put(
					key, editableFragmentEntryProcessorJSONObject.get(key));
			}
			else {
				mergedEditableFragmentEntryProcessorJSONObject.put(
					key,
					defaultEditableFragmentEntryProcessorJSONObject.get(key));
			}
		}

		editableValuesJSONObject.put(
			_KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR,
			mergedEditableFragmentEntryProcessorJSONObject);

		return editableValuesJSONObject;
	}

	private static final String _KEY_EDITABLE_FRAGMENT_ENTRY_PROCESSOR =
		"com.liferay.fragment.entry.processor.editable." +
			"EditableFragmentEntryProcessor";

	@Reference
	private FragmentEntryLinkService _fragmentEntryLinkService;

	@Reference
	private FragmentEntryProcessorRegistry _fragmentEntryProcessorRegistry;

	@Reference
	private FragmentRendererController _fragmentRendererController;

	@Reference
	private Portal _portal;

}