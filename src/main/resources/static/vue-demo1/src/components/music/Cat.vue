<template>
  <div class="container">
    <section class="section">
      <h1 class="section-title">哈基米音乐</h1>
      <div class="image-gallery">
        <a
          v-for="(img, index) in imageList" 
          :key="'music-'+index"
          :href="links[index]"
          target="_blank"
          class="gallery-item"
        >
          <img
            :alt="'哈基米音乐 ' + (index + 1)"
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
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const imageList = ref([])
const captions=ref([
'治愈活全家哈基米音乐之哈基米溯reverse',
'【哈基米FM】带我去找哈基米',
'哈雪大帽险',
'【哈基米】武器耄耋',
'“所念皆哈基，哈基中是咪”——所念皆星河',
'🎵太空曼波 𝑺𝒑𝒂𝒄𝒆 𝑾𝒂𝒍𝒌🎵哈基米纯音乐',
'【基米核】Are You Lost（完整版）',
'「雨停之后，我就会死」',
'【补档】🎵曼波 𝑵𝒐 𝑴𝒐𝒓𝒆🎵不再曼波（完整版）',
'【清明基单品】one last kitten 再见了，所有的耄耋',
'哈基米reboot （完整版）',
'《曼波の梦灯笼》',
'【完整版】乖基米小曲之♬ Sunshine 曼波 ♬ 阳光曼波',
'哈基米：🎵𝑨𝒍𝒍 𝑾𝒆 𝑲𝒏𝒐𝒘🎵【2025哈基米拜年基单品】',
"🐱世上最小的哈基米🐱World's Smallest Violin",
'⚡冰と僕らと(memes remix)',


])

const links=ref([
'https://www.bilibili.com/video/BV1ioPAeKEuT',
'https://www.bilibili.com/video/BV1h9NqeFETn',
'https://www.bilibili.com/video/BV1gQetekE5P',
'https://www.bilibili.com/video/BV13mNWeoEkC',
'https://www.bilibili.com/video/BV1hNfqY3EMg',
'https://www.bilibili.com/video/BV1rdBZYNEu3',
'https://www.bilibili.com/video/BV1MaPte8EGd',
'https://www.bilibili.com/video/BV1asQLYYEWx',
'https://www.bilibili.com/video/BV1qzFmeQEap',
'https://www.bilibili.com/video/BV1DeRSYNEBP',
'https://www.bilibili.com/video/BV14RRcY4Exc',
'https://www.bilibili.com/video/BV1vgpTefEZk',
'https://www.bilibili.com/video/BV17DAMeQE1K',
'https://www.bilibili.com/video/BV1CmF5eTEeA',
'https://www.bilibili.com/video/BV1cUXdYAEsu',
'https://www.bilibili.com/video/BV1fPzGYKEKB',

])

const images=import.meta.glob('@/assets/哈基米音乐/哈基米音乐*.{jpg,png}', { eager: true })
onMounted(()=>{
 imageList.value = Object.values(images).map(module => module.default)
})

</script>

<style>
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

</style>