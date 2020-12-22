<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="author" content="Hau Nguyen">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Tables</title>

<jsp:include page="/views/giaovien/common/diem.jsp"></jsp:include>

</head>

<body>
	</script>
	<div class="page-wrapper">
		<!-- header -->
		<jsp:include page="/views/sinhvien/common/headerDiem.jsp"></jsp:include>
		<!--end header -->
		<!-- MAIN CONTENT-->
		<div class="main-content">
			<div class="section__content section__content--p30">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-12">
							<c:choose>
								<c:when test="${param.mess eq 'exist' }">
									<div
										class="sufee-alert alert with-close alert-danger alert-dismissible fade show">
										<span class="badge badge-pill badge-danger">Erorr</span> Danh
										muc ton tai
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
								<c:when test="${param.mess eq 'addsuccess' }">
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
								<c:when test="${param.mess eq 'delete' }">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-success">Success</span>
										Xoa thanh cong
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
								<c:when test="${param.mess eq 'update' }">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-success">Success</span>
										Sua thanh cong
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
							</c:choose>
							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Chọn
								</h3>
								<div class="card-body card-block">
									<form action="/QuanLyDiem/sinhvien/diem" method="post"
										class="form-horizontal">
										<div class="table-data__tool">
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Kỳ
													học</label> <select name="hocky" id="select" class="form-control"
													required>
													<c:forEach items="${hockyList }" var="hockyList">
														<option value="${hockyList.id_hocky }">${hockyList.tenhocky }</option>
													</c:forEach>
												</select>
											</div>

										</div>
										<div class="card-footer">
											<button type="submit" class="btn btn-primary btn-sm">
												<i class="fa fa-dot-circle-o"></i> Submit
											</button>
										</div>
									</form>
								</div>
							</div>
							<!-- USER DATA-->

							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Sinh viên :
									${sessionScope.loginSinhVien.hoten}-${sessionScope.loginSinhVien.lop.tenlop}
								</h3>
								<div class="card-body card-block">
									<div class="table-responsive table-data">

										<table class="table">
											<thead>
												<tr>
													<td>Msv</td>
													<td style="width: 300px">Học phần</td>
													<td>Chuyên cần</td>
													<td>Kiểm tra GK</td>
													<td>Kết thúc</td>
													<td>TKHP</td>
													<td>Đánh giá</td>
													<td>Điểm chữ</td>
													<td>Ghi chú</td>
												</tr>
											</thead>
											<tbody>
												<div class="table-data__tool">
													<c:forEach items="${diem }" var="diem">
														<tr>
															<input name="id_diem" value="${diem.id_diem }"
																type="hidden">
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.sinhvien.msv }</h6>
																</div>
															</td>
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.mon.tenmon }</h6>
																</div>
															</td>
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.chuyencan}</h6>
																</div>
															</td>
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.kiemtragk}</h6>
																</div>
															</td>

															<td>
																<div class="table-data__info">
																	<c:choose>
																		<c:when test="${empty diem.ketthuc2 }">
																			<h6 style="color: #000">${diem.ketthuc1 }</h6>
																		</c:when>
																		<c:otherwise>
																			<h6 style="color: #000">${diem.ketthuc1 }|${diem.ketthuc2}</h6>
																		</c:otherwise>
																	</c:choose>

																</div>
															</td>
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.tongket}</h6>
																</div>
															</td>
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.danhgia}</h6>
																</div>
															</td>
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.diemchu}</h6>
																</div>
															</td>
															<td>
																<div class="table-data__info">
																	<h6 style="color: #000">${diem.ghichu}</h6>
																</div>
															</td>
														</tr>
													</c:forEach>
												</div>
											</tbody>
										</table>
									</div>
								</div>

							</div>
							<!-- END USER DATA-->
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
