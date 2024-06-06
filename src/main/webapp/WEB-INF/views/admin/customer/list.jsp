<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingSearchUrl" value="/admin/customer-list"></c:url>
<html>
<head>
    <title>Title</title>
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
                    List Customer
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
                                <form:form modelAttribute="customerSearch" action="${customerSearchUrl}" id="formList"
                                           method="GET">
                                    <div class="row">
                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <div class="col-xs-4">
                                                    <label class="form-label">Tên khách hàng</label>
                                                    <form:input class="form-control" path="fullname"
                                                                placeholder="Nhập tên khách hàng"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="form-label">Số điện thoại</label>
                                                    <form:input class="form-control" path="phone"
                                                                placeholder="Nhập sdt khách hàng"/>
                                                </div>
                                                <div class="col-xs-4">
                                                    <label class="form-label">Email</label>
                                                    <form:input class="form-control" path="email"
                                                                placeholder="Nhập sdt khách hàng"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
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
                                            <div class="col-xs-12">
                                                <div class="col-xs-3">
                                                    <button type="submit" class="btn btn-xs btn-danger"
                                                            id="btnSearchBuilding">
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
                            <a type="button" class="btn btn-info btn-no-border" href="/admin/customer-edit"
                               title="Thêm khách hàng">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-person-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"/>
                                    <path d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </a>
                            <a type="button" class="btn btn-danger btn-no-border" title="Xóa khách hàng"
                               id="btnDeleteCustomers">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-person-dash" viewBox="0 0 16 16">
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
                    <form:form modelAttribute="customerList">
                        <display:table name="customerList.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${buildingSearchUrl}" partialList="true" sort="external"
                                       size="${customerList.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="tableList" pagesize="${customerList.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${tableList.id}"
                                           id="checkbox_${tableList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>
                            <display:column headerClass="text-left" property="fullname" title="Tên khách hàng" />
                            <display:column headerClass="text-left" property="phone" title="Số điện thoại" />
                            <display:column headerClass="text-left" property="email" title="Email" />
                            <display:column headerClass="text-left" property="company" title="Công ty" />
                            <display:column headerClass="text-left" property="demand" title="Nhu cầu" />
                            <display:column headerClass="text-left" property="createdBy" title="Người thêm" />
                            <display:column headerClass="text-left" property="createdDate" title="Ngày thêm" />
                            <display:column headerClass="text-left" property="status" title="Trạng thái" />
                            <display:column headerClass="text-left" title="Thao tác">
                                <div class="hidden-sm hidden-xs btn-group">
                                    <a class="btn btn-xs btn-success" title="Giao khách hàng"
                                       onclick="assignmentCustomer(${tableList.id})">
                                        <i class="ace-icon fa fa-list bigger-120"></i>
                                    </a>

                                    <a type="button" class="btn btn-xs btn-info" title="Sửa khách hàng"
                                       href="/admin/customer-edit-${tableList.id}">
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>

                                    <a class="btn btn-xs btn-danger" onclick="">
                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                    </a>
                                </div>
                            </display:column>
                        </display:table>
                    </form:form>
                </div><!-- /.span -->
            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<!-- Modal -->
<div class="modal fade" id="assignmentCustomerModal" role="dialog"
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

                    </tbody>
                </table>
            </div>
            <input type="hidden" id="customerId" value="1">
            <div class="modal-footer">
                <button type="button" class="btn btn-info" id="btnAssignmentBuilding">Giao khách hàng</button>
                <button type="button" class="btn btn-danger" id>Hủy</button>
            </div>
        </div>

    </div>
</div>
<script>
    function assignmentCustomer(customerId) {
        $('#assignmentCustomerModal').modal();
        // loadStaffs(buildingId);
        $('#customerId').val(customerId);
    }
</script>
</body>
</html>
