/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service;

/**
 * Provides a wrapper for {@link RegionService}.
 *
 * @author Brian Wing Shun Chan
 * @see RegionService
 * @generated
 */
public class RegionServiceWrapper
	implements RegionService, ServiceWrapper<RegionService> {

	public RegionServiceWrapper(RegionService regionService) {
		_regionService = regionService;
	}

	@Override
	public com.liferay.portal.kernel.model.Region addRegion(
			long countryId, java.lang.String regionCode, java.lang.String name,
			boolean active)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _regionService.addRegion(countryId, regionCode, name, active);
	}

	@Override
	public com.liferay.portal.kernel.model.Region fetchRegion(long regionId) {
		return _regionService.fetchRegion(regionId);
	}

	@Override
	public com.liferay.portal.kernel.model.Region fetchRegion(
		long countryId, java.lang.String regionCode) {

		return _regionService.fetchRegion(countryId, regionCode);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _regionService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.Region getRegion(long regionId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _regionService.getRegion(regionId);
	}

	@Override
	public com.liferay.portal.kernel.model.Region getRegion(
			long countryId, java.lang.String regionCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _regionService.getRegion(countryId, regionCode);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Region> getRegions() {
		return _regionService.getRegions();
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		boolean active) {

		return _regionService.getRegions(active);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		long countryId) {

		return _regionService.getRegions(countryId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Region> getRegions(
		long countryId, boolean active) {

		return _regionService.getRegions(countryId, active);
	}

	@Override
	public RegionService getWrappedService() {
		return _regionService;
	}

	@Override
	public void setWrappedService(RegionService regionService) {
		_regionService = regionService;
	}

	private RegionService _regionService;

}