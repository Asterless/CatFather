<template>
  <div class="container">
    <!-- 哈基米音乐排行榜 -->
    <section class="section">
      <h1 class="section-title">哈基米音乐排行榜</h1>
      <div class="image-gallery">
        <a 
          v-for="(img, index) in imageList" 
          :key="'music-'+index"
          :href="links[index]"
          target="_blank"
          class="gallery-item"
        >
          <img
            :alt="'哈基米音乐排行榜 ' + (index + 1)"
            class="gallery-image"
            :src="img"
            loading="lazy"
          />
          <div class="image-caption">
            {{ captions[index] || '哈基米音乐 ' + (index + 1) }}
          </div>
        </a>
      </div>
    </section>

    <!-- 金基米奖 -->
    <section class="section golden-section">
      <h1 class="section-title golden-title">金基米奖</h1>
      <div class="image-gallery">
        <a 
          v-for="(img, index) in imageList1" 
          :key="'award-'+index"
          :href="links1[index]"
          target="_blank"
          class="gallery-item golden-item"
        >
          <img
            :alt="'金基米'+(index+1)"
            class="gallery-image"
            :src="img"
            loading="lazy"
          />
          <div class="image-caption">
            {{ jinjimi[index] }}
          </div>
        </a>
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const imageList1 = ref([])
const imageList = ref([])

const captions = ref([
  '2024年10月',
  '2024年11月',
  '2024年12月',
  '2025年1月',
  '2025年2月',
  '2025年3月',
  '2025年4月',
  '2024年夏日特辑',
])

const jinjimi = ref([
  '【THA】年度金基米奖提名',
  '【THA】年度金基米奖获奖作品'
])

const links = ref([
  'https://www.bilibili.com/video/BV1dXzAYrEXY',
  'https://www.bilibili.com/video/BV1jJiRYLEZY',
  'https://www.bilibili.com/video/BV1g96ZYWE73',
  'https://www.bilibili.com/video/BV1trNieJEwJ',
  'https://www.bilibili.com/video/BV1C69zY4EZL',
  'https://www.bilibili.com/video/BV1TddXYrEgh',
  'https://www.bilibili.com/video/BV1HzV5zFEoV',
  'https://www.bilibili.com/video/BV12GzfY7EoL',
])

const links1 = ref([
  'https://www.bilibili.com/video/BV1DXruYXEnE',
  'https://www.bilibili.com/video/BV1nuFve9Eu1'
])

const images = import.meta.glob('@/assets/哈基米音乐排行榜/哈基米音乐排行榜*.{jpg,png}', { eager: true })
const images2 = import.meta.glob('@/assets/哈基米音乐排行榜/金基米奖*.{jpg,png}', { eager: true })

onMounted(() => {
  imageList.value = Object.values(images).map(module => module.default)
  imageList1.value = Object.values(images2).map(module => module.default)
})
</script>

<style scoped>
.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.section {
  margin-bottom: 40px;
}

.section-title {
  font-size: 2rem;
  color: #333;
  text-align: center;
  margin-bottom: 30px;
  position: relative;
  padding-bottom: 10px;
}

.section-title::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  width: 80px;
  height: 3px;
  background: #333;
}

.golden-title {
  color: #FFD700;
}

.golden-title::after {
  background: #FFD700;
}

.image-gallery {
  display: grid;
  /* 主要修改：将 auto-fill 改为固定 repeat(3, 1fr) */
  grid-template-columns: repeat(3, minmax(200px, 1fr));
  gap: 25px;
  padding: 15px;
}
.gallery-item {
  display: block;
  text-decoration: none;
  transition: all 0.3s ease;
}

.gallery-image {
  width: 100%;
  height: auto;
  max-width: 100%;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease;

  object-fit: cover;
}

.gallery-item:hover .gallery-image {
  transform: scale(1.05);
  box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
}

.image-caption {
  font-size: 1.1rem;
  color: #333;
  text-align: center;
  margin-top: 12px;
  font-weight: 600;
  transition: all 0.3s ease;
  padding: 0 5px;
}

.gallery-item:hover .image-caption {
  color: #000;
  transform: translateY(3px);
}

/* 金基米奖特殊样式 */
.golden-section {
  border-top: 1px solid rgba(255, 215, 0, 0.3);
  padding-top: 30px;
}

.golden-item .image-caption {
  position: relative;
}

.golden-item .image-caption::before {
  content: "★ ";
  color: #FFD700;
}

.golden-item .image-caption::after {
  content: " ★";
  color: #FFD700;
}

/* 响应式调整 */
@media (max-width: 768px) {
  .image-gallery {
    grid-template-columns: repeat(3, 1fr);
  }
}

@media (max-width: 480px) {
  .image-gallery {
    grid-template-columns: 3fr;
  }
  
  .section-title {
    font-size: 1.5rem;
  }
}
</style>