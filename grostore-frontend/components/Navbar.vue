<template>
  <nav 
    class="navbar is-transparent is-fixed-top" 
    role="navigation" 
    aria-label="main navigation">
    <div class="navbar-brand">
      <a 
        class="navbar-item" 
        href="https://bulma.io">
        <img src="https://minutka-nn.ru/images/minutka-logo.png">
        <!--<b class="is-uppercase has-text-danger">Свободная Минутка</b>-->
      </a>
      
      <a
        role="button"
        class="navbar-burger burger"
        aria-label="menu"
        aria-expanded="false"
        data-target="navbarBasicExample"
      >
        <span aria-hidden="true"/>
        <span aria-hidden="true"/>
        <span aria-hidden="true"/>
      </a>
    </div>

    <div 
      id="navbarBasicExample" 
      class="navbar-menu">
      <!--<div class="navbar-start">
        <div class="navbar-item has-dropdown is-hoverable">
          <a class="navbar-link">Каталог товаров</a>
          <div class="navbar-dropdown">
            <a class="navbar-item">About</a>
            <a class="navbar-item">Jobs</a>
            <a class="navbar-item">Contact</a>
            <hr class="navbar-divider">
            <a class="navbar-item">Report an issue</a>
          </div>
        </div>
        <a class="navbar-item">Доставка</a>
        <a class="navbar-item">Оплата</a>
      </div>-->
      <div class="navbar-end">
        <div class="navbar-item">
          <div class="buttons">
            <a class="button is-danger">
              <strong>Sign up</strong>
            </a>
            <a class="button is-light">Log in</a>
          </div>
        </div>
      </div>
    </div>
  </nav>
</template>

<style>
.navbar-brand .navbar-item > img {
  margin-right: 10px;
}
.is-transparent {
  background-color: transparent;
}
</style>
