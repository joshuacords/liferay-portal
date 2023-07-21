/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import './ColorPaletteFieldDelegateTemplate.soy';
import {getConnectedComponent} from '../../../../store/ConnectedComponent.es';
import templates from './ColorPaletteField.soy';

/**
 * ColorPaletteField
 */
class ColorPaletteField extends Component {
	/**
	 * Handle Color Value Change
	 * @param {Event} event
	 * @private
	 * @review
	 */
	_handleColorValueChanged(event) {
		const targetElement = event.delegateTarget;

		this.emit('fieldValueChanged', {
			name: this.field.name,
			value: {
				cssClass: targetElement.dataset.backgroundColorCssClass,
				rgbValue: getComputedStyle(targetElement).backgroundColor
			}
		});
	}
}

ColorPaletteField.STATE = {
	/**
	 * The configuration field
	 * @review
	 * @type {object}
	 */
	field: Config.shapeOf({
		dataType: Config.string(),
		defaultValue: Config.object(),
		description: Config.string(),
		label: Config.string(),
		name: Config.string(),
		type: Config.string(),
		typeOptions: Config.object()
	})
};

const ConnectedColorPaletteField = getConnectedComponent(ColorPaletteField, [
	'themeColorsCssClasses'
]);

Soy.register(ConnectedColorPaletteField, templates);

export {ConnectedColorPaletteField, ColorPaletteField};
export default ConnectedColorPaletteField;
