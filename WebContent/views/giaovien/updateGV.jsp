<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >

<head>
<!-- Required meta tags-->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Sửa thông tin</title>

<jsp:include page="/views/giaovien/common/linkCss.jsp"></jsp:include>
</head>

<body>
	<div class="page-wrapper">
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
									<strong>Sửa thông tin</strong>
								</div>
								<div class="card-body card-block">
									<form
										action="/QuanLyDiem/giaovien/updateGV?id_giaovien=${gvUpdate.id_giaovien }"
										method="post" class="form-horizontal"
										enctype="multipart/form-data"accept-charset="UTF-8">
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="text-input" class=" form-control-label">Ảnh</label>
											</div>
											<div class="col-12 col-md-3">
												<img
													src="${pageContext.request.contextPath }/upload/giaovien/${gvUpdate.img }"
													name="img" alt="..." class="img-thumbnail"> <input
													type="file" id="file-input" name="img"
													class="form-control-file"
													value="E:/Java/QuangAnhCar/WebContent/upload/user/download.jpg">
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="text-input" class=" form-control-label">Họ tên</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="text" id="text-input" name="hoten"
													value="${gvUpdate.hoten }" placeholder="Fullname"
													class="form-control" required > <small
													class="form-text text-muted">This is a help text</small>
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="text-input" class=" form-control-label">Mã Giáo Viên</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="text" id="text-input" name="mgv"
													placeholder="Username" class="form-control"
													value="${gvUpdate.mgv }"> <small
													class="form-text text-muted">This is a help text</small>
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="email-input" class=" form-control-label">Mật khẩu</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="password" id="password-input" name="password"
													placeholder="Password" class="form-control"
													value="${gvUpdate.password }" disabled> <small
													class="form-text text-muted">This is a help text</small>
											</div>
										</div>
										<!--  <div class="row form-group">
											<div class="col col-md-3">
												<label for="disabled-input" class=" form-control-label">Disabled
													Input</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="text" id="disabled-input" name="disabled-input"
													placeholder="Disabled" disabled="" class="form-control">
											</div>
										</div>-->
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Chức vụ</label>
											</div>
											<div class="col-12 col-md-9">
												<select name="role" id="select" class="form-control" required>
													<option value="0"
														<c:if test="${gvUpdate.role eq '0' }">selected</c:if>>Quản Trị</option>
													<option value="1"
														<c:if test="${gvUpdate.role eq '1' }">selected</c:if>>Giáo Viên</option>
												</select>
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label class=" form-control-label">Giới tính</label>
											</div>
											<div class="col col-md-9">
												<div class="form-check-inline form-check">
													<label for="inline-radio1" class="form-check-label ">
														<input type="radio" id="inline-radio1" name="gioitinh"
														value="Nam" class="form-check-input"
														<c:if test="${gvUpdate.gioitinh eq 'Nam' }">checked</c:if>>Nam
													</label> <label for="inline-radio2" class="form-check-label ">
														<input type="radio" id="inline-radio2" name="gioitinh"
														value="Nữ" class="form-check-input"
														<c:if test="${gvUpdate.gioitinh eq 'Nữ' }">checked</c:if>>Nữ
													</label> <label for="inline-radio3" class="form-check-label ">
														<input type="radio" id="inline-radio3" name="gioitinh"
														value="Khác" class="form-check-input"
														<c:if test="${gvUpdate.gioitinh eq 'Khác' }">checked</c:if>>Khác
													</label>
												</div>
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="text-input" class=" form-control-label">Địa chỉ</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="text" id="text-input" name="diachi"
													placeholder="Address" class="form-control"
													value="${gvUpdate.diachi }"required> <small
													class="form-text text-muted">This is a help text</small>
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="text-input" class=" form-control-label">Ngày sinh</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="text" id="text-input" name="ngaysinh"
													placeholder="dd/mm/yyyy" class="form-control"
													value="${gvUpdate.ngaysinh }"> <small
													class="form-text text-muted">This is a help text</small>
											</div>
										</div>
										<div class="row form-group">
											<div class="col col-md-3">
												<label for="text-input" class=" form-control-label">Số điện thoại</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="text" id="text-input" name="sdt"
													placeholder="Phone" class="form-control"
													value="${gvUpdate.sdt }"required> <small
													class="form-text text-muted">This is a help text</small>
											</div>
										</div>
										<!--<div class="row form-group">
											<div class="col col-md-3">
												<label for="file-multiple-input" class=" form-control-label">Multiple
													File input</label>
											</div>
											<div class="col-12 col-md-9">
												<input type="file" id="file-multiple-input"
													name="file-multiple-input" multiple=""
													class="form-control-file">
											</div>
										</div>-->

										<div class="card-footer">
											<button type="submit" class="btn btn-primary btn-sm">
												<i class="fa fa-dot-circle-o"></i> Submit
											</button>
											<button type="reset" class="btn btn-danger btn-sm">
												<i class="fa fa-ban"></i> Reset
											</button>
										</div>
									</form>
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
