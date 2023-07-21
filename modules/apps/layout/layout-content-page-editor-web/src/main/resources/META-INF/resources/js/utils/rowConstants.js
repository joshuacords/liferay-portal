/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

/**
 * Available values for containerType config key
 */
export const CONTAINER_TYPES = [
	{
		containerTypeId: 'fixed',
		label: Liferay.Language.get('fixed-width')
	},

	{
		containerTypeId: 'fluid',
		label: Liferay.Language.get('fluid')
	}
];

/**
 * Available element config keys
 */
export const CONFIG_KEYS = {
	backgroundColorCssClass: 'backgroundColorCssClass',
	columnSpacing: 'columnSpacing',
	containerType: 'containerType',
	nonIndexable: 'nonIndexable',
	paddingHorizontal: 'paddingHorizontal',
	paddingVertical: 'paddingVertical'
};

/**
 * Default padding size
 */
const DEFAULT_PADDING_SIZE = '3';

/**
 * Max row columns
 */
export const MAX_COLUMNS = 12;

/**
 * Available values for containerType config key
 */
export const NUMBER_OF_COLUMNS_OPTIONS = [
	{
		label: '1',
		numberOfColumnId: '1'
	},
	{
		label: '2',
		numberOfColumnId: '2'
	},
	{
		label: '3',
		numberOfColumnId: '3'
	},
	{
		label: '4',
		numberOfColumnId: '4'
	},
	{
		label: '5',
		numberOfColumnId: '5'
	},
	{
		label: '6',
		numberOfColumnId: '6'
	}
];

/**
 * Available values for padding (horizontal and vertical) config key
 */
export const PADDING_OPTIONS = [
	{
		label: '0',
		paddingSize: '0'
	},
	{
		label: '1',
		paddingSize: '3'
	},
	{
		label: '2',
		paddingSize: '4'
	},
	{
		label: '4',
		paddingSize: '5'
	},
	{
		label: '6',
		paddingSize: '6'
	},
	{
		label: '8',
		paddingSize: '7'
	},
	{
		label: '10',
		paddingSize: '8'
	}
];

/**
 * Default component row config
 */
export const DEFAULT_COMPONENT_ROW_CONFIG = {
	[CONFIG_KEYS.columnSpacing]: true,
	[CONFIG_KEYS.paddingHorizontal]: DEFAULT_PADDING_SIZE,
	[CONFIG_KEYS.paddingVertical]: DEFAULT_PADDING_SIZE
};

/**
 * Default section row config
 */
export const DEFAULT_SECTION_ROW_CONFIG = {
	[CONFIG_KEYS.columnSpacing]: true,
	[CONFIG_KEYS.paddingHorizontal]: '0',
	[CONFIG_KEYS.paddingVertical]: '0'
};
