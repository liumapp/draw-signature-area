/**
 * @file index.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 2018/9/28
 */
<template>
  <div>
    <Row>
      <Col span="18" offset="3">
      <Card>
        <Steps :current="current">
          <Step title="上传doc文档" content="form表单上传一个待转换的doc文档"></Step>
          <Step title="转换过程中" content="通过socket等待转换结果，成功后获取转换好的pdf文档"></Step>
          <Step title="绘制区域" content="后端转换为Pic后获取文件，并在其上绘制200px*100px矩形"></Step>
          <Step title="下载" content="绘制成功后供后端处理，在指定的区域上附加一张图片后下载最终PDF文件"></Step>
        </Steps>
        <doc-upload v-if="current == 0" @next="nextStep" :convertId="convertId"></doc-upload>
        <converting v-else-if="current == 1" @next="nextStep" :convertId="convertId"></converting>
        <draw-area v-else-if="current == 2" @next="nextStep" @prev="prevStep"></draw-area>
        <download v-else-if="current == 3" @next="nextStep"></download>
        <div v-else>
          wrong current value !
        </div>
      </Card>
      </Col>
    </Row>
    <Row>
      <Col span="18" offset="3">
      当前在线人数：{{ people }}
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util';
import docUpload from '@/components/doc-upload'
import converting from '@/components/converting'
import drawArea from '@/components/draw-area';
import download from '@/components/download';
export default {
  name: 'index',
  components: {
    docUpload, converting, drawArea, download
  },
  data () {
    return {
      current: 0,
      people: 0,
      ws: null,
      convertId: 0,
      docList: []
    }
  },
  created () {
    this.convertId = util.randNumber(5);
  },
  mounted () {
    if ('window' in window) {
      this.initSocket();
    } else {
      this.$Message.error('your browser not support socket !');
    }
  },
  methods: {
    initSocket () {
      let _vue = this;
      this.ws = new WebSocket(util.onlineSocketUrl);
      this.ws.onmessage = (event) => {
        _vue.getMessage(event);
      };
    },
    getMessage (event) {
      this.people = event.data;
    },
    nextStep () {
      this.current++;
    },
    prevStep () {
      this.current--;
    }
  }
}
</script>
