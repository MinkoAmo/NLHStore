package com.nlhstore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    CATEGORY_NOT_EXISTED(1001, HttpStatus.NOT_FOUND, "Danh mục không tồn tại"),
    USER_NOT_EXISTED(1002, HttpStatus.NOT_FOUND, "Tài khoản không tồn tại"),
    BRAND_NOT_EXISTED(1003, HttpStatus.NOT_FOUND, "Thương hiệu không tồn tại"),
    PRODUCT_NOT_EXISTED(1004, HttpStatus.NOT_FOUND, "Sản phẩm không tồn tại"),
    ROLE_NOT_EXISTED(1005, HttpStatus.NOT_FOUND, "Role không tồn tại"),
    PERMISSION_NOT_EXISTED(1006, HttpStatus.NOT_FOUND, "Quyền không tồn tại"),

    CODE_CATEGORY_EXISTED(1101, HttpStatus.BAD_REQUEST, "Mã danh mục đã tồn tại"),
    NAME_CATEGORY_EXISTED(1102, HttpStatus.BAD_REQUEST, "Tên danh mục đã tồn tại"),
    USERNAME_EXISTED(1103, HttpStatus.BAD_REQUEST, "Username đã tồn tại"),
    EMAIL_EXISTED(1104, HttpStatus.BAD_REQUEST, "Email đã tồn tại"),
    CODE_BRAND_EXISTED(1105, HttpStatus.BAD_REQUEST, "Mã thương hiệu đã tồn tại"),
    NAME_BRAND_EXISTED(1106, HttpStatus.BAD_REQUEST, "Tên thương hiệu đã tồn tại"),
    CODE_PRODUCT_EXISTED(1107, HttpStatus.BAD_REQUEST, "Mã sản phẩm đã tồn tại"),
    NAME_PRODUCT_EXISTED(1108, HttpStatus.BAD_REQUEST, "Tên sản phẩm đã tồn tại"),
    CODE_PERMISSION_EXISTED(1109, HttpStatus.BAD_REQUEST, "Mã quyền đã tồn tại"),

    CODE_CATEGORY_INVALID(1201, HttpStatus.BAD_REQUEST, "Mã danh mục phải trong khoảng từ 1-50 kí tự"),
    NAME_CATEGORY_INVALID(1202, HttpStatus.BAD_REQUEST, "Tên danh mục phải trong khoảng từ 1-50 kí tự"),
    USERNAME_INVALID(1203, HttpStatus.BAD_REQUEST, "Username phải trong khoảng từ 4-50 kí tự"),
    PASSWORD_INVALID(1204, HttpStatus.BAD_REQUEST, "Password phải từ 8-20 ký tự, gồm chữ hoa, chữ thường, số và ký tự đặc biệt"),
    EMAIL_INVALID(1205, HttpStatus.BAD_REQUEST, "Định dạng email không đúng"),
    CODE_BRAND_INVALID(1206, HttpStatus.BAD_REQUEST, "Mã thương hiệu phải trong khoảng từ 1-50 kí tự"),
    NAME_BRAND_INVALID(1207, HttpStatus.BAD_REQUEST, "Tên thương hiệu phải trong khoảng từ 1-50 kí tự"),
    CODE_PRODUCT_INVALID(1206, HttpStatus.BAD_REQUEST, "Mã sản phẩm phải trong khoảng từ 1-50 kí tự"),
    NAME_PRODUCT_INVALID(1207, HttpStatus.BAD_REQUEST, "Tên sản phẩm phải trong khoảng từ 1-50 kí tự"),
    CODE_PERMISSION_INVALID(1208, HttpStatus.BAD_REQUEST, "Mã quyền phải trong khoảng từ 1-50 kí tự"),

    CODE_CATEGORY_BLANK(1301, HttpStatus.BAD_REQUEST, "Mã danh mục không được để trống"),
    NAME_CATEGORY_BLANK(1302, HttpStatus.BAD_REQUEST, "Tên danh mục không được để trống"),
    CODE_BRAND_BLANK(1303, HttpStatus.BAD_REQUEST, "Mã thương hiệu không được để trống"),
    NAME_BRAND_BLANK(1304, HttpStatus.BAD_REQUEST, "Tên thương hiệu không được để trống"),
    USERNAME_BLANK(1305, HttpStatus.BAD_REQUEST, "Username không được để trống"),
    PASSWORD_BLANK(1306, HttpStatus.BAD_REQUEST, "Password không được để trống"),
    CODE_PRODUCT_BLANK(1307, HttpStatus.BAD_REQUEST, "Mã sản phẩm không được để trống"),
    NAME_PRODUCT_BLANK(1308, HttpStatus.BAD_REQUEST, "Tên sản phẩm không được để trống"),
    PRICE_PRODUCT_BLANK(1309, HttpStatus.BAD_REQUEST, "Đơn giá không được để trống"),
    QUANTITY_PRODUCT_BLANK(1310, HttpStatus.BAD_REQUEST, "Số lượng không được để trống"),
    STATUS_PRODUCT_BLANK(1311, HttpStatus.BAD_REQUEST, "Trạng thái sản phẩm không được để trống"),
    CODE_PERMISSION_BLANK(1303, HttpStatus.BAD_REQUEST, "Mã quyền không được để trống"),

    GENERATE_TOKEN_FAIL(9995, HttpStatus.INTERNAL_SERVER_ERROR, "Không thể tạo token"),
    UNAUTHENTICATED(99976, HttpStatus.UNAUTHORIZED, "Không xác thực tài khoản"),
    UNAUTHORIZED(9997, HttpStatus.FORBIDDEN, "Bạn không có quyền thao tác"),
    KEY_INPUT_VALID(9998, HttpStatus.BAD_REQUEST, "Trường Error không hợp lệ"),
    UNCATEGORIZED_EXCEPTION(9999, HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi chưa phân loại");

    private int code;
    private HttpStatus httpStatusCode;
    private String message;
}
