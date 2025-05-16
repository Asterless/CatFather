<template>
  <div class="chronicle-container">
    <h1 class="title">「圆头耄耋」事件编年史</h1>

    <section class="timeline-section">
      <h2>时间线总览</h2>
      <table class="timeline-table">
        <tr v-for="(event, index) in timeline" :key="index">
          <td class="year">{{ event.year }}</td>
          <td class="event">{{ event.desc }}</td>
        </tr>
      </table>
    </section>

    <section 
      class="event-section" 
      v-for="(item, index) in chronicleData" 
      :key="index"
    >
      <h2 class="period">{{ item.period }}</h2>
      <ul class="event-list">
        <li v-for="(event, idx) in item.events" :key="idx">
          <template v-if="event.link">
            <a :href="event.link" target="_blank" class="event-link">{{ event.text }}</a>
          </template>
          <template v-else>
            {{ event.text }}
          </template>
          <ul v-if="event.subItems" class="sub-event-list">
            <li v-for="(subItem, subIdx) in event.subItems" :key="subIdx">
              <a 
                v-if="subItem.link" 
                :href="subItem.link" 
                target="_blank"
                class="event-link"
              >
                {{ subItem.text }}
              </a>
              <template v-else>{{ subItem.text }}</template>
            </li>
          </ul>
        </li>
      </ul>
    </section>

    <section class="footnote">
      <p>以上信息基于网络公开资料整理，时间节点可能存在误差</p>
    </section>
  </div>
</template>

<script setup>
const chronicleData = [
  {
    period: "2020年：事件起源",
    events: [
      { 
        text: "博主「白手套&马犬旺财」开始投喂和救助家附近的流浪猫狗",
        link: "https://www.xiaohongshu.com/user/profile/58ac3ef350c4b422771d1ae2"
      },
      { text: "与其他流浪猫相处融洽" }
    ]
  },
  {
    period: "2021年：橘猫初现",
    events: [
      { text: "橘猫进入流浪猫群体，初期表现正常" },
      { text: "逐渐显露攻击性，抢夺食物并攻击其他猫" },
      { text: "得名「猫界丧彪」" }
    ]
  },
  {
    period: "2022年：冲突升级",
    events: [
      { 
        text: "10月：疑似第一次被博主拍摄并上传小红书笔记",
        link: "https://www.xiaohongshu.com/explore/63932548000000001f00a074"
      },
      { text: "橘猫攻击导致多只猫受伤死亡" },
      { text: "博主停止投喂并开始驱赶" },
      { text: "橘猫转为偷盗模式" }
    ]
  },
  {
    period: "2024年：网络爆红",
    events: [
      {
        text: "9月11日：发布橘猫冲突视频",
        link: "https://www.xiaohongshu.com/explore/66e28b54000000000c01a6fa",
        subItems: [
          { text: "「圆头耄耋」表情包走红网络" },
          { text: "网友分化为三派争议" }
        ]
      }
    ]
  },
  {
    period: "2025年：后续发展",
    events: [
      {
        text: "1月底：博主最后一次目击橘猫",
        link: "https://www.xiaohongshu.com/explore/67cb046d000000002903201b"
      },
      {
        text: "3月：博主发布事件总结视频",
        link: "https://www.xiaohongshu.com/discovery/item/67e697cc000000001b03f2d5"
      },
      {
        text: "4月：",
        subItems: [
          { text: "网友目击橘猫复活传闻" },
          { 
            text: "品客薯片广告争议",
            link: "https://news.qq.com/rain/a/20250412A069BO00"
          }
        ]
      },
      { text: "5月：论坛持续讨论橘猫生死" }
    ]
  }
];

const timeline = [
  { year: "2020", desc: "博主开始救助流浪猫狗" },
  { year: "2021", desc: "橘猫出现并显露攻击性" },
  { year: "2022", desc: "冲突升级为偷盗模式" },
  { year: "2024", desc: "网络爆红与表情包传播" },
  { year: "2025", desc: "生死疑云与品牌争议" }
];
</script>

<style scoped>
/* 原有样式基础上新增链接样式 */
.event-link {
  color: #c00;
  text-decoration: none;
  border-bottom: 1px dotted #c00;
  transition: color 0.3s ease;
}

.event-link:hover {
  color: #900;
  border-bottom-style: solid;
}

.sub-event-list {
  list-style-type: circle;
  padding-left: 25px;
  margin-top: 5px;
}

.sub-event-list li {
  margin-bottom: 4px;
  color: #666;
}
</style>
