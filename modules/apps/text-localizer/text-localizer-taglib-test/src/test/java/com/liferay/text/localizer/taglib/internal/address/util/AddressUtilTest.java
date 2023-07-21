/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.text.localizer.taglib.internal.address.util;

import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.AddressWrapper;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.CountryWrapper;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.RegionWrapper;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.test.rule.LiferayUnitTestRule;

import java.util.Locale;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Drew Brokke
 */
public class AddressUtilTest {

	@ClassRule
	@Rule
	public static final LiferayUnitTestRule liferayUnitTestRule =
		LiferayUnitTestRule.INSTANCE;

	@After
	public void tearDown() {
		ServiceContextThreadLocal.popServiceContext();
	}

	@Test
	public void testGetCountryNameOptionalEmptyWithNoCountry() {
		Optional<String> countryNameOptional =
			AddressUtil.getCountryNameOptional(
				new AddressWrapper(null) {

					@Override
					public Country getCountry() {
						return null;
					}

				});

		Assert.assertFalse(countryNameOptional.isPresent());
	}

	@Test
	public void testGetCountryNameOptionalEmptyWithNullAddress() {
		Optional<String> countryNameOptional =
			AddressUtil.getCountryNameOptional(null);

		Assert.assertFalse(countryNameOptional.isPresent());
	}

	@Test
	public void testGetCountryNameOptionalLocalized() {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setLanguageId(LocaleUtil.toLanguageId(LocaleUtil.US));

		ServiceContextThreadLocal.pushServiceContext(serviceContext);

		Optional<String> countryNameOptional =
			AddressUtil.getCountryNameOptional(_getAddressWithCountry());

		Assert.assertEquals(_COUNTRY_NAME_LOCALIZED, countryNameOptional.get());
	}

	@Test
	public void testGetCountryNameOptionalNotLocalized() {
		Optional<String> countryNameOptional =
			AddressUtil.getCountryNameOptional(_getAddressWithCountry());

		Assert.assertEquals(_COUNTRY_NAME, countryNameOptional.get());
	}

	@Test
	public void testGetRegionNameOptional() {
		Optional<String> regionNameOptional = AddressUtil.getRegionNameOptional(
			new AddressWrapper(null) {

				@Override
				public Region getRegion() {
					return new RegionWrapper(null) {

						@Override
						public String getName() {
							return _REGION_NAME;
						}

						@Override
						public long getRegionId() {
							return RandomTestUtil.randomLong();
						}

					};
				}

			});

		Assert.assertEquals(_REGION_NAME, regionNameOptional.get());
	}

	@Test
	public void testGetRegionNameOptionalEmptyWithNoRegion() {
		Optional<String> regionNameOptional = AddressUtil.getRegionNameOptional(
			new AddressWrapper(null) {

				@Override
				public Region getRegion() {
					return null;
				}

			});

		Assert.assertFalse(regionNameOptional.isPresent());
	}

	@Test
	public void testGetRegionNameOptionalEmptyWithNullAddress() {
		Optional<String> regionNameOptional = AddressUtil.getRegionNameOptional(
			null);

		Assert.assertFalse(regionNameOptional.isPresent());
	}

	private Address _getAddressWithCountry() {
		return new AddressWrapper(null) {

			@Override
			public Country getCountry() {
				return new CountryWrapper(null) {

					@Override
					public long getCountryId() {
						return RandomTestUtil.randomLong();
					}

					@Override
					public String getName() {
						return _COUNTRY_NAME;
					}

					@Override
					public String getName(Locale locale) {
						return _COUNTRY_NAME_LOCALIZED;
					}

				};
			}

		};
	}

	private static final String _COUNTRY_NAME = RandomTestUtil.randomString();

	private static final String _COUNTRY_NAME_LOCALIZED =
		RandomTestUtil.randomString();

	private static final String _REGION_NAME = RandomTestUtil.randomString();

}