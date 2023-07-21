/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.editor.ckeditor.web.internal;

import com.liferay.frontend.editor.EditorRenderer;
import com.liferay.frontend.editor.ckeditor.web.internal.constants.CKEditorConstants;
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
	property = "name=ckeditor", service = {Editor.class, EditorRenderer.class}
)
public class CKEditorEditor implements Editor, EditorRenderer {

	@Override
	public String getAttributeNamespace() {
		return CKEditorConstants.ATTRIBUTE_NAMESPACE;
	}

	@Override
	public String[] getJavaScriptModules() {
		return new String[0];
	}

	@Override
	public String getJspPath() {
		return "/ckeditor.jsp";
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
		return PortalWebResourceConstants.RESOURCE_TYPE_EDITOR_CKEDITOR;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_name = (String)properties.get("name");
	}

	private String _name;

}