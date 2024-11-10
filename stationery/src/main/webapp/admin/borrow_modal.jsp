<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 文具领用信息的模态窗口，默认是隐藏的 -->
<div class="modal fade" id="borrowModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 id="myModalLabel">领用文具信息</h3>
            </div>
            <div class="modal-body">
                <form id="borrowForm">
                    <table class="table table-bordered table-striped" width="800px">
                        <%--文具的id，不展示在页面--%>
                        <span><input type="hidden" id="bid" name="id"></span>
                        <tr>
                            <td><span style="color: red">*</span>文具名称</td>
                            <td><input class="form-control" readonly placeholder="请输入名称" name="name" id="b_name"></td>
                             
                            <td><span style="color: red">*</span>规格</td>
                            <td><input class="form-control" readonly placeholder="请输入规格" name="specification" id="b_specification"></td>
                        
                        </tr>
                        <tr>
                            <td><span style="color: red">*</span>文具价格</td>
                            <td><input class="form-control" readonly type="number" placeholder="请输入价格" name="price" id="b_price"></td>
                            
                            <td><span style="color: red">*</span>领用数量</td>
                            <td><input class="form-control" placeholder="请输入数量（整数）" name="nums" id="b_nums" type="number" onKeypress="return (/[\d]/.test(String.fromCharCode(event.keyCode)))"></td>
                        
                        </tr>
                        <tr>
                            <td><span style="color: red">*</span>领用部门</td>
                            <td><input class="form-control" placeholder="请输入部门" name="department" id="b_department"></td>
                           
                            <!-- <td><span style="color: red">*</span>领用人</td>
                            <td><input class="form-control" placeholder="请输入领用人" name="borrower" id="b_borrower"></td> -->
                        </tr>
                        <tr>
                            <td>归还时间<br/><span style="color: red">*</span></td>
                            <%--时间控件中的内容改变时，调用js文件中的cg()方法--%>
                            <td><input class="form-control" type="date" name="returnTime" id="b_return_time" onchange="cg()">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <div class="modal-footer">
                <%--点击保存按钮时，隐藏模态窗口并调用js文件中的borrow()方法--%>
                <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" onclick="borrow()"
                        disabled="true" id="savemsg">保存
                </button>
                <button class="btn btn-default" data-dismiss="modal" aria-hidden="true">关闭</button>
            </div>
        </div>
    </div>
</div>
