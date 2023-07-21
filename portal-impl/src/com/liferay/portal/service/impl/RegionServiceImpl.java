/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.RegionCodeException;
import com.liferay.portal.kernel.exception.RegionNameException;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.impl.RegionModelImpl;
import com.liferay.portal.service.base.RegionServiceBaseImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class RegionServiceImpl extends RegionServiceBaseImpl {

	@Override
	public Region addRegion(
			long countryId, String regionCode, String name, boolean active)
		throws PortalException {

		if (!getPermissionChecker().isOmniadmin()) {
			throw new PrincipalException.MustBeOmniadmin(
				getPermissionChecker());
		}

		countryPersistence.findByPrimaryKey(countryId);

		if (Validator.isNull(regionCode)) {
			throw new RegionCodeException();
		}

		if (Validator.isNull(name)) {
			throw new RegionNameException();
		}

		long regionId = counterLocalService.increment();

		Region region = regionPersistence.create(regionId);

		region.setCountryId(countryId);
		region.setRegionCode(regionCode);
		region.setName(name);
		region.setActive(active);

		return regionPersistence.update(region);
	}

	@Override
	public Region fetchRegion(long regionId) {
		return regionPersistence.fetchByPrimaryKey(regionId);
	}

	@Override
	public Region fetchRegion(long countryId, String regionCode) {
		return regionPersistence.fetchByC_R(countryId, regionCode);
	}

	@Override
	public Region getRegion(long regionId) throws PortalException {
		return regionPersistence.findByPrimaryKey(regionId);
	}

	@Override
	public Region getRegion(long countryId, String regionCode)
		throws PortalException {

		return regionPersistence.findByC_R(countryId, regionCode);
	}

	@Override
	public List<Region> getRegions() {
		return regionPersistence.findAll();
	}

	@Override
	public List<Region> getRegions(boolean active) {
		return regionPersistence.findByActive(active);
	}

	@Override
	public List<Region> getRegions(long countryId) {
		return regionPersistence.findByCountryId(
			countryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			_getOrderByComparator(countryId));
	}

	@AccessControlled(guestAccessEnabled = true)
	@Override
	public List<Region> getRegions(long countryId, boolean active) {
		return regionPersistence.findByC_A(
			countryId, active, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			_getOrderByComparator(countryId));
	}

	private OrderByComparator<Region> _getOrderByComparator(long countryId) {
		Country country = countryService.fetchCountry(countryId);

		if (country == null) {
			return null;
		}

		return _orderByComparators.get(country.getA2());
	}

	private static final Map<String, OrderByComparator<Region>>
		_orderByComparators = new HashMap<String, OrderByComparator<Region>>() {
			{
				put(
					"JP",
					OrderByComparatorFactoryUtil.create(
						RegionModelImpl.TABLE_NAME, "regionCode", true));
			}
		};

}