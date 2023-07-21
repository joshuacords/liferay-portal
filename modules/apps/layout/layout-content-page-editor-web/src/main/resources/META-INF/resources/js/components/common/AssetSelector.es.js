/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';

import getConnectedComponent from '../../store/ConnectedComponent.es';
import templates from './AssetSelector.soy';

/**
 * AssetSelector
 */
class AssetSelector extends Component {}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
AssetSelector.STATE = {};

const ConnectedAssetSelector = getConnectedComponent(AssetSelector, [
	'spritemap'
]);

Soy.register(ConnectedAssetSelector, templates);

export {ConnectedAssetSelector, AssetSelector};
export default ConnectedAssetSelector;
