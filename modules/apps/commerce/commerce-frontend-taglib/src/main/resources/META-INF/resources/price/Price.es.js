/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import Component from 'metal-component';
import Soy, {Config} from 'metal-soy';

import template from './Price.soy';

class Price extends Component {
	created() {
		window.Liferay.on('priceUpdated', this._updatePrice, this);
	}

	detached() {
		window.Liferay.detach('priceUpdated', this._updatePrice, this);
	}
	_updatePrice(e) {
		if (e.id === this.id) {
			this.displayDiscountLevels = e.displayDiscountLevels;
			this.prices = e.prices;
		}
	}
}

Price.STATE = {
	additionalDiscountClasses: Config.string(),
	additionalPriceClasses: Config.string(),
	additionalPromoPriceClasses: Config.string(),
	displayDiscountLevels: Config.bool().value(false),
	id: Config.string(),
	prices: Config.shapeOf({
		discountPercentage: Config.string(),
		discountPercentages: Config.array().value(null),
		finalPrice: Config.string(),
		price: Config.string().required(),
		promoPrice: Config.string()
	})
};

Soy.register(Price, template);

export {Price};
export default Price;
