<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.http.HttpSession"%>
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
								<c:when test="${param.mess eq 'success' }">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-success">Success</span>
										Cập nhật thành công
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
							</c:choose>
							<c:choose>
								<c:when test="${not empty sessionScope.err}">
									<div
										class="sufee-alert alert with-close alert-success alert-dismissible fade show">
										<span class="badge badge-pill badge-danger">Erorr</span> Kiểm
										tra lại : ${sessionScope.err }
										<button type="button" class="close" data-dismiss="alert"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
								</c:when>
							</c:choose>
							<%
								HttpSession sessionErr = request.getSession();
							sessionErr.removeAttribute("err");
							%>
							<div class="user-data m-b-30">

								<div class="card-body card-block">
									<h5 class="title-3 m-b-30">
										<i class="zmdi zmdi-account-calendar"></i>Chọn
									</h5>
									<form action="/QuanLyDiem/giaovien/diem" method="post"
										class="form-horizontal">
										<div class="table-data__tool">
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Lớp
													Học</label> <select name="lop" id="select" class="form-control"
													required>
													<c:forEach items="${lopList }" var="phanMonLop">
														<option value="${phanMonLop.lop.id_lop }">${phanMonLop.lop.tenlop }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Môn
													học</label> <select name="mon" id="select" class="form-control"
													required>
													<c:forEach items="${monList }" var="phanMon">
														<option value="${phanMon.mon.id_mon }">${phanMon.mon.mamon}-${phanMon.mon.tenmon }</option>
													</c:forEach>
												</select>
											</div>
											<div class="col col-md-3">
												<label for="select" class=" form-control-label">Học
													Kỳ</label> <select name="hocky" id="select" class="form-control"
													required>
													<c:forEach items="${hockyList }" var="hockyList">
														<c:if test="${hockyList.status_hk eq 1 }">
															<option value="${hockyList.id_hocky }">${hockyList.tenhocky}</option>
														</c:if>
													</c:forEach>
												</select>
											</div>
											<div class="col col-md-3"></div>
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
									<i class="zmdi zmdi-account-calendar"></i>Danh sách sinh viên
									lớp ${tenlop.tenlop } - Môn ${tenmon.mamon}-${tenmon.tenmon }
								</h3>
								<div class="card-body card-block">
									<form action="/QuanLyDiem/giaovien/updatediem" method="post"
										class="form-horizontal">
										<div class="table-responsive table-data">
											<table class="table table-striped">
												<thead>
													<tr style="background: #f5f5f5">
														<td>Msv</td>
														<td style="width: 230px">Tên sinh vien</td>
														<td style="width: 20px">Cấm thi</td>
														<td style="width: 50px">Chuyên cần</td>
														<td style="width: 50px">Kiểm tra GK</td>
														<td style="width: 50px">Kết thúc lần 1</td>
														<td style="width: 50px">Kết thúc lần 2</td>
														<td style="width: 50px">TKHP</td>
														<td style="width: 70px">Đánh giá</td>
														<td style="width: 50px">Điểm chữ</td>
														<td style="width: 200px">Ghi chú</td>
														<td style="width: 50px">Update</td>
													</tr>
												</thead>
												<tbody>
													<input name="id_lop" value="${id_lop }" type="hidden">
													<input name="id_mon" value="${id_mon }" type="hidden">
													<input name="id_hocky" value="${id_hocky }" type="hidden">
													<c:forEach items="${diemList }" var="diem">
														<tr>
															<input name="id_diem" value="${diem.id_diem }"
																type="hidden">
															<input name="status" value="${diem.status }"
																type="hidden">
															<td>${diem.sinhvien.msv }</td>
															<td><input type="text" id="text-input" name="hoten"
																readonly value="${diem.sinhvien.hoten }"
																style="border: none; width: 230px; background: none;"></td>
															<td><input type="checkbox" name="camthi"
																value="${diem.id_diem }"
																<c:if test="${diem.danhgia eq 'HOCLAI' }">checked</c:if>></td>
															<td><input type="text" name="chuyencan"
																value="${diem.chuyencan }" style="width: 50px"
																pattern="([0-9]{1}|[1]{1}[0]{1}|[F])"></td>
															<td><input type="text" id="text-input"
																name="kiemtragk" value="${diem.kiemtragk }"
																style="width: 50px" pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
															<td><input type="text" id="text-input"
																name="ketthuc1" value="${diem.ketthuc1 }"
																style="width: 50px" pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
															<td><input type="text" id="text-input"
																name="ketthuc2" value="${diem.ketthuc2 }"
																style="width: 50px" pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
															<td><input type="text" name="tongket"
																value="${diem.tongket }" style="width: 50px"
																pattern="([0-9]{1}|[1]{1}[0]{1})" readonly="readonly"></td>
															<td>${diem.danhgia}</td>
															<td>${diem.diemchu}</td>
															<td><input type="text" id="text-input" name="ghichu"
																value="${diem.ghichu }"></td>
															<td>
																<div class="table-data-feature">

																	<button type="button" class="item" data-toggle="modal"
																		data-target="#largeModal${diem.id_diem }">

																		<i class="zmdi zmdi-edit"></i>

																	</button>
																</div>
															</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div class="table-data__tool card-footer">
											<div class="table-data__tool-left">
												<div class="">
													<button type="submit" class="btn btn-primary btn-sm">
														<i class="fa fa-dot-circle-o"></i> Cập nhật
													</button>
												</div>
											</div>
											<div class="table-data__tool-right">
												<div class="input-group-btn">
													<div class="btn-group">
														<button type="button" data-toggle="dropdown"
															aria-haspopup="true" aria-expanded="false"
															class="dropdown-toggle btn btn-primary">Xuất</button>
														<div tabindex="-1" aria-hidden="true" role="menu"
															class="dropdown-menu">
															<button type="button" tabindex="0" class="dropdown-item">
																<a
																	href="/QuanLyDiem/giaovien/export?id_lop=${tenlop.id_lop }&id_mon=${id_mon}&id_hocky=${id_hocky}">
																	<i class="fa fa-dot-circle-o"></i> Tất cả
																</a>
															</button>
															<button type="button" tabindex="0" class="dropdown-item">
																<a
																	href="/QuanLyDiem/giaovien/exportThiLaiLop?id_lop=${tenlop.id_lop }&id_mon=${id_mon}">
																	<i class="fa fa-dot-circle-o"></i> Thi lại
																</a>
															</button>
														</div>
													</div>
												</div>
											</div>

										</div>
									</form>
									<div class="table-data__tool-right">
										<form class="form-header"
											action="/QuanLyDiem/giaovien/import?id_mon=${id_mon }&id_lop=${id_lop}&id_hocky=${id_hocky}"
											method="post" enctype="multipart/form-data">
											<div class="col col-md-5">
												<div class="input-group">
													<select name="diem" id="selectLg"
														class="form-control form-control" style="border: none;">
														<option value="1">Điểm thành phần</option>
														<option value="2">Điểm lần 1</option>
														<option value="3">Điểm lần 2</option>
													</select> <input type="file" id="input3-group3" name="file"
														placeholder=".." class="form-control"
														style="border: none;">
													<div class="input-group-btn">
														<div class="btn-group">
															<button type="submit" class="btn btn-primary">Nhập</button>

														</div>
													</div>
												</div>
												</button>
											</div>
										</form>
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
	<c:forEach items="${diemList }" var="diem">
		<form action="/QuanLyDiem/giaovien/yeucauUpdate" method="post">
			<div class="modal fade" id="largeModal${diem.id_diem }" tabindex="-1"
				role="dialog" aria-labelledby="largeModalLabel" aria-hidden="true">
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
										pattern="([0-9]{1}|[1]{1}[0]{1}|[F])|([0-9]{1}[.][0-9]{1})">
								</div>
								<div class="col col-md-2">
									<label for="text-input" class=" form-control-label">Thi
										lần 2</label>
								</div>
								<div class="col-12 col-md-2">
									<input type="text" name="ketthuc2Update"
										value="${diem.ketthuc2 }" style="width: 50px"
										pattern="([0-9]{1}|[1]{1}[0]{1}|[F])|([0-9]{1}[.][0-9]{1})">
								</div>
								<div class="col col-md-2">
									<label for="text-input" class=" form-control-label">TKHP</label>
								</div>
								<div class="col-12 col-md-2">
									<input type="text" name="tongketUpdate"
										value="${diem.tongket }" style="width: 50px"
										readonly="readonly">
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
	<jsp:include page="/views/giaovien/common/scriptJS.jsp"></jsp:include>
</body>

</html>
<!-- end document-->
