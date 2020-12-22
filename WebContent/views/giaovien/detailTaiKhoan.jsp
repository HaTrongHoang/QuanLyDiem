<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Thông tin người dùng</title>

<jsp:include page="/views/giaovien/common/linkCss.jsp"></jsp:include>
</head>

<body>

	<!-- HEADER -->
	<jsp:include page="/views/giaovien/common/header.jsp"></jsp:include>
	<!-- MAIN CONTENT-->
	<div class="main-content">
		<div class="section__content section__content--p30">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-11">
						<div class="card">
							<div class="card-header">
								<strong>Thông tin tài khoản</strong>
							</div>
							<div class="card-body card-block">
								<div class="row form-group">
									<div class="col col-md-3">
										<label for="text-input" class=" form-control-label">Ảnh</label>
									</div>
									<div class="col-12 col-md-3">
										<img
											src="${pageContext.request.contextPath }/upload/giaovien/${taikhoan.img }"
											name="img" alt="..." class="img-thumbnail">
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-3">
										<label for="text-input" class=" form-control-label">Họ
											Tên</label>
									</div>
									<div class="col-12 col-md-9">
										<label for="text-input" class=" form-control-label">${taikhoan.hoten }</label>
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-3">
										<label for="text-input" class=" form-control-label">Mã
											Giáo Viên</label>
									</div>
									<div class="col-12 col-md-9">
										<label for="text-input" class=" form-control-label">${taikhoan.mgv }</label>
									</div>
								</div>

								<div class="row form-group">
									<div class="col col-md-3">
										<label for="select" class=" form-control-label">Chức
											vụ</label>
									</div>
									<div class="col-12 col-md-9">
										
										<c:if test="${taikhoan.role eq 0 }">
											<label for="text-input" class=" form-control-label">Quản Trị</label>
										</c:if>
										<c:if test="${taikhoan.role eq 1 }">
											<label for="text-input" class=" form-control-label">Giáo Viên</label>
										</c:if>
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-3">
										<label class=" form-control-label">Giới tính</label>
									</div>
									<div class="col col-md-9">
										<label for="text-input" class=" form-control-label">${taikhoan.gioitinh }</label>
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-3">
										<label for="text-input" class=" form-control-label">Địa
											chỉ</label>
									</div>
									<div class="col-12 col-md-9">
										<label for="text-input" class=" form-control-label">${taikhoan.diachi }</label>
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-3">
										<label for="text-input" class=" form-control-label">Ngày
											sinh</label>
									</div>
									<div class="col-12 col-md-9">
										<label for="text-input" class=" form-control-label">${taikhoan.ngaysinh }</label>
									</div>
								</div>
								<div class="row form-group">
									<div class="col col-md-3">
										<label for="text-input" class=" form-control-label">Số
											điện thoại</label>
									</div>
									<div class="col-12 col-md-9">
										<label for="text-input" class=" form-control-label">${taikhoan.sdt }</label>
									</div>
								</div>
									<div class="card-footer">
										<button type="button" class="btn btn-primary btn-sm">
											<a style="color: #212529"
												href="/QuanLyDiem/giaovien/taikhoan/doipass">
												<i class="zmdi zmdi-edit"></i>Đổi mật khẩu
											</a>
										</button>
									</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>

	</div>

	<jsp:include page="/views/giaovien/common/scriptJS.jsp"></jsp:include>
</body>

</html>
<!-- end document-->
