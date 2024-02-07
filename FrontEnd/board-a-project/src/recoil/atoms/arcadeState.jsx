import { atom } from 'recoil';

export const arcadListState = atom({
    key: 'arcadeListState',
    default: [],
})

export const locationState = atom({
    key: 'arcadeState',
    default: '서울시 강남구',
})

export const brandState = atom({
    key: 'brandState',
    default: '레드버튼',
})

// export const moimState = atom({
//     key: 'moimState',
//     default: {
//       userId: 10,
//       title: '',
//       content: '',
//       number: 2,
//       location: "",
//       datetime: new Date(),
//       // friends: [],
//     },
// })