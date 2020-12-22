<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Thêm mới</title>

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
							<c:choose>
								<c:when test="${param.mess eq 'success' }">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-success">Success</span>
										Them thanh cong
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
								<c:when test="${param.mess eq 'username' }">
									<div
										class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
										<span class="badge badge-pill badge-danger">error</span> Tai
										khoan ton tai
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
								<c:when test="${param.mess eq 'password' }">
									<div
										class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
										<span class="badge badge-pill badge-danger">error</span>
										Password khong dung
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
							</c:choose>
							<div class="card-header">
								<strong>Thêm người dùng</strong>
							</div>
							<div class="card-body card-block">
								<form action="/QuanLyDiem/giaovien/addSV" method="post"
									class="form-horizontal" enctype="multipart/form-data">
									<div class="row form-group">
										<div class="col col-md-3">
											<label for="text-input" class=" form-control-label">Họ tên</label>
										</div>
										<div class="col-12 col-md-9">
											<input type="text" id="text-input" name="hoten"
												placeholder="Fullname" class="form-control" required>
											<small class="form-text text-muted">This is a help
												text</small>
										</div>
									</div>
									<div class="row form-group">
										<div class="col col-md-3">
											<label for="text-input" class=" form-control-label">Mã Sinh Viên</label>
										</div>
										<div class="col-12 col-md-9">
											<input type="text" id="text-input" name="msv"
												placeholder="Username" class="form-control" required pattern="^[a-zA-Z0-9]{5,15}$">
											<small class="form-text text-muted">This is a help
												text</small>
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
											<label for="select" class=" form-control-label">Lớp</label>
										</div>
										<div class="col-12 col-md-9">
											<select name="lop" id="select" class="form-control" required>
											<c:forEach items="${lopList }" var="lop">
												<option value="${lop.id_lop }">${lop.tenlop }</option>
											</c:forEach>
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
													value="Nam" class="form-check-input">Nam
												</label> <label for="inline-radio2" class="form-check-label ">
													<input type="radio" id="inline-radio2" name="gioitinh"
													value="Nữ" class="form-check-input">Nữ
												</label> <label for="inline-radio3" class="form-check-label ">
													<input type="radio" id="inline-radio3" name="gioitinh"
													value="Khác" class="form-check-input">Khác
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
												placeholder="Address" class="form-control" required>
											<small class="form-text text-muted">This is a help
												text</small>
										</div>
									</div>
									<div class="row form-group">
										<div class="col col-md-3">
											<label for="text-input" class=" form-control-label">Ngày sinh</label>
										</div>
										<div class="col-12 col-md-9">
											<input type="text" id="text-input" name="ngaysinh"
												placeholder="dd/mm/yyyy" class="form-control" required>
											<small class="form-text text-muted">This is a help
												text</small>
										</div>
									</div>
									<div class="row form-group">
										<div class="col col-md-3">
											<label for="text-input" class=" form-control-label">Số điện thoại</label>
										</div>
										<div class="col-12 col-md-9">
											<input type="text" id="text-input" name="sdt"
												placeholder="Phone" class="form-control" required> <small
												class="form-text text-muted">This is a help text</small>
										</div>
									</div>
									<div class="row form-group">
										<div class="col col-md-3">
											<label for="file-input" class=" form-control-label">Ảnh</label>
										</div>
										<div class="col-12 col-md-9">
											<input type="file" id="file-input" name="img"
												class="form-control-file">
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
