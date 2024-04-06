<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<script type="text/javascript">
    (function () {
        /* alert("登录超时，请重新登录！");
        logout(); */
        alertMsg.error('登录超时或可能在别的地方登录，请重新登录！', {
            okCall: function () {
                logout();
            }
        });
    })();

    function logout() {
        top.location.href = "toLogin";
    }
</script>