/// <reference types="vite/client" />

declare interface ImportMetaEnv {
  readonly VITE_APP_TITLE: string
  readonly VITE_API_BASE_URL: string
}

declare interface ImportMeta {
  readonly env: ImportMetaEnv
}
