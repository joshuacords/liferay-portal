/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.image.editor.web.internal.portlet.action;

import com.liferay.frontend.image.editor.capability.ImageEditorCapability;
import com.liferay.frontend.image.editor.web.internal.constants.ImageEditorPortletKeys;
import com.liferay.frontend.image.editor.web.internal.portlet.tracker.ImageEditorCapabilityTracker;
import com.liferay.frontend.image.editor.web.internal.portlet.tracker.ImageEditorCapabilityTracker.ImageEditorCapabilityDescriptor;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.ServletContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + ImageEditorPortletKeys.IMAGE_EDITOR,
		"mvc.command.name=/", "mvc.command.name=/image_editor/view"
	},
	service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse) {

		Template template = _getTemplate(renderRequest);

		template.put(
			"imageEditorCapabilities",
			HashMapBuilder.<String, Object>put(
				"tools", _getImageEditorToolsContexts(renderRequest)
			).build());

		String entityURL = ParamUtil.getString(renderRequest, "entityURL");

		template.put("image", entityURL);

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		template.put("pathThemeImages", themeDisplay.getPathThemeImages());

		String eventName = ParamUtil.getString(renderRequest, "eventName");

		template.put("saveEventName", eventName);

		String saveFileEntryId = ParamUtil.getString(
			renderRequest, "saveFileEntryId");

		template.put("saveFileEntryId", saveFileEntryId);

		String saveFileName = ParamUtil.getString(
			renderRequest, "saveFileName");

		template.put("saveFileName", saveFileName);

		String saveMimeType = ParamUtil.getString(
			renderRequest, "saveMimeType");

		template.put("saveMimeType", saveMimeType);

		String saveParamName = ParamUtil.getString(
			renderRequest, "saveParamName");

		template.put("saveParamName", saveParamName);

		String saveURL = ParamUtil.getString(renderRequest, "saveURL");

		template.put("saveURL", saveURL);

		return "ImageEditor";
	}

	private Map<String, List<ImageEditorCapabilityDescriptor>>
		_getImageEditorCapabilityDescriptorsList(
			List<ImageEditorCapabilityDescriptor>
				imageEditorCapabilityDescriptors) {

		Map<String, List<ImageEditorCapabilityDescriptor>>
			imageEditorCapabilityDescriptorsMap = new HashMap<>();

		for (ImageEditorCapabilityDescriptor imageEditorCapabilityDescriptor :
				imageEditorCapabilityDescriptors) {

			Map<String, Object> properties =
				imageEditorCapabilityDescriptor.getProperties();

			String category = GetterUtil.getString(
				properties.get(
					"com.liferay.frontend.image.editor.capability.category"));

			List<ImageEditorCapabilityDescriptor>
				curImageEditorCapabilityDescriptors =
					imageEditorCapabilityDescriptorsMap.computeIfAbsent(
						category, key -> new ArrayList<>());

			curImageEditorCapabilityDescriptors.add(
				imageEditorCapabilityDescriptor);
		}

		return imageEditorCapabilityDescriptorsMap;
	}

	private List<Map<String, Object>> _getImageEditorToolsContexts(
		RenderRequest renderRequest) {

		List<Map<String, Object>> imageEditorToolsContexts = new ArrayList<>();

		List<ImageEditorCapabilityDescriptor>
			toolImageEditorCapabilityDescriptors =
				_imageEditorCapabilityTracker.
					getImageEditorCapabilityDescriptors("tool");

		if (toolImageEditorCapabilityDescriptors == null) {
			return imageEditorToolsContexts;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			renderRequest.getLocale(), getClass());

		Map<String, List<ImageEditorCapabilityDescriptor>>
			imageEditorCapabilityDescriptorsMap =
				_getImageEditorCapabilityDescriptorsList(
					toolImageEditorCapabilityDescriptors);

		for (Map.Entry<String, List<ImageEditorCapabilityDescriptor>> entry :
				imageEditorCapabilityDescriptorsMap.entrySet()) {

			List<Map<String, Object>> controlContexts = new ArrayList<>();
			String icon = StringPool.BLANK;

			for (ImageEditorCapabilityDescriptor
					imageEditorCapabilityDescriptor : entry.getValue()) {

				ImageEditorCapability imageEditorCapability =
					imageEditorCapabilityDescriptor.getImageEditorCapability();

				ServletContext servletContext =
					imageEditorCapability.getServletContext();

				Map<String, Object> properties =
					imageEditorCapabilityDescriptor.getProperties();

				String variant = GetterUtil.getString(
					properties.get(
						"com.liferay.frontend.image.editor.capability." +
							"controls"));

				Map<String, Object> controlContext =
					HashMapBuilder.<String, Object>put(
						"label",
						imageEditorCapability.getLabel(themeDisplay.getLocale())
					).put(
						"modulePath", servletContext.getContextPath()
					).put(
						"variant", variant
					).build();

				imageEditorCapability.prepareContext(
					controlContext,
					_portal.getHttpServletRequest(renderRequest));

				controlContexts.add(controlContext);

				icon = GetterUtil.getString(
					properties.get(
						"com.liferay.frontend.image.editor.capability.icon"));
			}

			String category = entry.getKey();

			imageEditorToolsContexts.add(
				HashMapBuilder.<String, Object>put(
					"controls", controlContexts
				).put(
					"icon", icon
				).put(
					"title", LanguageUtil.get(resourceBundle, category)
				).build());
		}

		return imageEditorToolsContexts;
	}

	private Template _getTemplate(RenderRequest renderRequest) {
		return (Template)renderRequest.getAttribute(WebKeys.TEMPLATE);
	}

	@Reference
	private ImageEditorCapabilityTracker _imageEditorCapabilityTracker;

	@Reference
	private Portal _portal;

}