const path = require('path')
const MergeIntoSingle = require('webpack-merge-and-include-globally')
const DeleteFilePlugin = require('./deleteFilePlugin.js')

module.exports = {
  entry: './blank.js',
  mode: 'production',
  output: {
    filename: 'blank.js',
    path: path.resolve(__dirname, 'build')
  },
  plugins: [
    new MergeIntoSingle({
      files: {
        'server.js': [
          'node_modules/react/umd/react.development.js',
          'node_modules/react-dom/umd/react-dom-server.browser.development.js'
        ]
      }
    }),
    new DeleteFilePlugin()
  ]
}