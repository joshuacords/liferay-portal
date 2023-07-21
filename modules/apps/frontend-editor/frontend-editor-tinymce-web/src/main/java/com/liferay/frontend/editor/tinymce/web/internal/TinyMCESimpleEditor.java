/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.tinymce.web.internal;

import com.liferay.frontend.editor.EditorRenderer;
import com.liferay.frontend.editor.tinymce.web.internal.constants.TinyMCEEditorConstants;
import com.liferay.portal.kernel.editor.Editor;
import com.liferay.portal.kernel.servlet.PortalWebResourceConstants;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Raymond Augé
 * @author Roberto Díaz
 */
@Component(
	property = "name=tinymce_simple",
	service = {Editor.class, EditorRenderer.class}
)
public class TinyMCESimpleEditor implements Editor, EditorRenderer {

	@Override
	public String getAttributeNamespace() {
		return TinyMCEEditorConstants.ATTRIBUTE_NAMESPACE;
	}

	@Override
	public String[] getJavaScriptModules() {
		return new String[0];
	}

	@Override
	public String getJspPath() {
		return "/tinymce_simple.jsp";
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public String getResourcesJspPath() {
		return "/resources.jsp";
	}

	@Override
	public String getResourceType() {
		return PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_TINYMCEEDITOR;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_name = (String)properties.get("name");
	}

	private String _name;

}