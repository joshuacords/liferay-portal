/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import classnames from 'classnames';
import PropTypes from 'prop-types';
import React from 'react';

import {collectDiscountLevels, isNonnull} from '../util/index';

function ItemPriceView({displayDiscountLevels, price}) {
	const {
		discountPercentage,
		finalPriceFormatted,
		priceFormatted,
		promoPrice,
		promoPriceFormatted
	} = price;

	const discountLevels = displayDiscountLevels
			? collectDiscountLevels(price)
			: [],
		hasPromo = isNonnull(promoPrice),
		hasDiscount = isNonnull(discountPercentage, ...discountLevels);

	return (
		<div className={'price'}>
			<span className="price-label">
				{Liferay.Language.get('catalog-price')}
			</span>
			<span
				className={classnames(
					'price-value',
					(hasPromo || hasDiscount) && 'price-value-inactive'
				)}
			>
				{priceFormatted}
			</span>

			{hasPromo && (
				<>
					<span className={'price-label'}>
						{Liferay.Language.get('promo-price')}
					</span>
					<span
						className={classnames(
							'price-value price-value-promo',
							hasDiscount && 'price-value-inactive'
						)}
					>
						{promoPriceFormatted}
					</span>
				</>
			)}

			{hasDiscount && (
				<>
					<span className="price-label">
						{Liferay.Language.get('discount')}
					</span>
					<span className="price-value price-value-discount">
						{displayDiscountLevels ? (
							discountLevels.map((level, index) => (
								<span
									className={'price-value-percentages'}
									key={index}
								>
									{level.slice(-2) === '00'
										? level.slice(0, level.length - 3)
										: level}
								</span>
							))
						) : (
							<span>&ndash;{discountPercentage}%</span>
						)}
					</span>
					<span className={'price-label'}>
						{Liferay.Language.get('final-price')}
					</span>
					<span className={'price-value price-value-final'}>
						{finalPriceFormatted}
					</span>
				</>
			)}
		</div>
	);
}

ItemPriceView.defaultProps = {
	displayDiscountLevels: false
};

ItemPriceView.propTypes = {
	displayDiscountLevels: PropTypes.bool,
	price: PropTypes.shape({
		currency: PropTypes.string.isRequired,
		discount: PropTypes.number,
		discountFormatted: PropTypes.string,
		discountPercentageLevel1: PropTypes.number,
		discountPercentageLevel2: PropTypes.number,
		discountPercentageLevel3: PropTypes.number,
		discountPercentageLevel4: PropTypes.number,
		finalPrice: PropTypes.number,
		finalPriceFormatted: PropTypes.string,
		price: PropTypes.number.isRequired,
		priceFormatted: PropTypes.string.isRequired,
		promoPrice: PropTypes.number,
		promoPriceFormatted: PropTypes.string
	}).isRequired
};

export default ItemPriceView;
