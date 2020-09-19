const path = require('path')
const rm = require('rimraf')

class DeleteFilePlugin {
  apply(compiler) {
    compiler.hooks.done.tap('DeleteFilePlugin', (stats) => {
      rm(path.resolve(__dirname, 'build/blank.js'), {}, () => {});
    });
  }
}

module.exports = DeleteFilePlugin;