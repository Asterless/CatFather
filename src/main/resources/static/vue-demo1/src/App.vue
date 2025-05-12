<template>
  <div class="wiki-container">
    <div class="api-request-section">
      <input 
        v-model="inputValue" 
        @input="handleInput"
        placeholder="输入表情包id" 
        class="api-input"
      />
      <button @click="fetchData" :disabled="isLoading" class="api-button">
        {{ isLoading ? '请求中...' : '获取数据' }}
      </button>
      <img v-if="imageSrc" :src="imageSrc" alt="动态图片" @error="handleImageError">
      
      <div v-if="errorMessage" class="error-message">
        {{ errorMessage }}
      </div>
    </div>

    <div class="image-gallery">
      <router-view @image-clicked="openLightbox"></router-view>
    </div>

    <div class="right-sidebar">
      <div class="collapsible-tags button1" >
        <button class="toggle-button " @click="isCollapsed = !isCollapsed">
          {{ isCollapsed ? '耄耋表情包' : '哈！！！！！' }}
          <span class="arrow" :class="{ rotated: !isCollapsed }">▼</span>
        </button>
        <div class="tags-container" v-show="!isCollapsed">
          <nav class="tags-nav">
            <RouterLink
              v-for="item in navItems"
              :key="item.name"
              :to="'/' + item.name"
              v-slot="{ isActive }"
            >
              <span class="wiki-tag" :class="{ 'active': isActive }">
                {{ item.label }}
              </span>
            </RouterLink>
          </nav>
        </div>
      </div>

      <div class="collapsible-tags button2">
        <button class="toggle-button " @click="isBronCollapsed = !isBronCollapsed">
          {{ isBronCollapsed ? '耄耋编年史' : '哈！！！！！' }}
          <span class="arrow" :class="{ rotated: !isBronCollapsed }">▼</span>
        </button>
        <div class="tags-container" v-show="!isBronCollapsed">
          <nav class="tags-nav">
            <RouterLink
              v-for="item in History"
              :key="item.name"
              :to="'/'+item.name"
              v-slot="{ isActive }"
            >
              <span class="wiki-tag" :class="{ 'active': isActive }">
                {{ item.label }}
              </span>
            </RouterLink>
          </nav>
        </div>
      </div>

      <div class="collapsible-tags button3">
        <button class="toggle-button " @click="isMusic=!isMusic">
          {{ isMusic?'哈基米音乐':'哈！！！！！' }}
          <span class="arrow" :class="{ rotated: !isMusic }">▼</span>
        </button>
        <div class="tags-container" v-show="!isMusic">
          <nav class="tags-nav">
            <RouterLink
              v-for="item in Musics"
              :key="item.name"
              :to="'/'+item.name"
              v-slot="{ isActive }"
            >
              <span class="wiki-tag" :class="{ 'active': isActive }">
                {{ item.label }}
              </span>
            </RouterLink>
          </nav>
        </div>
      </div>
    </div>

    <div v-if="isLightboxVisible" class="lightbox-overlay" @click="closeLightbox">
      <div class="lightbox-content" @click.stop>
        <img :src="lightboxImageUrl" alt="放大查看" class="lightbox-image" />
        <button class="lightbox-close" @click="closeLightbox" aria-label="关闭">&times;</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
import axios from 'axios';

const isCollapsed = ref(true);
const isBronCollapsed = ref(true);
const isLightboxVisible = ref(false);
const lightboxImageUrl = ref('');
const isMusic = ref('');

const navItems = [
  
  { name: 'gushen', label: '古神耄耋' },
  { name: 'junshi', label: '军事耄耋' },
  { name: 'putong', label: '普通耄耋' },
  { name: 'little', label: '小耄耋' },
  { name: 'beside', label: '未定义耄耋' },
  { name: 'grow', label: '养成' }
];

const History = [
  {name:'history',label:'正史'},
  {name:'besides1',label:'野史1'},
  {name:'besides2',label:'野史2'},
];

const Musics = [
  {name:'list',label:'排行榜'},
  {name:'cat',label:'音乐'}
];

const inputValue = ref('');
const apiResponse = ref(null);
const isLoading = ref(false);
const errorMessage = ref('');
const imageSrc = ref('');

const openLightbox = (imageUrl) => {
  lightboxImageUrl.value = imageUrl;
  isLightboxVisible.value = true;
};

const closeLightbox = () => {
  isLightboxVisible.value = false;
  lightboxImageUrl.value = '';
};

const handleImageError = () => {
  errorMessage.value = '图片加载失败';
  imageSrc.value = '';
};

const fetchData = async () => {
  if (!inputValue.value.trim()) {
    errorMessage.value = '请输入有效id';
    return;
  }

  isLoading.value = true;
  errorMessage.value = '';
  apiResponse.value = null;
  imageSrc.value = '';
  
  try {
    const response = await axios.get(`http://localhost:8212/api/images/${inputValue.value}`);
    apiResponse.value = response.data;
    
    const filePath = response.data.filePath;
    if (!filePath) throw new Error('耄耋不知道哦');
    
    const safePath = filePath.replace(/\\/g, '/').trim();
    const basePath = '/src/assets/';
    const extensions = ['.png', '.jpg'];
    const pathVariations = [
      `${safePath}`,
      `${safePath.split('/').pop()}`
    ];
    
    for (const path of pathVariations) {
      for (const ext of extensions) {
        const fullPath = `${basePath}${path}${ext}`;
        try {
          const imgUrl = new URL(fullPath, import.meta.url).href;
          const img = new Image();
          img.src = imgUrl;
          
          await new Promise((resolve, reject) => {
            img.onload = resolve;
            img.onerror = reject;
          });
          
          imageSrc.value = imgUrl;
          return;
        } catch {
          continue;
        }
      }
    }
    
    errorMessage.value = `找不到匹配的图片文件，已尝试路径: ${pathVariations.join(', ')}`;
  } catch (error) {
    errorMessage.value = (error.response?.data?.message || error.message);
    console.error('错误喵:', error);
  } finally {
    isLoading.value = false;
  }
};

let debounceTimer = null;
const handleInput = () => {
  clearTimeout(debounceTimer);
  debounceTimer = setTimeout(fetchData, 500);
};
</script>

<style scoped>
:global(html) {
  box-sizing: border-box;
}

:global(*),
:global(*::before),
:global(*::after) {
  box-sizing: inherit;
}

:global(body) {
  margin: 0;
  padding: 0;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue",
               Arial, "Noto Sans", sans-serif, "Apple Color Emoji", "Segoe UI Emoji",
               "Segoe UI Symbol", "Noto Color Emoji";
  line-height: 1.6;
  color: #333;
  background-color: #f4f6f8;
}

.wiki-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
  position: relative;
  padding: 30px;
}

.image-gallery {
  width: 100%;
  min-height: 400px;
  background-color: #ffffff;
  border-radius: 12px;
  margin: 0 auto 30px;
  padding: 25px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.08);
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 20px;
}

:deep(.gallery-item-image) {
  cursor: pointer;
  transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
  display: block;
  width: 100%;
  height: auto;
  border-radius: 8px;
}

:deep(.gallery-item-image:hover) {
  transform: scale(1.05);
  box-shadow: 0 6px 18px rgba(0,0,0,0.12);
}

.lightbox-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.88);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 2000;
  padding: 20px;
  box-sizing: border-box;
}

.lightbox-content {
  position: relative;
  background-color: #fff;
  padding: 15px;
  border-radius: 10px;
  box-shadow: 0 8px 25px rgba(0,0,0,0.25);
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  max-width: calc(100vw - 40px);
  max-height: calc(100vh - 40px);
  overflow: hidden;
}

.lightbox-image {
  display: block;
  max-width: 100%;
  max-height: calc(100vh - 100px);
  object-fit: contain;
  border-radius: 6px;
  user-select: none;
}

.lightbox-close {
  position: absolute;
  top: -15px;
  right: -15px;
  background: #470d7d;
  color: white;
  border: 2px solid white;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  font-size: 22px;
  line-height: 36px;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 2px 8px rgba(0,0,0,0.3);
  transition: background-color 0.2s ease, transform 0.2s ease;
  z-index: 2001;
}

.lightbox-close:hover {
  background: #e74c3c;
  transform: scale(1.1);
}

.right-sidebar {
  position: fixed;
  right: 20px;
  top: 20%; /* 改为50%居中 */
  transform: translateY(-50%);
  display: flex;
  flex-direction: column;
  gap: 15px;
  z-index: 110;
  max-height: 90vh; /* 限制最大高度 */
  
}

.collapsible-tags {
  background: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
  width: 200px;
  transition: all 0.3s ease;
  /* 确保每个折叠面板不会影响整体布局 */
  position: relative;
}

.toggle-button {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  padding: 15px 10px;
  background-color: #3b7aa1;
  border: 1px solid #478a2d;
  border-radius: 12px;
  font-size: 18px;
  font-weight: 500;
  color: #ffffff;
  transition: all 0.3s ease;
  cursor: pointer;
}
.button1{
   z-index: 90;
}
.button2{
  z-index:80
}
.button{
z-index:70
}
.toggle-button:hover {
  background-color: #dce3e9;
  border-color: #c5ced6;
}

.arrow {
  transition: transform 0.3s;
  font-size: 12px;
  margin-left: 8px;
}

.arrow.rotated {
  transform: rotate(180deg);
}

.tags-container {
  padding: 15px;
  background: #ffffff;
  border-radius: 0 0 12px 12px;
  border-top: 1px solid #e9edf1;
  /* 限制最大高度并添加滚动 
  overflow-y: auto;
  max-height: 300px;
  /* 确保内容展开不会影响容器位置 */
  position: absolute;
  
}

.tags-nav {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.wiki-tag {
  display: inline-block;
  padding: 8px 15px;
  background-color: #e9edf1;
  color: #34495e;
  border-radius: 18px;
  font-size: 14px;
  text-decoration: none;
  transition: all 0.2s ease;
  border: 1px solid #d1d9e0;
  cursor: pointer;
}

.wiki-tag:hover {
  background-color: #dce3e9;
  border-color: #c5ced6;
  transform: translateX(3px);
}

.wiki-tag.active {
  background-color: #4285f4;
  color: white;
  border-color: #3a75d9;
  font-weight: 500;
}

.api-request-section {
  max-width: 600px;
  margin: 2rem auto;
  padding: 1.5rem;
  background-color: #f8f9fa;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.api-input {
  width: 100%;
  padding: 12px 16px;
  margin-bottom: 1rem;
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  font-size: 16px;
  transition: all 0.3s ease;
  box-sizing: border-box;
}

.api-input:focus {
  border-color: #4a90e2;
  outline: none;
  box-shadow: 0 0 0 3px rgba(74, 144, 226, 0.2);
}

.api-button {
  width: 100%;
  padding: 12px;
  background-color: #4a90e2;
  color: white;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
}

.api-button:hover:not(:disabled) {
  background-color: #3a7bc8;
  transform: translateY(-1px);
}

.api-button:disabled {
  background-color: #a0c4ff;
  cursor: not-allowed;
  opacity: 0.7;
}

img {
  display: block;
  max-width: 100%;
  height: auto;
  margin: 1.5rem auto 0;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.error-message {
  margin-top: 1rem;
  padding: 12px;
  background-color: #ffebee;
  color: #d32f2f;
  border-radius: 8px;
  border-left: 4px solid #d32f2f;
  font-size: 14px;
  animation: fadeIn 0.3s ease;
}

@keyframes fadeIn {
  from { opacity: 0; transform: translateY(-10px); }
  to { opacity: 1; transform: translateY(0); }
}

@media (max-width: 768px) {
  .right-sidebar {
    position: static;
    transform: none;
    width: 100%;
    margin-top: 20px;
    gap: 15px;
  }
  
  .collapsible-tags {
    width: 100%;
  }
  
  .wiki-container {
    padding: 15px;
  }
  
  .image-gallery {
    padding: 15px;
    margin-bottom: 20px;
  }
}


</style>