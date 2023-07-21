/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy from 'metal-soy';
import {Config} from 'metal-state';

import templates from './AudioPreviewer.soy';

/**
 * Component that create an audio player
 * @review
 */
class AudioPreviewer extends Component {}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */
AudioPreviewer.STATE = {
	/**
	 * The max witdh of audio player based in
	 * video player width
	 * @instance
	 * @memberof AudioPreviewer
	 * @review
	 * @type {!number}
	 */
	audioMaxWidth: Config.number().required(),

	/**
	 * List of of audio sources
	 * @instance
	 * @memberof AudioPreviewer
	 * @review
	 * @type {!Array<object>}
	 */
	audioSources: Config.arrayOf(
		Config.shapeOf({
			type: Config.string().required(),
			url: Config.string().required()
		})
	).required()
};

Soy.register(AudioPreviewer, templates);
export {AudioPreviewer};
export default AudioPreviewer;
