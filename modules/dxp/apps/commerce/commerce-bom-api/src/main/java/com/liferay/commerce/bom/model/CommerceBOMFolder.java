/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.bom.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.TreeModel;
import com.liferay.portal.kernel.util.Accessor;

/**
 * The extended model interface for the CommerceBOMFolder service. Represents a row in the &quot;CommerceBOMFolder&quot; database table, with each column mapped to a property of this class.
 *
 * @author Luca Pellizzon
 * @see CommerceBOMFolderModel
 * @generated
 */
@ImplementationClassName(
	"com.liferay.commerce.bom.model.impl.CommerceBOMFolderImpl"
)
@ProviderType
public interface CommerceBOMFolder
	extends CommerceBOMFolderModel, PersistedModel, TreeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.liferay.commerce.bom.model.impl.CommerceBOMFolderImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<CommerceBOMFolder, Long>
		COMMERCE_BOM_FOLDER_ID_ACCESSOR =
			new Accessor<CommerceBOMFolder, Long>() {

				@Override
				public Long get(CommerceBOMFolder commerceBOMFolder) {
					return commerceBOMFolder.getCommerceBOMFolderId();
				}

				@Override
				public Class<Long> getAttributeClass() {
					return Long.class;
				}

				@Override
				public Class<CommerceBOMFolder> getTypeClass() {
					return CommerceBOMFolder.class;
				}

			};

	public java.util.List<Long> getAncestorCommerceBOMFolderIds()
		throws com.liferay.portal.kernel.exception.PortalException;

	public java.util.List<CommerceBOMFolder> getAncestors()
		throws com.liferay.portal.kernel.exception.PortalException;

	public CommerceBOMFolder getParentCommerceBOMFolder()
		throws com.liferay.portal.kernel.exception.PortalException;

	public boolean isRoot();

}