 var path = require('path');
 var webpack = require('webpack');

 module.exports = {
     entry: path.resolve(__dirname, 'src/main/js/app.jsx'),
     output: {
         path: path.resolve(__dirname, 'bin/static/scripts'),
         filename: 'main.bundle.js'
     },
     module: {
    	 loaders: [
    		    {
    		      test: /\.(js|jsx)$/,
    		      exclude: /node_modules/,
    		      loader: "babel-loader"
    		    }
    		  ]
     },
     stats: {
         colors: true
     },
     devtool: 'source-map'
 };