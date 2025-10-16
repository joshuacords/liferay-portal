/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.list.util;

import com.liferay.asset.list.service.AssetListEntryLocalService;
import com.liferay.asset.list.service.AssetListEntryLocalServiceUtil;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.rule.LiferayUnitTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.UnicodePropertiesBuilder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 * @author Joshua Cords
 */
public class AssetListTypeSettingsSanitizerUtilTest {

    @ClassRule
    @Rule
    public static final LiferayUnitTestRule liferayUnitTestRule =
        LiferayUnitTestRule.INSTANCE;

    @ClassRule
    public static final CodeCoverageAssertor codeCoverageAssertor =
        new CodeCoverageAssertor();

    @AfterClass
    public static void tearDownClass() {
        if (_assetListEntryLocalServiceSnapshot != null) {
            ReflectionTestUtil.setFieldValue(
                AssetListEntryLocalServiceUtil.class, "_serviceSnapshot",
                _assetListEntryLocalServiceSnapshot);
        }
    }

    @After
    public void tearDown() {
        if (_classNameLocalServiceUtilMockedStatic != null) {
            _classNameLocalServiceUtilMockedStatic.close();
        }
    }

    @Before
    public void setUp() {
        _assetListEntryLocalService = Mockito.mock(
            AssetListEntryLocalService.class);

        if (_assetListEntryLocalServiceSnapshot == null) {
            _assetListEntryLocalServiceSnapshot = ReflectionTestUtil.getFieldValue(
                AssetListEntryLocalServiceUtil.class, "_serviceSnapshot");
        }

        ReflectionTestUtil.setFieldValue(
            AssetListEntryLocalServiceUtil.class, "_serviceSnapshot",
            new Snapshot<AssetListEntryLocalService>(
                AssetListEntryLocalServiceUtil.class,
                AssetListEntryLocalService.class) {

                @Override
                public AssetListEntryLocalService get() {
                    return _assetListEntryLocalService;
                }

            });

        _classNameLocalServiceUtilMockedStatic = Mockito.mockStatic(
            ClassNameLocalServiceUtil.class);
    }

    @Test
    public void testSanitizeRemovesMissingAnyAssetType() throws Exception {
        long assetListEntryId = _randomPositiveLong();
        long segmentsEntryId = _randomPositiveLong();
        long classNameId = _randomPositiveLong();

        _classNameLocalServiceUtilMockedStatic.when(
            () -> ClassNameLocalServiceUtil.fetchClassName(classNameId)
        ).thenReturn(
            null
        );

        UnicodeProperties unicodeProperties = UnicodePropertiesBuilder.put(
            "anyAssetType", classNameId
        ).build();

        UnicodeProperties sanitizedUnicodeProperties =
            AssetListTypeSettingsSanitizerUtil.sanitize(
                assetListEntryId, segmentsEntryId, unicodeProperties);

        Assert.assertNull(
            sanitizedUnicodeProperties.getProperty("anyAssetType"));

        Mockito.verify(
            _assetListEntryLocalService
        ).updateAssetListEntryTypeSettings(
            assetListEntryId, segmentsEntryId,
            sanitizedUnicodeProperties.toString());
    }

    @Test
    public void testSanitizeRemovesMissingClassNameIds() throws Exception {
        long assetListEntryId = _randomPositiveLong();
        long segmentsEntryId = _randomPositiveLong();
        long missingClassNameId = _randomPositiveLong();
        long validClassNameId = _randomPositiveLong();

        _classNameLocalServiceUtilMockedStatic.when(
            () -> ClassNameLocalServiceUtil.fetchClassName(validClassNameId)
        ).thenReturn(
            new Object()
        );

        _classNameLocalServiceUtilMockedStatic.when(
            () -> ClassNameLocalServiceUtil.fetchClassName(missingClassNameId)
        ).thenReturn(
            null
        );

        UnicodeProperties unicodeProperties = UnicodePropertiesBuilder.put(
            "classNameIds",
            missingClassNameId + "," + validClassNameId
        ).build();

        UnicodeProperties sanitizedUnicodeProperties =
            AssetListTypeSettingsSanitizerUtil.sanitize(
                assetListEntryId, segmentsEntryId, unicodeProperties);

        Assert.assertEquals(
            String.valueOf(validClassNameId),
            sanitizedUnicodeProperties.getProperty("classNameIds"));

        Mockito.verify(
            _assetListEntryLocalService
        ).updateAssetListEntryTypeSettings(
            assetListEntryId, segmentsEntryId,
            sanitizedUnicodeProperties.toString());
    }

    private static Snapshot<AssetListEntryLocalService>
        _assetListEntryLocalServiceSnapshot;

    private long _randomPositiveLong() {
        return Math.max(1, Math.abs(RandomTestUtil.randomLong()));
    }

    private AssetListEntryLocalService _assetListEntryLocalService;
    private MockedStatic<ClassNameLocalServiceUtil>
        _classNameLocalServiceUtilMockedStatic;

}
