/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.exportimport.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Jorge Díaz
 * @author Michael Bowerman
 */
@ExtendedObjectClassDefinition(
	category = "infrastructure",
	scope = ExtendedObjectClassDefinition.Scope.COMPANY
)
@Meta.OCD(
	id = "com.liferay.exportimport.configuration.ExportImportServiceConfiguration",
	localization = "content/Language",
	name = "export-import-service-configuration-name"
)
@ProviderType
public interface ExportImportServiceConfiguration {

	@Meta.AD(
		deflt = "false",
		description = "replicate-individual-deletions-by-default-help",
		name = "replicate-individual-deletions-by-default", required = false
	)
	public boolean replicateIndividualDeletionsByDefault();

	@Meta.AD(
		deflt = "true", description = "publish-parent-layouts-by-default-help",
		name = "publish-parent-layouts-by-default", required = false
	)
	public boolean publishParentLayoutsByDefault();

	@Meta.AD(
		deflt = "false", description = "publish-permissions-by-default-help",
		name = "publish-permissions-by-default", required = false
	)
	public boolean publishPermissionsByDefault();

	@Meta.AD(
		deflt = "true", description = "validate-file-entry-references-help",
		name = "validate-file-entry-references", required = false
	)
	public boolean validateFileEntryReferences();

	@Meta.AD(
		deflt = "true", description = "validate-journal-feed-references-help",
		name = "validate-journal-feed-references", required = false
	)
	public boolean validateJournalFeedReferences();

	@Meta.AD(
		deflt = "true", description = "validate-layout-references-help",
		name = "validate-layout-references", required = false
	)
	public boolean validateLayoutReferences();

	@Meta.AD(
		deflt = "true", description = "validate-missing-references-help",
		name = "validate-missing-references", required = false
	)
	public boolean validateMissingReferences();

	@Meta.AD(
		deflt = "true", description = "staging-delete-temp-lar-on-failure-help",
		name = "staging-delete-temp-lar-on-failure", required = false
	)
	public boolean stagingDeleteTempLarOnFailure();

	@Meta.AD(
		deflt = "true", description = "staging-delete-temp-lar-on-success-help",
		name = "staging-delete-temp-lar-on-success", required = false
	)
	public boolean stagingDeleteTempLarOnSuccess();

	@Meta.AD(
		deflt = "false",
		description = "staging-use-virtual-host-of-the-remote-site-help",
		name = "staging-use-virtual-host-of-the-remote-site", required = false
	)
	public boolean stagingUseVirtualHostForRemoteSite();

}