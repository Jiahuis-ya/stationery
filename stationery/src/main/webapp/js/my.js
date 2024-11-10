//领用窗口中时间标签的内容改变时执行
function cg() {
    $("#savemsg").attr("disabled", false);
    var rt = $("#time").val().split("-");
    var ny = new Date().getFullYear();
    var nm = new Date().getMonth() + 1;
    var nd = new Date().getDate();
    if (rt[0] < ny) {
        alert("日期不能早于今天")
        $("#savemsg").attr("disabled", true);
    } else if (rt[0] == ny) {
        if (rt[1] < nm) {
            alert("日期不能早于今天")
            $("#savemsg").attr("disabled", true);
        } else if (rt[1] == nm) {
            if (rt[2] < nd) {
                alert("日期不能早于今天")
                $("#savemsg").attr("disabled", true);
            } else {
                $("#savemsg").attr("disabled", false);
            }
        }
    }
}
//点击领用文具时执行
function borrow() {
        var url =getProjectPath()+ "/stationeryRequisition/borrowStationery";
        $.post(url, $("#borrowForm").serialize(), function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath()+"/stationeryRequisition/searchBorrowed";
            }
        })
}

//重置添加和编辑窗口中输入框的内容
function resetFrom() {
    $("#aoe").attr("disabled",true)
    var $vals=$("#addOrEditForm input");
    $vals.each(function(){
        $(this).attr("style","").val("")
    });
}
//重置添加和编辑窗口中输入框的样式
function resetStyle() {
    $("#aoe").attr("disabled",false)
    var $vals=$("#addOrEditForm input");
    $vals.each(function(){
        $(this).attr("style","")
    });
}
//查询id对应的文具信息，并将文具信息回显到编辑或领用的窗口中
function findStationeryById(id,doname) {
    resetStyle()
    var url = getProjectPath()+"/stationeryRequisition/findById?id=" + id;
    $.get(url, function (response) {
        console.log('看看response', response)
        if(response?.success) {
            //如果是编辑文具，将获取的文具信息回显到编辑的窗口中
            if(doname=='edit'){
                $("#aebid").val(response.data.id);
                $("#aeb_name").val(response.data.name);
                $("#aeb_specification").val(response.data.specification);
                $("#aeb_price").val(response.data.price);
                $("#aeb_nums").val(response.data.nums);
                $("#aeb_department").val(response.data.department);
            }
            //如果是领用文具，将获取的文具信息回显到领用的窗口中
            if(doname=='borrow'){
                $("#savemsg").attr("disabled",true)
                $("#bid").val(response.data.id);
                $("#b_name").val(response.data.name);
                $("#b_specification").val(response.data.specification);
                $("#b_price").val(response.data.price);
                $("#b_nums").val(response.data.nums);
                $("#b_department").val(response.data.department);
                // $("#b_borrower").val(response.data.borrower);
                $("#b_return_time").val("");
            }
        } else {
            alert(response.message);
        }
        
    })
}

function onRequisitionBtn(id, userId) {
    resetStyle()
    
    var url = getProjectPath()+"/stationeryInfo/findById?id=" + id;
    $.get(url, function (response) {
        console.log('看看response', response, url)
        if(response?.success) {
            $("#aebid").val(response.data.id);
            $("#aaeb_name").val(response.data.name);
            $("#aaeb_specification").val(response.data.specification);
            $("#aaeb_price").val(response.data.price);
        } else {
            alert(response.message);
        }
        
    })

    onAddMsg(userId)
    
}

function onAddMsg(userId) {
    var url = getProjectPath()+"/StationerySearchRecord/findById/" + userId;
    $.get(url, function (response) {
        console.log('看看response222', response)
        const result = (response?.data || [])?.map(item => item?.recordName)
        const mergeData = [...new Set(result)].join(',')
       $("#mergeData").text(mergeData);
    })
}

//点击添加或编辑的窗口的确定按钮时，提交文具信息
function addOrEdit() {
    const formData = $("#addOrEditForm").serialize();
    console.log('看看表单', formData);

    console.log('走添加');
    var url = getProjectPath()+"/stationeryRequisition/addStationery";
    $.post(url, formData, function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath()+"/stationeryRequisition/search";
        }
    })
}
// 编辑文具领用信息
function addOrEdit2 () {
    const formData = $("#addOrEditForm").serialize();
    console.log('看看表单', formData)
    console.log('走编辑');
    var url = getProjectPath()+"/stationeryRequisition/editStationery";
    $.post(url, $("#addOrEditForm").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath()+"/stationeryRequisition/search";
        }
    })
}
//归还文具时执行
function returnStationery(bid) {
    var r = confirm("确定归还文具?");
    if (r) {
        var url = getProjectPath()+"/stationeryRequisition/returnStationery?id=" + bid;
        $.get(url, function (response) {
            alert(response.message)
            //还书成功时，刷新当前领用的列表数据
            if (response.success == true) {
                window.location.href = getProjectPath()+"/stationeryRequisition/searchBorrowed";
            }
        })
    }
}
//确认文具已经归还
function returnConfirm(bid) {
    var r = confirm("确定文具已归还?");
    if (r) {
        var url = getProjectPath()+"/stationeryRequisition/returnConfirm?id=" + bid;
        $.get(url, function (response) {
            alert(response.message)
            //还书确认成功时，刷新当前领用的列表数据
            if (response.success == true) {
                window.location.href = getProjectPath()+"/stationeryRequisition/searchBorrowed";
            }
        })
    }
}
//检查文具信息的窗口中，文具信息填写是否完整
function checkval(){
    var $inputs=$("#addOrEditTab input")
    var count=0;
    $inputs.each(function () {
        if($(this).val()==''||$(this).val()=="不能为空！"){
            count+=1;
        }
    })
    //如果全部输入框都填写完整，解除确认按钮的禁用状态
    if(count==0){
        $("#aoe").attr("disabled",false)
    }
}
//页面加载完成后，给文具模态窗口的输入框绑定失去焦点和获取焦点事件
$(function() {
    var $inputs=$("#addOrEditForm input")
    var eisbn="";
    $inputs.each(function () {
        //给输入框绑定失去焦点事件
        $(this).blur(function () {
            if($(this).val()==''){
                $("#aoe").attr("disabled",true)
                $(this).attr("style","color:red").val("不能为空！")
            }
            else{
                checkval()
            }
        }).focus(function () {
            if($(this).val()=='不能为空！'){
                $(this).attr("style","").val("")
            }else{
                $(this).attr("style","")
            }
        })
    })
});
//获取当前项目的名称
function getProjectPath() {
    //获取主机地址之后的目录，如： stationery/admin/stationery.jsp
    var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/stationery
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return  projectName;
}

/**
 * 数据展示页面分页插件的参数
 * cur 当前页
 * total 总页数
 * len   显示多少页数
 * pagesize 1页显示多少条数据
 * gourl 页码变化时 跳转的路径
 * targetId 分页插件作用的id
 */
var pageargs = {
    cur: 1,
    total: 0,
    len: 5,
    pagesize:10,
    gourl:"",
    targetId: 'pagination',
    callback: function (total) {
        var oPages = document.getElementsByClassName('page-index');
        for (var i = 0; i < oPages.length; i++) {
            oPages[i].onclick = function () {
                changePage(this.getAttribute('data-index'), pageargs.pagesize);
            }
        }
        var goPage = document.getElementById('go-search');
        goPage.onclick = function () {
            var index = document.getElementById('yeshu').value;
            if (!index || (+index > total) || (+index < 1)) {
                return;
            }
            changePage(index, pageargs.pagesize);
        }
    }
}


/**
 *文具列表查询栏的查询参数
 * name 文具名
 * purchaser 采购人
 * supplier 供应商
 */
 var stationeryListVO={
    name: '',
    purchaser:'',
    supplier:''
}
// 领用列表
 var stationeryVO={
    name: '',
    borrower: '',
}
// 领用记录
 var stationeryRecordVO={
    recordName: '',
    recordBorrower: '',
}
// 用户列表
var userVO = {
    id: '',
    name: ''
}

//数据展示页面分页插件的页码发送变化时执行
function changePage(pageNo,pageSize) {
    pageargs.cur=pageNo;
    pageargs.pagesize=pageSize;
    document.write("<form action="+pageargs.gourl +" method=post name=form1 style='display:none'>");
    document.write("<input type=hidden name='pageNum' value="+pageargs.cur+" >");
    document.write("<input type=hidden name='pageSize' value="+pageargs.pagesize+" >");
    
    //文具列表搜索栏
    if(pageargs.gourl.indexOf("stationeryInfo")>=0){
        document.write("<input type=hidden name='name' value="+stationeryListVO.name+" >");
        document.write("<input type=hidden name='purchaser' value="+stationeryListVO.purchaser+" >");
        document.write("<input type=hidden name='supplier' value="+stationeryListVO.supplier+" >");
    }
    // 文具领用列表搜索栏
    if(pageargs.gourl.indexOf("stationeryRequisition")>=0){
        document.write("<input type=hidden name='name' value="+stationeryVO.name+" >");
        document.write("<input type=hidden name='borrower' value="+stationeryVO.borrower+" >");
    }
    // 文具领用记录搜索栏
    if(pageargs.gourl.indexOf("StationerySearchRecord")>=0){
        document.write("<input type=hidden name='recordName' value="+stationeryRecordVO.recordName+" >");
        document.write("<input type=hidden name='recordBorrower' value="+stationeryRecordVO.recordBorrower+" >");
    }
    // 用户搜索栏
    if (pageargs.gourl.indexOf("user") >= 0) {
        document.write("<input type=hidden name='id' value=" + userVO.id + " >");
        document.write("<input type=hidden name='name' value=" + userVO.name + " >");
    }
    document.write("</form>");
    document.form1.submit();
    pagination(pageargs);
}

function onAddStationeryBtn() {
    window.location.href = getProjectPath()+"/stationeryInfo/search";
}

function findUserById(uid) {
    var url = getProjectPath()+"/user/findById?id=" + uid;
    $.get(url, function (response) {
        $("#uid").val(response.id);
        $("#uname").val(response.name);
        $("#pw").val(response.password);
        $("#urole").val(response.role);
        $("#uemail").val(response.email);
        $("#uhire").val(response.hiredate);

    })
}

function editUser() {
    var url =getProjectPath()+ "/user/editUser";
    $.post(url, $("#editUser").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href = getProjectPath()+"/user/search";
        }
    })
}


function changeVal() {
    $("#addmsg").html("")
}

function checkVal() {
    $("#savemsg").attr("disabled", false);
    $("#addmsg").html("")
    var adduname = $("#adduname").val();
    var adduemail = $("#adduemail").val();
    var addPw = $("#addPw").val();
    var addtime = $("#time").val();
    if ($.trim(adduname) == '') {
        $("#savemsg").attr("disabled", true);
        $("#addmsg").html("姓名不能为空")
    } else {
        checkName(adduname);
        if ($.trim(adduemail) == '') {
            $("#savemsg").attr("disabled", true);
            $("#addmsg").html("邮箱不能为空")
        } else if ($.trim(adduemail) != '') {
            checkEmail(adduemail);
                if ($.trim(addPw) == '') {
                $("#savemsg").attr("disabled", true);
                $("#addmsg").html("密码不能为空")
            }else if($.trim(addPw) != ''){
                if($.trim(addtime) == ''){
                    $("#savemsg").attr("disabled", true);
                    $("#addmsg").html("入职日期不能为空")
                }else{
                    cg()
                }
            }
        }
    }
}

function checkName(name, email) {
    var url = getProjectPath()+"/user/checkName?name=" + name;
    $.post(url, function (response) {
        if (response.success != true) {
            $("#savemsg").attr("disabled", true);
            $("#addmsg").html(response.message)
        }
    })
}

function checkEmail(email) {
    var url = getProjectPath()+"/user/checkEmail?email=" + email;
    $.post(url, function (response) {
        if (response.success != true) {
            $("#savemsg").attr("disabled", true);
            $("#addmsg").html(response.message)
        }
    })
}

function saveUser() {
    var url =getProjectPath()+"/user/addUser";
    $.post(url, $("#addUser").serialize(), function (response) {
        alert(response.message)
        if (response.success == true) {
            window.location.href =  getProjectPath()+"/user/search";
        }
    })
}
function delUser(uid) {
    var r = confirm("确定办理工号：" + uid + "的离职?");
    if (r) {
        var url = getProjectPath() + "/user/delUser?id=" + uid;
        $.get(url, function (response) {
            alert(response.message)
            if (response.success == true) {
                window.location.href = getProjectPath() + "/user/search";
            }
        })
    }
}