var packageJSON = require('./package.json');
var path = require('path');
var webpack = require('webpack');
var combineLoaders = require('webpack-combine-loaders');

module.exports = {
	devtool: 'source-map',
	entry: './index.js',
	output: {
		path: path.join(__dirname, 'generated'),
		filename: 'app-bundle.js'},
	resolve: {extensions: ['.js', '.jsx']},
	plugins: [
		new webpack.LoaderOptionsPlugin({
			debug: true}),
		new webpack.DefinePlugin({
			"process.env": {
				NODE_ENV: JSON.stringify("development")
			}
		})
	],
	module: {
		rules: [
			{
				test: /\.jsx?$/,
				loader: 'babel-loader',
				exclude: /node_modules/
			}, {
				test: /\.css$/,
				loader: combineLoaders([
					{
						loader: 'style-loader'
					}, {
						loader: 'css-loader',
						query: {
							modules: true,
							localIdentName: '[name]__[local]___[hash:base64:5]'
						}
					}
				])
			}
		]


	},
	devServer: {
		noInfo: false,
		quiet: false,
		lazy: false,
		watchOptions: {
			poll: true
		}
	}
}