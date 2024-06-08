<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerEditUrl" value="/admin/customer-edit"></c:url>
<c:url var="customerAPI" value="/api/customer"/>
<c:url var="customerList" value="/admin/customer-list"/>
<html>
<head>
    <title>Thêm mới và chỉnh sửa thông tin khách hàng</title>
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
                <c:if test="${not empty customerEdit.id}">
                    <li class="active">Edit customer's information</li>
                </c:if>
                <c:if test="${empty customerEdit.id}">
                    <li class="active">Add customer's information</li>
                </c:if>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="page-header">
                <h1>
                    Customer Information
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        dashboard
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <div class="col-xs-12">
                    <form:form modelAttribute="customerEdit" method="GET" class="form-horizontal" id="form-edit">
                        <div class="form-group">
                            <label class="col-xs-3">Tên khách hàng</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="fullname"/>
                                <div id="error-fullname" class="text-danger"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số điện thoại</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="phone"/>
                                <div id="error-phone" class="text-danger"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Email</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="email"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tên công ty</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="company"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Nhu cầu</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="demand"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Tình trạng</label>
                            <div class="col-xs-5">
                                <form:select path="status" id="staff" class="form-control">
                                    <form:option value="">---Chọn tình trạng---</form:option>
                                    <form:options items="${statusType}"/>
                                </form:select>
                            </div>
                        </div>

                        <form:hidden path="id"/>

                        <div class="form-group">
                            <div class="col-xs-3">
                            </div>
                            <div class="col-xs-4">
                                <c:choose>
                                    <c:when test="${empty customerEdit.id}">
                                        <button class="btn btn-info" id="btnAddOrUpdateCustomer">Thêm khách hàng</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-info" id="btnAddOrUpdateCustomer">Cập nhật thông tin khách hàng</button>
                                    </c:otherwise>
                                </c:choose>
                                <button class="btn btn-danger" id="btnCancel">Hủy thao tác</button>
                            </div>
                        </div>
                    </form:form>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->

        <c:forEach var="item" items="${transactionType}">
            <div class="col-xs12">
                <div class="col-sm-12">
                    <h3 class="header smaller lighter blue">${item.value}</h3>
                    <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}', ${customerEdit.id})">
                        <i class="orange ace-icon fa fa-location-arrow bigger-130"></i> Add
                    </button>
                </div>
                <c:if test="${item.key == 'CSKH'}">
                    <table id="table-cskh" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>Ngày tạo</th>
                                <th>Người tạo</th>
                                <th>Ngày sửa</th>
                                <th>Người sửa</th>
                                <th>Chi tiết giao dịch</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cskhItem" items="${cskh}">
                                <tr>
                                    <td>${cskhItem.createdDate}</td>
                                    <td>${cskhItem.createdBy}</td>
                                    <td>${cskhItem.modifiedDate}</td>
                                    <td>${cskhItem.modifiedBy}</td>
                                    <td>${cskhItem.note}</td>
                                    <td>
                                        <div class="hidden-sm hidden-xs btn-group">
                                            <a type="button" class="btn btn-xs btn-info" title="Sửa thông tin giao dịch"
                                               onclick="updateTransaction(${cskhItem.id})">
                                                <i class="ace-icon fa fa-pencil bigger-120"></i>
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${item.key == 'DDX'}">
                    <table id="table-ddx" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Ngày tạo</th>
                            <th>Người tạo</th>
                            <th>Ngày sửa</th>
                            <th>Người sửa</th>
                            <th>Chi tiết giao dịch</th>
                            <th>Thao tác</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="ddxItem" items="${ddx}">
                            <tr>
                                <td>${ddxItem.createdDate}</td>
                                <td>${ddxItem.createdBy}</td>
                                <td>${ddxItem.modifiedDate}</td>
                                <td>${ddxItem.modifiedBy}</td>
                                <td>${ddxItem.note}</td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <a type="button" class="btn btn-xs btn-info" title="Sửa thông tin giao dịch"
                                           onclick="updateTransaction(${ddxItem.id})">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </c:forEach>
    </div>
</div><!-- /.main-content -->
<!-- Modal -->
<div class="modal fade" id="transactionDetailModal" role="dialog"
     style="font-family: 'Times New Roman', Times, serif;">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success">
                    <div class="col-xs-12">
                        <label for="transactionDetail" class="col-sm-3 control-label no-padding-right">Chi tiết giao dịch</label>
                        <div class="col-sm-9">
                            <span class="block input-icon input-icon-right">
                                <input type="text" id="transactionDetail" class="width-100">
                            </span>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="customerId" id="customerId" value="">
                <input type="hidden" name="code" id="code" value="">
                <input type="hidden" name="id" id="transactionId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
                <button type="button" class="btn btn-danger" id="btnCancelTransaction">Hủy</button>
            </div>
        </div>

    </div>
</div>

<script>
    function transactionType(code, customerId){
        $('#transactionDetailModal').modal();
        $('#customerId').val(customerId);
        $('#code').val(code);
        $('#btnAddOrUpdateTransaction').text("Thêm giao dịch");
    }
    function updateTransaction(id) {
        $('#transactionDetailModal').modal('show');
        $('#btnAddOrUpdateTransaction').text("Sửa thông tin giao dịch");
        $('#transactionId').val(id);
        loadTransaction(id);
    }

    function loadTransaction(id){
        $.ajax({
            type: "GET",
            url: "${customerAPI}/transaction/" + id,
            contentType: "application/json",
            //data: JSON.stringify(data),
            dataType: "JSON",
            success: function(response){
                $('#transactionDetail').val(response.note);
                var customerid = response.customerId;
                $('#customerId').val(customerid);
                $('#code').val(response.code);
            },
            error: function(response){
                showMessageConfirmation("Xảy ra lỗi", "Tải dữ liệu thất bại", "warning", "/admin/customer-edit-" + customerid);
            }
        });
    }


    $('#btnAddOrUpdateTransaction').click(function (e){
        e.preventDefault();
        let data = {}
        data['id'] = $('#transactionId').val()
        data['code'] = $('#code').val()
        data['customerId'] = $('#customerId').val()
        data['note'] = $('#transactionDetail').val()
        addOrUpdateTransaction(data);
    })

    function addOrUpdateTransaction(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}/transaction",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "Text",
            success: function(response){
                showMessageConfirmation("Thành công", response, "success", "/admin/customer-edit-" + data.customerId);
            },
            error: function(response){
                showMessageConfirmation("Xảy ra lỗi", "Tạo giao dịch thất bại", "warning", "/admin/customer-edit-" + data.customerId);
            }
        });
    }

    $('#btnAddOrUpdateCustomer').click(function (e){
        e.preventDefault();
        var data = {};
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function (i, v){
            data["" + v.name + ""] = v.value;
        });
        addOrUpdateCustomer(data);
    });

    function addOrUpdateCustomer(data){
        $.ajax({
            type: "POST",
            url: "${customerAPI}",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "JSON",
            success: function(response){
                showMessageConfirmation("Thành công", "Thao tác thành công!", "success", "/admin/customer-edit-" + response.id);
            },
            error: function(response){
                var errors = response.responseJSON;
                errors.forEach(function(error) {
                    var field = error.split(':')[0];
                    var message = error.split(':')[1];
                    $('#error-' + field).text(message);
                });
            }
        });
    }

    $('#btnCancel').click(function (e){
        e.preventDefault();
        window.location.href = "${customerList}";
    });
    $('#btnCancelTransaction').click(function (e){
        e.preventDefault();
        $('#transactionDetailModal').modal('hide')
    });

</script>
</body>

</html>
