const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

// set up the defaults
module.exports = {
	entry: './src/index.js',
	output: {
		path: path.join(__dirname, '/dist'),
		filename: 'index_bundle.js'
	},
	module: {
		rules: [
			{
				test: /\.js$/,
				exclude: /node_modules/,
				use: {
					loader: 'babel-loader'
				}
			}
		]
	},
	plugins: [
		new HtmlWebpackPlugin({
			template: './src/index.html'
		})
	],
	
	resolve: {
		modules: ['C:/Users/gpad/Desktop/cezdev/libs/test', 'node_modules'],
	
		alias: {
			C3DClassesSDK: 'C:/Users/gpad/Desktop/cezdev/libs/test',
		}
  }
} // end module.exports

module.exports.entry = "[entry-js-file]";
module.exports.output.path = path.join(__dirname, "[output-path]");
module.exports.output.filename = "[output-file]";
module.exports.module.rules[0].test = [files-to-test];
module.exports.module.rules[0].exclude = [files-to-exclude];
module.exports.plugins[0] = new HtmlWebpackPlugin({template:'[entry-html-file]'});