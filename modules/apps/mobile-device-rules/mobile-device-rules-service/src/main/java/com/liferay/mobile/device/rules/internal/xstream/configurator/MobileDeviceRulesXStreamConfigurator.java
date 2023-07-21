/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.internal.xstream.configurator;

import com.liferay.exportimport.kernel.xstream.XStreamAlias;
import com.liferay.exportimport.kernel.xstream.XStreamConverter;
import com.liferay.exportimport.kernel.xstream.XStreamType;
import com.liferay.mobile.device.rules.model.impl.MDRActionImpl;
import com.liferay.mobile.device.rules.model.impl.MDRRuleGroupImpl;
import com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceImpl;
import com.liferay.mobile.device.rules.model.impl.MDRRuleImpl;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.xstream.configurator.XStreamConfigurator;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Máté Thurzó
 */
@Component(immediate = true, service = XStreamConfigurator.class)
public class MobileDeviceRulesXStreamConfigurator
	implements XStreamConfigurator {

	@Override
	public List<XStreamType> getAllowedXStreamTypes() {
		return null;
	}

	@Override
	public List<XStreamAlias> getXStreamAliases() {
		return ListUtil.fromArray(_xStreamAliases);
	}

	@Override
	public List<XStreamConverter> getXStreamConverters() {
		return null;
	}

	protected void activate() {
		_xStreamAliases = new XStreamAlias[] {
			new XStreamAlias(MDRActionImpl.class, "MDRAction"),
			new XStreamAlias(MDRRuleImpl.class, "MDRRule"),
			new XStreamAlias(MDRRuleGroupImpl.class, "MDRRuleGroup"),
			new XStreamAlias(
				MDRRuleGroupInstanceImpl.class, "MDRRuleGroupInstance")
		};
	}

	private XStreamAlias[] _xStreamAliases;

}