/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './src/**/*.{html,ts}'
  ],
  theme: {
    extend: {
      colors: {
        primaryt: '#B30E15',
        secondaryt: '#ff6d43',
        dangert: '#F51414',
        infot: '#ececed',
        blurt: '#77929F',
        cleart: '#ffffff',
        primaryhover: '#C91E28',
        secondaryhover: '#E55737',
        dangerhover: '#E01414',
        infohover: '#a5a5a6',
        blurhover: '#647C87',
        clearhover: '#D6D6D6'
      }
    },
  },
  plugins: [],
}