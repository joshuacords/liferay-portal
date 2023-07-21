/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wiki.editor.configuration.internal;

import com.liferay.item.selector.ItemSelector;
import com.liferay.item.selector.ItemSelectorCriterion;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.portlet.PortletURLWrapper;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import org.skyscreamer.jsonassert.JSONAssert;

/**
 * @author Roberto Díaz
 */
@RunWith(PowerMockRunner.class)
public class WikiLinksCKEditorEditorConfigContributorTest extends PowerMockito {

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());

		_inputEditorTaglibAttributes.put(
			"liferay-ui:input-editor:name", "testEditor");

		when(
			_itemSelector.getItemSelectorURL(
				Matchers.any(RequestBackedPortletURLFactory.class),
				Matchers.anyString(), Matchers.any(ItemSelectorCriterion.class))
		).thenReturn(
			new PortletURLWrapper(null) {

				@Override
				public String toString() {
					return "oneTabItemSelectorPortletURL";
				}

			}
		);

		when(
			_itemSelector.getItemSelectorURL(
				Matchers.any(RequestBackedPortletURLFactory.class),
				Matchers.anyString(), Matchers.any(ItemSelectorCriterion.class),
				Matchers.any(ItemSelectorCriterion.class))
		).thenReturn(
			new PortletURLWrapper(null) {

				@Override
				public String toString() {
					return "twoTabsItemSelectorPortletURL";
				}

			}
		);

		_wikiLinksCKEditorEditorConfigContributor =
			new WikiLinksCKEditorConfigContributor();

		ReflectionTestUtil.setFieldValue(
			_wikiLinksCKEditorEditorConfigContributor, "itemSelector",
			_itemSelector);
	}

	@Test
	public void testItemSelectorURLWhenNullWikiPageAndValidNode()
		throws Exception {

		populateInputEditorWikiPageAttributes(0, 1);

		JSONObject originalJSONObject =
			getJSONObjectWithDefaultItemSelectorURL();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		_wikiLinksCKEditorEditorConfigContributor.populateConfigJSONObject(
			jsonObject, _inputEditorTaglibAttributes, null, null);

		JSONObject expectedJSONObject = JSONUtil.put(
			"filebrowserBrowseUrl", "oneTabItemSelectorPortletURL"
		).put(
			"removePlugins", "plugin1"
		);

		JSONAssert.assertEquals(
			expectedJSONObject.toString(), jsonObject.toString(), true);
	}

	@Test
	public void testItemSelectorURLWhenValidWikiPageAndNode() throws Exception {
		populateInputEditorWikiPageAttributes(1, 1);

		JSONObject originalJSONObject =
			getJSONObjectWithDefaultItemSelectorURL();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		_wikiLinksCKEditorEditorConfigContributor.populateConfigJSONObject(
			jsonObject, _inputEditorTaglibAttributes, null, null);

		JSONObject expectedJSONObject = JSONUtil.put(
			"filebrowserBrowseUrl", "twoTabsItemSelectorPortletURL"
		).put(
			"removePlugins", "plugin1"
		);

		JSONAssert.assertEquals(
			expectedJSONObject.toString(), jsonObject.toString(), true);
	}

	@Test
	public void testItemSelectorURLWhenValidWikiPageAndNullNode()
		throws Exception {

		populateInputEditorWikiPageAttributes(1, 0);

		JSONObject originalJSONObject =
			getJSONObjectWithDefaultItemSelectorURL();

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			originalJSONObject.toString());

		_wikiLinksCKEditorEditorConfigContributor.populateConfigJSONObject(
			jsonObject, _inputEditorTaglibAttributes, null, null);

		JSONObject expectedJSONObject = JSONUtil.put(
			"filebrowserBrowseUrl", "oneTabItemSelectorPortletURL"
		).put(
			"removePlugins", "plugin1"
		);

		JSONAssert.assertEquals(
			expectedJSONObject.toString(), jsonObject.toString(), true);
	}

	protected JSONObject getJSONObjectWithDefaultItemSelectorURL()
		throws Exception {

		return JSONUtil.put(
			"filebrowserBrowseUrl", "defaultItemSelectorPortletURL"
		).put(
			"removePlugins", "plugin1"
		);
	}

	protected void populateInputEditorWikiPageAttributes(
		long wikiPageResourcePrimKey, long nodeId) {

		Map<String, String> fileBrowserParamsMap = HashMapBuilder.put(
			"nodeId", String.valueOf(nodeId)
		).put(
			"wikiPageResourcePrimKey", String.valueOf(wikiPageResourcePrimKey)
		).build();

		_inputEditorTaglibAttributes.put(
			"liferay-ui:input-editor:fileBrowserParams", fileBrowserParamsMap);
	}

	private final Map<String, Object> _inputEditorTaglibAttributes =
		new HashMap<>();

	@Mock
	private ItemSelector _itemSelector;

	private WikiLinksCKEditorConfigContributor
		_wikiLinksCKEditorEditorConfigContributor;

}