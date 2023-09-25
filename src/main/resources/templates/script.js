function toggleEditor() {
  var editorContainer = document.getElementById('editor-container');
  editorContainer.style.display = (editorContainer.style.display === 'none') ? 'flex' : 'none';
}

window.onload = function() {
  var postTitle = document.getElementById('post-title');
  var postContent = document.getElementById('post-content');
  var saveButton = document.getElementById('save-button');
  var postList = document.getElementById('post-list');

  saveButton.addEventListener('click', function() {
      var title = postTitle.value;
      var content = postContent.value;

      var listItem = document.createElement('li');
      listItem.classList.add('post-item'); // .post-item 클래스 추가
      listItem.innerHTML = '<h3>' + title + '</h3><p>' + content + '</p>';
      listItem.setAttribute('data-time', getCurrentTime()); // 작성 시간 추가
      postList.appendChild(listItem);

      postTitle.value = '';
      postContent.value = '';
  });
 

  function getCurrentTime() {
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth() + 1;
      var day = now.getDate();
      var hours = now.getHours();
      var minutes = now.getMinutes();
      return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes;
  }
};
function goToNeighbors() {
  window.location.href = 'neighbors.html'; // 이동할 페이지 URL 설정
}
