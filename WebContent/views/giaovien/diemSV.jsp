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
		<jsp:include page="/views/giaovien/common/headerDiem.jsp"></jsp:include>
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
							<!-- USER DATA-->

							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Sinh viên :${sv.hoten }
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
													<td></td>
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
																		<c:when test="${empty diem.ketthuc2  }">
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
															<c:if test="${sessionScope.loginGiaoVien.role eq '0' }">
																<td>
																	<div class="table-data-feature">

																		<button type="button" class="item" data-toggle="modal"
																			data-target="#largeModal${diem.id_diem }">

																			<i class="zmdi zmdi-edit"></i>

																		</button>
																	</div>
																</td>
															</c:if>
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
	<c:if test="${sessionScope.loginGiaoVien.role eq '0' }">
		<c:forEach items="${diem }" var="diem">
			<form action="/QuanLyDiem/giaovien/lop/sinhvien/diem/update"
				method="post">
				<div class="modal fade" id="largeModal${diem.id_diem }"
					tabindex="-1" role="dialog" aria-labelledby="largeModalLabel"
					aria-hidden="true">
					<div class="modal-dialog modal-lg" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="largeModalLabel">Sửa điểm:
									${diem.sinhvien.hoten }</h5>
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
							</div>
							<input name="id_lop" value="${id_lop }" type="hidden"> <input
								name="id_mon" value="${id_mon }" type="hidden"> <input
								name="id_hocky" value="${id_hocky }" type="hidden">
							<div class="modal-body">
								<input name="id_diemUpdate" value="${diem.id_diem }"
									type="hidden"> <input name="statusUpdate"
									value="${diem.status }" type="hidden">
								<div class="row form-group">
									<div class="col col-md-2">
										<label for="text-input" class=" form-control-label">Chuyên
											cần</label>
									</div>
									<div class="col-12 col-md-2">
										<input type="text" name="chuyencanUpdate"
											value="${diem.chuyencan }" style="width: 50px"
											pattern="([0-9]{1}|[1]{1}[0]{1}|[F])">
									</div>
									<div class="col col-md-2">
										<label for="text-input" class=" form-control-label">Kiểm
											tra GK</label>
									</div>
									<div class="col-12 col-md-2">
										<input type="text" name="kiemtragkUpdate"
											value="${diem.kiemtragk }" style="width: 50px"
											pattern="([0-9]{1}|[1]{1}[0]{1}|[F])">
									</div>
									<div class="col col-md-2">
										<label for="text-input" class=" form-control-label">Thi
											lần 1</label>
									</div>
									<div class="col-12 col-md-2">
										<input type="text" name="ketthuc1Update"
											value="${diem.ketthuc1 }" style="width: 50px"
											pattern="([0-9]{1}|[1]{1}[0]{1}|[F])">
									</div>
									<div class="col col-md-2">
										<label for="text-input" class=" form-control-label">Thi
											lần 2</label>
									</div>
									<div class="col-12 col-md-2">
										<input type="text" name="ketthuc2Update"
											value="${diem.ketthuc2 }" style="width: 50px"
											pattern="([0-9]{1}|[1]{1}[0]{1}|[F])">
									</div>
									<div class="col col-md-2">
										<label for="text-input" class=" form-control-label">TKHP</label>
									</div>
									<div class="col-12 col-md-2">
										<input type="text" name="tongketUpdate"
											value="${diem.tongket }" style="width: 50px"
											pattern="([0-9]{1}|[1]{1}[0]{1}|[F])" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">Gửi</button>
							</div>

						</div>
					</div>
				</div>
			</form>
		</c:forEach>
	</c:if>
	<jsp:include page="/views/giaovien/common/scriptJS.jsp"></jsp:include>
</body>

</html>
<!-- end document-->
