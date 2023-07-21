/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {setIn} from './FragmentsEditorUpdateUtils.es';

/**
 * Adds encoded id to an object and creating a copy of the original one
 * @param {!object} obj Original object
 * @param {!Array} ids Array with id fields to be encoded
 * @private
 */
function _addEncodedId(obj, ids) {
	const idObj = {};

	ids.forEach(id => {
		idObj[id] = obj[id];
	});

	return setIn(obj, ['encodedId'], _encodeId(idObj));
}

/**
 * Encode an id object and returns encoded id
 * @param {!obj} obj
 */
const _encodeId = obj => btoa(JSON.stringify(obj));

/**
 * Decode an id and returns original object
 * @param {!string} str
 */
const decodeId = str => JSON.parse(atob(str));

/**
 * @param {!object} asset Asset object
 * @return {object} Asset object with encoded id added
 */
function encodeAssetId(asset) {
	if (!(asset && typeof asset === 'object')) {
		throw new TypeError('Expect input to be an object');
	}
	else if (!(asset.classNameId && asset.classPK)) {
		throw new Error('Expect input to be an asset');
	}

	return _addEncodedId(asset, ['classNameId', 'classPK']);
}

export {decodeId, encodeAssetId};
