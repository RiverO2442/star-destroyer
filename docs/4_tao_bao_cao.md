# IV. Tạo báo cáo

## Thông tin chung

## Thông tin nghiệp vụ

### Mô tả luồng nghiệp vụ

- Tên nghiệp vụ: Xuất báo cáo
- Mô tả: Người dùng xuất báo cáo về khoảng số dư, dư nợ và thông tin chi tiêu.
- Tác nhân:
    - Người dùng
    - Hệ thống
- Điều kiện kích hoạt: Người dùng chọn xuất báo cáo
- Điều kiện tiên quyết:
- Điều kiện thành công:

| STT | Tác nhân   | Luồng cơ bản                      | Luồng thay thế |
|-----|------------|-----------------------------------|----------------|
| 1   | Người dùng | Chọn chức năng xuất báo cáo       |                |
| 2   | Người dùng | Chọn ngày bắt đầu & ngày kết thúc |                |
| 3   | Người dùng | Bấm chọn xuất                     |                |
| 3   | Hệ thống   | Tạo báo cáo                       |                |

### Danh sách thuộc tính

| Tên thuộc tính    | Mô tả | Kiểu dữ liệu | Ràng buộc        | Duy nhất | Yêu cầu |
|-------------------|-------|--------------|------------------|----------|---------|
| Ngày bắt đầu      |       | Instant      | <= ngày kết thúc |          |         |
| Ngày kết thúc     |       | Instant      | >= ngày bắt đầu  |          |         |
| Danh sách hóa đơn |       | List         |                  |          |         |
| Danh sách buổi ăn |       | List         |                  |          |         |
| Tổng số dư        |       | Decimal      |                  |          |         |
| Tổng dư nợ        |       | Decimal      |                  |          |         |

### Các ràng buộc liên quan

| STT | Ràng buộc |
|-----|-----------|
| 1   | ---       |
