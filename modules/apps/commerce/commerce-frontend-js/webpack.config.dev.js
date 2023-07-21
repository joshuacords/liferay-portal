/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');
const webpack = require('webpack');

const components = require('./test/dev/components/index');
const {defineServerResponses} = require('./test/dev/fakeServerUtilities');

const outputPath = path.resolve(__dirname, './dev/public');

function getComponentPath(entry) {
	return path.join(__dirname, 'test', 'dev', 'components', entry);
}

// eslint-disable-next-line no-undef
module.exports = {
	devServer: {
		before(app) {
			defineServerResponses(app);
		},
		compress: false,
		contentBase: './test/dev/public',
		open: true,
		openPage: 'index.html',
		port: 9000,
		proxy: {
			'/o': {
				target: 'http://localhost:8080/'
			}
		},
		publicPath: '/'
	},
	devtool: 'inline-source-map',
	entry: [...components, {entry: 'Menu'}].reduce((comp, current) => {
		comp[current.entry] = getComponentPath(current.entry);

		return comp;
	}, {}),
	mode: 'development',
	module: {
		rules: [
			{
				exclude: /node_modules/,
				test: /\.(js|jsx)$/,
				use: [
					{
						loader: 'babel-loader'
					}
				]
			},
			{
				test: /\.(scss|css)$/,
				use: [
					{loader: 'style-loader'},
					{loader: 'css-loader'},
					{loader: 'sass-loader'}
				]
			},
			{
				exclude: /node_modules/,
				test: /\.tsx?$/,
				use: 'ts-loader'
			}
		]
	},
	output: {
		filename: '[name].js',
		path: outputPath
	},
	plugins: [
		new webpack.optimize.ModuleConcatenationPlugin(),
		new HtmlWebpackPlugin({
			inject: false,
			template: path.resolve(__dirname, './test/dev/public/index.html')
		})
	],
	resolve: {
		extensions: ['.js', '.jsx']
	}
};
