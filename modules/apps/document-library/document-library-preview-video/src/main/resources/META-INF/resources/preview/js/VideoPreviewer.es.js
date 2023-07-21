/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './VideoPreviewer.soy';

/**
 * Component that create an video player
 * @review
 */
class VideoPreviewer extends Component {}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
VideoPreviewer.STATE = {
	/**
	 * The "poster" attribute of the <video> element
	 * @instance
	 * @memberof VideoPreviewer
	 * @review
	 * @type {String}
	 */
	videoPosterURL: Config.string(),

	/**
	 * List of of video sources
	 * @instance
	 * @memberof VideoPreviewer
	 * @review
	 * @type {!Array<object>}
	 */
	videoSources: Config.arrayOf(
		Config.shapeOf({
			type: Config.string().required(),
			url: Config.string().required()
		})
	).required()
};

Soy.register(VideoPreviewer, templates);
export {VideoPreviewer};
export default VideoPreviewer;
