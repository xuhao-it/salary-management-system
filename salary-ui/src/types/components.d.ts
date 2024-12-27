declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const componentType: DefineComponent<{}, {}, any>
  export default componentType
}
