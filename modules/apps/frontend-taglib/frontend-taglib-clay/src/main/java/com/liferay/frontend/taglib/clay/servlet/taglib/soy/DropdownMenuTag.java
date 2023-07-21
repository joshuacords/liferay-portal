/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.frontend.taglib.clay.servlet.taglib.soy;

import com.liferay.frontend.taglib.clay.servlet.taglib.soy.base.BaseClayTag;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

/**
 * @author Chema Balsas
 */
public class DropdownMenuTag extends BaseClayTag {

	@Override
	public int doEndTag() throws JspException {
		Map<String, Object> context = getContext();

		List<DropdownItem> dropdownItems = (List<DropdownItem>)context.get(
			"items");

		if (ListUtil.isEmpty(dropdownItems)) {
			return EVAL_PAGE;
		}

		return super.doEndTag();
	}

	@Override
	public int doStartTag() {
		setComponentBaseName("ClayDropdown");
		setHydrate(true);
		setModuleBaseName("dropdown");

		Map<String, Object> context = getContext();

		if (Validator.isNotNull(context.get("buttonLabel"))) {
			putValue(
				"button",
				HashMapBuilder.put(
					"label", (String)context.get("buttonLabel")
				).put(
					"style", (String)context.get("buttonStyle")
				).put(
					"type", (String)context.get("buttonType")
				).build());
		}

		if (PortalUtil.isRightToLeft(request)) {
			putValue("preferredAlign", "BottomRight");
		}

		return super.doStartTag();
	}

	public void setButtonLabel(String buttonLabel) {
		putValue("buttonLabel", buttonLabel);
	}

	public void setButtonStyle(String buttonStyle) {
		putValue("buttonStyle", buttonStyle);
	}

	public void setButtonType(String buttonType) {
		putValue("buttonType", buttonType);
	}

	public void setDropdownItems(List<DropdownItem> dropdownItems) {
		putValue("items", dropdownItems);
	}

	public void setExpanded(Boolean expanded) {
		putValue("expanded", expanded);
	}

	public void setIcon(String icon) {
		putValue("icon", icon);
	}

	public void setItemsIconAlignment(String itemsIconAlignment) {
		putValue("itemsIconAlignment", itemsIconAlignment);
	}

	public void setLabel(String label) {
		putValue("label", label);
	}

	public void setSearchable(Boolean searchable) {
		putValue("searchable", searchable);
	}

	public void setShowToggleIcon(Boolean showToggleIcon) {
		putValue("showToggleIcon", showToggleIcon);
	}

	public void setStyle(String style) {
		putValue("style", style);
	}

	public void setTriggerCssClasses(String triggerCssClasses) {
		putValue("triggerClasses", triggerCssClasses);
	}

	public void setTriggerTitle(String triggerTitle) {
		putValue("triggerTitle", triggerTitle);
	}

	public void setType(String type) {
		putValue("type", type);
	}

}