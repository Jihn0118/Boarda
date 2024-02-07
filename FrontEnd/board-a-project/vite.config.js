import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";

const isProd = process.env.NODE_ENV === "production";

// https://vitejs.dev/config/
export default defineConfig({
  base: "./",
  plugins: [react()],
  assetsInclude: ["**/*.jpg", "**/*.png", "**/*.svg"],
  server: {
    port: 3000,
    host: "0.0.0.0",
    hmr: !isProd,
  },
  resolve: {
    alias: [{ find: "@", replacement: path.resolve(__dirname, "src") }],
  },
  preview: {
    port: 3000,
  },
});
