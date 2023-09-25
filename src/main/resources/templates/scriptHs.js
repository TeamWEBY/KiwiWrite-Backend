// scriptHS.js 파일의 내용
document.getElementById('loginButton').addEventListener('click', function() {
  window.location.href = 'login.html';
});

document.querySelector('form').addEventListener('submit', function(event) {
  event.preventDefault(); // 기본 폼 제출 동작 막기
  // 회원가입 처리 로직을 구현해야 합니다.
  // 입력된 이메일, 비밀번호, 필명 등을 서버로 전송하여 계정을 만드는 등의 동작을 수행해야 합니다.
  // 서버 측 회원가입 처리를 위해 Ajax나 fetch API를 사용하거나, 클라이언트 측 회원가입 동작을 구현할 수 있습니다.
});

document.getElementById('emailForm').addEventListener('submit', function(event) {
  event.preventDefault(); // 기본 폼 제출 동작 막기
  // 이메일 전송 로직을 구현해주세요.
  // 이메일을 보내고 인증 번호 입력 페이지로 이동하는 코드를 추가합니다.
  window.location.href = 'verification.html';
});
