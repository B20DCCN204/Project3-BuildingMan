<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingSearchUrl" value="/admin/building-list"></c:url>
<c:url var="buildingAPI" value="api/building"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">

            <div class="page-header">
                <h1>
                    List Building
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        search
                    </small>
                </h1>
            </div><!-- /.page-header -->

            <div class="row">
                <div class="col-xs-12">
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Search</h4>

                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>

                        <div class="widget-body" style="display: block; font-family: 'Times New Roman', Times, serif;">
                            <div class="widget-main">
                                <form:form modelAttribute="buildingSearch" action="${buildingSearchUrl}" id="formList" method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-6">
                                                    <label class="form-label">Tên tòa nhà</label>
                                                    <form:input class="form-control" path="name"
                                                           placeholder="Nhập tên tòa nhà"/>
                                                </div>
                                                <div class="col-xs-6">
                                                    <label class="form-label">Diện tích sàn</label>
                                                    <form:input class="form-control" path="floorArea"
                                                                placeholder="Nhập diện tích sàn"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-2">
                                                    <label class="form-label">Quận</label>
                                                    <form:select path="district" class="form-control">
                                                        <form:option value="">---Chọn quận---</form:option>
                                                        <form:options items="${listDistricts}"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="form-label">Phường</label>
                                                    <form:input class="form-control" path="ward"
                                                                placeholder="Nhập phường"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="form-label">Đường</label>
                                                    <form:input class="form-control" path="street"
                                                                placeholder="Nhập đường"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label class="form-label">Số tầng hầm</label>
                                                    <form:input class="form-control" path="numberOfBasement"
                                                                placeholder="Nhập số tầng hầm"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="form-label">Hướng</label>
                                                    <form:input class="form-control" path="direction"
                                                                placeholder="Nhập hướng"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="form-label">Hạng</label>
                                                    <form:input class="form-control" path="level"
                                                                placeholder="Nhập hạng"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <label class="form-label">Diện tích từ</label>
                                                    <form:input class="form-control" path="areaFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="form-label">Diện tích đến</label>
                                                    <form:input class="form-control" path="areaTo"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="form-label">Giá thuê từ</label>
                                                    <form:input class="form-control" path="rentPriceFrom"/>
                                                </div>
                                                <div class="col-xs-3">
                                                    <label class="form-label">Giá thuê đến</label>
                                                    <form:input class="form-control" path="rentPriceTo"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-5">
                                                    <label class="form-label">Tên quản lí</label>
                                                    <form:input class="form-control" path="managerName"
                                                           placeholder="Nhập tên quản lí"/>
                                                </div>
                                                <div class="col-xs-5">
                                                    <label class="form-label">Số điện thoại quản lí</label>
                                                    <form:input class="form-control" path="managerPhone"
                                                                placeholder="Nhập sdt quản lí"/>
                                                </div>
                                                <div class="col-xs-2">
                                                    <label class="form-label">Chọn nhân viên phụ trách</label>
                                                    <form:select path="staffId" id="staff" class="form-control">
                                                        <form:option value="">---Chọn nhân viên---</form:option>
                                                        <form:options items="${listStaffs}"/>
                                                    </form:select>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-7 inline" style="margin-top: 10px;">
                                                <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <button class="btn btn-xs btn-danger" id="btnSearchBuilding">
                                                        <i class="ace-icon fa fa-search bigger-110"></i>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form:form>
                            </div>
                        </div>

                        <div class="widget-footer pull-right">
                            <a type="button" class="btn btn-info btn-no-border" href="/admin/building-edit"
                               title="Thêm tòa nhà">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </a>
                            <a type="button" class="btn btn-danger btn-no-border" title="Xóa tòa nhà" id="btnDeleteBuildings">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </a>
                        </div>
                    </div>
                </div>
            </div><!-- /.row -->

            <div class="row">
                <div class="col-xs-12">
                    <h2 class="text-primary"
                        style="font-size: large; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">Search
                        Result</h2>
                </div>
            </div>

            <!-- Table  -->
            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <div class="col-xs-12">
                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" name="checkList" value="">
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên tòa nhà</th>
                            <th>Địa chỉ</th>
                            <th>Số tầng hầm</th>
                            <th>Tên quản lí</th>
                            <th>SĐT quản lí</th>
                            <th>Diện tích sàn</th>
                            <th>Diện tích thuê</th>
                            <th>Giá thuê</th>
                            <th>Phí dịch vụ</th>
                            <th>Phí môi giới</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${buildingList}">
                            <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" name="checkList" value="${item.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${item.name}</td>
                                <td>${item.address}</td>
                                <td>${item.numberOfBasement}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.rentArea}</td>
                                <td>${item.rentPrice}</td>
                                <td>${item.serviceFee}</td>
                                <td>${item.brokerageFee}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-success" title="Giao tòa nhà"
                                                onclick="assignmentBuilding(${item.id})">
                                            <i class="ace-icon fa fa-list bigger-120"></i>
                                        </button>

                                        <a type="button" class="btn btn-xs btn-info" title="Sửa tòa nhà" href="/admin/building-edit-${item.id}">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </a>

                                        <button class="btn btn-xs btn-danger" onclick="deleteBuilding(${item.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<!-- Modal -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog"
     style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover center" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">Chọn</th>
                        <th class="center">Nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <label class="pos-rel">
                                <input type="checkbox" class="ace" id="checkbox_1" value="1">
                                <span class="lbl"></span>
                            </label>
                        </td>
                        <td>Nguyễn Văn A</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <input type="hidden" id="buildingId" value="1">
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btnAssignmentBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-danger" id>Hủy</button>
            </div>
        </div>

    </div>
</div>
<script>
    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        loadStaffs(buildingId);
        $('#buildingId').val(buildingId);
    }

    function loadStaffs(){

    }

    $('#btnAssignmentBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        console.log("Ok");
    });

    function assignment(){

    }


    function deleteBuilding(id){
        var buildingId = [id];
        deleteBuildings(buildingId);
    }

    $('#btnDeleteBuildings').click(function (e) {
        e.preventDefault();
        var buildingIds = $('#simple-table').find('tbody input[type = checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteBuildings(buildingIds);
        console.log("Ok");
    });

    function deleteBuildings(data){
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}/" + data,
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "JSON",
            success: function(response){
                console.log("Success");
            },
            error: function(response){
                console.log("error")
            }
        });
    }
</script>
</body>
</html>
