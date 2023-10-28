# I. Tạo hóa đơn

## Thông tin chung

Một user mua hàng, tạo đơn hàng.

## Thông tin nghiệp vụ

### Mô tả luồng nghiệp vụ

| STT | Tác nhân   | Luồng cơ bản                                                                   | Luồng thay thế |
|-----|------------|--------------------------------------------------------------------------------|----------------|
| 1   | Người dùng | Tạo đơn hàng                                                                   |                |
| 2   | Người dùng | User tạo đơn hàng nhập thông tin người mua, người trả tiền, thông tin đơn hàng |                |
| 3   | Hệ thống   | Lưu đơn hàng và thực hiện tính toán số dư, dư nợ                               |

### Danh sách thuộc tính

| Tên thuộc tính      | Mô tả                                        | Kiểu dữ liệu | Ràng buộc                                   | Duy nhất | Yêu cầu |
|---------------------|----------------------------------------------|----------|---------------------------------------------|----------|---------|
| Thông tin người mua | Thông tin của người đã trả tiền cho đơn hàng | String[] | không được rỗng, không được có id trùng lập | không    | x       |

### Các ràng buộc liên quan

| STT | Ràng buộc |
|-----|-----------|
| 1   | ---       |