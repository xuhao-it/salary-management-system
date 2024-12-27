declare module 'vue-router' {
    import { Component } from 'vue'
    export interface Router {
        push(to: string | { path: string }): Promise<void>
        beforeEach(guard: NavigationGuard): void
    }
    export interface RouteRecordRaw {
        path: string
        name?: string
        component?: Component | (() => Promise<Component>)
        children?: RouteRecordRaw[]
    }
    export const createRouter: (options: any) => Router
    export const createWebHistory: (base?: string) => any
    export const RouterView: Component
    export const useRouter: () => Router  // 添加这行
    export type NavigationGuard = (
        to: any,
        from: any,
        next: (to?: string | false | void) => void
    ) => any
}
