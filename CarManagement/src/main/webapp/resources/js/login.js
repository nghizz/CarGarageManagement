const loginForm = document.getElementById('login-form');
const errorMessage = document.getElementById('error-message');

loginForm.addEventListener('submit', (event) => {
    event.preventDefault(); // Ngăn chặn hành vi mặc định của form

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    // Gửi yêu cầu POST đến API để đăng nhập
    fetch('/api/auth/login', { 
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(data => {
                throw new Error(data.message || 'Đăng nhập thất bại');
            });
        }
        return response.json(); 
    })
    .then(data => {
        // Lưu trữ token JWT trong Local Storage
        localStorage.setItem('token', data.token); 

        // Hiển thị thông báo đăng nhập thành công
        alert(data.message || 'Đăng nhập thành công!'); 

        // Chuyển hướng đến trang chính
        window.location.href = '/'; 
    })
    .catch(error => {
        errorMessage.textContent = error.message; 
    });
});