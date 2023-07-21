/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wysiwyg.web.internal.exportimport.portlet.preferences.processor;

import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.PortletDataException;
import com.liferay.exportimport.portlet.preferences.processor.Capability;
import com.liferay.exportimport.portlet.preferences.processor.ExportImportPortletPreferencesProcessor;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.wysiwyg.web.internal.constants.WYSIWYGPortletKeys;

import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Lianne Louie
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + WYSIWYGPortletKeys.WYSIWYG,
	service = ExportImportPortletPreferencesProcessor.class
)
public class WYSIWYGExportImportPortletPreferencesProcessor
	implements ExportImportPortletPreferencesProcessor {

	@Override
	public List<Capability> getExportCapabilities() {
		return null;
	}

	@Override
	public List<Capability> getImportCapabilities() {
		return null;
	}

	@Override
	public PortletPreferences processExportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		String message = portletPreferences.getValue(
			"message", StringPool.BLANK);

		try {
			portletPreferences.setValue(
				"message",
				StringUtil.replace(
					message, "/documents/" + portletDataContext.getGroupId(),
					"/documents/[$groupId$]"));
		}
		catch (ReadOnlyException readOnlyException) {
			throw new PortletDataException(
				"Unable to update WYSIWYG portlet preferences during export",
				readOnlyException);
		}

		return portletPreferences;
	}

	@Override
	public PortletPreferences processImportPortletPreferences(
			PortletDataContext portletDataContext,
			PortletPreferences portletPreferences)
		throws PortletDataException {

		String message = portletPreferences.getValue(
			"message", StringPool.BLANK);

		try {
			portletPreferences.setValue(
				"message",
				StringUtil.replace(
					message, "/documents/[$groupId$]",
					"/documents/" + portletDataContext.getGroupId()));
		}
		catch (ReadOnlyException readOnlyException) {
			throw new PortletDataException(
				"Unable to update WYSIWYG portlet preferences during import",
				readOnlyException);
		}

		return portletPreferences;
	}

}