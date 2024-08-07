function toggleDropdown() {
    const dropdown = document.querySelector('.user-info .dropdown-menu');
    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
}
// Close the dropdown if the user clicks outside of it
window.onclick = function(event) {
    if (!event.target.closest('.user-info')) {
        const dropdowns = document.getElementsByClassName('dropdown-menu');
        for (let i = 0; i < dropdowns.length; i++) {
            const openDropdown = dropdowns[i];
            if (openDropdown.style.display === 'block') {
                openDropdown.style.display = 'none';
            }
        }
    }
}


function showDetails(soDen, ngayBanHanh, coQuanBanHanh, loaiVanBan, soKyHieu, trichYeu, nguoiXuLy, vanBanChiDao, ngayHetHanXLVB, ngayHetHanXuLyCoPhong, tepDinhKem) {
    const mainContent = document.getElementById('main-content');
    mainContent.innerHTML = `
        <div>
            <div class="sub-option">
                <ul class="child-option">
                    <li>
                        <a id="saveAndTrinhChanhVP"><i class="fa-solid fa-file-export"></i></i>Trình Chánh VP</a>
                    </li>
                    <li>
                        <a><i class="fa-solid fa-pen-to-square"></i> Chỉnh sửa</a>
                    </li>
                    <li>
                        <a><i class="fa-solid fa-trash"></i> Xoá</a>
                    </li>
                </ul>
            <div>
            <div style="padding: 10px;">
                <h2>Chi tiết văn bản</h2>
                <p><strong>Số đến:</strong> ${soDen}</p>
                <p><strong>Ngày ban hành:</strong> ${ngayBanHanh}</p>
                <p><strong>Cơ quan ban hành:</strong> ${coQuanBanHanh}</p>
                <p><strong>Loại văn bản:</strong> ${loaiVanBan}</p>
                <p><strong>Số, ký hiệu:</strong> ${soKyHieu}</p>
                <p><strong>Trích yếu:</strong> ${trichYeu}</p>
                <p><strong>Người xử lý:</strong> ${nguoiXuLy}</p>
                <p><strong>Văn bản chỉ đạo:</strong> ${vanBanChiDao}</p>
                <p><strong>Ngày hết hạn XLVB:</strong> ${ngayHetHanXLVB}</p>
                <p><strong>Ngày hết hạn xử lý của phòng:</strong> ${ngayHetHanXuLyCoPhong}</p>
                <p><strong>Tệp đính kèm:</strong></p>
                <ul>
                    ${tepDinhKem.map(tep => `<li><a href="#">${tep}</a></li>`).join('')}
                </ul>
            </div>
        </div>
    `;

    // Gán sự kiện sau khi nội dung được cập nhật
    document.getElementById('saveAndTrinhChanhVP').addEventListener('click', function(event) {
        event.preventDefault();
        document.getElementById('modal').style.display = 'block';
    });
}

document.getElementById('fileInput').addEventListener('change', function(event) {
    const fileList = document.getElementById('fileList');
    fileList.innerHTML = ''; // Clear the list

    Array.from(event.target.files).forEach((file, index) => {
        const fileItem = document.createElement('div');
        fileItem.className = 'file-item';

        const fileName = document.createElement('span');
        fileName.textContent = file.name;
        fileItem.appendChild(fileName);

        const removeButton = document.createElement('button');
        removeButton.textContent = 'Xoá';
        removeButton.addEventListener('click', () => {
            const dataTransfer = new DataTransfer();
            const files = Array.from(event.target.files);
            files.splice(index, 1);

            files.forEach(file => {
                dataTransfer.items.add(file);
            });

            event.target.files = dataTransfer.files;
            fileItem.remove();
        });
        fileItem.appendChild(removeButton);

        fileList.appendChild(fileItem);
    });
});


document.addEventListener('DOMContentLoaded', function() {
    // Tính toán thời gian hiện tại theo giờ Việt Nam (UTC+7)
    const now = new Date();
    const offset = 7 * 60; // Giờ Việt Nam là UTC+7
    const localTime = new Date(now.getTime() + offset * 60 * 1000);

    // Định dạng ngày theo định dạng 'YYYY-MM-DD'
    const formattedDate = localTime.toISOString().split('T')[0];
    // Định dạng ngày giờ theo định dạng 'YYYY-MM-DDTHH:MM'
    const formattedDateTime = localTime.toISOString().slice(0,16);

    // Cập nhật giá trị cho các trường ngày
    document.getElementById('arrivalDate').value = formattedDateTime;
    document.getElementById('issueDate').value = formattedDate;
});

document.getElementById('saveAndSubmit').addEventListener('click', function(event) {
    event.preventDefault(); // Ngăn hành động mặc định của liên kết

    // Kiểm tra tính hợp lệ của biểu mẫu
    const form = document.querySelector('form');
    if (form.checkValidity()) {
        // Hiển thị hộp thoại xác nhận
        const isConfirmed = confirm('Bạn chắc chắn lưu văn bản?');

        if (isConfirmed) {
            // Gửi biểu mẫu nếu người dùng chọn đồng ý
            form.submit(); // Hoặc xử lý gửi dữ liệu bằng AJAX/Fetch API ở đây
            alert('Form đã được gửi thành công!');
        } else {
            // Nếu người dùng chọn hủy, không làm gì cả
            alert('Đã hủy thao tác lưu.');
        }
    } else {
        // Nếu biểu mẫu không hợp lệ, hiển thị thông báo lỗi
        alert('Vui lòng điền đầy đủ thông tin.');
    }
});

// Thêm sự kiện click cho nút "Lưu và Trình Chánh VP"
document.getElementById('saveAndTrinhChanhVP').addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById('modal').style.display = 'block';
});

// Thêm sự kiện click cho nút đóng modal
document.querySelector('.modal .close').addEventListener('click', function() {
    document.getElementById('modal').style.display = 'none';
});

// Thêm sự kiện click cho nút "Chuyển"
document.getElementById('sendButton').addEventListener('click', function() {
    alert('Văn bản đã được chuyển!');
    document.getElementById('modal').style.display = 'none';
});


document.addEventListener('DOMContentLoaded', function() {
    var selectAllCheckbox = document.getElementById('selectAllCheckbox');
    var rowCheckboxes = document.querySelectorAll('.rowCheckbox');
    var tableRows = document.querySelectorAll('.document-table tbody tr');
    var selectElement = document.getElementById('selectOptions');

    // Khi checkbox "Chọn tất cả" thay đổi
    selectAllCheckbox.addEventListener('change', function() {
        var isChecked = selectAllCheckbox.checked;
        rowCheckboxes.forEach(function(checkbox) {
            checkbox.checked = isChecked;
            toggleHighlight(checkbox);
        });
        updateSelectOptions();
    });

    // Khi checkbox trong bảng thay đổi
    rowCheckboxes.forEach(function(checkbox) {
        checkbox.addEventListener('change', function() {
            toggleHighlight(checkbox);
            updateSelectAllCheckbox();
            updateSelectOptions();
        });
    });

    // Cập nhật trạng thái của checkbox "Chọn tất cả"
    function updateSelectAllCheckbox() {
        var allChecked = Array.from(rowCheckboxes).every(function(checkbox) {
            return checkbox.checked;
        });
        selectAllCheckbox.checked = allChecked;
    }

    // Thay đổi màu nền của hàng dựa trên trạng thái checkbox
    function toggleHighlight(checkbox) {
        var row = checkbox.closest('tr');
        if (checkbox.checked) {
            row.classList.add('highlight');
        } else {
            row.classList.remove('highlight');
        }
    }

    // Cập nhật tùy chọn của thẻ select từ danh sách checkbox
    function updateSelectOptions() {
        var selectedValues = new Set(); // Dùng Set để loại bỏ giá trị trùng lặp
        rowCheckboxes.forEach(function(checkbox) {
            if (checkbox.checked) {
                var value = checkbox.dataset.value; // Lấy giá trị từ thuộc tính data-value của checkbox
                if (value) {
                    selectedValues.add(value);
                }
            }
        });

        // Xóa tất cả các tùy chọn hiện tại trong thẻ select
        selectElement.innerHTML = '<option value="">Chọn một tùy chọn</option>';

        // Thêm các tùy chọn mới vào thẻ select
        selectedValues.forEach(function(value) {
            var option = document.createElement('option');
            option.value = value;
            option.textContent = value;
            selectElement.appendChild(option);
        });
    }
});