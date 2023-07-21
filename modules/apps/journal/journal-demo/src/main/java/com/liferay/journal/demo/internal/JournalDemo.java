/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.demo.internal;

import com.liferay.journal.demo.data.creator.JournalArticleDemoDataCreator;
import com.liferay.portal.instance.lifecycle.BasePortalInstanceLifecycleListener;
import com.liferay.portal.instance.lifecycle.PortalInstanceLifecycleListener;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.users.admin.demo.data.creator.SiteAdminUserDemoDataCreator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jürgen Kappler
 */
@Component(immediate = true, service = PortalInstanceLifecycleListener.class)
public class JournalDemo extends BasePortalInstanceLifecycleListener {

	@Override
	public void portalInstanceRegistered(Company company) throws Exception {
		Group group = _groupLocalService.getGroup(
			company.getCompanyId(), "Guest");

		User user = _siteAdminUserDemoDataCreator.create(
			group.getGroupId(), "sharon.choi@liferay.com");

		for (int i = 0; i < 5; i++) {
			_journalArticleDemoDataCreator.create(
				user.getUserId(), group.getGroupId());
		}
	}

	@Deactivate
	protected void deactivate() throws PortalException {
		_journalArticleDemoDataCreator.delete();

		_siteAdminUserDemoDataCreator.delete();
	}

	@Reference(unbind = "-")
	protected void setGroupLocalService(GroupLocalService groupLocalService) {
		_groupLocalService = groupLocalService;
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference(unbind = "-")
	protected void setSiteAdminUserDemoDataCreator(
		SiteAdminUserDemoDataCreator siteAdminUserDemoDataCreator) {

		_siteAdminUserDemoDataCreator = siteAdminUserDemoDataCreator;
	}

	@Reference(unbind = "-")
	protected void setSomeJournalArticleDemoDataCreator(
		JournalArticleDemoDataCreator journalArticleDemoDataCreator) {

		_journalArticleDemoDataCreator = journalArticleDemoDataCreator;
	}

	private GroupLocalService _groupLocalService;
	private JournalArticleDemoDataCreator _journalArticleDemoDataCreator;
	private SiteAdminUserDemoDataCreator _siteAdminUserDemoDataCreator;

}