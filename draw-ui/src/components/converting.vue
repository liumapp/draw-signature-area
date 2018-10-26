/**
 * @file converting.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/3/18
 */
<style lang="less">
  .spin-container {
    display: inline-block;
    text-align: center;
    width: 500px;
    position: relative;
    border: none;
  }
</style>
<template>
  <div>
    <Row>
      <Col span="12" offset="6">
        <br>
        <div>
          文档列表
        </div>
        <div class="spin-container">
          <div>
            <Table stripe :ref="tableRefs" :columns="tableColumn" :data="tableData"></Table>
          </div>
        </div>
        <br>
        <div>
          签署人列表
        </div>
        <div class="spin-container">
          <div>
            <Table stripe :ref="signersTableRefs" :columns="signersColumn" :data="signersTableData">
              <div slot="footer" align="left" style="padding-left: 15px;">
                <Button type="primary">添加签署人</Button>
              </div>
            </Table>
          </div>
        </div>
        <div class="spin-content">
          <br>
          <Button type="default" @click="getBack">返回</Button>
        </div>
      </Col>
    </Row>
  </div>
</template>
<script>
import status from '@/libs/status'
import util from '@/libs/util'
import resultColumn from '@/column/resultsColumn'
import signersColumn from '@/column/signersColumn'

export default {
  name: 'converting',
  props: [
    'convertId'
  ],
  data () {
    return {
      ws: null,
      //转换合同表格
      tableRefs: 'convertingResultTable',
      tableColumn: [],
      tableData: [],

      //联系人表格
      signersTableRefs: 'signersTable',
      signersColumn: [],
      signersTableData: []
    };
  },
  created () {
    this.initTable();
  },
  mounted () {
    if ('window' in window) {
      this.initSocket();
    } else {
      this.$Message.error('your browser not support socket !');
    }
  },
  methods: {
    initTable () {
      this.tableColumn = resultColumn.tableResultsColumn;
      this.tableData = this.$store.getters.docs;
      this.signersColumn = signersColumn.tableSignersColumn;
      this.tableColumn.forEach (item => {
        if (item.handle) {
          item.render = (h, params) => {
            const row = params.row;
            const results = [];
            if (row.status == status.CONVERTED_SUCCESS) {
              results.push(this.getDrawButton(h, row));
            } else {
//           todo 转换失败的情况
              results.push(this.getCancledButton(h, row));
            }
            return h('div', results);
          };
        }
      });
    },
    initSocket () {
      let _vue = this;
      this.ws = new WebSocket(util.convertingResultSocket + "/" + this.convertId);
      this.ws.onmessage = (event) => {
        _vue.getMessage(event);
      };
    },
    /**
     * the event is a json object
     * contains {index, savename, status}
     */
    getMessage (event) {
      let _vue = this;
      let data = event.data;
      data = JSON.parse(data);
      this.tableData.forEach((item,key) => {
        if (key == data.index) {
          if (data.status == status.CONVERTED_SUCCESS) {
            _vue.tableData[key].savename = data.savename;
            _vue.tableData[key].status = status.CONVERTED_SUCCESS;
            _vue.tableData[key].picNumbers = data.picNumbers;
            _vue.tableData[key].picNames = data.picNames;
            this.$Message.success("convert success , the file savename is : " + data.savename + " and converted " + data.picNumbers + " pics");
          } else {
            _vue.tableData[key].savename = data.savename;
            _vue.tableData[key].status = status.CONVERTED_FAILD;
            _vue.tableData[key].picNumbers = data.picNumbers;
            _vue.tableData[key].picNames = data.picNames;
            this.$Message.error("convert failed");
          }
          _vue.$store.dispatch('updateDoc', _vue.tableData[key], key);
        }
      });
    },
    downloadPdf () {
      window.open(util.ajaxUrl + "download/?filename=" + this.filename);
    },
    getBack () {
      window.location.reload();
    },
    getDrawButton (h, row) {
      let _vue = this;
      _vue.$store.dispatch('choiceDocToDraw', row._index);
      return h('Button', {
        props: {
          type: 'primary',
        },
        on: {
          click: () => {
            _vue.$emit('next');
          }
        }
      }, '绘制');
    },
    getDownloadButton (h, row) {
      return h('Button', {
        props: {
          type: 'success',
        },
        on: {
          click: () => {
            window.open(util.ajaxUrl + "download/?filename=" + row.savename);
          }
        }
      }, '下载');
    },
    getCancledButton (h, row) {
      let _vue = this;
      return h('Button', {
        props: {
          type: 'warning',
        },
        on: {
          click: () => {
            let index = row._index--;
            _vue.tableData.splice(index, 1);
          }
        }
      }, '取消')
    }
  }
}
</script>
