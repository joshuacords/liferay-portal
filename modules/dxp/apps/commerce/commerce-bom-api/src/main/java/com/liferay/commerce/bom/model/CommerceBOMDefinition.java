/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceBOMDefinition service. Represents a row in the &quot;CommerceBOMDefinition&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMDefinitionModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionImpl"
)
@ProviderType
public interface CommerceBOMDefinition
	extends CommerceBOMDefinitionModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.bom.model.impl.CommerceBOMDefinitionImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBOMDefinition, Long>
		COMMERCE_BOM_DEFINITION_ID_ACCESSOR =
			new Accessor<CommerceBOMDefinition, Long>() {

				@Override
				public Long get(CommerceBOMDefinition commerceBOMDefinition) {
					return commerceBOMDefinition.getCommerceBOMDefinitionId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceBOMDefinition> getTypeClass() {
					return CommerceBOMDefinition.class;
				}

			};

	public CommerceBOMFolder fetchCommerceBOMFolder();

	public com.liferay.commerce.product.model.CPAttachmentFileEntry
		fetchCPAttachmentFileEntry();

}