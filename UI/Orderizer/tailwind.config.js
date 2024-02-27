/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        mainColor: "#1f2937",
        textColor: "#5cab7d",
      },
    },
  },
  plugins: [],
};
