/**
 * @file signerColumn.js
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/10/25
 */

import status from '@/libs/status';

export const tableSignersColumn = [
  {
    title: '序号',
    type: 'index',
    align: 'center',
    width: 80
  },
  {
    title: '姓名',
    align: 'center',
    render: (h, params) => {
      const row = params.row;
      return h('span', row.name);
    }
  },
  {
    title: '身份证号码',
    align: 'center',
    render: (h, params) => {
      const row = params.row;
      return h('span', row.identityCode);
    }
  },
  {
    title: '实名认证',
    align: 'center',
    render: (h, params) => {
      const row = params.row;
      const color = row.status === status.CHECK_FAILD ? 'red' : row.status === status.CHECK_SUCCESS ? 'green' : 'blue';
      const text = row.status === status.CHECK_FAILD ? '未通过' : row.status === status.CHECK_SUCCESS ? '通过' : '认证中';
      return h('Tag', {
        props: {
          type: 'dot',
          color: color
        }
      }, text);
    }
  }
];

const resultColumn = {
  tableSignersColumn: tableSignersColumn
};

export default resultColumn;

