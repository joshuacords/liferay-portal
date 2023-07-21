/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.mobile.device.rules.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the MDRRuleGroup service. Represents a row in the &quot;MDRRuleGroup&quot; database table, with each column mapped to a property of this class.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.mobile.device.rules.model.impl.MDRRuleGroupImpl"
)
@ProviderType
public interface MDRRuleGroup extends MDRRuleGroupModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.mobile.device.rules.model.impl.MDRRuleGroupImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MDRRuleGroup, Long> RULE_GROUP_ID_ACCESSOR =
		new Accessor<MDRRuleGroup, Long>() {

			@Override
			public Long get(MDRRuleGroup mdrRuleGroup) {
				return mdrRuleGroup.getRuleGroupId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<MDRRuleGroup> getTypeClass() {
				return MDRRuleGroup.class;
			}

		};

	public java.util.List<MDRRule> getRules();

}