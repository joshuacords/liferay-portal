/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.portlet.action;

import com.liferay.fragment.service.FragmentEntryLinkLocalService;
import com.liferay.layout.content.page.editor.constants.ContentPageEditorPortletKeys;
import com.liferay.layout.content.page.editor.web.internal.segments.SegmentsExperienceUtil;
import com.liferay.layout.page.template.service.LayoutPageTemplateStructureLocalService;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.JSONPortletResponseUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.PortletLocalService;
import com.liferay.portal.kernel.service.PortletPreferencesLocalService;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.TransactionConfig;
import com.liferay.portal.kernel.transaction.TransactionInvokerUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.segments.model.SegmentsExperience;
import com.liferay.segments.service.SegmentsExperienceService;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author David Arques
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ContentPageEditorPortletKeys.CONTENT_PAGE_EDITOR_PORTLET,
		"mvc.command.name=/content_layout/duplicate_segments_experience"
	},
	service = MVCActionCommand.class
)
public class DuplicateSegmentsExperienceMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Callable<JSONObject> callable = new DuplicateSegmentsExperienceCallable(
			actionRequest);

		JSONObject jsonObject = null;

		try {
			jsonObject = TransactionInvokerUtil.invoke(
				_transactionConfig, callable);
		}
		catch (Throwable t) {
			_log.error(t, t);

			HttpServletResponse httpServletResponse =
				_portal.getHttpServletResponse(actionResponse);

			httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			jsonObject = JSONUtil.put(
				"error",
				LanguageUtil.get(
					themeDisplay.getRequest(), "an-unexpected-error-occurred"));
		}

		hideDefaultSuccessMessage(actionRequest);

		JSONPortletResponseUtil.writeJSON(
			actionRequest, actionResponse, jsonObject);
	}

	private JSONObject _duplicateSegmentsExperience(ActionRequest actionRequest)
		throws PortalException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		long segmentsExperienceId = ParamUtil.getLong(
			actionRequest, "segmentsExperienceId");

		SegmentsExperience segmentsExperience =
			_segmentsExperienceService.getSegmentsExperience(
				segmentsExperienceId);

		StringBundler sb = new StringBundler(5);

		sb.append(segmentsExperience.getName(LocaleUtil.getSiteDefault()));
		sb.append(StringPool.SPACE);
		sb.append(StringPool.OPEN_PARENTHESIS);
		sb.append(_language.get(themeDisplay.getLocale(), "copy"));
		sb.append(StringPool.CLOSE_PARENTHESIS);

		SegmentsExperience duplicatedSegmentsExperience =
			_segmentsExperienceService.addSegmentsExperience(
				segmentsExperience.getSegmentsEntryId(),
				segmentsExperience.getClassNameId(),
				segmentsExperience.getClassPK(),
				Collections.singletonMap(
					LocaleUtil.getSiteDefault(), sb.toString()),
				segmentsExperience.isActive(),
				ServiceContextFactory.getInstance(actionRequest));

		_populateSegmentsExperienceJSONObject(
			jsonObject, duplicatedSegmentsExperience);

		String layoutData = SegmentsExperienceUtil.copyLayoutData(
			duplicatedSegmentsExperience.getClassNameId(),
			themeDisplay.getPlid(), themeDisplay.getScopeGroupId(),
			_layoutPageTemplateStructureService,
			segmentsExperience.getSegmentsExperienceId(),
			duplicatedSegmentsExperience.getSegmentsExperienceId());

		_populateLayoutDataJSONObject(jsonObject, layoutData);

		Map<Long, String> fragmentEntryLinksEditableValuesMap =
			SegmentsExperienceUtil.copyFragmentEntryLinksEditableValues(
				duplicatedSegmentsExperience.getClassNameId(),
				themeDisplay.getPlid(), _fragmentEntryLinkLocalService,
				themeDisplay.getScopeGroupId(),
				segmentsExperience.getSegmentsExperienceId(),
				duplicatedSegmentsExperience.getSegmentsExperienceId());

		_populateFragmentEntryLinksJSONObject(
			jsonObject, fragmentEntryLinksEditableValuesMap);

		SegmentsExperienceUtil.copyPortletPreferences(
			themeDisplay.getPlid(), _portletLocalService,
			_portletPreferencesLocalService,
			segmentsExperience.getSegmentsExperienceId(),
			segmentsExperience.getSegmentsExperienceId());

		_populateAvailableSegmentsExperiencesSoyContext(
			jsonObject, themeDisplay);

		return jsonObject;
	}

	private void _populateAvailableSegmentsExperiencesSoyContext(
			JSONObject jsonObject, ThemeDisplay themeDisplay)
		throws PortalException {

		jsonObject.put(
			"availableSegmentsExperiences",
			SegmentsExperienceUtil.getAvailableSegmentsExperiencesSoyContext(
				themeDisplay));
	}

	private void _populateFragmentEntryLinksJSONObject(
			JSONObject jsonObject,
			Map<Long, String> fragmentEntryLinksEditableValuesMap)
		throws JSONException {

		JSONObject fragmentEntryLinksJSONObject =
			JSONFactoryUtil.createJSONObject();

		for (Map.Entry<Long, String> entry :
				fragmentEntryLinksEditableValuesMap.entrySet()) {

			fragmentEntryLinksJSONObject.put(
				String.valueOf(entry.getKey()),
				JSONFactoryUtil.createJSONObject(entry.getValue()));
		}

		jsonObject.put("fragmentEntryLinks", fragmentEntryLinksJSONObject);
	}

	private void _populateLayoutDataJSONObject(
			JSONObject jsonObject, String layoutData)
		throws JSONException {

		jsonObject.put(
			"layoutData", JSONFactoryUtil.createJSONObject(layoutData));
	}

	private void _populateSegmentsExperienceJSONObject(
		JSONObject jsonObject, SegmentsExperience segmentsExperience) {

		jsonObject.put(
			"segmentsExperience",
			SegmentsExperienceUtil.getSegmentsExperienceJSONObject(
				segmentsExperience));
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DuplicateSegmentsExperienceMVCActionCommand.class);

	private static final TransactionConfig _transactionConfig =
		TransactionConfig.Factory.create(
			Propagation.REQUIRED, new Class<?>[] {Exception.class});

	@Reference
	private FragmentEntryLinkLocalService _fragmentEntryLinkLocalService;

	@Reference
	private Language _language;

	@Reference
	private LayoutPageTemplateStructureLocalService
		_layoutPageTemplateStructureService;

	@Reference
	private Portal _portal;

	@Reference
	private PortletLocalService _portletLocalService;

	@Reference
	private PortletPreferencesLocalService _portletPreferencesLocalService;

	@Reference
	private SegmentsExperienceService _segmentsExperienceService;

	private class DuplicateSegmentsExperienceCallable
		implements Callable<JSONObject> {

		@Override
		public JSONObject call() throws Exception {
			return _duplicateSegmentsExperience(_actionRequest);
		}

		private DuplicateSegmentsExperienceCallable(
			ActionRequest actionRequest) {

			_actionRequest = actionRequest;
		}

		private final ActionRequest _actionRequest;

	}

}