function app() {
  return ReactDOMServer.renderToString(React.createElement('p', null, 'Hello from React DOM Server!'));
}