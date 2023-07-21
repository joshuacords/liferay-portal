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
 * The extended model interface for the MDRRuleGroupInstance service. Represents a row in the &quot;MDRRuleGroupInstance&quot; database table, with each column mapped to a property of this class.
 *
 * @author Edward C. Han
 * @see MDRRuleGroupInstanceModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceImpl"
)
@ProviderType
public interface MDRRuleGroupInstance
	extends MDRRuleGroupInstanceModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.mobile.device.rules.model.impl.MDRRuleGroupInstanceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<MDRRuleGroupInstance, Long>
		RULE_GROUP_INSTANCE_ID_ACCESSOR =
			new Accessor<MDRRuleGroupInstance, Long>() {

				@Override
				public Long get(MDRRuleGroupInstance mdrRuleGroupInstance) {
					return mdrRuleGroupInstance.getRuleGroupInstanceId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<MDRRuleGroupInstance> getTypeClass() {
					return MDRRuleGroupInstance.class;
				}

			};

	public java.util.List<MDRAction> getActions();

	public MDRRuleGroup getRuleGroup()
		throws com.liferay.portal.kernel.exception.PortalException;

}