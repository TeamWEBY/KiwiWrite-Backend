function changeIframeSource(source) {
  document.getElementById("iframeContent").src = source;
}

function changeIframeToKeyword() {
  changeIframeSource('./keyword.html');
}