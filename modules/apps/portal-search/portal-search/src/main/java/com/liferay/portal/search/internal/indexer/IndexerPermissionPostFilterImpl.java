/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.internal.indexer;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.search.indexer.IndexerPermissionPostFilter;
import com.liferay.portal.search.spi.model.result.contributor.ModelVisibilityContributor;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Michael C. Han
 */
public class IndexerPermissionPostFilterImpl
	implements IndexerPermissionPostFilter {

	public IndexerPermissionPostFilterImpl(
		Supplier<Optional<ModelResourcePermission>>
			modelResourcePermissionSupplier,
		Supplier<Optional<ModelVisibilityContributor>>
			modelVisibilityContributorSupplier) {

		_modelResourcePermissionSupplier = modelResourcePermissionSupplier;
		_modelVisibilityContributorSupplier =
			modelVisibilityContributorSupplier;
	}

	@Override
	public boolean hasPermission(
		PermissionChecker permissionChecker, long entryClassPK) {

		Optional<ModelResourcePermission> optional =
			_modelResourcePermissionSupplier.get();

		return optional.map(
			modelResourcePermission -> _containsView(
				modelResourcePermission, permissionChecker, entryClassPK)
		).orElse(
			true
		);
	}

	@Override
	public boolean isPermissionAware() {
		Optional<ModelResourcePermission> optional =
			_modelResourcePermissionSupplier.get();

		return optional.isPresent();
	}

	@Override
	public boolean isVisible(long classPK, int status) {
		Optional<ModelVisibilityContributor> optional =
			_modelVisibilityContributorSupplier.get();

		return optional.map(
			modelVisibilityContributor -> modelVisibilityContributor.isVisible(
				classPK, status)
		).orElse(
			true
		);
	}

	private Boolean _containsView(
		ModelResourcePermission<?> modelResourcePermission,
		PermissionChecker permissionChecker, long entryClassPK) {

		try {
			return modelResourcePermission.contains(
				permissionChecker, entryClassPK, ActionKeys.VIEW);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		IndexerPermissionPostFilterImpl.class);

	private final Supplier<Optional<ModelResourcePermission>>
		_modelResourcePermissionSupplier;
	private final Supplier<Optional<ModelVisibilityContributor>>
		_modelVisibilityContributorSupplier;

}