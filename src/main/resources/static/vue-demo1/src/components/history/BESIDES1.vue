<template>
  <div class="philosophy-container">
    <h1 class="title">《猫爹的哈基米主义：虐与意志的哲学链条》</h1>

    <section class="chapter">
      <h2 class="chapter-title">第一环：《在虐人中成为哈吉米主义者》</h2>
      <p class="chapter-content">赤马出版社推出的"二十一世纪中文哲学丛书"开篇，猫爹以《存在之茧与生存之刃》为题，探讨"哈吉米现象"的哲学意涵。封面采用法文"L'être et le miel dans la condition humaine"（存在与蜜糖于人类境遇中）与中文形成互文，展现生存困境与甜蜜悖论的双重面相。暗红色调隐喻黎明前的淬火，而"MaoDIE"的拼写异化（猫爹/毛蝶）则象征思想破茧的蜕变过程。书中提出，唯有在直面生存的锋利真实时，个体方能触摸存在的本真质地，这种对存在本质的不懈追问本身，就是最深刻的哲学实践。</p>
      
      <div class="image-wrapper">
        <img :src="imagesList[3]" alt="哈吉米主义封面" class="book-cover" />
      </div>
    </section>

    <section class="chapter">
      <h2 class="chapter-title">第二环：《哈基米哲学中的发生问题》</h2>
      <p class="chapter-content">哈基米主编的研讨班论文集进一步体系化猫爹的思想。封面标题"发生问题"指向哈基米主义的起源：虐人行为如何从个体暴力升华为集体哲学。副标题"Maudie, Mahimi"将猫爹（Maodie）与哈基米（Hakimi）的名字并置，揭示两者实为同一思想谱系的两极——前者是实践者，后者是理论家。纯黑背景中的白色文字象征极端对立的辩证统一。</p>
      <div class="image-wrapper">
        
        <img :src="imagesList[2]" alt="耆耋意志封面" class="book-cover" />
      </div>
    </section>

    <section class="chapter">
      <h2 class="chapter-title">第三环：《耆耋意志》</h2>
      <p class="chapter-content">猫爹在另一部著作中转向内在性的深层勘探，德语标题"Die Alchemie des Katzengeistes"（猫之精神的炼金术）暗指某种自我提纯的隐秘过程。书脊烫银的螺旋纹样暗示着思想在反复折叠中产生的质变，素白封面上的细微皱褶则隐喻精神在经历无形锻打后留下的智慧刻痕。  

作为"二十一世纪中文哲学丛书"的进阶理论，该书探讨"反向滋养"的奇特现象——某些看似破坏性的精神摩擦，反而能催生更致密的认知结晶。书中以老者般的沉静笔触描述：当个体持续面对自身认知的断裂带，那些裂隙处会逐渐生长出新的思维骨架，最终形成某种"经历过所有否定后依然成立的肯定"。  

这种理论不强调对抗或征服，而是关注"精神物质如何在内在压力下自我重组"，就像古老的锻造术能让金属在反复淬火后获得独特的纹理与韧性。</p>
      <div class="image-wrapper">
        <img :src="imagesList[1]" alt="发生问题封面" class="book-cover" />
      </div>
    </section>

    <section class="chapter">
      <h2 class="chapter-title">终环：《普通哈气学教程》</h2>
      <p class="chapter-content">哈基米主编的《普通哈气学教程》将晦涩理论转化为可操作的生存技艺。"哈气"的谐音游戏既指向某种呼吸韵律的调控，也暗喻精神淬火时必要的宣泄与回火。齐野出版社的命名（"齐"指向重复的韵律，"野"暗示未驯服的原始力）揭示这套方法的辩证内核——在规律性的自我磨砺中，让野性升华为更精纯的存在状态。

封面采用红黑渐层波纹，既像炉中金属的氧化色变，又令人联想到思想在高温与冷却间的无数次循环。书中提出"自主呼吸"概念：当个体有意识地调节自身承受与释放的节奏，便能像熟练匠人般，将每一次精神锻打都转化为塑造内在形态的契机。这种在不自由中寻找自主张力的智慧，恰与猫爹早期论述中"存在之茧"的隐喻形成隐秘呼应——茧壳的束缚本身，正是羽化必需的成型模具。</p>
      <div class="image-wrapper">
        <img :src="imagesList[0]" alt="哈气学教程封面" class="book-cover" />
      </div>
    </section>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const imagesList = ref([])
const images = import.meta.glob('@/assets/野史1/野史(*).{jpg,png}', { eager: true })

onMounted(() => {
  for (const path in images) {
    imagesList.value.push(images[path].default)
  }
})
</script>

<style scoped>
.philosophy-container {
  max-width: 900px;
  margin: 0 auto;
  padding: 2rem;
  font-family: "Noto Serif SC", serif;
  color: #333;
  line-height: 1.8;
}

.title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 3rem;
  color: #8b0000;
  border-bottom: 2px solid #d4a76a;
  padding-bottom: 1rem;
}

.chapter {
  margin-bottom: 4rem;
  background: #fff9f0;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
}

.chapter-title {
  font-size: 1.5rem;
  color: #8b0000;
  margin-bottom: 1.5rem;
  border-left: 4px solid #d4a76a;
  padding-left: 1rem;
}

.chapter-content {
  text-align: justify;
  text-indent: 2em;
  margin-bottom: 2rem;
}

.image-wrapper {
  text-align: center;
  margin: 2rem 0;
}

.book-cover {
  max-width: 300px;
  height: auto;
  border: 5px solid #fff;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease;
}

.book-cover:hover {
  transform: scale(1.03);
}

@media (max-width: 768px) {
  .philosophy-container {
    padding: 1rem;
  }
  
  .chapter {
    padding: 1.5rem;
  }
  
  .book-cover {
    max-width: 200px;
  }
}
</style>