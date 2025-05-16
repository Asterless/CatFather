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

    <section class="event-section" v-for="(item, index) in chronicleData" :key="index">
      <h2 class="period">{{ item.period }}</h2>
      <ul class="event-list">
        <li v-for="(event, idx) in item.events" :key="idx">{{ event }}</li>
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
      "博主「白手套&马犬旺财」开始投喂和救助家附近的流浪猫狗",
      "与其他流浪猫相处融洽"
    ]
  },
  {
    period: "2021年：橘猫初现",
    events: [
      "橘猫进入流浪猫群体，初期表现正常",
      "逐渐显露攻击性，抢夺食物并攻击其他猫",
      "得名「猫界丧彪」"
    ]
  },
  {
    period: "2022年：冲突升级",
    events: [
      "橘猫攻击导致多只猫受伤死亡",
      "博主停止投喂并开始驱赶",
      "橘猫转为偷盗模式"
    ]
  },
  {
    period: "2024年：网络爆红",
    events: [
      "人猫冲突视频《我愿称之为史上最强战猫》发布",
      "「圆头耄耋」表情包走红网络",
      "舆论分化为三派争议"
    ]
  },
  {
    period: "2025年：后续发展",
    events: [
      "1月：橘猫疑似中毒死亡",
      "4月：品客薯片广告引发争议",
      "5月：生死成谜持续讨论"
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
.chronicle-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  font-family: "Microsoft YaHei", sans-serif;
}

.title {
  text-align: center;
  color: #333;
  margin-bottom: 30px;
}

.timeline-section {
  margin-bottom: 40px;
}

.timeline-table {
  width: 100%;
  border-collapse: collapse;
}

.timeline-table td {
  padding: 12px;
  border-bottom: 1px solid #eee;
}

.year {
  width: 20%;
  font-weight: bold;
  color: #666;
}

.event {
  width: 80%;
}

.event-section {
  margin-bottom: 30px;
  background: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
}

.period {
  color: #c00;
  border-left: 4px solid #c00;
  padding-left: 10px;
  margin-bottom: 15px;
}

.event-list {
  list-style-type: square;
  padding-left: 20px;
}

.event-list li {
  margin-bottom: 8px;
  line-height: 1.6;
}

.footnote {
  margin-top: 40px;
  font-size: 0.9em;
  color: #666;
  text-align: center;
}

@media (max-width: 768px) {
  .chronicle-container {
    padding: 15px;
  }
  
  .event-section {
    padding: 15px;
  }
}
</style>
