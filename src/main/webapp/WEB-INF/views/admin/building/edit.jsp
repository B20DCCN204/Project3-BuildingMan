<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingList" value="/admin/building-list"/>
<html>
<head>
    <title>Thêm & sửa tòa nhà</title>
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
                    Add & Edit Building
                    <small>
                        <i class="ace-icon fa fa-angle-double-right"></i>
                        dashboard
                    </small>
                </h1>
            </div><!-- /.page-header -->
            <div class="row" style="font-family: 'Times New Roman', Times, serif;">
                <div class="col-xs-12">
                    <form:form modelAttribute="buildingEdit" method="GET" class="form-horizontal" id="form-edit">
                        <div class="form-group">
                            <label class="col-xs-3">Tên tòa nhà</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Quận</label>
                            <div class="col-xs-3">
                                <form:select class="form-control" path="district">
                                    <form:option value="">---Chọn quận---</form:option>
                                    <form:options items="${listDistricts}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phường</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="ward"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đường</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="street"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Kết cấu</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="structure"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="numberOfBasement"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="floorArea"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hướng</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="direction"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hạng</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="level"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích thuê</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentArea"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentPrice"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Mô tả giá</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentPriceDescription"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="serviceFee"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ô tô</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="carFee"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí mô tô</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="motoFee"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="overtimeFee"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tiền điện</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="electricityFee"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đặt cọc</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="deposit"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thanh toán</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="payment"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thời hạn thuê</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="rentTime"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thời gian trang trí</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="decorationTime"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lí</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="managerName"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số điện thoại quản lí</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="managerPhone"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí môi giới</label>
                            <div class="col-xs-9">
                                <form:input class="form-control" path="brokerageFee"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Loại tòa nhà</label>
                            <div class="col-xs-4" style="display: flex; justify-content: space-between;">
                                <form:checkboxes path="typeCode" items="${typeCodes}"/>
                            </div>
                        </div>
                        <form:hidden path="id"/>
                        <!-- <div class="form-group">
                            <label class="col-xs-3">Hình đại diện</label>
                            <div class="col-xs-3">
                                <input class="form-control" type="image" id="name" name="name">
                            </div>
                        </div> -->
                        <div class="form-group">
                            <div class="col-xs-3">
                            </div>
                            <div class="col-xs-4">
                                <c:choose>
                                    <c:when test="${empty buildingEdit.id}">
                                        <button class="btn btn-info" id="btnAddOrUpdateBuilding">Thêm tòa nhà</button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="btn btn-info" id="btnAddOrUpdateBuilding">Cập nhật tòa nhà</button>
                                    </c:otherwise>
                                </c:choose>
                                <button class="btn btn-danger" id="btnCancel">Hủy thao tác</button>
                            </div>
                        </div>
                    </form:form>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<script>
    $('#btnAddOrUpdateBuilding').click(function(e){
        e.preventDefault();
        var data = {};
        var typeCode = [];
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function(i, v){
            if(v.name != 'typeCode'){
                data["" + v.name + ""] = v.value;
            }else{
                typeCode.push(v.value);
            }
        })
        data['typeCode'] = typeCode;
        //validate
        if(typeCode != ''){
            addOrUpdateBuilding(data);
        }else{
            alert("TypeCode is required!!!")
            window.location.href = "<c:url value="/admin/building-edit?typeCode=required"/>";
        }

    })

    function addOrUpdateBuilding(data){
        // call api
        $.ajax({
            type: "POST",
            url: "${buildingAPI}",
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

    $('#btnCancel').click(function (e){
        e.preventDefault();
        window.location.href = "${buildingList}";
    });
</script>
</body>
</html>
