/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.fragment.web.internal.servlet.taglib.clay;

import com.liferay.fragment.model.FragmentCollection;
import com.liferay.frontend.taglib.clay.servlet.taglib.soy.HorizontalCard;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.HtmlUtil;

import java.util.Map;

/**
 * @author Eudaldo Alonso
 */
public class FragmentCollectionHorizontalCard implements HorizontalCard {

	public FragmentCollectionHorizontalCard(BaseModel<?> baseModel) {
		_fragmentCollection = (FragmentCollection)baseModel;
	}

	@Override
	public Map<String, String> getData() {
		return HashMapBuilder.put(
			"id", String.valueOf(_fragmentCollection.getFragmentCollectionId())
		).put(
			"name", _fragmentCollection.getName()
		).build();
	}

	@Override
	public String getElementClasses() {
		return "card-interactive card-interactive-primary selector-button";
	}

	@Override
	public String getIcon() {
		return "documents-and-media";
	}

	@Override
	public String getTitle() {
		return HtmlUtil.escape(_fragmentCollection.getName());
	}

	@Override
	public boolean isSelectable() {
		return false;
	}

	private final FragmentCollection _fragmentCollection;

}