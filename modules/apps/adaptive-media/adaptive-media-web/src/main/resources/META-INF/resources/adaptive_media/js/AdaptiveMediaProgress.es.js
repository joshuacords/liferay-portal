/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import 'clay-progress-bar';
import {PortletBase, fetch} from 'frontend-js-web';
import core from 'metal';
import Soy from 'metal-soy';

import templates from './AdaptiveMediaProgress.soy';

/**
 * Handles the actions of the configuration entry's progressbar.
 *
 * @abstract
 * @extends {PortletBase}
 */

class AdaptiveMediaProgress extends PortletBase {
	/**
	 * @inheritDoc
	 */
	created() {
		this._id = this.namespace + 'AdaptRemaining' + this.uuid + 'Progress';

		this._updateProgressBar(this.adaptedImages, this.totalImages);

		if (this.autoStartProgress) {
			this.startProgress();
		}
	}

	/**
	 * It starts checking the percentage of adapted images by
	 * doing ajax request continously.
	 *
	 * @param  {String} backgroundTaskUrl The background task
	 * that has to be invoked.
	 */
	startProgress(backgroundTaskUrl) {
		if (
			this._percentage >= 100 ||
			this.totalImages === 0 ||
			this.disabled
		) {
			return;
		}

		if (backgroundTaskUrl) {
			fetch(backgroundTaskUrl);
		}

		this._clearInterval();

		this._intervalId = setInterval(
			this._getAdaptedImagesPercentage.bind(this),
			this.intervalSpeed
		);

		this._showLoadingIndicator = true;

		this.emit('start', {uuid: this.uuid});
	}

	/**
	 * Clears the interval to stop sending ajax requests.
	 *
	 * @protected
	 */
	_clearInterval() {
		if (this._intervalId) {
			clearInterval(this._intervalId);
		}
	}

	/**
	 * Retry the proccess of adapting images
	 *
	 *  @protected
	 */
	_handleClickRetry() {
		this._imagesFailed = 0;

		this.startProgress();
	}

	/**
	 * Sends an ajax request to obtain the percentage of
	 * adapted images and updates the progressbar.
	 *
	 * @protected
	 */
	_getAdaptedImagesPercentage() {
		fetch(this.percentageUrl)
			.then(res => res.json())
			.then(json => {
				const adaptedImages = json.adaptedImages;
				const totalImages = json.totalImages;
				const errors = json.errors;

				this._imagesFailed = errors;
				this._updateProgressBar(adaptedImages, totalImages);

				if (
					this._percentage >= 100 ||
					adaptedImages + errors === totalImages
				) {
					this._onProgressBarComplete();
				}
			})
			.catch(() => {
				clearInterval(this._intervalId);
			});
	}

	/**
	 * Stops sending ajax request and hides the loading icon.
	 *
	 * @protected
	 */
	_onProgressBarComplete() {
		this._clearInterval();
		this._showLoadingIndicator = false;

		this.emit('finish', {uuid: this.uuid});
	}

	/**
	 * Updates the progressbar
	 *
	 * @param  {Number} progress progressbar value
	 * @protected
	 */
	_updateProgressBar(adaptedImages, totalImages) {
		this._percentage = Math.round((adaptedImages / totalImages) * 100) || 0;
		this._progressBarTooltip = this.tooltip
			? this.tooltip
			: adaptedImages + '/' + totalImages;
	}
}

/**
 * AdaptiveMedia State definition.
 * @ignore
 * @static
 * @type {!Object}
 */
AdaptiveMediaProgress.STATE = {
	/**
	 * Number of images that have failed durings
	 * the process of adapting images.
	 *
	 * @memberof AdaptiveMediaProgress
	 * @protected
	 * @type {Number}
	 */
	_imagesFailed: {
		validator: core.isNumber
	},
	/**
	 * Percentage of adapted images.
	 *
	 * @memberof AdaptiveMediaProgress
	 * @protected
	 * @type {Number}
	 */
	_percentage: {
		validator: core.isNumber
	},

	/**
	 * The progress bar tooltip. If AdaptiveMediaProgress.tooltip
	 * is defined, this will be used.
	 *
	 * @memberof AdaptiveMediaProgress
	 * @protected
	 * @type {String}
	 */
	_progressBarTooltip: {
		validator: core.isString
	},

	/**
	 * If the loading indicator has to be shown
	 * near the progress bar.
	 *
	 * @memberof AdaptiveMediaProgress
	 * @protected
	 * @type {Boolean}
	 */
	_showLoadingIndicator: {
		validator: core.isBoolean,
		value: false
	},

	/**
	 * Number of adapted images in the platform.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {Number}
	 */
	adaptedImages: {
		validator: core.isNumber
	},

	/**
	 * Number of adapted images in the platform.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {Number}
	 */
	autoStartProgress: {
		validator: core.isBoolean,
		value: false
	},

	/**
	 * Indicates if the entry is disabled or not.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {Boolean}
	 */
	disabled: {
		validator: core.isBoolean,
		value: false
	},

	/**
	 * The interval (in milliseconds) on how often
	 * we will check the percentage of adapted images.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {Number}
	 */
	intervalSpeed: {
		validator: core.isNumber,
		value: 1000
	},

	/**
	 * Url to the action that returns the percentage
	 * of adapted images.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {String}
	 */
	percentageUrl: {
		validator: core.isString
	},

	/**
	 * The path to the SVG spritemap file containing the icons.
	 * @memberof AdaptiveMediaProgress
	 * @protected
	 * @type {String}
	 */
	spritemap: {
		validator: core.isString
	},

	/**
	 * The tooltip text to show in the progress bar.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {String}
	 */
	tooltip: {
		validator: core.isString
	},

	/**
	 * Number of images present in the platform.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {Number}
	 */
	totalImages: {
		validator: core.isNumber
	},

	/**
	 * Configuration entry's uuid.
	 *
	 * @instance
	 * @memberof AdaptiveMediaProgress
	 * @type {String}
	 */
	uuid: {
		validator: core.isString
	}
};

// Register component

Soy.register(AdaptiveMediaProgress, templates);

export default AdaptiveMediaProgress;
