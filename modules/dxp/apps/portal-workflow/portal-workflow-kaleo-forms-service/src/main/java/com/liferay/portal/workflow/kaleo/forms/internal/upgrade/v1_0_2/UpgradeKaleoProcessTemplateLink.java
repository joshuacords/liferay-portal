/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.workflow.kaleo.forms.internal.upgrade.v1_0_2;

import com.liferay.dynamic.data.mapping.service.DDMTemplateLinkLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcess;
import com.liferay.portal.workflow.kaleo.forms.model.KaleoProcessLink;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Rafael Praxedes
 */
public class UpgradeKaleoProcessTemplateLink extends UpgradeProcess {

	public UpgradeKaleoProcessTemplateLink(
		ClassNameLocalService classNameLocalService,
		DDMTemplateLinkLocalService ddmTemplateLinkLocalService) {

		_classNameLocalService = classNameLocalService;
		_ddmTemplateLinkLocalService = ddmTemplateLinkLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateKaleoProcess();
		updateKaleoProcessLink();
	}

	protected void updateKaleoProcess() throws SQLException {
		long kaleoProcessClassNameId = _classNameLocalService.getClassNameId(
			KaleoProcess.class.getName());

		StringBundler sb = new StringBundler(8);

		sb.append("select KaleoProcess.kaleoProcessId, KaleoProcess.");
		sb.append("DDMTemplateId from KaleoProcess where (KaleoProcess.");
		sb.append("DDMTemplateId > 0) and not exists (select 1 from ");
		sb.append("DDMTemplateLink where (DDMTemplateLink.classPK = ");
		sb.append("KaleoProcess.kaleoProcessId) and (DDMTemplateLink.");
		sb.append("classNameId = ");
		sb.append(kaleoProcessClassNameId);
		sb.append("))");

		try (PreparedStatement ps = connection.prepareStatement(sb.toString());
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long kaleoProcessLinkId = rs.getLong("kaleoProcessId");
				long ddmTemplateId = rs.getLong("DDMTemplateId");

				_ddmTemplateLinkLocalService.addTemplateLink(
					kaleoProcessClassNameId, kaleoProcessLinkId, ddmTemplateId);
			}
		}
	}

	protected void updateKaleoProcessLink() throws SQLException {
		long kaleoProcessLinkClassNameId =
			_classNameLocalService.getClassNameId(
				KaleoProcessLink.class.getName());

		StringBundler sb = new StringBundler(8);

		sb.append("select KaleoProcessLink.kaleoProcessLinkId, ");
		sb.append("KaleoProcessLink.DDMTemplateId from KaleoProcessLink ");
		sb.append("where (KaleoProcessLink.DDMTemplateId > 0) and not exists ");
		sb.append("(select 1 from DDMTemplateLink where (DDMTemplateLink.");
		sb.append("classPK = KaleoProcessLink.kaleoProcessLinkId) and ");
		sb.append("(DDMTemplateLink.classNameId = ");
		sb.append(kaleoProcessLinkClassNameId);
		sb.append("))");

		try (PreparedStatement ps = connection.prepareStatement(sb.toString());
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long kaleoProcessLinkId = rs.getLong("kaleoProcessLinkId");
				long ddmTemplateId = rs.getLong("DDMTemplateId");

				_ddmTemplateLinkLocalService.addTemplateLink(
					kaleoProcessLinkClassNameId, kaleoProcessLinkId,
					ddmTemplateId);
			}
		}
	}

	private final ClassNameLocalService _classNameLocalService;
	private final DDMTemplateLinkLocalService _ddmTemplateLinkLocalService;

}