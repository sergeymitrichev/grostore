<template>
  <div>
    <navbar/>
    <header>
      <section class="hero is-fullheight">
        <div class="hero-body">
          <div class="container">
            <h2 class="subtitle">Привезем продукты из супермаркета домой и на работу</h2>
            <h1 class="title is-1">Дарим свободное время</h1>
            <h2 class="subtitle is-2">Для родных и близких</h2>
            <a 
              href="#" 
              class="button is-danger is-rounded is-medium">
              <span>
                Попробовать сейчас
                <span 
                  style="font-size:undefinedrem" 
                  role="img" 
                  aria-label="emoji">
                  🤝
                </span>
              </span>
            </a>
          </div>
        </div>
      </section>
    </header>
    <section>
      2
    </section>
  </div>
</template>

<script>
import Navbar from '@/components/Navbar'

export default {
  layout: 'home',
  name: 'HomePage',
  components: { Navbar },
  data() {
    return {}
  }
}
</script>

<style scoped>
.hero {
  background-color: #fff;
  background-image: url('/hero.jpeg');
  background-repeat: no-repeat;
  background-position: 140% 0%;
  background-size: auto 100%;
}
</style>
