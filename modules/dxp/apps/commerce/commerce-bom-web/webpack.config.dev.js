/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');

const {defineServerResponses} = require('./dev/fakeServerUtilities');

const outputPath = path.resolve(__dirname, './dev/public');

module.exports = {
	devServer: {
		before(app) {
			defineServerResponses(app);
		},
		compress: false,
		contentBase: './dev/public',
		filename: path.join(outputPath, '/bundle.js'),
		historyApiFallback: true,
		hot: true,
		open: true,
		port: 9000,
		publicPath: '/'
	},
	entry: path.join(
		__dirname,
		'./src/main/resources/META-INF/resources/js/index.dev.es.js'
	),
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
			}
		]
	},
	output: {
		filename: 'bundle.js',
		path: outputPath
	},
	plugins: [
		new HtmlWebpackPlugin({
			inject: false,
			template: path.resolve(__dirname, './dev/public/index.html')
		})
	],
	resolve: {
		extensions: ['.js', '.jsx']
	}
};
