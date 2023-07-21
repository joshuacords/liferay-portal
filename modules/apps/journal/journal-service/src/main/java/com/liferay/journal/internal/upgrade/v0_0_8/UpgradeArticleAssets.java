/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.journal.internal.upgrade.v0_0_8;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalArticleConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.LoggingTimer;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.List;

/**
 * @author Preston Crary
 * @author Alberto Chaparro
 */
public class UpgradeArticleAssets extends UpgradeProcess {

	public UpgradeArticleAssets(
		AssetEntryLocalService assetEntryLocalService,
		CompanyLocalService companyLocalService) {

		_assetEntryLocalService = assetEntryLocalService;
		_companyLocalService = companyLocalService;
	}

	@Override
	protected void doUpgrade() throws Exception {
		updateDefaultDraftArticleAssets();
	}

	protected void updateDefaultDraftArticleAssets() throws Exception {
		try (LoggingTimer loggingTimer = new LoggingTimer()) {
			List<Company> companies = _companyLocalService.getCompanies();

			for (Company company : companies) {
				updateDefaultDraftArticleAssets(company.getCompanyId());
			}
		}
	}

	protected void updateDefaultDraftArticleAssets(long companyId)
		throws Exception {

		try (PreparedStatement ps = connection.prepareStatement(
				StringBundler.concat(
					"select resourcePrimKey, indexable from JournalArticle ",
					"where companyId = ", companyId, " and version = ",
					JournalArticleConstants.VERSION_DEFAULT, " and status = ",
					WorkflowConstants.STATUS_DRAFT));
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long resourcePrimKey = rs.getLong("resourcePrimKey");
				boolean indexable = rs.getBoolean("indexable");

				AssetEntry assetEntry = _assetEntryLocalService.getEntry(
					JournalArticle.class.getName(), resourcePrimKey);

				_assetEntryLocalService.updateEntry(
					assetEntry.getClassName(), assetEntry.getClassPK(), null,
					null, indexable, assetEntry.isVisible());
			}
		}
	}

	private final AssetEntryLocalService _assetEntryLocalService;
	private final CompanyLocalService _companyLocalService;

}