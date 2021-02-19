/*=========================================================================================
  File Name: router.js
  Description: Routes for vue-router. Lazy loading is enabled.
  ----------------------------------------------------------------------------------------
  Item Name: Vuexy - Vuejs, HTML & Laravel Admin Dashboard Template
  Author: Pixinvent
  Author URL: http://www.themeforest.net/user/pixinvent
==========================================================================================*/


import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    scrollBehavior () {
        return { x: 0, y: 0 }
    },
    routes: [

        {
    // =============================================================================
    // MAIN LAYOUT ROUTES
    // =============================================================================
            path: '/',
            component: () => import('./layouts/main/Main.vue'),
            children: [
        // =============================================================================
        // Theme Routes
        // =============================================================================
              {
                path: '/admin',
                name: 'home',
                component: () => import('./views/Home.vue')
              },
              {
                path: '/admin/profil',
                name: 'page-2',
                component: () => import('./views/Page2.vue')
              },
              {
                path: '/admin/GMarques',
                name: 'Gestion de Marques',
                component: () => import('@/views/pages/GMarques/table-marques/DataListListView.vue'),
                meta: {
                    breadcrumb: [
                        { title: 'Accueil', url: '/' },
                        { title: 'Gestion de Marques',  active: true },
                    ],
                    pageTitle: 'Gestion de Marques',
                    rule: 'editor'
                },
              },
              {
                path: '/admin/GAvis',
                name: 'Gestion de Avis',
                component: () => import('./views/pages/GAvis.vue')
              },
              {
                path: '/admin/GCommandes',
                name: 'Gestion de Commandes',
                component: () => import('./views/pages/GCommandes.vue')
              },
              {
                path: '/admin/GImages',
                name: 'Gestion de Images',
                component: () => import('./views/pages/GImages.vue')
              },
              {
                path: '/admin/GNewsletter',
                name: 'Gestion de Newsletter',
                component: () => import('./views/pages/GNewsletter.vue')
              },
              {
                path: '/admin/GPartenaires',
                name: 'Gestion de Partenaires',
                component: () => import('./views/pages/GPartenaires.vue')
              },
              {
                path: '/admin/GUtilisateurs',
                name: 'Gestion de Utilisateurs',
                component: () => import('./views/pages/GUtilisateurs.vue')
              },


            ],
        },
    // =============================================================================
    // FULL PAGE LAYOUTS
    // =============================================================================
        {
            path: '/',
            component: () => import('@/layouts/full-page/FullPage.vue'),
            children: [
        // =============================================================================
        // PAGES
        // =============================================================================
              {
                path: '/login',
                name: 'page-login',
                component: () => import('@/views/auth/Login.vue')
              },
              {
                    path: '/register',
                    name: 'page-register',
                    component: () => import('@/views/auth/Register.vue'),
                    meta: {
                        rule: 'editor'
                    }
              },
              {
                path: '/error-404',
                name: 'page-error-404',
                component: () => import('@/views/pages/Error404.vue')
              },

            ]
        },

        // Redirect to 404 page, if no match found
        {
            path: '*',
            redirect: '/login'
        }
    ],
})

router.afterEach(() => {
  // Remove initial loading
  const appLoading = document.getElementById('loading-bg')
    if (appLoading) {
        appLoading.style.display = "none";
    }
})

export default router
