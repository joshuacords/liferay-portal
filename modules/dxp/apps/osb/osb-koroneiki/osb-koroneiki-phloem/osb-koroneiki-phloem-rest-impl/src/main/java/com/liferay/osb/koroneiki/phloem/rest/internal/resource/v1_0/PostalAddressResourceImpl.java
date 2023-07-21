/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0;

import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.PostalAddress;
import com.liferay.osb.koroneiki.phloem.rest.dto.v1_0.util.PostalAddressUtil;
import com.liferay.osb.koroneiki.phloem.rest.internal.resource.v1_0.util.ServiceContextUtil;
import com.liferay.osb.koroneiki.phloem.rest.resource.v1_0.PostalAddressResource;
import com.liferay.osb.koroneiki.taproot.model.Account;
import com.liferay.osb.koroneiki.taproot.service.AccountService;
import com.liferay.osb.koroneiki.taproot.service.AddressService;
import com.liferay.portal.kernel.exception.NoSuchListTypeException;
import com.liferay.portal.kernel.exception.NoSuchRegionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Contact;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.ListTypeService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Kyle Bischof
 * @author Amos Fong
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/postal-address.properties",
	scope = ServiceScope.PROTOTYPE, service = PostalAddressResource.class
)
public class PostalAddressResourceImpl extends BasePostalAddressResourceImpl {

	@Override
	public void deletePostalAddress(
			String agentName, String agentUID, Long postalAddressId)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		_addressService.deleteAddress(postalAddressId);
	}

	@Override
	public Page<PostalAddress> getAccountAccountKeyPostalAddressesPage(
			String accountKey)
		throws Exception {

		Account account = _accountService.getAccount(accountKey);

		return Page.of(
			transform(
				account.getAddresses(),
				address -> PostalAddressUtil.toPostalAddress(address)));
	}

	@Override
	public PostalAddress getPostalAddress(Long postalAddressId)
		throws Exception {

		return PostalAddressUtil.toPostalAddress(
			_addressService.getAddress(postalAddressId));
	}

	@Override
	public PostalAddress postAccountAccountKeyPostalAddress(
			String agentName, String agentUID, String accountKey,
			PostalAddress postalAddress)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		Account account = _accountService.getAccount(accountKey);

		long countryId = _getCountryId(postalAddress.getAddressCountry(), 0);

		long regionId = _getRegionId(
			countryId, postalAddress.getAddressRegion(), 0);

		return PostalAddressUtil.toPostalAddress(
			_addressService.addAddress(
				Account.class.getName(), account.getAccountId(),
				postalAddress.getStreetAddressLine1(),
				postalAddress.getStreetAddressLine2(),
				postalAddress.getStreetAddressLine3(),
				postalAddress.getAddressLocality(),
				postalAddress.getPostalCode(), regionId, countryId,
				_getListTypeId(postalAddress.getAddressType(), 0),
				GetterUtil.getBoolean(postalAddress.getMailing()),
				GetterUtil.getBoolean(postalAddress.getPrimary()),
				ServiceContextUtil.getServiceContext()));
	}

	@Override
	public PostalAddress putPostalAddress(
			String agentName, String agentUID, Long postalAddressId,
			PostalAddress postalAddress)
		throws Exception {

		ServiceContextUtil.setAgentFields(agentName, agentUID);

		Address curAddress = _addressService.getAddress(postalAddressId);

		String streetAddressLine1 = GetterUtil.getString(
			postalAddress.getStreetAddressLine1(), curAddress.getStreet1());
		String streetAddressLine2 = GetterUtil.getString(
			postalAddress.getStreetAddressLine2(), curAddress.getStreet2());
		String streetAddressLine3 = GetterUtil.getString(
			postalAddress.getStreetAddressLine3(), curAddress.getStreet3());
		String addressLocality = GetterUtil.getString(
			postalAddress.getAddressLocality(), curAddress.getCity());
		String postalCode = GetterUtil.getString(
			postalAddress.getPostalCode(), curAddress.getZip());
		boolean mailing = GetterUtil.getBoolean(
			postalAddress.getMailing(), curAddress.isMailing());
		boolean primary = GetterUtil.getBoolean(
			postalAddress.getPrimary(), curAddress.isPrimary());

		long countryId = _getCountryId(
			postalAddress.getAddressCountry(), curAddress.getCountryId());

		long regionId = _getRegionId(
			countryId, postalAddress.getAddressRegion(),
			curAddress.getRegionId());

		long listTypeId = _getListTypeId(
			postalAddress.getAddressType(), curAddress.getTypeId());

		return PostalAddressUtil.toPostalAddress(
			_addressService.updateAddress(
				postalAddressId, streetAddressLine1, streetAddressLine2,
				streetAddressLine3, addressLocality, postalCode, regionId,
				countryId, listTypeId, mailing, primary));
	}

	private long _getCountryId(String addressCountry, long countryId)
		throws PortalException {

		if (Validator.isNull(addressCountry)) {
			return countryId;
		}

		Country country = _countryService.getCountryByName(addressCountry);

		return country.getCountryId();
	}

	private long _getListTypeId(String addressType, long typeId)
		throws PortalException {

		if (Validator.isNull(addressType)) {
			return typeId;
		}

		ListType listType = _listTypeService.getListType(
			addressType, Contact.class.getName() + ".address");

		if (listType == null) {
			throw new NoSuchListTypeException(
				"Address type " + addressType + " is not valid");
		}

		return listType.getListTypeId();
	}

	private long _getRegionId(
			long countryId, String addressRegion, long regionId)
		throws PortalException {

		if (addressRegion == null) {
			return regionId;
		}

		if ((countryId <= 0) && Validator.isNotNull(addressRegion)) {
			throw new PortalException(
				"Country is required when specifying a Region");
		}

		if (Validator.isNull(addressRegion)) {
			return 0;
		}

		List<Region> regions = _regionService.getRegions(countryId);

		for (Region region : regions) {
			if (StringUtil.equalsIgnoreCase(region.getName(), addressRegion)) {
				return region.getRegionId();
			}
		}

		throw new NoSuchRegionException(
			"No Region exists with the name " + addressRegion);
	}

	@Reference
	private AccountService _accountService;

	@Reference
	private AddressService _addressService;

	@Reference
	private CountryService _countryService;

	@Reference
	private ListTypeService _listTypeService;

	@Reference
	private RegionService _regionService;

}