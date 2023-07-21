/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import 'clay-badge';

import 'clay-dropdown';
import Component from 'metal-component';
import {closest} from 'metal-dom';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './LayoutColumn.soy';
import LAYOUT_COLUMN_ITEM_DROPDOWN_ITEMS from './utils/LayoutColumnItemDropdownItems.es';

/**
 * LayoutColumn
 */
class LayoutColumn extends Component {
	/**
	 * Get layout column item dropdown options
	 * @param {object} layoutColumnItem
	 * @return {object[]} Dropdown options
	 * @review
	 */
	static _getLayoutColumnItemDropDownItems(layoutColumnItem, namespace) {
		const {actionURLs = {}} = layoutColumnItem;

		const dropdownItems = LAYOUT_COLUMN_ITEM_DROPDOWN_ITEMS.filter(
			dropdownItem => actionURLs[dropdownItem.name]
		).map(dropdownItem => ({
			handleClick: dropdownItem.handleClick || null,
			href: actionURLs[dropdownItem.name],
			icons: dropdownItem.icons,
			label: dropdownItem.label,
			layoutColumnItem,
			namespace,
			target: dropdownItem.target || '_self'
		}));

		return dropdownItems;
	}

	/**
	 * @param {object} state
	 * @inheritdoc
	 */
	prepareStateForRender(state) {
		const layoutColumn = this.layoutColumn.map(layoutColumnItem => ({
			...layoutColumnItem,
			dropdownItems: LayoutColumn._getLayoutColumnItemDropDownItems(
				layoutColumnItem,
				this.portletNamespace
			)
		}));

		return Object.assign(state, {
			layoutColumn
		});
	}

	/**
	 * @inheritDoc
	 * @review
	 */
	rendered() {
		if (this.refs.active) {
			this.refs.active.scrollIntoView();
		}
	}

	/**
	 * Handle column item dropdown item click event.
	 * @param {Event} event
	 */
	_handleLayoutColumnItemDropdownItemClick(event) {
		if (event.data && event.data.item && event.data.item.handleClick) {
			event.data.item.handleClick(event, this);
		}
	}

	/**
	 * Handle column item title click event and propagate it to
	 * the corresponding item mask.
	 * @param {MouseEvent} event
	 */
	_handleLayoutColumnItemTitleClick(event) {
		const layoutItemElement = closest(event.delegateTarget, '.layout-item');

		const maskElement =
			layoutItemElement &&
			layoutItemElement.querySelector('.layout-column-item-click-mask');

		if (maskElement) {
			maskElement.click();
		}
	}
}

/**
 * State definition.
 * @type {!Object}
 * @static
 */

LayoutColumn.STATE = {
	/**
	 * List of layouts in the current column
	 * @default undefined
	 * @instance
	 * @memberof LayoutColumn
	 * @type {!Array}
	 */

	layoutColumn: Config.arrayOf(
		Config.shapeOf({
			actionURLs: Config.object().required(),
			actions: Config.string().required(),
			active: Config.bool().required(),
			description: Config.string().required(),
			hasChild: Config.bool().required(),
			hasScopeGroup: Config.bool().required(),
			plid: Config.string().required(),
			title: Config.string().required(),
			url: Config.string().required()
		})
	).required(),

	/**
	 * URL for using icons
	 * @default undefined
	 * @instance
	 * @memberof LayoutColumn
	 * @type {!string}
	 */

	pathThemeImages: Config.string().required(),

	/**
	 * Namespace of portlet to prefix parameters names
	 * @default undefined
	 * @instance
	 * @memberof LayoutColumn
	 * @type {!string}
	 */

	portletNamespace: Config.string().required(),

	/**
	 * Site navigation menu names, to add layouts by default
	 * @instance
	 * @memberof Layout
	 * @type {!string}
	 */

	siteNavigationMenuNames: Config.string().required(),

	/**
	 * CSS class to modify style
	 * @default undefined
	 * @instance
	 * @review
	 * @type {!string}
	 */

	styleModifier: Config.string()
};

Soy.register(LayoutColumn, templates);

export {LayoutColumn};
export default LayoutColumn;
