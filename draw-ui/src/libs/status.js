/**
 * @file status.js
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/10/9
 */

let status = {};

//合同转换状态
status.WAITING_CONVERT = 0;
status.CONVERTED_SUCCESS = 1;
status.CONVERTED_FAILD = 2;

//实名认证情况
status.WAITING_CHECK = 0;
status.CHECK_SUCCESS = 1;
status.CHECK_FAILD =2;

export default status;


