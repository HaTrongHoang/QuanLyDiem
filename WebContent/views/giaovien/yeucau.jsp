<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="au theme template">
<meta name="author" content="Hau Nguyen">
<meta name="keywords" content="au theme template">

<!-- Title Page-->
<title>Quản lý người dùng</title>

<jsp:include page="/views/giaovien/common/linkCss.jsp"></jsp:include>

</head>

<body>
	</script>
	<div class="page-wrapper">
		<!-- header -->
		<jsp:include page="/views/giaovien/common/header.jsp"></jsp:include>
		<!--end header -->
		<!-- MAIN CONTENT-->
		<div class="main-content">
			<div class="section__content section__content--p30">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-12">
							<c:choose>
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
							</c:choose>
							<!-- USER DATA-->
							<div class="user-data m-b-30">
								<h3 class="title-3 m-b-30">
									<i class="zmdi zmdi-account-calendar"></i>Danh sách yêu cầu
								</h3>
								<div class="table-data__tool">
									<div class="table-data__tool-right">

										<form class="form-header"
											action="/QuanLyDiem/giaovien/searchlop" method="POST"
											accept-charset="UTF-8">
											<input class="au-input au-input--xl" type="text" name="key"
												placeholder="Search ..." />
											<button class="au-btn--submit" type="submit">
												<i class="zmdi zmdi-search"></i>
											</button>
										</form>
									</div>
								</div>
								<div class="table-responsive table-data">
									<table class="table">
										<thead>
											<tr>
												<td>Msv</td>
												<td style="width: 70px">Tên sinh vien</td>
												<td style="width: 50px">Chuyên cần</td>
												<td style="width: 50px">Kiểm tra GK</td>
												<td style="width: 50px">Kết thúc lần 1</td>
												<td style="width: 50px">Kết thúc lần 2</td>
												<td style="width: 50px">TKHP</td>
												<td style="width: 50px">Đánh giá</td>
												<td style="width: 50px">Điểm chữ</td>
												<td style="width: 70px">Ghi chú</td>
												<td style="width: 70px">GV yêu cầu</td>
												<td style="width: 70px">Học phần</td>
												<td></td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${yeucauList }" var="yeucauList">
												<input name="id_diem" value="${diem.id_diem }" type="hidden">
												<input name="status" value="${diem.status }" type="hidden">

												<tr>
													<td><input type="text" id="text-input" name="ghichu"
														readonly value="${yeucauList.diem.sinhvien.msv }"
														style="border: none; width: 120px"></td>
													<td><input type="text" id="text-input" name="ghichu"
														readonly value="${yeucauList.diem.sinhvien.hoten }"
														style="border: none; width: 200px"></td>
													<td><input type="text" name="chuyencan"
														value="${yeucauList.diem.chuyencan}->${yeucauList.chuyencan }"
														style="width: 50px" readonly
														pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
													<td><input type="text" id="text-input"
														name="kiemtragk"
														value="${yeucauList.diem.kiemtragk}->${yeucauList.kiemtragk }"
														style="width: 50px" readonly
														pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
													<td><input type="text" id="text-input" name="ketthuc1"
														value="${yeucauList.diem.ketthuc1}->${yeucauList.ketthuc1 }"
														style="width: 50px" readonly
														pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
													<td><input type="text" id="text-input" name="ketthuc2"
														value="${yeucauList.diem.ketthuc2}->${yeucauList.ketthuc2 }"
														style="width: 50px" readonly
														pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
													<td><input type="text" name="tongket"
														value="${yeucauList.diem.tongket}->${yeucauList.tongket }"
														style="width: 80px" readonly
														pattern="([0-9]{1}|[1]{1}[0]{1})"></td>
													<td><input type="text" id="text-input" name="ghichu"
														readonly
														value="${yeucauList.diem.danhgia}->${yeucauList.danhgia}"
														style="border: none; width: 100px"></td>
													<td><input type="text" id="text-input" name="ghichu"
														readonly
														value="${yeucauList.diem.diemchu}->${yeucauList.diemchu}"
														style="border: none; width: 100px"></td>
													<td><input type="text" id="text-input" name="ghichu"
														readonly value="${yeucauList.ghichu }"
														style="width: 120px"></td>
													<td><input type="text" id="text-input" name="ghichu"
														readonly value="${yeucauList.user.hoten }"
														style="border: none; width: 150px"></td>
													<td><input type="text" id="text-input" name="ghichu"
														readonly value="${yeucauList.diem.mon.tenmon }"
														style="border: none;"></td>

													<td>
														<div class="table-data-feature">
															<c:if test="${yeucauList.status eq 0 }">
																<button class="item" data-toggle="tooltip"
																	data-placement="top" title="Update">
																	<a
																		href="/QuanLyDiem/giaovien/yeucau/update?id_yeucau=${yeucauList.id_yeucau }">
																		<i class="fa fa-check"></i>
																	</a>
																</button>
															</c:if>
															<button class="item" data-toggle="tooltip"
																data-placement="top" title="Delete">
																<a
																	href="/QuanLyDiem/giaovien/yeucau/delete?id_yeucau=${yeucauList.id_yeucau }"
																	onclick="return confirm('Bạn có chắc chắn muốn xóa không?');"><i
																	class="zmdi zmdi-delete"></i></a>
															</button>
														</div>
													</td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

								<div class="user-data__footer">
									<nav aria-label="Page navigation example ">
										<ul class="pagination justify-content-center">
											<c:choose>
												<c:when test="${page le totalPage }">
													<li class="page-item"><a class="page-link"
														href="${requestScope.request_uri}?page=1">Previous</a></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link"
														href="${requestScope.request_uri}?page=${page-1 }">Previous</a></li>
												</c:otherwise>
											</c:choose>
											<c:forEach items="${pageList}" var="page">
												<li
													class="page-item <c:if test="${param.page eq page}"> active </c:if> "><a
													class="page-link"
													href="${requestScope.request_uri}?page=${page}">${page}</a></li>
											</c:forEach>
											<c:choose>
												<c:when test="${page ge totalPage }">
													<li class="page-item"><a class="page-link"
														href="${requestScope.request_uri}?page=${totalPage}">Next</a></li>
												</c:when>
												<c:otherwise>
													<li class="page-item"><a class="page-link"
														href="${requestScope.request_uri}?page=${page +1 }">Next</a></li>
												</c:otherwise>
											</c:choose>
										</ul>
									</nav>
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
